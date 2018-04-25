package com.boco.xxzx.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.boco.xxzx.model.ActHiTaskinst;
import com.boco.xxzx.model.ActReDeployment;
import com.boco.xxzx.model.BoFile;
import com.boco.xxzx.model.LeaveBill;
import com.boco.xxzx.model.User;
import com.boco.xxzx.service.ActHiTaskinstService;
import com.boco.xxzx.service.ActReDeploymentService;
import com.boco.xxzx.service.BoFileService;
import com.boco.xxzx.service.LeaveBillService;
import com.boco.xxzx.service.LogService;
import com.boco.xxzx.service.UserService;
import com.boco.xxzx.service.WorkflowSecurityService;
import com.boco.xxzx.service.WorkflowService;
import com.boco.xxzx.utils.constant.Constant;
import com.boco.xxzx.utils.mail.SimpleMailSender;
import com.boco.xxzx.utils.session.SessionContext;
import com.boco.xxzx.utils.util.DateUtils;
import com.boco.xxzx.vo.DeploymentVO;
import com.boco.xxzx.vo.MyTaskVO;
import com.boco.xxzx.vo.TaskInstanceVO;
import com.boco.xxzx.vo.WorkflowBeanVO;
/**
 * 
 * @ClassName:  WorkflowController   
 * @Description:TODO(流程功能controller)   
 * @author: niutongtong  
 * @date:   2017年9月26日 下午7:46:45   
 *
 */

@Controller
@RequestMapping("/wf")
public class WorkflowController {
	Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private WorkflowSecurityService workflowSecurityService;
	@Autowired
	private LeaveBillService leaveBillService;	
	@Autowired
	private BoFileService boFileService;
	
	@Autowired
	// 资源服务
	private RepositoryService repositoryService;
	@Autowired
	// 运行时服务
	private RuntimeService runtimeService;
	@Autowired
	/**历史服务*/
	private HistoryService historyService;
	/***/
	@Autowired
	private UserService userService;
	@Autowired
	private LogService logService;
	
	@Autowired
	private ActHiTaskinstService  actHiTaskinstService;
	
	@Autowired
	private ActReDeploymentService  actReDeploymentService;
	
