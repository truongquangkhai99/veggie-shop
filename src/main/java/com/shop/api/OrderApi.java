package com.shop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.repository.OrderRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("api/orders")
public class OrderApi {
	@Autowired
	OrderRepository repo;
}
