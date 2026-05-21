package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javaexpress.dto.ProductResponseDto;
import com.javaexpress.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductRestIntegrationService {

	@Autowired
	private RestTemplate restTemplate;
	
	private static final String PRODUCT_SERVICE_URL = "http://localhost:8031/products/v1/{productId}";
	
	public ProductResponseDto fetchProduct(Long productId)
	{
		try {
			var responseEntity = restTemplate.getForEntity(PRODUCT_SERVICE_URL, ProductResponseDto.class,productId);
			if(responseEntity.getStatusCode().is2xxSuccessful())
			{
				return responseEntity.getBody();
			}else {
				throw new ResourceNotFoundException("Failed to fetch product information");
			}
		}catch(Exception ex){
			log.error("Error occured while fetching product from proudct service ", ex);
			throw new ResourceNotFoundException(ex.getMessage());
		}
	}
}
