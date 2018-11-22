package lt.bit.product;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import lt.bit.core.NotFoundException;

@Service
@Validated
public class ProductService {

	private ProductJpaRepository rep;

	public ProductService(ProductJpaRepository rep) {
		this.rep = rep;
	}

	public List<Product> getProducts() {
		return rep.findAll().stream()
				.collect(Collectors.toList());
	}

	public List<Product> getFilteredProducts(ProductFilter productFilter) {
		
	return rep.findAll(ProductSpecificationsBuilder.productHasName(productFilter.getName())
			.and(ProductSpecificationsBuilder.productPriceRange(productFilter.getPriceMin(), productFilter.getPriceMax())));
//		return rep.findAll().stream()
//				.filter(a -> a.getName().contains(name))
//				.filter(a -> a.getPrice() >= (!StringUtils.isEmpty(priceMin)?Float.parseFloat(priceMin) : 0 ) )
//				.filter(a -> a.getPrice() <= (!StringUtils.isEmpty(priceMax)?Float.parseFloat(priceMax) : Float.MAX_VALUE ) )
//				.collect(Collectors.toList());
	}

	public UUID saveProduct(@NotNull @Valid Product product) {
		return rep.save(product).getId();
	}
	
	public void deleteProduct(@NotEmpty String id) {
		rep.deleteById(UUID.fromString(id));
	}
	
	public Product getProductbyId(UUID id) {
		return rep.getById(id).orElseThrow( () -> new NotFoundException("entry not found") );
	}
	public UUID updateProduct(String id, Product product) {
		Product pr1 = rep.findAll().stream()
		.filter( a -> id.equals(a.getId().toString()))
		.findFirst().orElseGet(() -> null);
		product.setId(pr1.getId());
		return rep.save(product).getId();
	}

}
