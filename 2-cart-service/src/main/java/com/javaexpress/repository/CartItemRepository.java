package com.javaexpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	List<CartItem> findByUserId(Long userId);
}
