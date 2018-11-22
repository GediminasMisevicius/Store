package lt.bit.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import lt.bit.core.NotFoundException;


public class ProductServiceTest extends UnitTestBase {

	private ProductService service;
	@Mock
	private ProductJpaRepository rep;

	@Before
	public void setUp() {
		service = new ProductService(rep);
	}

	@Test
	public void givenNewProduct_whenSaving_thenProductIsSavedToDB() {
		// given
		Product p = ProductTestFixture.filledNewProduct();
		when(rep.save(p)).thenReturn(p);
		// when
		UUID uuid = service.saveProduct(p);
		// then
		verify(rep).save(p);
		assertThat(uuid).isNotNull();

	}

	@Test
	public void givenSomeProductsExist_whenGettingProducts_thenReturnProductMap() {
//		// given
//		Product p = ProductTestFixture.filledNewProduct();
//		when(rep.findAll()).thenReturn(Collections.singletonList(p));
//		// when
//		Map<UUID, Product> products = service.getProducts();
//		// then
//		assertThat(products.get(p.getId())).isEqualToComparingFieldByField(p);
	}

	@Test
	public void givenProductExistsInDB_whenDeletingProduct_thenDelete() {
		// given
		Product p = ProductTestFixture.filledNewProduct();
		rep.save(p);
		// when
		service.deleteProduct(p.getId().toString());
		// then
		verify(rep).deleteById(p.getId());
	}

	@Test
	public void givenProductExists_whenGettingProductById_thenReturnProduct() {
		// given
		Product p = ProductTestFixture.filledNewProduct();
		when(rep.getById(p.getId())).thenReturn(Optional.of(p));
		// when
		Product pro = service.getProductbyId(p.getId());
		// then
		assertThat(pro).isEqualToComparingFieldByField(p);
	}

	@Test
	public void givenProductDoesntExists_whenGettingProductById_thenThrowException() {
		// given
		UUID id = UUID.randomUUID();
		when(rep.getById(id)).thenReturn(Optional.empty());
		// when
		Throwable exception = catchThrowable(() -> service.getProductbyId(id));
		// then
		assertThat(exception).isInstanceOf(NotFoundException.class);
		assertThat(exception.getMessage()).isEqualTo("entry not found");
	}

	@Test
	public void givenProductExists_whenUpdatingProduct_thenReturnUpdatedProduct() {
		// given
		Product p = ProductTestFixture.filledNewProduct();
		Product p2 = ProductTestFixture.filledNewProduct();
		when(rep.findAll()).thenReturn(Collections.singletonList(p));
		when(rep.save(p2)).thenReturn(p2);
		// when
		UUID uuid = service.updateProduct(p.getId().toString(), p2);
		// then
		verify(rep).save(p2);
		assertThat(uuid).isNotNull();
	}
	
	@Test
	public void test() {
		Product empty = new Product();
		assertThat(empty.emptyProduct(pr -> pr.getName() == null )).isTrue();
	}

}
