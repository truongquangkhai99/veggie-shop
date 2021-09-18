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
@Table(name = "orders")
public class Order {
	@Id
	private Long ordersId;
	private LocalDate orderDate;
	private Double amount;
	private String address;
	private String phone;
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
}
