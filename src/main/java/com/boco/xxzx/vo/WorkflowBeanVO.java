package com.boco.xxzx.vo;


import org.springframework.web.multipart.MultipartFile;
/**
 * 
 * @ClassName:  WorkflowBeanVO   
 * @Description:TODO(流程信息VO)   
 * @author: niutongtong  
 * @date:   2017年9月28日 下午5:38:11   
 *
 */
public class WorkflowBeanVO {
	/**
	 * 流程定义部署文件
	 */
	private MultipartFile file;	
	/**
	 * 流程定义名称
	 */
	private String filename;    
	/**
	 * 申请单ID
	 */
	private Long id;            
	/**
	 * 部署对象ID
	 */
	private String deploymentId;
	/**
	 * 资源文件名称
	 */
	private String imageName;	
	/**
	 * 任务ID
	 */
	private String taskId;		
	/**
	 * 连线名称
	 */
	private String outcome;		
	/**
	 * 备注
	 */
	private String comment;		
	
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "WorkflowBeanVO [file=" + file + ", filename=" + filename + ", id=" + id + ", deploymentId="
				+ deploymentId + ", imageName=" + imageName + ", taskId=" + taskId + ", outcome=" + outcome
				+ ", comment=" + comment + "]";
	}
	
}
