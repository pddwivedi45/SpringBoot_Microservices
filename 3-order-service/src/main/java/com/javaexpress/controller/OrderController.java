package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.OrderRequestDto;
import com.javaexpress.dto.OrderResponseDto;
import com.javaexpress.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping
	public OrderResponseDto placeOrder(@RequestBody OrderRequestDto request) {
		return orderService.placeOrder(request);
	}
	
	@GetMapping("/{orderId}")
	public OrderResponseDto fetchOrderDetails(@PathVariable Long orderId) {
		return orderService.getOrderById(orderId);
	}
}
