package com.javaexpress.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.CredentialDto;
import com.javaexpress.dto.UserDto;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.models.Credential;
import com.javaexpress.models.User;
import com.javaexpress.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserDto save(UserDto userDto) {
		// TODO Auto-generated method stub
		
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		
		Credential credential = new Credential();
		BeanUtils.copyProperties(userDto.getCredential(), credential);
		
		//set bidirectional relationship
		user.setCredential(credential);
		credential.setUser(user);
		
		//TODO: encode the raw text password before saving the user in database 
		User dbUser = userRepository.save(user);
		
		return maptoDto(dbUser);
	}
	
	private UserDto maptoDto(User dbUser)
	{
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(dbUser, userDto);
		
		CredentialDto credentialDto = new CredentialDto();
		BeanUtils.copyProperties(dbUser.getCredential(), credentialDto);
		
		userDto.setCredential(credentialDto);
		
		return userDto;
	}

	public UserDto findById(Long userId) {
		// TODO Auto-generated method stub
		Optional<User> optional = userRepository.findById(userId);
		if(optional.isPresent())
		{
			User dbUser = optional.get();
			return maptoDto(dbUser);
		}
		else {
			 throw new ResourceNotFoundException("User not exists");
		}
	}

	public UserDto getByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByCredentialUsername(username)
				.map(user->maptoDto(user))
				.orElseThrow(()->new ResourceNotFoundException("User not exists"));
	}

}
