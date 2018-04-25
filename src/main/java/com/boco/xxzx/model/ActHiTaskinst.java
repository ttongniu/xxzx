package com.boco.xxzx.model;

import java.io.Serializable;
import java.util.Date;

public class ActHiTaskinst implements Serializable{
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
	        * 申请人  、 审批人
	        */
	      private    String  assignee ;
	      /**
	       * 流程实例id
	       */
	      private    String  procInstId  ;
	      /**
	       * 流程定义id
	       */
	      private    String   procDefId;
	      
		public ActHiTaskinst() {
			super();
		}
	
		public ActHiTaskinst(String id, String name, Date startTime, String assignee, String procInstId) {
			super();
			this.id = id;
			this.name = name;
			this.startTime = startTime;
			this.assignee = assignee;
			this.procInstId = procInstId;
		}
	
		@Override
		public String toString() {
			return "ActHiTaskinst [id=" + id + ", name=" + name + ", startTime=" + startTime + ", assignee=" + assignee
					+ ", procInstId=" + procInstId + "]";
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
	
		public String getAssignee() {
			return assignee;
		}
	
		public void setAssignee(String assignee) {
			this.assignee = assignee;
		}
	
		public String getProcInstId() {
			return procInstId;
		}
	
		public void setProcInstId(String procInstId) {
			this.procInstId = procInstId;
		}

		public String getProcDefId() {
			return procDefId;
		}

		public void setProcDefId(String procDefId) {
			this.procDefId = procDefId;
		}
	      
      
}
