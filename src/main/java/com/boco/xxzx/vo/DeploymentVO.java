package com.boco.xxzx.vo;

import java.io.Serializable;
import java.util.Date;

public class DeploymentVO implements Serializable {
	   
	private static final long serialVersionUID = 1L;
	private String id ;
       private String name;
       private Date deploymentTime;
       private String filePath;
	@Override
	public String toString() {
		return "DeploymentVO [id=" + id + ", name=" + name + ", deploymentTime=" + deploymentTime + ", filePath="
				+ filePath + "]";
	}
	public DeploymentVO(String id, String name, Date deploymentTime, String filePath) {
		super();
		this.id = id;
		this.name = name;
		this.deploymentTime = deploymentTime;
		this.filePath = filePath;
	}
	public DeploymentVO() {
		super();
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
	public Date getDeploymentTime() {
		return deploymentTime;
	}
	public void setDeploymentTime(Date deploymentTime) {
		this.deploymentTime = deploymentTime;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
       
       
}
