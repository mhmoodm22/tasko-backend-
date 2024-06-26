package com.taskmanager.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.ws.io.entity.CollaboratorEntity;
import com.taskmanager.ws.io.entity.TaskEntity;
import com.taskmanager.ws.service.TaskService;
import com.taskmanager.ws.service.UserService;
import com.taskmanager.ws.shared.dto.CollaboratorDto;
import com.taskmanager.ws.shared.dto.TaskDto;
import com.taskmanager.ws.shared.dto.UserDto;
import com.taskmanager.ws.ui.model.request.TaskRequestModel;
import com.taskmanager.ws.ui.model.request.UserDetailsRequestModel;
import com.taskmanager.ws.ui.model.response.CollaboratorRest;
import com.taskmanager.ws.ui.model.response.TaskRest;
import com.taskmanager.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("tasks")
public class TaskController {

	@Autowired
	TaskService taskService;

	@PostMapping
	public TaskRest createTask(@RequestBody TaskRequestModel taskDetail) {

		if (taskDetail.getUserId() == null || taskDetail.getUserId().trim() == "") {
			throw new RuntimeErrorException(null, "User Id Not found");
		}

		TaskRest returnValue = new TaskRest();

		TaskDto taskDto = new TaskDto();

		BeanUtils.copyProperties(taskDetail, taskDto);
		
		List<CollaboratorDto> collaboratorsDto = new ArrayList<>();
		
		if(!taskDetail.getCollaboratorsId().isEmpty()) {
			
			taskDetail.getCollaboratorsId().forEach(t ->{
				CollaboratorDto dto = new CollaboratorDto();
				
				dto.setFriendId(t);
				collaboratorsDto.add(dto);
			
			} );
		}
		
		taskDto.setCollaboratorDto(collaboratorsDto);

		TaskDto createTask = taskService.createTask(taskDto);

		BeanUtils.copyProperties(createTask, returnValue);

		List<CollaboratorDto> CollaboratorDtos = createTask.getCollaboratorDto();

		List<CollaboratorRest> collaboratorRest = new ArrayList<>();

		CollaboratorDtos.forEach(t -> {
			CollaboratorRest rest = new CollaboratorRest();

			rest.setStatus(t.getStatus());
			rest.setFriendId(t.getFriendId());
			rest.setUserId(t.getUserId());
			rest.setImg(t.getImg());

			collaboratorRest.add(rest);

		});

		returnValue.setCollaboators(collaboratorRest);

		return returnValue;
	}

	@PutMapping("/updateStatus")
	public TaskRest updateTaskStatus(@RequestParam("taskID") String taskID, @RequestParam("status") String status,
			@RequestParam("userID") String userID) {

		TaskRest returnValue = new TaskRest();

		TaskDto createTask = taskService.changeTaskStatus(taskID, status, userID);
		BeanUtils.copyProperties(createTask, returnValue);

		return returnValue;
	}

	@GetMapping("/category")
	public List<TaskRest> getTaskListByCategory(@RequestParam("category") String category,
			@RequestParam("userID") String userID) {

		List<TaskRest> returnValue = new ArrayList<>();

		List<TaskDto> taskList = taskService.getAllTaskByCategory(category, userID);

		for (TaskDto dto : taskList) {
			TaskRest taskrest = new TaskRest();
			BeanUtils.copyProperties(dto, taskrest);

			returnValue.add(taskrest);
		}

		return returnValue;
	}

	@GetMapping("/status")
	public List<TaskRest> getTaskListByStatus(@RequestParam("status") String status,
			@RequestParam("userID") String userID) {

		List<TaskRest> returnValue = new ArrayList<>();

		List<TaskDto> taskList = taskService.getAllTaskByStatus(status, userID);

		for (TaskDto dto : taskList) {
			TaskRest taskrest = new TaskRest();
			BeanUtils.copyProperties(dto, taskrest);

			returnValue.add(taskrest);
		}

		return returnValue;
	}

	@GetMapping
	public List<TaskRest> getTaskList(@RequestParam("userID") String userID) {

		List<TaskRest> returnValue = new ArrayList<>();

		List<TaskDto> taskList = taskService.getAllTask(userID);

		for (TaskDto dto : taskList) {
			TaskRest taskrest = new TaskRest();
			BeanUtils.copyProperties(dto, taskrest);

			returnValue.add(taskrest);
		}

		return returnValue;
	}

	@GetMapping("/collaborated")
	public List<TaskRest> getCollaboratedTaskList(@RequestParam("userID") String userID) {

		List<TaskRest> returnValue = new ArrayList<>();

		List<TaskDto> taskList = taskService.getAllTaskByCollaboration(userID);

		for (TaskDto dto : taskList) {
			TaskRest taskrest = new TaskRest();
			BeanUtils.copyProperties(dto, taskrest);

			returnValue.add(taskrest);
		}

		return returnValue;
	}

	@DeleteMapping("/deleteTask")
	public TaskRest deleteTask(@RequestParam("taskID") String taskID) {

		TaskRest returnValue = new TaskRest();

		TaskDto createTask = taskService.deleteTask(taskID);
		BeanUtils.copyProperties(createTask, returnValue);

		return returnValue;
	}

	@GetMapping("/getTaskDetail")
	public TaskRest getTaskDetail(@RequestParam("taskID") String taskID) {

		TaskRest returnValue = new TaskRest();

		TaskDto createTask = taskService.getTask(taskID);
		BeanUtils.copyProperties(createTask, returnValue);

		return returnValue;
	}

}
