package com.akash.struts;

import java.sql.Timestamp;
import java.util.Date;

public class UserBean {

	private int userId;
	private String userName;
	private int approved;
	private Timestamp registerTime;
	private Timestamp approvedTime;
	private int levelID;
	private String lName;

	public int getLevelID() {
		return levelID;
	}

	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public Timestamp getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(Timestamp approvedTime) {
		this.approvedTime = approvedTime;
	}
	
}
