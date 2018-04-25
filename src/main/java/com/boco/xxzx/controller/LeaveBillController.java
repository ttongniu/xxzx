package com.boco.xxzx.controller;

import java.util.List;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;
import com.boco.xxzx.model.LeaveBill;
import com.boco.xxzx.model.User;
import com.boco.xxzx.service.LeaveBillService;
import com.boco.xxzx.service.WorkflowSecurityService;
import com.boco.xxzx.service.WorkflowService;
import com.boco.xxzx.utils.constant.Constant;
import com.boco.xxzx.utils.session.SessionContext;
/**
 * 
 * @ClassName:  LeaveBill   
 * @Description:TODO(请假管理)   
 * @author: niutongtong  
 * @date:   2017年10月19日 上午11:44:18   
 *
 */
@Controller
@RequestMapping("/leaveBill")
public class LeaveBillController {
	  Logger logger=Logger.getLogger(this.getClass());
	  @Autowired
	  private  LeaveBillService leaveBillService;
	  @Autowired
	  private WorkflowService workflowService;
	  @Autowired
	  private WorkflowSecurityService WorkflowSecurityService;
	  
      @RequestMapping("/toShowLeaveBill")
	  public String toShowLeaveBill(Model model){
    	  long  userId= SessionContext.getCurrentUser().getId();
    	  List<LeaveBill>  leaveBills =  leaveBillService.listLeaveBill(userId);
    	  model.addAttribute("leaveBills", leaveBills);
		  return "leaveBill/showLeaveBills";
	  }     
      @RequestMapping("/showLeaveBillsByParameter")
	  public String showLeaveBillsByParameter(String id,String state,String sdate, String edate, Model model){
    	  logger.info("****id:"+id+"****state:"+state+"*****sdate:"+sdate+"*****edate:"+edate);
    	  model.addAttribute("id", id);
    	  model.addAttribute("state", state);
    	  model.addAttribute("sdate", sdate);
    	  model.addAttribute("edate", edate);
    	  long  userId= SessionContext.getCurrentUser().getId();
    	  List<LeaveBill>  leaveBills =  leaveBillService.listLeaveBillByParameter(id, state, sdate, edate,userId);
    	  model.addAttribute("leaveBills", leaveBills);
		  return "leaveBill/showLeaveBills";
	  }     
      @RequestMapping("/tosaveLeaveBill")
      public String tosaveLeaveBill( String groupName, Model model){
    	  List<User> userList =  WorkflowSecurityService.listUserInShip(groupName);
    	  model.addAttribute("userList", userList);
    	  model.addAttribute("groupName", groupName);
    	  return "leaveBill/saveLeaveBill";
      }
      
      
      @RequestMapping("/toEditLeaveBill")
      public String toEditLeaveBill(String id,String groupName, Model model){
    	LeaveBill leaveBill=  leaveBillService.getLeaveBillById(id);
    	 List<User> userList =  WorkflowSecurityService.listUserInShip(groupName);
   	    model.addAttribute("userList", userList);
    	model.addAttribute("leaveBill", leaveBill);
    	model.addAttribute("groupName", groupName);
    	  return "leaveBill/editLeaveBill";
      }
	  @RequestMapping("/saveLeaveBill")
      public String saveLeaveBill(LeaveBill leaveBill){
		  leaveBill.setUser(SessionContext.getCurrentUser());
		  leaveBillService.insertLeaveBill(leaveBill);
		  return "redirect:/leaveBill/showLeaveBillsByParameter.do?state="+Constant.FormKey.INITIAL;
      }
	  @RequestMapping("/submitLeaveBillTask")
	  public String submitLeaveBillTask(LeaveBill leaveBill,String comment,String[] s2 ,String outCome){		
		  String assignee = null;
		  if(s2==null){
				 assignee=null;
			}else{
				 assignee=String.valueOf(StringUtils.join(s2, ","));//制定下一步办理人
				logger.info("************assignee"+StringUtils.join(s2, ","));
			}
		  leaveBill.setUser(SessionContext.getCurrentUser());
		  /**
		   *保存信息 ，返回主键
		   */
		  leaveBillService.insertLeaveBill(leaveBill);
		  /**
		   * 启动流程实例 
		   */
		 Task task =  workflowService.saveStartProcessReturnTask(LeaveBill.class.getSimpleName(), String.valueOf(leaveBill.getId()));
		 /**
		   *更新信息状态
		   */
		  leaveBillService.updateLeaveBillState(String.valueOf(leaveBill.getId()) , Constant.FormKey.PROCESS_FLOW);
		  /**
		   * 拿到任务id 并完成   申请————》领导A
		   */
		  if(task!=null){
			  workflowService.completeTaskByoutcome(task.getId(), outCome, comment, String.valueOf(SessionContext.getCurrentUser().getId()), assignee);
		  }
		  /**
		   * 回到待办页面
		   */
		  return "redirect:/wf/listTODO.do"; 
	  }
	  
