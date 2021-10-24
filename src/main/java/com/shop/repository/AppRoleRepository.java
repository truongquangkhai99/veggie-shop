package com.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.common.ERole;
import com.shop.entitty.AppRole;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long>{
	Optional<AppRole> findByName(ERole nam);
}
