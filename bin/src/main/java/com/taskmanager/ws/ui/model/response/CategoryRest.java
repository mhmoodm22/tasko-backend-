package com.taskmanager.ws.ui.model.response;

public class CategoryRest {
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


	public CategoryRest(long id, String catName) {
		super();
		this.id = id;
		this.catName = catName;
	}


	public CategoryRest() {
		super();
	}
	
	
	
}
