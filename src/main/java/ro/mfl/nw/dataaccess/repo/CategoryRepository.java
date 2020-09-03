package ro.mfl.nw.dataaccess.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import ro.mfl.nw.dataaccess.domain.Category;

public interface CategoryRepository extends ReactiveCrudRepository<Category, Integer> {

}
