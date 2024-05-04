package com.taskmanager.ws.shared.dto;

import java.io.Serializable;

public class CategoryDto implements Serializable {

	private static final long serialVersionUID = 2042159732813992708L;
	
	private long id;


	private String catName;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCatName() {
		return catName;
	}


	public void setCatName(String catName) {
		this.catName = catName;
	}


	public CategoryDto(long id, String catName) {
		super();
		this.id = id;
		this.catName = catName;
	}


	public CategoryDto() {
		super();
	}

	
	
}
