package ro.mfl.nw.dataaccess.repo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ro.mfl.nw.dataaccess.domain.Category;

@DataR2dbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
class CategoryRepositoryTests {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	void testLoad() {
		Mono<Category> mono = categoryRepository.findById(1);
		Category c = mono.block();
		assertEquals(1, c.getCategoryId(), "Id should be 1");
	}
	
	@Test
	void testLoadAll() {
		Flux<Category> flux = categoryRepository.findAll();
		StepVerifier.create(flux)
		.assertNext(  value -> assertNotNull(value.getCategoryId()) )
		.assertNext(  value -> assertNotNull(value.getCategoryId()) );
	}
	
	@Test
	void testSave() {
		Category c = new Category();
		c.setCategoryName("test reactive");
		c.setDescription("test reactive description");
		c = categoryRepository.save(c).block();
		assertEquals(c.getCategoryName(), "test reactive");
		categoryRepository.delete(c);
	}
}
