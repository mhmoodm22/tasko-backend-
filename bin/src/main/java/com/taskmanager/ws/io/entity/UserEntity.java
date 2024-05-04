package com.taskmanager.ws.io.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 304735113199276500L;

	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false)
	private String userId;
	@Column(nullable = false, length = 50)
	private String userName;
	@Column(nullable = false, length = 120)
	private String email;
	@Column(nullable = false, length = 10)
	private String password;

	@Column(name = "image")
	@Lob
	private java.sql.Blob img;

	@Column(name = "earnedPoint")
	private Integer earnedPoint;

	@Column(name = "currentLabel")
	private String currentLabel;

	@Column(name = "labelIndex")
	private int labelIndex;

	@Column(name = "currentPoint")
	private int currentPoint;

	@Column(name = "nextLabelPoint")
	private int nextLabelPoint;

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

	public Integer getEarnedPoint() {
		return earnedPoint;
	}

	public void setEarnedPoint(Integer earnedPoint) {
		this.earnedPoint = earnedPoint;
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

	@JsonIgnore
	public java.sql.Blob getImg() {
		return img;
	}

	public void setImg(java.sql.Blob img) {
		this.img = img;
	}

	public String getCurrentLabel() {
		return currentLabel;
	}

	public void setCurrentLabel(String currentLabel) {
		this.currentLabel = currentLabel;
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

	public int getLabelIndex() {
		return labelIndex;
	}

	public void setLabelIndex(int labelIndex) {
		this.labelIndex = labelIndex;
	}

}
