package ro.mfl.nw.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ro.mfl.nw.dataaccess.domain.Product;
import ro.mfl.nw.services.ProductService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    void testGetProductInformation() {
        Mono<Product> mono = productService.getProductInformation(1);
        StepVerifier.create(mono)
                .assertNext( product -> {
                    assertNotNull(product.getCategory());
                    assertTrue(product.getProductName().length() > 0);
                    assertTrue(product.getCategory().getCategoryName().length() > 0);
                })
                .verifyComplete();
    }
}
