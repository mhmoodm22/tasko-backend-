package com.taskmanager.ws.service;

import java.util.List;

import com.taskmanager.ws.io.entity.FriendEntity;
import com.taskmanager.ws.shared.dto.FriendDto;

public interface FriendService {

	//should send your friend list
	List<FriendDto> findfreindList(String id);
	
	
	//should send your friend list that you have requested + current friend
	List<FriendDto> findAllfreindList(String id);
	
	//should send your friend list that you have requested
	List<FriendDto> findRequestedfreindList(String id);
	
	List<FriendDto> findNewFriendRequestedList(String id);
	
	
	FriendDto sendFriendRequest(String userId,String friendId);
	
	FriendDto acceptFriendRequest(Long id);
	
	FriendDto rejectFriendRequest(Long id);
}
