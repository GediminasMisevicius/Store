package lt.bit.product;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {

	private Resource productResource = new ClassPathResource("/static/products.json");
	
	private ObjectMapper mapper;
	
	public ProductService(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<Product> loadProductsFromJson() throws IOException{
		TypeReference<List<Product>> typeRef = new TypeReference<List<Product>>() {
		};
		
		return mapper.readValue(productResource.getInputStream(), typeRef);
	}

}
