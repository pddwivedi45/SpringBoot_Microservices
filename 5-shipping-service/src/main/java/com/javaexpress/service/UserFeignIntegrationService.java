package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.UserDto;
import com.javaexpress.feignclients.UserFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserFeignIntegrationService {

	@Autowired
	private UserFeignClient userFeignClient;
	
	public UserDto fetchUserDetails(Long userId) {
		return userFeignClient.findById(userId);
	}
}
