package com.javaexpress.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemResponseDto {

	private Long userId;
	private Long productId;
	private Integer quantity;
	private ProductResponseDto product;
	private UserDto user;
}
