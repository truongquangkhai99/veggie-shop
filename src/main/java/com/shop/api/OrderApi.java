package com.shop.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entitty.Cart;
import com.shop.entitty.CartDetail;
import com.shop.entitty.Order;
import com.shop.entitty.OrderDetail;
import com.shop.repository.CartDetailRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.OrderDetailRepository;
import com.shop.repository.OrderRepository;
import com.shop.repository.UserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("api/orders")
public class OrderApi {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartDetailRepository cartDetailRepository;
	
	@GetMapping("{id}")
	public ResponseEntity<Order> getById(@PathVariable("id") Long id) {
		if(!orderRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderRepository.findById(id).get());
	}
	
	@GetMapping("/user/{email}")
	public ResponseEntity<List<Order>> getByUser(@PathVariable("email") String email) {
		if(!userRepository.existsByEmail(email)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderRepository.findByUser(userRepository.findByEmail(email)));
	}
	
	@PostMapping("/{email}")
	public ResponseEntity<Order> checkout(@PathVariable("email") String email, @RequestBody Cart cart) {
		if(!userRepository.existsByEmail(email)) {
			return ResponseEntity.notFound().build();
		}
		if(!cartRepository.existsById(cart.getCartId())) {
			return ResponseEntity.notFound().build();
		}
		List<CartDetail> items = cartDetailRepository.findByCart(cart);
		Double amount = 0.0;
		for(CartDetail i : items) {
			amount += i.getPrice();
		}
		Order order = orderRepository.save(new Order(0L, new Date(), amount, cart.getAddress(), cart.getPhone(), 0, userRepository.findByEmail(email)));
		for(CartDetail i : items) {
			OrderDetail orderDetail = new OrderDetail(0L, i.getQuantity(), i.getPrice(), i.getProduct(), order);
			orderDetailRepository.save(orderDetail);
		}
//		cartDetailRepository.deleteByCart(cart);
		for(CartDetail i : items) {
			cartDetailRepository.delete(i);
		}
		return ResponseEntity.ok(order);
	}
}
