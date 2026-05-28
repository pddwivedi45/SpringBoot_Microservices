package com.javaexpress.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.dto.CartItemResponseDto;

@FeignClient(name = "cart-service",path = "/cart")
public interface CartsFeignClient {

	@GetMapping("/{userId}")
	public List<CartItemResponseDto> getCartItemByUser(@PathVariable Long userId);
}
