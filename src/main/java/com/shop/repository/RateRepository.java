package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.entitty.OrderDetail;
import com.shop.entitty.Product;
import com.shop.entitty.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long>{
	List<Rate> findAllByOrderByIdDesc();
	Rate findByOrderDetail(OrderDetail orderDetail);
	List<Rate> findByProductOrderByIdDesc(Product product);
}
