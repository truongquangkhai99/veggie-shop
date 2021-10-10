package com.shop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.repository.ProductRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/statistical")
public class StatisticalApi {
	@Autowired
	ProductRepository productRepo;
}
