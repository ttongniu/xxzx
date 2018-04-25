package com.boco.xxzx.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value="RoleDict")
public class RoleDict implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer roleId;
	private Integer dataId;
	private Integer menuId;
	private String creater;
	private Date createTime;
	private String modifier;
	private Date modifyTime;
	private Dictionary role;
	private Menu menu;
	private Dictionary data;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public Dictionary getRole() {
		return role;
	}
	public void setRole(Dictionary role) {
		this.role = role;
	}
	public Dictionary getData() {
		return data;
	}
	public void setData(Dictionary data) {
		this.data = data;
	}
	public RoleDict() {
		super();
	}
	public RoleDict(Integer roleId, Integer dataId, Integer menuId,
			String creater, Date createTime, String modifier, Date modifyTime,
			Dictionary role, Menu menu, Dictionary data) {
		super();
		this.roleId = roleId;
		this.dataId = dataId;
		this.menuId = menuId;
		this.creater = creater;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.role = role;
		this.menu = menu;
		this.data = data;
	}
	

}
