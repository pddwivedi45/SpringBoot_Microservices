package com.javaexpress.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
	
//	@Autowired
//	private ProductRestIntegrationService productRestIntegrationService;
	
	@Autowired
	private ProductFeignIntegrationService productFeignIntegrationService;
	
	public CartItemResponseDto addToCart(CartItemRequestDto request) {
		
//		Long userId = request.getUserId();
		Long productId = request.getProductId(); 
		
//		var response = productRestIntegrationService.fetchProduct(productId);
		var response = productFeignIntegrationService.fetchProduct(productId);
		
		
		CartItem cartItem = new CartItem();
		BeanUtils.copyProperties(request, cartItem);
		CartItem dbCartItem = cartItemRepository.save(cartItem);
		
		return maptoDto(dbCartItem,response);  
	}
	
	public List<CartItemResponseDto> getCartItemByUser(Long userId){
		List<CartItemResponseDto> response =   cartItemRepository.findByUserId(userId)
		.stream()
		.map(cart->{
			ProductResponseDto productResponseDto = productFeignIntegrationService.fetchProduct(cart.getProductId());
			return maptoDto(cart, productResponseDto);
		})
		.collect(Collectors.toList());
		
		return response;
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
