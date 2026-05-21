package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.UserDto;
import com.javaexpress.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public UserDto save(@RequestBody UserDto userDto)
	{
		System.out.println("UserDto is :" + userDto);
		return userService.save(userDto);
	}
	
	@GetMapping("/{userId}")
	public UserDto findById(@PathVariable Long userId)
	{
		return userService.findById(userId); 
	}
	
	@GetMapping("/username/{username}") 
	public UserDto getByUsername(@PathVariable String username)
	{
		return userService.getByUsername(username); 
	}
	
	
}
 