	/**
	 * 
	 * @Title: showDeploy   
	 * @Description: TODO(流程部署列表信息)   
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/showDeploy")
	public String showDeploy(Model model) {
		List<DeploymentVO> depListVo=new ArrayList<DeploymentVO>();
		// 1:查询部署对象信息，对应表（act_re_deployment）
		List<Deployment> depList = workflowService.listDeployment();
		if(depList!=null&depList.size()>0){
			for(Deployment dep:depList){
				DeploymentVO vo=new DeploymentVO();
				vo.setId(dep.getId());
				vo.setName(dep.getName());
				vo.setDeploymentTime(dep.getDeploymentTime());
				
				List<BoFile> boFileList = boFileService.listFilesByCode(dep.getId());
				if(boFileList!=null&&boFileList.size()>0){
			      vo.setFilePath(boFileList.get(0).getFilePath());
				}
				depListVo.add(vo);
			}	
		}
		// 2:查询流程定义的信息，对应表（act_re_procdef）
		List<ProcessDefinition> pdList = workflowService.listProcessDefinition();
		// 放置到上下文对象中
		model.addAttribute("deploymentList", depListVo);
		model.addAttribute("processDefinitionList", pdList);
		return "wf/activiti/showDeploy";
	}
	/**
	 * 
	 * @Title: saveDeploy   
	 * @Description: TODO(部署新流程)   
	 * @param: @param workflowBeanVO
	 * @param: @param request
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/saveDeploy")
	public String saveDeploy(WorkflowBeanVO workflowBeanVO, HttpServletRequest request) {
		// 获取页面传递的值
		// 1：获取页面上传递的zip格式的文件，格式是File类型
		MultipartFile file = workflowBeanVO.getFile();
		if (!file.isEmpty()) {
			String filePath = "";
			// 文件保存路径
			filePath = request.getSession().getServletContext().getRealPath("//") + "/fileUpload/"
					+ DateUtils.getCurSysDateStr() + "-" + file.getOriginalFilename();
			// 文件名称
			String fileName = file.getOriginalFilename();
			// 转存文件
			try {
				file.transferTo(new File(filePath));
			} catch (IllegalStateException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

			// 流程部署名称
			String processName = workflowBeanVO.getFilename();
			// 完成部署
			workflowService.saveNewDeploye(new File(filePath), processName);
			logService.addLog("部署流程完成！！ 流程名称为：" + processName);
			BoFile fileInfo = new BoFile();
			List<Deployment> deploymentList = new ArrayList<Deployment>();
			deploymentList = workflowService.findDeploymentList();
			if (deploymentList != null && deploymentList.size() > 0) {
				Deployment deployment = deploymentList.get(deploymentList.size() - 1);
				fileInfo.setCode(deployment.getId());
				fileInfo.setFilePath(filePath);
				fileInfo.setFileName(fileName);
				fileInfo.setCreateMan(SessionContext.getCurrentUser());
			}
			// 保存文件信息
			boFileService.saveFile(fileInfo);
		}
		return "redirect:/wf/showDeploy.do";
	}
	
	 @RequestMapping("/download")    
	    public ResponseEntity<byte[]> download(HttpServletRequest request,String filePath) throws IOException {    
	        File file=new File(filePath);  
	        HttpHeaders headers = new HttpHeaders();  
	        String agent = request.getHeader("USER-AGENT");  
	        String fileName=file.getName();//filePath.substring(filePath.lastIndexOf("/")+1);   
	        if (null != agent && -1 != agent.indexOf("MSIE") || null != agent  
	                   && -1 != agent.indexOf("Trident")) {// ie  
	        	   String name = java.net.URLEncoder.encode(fileName, "UTF8");  
	        	   fileName=name;
	        }else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等  
	        	 fileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
	        }
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
	                                          headers, HttpStatus.OK);    
	    }    
	
	
    /**
     * 
     * @Title: delDeployment   
     * @Description: TODO(删除流程对象)   
     * @param: @param workflowBeanVO
     * @param: @return      
     * @return: String      
     * @throws
     */
	@RequestMapping("/delDeployment")
	public String delDeployment(WorkflowBeanVO workflowBeanVO) {
		// 1：获取部署对象ID
		String deploymentId = workflowBeanVO.getDeploymentId();
		// 2：使用部署对象ID，删除流程定义
		workflowService.deleteProcessDefinitionByDeploymentId(deploymentId);
		return "redirect:/wf/showDeploy.do";
	}
	/**
	 * 
	 * @Title: viewImage   
	 * @Description: TODO(查看流程视图)   
	 * @param: @param workflowBeanVO
	 * @param: @param response
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/viewImage")
	public String viewImage(WorkflowBeanVO workflowBeanVO, HttpServletResponse response) throws Exception {
		// 1：获取页面传递的部署对象ID和资源图片名称
		// 部署对象ID
		String deploymentId = workflowBeanVO.getDeploymentId();
		// 资源图片名称
		String imageName = workflowBeanVO.getImageName();
		// 2：获取资源文件表（act_ge_bytearray）中资源图片输入流InputStream
		InputStream in = workflowService.getImageInputStream(deploymentId, imageName);
		// 3：从response对象获取输出流
		OutputStream out = response.getOutputStream();
		// 4：将输入流中的数据读取出来，写到输出流中
		for (int b = -1; (b = in.read()) != -1;) {
			out.write(b);
		}
		out.close();
		in.close();
		// 将图写到页面上，用输出流写
		return null;
	}
	
	/**
	 * 
	 * @Title: viewImage   
	 * @Description: TODO(查看流程视图)   
	 * @param: @param workflowBeanVO
	 * @param: @param response
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/viewImageHis")
	public String viewImageHis(String taskId, HttpServletResponse response) throws Exception {
		 HistoricTaskInstance hti=historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		String processDefinitionId=hti.getProcessDefinitionId(); // 获取流程定义id
		ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery() // 创建流程定义查询
				.processDefinitionId(processDefinitionId) // 根据流程定义id查询
				.singleResult(); 
		// 部署对象ID
		String deploymentId = processDefinition.getDeploymentId();
		// 资源图片名称
		String imageName = processDefinition.getDiagramResourceName();
		// 2：获取资源文件表（act_ge_bytearray）中资源图片输入流InputStream
		InputStream in = workflowService.getImageInputStream(deploymentId, imageName);
		// 3：从response对象获取输出流
		OutputStream out = response.getOutputStream();
		// 4：将输入流中的数据读取出来，写到输出流中
		for (int b = -1; (b = in.read()) != -1;) {
			out.write(b);
		}
		out.close();
		in.close();
		// 将图写到页面上，用输出流写
		return null;
	}
	/**
	 * 
	 * <p>Title: listTODO</p>   
	 * <p>Description: TODO(查询个人待办)</p>  
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/listTODO")
	public String listTODO(Model model){
		List<MyTaskVO>  tasks =	workflowService.listTODO();
		model.addAttribute("tasks", tasks);
		return "wf/activiti/showTaskList" ;
	}
	/**
	 * 
	 * <p>Title: listHaveTODO</p>   
	 * <p>Description: TODO(查询个人已办)</p>  
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/listHaveTODO")
	public String listHaveTODO( String taskName  ,Model model){
		if(taskName==null){
			taskName="";
		}
		List<MyTaskVO>  taskInstances= workflowService.listHaveTODO(taskName);
		model.addAttribute("taskInstances", taskInstances);
		return "wf/activiti/showHisTaskList";
	}
	
	/**
	 * 
	 * <p>Title: listHaveTODO</p>   
	 * <p>Description: TODO(查询个人已办--归档)</p>  
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/listFinishedHaveTODO")
	public String listFinishedHaveTODO(String id,String sdate, String edate,Model model){
		model.addAttribute("id", id);
		model.addAttribute("sdate", sdate);
		model.addAttribute("edate", edate);
		String assignee=String.valueOf(SessionContext.getCurrentUser().getId()) ;
		/*List<MyTaskVO>  taskInstances= workflowService.listFinishedHaveTODO(assignee,taskName);*/
		List<ActReDeployment>  actReDeployments  = actReDeploymentService.listActReDeployment();
		model.addAttribute("actReDeployments", actReDeployments);
		List<TaskInstanceVO>  taskInstances=new ArrayList<TaskInstanceVO>();
		/**
		 * 查询个人已办归档--流转中的流程实例
		 */
		List<String>   procInstIds  =  actHiTaskinstService.listProcInstIdFinished(assignee,id,sdate,edate);
		for( String  procInstId:procInstIds){
			TaskInstanceVO instanceVO=new TaskInstanceVO();
			/**
			 * 查询申请人申请时间 流程名称
			 */
			ActHiTaskinst actHiTaskinst = actHiTaskinstService.getTaskinst(procInstId);
			/**
			 * 查询完成的任务
			 */
			List<ActHiTaskinst>  actHiTaskinsts =  actHiTaskinstService.listTaskinst(procInstId, assignee);
			
			if(actHiTaskinst!=null&&actHiTaskinsts!=null&&actHiTaskinsts.size()>0){
				instanceVO.setId(actHiTaskinsts.get(0).getId());
			//	instanceVO.setAssignee(userService.getUserByUserId(Integer.valueOf(actHiTaskinsts.get(0).getAssignee())));
				instanceVO.setName(actHiTaskinst.getName());
				instanceVO.setProposer(userService.getUserByUserId(Integer.valueOf(actHiTaskinst.getAssignee())));
				instanceVO.setStartTime(actHiTaskinst.getStartTime());
				instanceVO.setProcDefkey(actHiTaskinst.getProcDefId().substring(0, actHiTaskinst.getProcDefId().indexOf(":")));
			}
			taskInstances.add(instanceVO);
		}
		model.addAttribute("taskInstances", taskInstances);
		//logService.addLog("根据流程任务名称："+name+"模糊查询已办流转");
		return "wf/activiti/showFinishedHisTaskList";
	}	
	/**
	 * 
	 * <p>Title: listHaveTODO</p>   
	 * <p>Description: TODO(查询个人已办--流转中)</p>  
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/listunFinishedHaveTODO")
	public String listunFinishedHaveTODO(String id,String sdate, String edate,Model model){
		model.addAttribute("id", id);
		model.addAttribute("sdate", sdate);
		model.addAttribute("edate", edate);
		String assignee=String.valueOf(SessionContext.getCurrentUser().getId()) ;
		/*List<MyTaskVO>  taskInstances= workflowService.listunFinishedHaveTODO(assignee,taskName);
		model.addAttribute("taskInstances", taskInstances);*/
		List<ActReDeployment>  actReDeployments  = actReDeploymentService.listActReDeployment();
		model.addAttribute("actReDeployments", actReDeployments);
		List<TaskInstanceVO>  taskInstances=new ArrayList<TaskInstanceVO>();
		/**
		 * 查询个人已办--流转中的流程实例
		 */
		List<String>   procInstIds  =  actHiTaskinstService.listProcInstIdUnFinished(assignee,id,sdate,edate);
		
		for( String  procInstId:procInstIds){
			TaskInstanceVO instanceVO=new TaskInstanceVO();
			/**
			 * 查询申请人申请时间 流程名称
			 */
			ActHiTaskinst actHiTaskinst = actHiTaskinstService.getTaskinst(procInstId);
			/**
			 * 查询完成的任务
			 */
			List<ActHiTaskinst>  actHiTaskinsts =  actHiTaskinstService.listTaskinst(procInstId, assignee);
			/**
			 * 查任务当前状态
			 * 
			 */
			ActHiTaskinst actHiTaskinstState =    actHiTaskinstService.getTaskinstState(procInstId);
			
			if(actHiTaskinst!=null&&actHiTaskinsts!=null&&actHiTaskinsts.size()>0){
				instanceVO.setId(actHiTaskinsts.get(0).getId());
			//	instanceVO.setAssignee(userService.getUserByUserId(Integer.valueOf(actHiTaskinsts.get(0).getAssignee())));
				instanceVO.setName(actHiTaskinst.getName());
				instanceVO.setProposer(userService.getUserByUserId(Integer.valueOf(actHiTaskinst.getAssignee())));
				instanceVO.setStartTime(actHiTaskinst.getStartTime());
				instanceVO.setStep(actHiTaskinstState.getName());
				instanceVO.setStateUser(actHiTaskinstState.getAssignee());
				instanceVO.setProcDefkey(actHiTaskinst.getProcDefId().substring(0, actHiTaskinst.getProcDefId().indexOf(":")));
			}
			taskInstances.add(instanceVO);
		}
		
		model.addAttribute("taskInstances", taskInstances);
		//logService.addLog("根据流程任务名称："+name+"模糊查询已办流转");
		return "wf/activiti/showunFinishedHisTaskList";
	}	
	/**
	 * 
	 * <p>Title: toLeaveBillForm</p>   
	 * <p>Description: TODO(to任务班里表单页面)</p>  
	 * @param: @param taskId
	 * @param: @param outCome
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/toLeaveBillForm")
	public String   toLeaveBillForm (String taskId,String taskName,String outCome, Model model){	
		logger.info("**********"+outCome);
		model.addAttribute("taskId", taskId);
		model.addAttribute("taskName", taskName);
		//是否只读
		String  flag="";
		if(!taskName.contains("驳回重提")){
			flag="readonly";
		}
		model.addAttribute("flag", flag);
		LeaveBill leaveBill = workflowService.findLeaveBillByTaskId(taskId);
		model.addAttribute("leaveBill", leaveBill);
	    List<String> outComeList=  workflowService.findOutComeListByTaskId(taskId);
		model.addAttribute("outComeList", outComeList);
		List<Comment>  commentList = workflowService.findCommentByTaskId(taskId);
		model.addAttribute("commentList", commentList);
		if(outCome==null||"".equals(outCome)){
			List<User> userList =   workflowSecurityService.listUserInShip(outComeList.get(0));
			model.addAttribute("userList", userList);
			model.addAttribute("outCome", outCome);
		}else{
			List<User> userList =   workflowSecurityService.listUserInShip(outCome);
			model.addAttribute("userList", userList);
			model.addAttribute("outCome", outCome);
		}
		
		return "leaveBill/leaveBillForm";
	}	
	/**
	 * 
	 * <p>Title: completeTask</p>   
	 * <p>Description: TODO(完成任务)</p>  
	 * @param: @param id
	 * @param: @param taskId
	 * @param: @param outCome
	 * @param: @param comment
	 * @param: @param s2
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/completeTask")
	public String   completeTask (LeaveBill leaveBill,String taskId,String taskName, String outCome, String comment,String[] s2, Model model){
		//logger.info("****************s2:"+s2.length);
		User currUser =    SessionContext.getCurrentUser();
		String userId=String.valueOf(currUser.getId());//当前谁办理
		String assignee = null;
		boolean flag=false; //判断是否驳回的标记
		if(s2==null){
			 assignee=null;
		}else{
			 assignee=String.valueOf(StringUtils.join(s2, ","));//制定下一步办理人
			logger.info("************assignee"+StringUtils.join(s2, ","));
		}
		if(outCome.contains("驳回")){
			flag=true;
		}
		//表单更新
		if(taskName.contains("驳回重提")){
			leaveBillService.updateLeaveBill(leaveBill);
			logService.addLog("驳回重提表单更新表单号为"+leaveBill.getId());
		}
		leaveBill = leaveBillService.getLeaveBillById(String.valueOf(leaveBill.getId()));
	    String processInstanceId = workflowService.completeTaskByoutcome(taskId, outCome, comment, userId, assignee);
		if(workflowService.isProcessEnd(processInstanceId)){
			leaveBillService.updateLeaveBillState(String.valueOf(leaveBill.getId()), Constant.FormKey.PROCESS_FILE);
			/**发送归档通知邮件*/
			SimpleMailSender.MailSendPigeonholeAction(String.valueOf(leaveBill.getId()), "请假单流程", "新工作平台", leaveBill.getUser().getEmail());
			logger.info("***********任务完成 任务号为："+taskId+"流程归档表单状态流转中-----》归档");
			logService.addLog("请假单号："+leaveBill.getId()+"，任务号："+taskId+"流转完成！！流程归档结束！！");
		}else{
			if(flag){
				/**发送驳回通知邮件*/
				SimpleMailSender.MailSendRejectAssignmentHandler(String.valueOf(leaveBill.getId()), "新工作平台", leaveBill.getUser().getEmail());    
				logService.addLog("请假单号："+leaveBill.getId()+"，任务号："+taskId+"驳回流转完成！！流转至下一步：【"+outCome+"】");
				logger.info("***********任务驳回完成 任务号为："+taskId);
			}else{
			User user = userService.getUserByUserId(Integer.valueOf(assignee));
			/**发送通知邮件*/
			SimpleMailSender.MailSendCountersignAction(String.valueOf(leaveBill.getId()), "新工作平台", user.getEmail());
			logService.addLog("请假单号："+leaveBill.getId()+"，任务号："+taskId+"流转完成！！流转至下一步：【"+outCome+"】");
			logger.info("***********任务完成 任务号为："+taskId);
			}
		}
		return "redirect:/wf/listTODO.do";
	}	
	
	/**
	 * 
	 * <p>Title: viewCurrentImage</p>   
	 * <p>Description: TODO(当前活动的位置参数)</p>  
	 * @param: @param taskId
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/viewCurrentImage")
    public String viewCurrentImage(String taskId ,Model model){
	   ProcessDefinition  processDefinition = workflowService.findProcessDefinitionByTaskId(taskId);
	   model.addAttribute("deploymentId", processDefinition.getDeploymentId());
	   model.addAttribute("imageName", processDefinition.getDiagramResourceName());
	   Map<String, Object>  map = new HashMap<String, Object>(16); 
	   map = workflowService.findCoordingByTask(taskId);
	   logger.info("*******Map:"+JSON.toJSONString(map));
	   model.addAttribute("x", map.get("x"));
	   model.addAttribute("y", map.get("y"));
	   model.addAttribute("width", map.get("width"));
	   model.addAttribute("height", map.get("height"));
	   return "wf/activiti/image";
	}	
	

	@RequestMapping("/viewHisCurrentImage")
    public String viewHisCurrentImage(String taskId ,Model model){
	   HistoricTaskInstance hti=historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		String processDefinitionId=hti.getProcessDefinitionId(); // 获取流程定义id
		ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery() // 创建流程定义查询
				.processDefinitionId(processDefinitionId) // 根据流程定义id查询
				.singleResult(); 
		model.addAttribute("deploymentId",processDefinition.getDeploymentId()); // 部署id
		model.addAttribute("imageName", processDefinition.getDiagramResourceName()); // 图片资源文件名称
		
	    ProcessDefinitionEntity	processDefinitionEntity=(ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId); 
	    String processInstanceId=hti.getProcessInstanceId(); // 获取流程实例id
	    ProcessInstance pi=runtimeService.createProcessInstanceQuery() // 根据流程实例id获取流程实例
	    		.processInstanceId(processInstanceId)
	    		.singleResult();
	    ActivityImpl activityImpl=processDefinitionEntity.findActivity(pi.getActivityId()); // 根据活动id获取活动实例
	    model.addAttribute("x", activityImpl.getX()); // x坐标
	    model.addAttribute("y", activityImpl.getY()); // y坐标
	    model.addAttribute("width", activityImpl.getWidth()); // 宽度
	    model.addAttribute("height", activityImpl.getHeight()); // 高度
	   
	   return "wf/activiti/image";
	}	
	
	/**
	 * 
	 * <p>Title: listAction</p>   
	 * <p>Description: (流程执行过程)</p>  
	 * @param: @param taskId
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/listAction")
	public String listAction(String taskId,Model model){
		List<HistoricActivityInstance> activityInstances = workflowService.listAction(taskId);
		model.addAttribute("activityInstances", activityInstances);
		return "wf/activiti/showHisAction";
	}
}
