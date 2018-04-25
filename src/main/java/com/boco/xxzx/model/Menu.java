package com.boco.xxzx.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
@Alias(value="Menu")
public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer menuId; 
	private String menuName;
	private Integer parentId;
	private String url;
	private String creater;
	private Date createTime;
	private String modifier;
	private Date modifyTime;
	private Integer flag;
    
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	public String getModifier() {
		return modifier;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
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
	public Menu() {
		super();
	}
	public Menu(Integer menuId, String menuName, Integer parentId, String url,
			String creater, Date createTime, String modifier,
			Date modifyTime, Integer flag) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.parentId = parentId;
		this.url = url;
		this.creater = creater;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.flag = flag;
	}
	
}
