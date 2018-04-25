package com.boco.xxzx.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias(value="Department")
public class Department implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String depId;
	private String parentId;
	private String depNum;
	private String depName;
	private String creater;
	private Date createTime;
	private String modifier;
	private Date modifyTime;
    private Integer flag;
    private List<User> userList;
	
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDepNum() {
		return depNum;
	}
	public void setDepNum(String depNum) {
		this.depNum = depNum;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public Department() {
		super();
	}
	public Department(String depId, String parentId, String depNum,
			String depName, String creater, Date createTime, String modifier,
			Date modifyTime, Integer flag, List<User> userList) {
		super();
		this.depId = depId;
		this.parentId = parentId;
		this.depNum = depNum;
		this.depName = depName;
		this.creater = creater;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.flag = flag;
		this.userList = userList;
	}
	
}
