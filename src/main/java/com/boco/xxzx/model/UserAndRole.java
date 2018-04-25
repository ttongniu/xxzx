package com.boco.xxzx.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
@Alias(value="UserAndRole")
public class UserAndRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String employeeId;
	private Integer roleId;
	private String creater;
	private Date createTime;
	private String modifier;
	private Date modifyTime;
	private User employee;
	private Dictionary role;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

	public UserAndRole() {
		super();
	}

	public User getUser() {
		return employee;
	}

	public void setUser(User employee) {
		this.employee = employee;
	}
	

	public Dictionary getRole() {
		return role;
	}

	public void setRole(Dictionary role) {
		this.role = role;
	}
	

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public UserAndRole(Integer id, String employeeId, Integer roleId,
			String creater, Date createTime, String modifier, Date modifyTime,
			User employee, Dictionary role) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.roleId = roleId;
		this.creater = creater;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.employee = employee;
		this.role = role;
	}

	public UserAndRole(Integer id, String employeeId, Integer roleId,
			String creater, Date createTime, String modifier,
			Date modifyTime) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.roleId = roleId;
		this.creater = creater;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
	}
	

}
