package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.ProductResponseDto;
import com.javaexpress.feignclients.ProductFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductFeignIntegrationService {
	
	@Autowired
	private ProductFeignClient productFeignClient;
	
	
	public ProductResponseDto fetchProduct(Long productId)
	{
		return productFeignClient.getProduct(productId);
	}

}
