package com.shop.entitty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cartDetails")
public class CartDetail {
	@Id
	private Long cartDetailId;
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
}
