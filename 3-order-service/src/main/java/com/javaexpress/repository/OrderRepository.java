package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
