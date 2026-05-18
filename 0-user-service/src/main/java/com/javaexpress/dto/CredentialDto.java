package com.javaexpress.dto;

import com.javaexpress.models.Role;

import lombok.Data;

@Data		
public class CredentialDto {

	private String username;
	private String password;
	private Role role;   
}
