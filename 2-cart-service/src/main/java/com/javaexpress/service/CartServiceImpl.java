package com.javaexpress.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.CartItemRequestDto;
import com.javaexpress.dto.CartItemResponseDto;
import com.javaexpress.dto.ProductResponseDto;
import com.javaexpress.model.CartItem;
import com.javaexpress.repository.CartItemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ProductRestIntegrationService productRestIntegrationService;
	
	public CartItemResponseDto addToCart(CartItemRequestDto request) {
		
//		Long userId = request.getUserId();
		Long productId = request.getProductId(); 
		
		var response = productRestIntegrationService.fetchProduct(productId);
		
		
		CartItem cartItem = new CartItem();
		BeanUtils.copyProperties(request, cartItem);
		CartItem dbCartItem = cartItemRepository.save(cartItem);
		
		return maptoDto(dbCartItem,response);  
	}

	private CartItemResponseDto maptoDto(CartItem dbCartItem, ProductResponseDto product) {
		// TODO Auto-generated method stub
		CartItemResponseDto response = new CartItemResponseDto();
		BeanUtils.copyProperties(dbCartItem, response);
		response.setProduct(product);
		
		// TODO: set user information here 
		return response;
	}
}
