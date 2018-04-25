package com.boco.xxzx.model;
/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年12月6日 上午11:24:10 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class GroupMenu {
	private Integer gmId;
	private Integer groupId;
	private Integer menuId;
	private Dictionary group;
	private Menu menu;
	public Integer getGmId() {
		return gmId;
	}
	public void setGmId(Integer gmId) {
		this.gmId = gmId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	public Dictionary getGroup() {
		return group;
	}
	public void setGroup(Dictionary group) {
		this.group = group;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public GroupMenu() {
		super();
	}
	public GroupMenu(Integer gmId, Integer groupId, Integer menuId,
			Dictionary group, Menu menu) {
		super();
		this.gmId = gmId;
		this.groupId = groupId;
		this.menuId = menuId;
		this.group = group;
		this.menu = menu;
	}
	
}


