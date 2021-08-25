package ro.mfl.nw.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ro.mfl.nw.dataaccess.domain.Product;


public interface ProductService {

    Mono<Product> getProductInformation(Integer id);
    Flux<Product> getAllProducts();

}
