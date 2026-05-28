package com.javaexpress.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.dto.OrderResponseDto;
      
@FeignClient(name = "order-service",path = "/orders")
public interface OrderFeignClient {

	@GetMapping("/{orderId}")
	public OrderResponseDto fetchOrderDetails(@PathVariable("orderId") Long orderId);
}
