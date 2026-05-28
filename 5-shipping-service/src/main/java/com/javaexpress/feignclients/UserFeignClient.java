package com.javaexpress.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.dto.UserDto;

@FeignClient(name = "user-service",path = "/users")
public interface UserFeignClient {

	@GetMapping("/{userId}")
	public UserDto findById(@PathVariable Long userId);
}
