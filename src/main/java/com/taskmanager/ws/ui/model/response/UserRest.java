package com.taskmanager.ws.ui.model.response;

import javax.persistence.Lob;

public class UserRest {

	private String userId;
	private String userName;
	private String email;

	byte[] img;

	private Integer earnedPoint;
	private String currentLabel;
	private int labelIndex;
	private int currentPoint;
	private int nextLabelPoint;

	


	public String getCurrentLabel() {
		return currentLabel;
	}

	public void setCurrentLabel(String currentLabel) {
		this.currentLabel = currentLabel;
	}

	public int getLabelIndex() {
		return labelIndex;
	}

	public void setLabelIndex(int labelIndex) {
		this.labelIndex = labelIndex;
	}

	public int getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(int currentPoint) {
		this.currentPoint = currentPoint;
	}

	public int getNextLabelPoint() {
		return nextLabelPoint;
	}

	public void setNextLabelPoint(int nextLabelPoint) {
		this.nextLabelPoint = nextLabelPoint;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEarnedPoint() {
		return earnedPoint;
	}

	public void setEarnedPoint(Integer earnedPoint) {
		this.earnedPoint = earnedPoint;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}


	
	

}
