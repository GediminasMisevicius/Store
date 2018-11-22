package lt.bit.product;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductThymeleafController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		return "products";
	}
	
	@PostMapping("/products")
	public String addProduct(HttpServletRequest request) {
		Product edit = new Product(request.getParameter("fname"),
				Float.parseFloat(request.getParameter("fprice")));
		String id = request.getParameter("fid");
		if( !StringUtils.isEmpty(id) ){
			edit.setId(UUID.fromString(id));
		}
		productService.saveProduct(edit);
		return "redirect:/th/products";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);
		return "redirect:/th/products";
	}
	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable String id, Model model) {
		
		Product editable = productService.getProductbyId(UUID.fromString(id));
		model.addAttribute("editProduct", editable);
		
		
		return "products";
	}
	
	@GetMapping("/filter")
	public String getForm(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		return "products";
	}

	@PostMapping("/filter")
	public String filterProducts(HttpServletRequest request, Model model) {
		ProductFilter filter = new ProductFilter();
		filter.setName(request.getParameter("finame"))
		.setPriceMax(Float.valueOf(request.getParameter("fipricemax")))
		.setPriceMin(Float.valueOf(request.getParameter("fipricemin")));
		List<Product> filtered = productService.getFilteredProducts(filter);
		model.addAttribute("products", filtered);
		return "products";
	}
	
}
