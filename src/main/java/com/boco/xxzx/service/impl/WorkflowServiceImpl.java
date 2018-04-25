package com.boco.xxzx.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;
//import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.xxzx.model.LeaveBill;
import com.boco.xxzx.model.User;
import com.boco.xxzx.service.LeaveBillService;
import com.boco.xxzx.service.UserService;
import com.boco.xxzx.service.WorkflowService;
import com.boco.xxzx.utils.session.SessionContext;
import com.boco.xxzx.vo.MyTaskVO;

/**
 * 
 * @ClassName:  WorkflowServiceImpl   
 * @Description:(这里用一句话描述这个类的作用)   
 * @author: niutongtong  
 * @date:   2017年10月17日 下午7:46:03   
 *
 */
@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	// 资源服务
	private RepositoryService repositoryService;
	@Autowired
	// 运行时服务
	private RuntimeService runtimeService;
	/**任务服务*/
	@Autowired
	private TaskService taskService;
	
	@Autowired
	/**历史服务*/
	private HistoryService historyService;
	@Autowired
	private UserService   userService;
   
	@Autowired
	private LeaveBillService leaveBillService;
	public void saveNewDeploye(File file, String filename) {
		try {
			// 2：将File类型的文件转化成ZipInputStream流
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
			logger.info("file****" + file);
			logger.info("filename****" + filename);
			repositoryService.createDeployment()// 创建部署对象
					.name(filename)// 添加部署名称
					.addZipInputStream(zipInputStream)//
					.deploy();// 完成部署
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
	public List<Deployment> listDeployment() {
		List<Deployment> list = repositoryService.createDeploymentQuery()// 创建部署对象查询
				.orderByDeploymenTime().asc()//
				.list();
		return list;
	}
  
	public List<ProcessDefinition> listProcessDefinition() {
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()// 创建流程定义查询
				.orderByProcessDefinitionKey().asc()	
				.orderByProcessDefinitionVersion().asc()//
				.list();
		return list;
	}
    
	public InputStream getImageInputStream(String deploymentId, String imageName) {
		return repositoryService.getResourceAsStream(deploymentId, imageName);
	}
   
	public void deleteProcessDefinitionByDeploymentId(String deploymentId) {

		repositoryService.deleteDeployment(deploymentId, true);
	}
   
	public List<MyTaskVO> listTODO() {
		List<MyTaskVO> myTaskVOs=new ArrayList<MyTaskVO>();
		String userId=String.valueOf(SessionContext.getCurrentUser().getId()); 
		List<Task> toDoList = new ArrayList<Task>();
		toDoList = taskService.createTaskQuery()//
				.taskAssignee(userId)//
				.orderByTaskCreateTime().desc()//
				.list();
		for(Task  task:toDoList){
			MyTaskVO taskVO=new MyTaskVO();
			taskVO.setId(task.getId());
			taskVO.setName(task.getName());
			taskVO.setCreateTime(task.getCreateTime());
			taskVO.setAssignee(userService.
					getUserByUserId( Integer.valueOf( task.getAssignee()))
					.getEmployeeName());
			String   variableName="inputUser";
			String inputUser  =  (String)taskService.getVariable(task.getId(), variableName) ;
			taskVO.setProposer(userService.
					getUserByUserId( Integer.valueOf(inputUser))
					.getEmployeeName());
			String deploymentId = repositoryService.createProcessDefinitionQuery()//
			                 .processDefinitionId(task.getProcessDefinitionId())//
			                 .singleResult().getDeploymentId();
			String deploymentName =  repositoryService  
            .createDeploymentQuery()  
            .deploymentId(deploymentId).singleResult().getName();
			taskVO.setDeploymentName(deploymentName);
			taskVO.setProceKey( task.getProcessDefinitionId().substring(0, task.getProcessDefinitionId().indexOf(":"))); 
			myTaskVOs.add(taskVO);
		}
		
		return myTaskVOs;
	}
  
	public void completeTask(String taskId, Map<String, Object> variables) {
		taskService.complete(taskId, variables);
	}
  
	public List<String> findOutComeListByTaskId(String taskId) {
		// 返回存放连线名称的集合
		List<String> outCome = new ArrayList<String>();
		// 1.使用任务Id，查询任务对象
		Task task = taskService.createTaskQuery()//
				.taskId(taskId)//
				.singleResult();
		// 2.获取流程定义的id
		String processDefinitionId = task.getProcessDefinitionId();
		// 3.查询ProcessDefinitionEntiy对象
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);
		// 4.使用任务对象 获取流程实例id
		String processInstanceId = task.getProcessInstanceId();
		// 5.使用流程实例id,查询正在执行的执行对象表，返回流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
				.processInstanceId(processInstanceId)
				.singleResult();
		// 6. 获取当前活动id
		String activityId="";
		CharSequence  flag="会签";
		if(task.getName().contains(flag)){
		List<Execution> executions = runtimeService.createExecutionQuery()
				.processInstanceId(pi.getId()).list();
				// 得到正在执行的Activity的Id
				List<String> activityIds = new ArrayList<String>();
				for (Execution exe : executions) {
				List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
				activityIds.addAll(ids);
				}
				activityId=activityIds.get(0);
		}else{
			activityId = pi.getActivityId();
		}
		// 7.获取当前活动
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
		// 8.获取当前活动完成之后连线的名称
		List<PvmTransition> pvmList = activityImpl.getOutgoingTransitions();
		if (pvmList != null && pvmList.size() > 0) {
			for (PvmTransition pvm : pvmList) {
				String name = (String) pvm.getProperty("name");
				if (StringUtils.isNotBlank(name)) {
					outCome.add(name);
				} else {
					outCome.add("默认提交");
				}
			}
		}
		return outCome;
	}
  
	public Map<String, Object> findCoordingByTask(String taskId) {
		// 存放坐标
		Map<String, Object> map = new HashMap<String, Object>();
		// 使用任务ID，查询任务对象
		Task task = taskService.createTaskQuery()//
				.taskId(taskId)// 使用任务ID查询
				.singleResult();
		// 获取流程定义的ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 获取流程定义的实体对象（对应.bpmn文件中的数据）
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);
		// 流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		// 使用流程实例ID，查询正在执行的执行对象表，获取当前活动对应的流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()// 创建流程实例查询
				.processInstanceId(processInstanceId)// 使用流程实例ID查询
				.singleResult();
		// 获取当前活动的ID
		//String activityId = pi.getActivityId();
		String activityId="";
		CharSequence  flag="会签";
		if(task.getName().contains(flag)){
		List<Execution> executions = runtimeService.createExecutionQuery()
				.processInstanceId(pi.getId()).list();
				// 得到正在执行的Activity的Id
				List<String> activityIds = new ArrayList<String>();
				for (Execution exe : executions) {
				List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
				activityIds.addAll(ids);
				}
				activityId=activityIds.get(0);
		}else{
			activityId = pi.getActivityId();
		}
		// 获取当前活动对象
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);// 活动ID
		// 获取坐标
		map.put("x", activityImpl.getX());
		map.put("y", activityImpl.getY());
		map.put("width", activityImpl.getWidth());
		map.put("height", activityImpl.getHeight());
		return map;
	}
	public List<Comment> findCommentByObjId(String objId) {	
		/**1:使用历史的流程实例查询，返回历史的流程实例对象，获取流程实例ID*/
//		HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()//对应历史的流程实例表
//						.processInstanceBusinessKey(objId)//使用BusinessKey字段查询
//						.singleResult();
//		//流程实例ID
//		String processInstanceId = hpi.getId();
		/**2:使用历史的流程变量查询，返回历史的流程变量的对象，获取流程实例ID*/
		HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery()//对应历史的流程变量表
						.variableValueEquals("objId", objId)//使用流程变量的名称和流程变量的值查询
						.singleResult();
		//流程实例ID
		String processInstanceId = hvi.getProcessInstanceId();
		List<Comment> list = taskService.getProcessInstanceComments(processInstanceId);
		Collections.reverse(list); // 倒序排列 
		return list;
	}
	
	
	public List<Comment> findCommentByTaskId(String taskId) {
		List<Comment> commentList = new ArrayList<Comment>();
		Task task = taskService.createTaskQuery()//
				.taskId(taskId)//
				.singleResult();
		// 获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		// 使用流程实例ID，查询历史任务，获取历史任务对应的每个任务ID
		List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()//
				.processInstanceId(processInstanceId)//
				.orderByTaskCreateTime()//
				.asc()//
				.list();
		if (historicTaskInstances != null && historicTaskInstances.size() > 0) {
			for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
				List<Comment> list = taskService.getTaskComments(historicTaskInstance.getId());
				commentList.addAll(list);
			}

		}
		// 获取批注信息
		// commentList=taskService.getProcessInstanceComments(processInstanceId);
		return commentList;
	}
 
	public String completeTaskByoutcome(String taskId, String outcome, String message, String userId, String assignee) {
		/* 1：在完成之前，添加一个批注信息，向act_hi_comment表中添加数据，用于记录对当前申请人的一些审核信息 */
		// 根据taskId 查询任务实例 ，获取流程实例ID
		Task task = taskService.createTaskQuery()//
				.taskId(taskId)//
				.singleResult();
		String processInstanceId = task.getProcessInstanceId();
		String   variableName="inputUser";
		String inputUser  =  (String)taskService.getVariable(taskId, variableName) ;
		System.out.println("****inputUser："+inputUser);
		/*
		 * 注意：添加批注的时候，由于Activiti底层代码是使用： String userId =
		 * Authentication.getAuthenticatedUserId(); CommentEntity comment = new
		 * CommentEntity(); comment.setUserId(userId);
		 * 所有需要从Session中获取当前登录人，作为该任务的办理人（审核人），对应act_hi_comment表中的User_ID的字段，
		 * 不过不添加审核人，该字段为null
		 * 所以要求，添加配置执行使用Authentication.setAuthenticatedUserId();添加当前任务的审核人
		 */
		Authentication.setAuthenticatedUserId("【"+task.getName()+"】"+SessionContext.getCurrentUser().getEmployeeName());
		
		
		/*
		 * 3：如果连线的名称是“默认提交”，那么就不需要设置，如果不是，就需要设置流程变量 在完成任务之前，设置流程变量，按照连线的名称，去完成任务
		 * 流程变量的名称：outcome 流程变量的值：连线的名称
		 */
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("outcome", outcome);
		/**
		 4：当任务完成之后，需要指定下一个任务的办理人（使用类）-----已经开发完成
		 */
		 CharSequence flag="会签";
	     CharSequence  cs="驳回";
		if(outcome.contains(cs)&&assignee==null){
		   variables.put("inputUser",inputUser);
		   message="<font color='blue'>"+message+"</font>&nbsp;&nbsp;<font color='red'> 驳回 ;</font>" ;
		}else if(outcome.contains(flag)){
		   variables.put("assigneeList", Arrays.asList(assignee.split(","))  ); 
		   message="<font color='blue'>"+message+"</font>&nbsp;&nbsp; <b> 通过 ;</b>" ;
		}else{
		   variables.put("assignee",assignee);	
		   message="<font color='blue'>"+message+"</font>&nbsp;&nbsp; <b> 通过 ;</b>" ;
		}
		taskService.addComment(taskId, processInstanceId, message);
		// 5：使用任务ID，完成当前人的个人任务，同时流程变量
		taskService.complete(taskId, variables);
		return  processInstanceId;
	}

	public boolean isProcessEnd(String processInstanceId) {
		/*
		 * 在完成任务之后，判断流程是否结束 如果流程结束了，更新请假单表的状态从1变成2（审核中-->审核完成）
		 */
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
				.processInstanceId(processInstanceId)//
				.singleResult();
		//流程已经结束
		if(pi== null){
			return true;
		}else{
			return false;
		}
		
	}
	public List<MyTaskVO> listHaveTODO(String taskName) {
		List<MyTaskVO>  myTasks=new ArrayList<MyTaskVO>();
		List<HistoricTaskInstance> taskInstances = historyService.createHistoricTaskInstanceQuery()//
				      .taskNameLike("%"+taskName+"%")      
				      .finished()
		              .orderByTaskCreateTime()//
		              .desc()//
		              .list();//
		for(HistoricTaskInstance taskInstance:taskInstances){
			    	MyTaskVO myTaskVO=new MyTaskVO();
			    	myTaskVO.setId(taskInstance.getId());
			    	myTaskVO.setName(taskInstance.getName());
			    	myTaskVO.setCreateTime(taskInstance.getCreateTime());
			    	myTaskVO.setEndTime(taskInstance.getEndTime());
			       User user  =	 userService.getUserByUserId(Integer.valueOf(taskInstance.getAssignee()));
			    	myTaskVO.setAssignee(user.getEmployeeName());
			    	String   variableName="inputUser";
					String inputUser=(String) getVariablesByInstance(taskInstance.getProcessInstanceId())//
							                        .get(variableName);
			    	myTaskVO.setProposer(userService.
							getUserByUserId( Integer.valueOf(inputUser))
							.getEmployeeName());
					String deploymentId = repositoryService.createProcessDefinitionQuery()//
					                 .processDefinitionId(taskInstance.getProcessDefinitionId())//
					                 .singleResult().getDeploymentId();
					String deploymentName =  repositoryService  
		            .createDeploymentQuery()  
		            .deploymentId(deploymentId).singleResult().getName();
					myTaskVO.setDeploymentName(deploymentName);
			    	myTasks.add(myTaskVO);
			    }
		return myTasks;
	}
    
    public void saveStartProcess(String key, String id) {
		Map<String, Object> variables = new HashMap<String,Object>();
		variables.put("inputUser",String.valueOf(SessionContext.getCurrentUser().getId()));//表示惟一用户SessionContext.get().getName()
		/**
		 * 	(1)使用流程变量设置字符串（格式：Key.id的形式），通过设置，让启动的流程（流程实例）关联业务
   			(2)使用正在执行对象表中的一个字段BUSINESS_KEY（Activiti提供的一个字段），让启动的流程（流程实例）关联业务
		 */
		//格式：Key.id的形式（使用流程变量）
		String objId = key+"."+id;
		variables.put("objId", objId);
		//：使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
		runtimeService.startProcessInstanceByKey(key,objId,variables);
		
	}
    
    public Task saveStartProcessReturnTask(String key, String id) {
		Map<String, Object> variables = new HashMap<String,Object>();
	  String inputUser = String.valueOf(SessionContext.getCurrentUser().getId());
	  variables.put("inputUser",inputUser);//表示惟一用户
		/**
		 * 	(1)使用流程变量设置字符串（格式：Key.id的形式），通过设置，让启动的流程（流程实例）关联业务
   			(2)使用正在执行对象表中的一个字段BUSINESS_KEY（Activiti提供的一个字段），让启动的流程（流程实例）关联业务
		 */
		//格式：Key.id的形式（使用流程变量）
		String objId = key+"."+id;
		variables.put("objId", objId);
		//：使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
		runtimeService.startProcessInstanceByKey(key,objId,variables);
        List <Task>	tasklist =	taskService.createTaskQuery()//
        		          .taskDefinitionKey("usertask1")
        		          .taskAssignee(inputUser)
        		          .orderByTaskCreateTime()
        		          .desc()
        		          .list();
		return tasklist.get(0);
	}

	public ProcessDefinition findProcessDefinitionByTaskId(String taskId) {
		 /**获取任务对象*/
		 Task task  =  taskService.createTaskQuery()//
		                .taskId(taskId)//
		                .singleResult();
		   /**
		    * 获取流程定义Id
		    */
		  String processDefinitionId  =   task.getProcessDefinitionId();
		  /**
		   * 获取流程定义对象
		   */
		  ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()//
		                           .processDefinitionId(processDefinitionId)//
		                           .singleResult();
		return processDefinition;
	}

	public LeaveBill findLeaveBillByTaskId(String taskId) {
		        //1：使用任务ID，查询任务对象Task
				Task task = taskService.createTaskQuery()//
								.taskId(taskId)//使用任务ID查询
								.singleResult();
				//2：使用任务对象Task获取流程实例ID
				String processInstanceId = task.getProcessInstanceId();
				//3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
				ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
								.processInstanceId(processInstanceId)//使用流程实例ID查询
								.singleResult();
				//4：使用流程实例对象获取BUSINESS_KEY
				String buniness_key = pi.getBusinessKey();
				//5：获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
				String id = "";
				if(StringUtils.isNotBlank(buniness_key)){
					//截取字符串，取buniness_key小数点的第2个值
					id = buniness_key.split("\\.")[1];
				}
				//查询请假单对象
				//使用hql语句：from LeaveBill o where o.id=1
				LeaveBill leaveBill = leaveBillService.getLeaveBillById(id);
				return leaveBill;
		
	}
	public LeaveBill findLeaveBillByHisTaskId(String taskId) {
        //1：使用任务ID，查询任务对象Task
		HistoricTaskInstance taskInstance  =   historyService.createHistoricTaskInstanceQuery()//
                .taskId(taskId)//
                .singleResult();
		//2：使用任务对象Task获取流程实例ID
		String processInstanceId = taskInstance.getProcessInstanceId();
		//3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		HistoricProcessInstance pi = historyService.createHistoricProcessInstanceQuery()//
						.processInstanceId(processInstanceId)
						.singleResult();
		//4：使用流程实例对象获取BUSINESS_KEY
		String buniness_key = pi.getBusinessKey();
		//5：获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
		String id = "";
		if(StringUtils.isNotBlank(buniness_key)){
			//截取字符串，取buniness_key小数点的第2个值
			id = buniness_key.split("\\.")[1];
		}
		//查询请假单对象
		//使用hql语句：from LeaveBill o where o.id=1
		LeaveBill leaveBill = leaveBillService.getLeaveBillById(id);
		return leaveBill;

}
	public List<Comment> findCommentByHisTaskId(String taskId) {
		List<Comment> commentList = new ArrayList<Comment>();
		HistoricTaskInstance taskInstance  =   historyService.createHistoricTaskInstanceQuery()//
		                        .taskId(taskId)//
		                        .singleResult();
       // 获取流程实例ID
		String processInstanceId = taskInstance.getProcessInstanceId();
		// 使用流程实例ID，查询历史任务，获取历史任务对应的每个任务ID
		List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()//
				.processInstanceId(processInstanceId)//
				.orderByTaskCreateTime()//
				.asc()//
				.list();
		if (historicTaskInstances != null && historicTaskInstances.size() > 0) {
			for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
				List<Comment> list = taskService.getTaskComments(historicTaskInstance.getId());
				commentList.addAll(list);
			}
		}
		// 获取批注信息
		// commentList=taskService.getProcessInstanceComments(processInstanceId);
		return commentList;
	}

	public List<HistoricActivityInstance> listAction(String taskId) {
		HistoricTaskInstance hti=historyService.createHistoricTaskInstanceQuery()//
												.taskId(taskId)//
												.singleResult();
		String processInstanceId=hti.getProcessInstanceId(); // 获取流程实例id
		List<HistoricActivityInstance> haiList=historyService.createHistoricActivityInstanceQuery()//
				                                             .processInstanceId(processInstanceId)
				                                             .orderByHistoricActivityInstanceStartTime()
				                                             .asc()
				                                             .list();
		return haiList;
	}

	public List<MyTaskVO> listFinishedHaveTODO(String assignee, String taskName) {
		List<MyTaskVO>  myTasks=new ArrayList<MyTaskVO>();
		List<HistoricTaskInstance> taskInstances = historyService.createHistoricTaskInstanceQuery()//
	              .taskAssignee(assignee)//
	              .taskNameLike("%"+taskName+"%")
	              .finished()
	              .orderByTaskCreateTime()//
	              .desc()//
	              .list();// 
		for(HistoricTaskInstance taskInstance:taskInstances){
			 String processInstanceId=taskInstance.getProcessInstanceId(); // 获取流程实例id
			    ProcessInstance pi=runtimeService.createProcessInstanceQuery() // 根据流程实例id获取流程实例
			    		.processInstanceId(processInstanceId)
			    		.singleResult();
			    if(pi==null){
			    	MyTaskVO myTaskVO=new MyTaskVO();
			    	myTaskVO.setId(taskInstance.getId());
			    	myTaskVO.setName(taskInstance.getName());
			    	myTaskVO.setCreateTime(taskInstance.getCreateTime());
			    	myTaskVO.setEndTime(taskInstance.getEndTime());
				    User user  =	 userService.getUserByUserId(Integer.valueOf(taskInstance.getAssignee()));
				    myTaskVO.setAssignee(user.getEmployeeName());
				    String   variableName="inputUser";
					String inputUser=(String) getVariablesByInstance(taskInstance.getProcessInstanceId())//
							                        .get(variableName);
			    	myTaskVO.setProposer(userService.
							getUserByUserId( Integer.valueOf(inputUser))
							.getEmployeeName());
					String deploymentId = repositoryService.createProcessDefinitionQuery()//
					                 .processDefinitionId(taskInstance.getProcessDefinitionId())//
					                 .singleResult().getDeploymentId();
					String deploymentName =  repositoryService  
		            .createDeploymentQuery()  
		            .deploymentId(deploymentId).singleResult().getName();
					myTaskVO.setDeploymentName(deploymentName);
			    	myTasks.add(myTaskVO);
			    }
		}
		
		return myTasks;
	}	
	
	public List<MyTaskVO> listunFinishedHaveTODO(String assignee, String taskName) {
		List<MyTaskVO>  myTasks=new ArrayList<MyTaskVO>();
		List<HistoricTaskInstance> taskInstances = historyService.createHistoricTaskInstanceQuery()//
	              .taskAssignee(assignee)//
	              .taskNameLike("%"+taskName+"%")
	              .finished()
	              .orderByTaskCreateTime()//
	              .desc()//
	              .list();// 
		for(HistoricTaskInstance taskInstance:taskInstances){
			 String processInstanceId=taskInstance.getProcessInstanceId(); // 获取流程实例id
			    ProcessInstance pi=runtimeService.createProcessInstanceQuery() // 根据流程实例id获取流程实例
			    		.processInstanceId(processInstanceId)
			    		.singleResult();
			    if(pi!=null){
			    	MyTaskVO myTaskVO=new MyTaskVO();
			    	myTaskVO.setId(taskInstance.getId());
			    	myTaskVO.setName(taskInstance.getName());
			    	myTaskVO.setCreateTime(taskInstance.getCreateTime());
			    	myTaskVO.setEndTime(taskInstance.getEndTime());
			       User user  =	 userService.getUserByUserId(Integer.valueOf(taskInstance.getAssignee()));
			    	myTaskVO.setAssignee(user.getEmployeeName());
			    	String   variableName="inputUser";
					String inputUser=(String) getVariablesByInstance(taskInstance.getProcessInstanceId())//
							                        .get(variableName);
			    	myTaskVO.setProposer(userService.
							getUserByUserId( Integer.valueOf(inputUser))
							.getEmployeeName());
					String deploymentId = repositoryService.createProcessDefinitionQuery()//
					                 .processDefinitionId(taskInstance.getProcessDefinitionId())//
					                 .singleResult().getDeploymentId();
					String deploymentName =  repositoryService  
		            .createDeploymentQuery()  
		            .deploymentId(deploymentId).singleResult().getName();
					myTaskVO.setDeploymentName(deploymentName);
			    	myTasks.add(myTaskVO);
			    }
		}
		
		return myTasks;
	}	
	
	public Map<String,Object> getVariablesByInstance(String instanceId){

	       Map<String,Object> map = new HashMap<String,Object>();

	       List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().processInstanceId(instanceId).list();

	       for (HistoricVariableInstance hi : list) {

	           map.put(hi.getVariableName(), hi.getValue());

	       }

	       return map;

	    }

	public List<Deployment> findDeploymentList() {
		List<Deployment> list = repositoryService.createDeploymentQuery()//创建部署对象查询
				.orderByDeploymenTime().asc()//
				.list();
		return list;
	}
}
