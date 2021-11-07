package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.entitty.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{
	List<Notification> findByOrderByIdDesc();
	
	@Modifying
	@Query(value = "update notification set status = true", nativeQuery = true)
	void readAll();
}
