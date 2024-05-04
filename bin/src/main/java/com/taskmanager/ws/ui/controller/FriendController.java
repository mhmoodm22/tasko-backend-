//package com.taskmanager.ws.ui.controller;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.taskmanager.ws.service.UserService;
//import com.taskmanager.ws.shared.dto.UserDto;
//import com.taskmanager.ws.ui.model.response.UserRest;
//
//@RestController
//@RequestMapping("friend")
//public class FriendController {
//	
//	
//	@Autowired
//	UserService userService;
//
//	
//	@GetMapping()
//	public List<UserRest> getFriendSuggest(@RequestParam("id") String id) {
//		UserRest returnValue = new UserRest();
//
//		UserDto currentUser = userService.getUserByUserId(id);
//		
//		
//		
//		
//		
//		BeanUtils.copyProperties(createUser, returnValue);
//
//		return returnValue;
//
//	}
//	
//
//}
