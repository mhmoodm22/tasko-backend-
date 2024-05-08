package com.taskmanager.ws.service;

import java.util.List;

import com.taskmanager.ws.io.entity.FriendEntity;

public interface FriendService {
	List<FriendEntity> findAllfreindList(String id);
	
	List<FriendEntity> findRequestedfreindList(String id);
}
