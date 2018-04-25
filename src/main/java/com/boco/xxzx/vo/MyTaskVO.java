package com.boco.xxzx.vo;

import java.io.Serializable;
import java.util.Date;

public class MyTaskVO implements Serializable{
	    
	private static final long serialVersionUID = 1L;
	private String id; // 任务id
	private String name; // 任务名称
	private Date createTime;  // 创建日期
	private Date endTime; // 结束日期
	private String assignee;
	private String proceKey;
	/**
	 * 部署流程名称
	 */
	private String  deploymentName;
	/**
	 * 申请人
	 */
	private String proposer;
	
	public MyTaskVO(String id, String name, Date createTime, Date endTime) {
		super();
		this.id = id;
		this.name = name;
		this.createTime = createTime;
		this.endTime = endTime;
	}
	public MyTaskVO() {
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "MyTaskVO [id=" + id + ", name=" + name + ", createTime=" + createTime + ", endTime=" + endTime + "]";
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getDeploymentName() {
		return deploymentName;
	}
	public void setDeploymentName(String deploymentName) {
		this.deploymentName = deploymentName;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public String getProceKey() {
		return proceKey;
	}
	public void setProceKey(String proceKey) {
		this.proceKey = proceKey;
	}
	
}
