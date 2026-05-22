package com.javaexpress.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Setter
@Getter
public class OrderItem {

	private Long id;
	private Long productId;
	private Integer quantity;
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
}
