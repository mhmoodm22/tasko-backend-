package com.taskmanager.ws.shared.dto;

import java.io.Serializable;

public class TaskStatusDto implements Serializable {

	private static final long serialVersionUID = 2042159732813992708L;
	
	public TaskStatusDto() {
		super();
	}


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


	public TaskStatusDto(long id, String statusTitle) {
		super();
		this.id = id;
		this.statusTitle = statusTitle;
	}



	
	
}
