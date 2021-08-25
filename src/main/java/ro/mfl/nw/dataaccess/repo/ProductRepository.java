package ro.mfl.nw.dataaccess.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ro.mfl.nw.dataaccess.domain.Product;

public interface ProductRepository extends ReactiveCrudRepository<Product, Integer>  {
}
