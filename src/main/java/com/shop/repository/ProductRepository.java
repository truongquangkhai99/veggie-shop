package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.Category;
import com.shop.entitty.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByStatusTrue();
	List<Product> findByStatusTrueOrderBySoldDesc();
	List<Product> findByStatusTrueOrderByEnteredDateDesc();
	List<Product> findByCategory(Category category);
}
