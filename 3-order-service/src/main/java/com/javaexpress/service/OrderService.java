package com.javaexpress.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.CartItemResponseDto;
import com.javaexpress.dto.OrderItemResponseDto;
import com.javaexpress.dto.OrderRequestDto;
import com.javaexpress.dto.OrderResponseDto;
import com.javaexpress.dto.ProductResponseDto;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.model.Order;
import com.javaexpress.model.OrderItem;
import com.javaexpress.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartFeignIntegrationService cartFeignIntegrationService;
	
	@Autowired
	private ProductFeignIntegrationService productFeignIntegrationService;
	
	public OrderResponseDto placeOrder(OrderRequestDto request) {
		
		List<CartItemResponseDto> cartItems = cartFeignIntegrationService.getCartItemByUser(request.getUserId());
		if(cartItems.isEmpty()) {
			throw new ResourceNotFoundException("Cart items are empty!");
		}
		
		// first calculate total price
		BigDecimal totalPrice = calculateTotalPrice(cartItems);
		
		// create order items from cart items
		List<OrderItem> orderItems = buildOrderItems(cartItems);
		
		// create order entity to save in database
		Order order = createOrder(request,totalPrice,orderItems); 
		
		// save in database 
		Order dbOrder = orderRepository.save(order);
		
		
		return mapToDto(dbOrder);
	}

	private OrderResponseDto mapToDto(Order dbOrder) {
		OrderResponseDto orderResponseDto = new OrderResponseDto();
	    BeanUtils.copyProperties(dbOrder, orderResponseDto);
	    orderResponseDto.setOrderId(dbOrder.getId());
	    
	    var orderItemResponse = dbOrder.getItems().stream().map(item->{
	    	OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
	    	BeanUtils.copyProperties(item, orderItemResponseDto);
	    	ProductResponseDto product = productFeignIntegrationService.fetchProduct(item.getProductId());
	    	orderItemResponseDto.setProduct(product);
	    	return orderItemResponseDto;
	    }).collect(Collectors.toList());
	    orderResponseDto.setItems(orderItemResponse);
		return orderResponseDto;
	}

	private Order createOrder(OrderRequestDto request, BigDecimal totalPrice, List<OrderItem> orderItems) {
		Order order = new Order();
		order.setUserId(request.getUserId());
		order.setTotalPrice(totalPrice);
		order.setStatus("PLACED");
	
		// this is for bi-directional relationship
		for(OrderItem item : orderItems) {
			item.setOrder(order);
		}
		order.setItems(orderItems);
		return order;
	}

	private List<OrderItem> buildOrderItems(List<CartItemResponseDto> cartItems) {
		List<OrderItem> orderItems = new ArrayList<>();
		for(CartItemResponseDto item : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(item.getProduct().getPrice());
			orderItem.setProductId(item.getProductId());
			orderItem.setQuantity(item.getQuantity());
			orderItems.add(orderItem);
		}
		return orderItems;
	}

	private BigDecimal calculateTotalPrice(List<CartItemResponseDto> cartItems) {
		
		BigDecimal totalPrice = BigDecimal.ZERO;
		for(CartItemResponseDto item : cartItems) {
			BigDecimal productPrice = item.getProduct().getPrice();
			totalPrice=totalPrice.add(productPrice.multiply(BigDecimal.valueOf(item.getQuantity())));
		}
		return totalPrice;
	}
	
	public OrderResponseDto getOrderById(Long orderId) {
		return orderRepository.findById(orderId)
				.map(this::mapToDto)
				.orElseThrow(()-> new ResourceNotFoundException("Order ID not found in database"));
	}

}
