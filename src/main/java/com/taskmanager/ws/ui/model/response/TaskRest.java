package com.taskmanager.ws.ui.model.response;

import java.util.Date;

public class TaskRest {
	private String taskId;
	private String title;
	private String description;
	private Date date;
	private String status;
	private String category;
	private int point;
	private int isCollaborated;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getIsCollaborated() {
		return isCollaborated;
	}
	public void setIsCollaborated(int isCollaborated) {
		this.isCollaborated = isCollaborated;
	}
	
	
	
}
