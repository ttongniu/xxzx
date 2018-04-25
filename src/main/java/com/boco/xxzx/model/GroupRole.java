package com.boco.xxzx.model;

import java.util.List;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年12月6日 上午11:17:15 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class GroupRole {
	private Integer grId;
	private Integer groupId;
	private Integer roleId;
	private Integer dataId;
	private Dictionary group;
	private Dictionary role;
	private Dictionary data;
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Dictionary getRole() {
		return role;
	}
	public void setRole(Dictionary role) {
		this.role = role;
	}
	public Dictionary getGroup() {
		return group;
	}
	public void setGroup(Dictionary group) {
		this.group = group;
	}
	public Dictionary getData() {
		return data;
	}
	public void setData(Dictionary data) {
		this.data = data;
	}
	public Integer getGrId() {
		return grId;
	}
	public void setGrId(Integer grId) {
		this.grId = grId;
	}
	
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	public GroupRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupRole(Integer grId, Integer groupId, Integer roleId,
			Integer dataId, Dictionary group, Dictionary role, Dictionary data) {
		super();
		this.grId = grId;
		this.groupId = groupId;
		this.roleId = roleId;
		this.dataId = dataId;
		this.group = group;
		this.role = role;
		this.data = data;
	}	
}


