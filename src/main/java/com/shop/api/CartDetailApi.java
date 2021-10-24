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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entitty.CartDetail;
import com.shop.entitty.Product;
import com.shop.repository.CartDetailRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.ProductRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("api/cartDetail")
public class CartDetailApi {
	@Autowired
	CartDetailRepository cartDetailRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("cart/{id}")
	public ResponseEntity<List<CartDetail>> getByCartId(@PathVariable("id") Long id) {
		if (!cartRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cartDetailRepository.findByCart(cartRepository.findById(id).get()));
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public ResponseEntity<CartDetail> getOne(@PathVariable("id") Long id) {
		if(!cartDetailRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cartDetailRepository.findById(id).get());
	}

	@PostMapping()
	public ResponseEntity<CartDetail> post(@RequestBody CartDetail detail) {
		if (!cartRepository.existsById(detail.getCart().getCartId())) {
			return ResponseEntity.notFound().build();
		}
		boolean check = false;
		List<Product> listP = productRepository.findByStatusTrue();
		Product product = productRepository.findByProductIdAndStatusTrue(detail.getProduct().getProductId());
		for(Product p : listP){
			if(p.getProductId() == product.getProductId()) {
				check = true;
			}
		};
		if(!check) {
			return ResponseEntity.notFound().build();			
		}
		List<CartDetail> listD = cartDetailRepository.findByCart(cartRepository.findById(detail.getCart().getCartId()).get());
		for (CartDetail item : listD) {
			if (item.getProduct().getProductId() == detail.getProduct().getProductId()) {
				item.setQuantity(item.getQuantity() + 1);
				item.setPrice(item.getPrice() + detail.getPrice());
				return ResponseEntity.ok(cartDetailRepository.save(item));
			}
		}
		return ResponseEntity.ok(cartDetailRepository.save(detail));
	}

	@PutMapping()
	public ResponseEntity<CartDetail> put(@RequestBody CartDetail detail) {
		if (!cartRepository.existsById(detail.getCart().getCartId())) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cartDetailRepository.save(detail));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (!cartDetailRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cartDetailRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
