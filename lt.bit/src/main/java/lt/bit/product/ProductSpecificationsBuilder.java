package lt.bit.product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

public class ProductSpecificationsBuilder {
	
	public static Specification<Product> buildSpecificationByFilter(ProductFilter productFilter) {
		Specification<Product> spec = null;
		
		return spec;
	}

	public static Specification<Product> productHasName(String name) {
		return new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.like(root.get("name"), "%" + name + "%"); //equal(root.get("name"), name);
			}
		};
	}
	
	public static Specification<Product> productPriceRange(float priceMin, float priceMax) {
		return new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.between(root.get("price"), priceMin, priceMax);
			}
		};
	}

}
