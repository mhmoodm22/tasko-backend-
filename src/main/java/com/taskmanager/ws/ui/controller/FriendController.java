package com.taskmanager.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.ws.service.FriendService;
import com.taskmanager.ws.service.UserService;
import com.taskmanager.ws.shared.dto.FriendDto;
import com.taskmanager.ws.shared.dto.UserDto;
import com.taskmanager.ws.ui.model.response.FriendRest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("friend")
public class FriendController {

	@Autowired
	UserService userService;

	@Autowired
	FriendService friendService;

	@GetMapping(path = "/suggestion")
	public List<FriendRest> getFriendSuggestion(@RequestParam("id") String id) {
		List<FriendRest> returnValue = new ArrayList<>();

		List<UserDto> users = userService.getAllUser(id);

		List<FriendDto> excludeList = friendService.findAllfreindList(id);

		for (UserDto user : users) {

//			excludeList.forEach(item -> {
//				System.out.println(item);
//
//				if (item.getUserId().equals(user.getUserId())) {
//					return;
//				}
//				;

				FriendRest userRest = new FriendRest();

				userRest.setFriendId(user.getUserId());
				userRest.setImg(user.getImg());
				userRest.setUserName(user.getUserName());
				userRest.setCurrentLabel(user.getCurrentLabel());

				returnValue.add(userRest);
			//});

		}

		return returnValue;

	}

	@PostMapping(path = "/sendRequest")
	public FriendRest sendfriendRequest(@RequestParam("userId") String userId,
			@RequestParam("friendId") String friendId) {

		FriendRest returnValue = new FriendRest();

		FriendDto friendDto = friendService.sendFriendRequest(userId, friendId);

		returnValue.setFriendId(friendId);
		returnValue.setCurrentLabel(friendDto.getCurrentLabel());
		returnValue.setImg(friendDto.getImg());
		returnValue.setUserName(friendDto.getFriendName());

		return returnValue;
	}

	@PutMapping(path = "/acceptRequest")
	public FriendRest acceptfriendRequest(@RequestParam("id") Long id) {

		FriendRest returnValue = new FriendRest();

		FriendDto friendDto = friendService.acceptFriendRequest(id);

		returnValue.setFriendId(friendDto.getFriendId());
		returnValue.setCurrentLabel(friendDto.getCurrentLabel());
		returnValue.setImg(friendDto.getImg());
		returnValue.setUserName(friendDto.getFriendName());

		return returnValue;
	}
	
	
	@PutMapping(path = "/rejectRequest")
	public FriendRest rejectfriendRequest(@RequestParam("id") Long id) {

		FriendRest returnValue = new FriendRest();

		FriendDto friendDto = friendService.rejectFriendRequest(id);

		returnValue.setFriendId(friendDto.getFriendId());
		returnValue.setCurrentLabel(friendDto.getCurrentLabel());
		returnValue.setImg(friendDto.getImg());
		returnValue.setUserName(friendDto.getFriendName());

		return returnValue;
	}

	@GetMapping(path = "/newRequest")
	public List<FriendDto> getFriedRequest(@RequestParam("userId") String userId) {

		List<FriendDto> friendDto = friendService.findNewFriendRequestedList(userId);

		return friendDto;
	}

}
