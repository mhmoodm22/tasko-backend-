package com.taskmanager.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.ws.io.entity.CategoryEntity;
import com.taskmanager.ws.io.entity.TaskStatusEntity;
import com.taskmanager.ws.io.repository.TaskStatusRepository;
import com.taskmanager.ws.service.CategoryService;
import com.taskmanager.ws.service.TaskStatusService;
import com.taskmanager.ws.shared.dto.CategoryDto;
import com.taskmanager.ws.shared.dto.TaskStatusDto;

@Service
public class TaskServiceImpl implements TaskStatusService {

	@Autowired
	TaskStatusRepository taskStatusRepository;

	@Override
	public List<TaskStatusDto> getAllTaskStatus() {
		List<TaskStatusDto> returnValue = new ArrayList<>();


		List<TaskStatusEntity> taskStatusEntitys = (List<TaskStatusEntity>) taskStatusRepository.findAll();

		for (TaskStatusEntity taskEntity : taskStatusEntitys) {
			TaskStatusDto taskDto = new TaskStatusDto(taskEntity.getId(),taskEntity.getStatusTitle());
			returnValue.add(taskDto);
		}
		

		return returnValue;
	}


}
