package com.taskmanager.ws.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.taskmanager.ws.io.entity.FriendEntity;
@Repository
public interface FriendRepository extends CrudRepository<FriendEntity, Long> {
	
	List<FriendEntity> findfreindList(String id);
	
	List<FriendEntity> findrequestedfreindList(String id);
	

}
