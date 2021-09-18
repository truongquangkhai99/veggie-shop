package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long>{

}
