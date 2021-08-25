package ro.mfl.nw.dataaccess.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ro.mfl.nw.dataaccess.domain.Product;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataR2dbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testLoadById() {
        Mono<Product> mono = productRepository.findById(1);
        StepVerifier.create(mono)
                .assertNext( p -> {
                    assertTrue(p.getCategoryId() > 0);
                    assertNotNull(p.getUnitsInStock(), "units in stock");
                    assertNotNull(p.getProductName(), "product name");
                })
                .verifyComplete();
    }

}
