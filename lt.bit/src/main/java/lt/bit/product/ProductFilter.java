package lt.bit.product;

public class ProductFilter {

	private String name;
	private float priceMin; 
	private float priceMax;
	
	public String getName() {
		return name;
	}
	public ProductFilter setName(String name) {
		this.name = name;
		return this;
	}
	public float getPriceMin() {
		return priceMin;
	}
	public ProductFilter setPriceMin(float priceMin) {
		this.priceMin = priceMin;
		return this;
	}
	public float getPriceMax() {
		return priceMax;
	}
	public ProductFilter setPriceMax(float priceMax) {
		this.priceMax = priceMax;
		return this;
	}
}
