package com.javaexpress.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentResponseDto {

	private Long paymentId;
	private BigDecimal amount;
	private Long userId;
	private Long orderId;
	private String status;
	private OrderResponseDto orderResponseDto;
}
