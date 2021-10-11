package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.Cart;
import com.shop.entitty.User;

public interface CartRepository extends JpaRepository<Cart, Long>{
	Cart findByUser(User user);
}
