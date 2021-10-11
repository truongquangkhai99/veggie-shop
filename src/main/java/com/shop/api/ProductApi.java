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

import com.shop.entitty.Category;
import com.shop.entitty.Product;
import com.shop.repository.CategoryRepository;
import com.shop.repository.ProductRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("api/products")
public class ProductApi {
	@Autowired
	ProductRepository repo;
	
	@Autowired
	CategoryRepository cRepo;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok(repo.findByStatusTrue());
	}
	
	@GetMapping("bestseller")
	public ResponseEntity<List<Product>> getBestSeller() {
		return ResponseEntity.ok(repo.findByStatusTrueOrderBySoldDesc());
	}
	
	@GetMapping("latest")
	public ResponseEntity<List<Product>> getLasted() {
		return ResponseEntity.ok(repo.findByStatusTrueOrderByEnteredDateDesc());
	}
	
	@GetMapping("rated")
	public ResponseEntity<List<Product>> getRated() {
		return ResponseEntity.ok(repo.findProductRated());
	}
	
	@GetMapping("suggest/{id}")
	public ResponseEntity<List<Product>> suggest(@PathVariable("id") Long categoryId) {
		return ResponseEntity.ok(repo.findAll());
	}
	
	@GetMapping("category/{id}")
	public ResponseEntity<List<Product>> getByCategory(@PathVariable("id") Long id) {
		if(!cRepo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}		
		Category c = cRepo.findById(id).get();
		return ResponseEntity.ok(repo.findByCategory(c));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") Long id) {
		if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok(repo.findById(id).get());
	}
	
	@PostMapping
	public ResponseEntity<Product> post(@RequestBody Product product) {
		if(repo.existsById(product.getProductId())) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(repo.save(product));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Product> put(@PathVariable("id") Long id, @RequestBody Product product) {
		if(!id.equals(product.getProductId())) {
			return ResponseEntity.badRequest().build();
		}
		if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(repo.save(product));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if(!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		Product p = repo.findById(id).get();
		p.setStatus(false);
		repo.save(p);
//		repo.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
