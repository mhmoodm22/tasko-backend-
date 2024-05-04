package com.taskmanager.ws.ui.model.response;

public class TaskStatusRest {
	private long id;

	private String statusTitle;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatusTitle() {
		return statusTitle;
	}

	public void setStatusTitle(String statusTitle) {
		this.statusTitle = statusTitle;
	}

	public TaskStatusRest(long id, String statusTitle) {
		super();
		this.id = id;
		this.statusTitle = statusTitle;
	}

	public TaskStatusRest() {
		super();
	}
	
	

}
