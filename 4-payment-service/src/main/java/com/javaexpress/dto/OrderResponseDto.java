package com.javaexpress.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderResponseDto {

	private Long orderId;
	private Long userId;
	private BigDecimal totalPrice;
	private String status;
	private LocalDateTime plcaedAt = LocalDateTime.now();
	private List<OrderItemResponseDto> items;
}
