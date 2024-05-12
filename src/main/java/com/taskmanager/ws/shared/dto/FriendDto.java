package com.taskmanager.ws.shared.dto;

import java.util.Date;

public class FriendDto {
	
	private long id;
	private String userId;
	private String friendId;
	private String friendName;
	private byte[] img;
	private String currentLabel;
	private int status;
	private Date createTime;
	
	
	
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
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public String getCurrentLabel() {
		return currentLabel;
	}
	public void setCurrentLabel(String currentLabel) {
		this.currentLabel = currentLabel;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
	
}
