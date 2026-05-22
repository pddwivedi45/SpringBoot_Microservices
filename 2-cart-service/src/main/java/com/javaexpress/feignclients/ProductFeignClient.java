package com.javaexpress.feignclients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.dto.ProductResponseDto;

@FeignClient(name = "product-service",path="/products/v1")
@LoadBalancerClients   
public interface ProductFeignClient {

	@GetMapping("/{productId}")
	public ProductResponseDto getProduct(@PathVariable Long productId);
}
