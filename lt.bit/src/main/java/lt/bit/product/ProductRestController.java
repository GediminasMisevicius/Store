package lt.bit.product;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/products")
public class ProductRestController {

	private ProductService service;

	// Autowired
	public ProductRestController(ProductService service) {
		this.service = service;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Product>> getProductResponseEntity() {
		return ResponseEntity.ok(service.getProducts());
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Product> getProductById(@PathVariable String id) {
		return ResponseEntity.ok(service.getProductbyId(UUID.fromString(id)));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public UUID addProductToMap(@RequestBody Product product) {
		return service.saveProduct(product);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteProductFromMap(@PathVariable String id) {
		service.deleteProduct(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK, reason = "Oro balionas")
	public void updateProduct(@PathVariable String id, @RequestBody Product product) {
		service.updateProduct(id, product);
	}

	@ResponseBody
	public HttpStatus sendViaResponseEntity(String isStatus) {
		if (isStatus.equals("Not Found")) {
			System.out.println("ResponseEntity 1");
			return HttpStatus.NOT_FOUND;
		}
		System.out.println("ResponseEntity 2");
		return HttpStatus.OK;
	}

}
