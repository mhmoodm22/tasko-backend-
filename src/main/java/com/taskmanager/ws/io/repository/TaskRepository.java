package com.taskmanager.ws.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taskmanager.ws.io.entity.CategoryEntity;
import com.taskmanager.ws.io.entity.TaskEntity;



@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
	List<TaskEntity> findAllTaskByUserId(String userId);
	List<TaskEntity> findAllTaskByUserIdAndIsCollaborated(String userId,int col);
	List<TaskEntity> findAllByCategoryAndUserId(String catName,String userID);
	List<TaskEntity> findAllByStatusAndUserId(String status,String userID);
	TaskEntity findByTaskId(String taskId);
	

}
