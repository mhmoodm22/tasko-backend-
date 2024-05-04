
package com.taskmanager.ws.io.entity;

import java.io.Serializable;
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

@Entity(name = "taskstatus")
@EntityListeners(AuditingEntityListener.class)
public class TaskStatusEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 304735113199276500L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String statusTitle;

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



	public String getStatusTitle() {
		return statusTitle;
	}

	public void setStatusTitle(String statusTitle) {
		this.statusTitle = statusTitle;
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