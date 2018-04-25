package com.boco.xxzx.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName:  ActReDeployment   
 * @Description:()   
 * @author: niutongtong  
 * @date:   2017年12月4日 下午3:30:16   
 *
 */
@Deprecated
public class ActReDeployment implements Serializable {
	private static final long serialVersionUID = 1L;
	private   String id;
      private   String name;
      private   String  category;
      private   String tenantId;
      private   Date   deployTime;
      
      
	public ActReDeployment() {
		super();
	}
	public ActReDeployment(String id, String name, String category, String tenantId, Date deployTime) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.tenantId = tenantId;
		this.deployTime = deployTime;
	}
	
	
	@Override
	public String toString() {
		return "ActReDeployment [id=" + id + ", name=" + name + ", category=" + category + ", tenantId=" + tenantId
				+ ", deployTime=" + deployTime + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public Date getDeployTime() {
		return deployTime;
	}
	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}
      
}
