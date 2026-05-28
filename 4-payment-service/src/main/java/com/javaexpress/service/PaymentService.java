package com.javaexpress.service;

import com.javaexpress.repository.PaymentRepository;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.OrderResponseDto;
import com.javaexpress.dto.PaymentRequestDto;
import com.javaexpress.dto.PaymentResponseDto;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.model.Payment;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

	private final PaymentRepository paymentRepository;
	@Autowired
	private OrderFeignIntegrationService orderFeignIntegrationService;

	PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
	
	public PaymentResponseDto processPayment(PaymentRequestDto request) {
		 log.info("PaymentService processPayment :{}",request);
		 OrderResponseDto orderDetails = orderFeignIntegrationService.fetchOrderDetails(request.getOrderId());
		 log.info("Fetched orderdetails");
		 if(orderDetails!=null) {
			 Payment payment = new Payment();
			 BeanUtils.copyProperties(request, payment);
			 payment.setStatus("SUCCESS");
			 Payment dbPayment = paymentRepository.save(payment);
			 return mapToDto(dbPayment,orderDetails);
		 }else {
			 throw new ResourceNotFoundException("Order detail is not found");
		 }
	}

	private PaymentResponseDto mapToDto(Payment dbPayment, OrderResponseDto orderDetails) {
		PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
		BeanUtils.copyProperties(dbPayment, paymentResponseDto);
		paymentResponseDto.setPaymentId(dbPayment.getId());
		paymentResponseDto.setOrderResponseDto(orderDetails);
		return paymentResponseDto;
	}
	
	public PaymentResponseDto getPaymentDetails(Long paymentId) {
		Optional<Payment> payment = paymentRepository.findById(paymentId);
		if(payment.isPresent()) {
			Payment dbPayment = payment.get();
			OrderResponseDto orderResponseDto = orderFeignIntegrationService.fetchOrderDetails(dbPayment.getOrderId());
			return mapToDto(dbPayment, orderResponseDto);
		}else {
			throw new ResourceNotFoundException("Payment details not found in database.");
		}
	}
}
