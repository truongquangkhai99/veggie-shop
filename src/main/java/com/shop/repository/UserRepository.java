package com.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.User;

public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findByStatusTrue();
	Boolean existsByEmail(String email);
//	User findByEmail(String email);
//	User findByEmailAndRoleTrue(String email);
//	User findByEmailAndRoleFalse(String email);
	Optional<User> findByEmail(String username);
	User findByToken(String token);
}
