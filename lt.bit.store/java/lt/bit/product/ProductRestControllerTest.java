package lt.bit.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.ImmutableMap;

public class ProductRestControllerTest extends UnitTestBase {

	private ProductRestController controller;
	@Mock
	private ProductService service;

	@Before
	public void setUp() {
		controller = new ProductRestController(service);
	}

	@Test
	public void givenNoProductsFound_whenGettingProducts_thenStatus404IsReturned() {
//		when(service.getProducts()).thenReturn(Collections.emptyMap());
//		assertThat(controller.getProductResponseEntity()
//				.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void givenProductsExist_whenGettingProducts_thenResponseWithStatus200IsReturned() {
//		//given
//		Product pr = new Product(); 
//		Product expectedProduct = new Product();
//		BeanUtils.copyProperties(pr, expectedProduct);
//		
//		when(service.getProducts()) 
//		.thenReturn(ImmutableMap.of(pr.getId(), pr));
//		//when
//		ResponseEntity<Map<UUID, Product>> response =
//				controller.getProductResponseEntity();
//		//then
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//		
//		assertThat(response.getBody().get(pr.getId()))
//		.isEqualToComparingFieldByField(expectedProduct);
	}

	@Test
	public void whenGettingProductById_thenFilledProductIsReturned() {
		Product a = new Product().setId(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")).setName("Pienas");
		controller.addProductToMap(a);
//		Map<UUID, Product> map = (Map<UUID, Product>) ReflectionTestUtils.getField(controller.getClass(), "PRODUCTS");
		assertThat(controller.getProductById("38400000-8cf0-11bd-b23e-10b96e4ef00d")).isNotNull();
	}

	@Test
	public void whenGettingProductByID_thenNullIsReturned() {
		assertThat(controller.getProductById("54947df8-0e9e-4471-a2f9-9af509fb5889").getBody()).isNull();
	}

	@Test
	public void whenPostingProduct_thenProductServiceIsCalled() {
		//given
		Product a = new Product()
				.setId(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"))
				.setName("Pienas");
		when(service.saveProduct(a)) 
		.thenReturn(UUID.randomUUID());
		
		//when (controller.addProductToMap(a)) and then (assertThat)
		assertThat(controller.addProductToMap(a)).isNotNull();
//		verify(service).saveProduct(a);
	}

	@Test
	public void whenDeletingProduct_thenProductDeletedFromMap() {
//		Product a = new Product().setId(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")).setName("Pienas");
//		controller.addProductToMap(a);
//
//		controller.deleteProductFromMap("38400000-8cf0-11bd-b23e-10b96e4ef00d");
//
//		assertThat(controller.getProductResponseEntity().getBody()
//				.get(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"))).isNull();
	}

	@Test
	public void whenUpdatingProduct_thenProductMapUpdated() {
//		Product a = new Product().setId(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")).setName("Pienas");
//		controller.addProductToMap(a);
//		Product b = new Product().setId(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")).setName("Sausainis");
//
//		controller.updateProduct("38400000-8cf0-11bd-b23e-10b96e4ef00d", b);
//
//		assertThat(controller.getProductResponseEntity().getBody()
//				.get(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")).getName()).isEqualTo("Sausainis");

	}
}
