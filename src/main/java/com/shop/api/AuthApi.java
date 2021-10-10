package com.shop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entitty.Login;
import com.shop.entitty.User;
import com.shop.repository.UserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthApi {
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping("admin/login")
	public ResponseEntity<User> LoginAdmin(@RequestBody Login login) {
		User user = userRepo.findByEmailAndRoleTrue(login.getEmail());
		if(user!=null) {
			if(user.getPassword().equals(login.getPassword())) {
				return ResponseEntity.ok(user);
			}
			return ResponseEntity.badRequest().build();
		}		
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping("user/login")
	public ResponseEntity<User> LoginUser(@RequestBody Login login) {
		User user = userRepo.findByEmailAndRoleFalse(login.getEmail());
		if(user!=null) {
			if(user.getPassword().equals(login.getPassword())) {
				return ResponseEntity.ok(user);
			}
			return ResponseEntity.badRequest().build();
		}		
		return ResponseEntity.notFound().build();
	}
}
