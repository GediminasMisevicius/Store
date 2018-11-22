package lt.bit.product;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService service;
	private final AuthService authService;

	// Autowired
	ProductController(ProductService service, AuthService authService) {
		this.service = service;
		this.authService = authService;
	}
	
//	@RolesAllowed(value = { "CUSTOMER", "bananas" })
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Product>> getProductList(@RequestHeader("Authorization") String token) throws IOException {
		if(authService.isAuthenticated(token)) {
			return ResponseEntity.ok(service.loadProductsFromJson());
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();			
		}
		
	}
	
	

}
