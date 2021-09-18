package com.shop.entitty;

import java.time.LocalDate;

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
@Table(name = "products")
public class Product {
	@Id
	private Long productId;
	private String name;
	private String quantity;
	private String price;
	private int discount;
	private String description;
	private LocalDate enteredDate;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
}
