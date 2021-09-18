package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
