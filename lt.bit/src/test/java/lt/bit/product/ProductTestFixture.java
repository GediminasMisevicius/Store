package lt.bit.product;

public class ProductTestFixture {

	public static Product filledNewProduct() {
		return filledNewProduct("name", 66.6f);
	}
	
	public static Product filledNewProduct(String name, float price) {
		return new Product().setName(name).setPrice(price);
	}
}
