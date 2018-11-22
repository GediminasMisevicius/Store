package lt.bit.product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
	void deleteById(UUID id);
	Optional <Product> getById(UUID id);
	
	List<Product> findByPriceGreaterThan(float price);
	//per ilgas metodas
	List<Product> findByPriceGreaterThanOrPriceIsNullAndNameContaining(float price, String name);
	//alternatyvus metodas
	@Query("select p from Product p where p.price > ?1 or p.price is null and p.name like %?2%")
	List<Product> findByPriceAndName(float price, String name);
	//kitas budas
	@Query("select p from Product p where p.price > :price or p.price is null and p.name like %:name%")
	List<Product> findByPriceAndName2(@Param("price") float price, @Param("name") String name);
}
