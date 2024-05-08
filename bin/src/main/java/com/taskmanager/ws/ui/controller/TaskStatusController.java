package com.taskmanager.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.ws.service.TaskStatusService;
import com.taskmanager.ws.shared.dto.CategoryDto;
import com.taskmanager.ws.shared.dto.TaskStatusDto;
import com.taskmanager.ws.shared.dto.UserDto;
import com.taskmanager.ws.ui.model.request.UserDetailsRequestModel;
import com.taskmanager.ws.ui.model.response.CategoryRest;
import com.taskmanager.ws.ui.model.response.TaskStatusRest;
import com.taskmanager.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("taskstatuslist")
public class TaskStatusController {

	@Autowired
	TaskStatusService taskStatusService;

	@GetMapping
	public List<TaskStatusRest> allTaskStatus() {

		List<TaskStatusRest> returnValue = new ArrayList<>();

		List<TaskStatusDto> taskStatusList = taskStatusService.getAllTaskStatus();

		for (TaskStatusDto taskStatus : taskStatusList) {

			TaskStatusRest taskRest = new TaskStatusRest(taskStatus.getId(), taskStatus.getStatusTitle());

			returnValue.add(taskRest);

		}

		return returnValue;
	}

}
