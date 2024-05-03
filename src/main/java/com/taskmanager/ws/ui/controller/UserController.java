package com.taskmanager.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.taskmanager.ws.io.entity.UserEntity;
import com.taskmanager.ws.service.UserService;
import com.taskmanager.ws.shared.dto.UserDto;
import com.taskmanager.ws.ui.model.request.UserDetailsRequestModel;
import com.taskmanager.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping()
	public UserRest getUser(@RequestParam("id") String id) {
		UserRest returnValue = new UserRest();

		UserDto user = userService.getUserByUserId(id);
		BeanUtils.copyProperties(user, returnValue);

		return returnValue;

	}
	
	
	@GetMapping(path = "/suggestion")
	public List<UserRest> getFriendSuggestion(@RequestParam("id") String id) {
		List<UserRest> returnValue = new ArrayList<>();

		List<UserDto> users = userService.getAllUser(id);

		
		
		for (UserDto user: users ) {
			
			UserRest userRest = new UserRest();
			
			userRest.setUserId(user.getUserId());
			userRest.setImage(user.getImges());
			userRest.setUserName(user.getUserName());
			userRest.setCurrentLabel(user.getCurrentLabel());
			
			returnValue.add(userRest);
		}

		return returnValue;

	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

		UserRest returnValue = new UserRest();

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userDetails, userDto);

		UserDto createUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createUser, returnValue);

		return returnValue;
	}

	@PutMapping("/password-reset")
	public UserRest resetUserPassword(@RequestParam("email") String email, @RequestParam("password") String newPassword) {
		
		if (email.isEmpty()) {
			throw new RuntimeErrorException(null, "Email Not found");
		}
		UserRest returnValue = new UserRest();
		// Get the file bytes
		
		UserDto updateImage = userService.updatePassword(email, newPassword);
		BeanUtils.copyProperties(updateImage, returnValue);
		
		return returnValue;
	
	}


	@PostMapping("/change-image")
	public UserRest changeImage(@RequestParam("file") MultipartFile file, @RequestParam("id") String id)  {
		if (file.isEmpty()) {
			throw new RuntimeErrorException(null, "File Upload Failed");
		}

		try {

			UserRest returnValue = new UserRest();
			// Get the file bytes
			byte[] bytes = file.getBytes();
			
			UserDto updateImage = userService.uploadImage(id, bytes);
			BeanUtils.copyProperties(updateImage, returnValue);
			
			return returnValue;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeErrorException(null, "File Upload Failed");
		}
	}

}
