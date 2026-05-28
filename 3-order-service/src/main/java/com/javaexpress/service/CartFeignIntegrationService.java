package com.javaexpress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.CartItemResponseDto;
import com.javaexpress.feignclients.CartsFeignClient;

@Service
public class CartFeignIntegrationService {

	@Autowired
	private CartsFeignClient cartsFeignClient;
	
	public List<CartItemResponseDto> getCartItemByUser(Long userId){
		return cartsFeignClient.getCartItemByUser(userId);
	} 
}
