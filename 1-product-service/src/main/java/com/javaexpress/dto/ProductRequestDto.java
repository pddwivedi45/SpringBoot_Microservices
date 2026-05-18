package com.javaexpress.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductRequestDto {

	private String name;
	private String description;
	private BigDecimal price;
	private Integer stock;
}
