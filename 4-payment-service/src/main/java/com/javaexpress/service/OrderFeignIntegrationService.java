package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.OrderResponseDto;
import com.javaexpress.exception.OrderServiceException;
import com.javaexpress.feignclients.OrderFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderFeignIntegrationService {

	@Autowired
	private OrderFeignClient orderFeignClient; 
	
	public OrderResponseDto fetchOrderDetails(Long orderId) { 
		log.info("OrderFeignIntegrationService fetchOrderDetails : {}", orderId);
		OrderResponseDto orderResponseDto = orderFeignClient.fetchOrderDetails(orderId);
		log.info("Order details fetched successfully for the payment");
		return orderResponseDto; 
	}
}
