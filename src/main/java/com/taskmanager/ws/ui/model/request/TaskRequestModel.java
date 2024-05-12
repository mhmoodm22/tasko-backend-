package com.taskmanager.ws.ui.model.request;

import java.util.Date;
import java.util.List;

public class TaskRequestModel {
	
	private String userId;
	private String title;
	private String description;
	private Date date;
	private List<String> collaboratorsId;
	private String category;
	
	
	public List<String> getCollaboratorsId() {
		return collaboratorsId;
	}
	public void setCollaboratorsId(List<String> collaboratorsId) {
		this.collaboratorsId = collaboratorsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	

}
