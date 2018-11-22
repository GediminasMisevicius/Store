package lt.bit.product;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class Product implements Serializable {

	private UUID id;
	private String name;
	private float price;

	public Product() {
		this.id=UUID.randomUUID();
	}
	
	public UUID getId() {
		return id;
	}

	public Product setId(UUID id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Product setName(String name) {
		this.name = name;
		return this;
	}

	public float getPrice() {
		return price;
	}

	public Product setPrice(float price) {
		this.price = price;
		return this;
	}
	
	public Product supplyProduct(Supplier<Product> sp) {
		return sp.get();
	}
	
	public boolean emptyProduct(Predicate<Product> pr) {
		return pr.test(this);
	}

}
