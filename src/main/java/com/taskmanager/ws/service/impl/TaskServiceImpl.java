package com.taskmanager.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.ws.io.entity.LevelEntity;
import com.taskmanager.ws.io.entity.TaskEntity;
import com.taskmanager.ws.io.entity.UserEntity;
import com.taskmanager.ws.io.repository.LavelRepository;
import com.taskmanager.ws.io.repository.TaskRepository;
import com.taskmanager.ws.io.repository.UserRepository;
import com.taskmanager.ws.service.TaskService;
import com.taskmanager.ws.shared.Utils;
import com.taskmanager.ws.shared.dto.TaskDto;
import com.taskmanager.ws.shared.dto.UserDto;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	LavelRepository lavelRepository;

	@Autowired
	Utils utils;

	@Override
	public List<TaskDto> getAllTask(String userId) {
		List<TaskEntity> allTask = (List<TaskEntity>) taskRepository.findAllTaskByUserId(userId);

		List<TaskDto> taskDtos = new ArrayList<>();

		for (TaskEntity entity : allTask) {
			TaskDto taskdto = new TaskDto();
			BeanUtils.copyProperties(entity, taskdto);

			taskDtos.add(taskdto);
		}

		return taskDtos;
	}

	@Override
	public List<TaskDto> getAllTaskByStatus(String status, String userId) {
		List<TaskEntity> allTask = (List<TaskEntity>) taskRepository.findAllByStatusAndUserId(status, userId);

		List<TaskDto> taskDtos = new ArrayList<>();

		for (TaskEntity entity : allTask) {
			TaskDto taskdto = new TaskDto();
			BeanUtils.copyProperties(entity, taskdto);

			taskDtos.add(taskdto);
		}

		return taskDtos;
	}

	@Override
	public List<TaskDto> getAllTaskByCategory(String category, String userId) {
		List<TaskEntity> allTask = (List<TaskEntity>) taskRepository.findAllByCategoryAndUserId(category, userId);

		List<TaskDto> taskDtos = new ArrayList<>();

		for (TaskEntity entity : allTask) {
			TaskDto taskdto = new TaskDto();
			BeanUtils.copyProperties(entity, taskdto);

			taskDtos.add(taskdto);
		}

		return taskDtos;
	}

	@Override
	public TaskDto createTask(TaskDto task) {
		// TODO Auto-generated method stub

		TaskEntity taskEntity = new TaskEntity();
		BeanUtils.copyProperties(task, taskEntity);

		String taskId = utils.generateUserId(30);
		taskEntity.setPoint(20);
		taskEntity.setTaskId(taskId);
		taskEntity.setIsCollaborated(0);
		taskEntity.setStatus("Pending");
		TaskEntity storedUserDetails = taskRepository.save(taskEntity);
		TaskDto returnValue = new TaskDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		return returnValue;

	}

	@Override
	public TaskDto updateTask(TaskDto task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskDto changeTaskStatus(String taskID, String status, String userID) {

		TaskDto returnValue = new TaskDto();

		TaskEntity entity = taskRepository.findByTaskId(taskID);

		entity.setStatus(status);

		TaskEntity saveEntity = taskRepository.save(entity);

		BeanUtils.copyProperties(saveEntity, returnValue);

		UserEntity userEntity = userRepository.findByUserId(userID);
		
		String done = "Done";

		if (status.trim().equals(done)) {
			int currentPoint = userEntity.getCurrentPoint() + 20;

			userEntity.setCurrentPoint(currentPoint);

			LevelEntity levelEntitie = lavelRepository.findFirstByLabellimitGreaterThan(currentPoint);

			if (levelEntitie != null) {
				int nextlabelPoint = levelEntitie.getLabellimit() - currentPoint;

				userEntity.setNextLabelPoint(nextlabelPoint);
				userEntity.setCurrentLabel(levelEntitie.getLabelName());
			}

		}

		userRepository.save(userEntity);

		return returnValue;
	}

	@Override
	public List<TaskDto> getAllTaskByCollaboration(String userID) {
		List<TaskEntity> allTask = (List<TaskEntity>) taskRepository.findAllTaskByUserIdAndIsCollaborated(userID, 1);

		List<TaskDto> taskDtos = new ArrayList<>();

		for (TaskEntity entity : allTask) {
			TaskDto taskdto = new TaskDto();
			BeanUtils.copyProperties(entity, taskdto);

			taskDtos.add(taskdto);
		}

		return taskDtos;
	}

}
