package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
