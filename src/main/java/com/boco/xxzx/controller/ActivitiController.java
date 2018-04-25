package com.boco.xxzx.controller;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activiti")
public class ActivitiController {
	@Resource
	private ProcessEngine processEngine;
	/*流程定义*/
	@RequestMapping("/deploy")
	public void deploymentProcessDefinition(){
		System.out.println("流程定义");
		Deployment deployment=processEngine.getRepositoryService().createDeployment().addClasspathResource("diagrams/HelloWorld.bpmn").deploy();
		System.out.println("部署ID="+deployment.getId());
		System.out.println("部署名称="+deployment.getName());
	}
	
	/*已部署流程列表*/
	@RequestMapping("/deploylist")
	public void deployList(){
		List<ProcessDefinition> processDefinitionList=processEngine.getRepositoryService().createProcessDefinitionQuery().list();
		for (int i = 0; i < processDefinitionList.size(); i++) {
			ProcessDefinition processDefinition=processDefinitionList.get(i);
			System.out.println(processDefinition.getId());
			System.out.println(processDefinition.getName());
		}
	}
	
	/*启动一个流程实例*/
	@RequestMapping("/start")
	public void start(){
		RuntimeService runtimeService=processEngine.getRuntimeService();
		runtimeService.startProcessInstanceById("myProcess:1:2504");
	}
	
	/*所有已启动流程实例*/
	@RequestMapping("/startlist")
	public void startList(){
		List<ProcessInstance> processInstanceList=processEngine.getRuntimeService().createProcessInstanceQuery().list();
		for (int i = 0; i < processInstanceList.size(); i++) {
			ProcessInstance processInstance=processInstanceList.get(i);
			System.out.println(processInstance.getId());
			System.out.println(processInstance.getName());
		}
	}
	
	/*任务列表*/
	@RequestMapping("/tasklist")
	public void taskList(){
		List<Task> taskList=processEngine.getTaskService().createNativeTaskQuery().list();
		for (int i = 0; i < taskList.size(); i++) {
			Task task=taskList.get(i);
			System.out.println(task.getId());
			System.out.println(task.getName());
		}
	}
	
	/*完成当前任务*/
	public void completeTask(){
		processEngine.getTaskService().complete("");
	}
	
	
}
