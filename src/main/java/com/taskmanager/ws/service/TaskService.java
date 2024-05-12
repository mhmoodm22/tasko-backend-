package com.taskmanager.ws.service;

import java.util.List;

import com.taskmanager.ws.shared.dto.TaskDto;
import com.taskmanager.ws.shared.dto.TaskStatusDto;
import com.taskmanager.ws.shared.dto.UserDto;

public interface TaskService {
	List<TaskDto> getAllTask(String id);
	List<TaskDto> getAllTaskByStatus(String status,String userID);
	List<TaskDto> getAllTaskByCategory(String category,String userID);
	List<TaskDto> getAllTaskByCollaboration(String userID);
	TaskDto createTask(TaskDto task);
	TaskDto updateTask(TaskDto task);
	TaskDto changeTaskStatus(String taskID,String status,String userID);
	TaskDto deleteTask(String taskID);
	TaskDto getTask(String taskID);
	
}
