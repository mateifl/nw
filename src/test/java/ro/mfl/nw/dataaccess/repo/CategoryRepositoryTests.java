package ro.mfl.nw.dataaccess.repo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ro.mfl.nw.dataaccess.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class CategoryRepositoryTests {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	void testLoad() {
		Mono<Category> mono = categoryRepository.findById(1);
		StepVerifier.create(mono)
					.assertNext(value -> {
						assertEquals(1, value.getCategoryId(), "Id should be 1");
						assertEquals("Beverages", value.getCategoryName());
						assertTrue(value.getDescription().length() > 0);
						})
					.verifyComplete();
	}
	
	@Test
	void testLoadAll() {
		Mono<Long> mono = categoryRepository.findAll().count();
		StepVerifier.create(mono)
				.assertNext(  value -> assertTrue(value > 0) )
				.verifyComplete();
	}
	
	@Test
	void testSave() {
		Category c = new Category();
		c.setCategoryName("test reactive");
		c.setDescription("test reactive description");
		Mono<Category> mono = categoryRepository.save(c);
		StepVerifier.create(mono)
					.assertNext(v -> {
						assertEquals(c.getCategoryName(), v.getCategoryName());
						assertNotNull(v.getCategoryId());
					})
					.expectComplete()
					.verify();

	}

}
