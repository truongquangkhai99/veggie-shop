package com.shop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entitty.Cart;
import com.shop.repository.CartDetailRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.UserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("api/cart")
public class CartApi {
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartDetailRepository cartDetailRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Cart> getCartUser(@PathVariable("id") Long id) {
		if(!userRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cartRepository.findByUser(userRepository.findById(id).get()));
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<Cart> putCartUser(@PathVariable("id") Long id, @RequestBody Cart cart) {
		if(!userRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cartRepository.save(cart));
	}
}
