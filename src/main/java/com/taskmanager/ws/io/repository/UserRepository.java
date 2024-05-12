package com.taskmanager.ws.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taskmanager.ws.io.entity.UserEntity;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<UserEntity, Long> {
		
	UserEntity findByEmail(String email);
	

	UserEntity findByUserId(String id);
	
	
}
