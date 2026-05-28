package com.javaexpress.dto;

import java.math.BigDecimal;
import java.security.PrivateKey;

import lombok.Data;

@Data
public class OrderItemResponseDto {

	private Long productId;
	private Integer quantity;
	private BigDecimal price;
//	private ProductResponseDto product;
}
