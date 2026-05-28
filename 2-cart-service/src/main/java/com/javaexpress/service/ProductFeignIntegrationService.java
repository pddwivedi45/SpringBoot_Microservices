package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.ProductResponseDto;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.feignclients.ProductFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductFeignIntegrationService {
	
	@Autowired
	private ProductFeignClient productFeignClient;
	
	@CircuitBreaker(name = "productServiceCB", fallbackMethod = "fetchProductFallback")
	public ProductResponseDto fetchProduct(Long productId)
	{
		ProductResponseDto responseDto = productFeignClient.getProduct(productId);
		return responseDto;
	}
	
	public ProductResponseDto fetchProductFallback(Long productId,Throwable t)
	{
        throw new ResourceNotFoundException("Product Service is not available. Please try again later.");
	}

}
