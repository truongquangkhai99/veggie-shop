package com.shop.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entitty.Favorite;
import com.shop.entitty.Product;
import com.shop.entitty.User;
import com.shop.repository.FavoriteRepository;
import com.shop.repository.ProductRepository;
import com.shop.repository.UserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("api/favorites")
public class FavoritesApi {
	@Autowired
	FavoriteRepository favoriteRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("email/{email}")
	public ResponseEntity<List<Favorite>> findByEmail(@PathVariable("email") String email) {
		if(userRepository.existsByEmail(email)) {
			return ResponseEntity.ok(favoriteRepository.findByUser(userRepository.findByEmail(email).get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("product/{id}")
	public ResponseEntity<Integer> findByProduct(@PathVariable("id") Long id) {
		if(productRepository.existsById(id)) {
			return ResponseEntity.ok(favoriteRepository.countByProduct(productRepository.getById(id)));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("{productId}/{email}")
	public ResponseEntity<Favorite> findByProductAndUser(@PathVariable("productId") Long productId, @PathVariable("email") String email) {
		if(userRepository.existsByEmail(email)) {
			if(productRepository.existsById(productId)) {
				Product product = productRepository.findById(productId).get();
				User user = userRepository.findByEmail(email).get();
				return ResponseEntity.ok(favoriteRepository.findByProductAndUser(product, user));
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("email")
	public ResponseEntity<Favorite> post(@RequestBody Favorite favorite) {
		return ResponseEntity.ok(favoriteRepository.save(favorite));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if(favoriteRepository.existsById(id)) {
			favoriteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
