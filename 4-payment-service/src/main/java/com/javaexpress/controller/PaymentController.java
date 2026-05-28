package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.PaymentRequestDto;
import com.javaexpress.dto.PaymentResponseDto;
import com.javaexpress.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public PaymentResponseDto makePayment(@RequestBody PaymentRequestDto request) {
		log.info("PaymentController makePayment : {}", request);
		return paymentService.processPayment(request);
	}
	
	@GetMapping("/{paymentId}")
	public PaymentResponseDto getPaymentDetails(@PathVariable Long paymentId) {
		log.info("PaymentController getPaymentDetails");
		return paymentService.getPaymentDetails(paymentId);
	}
}
