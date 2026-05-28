package com.javaexpress.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.OrderResponseDto;
import com.javaexpress.dto.ShippingRequestDto;
import com.javaexpress.dto.ShippingResponseDto;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.model.Shipping;
import com.javaexpress.repository.ShippingRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShippingService {

	@Autowired
	private OrderFeignIntegrationService orderFeignIntegrationService;
	
	@Autowired
	private ShippingRepository shippingRepository;
	
	public ShippingResponseDto shipOrder(ShippingRequestDto request) {
		
		OrderResponseDto orderResponseDto = orderFeignIntegrationService.fetchOrderDetails(request.getOrderId());
		if(orderResponseDto != null) {
		Shipping shipping = new Shipping();
		BeanUtils.copyProperties(request, shipping);
		shipping.setStatus("SHIPPED");
		shipping.setShippedAt(LocalDateTime.now());
		Shipping dbShipping = shippingRepository.save(shipping);
		
		return mapToDto(dbShipping,orderResponseDto);
		}
		else {
			throw new ResourceNotFoundException("Invalid order ID");
		}
	}

	private ShippingResponseDto mapToDto(Shipping dbShipping, OrderResponseDto orderResponseDto) {
		ShippingResponseDto shippingResponseDto = new ShippingResponseDto();
		BeanUtils.copyProperties(dbShipping, shippingResponseDto);
		shippingResponseDto.setOrderResponseDto(orderResponseDto);
		return shippingResponseDto;
	}
}