	  @RequestMapping("/submitLeaveBillTaskCaoGao")
	  public String submitLeaveBillTaskCaoGao(LeaveBill leaveBill,String comment,String[] s2 ,String outCome){		
		  String assignee = null;
		  if(s2==null){
				 assignee=null;
			}else{
				 assignee=String.valueOf(StringUtils.join(s2, ","));//制定下一步办理人
				logger.info("************assignee"+StringUtils.join(s2, ","));
			}
		  /**
		   *更新信息
		   */
		  leaveBillService.updateLeaveBill(leaveBill);
		  
		  /**
		   * 启动流程实例 
		   */
		 Task task =  workflowService.saveStartProcessReturnTask(LeaveBill.class.getSimpleName(), String.valueOf(leaveBill.getId()));
		 /**
		   *更新信息状态
		   */
		  leaveBillService.updateLeaveBillState(String.valueOf(leaveBill.getId()) , Constant.FormKey.PROCESS_FLOW);
		 /**
		   * 拿到任务id 并完成   申请————》领导A
		   */
		  if(task!=null){
			  workflowService.completeTaskByoutcome(task.getId(), outCome, comment, String.valueOf(SessionContext.getCurrentUser().getId()), assignee);
		  }
		  /**
		   * 回到待办页面
		   */
		  return "redirect:/wf/listTODO.do"; 
	  }
	  
	  
	  
	  @RequestMapping("/updateLeaveBill")
	  public String updateLeaveBill(LeaveBill leaveBill){
		  leaveBillService.updateLeaveBill(leaveBill);
		  return "redirect:/leaveBill/showLeaveBillsByParameter.do?state="+Constant.FormKey.INITIAL;
	  }
	  
	  @RequestMapping("/deleteLeaveBill")
	  public String deleteLeaveBill(String id ){
		  leaveBillService.deleteLeaveBillById(id);
		  return "redirect:/leaveBill/toShowLeaveBill.do";
	  }
      @RequestMapping("/startProcess")
	  public String startProcess(String id){
    	  LeaveBill leaveBill=  leaveBillService.getLeaveBillById(id);
    	  String key=leaveBill.getClass().getSimpleName();
    	  logger.info("***key:"+key);
    	  logger.info("***id:"+id);
    	  workflowService.saveStartProcess(key, id);
    	  String state=Constant.FormKey.PROCESS_FLOW;
    	  logger.info("*****state:"+state);
    	  leaveBillService.updateLeaveBillState(id, state);
    	  return "redirect:/wf/listTODO.do";
	  }
	  @RequestMapping("/viewHisComment")
      public String  viewHisComment(String id , Model model){
    	LeaveBill leaveBill =  leaveBillService.getLeaveBillById(id);
    	model.addAttribute("leaveBill", leaveBill);
    	logger.info("***"+JSON.toJSONString(leaveBill));
    	//获取对象的名称
		String objectName = leaveBill.getClass().getSimpleName();
		//组织流程表中的字段中的值
		String objId = objectName+"."+id;
    	List<Comment> commentList =	workflowService.findCommentByObjId(objId);
    	model.addAttribute("commentList", commentList);
    	logger.info("****"+JSON.toJSONString(commentList));
		return "leaveBill/leaveBillHis";
      }
	  
	  @RequestMapping("/leaveBillviewHisTask")
      public String  leaveBillviewHisTask(String taskId , Model model){
    	LeaveBill leaveBill =  workflowService.findLeaveBillByHisTaskId(taskId);
    	model.addAttribute("leaveBill", leaveBill);
    	logger.info("***"+JSON.toJSONString(leaveBill));
    	List<Comment> commentList =	workflowService.findCommentByHisTaskId(taskId);
    	model.addAttribute("commentList", commentList);
    	logger.info("****"+JSON.toJSONString(commentList));
		return "leaveBill/leaveBillHis";
      }    
}
