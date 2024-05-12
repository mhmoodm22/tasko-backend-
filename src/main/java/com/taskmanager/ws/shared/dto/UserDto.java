package com.taskmanager.ws.shared.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 2042159732813992708L;

	private long id;
	private String userId;
	private String userName;
	private String email;
	private String password;
	private byte[] img;


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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public Integer getEarnedPoint() {
		return earnedPoint;
	}

	public void setEarnedPoint(Integer earnedPoint) {
		this.earnedPoint = earnedPoint;
	}

}
