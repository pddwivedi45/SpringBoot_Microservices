package com.javaexpress.dto;

import lombok.Data;

@Data
public class ShippingRequestDto {

	private Long orderId;
	private String shippingMethod;
	private String carrier;
}
