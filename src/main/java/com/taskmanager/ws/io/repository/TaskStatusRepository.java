package com.taskmanager.ws.io.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.taskmanager.ws.io.entity.TaskStatusEntity;



@Repository
public interface TaskStatusRepository extends CrudRepository<TaskStatusEntity, Long> {
	
	
	//List<TaskStatusEntity> findAll();


}
