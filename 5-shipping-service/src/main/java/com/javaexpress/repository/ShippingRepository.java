package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.model.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
	
}
