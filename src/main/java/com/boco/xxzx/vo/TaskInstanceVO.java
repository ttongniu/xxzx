package com.boco.xxzx.vo;

import java.io.Serializable;
import java.util.Date;

import com.boco.xxzx.model.User;
/**
 * 
 * @ClassName:  TaskInstanceVO   
 * @Description:(vo视图类)   
 * @author: niutongtong  
 * @date:   2017年11月28日 上午11:05:21   
 *
 */
public class TaskInstanceVO implements Serializable{
	    
	private static final long serialVersionUID = 1L;
	/**
	   * 任务的id
	   */
    private    String   id ;
     /**
      * 流程名称
      */
    private    String   name ;
    /**
     * 流程提交时间
     */
    private    Date    startTime;
     /**
      * 申请人
      */
    private    User  proposer ;
     /**
      *  审批人
      */
    private    User  assignee; 
    /**
     * 当前在哪一步
     */
    private  String  step;
    /**
     * 当前在谁那里
     */
    private  String   stateUser;
    /**
     * 流程定义key
     */
    private  String   procDefkey;
    
    public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getStateUser() {
		return stateUser;
	}

	public void setStateUser(String stateUser) {
		this.stateUser = stateUser;
	}

	/**
     * 流程实例id
     */
    private    String  procInstId  ;
	public TaskInstanceVO(String id, String name, Date startTime, User proposer, User assignee, String procInstId) {
		super();
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.proposer = proposer;
		this.assignee = assignee;
		this.procInstId = procInstId;
	}
	
	public TaskInstanceVO() {
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public User getProposer() {
		return proposer;
	}
	public void setProposer(User proposer) {
		this.proposer = proposer;
	}
	public User getAssignee() {
		return assignee;
	}
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	public String getProcInstId() {
		return procInstId;
	}
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	@Override
	public String toString() {
		return "TaskInstanceVO [id=" + id + ", name=" + name + ", startTime=" + startTime + ", proposer=" + proposer
				+ ", assignee=" + assignee + ", procInstId=" + procInstId + "]";
	}

	public String getProcDefkey() {
		return procDefkey;
	}

	public void setProcDefkey(String procDefkey) {
		this.procDefkey = procDefkey;
	}
   
	
    
}
