package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entitty.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

}
