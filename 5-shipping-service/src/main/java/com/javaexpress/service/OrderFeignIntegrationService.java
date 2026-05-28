package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.OrderResponseDto;
import com.javaexpress.feignclients.OrderFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderFeignIntegrationService {

	@Autowired
	private OrderFeignClient orderFeignClient;
	
	public OrderResponseDto fetchOrderDetails(Long orderId) {
		return orderFeignClient.fetchOrderDetails(orderId);
	}
}
