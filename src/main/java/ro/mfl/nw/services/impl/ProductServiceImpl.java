package ro.mfl.nw.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ro.mfl.nw.dataaccess.domain.Category;
import ro.mfl.nw.dataaccess.domain.Product;
import ro.mfl.nw.dataaccess.repo.CategoryRepository;
import ro.mfl.nw.dataaccess.repo.ProductRepository;
import ro.mfl.nw.services.ProductService;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Mono<Product> getProductInformation(Integer id) {
        log.debug("get product information {}", id);
        if(id < 1) throw new IllegalArgumentException();
        Mono<Product> mono = productRepository.findById(id).flatMap( product -> {
            Mono<Category> c = categoryRepository.findById(product.getCategoryId());

            return Mono.zip(Mono.just(product), c, (product1, category) -> {
                log.debug("category {} {}", category.getCategoryId(), category.getCategoryName());
                product1.setCategory(category);
                return product1;
            });
        });
        return mono;
    }

    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
