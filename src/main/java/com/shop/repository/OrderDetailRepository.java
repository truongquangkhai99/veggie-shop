package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.Order;
import com.shop.entitty.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
	List<OrderDetail> findByOrder(Order order);
}
