package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.User;

public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findByStatusTrueAndRoleFalse();
	Boolean existsByEmail(String email);
	User findByEmail(String email);
	User findByEmailAndRoleTrue(String email);
	User findByEmailAndRoleFalse(String email);
}
