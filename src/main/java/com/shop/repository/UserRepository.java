package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Boolean existsByEmail(String email);
	User findByEmail(String email);
}
