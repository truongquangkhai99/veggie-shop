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

import com.shop.entitty.User;
import com.shop.repository.UserRepository;

@CrossOrigin("*")
@RequestMapping("api/users")
public class UserApi {
	@Autowired
	UserRepository repo;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(repo.findAll());
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
		return ResponseEntity.ok(repo.findByEmail(email));
	}
	
	@PostMapping
	public ResponseEntity<User> post(@RequestBody User user) {
		if(repo.existsByEmail(user.getEmail())) {
			return ResponseEntity.notFound().build();
		}
		if(repo.existsById(user.getUserId())) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(repo.save(user));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> put(@PathVariable("id") Long id, @RequestBody User user) {
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
		repo.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
