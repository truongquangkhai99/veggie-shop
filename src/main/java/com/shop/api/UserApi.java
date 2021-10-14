package com.shop.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entitty.Cart;
import com.shop.entitty.User;
import com.shop.repository.CartRepository;
import com.shop.repository.UserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("api/users")
public class UserApi {
	@Autowired
	UserRepository repo;
	
	@Autowired
	CartRepository cartRepository;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(repo.findByStatusTrueAndRoleFalse());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getOne(@PathVariable("id") Long id) {
		if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(repo.findById(id).get());
	}
	
	@GetMapping("email/{email}")
	public ResponseEntity<User> getOneByEmail(@PathVariable("email") String email) {
		if(repo.existsByEmail(email)) {			
			return ResponseEntity.ok(repo.findByEmail(email));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<User> post(@RequestBody User user) {
		if(repo.existsByEmail(user.getEmail())) {
			return ResponseEntity.notFound().build();
		}
		if(repo.existsById(user.getUserId())) {
			return ResponseEntity.badRequest().build();
		}
		user.setRole(false);
		User u =  repo.save(user);
		Cart c = new Cart(0L, 0.0, u.getAddress(), u.getPhone(), u);
		cartRepository.save(c);
		return ResponseEntity.ok(u);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> put(@PathVariable("id") Long id, @RequestBody User user) {
		if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		if(!id.equals(user.getUserId())) {
			return ResponseEntity.badRequest().build();
		}
		user.setRole(false);
		return ResponseEntity.ok(repo.save(user));
	}
	
	@PutMapping("admin/{id}")
	public ResponseEntity<User> putAdmin(@PathVariable("id") Long id, @RequestBody User user) {
		if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		if(!id.equals(user.getUserId())) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(repo.save(user));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		User u = repo.findById(id).get();
		u.setStatus(false);
		repo.save(u);
//		repo.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
