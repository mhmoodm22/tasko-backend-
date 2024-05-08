package com.taskmanager.ws.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskmanager.ws.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
		
	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String id);
	
	
}
