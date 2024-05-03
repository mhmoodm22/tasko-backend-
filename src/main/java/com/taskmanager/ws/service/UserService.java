package com.taskmanager.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.taskmanager.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto user);

	UserDto getUser(String email);

	List<UserDto> getAllUser(String id);
	
	UserDto getUserByUserId(String id);

	UserDto uploadImage(String id, byte[] image);
	
	UserDto updatePassword(String email, String password);



}


