package com.javaexpress.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByCredentialUsername(String username); 
}
