package com.javaexpress.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShippingResponseDto {

	private Long orderId;
	private String shippingMethod;
	private String carrier;
	private LocalDateTime shippedAt;
	private LocalDateTime deliveryDate;
	private String status;
	private OrderResponseDto orderResponseDto;
	
}
