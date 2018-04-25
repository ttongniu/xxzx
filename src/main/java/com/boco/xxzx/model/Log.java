package com.boco.xxzx.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value="Log")
public class Log implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private String operatorName;
	private Date operatorTime;
	private String operatorDate;
	private String operatorIP;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Date getOperatorTime() {
		return operatorTime;
	}
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	
	public String getOperatorIP() {
		return operatorIP;
	}
	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}
	
	public String getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(String operatorDate) {
		this.operatorDate = operatorDate;
	}
	
	
	public Log() {
		super();
	}
	public Log(Integer id, User user, String operatorName, Date operatorTime, String operatorIP) {
		super();
		this.id = id;
		this.user = user;
		this.operatorName = operatorName;
		this.operatorTime = operatorTime;
		this.operatorIP = operatorIP;
	}
	
	
}
