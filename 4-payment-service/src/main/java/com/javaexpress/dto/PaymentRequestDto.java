package com.javaexpress.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentRequestDto {

	private BigDecimal amount;
	private Long userId;
	private Long orderId;
}
