package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.Cart;
import com.shop.entitty.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long>{
	List<CartDetail> findByCart(Cart cart);
	void deleteByCart(Cart cart);
}
