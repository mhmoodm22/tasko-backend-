package com.taskmanager.ws.io.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



@Entity(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
public class TaskEntity implements Serializable {

	private static final long serialVersionUID = 304735113199276500L;

	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false,name = "userId")
	private String userId;
	@Column(nullable = false)
	private String taskId;
	
	@Column(nullable = false, length = 50)
	private String title;
	
	@Column
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name = "status", nullable = false)
	private String status;
	
//	@Column(name = "collaborator")
//	private Json earnedPoint;
	

	
	@Column(nullable = false)
	private String category;
	
	
	@Column(name = "point",length = 2)
	private int point;
	
	@Column(length = 1)
	private int isCollaborated;
	
	
	
	@Column(name = "createTime", updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	@Column(name = "updateTime")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updateTime;
	@Column(name = "createUser", updatable = false)
	@CreatedBy
	private String createUser;
	@Column(name = "updateUser")
	@LastModifiedBy
	private String updateUser;

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

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getIsCollaborated() {
		return isCollaborated;
	}

	public void setIsCollaborated(int isCollaborated) {
		this.isCollaborated = isCollaborated;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
}
