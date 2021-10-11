package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entitty.Category;
import com.shop.entitty.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByStatusTrue();
	List<Product> findByStatusTrueOrderBySoldDesc();
	List<Product> findByStatusTrueOrderByEnteredDateDesc();
	List<Product> findByCategory(Category category);
	Product findByProductIdAndStatusTrue(Long id);
	
	@Query(value = "Select p.* From products p \r\n"
			+ "left join rates r on p.product_id = r.product_id\r\n"
			+ "group by p.product_id , p.name\r\n"
			+ "Order by  avg(r.rating) desc, RAND()", nativeQuery = true)
	List<Product> findProductRated();
}
