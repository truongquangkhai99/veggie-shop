package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.User;

public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findByStatusTrue();
	Boolean existsByEmail(String email);
	User findByEmail(String email);
	
}
