package com.taskmanager.ws.service;

import java.util.List;

import com.taskmanager.ws.shared.dto.CategoryDto;
import com.taskmanager.ws.shared.dto.TaskStatusDto;


public interface TaskStatusService {
	
	List<TaskStatusDto> getAllTaskStatus();
	
}
