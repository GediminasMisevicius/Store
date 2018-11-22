package lt.bit.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductJpaRepositoryTest {

	@Autowired
	private ProductJpaRepository rep;
	
	private UUID id;

	@Before
	public void setUp() {
		Product p = ProductTestFixture.filledNewProduct();
		id = p.getId();
		rep.saveAndFlush(p);
		rep.saveAndFlush(ProductTestFixture.filledNewProduct("antras", 50f));
		rep.saveAndFlush(ProductTestFixture.filledNewProduct("trecias", 0.5f));
	}

	@Test
	public void givenProductCreated_whenGettingById_thenReturnSameProduct() {
		// given
		Product p = ProductTestFixture.filledNewProduct();
		UUID id = p.getId();
		rep.saveAndFlush(p);
		// when
		Optional<Product> actual = rep.getById(id);
		// then
		assertThat(actual.get()).isEqualToComparingFieldByField(p);
	}

	@Test
	public void givenProductExists_whenDeletingById_thenProductIsDeleted() {
		// given
		assertThat(rep.getById(id)).isNotEmpty();
		//when
		rep.deleteById(id);
		//then
		assertThat(rep.getById(id)).isEmpty();
	}

}
