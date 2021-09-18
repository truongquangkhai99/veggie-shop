package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
