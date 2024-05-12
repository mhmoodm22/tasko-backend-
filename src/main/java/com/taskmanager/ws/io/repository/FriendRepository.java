package com.taskmanager.ws.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.taskmanager.ws.io.entity.FriendEntity;
@Repository
public interface FriendRepository extends CrudRepository<FriendEntity, Long> {
	
	List<FriendEntity> findAllFriendByUserIdAndStatus(String id,int status);
	
	List<FriendEntity> findAllFriendByUserId(String id);

	List<FriendEntity> findAllFriendByFriendIdAndStatus(String id,int status);
	
}
