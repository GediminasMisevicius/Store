package lt.bit.product;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="products")
public class Product implements Serializable {
	

	@Id
	@NotNull
	@Column(name="id")
	private UUID id;
	
	@Column(name="name", nullable=false)
	@NotBlank
//	"     "
//	"	"
//	"\n"
//	null
	@Size(min=5, max=100)
	private String name;
	
	@Column(name="price")
	@Min(0)
//	@Max(Integer.MAX_VALUE)
	private float price;

	public Product() {
		this.id=UUID.randomUUID();
	}
	
	public Product(String name, float price) {
		this.id=UUID.randomUUID();
		this.name = name;
		this.price = price;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}
