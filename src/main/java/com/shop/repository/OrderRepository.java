package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.Order;
import com.shop.entitty.User;

public interface OrderRepository extends JpaRepository<Order, Long>{
	List<Order> findByUser(User user);
	List<Order> findByUserOrderByOrdersIdDesc(User user);
	List<Order> findAllByOrderByOrdersIdDesc();
	List<Order> findByStatus(int status);
}
