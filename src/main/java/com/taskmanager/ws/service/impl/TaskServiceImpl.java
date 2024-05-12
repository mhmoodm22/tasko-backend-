package com.taskmanager.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.ws.io.entity.CollaboratorEntity;
import com.taskmanager.ws.io.entity.LevelEntity;
import com.taskmanager.ws.io.entity.TaskEntity;
import com.taskmanager.ws.io.entity.UserEntity;
import com.taskmanager.ws.io.repository.LavelRepository;
import com.taskmanager.ws.io.repository.TaskRepository;
import com.taskmanager.ws.io.repository.UserRepository;
import com.taskmanager.ws.service.TaskService;
import com.taskmanager.ws.shared.Utils;
import com.taskmanager.ws.shared.dto.CollaboratorDto;
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
		int point = utils.generatePoint();
		taskEntity.setPoint(point);
		taskEntity.setTaskId(taskId);
		taskEntity.setIsCollaborated(0);
		taskEntity.setStatus("Pending");

		List<CollaboratorEntity> collaboratorEntitys = new ArrayList<>();

		if (!task.getCollaboratorDto().isEmpty()) {
			taskEntity.setIsCollaborated(1);
			task.getCollaboratorDto().forEach(t -> {
				CollaboratorEntity collaboratorEntity = new CollaboratorEntity();

				collaboratorEntity.setTaskEntity(taskEntity);
				collaboratorEntity.setStatus(0);
				collaboratorEntity.setUserId(task.getUserId());
				collaboratorEntity.setFriendId(t.getFriendId());
				collaboratorEntitys.add(collaboratorEntity);
			});
		}

		taskEntity.setCollaboratorEntitys(collaboratorEntitys);
		TaskEntity storedUserDetails = taskRepository.save(taskEntity);

		TaskDto returnValue = new TaskDto();

		BeanUtils.copyProperties(storedUserDetails, returnValue);

		List<CollaboratorEntity> collaboratorEntities = storedUserDetails.getCollaboratorEntitys();

		List<CollaboratorDto> CollaboratorDtos = new ArrayList<>();

		collaboratorEntities.forEach(t -> {
			CollaboratorDto dto = new CollaboratorDto();

			UserEntity userEntity = userRepository.findByUserId(t.getFriendId());

			dto.setStatus(t.getStatus());
			dto.setFriendId(t.getFriendId());
			dto.setUserId(t.getUserId());
			dto.setImg(userEntity.getImg());

			CollaboratorDtos.add(dto);

		});

		returnValue.setCollaboratorDto(CollaboratorDtos);

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

	@Override
	public TaskDto deleteTask(String taskID) {
		TaskDto returnValue = new TaskDto();
		TaskEntity entity = taskRepository.findByTaskId(taskID);

		try {
			if (entity != null) {
				taskRepository.delete(entity);
				BeanUtils.copyProperties(entity, returnValue);
			}
		} catch (Exception e) {
			throw new RuntimeException("Delete operation Failed");
		}

		return returnValue;
	}

	@Override
	public TaskDto getTask(String taskID) {
		TaskDto returnValue = new TaskDto();
		TaskEntity entity = taskRepository.findByTaskId(taskID);

		try {
			if (entity != null) {
				BeanUtils.copyProperties(entity, returnValue);
			}
		} catch (Exception e) {
			throw new RuntimeException("Task Not Found");
		}

		return returnValue;
	}
}
