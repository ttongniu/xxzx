package com.boco.xxzx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boco.xxzx.service.LogService;
import com.boco.xxzx.service.WorkflowSecurityService;
import com.boco.xxzx.vo.WfGroupVO;

/**
 * 
 * @ClassName:  WorkflowSecurityController   
 * @Description:TODO(流程权限controller)   
 * @author: niutongtong  
 * @date:   2017年9月26日 下午7:47:05   
 *
 */
@Controller
@RequestMapping("/wfSecurity")
public class WorkflowSecurityController {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private WorkflowSecurityService workflowSecurityService;
	@Autowired
	private LogService  logService;
    
	/**
	 * 
	 * <p>Title: toSaveWfGroup</p>   
	 * <p>Description: TODO(跳转添加权限组页面)</p>  
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/toSaveWfGroup")
	public String toSaveWfGroup() {
		return "/wf/workflowSecurity/saveWfGroup";
	}
    
	/**
	 * 
	 * <p>Title: saveWfGroup</p>   
	 * <p>Description: TODO(保存权限组信息)</p>  
	 * @param: @param wfGroupVO
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/saveWfGroup")
	public String saveWfGroup(WfGroupVO wfGroupVO) {
		logger.info("***" + wfGroupVO.toString());
		workflowSecurityService.saveWorkflowGroup(wfGroupVO);
		logService.addLog("权限组名称：【"+wfGroupVO.getId()+"】   权限组信息保存完成！！！");
		return "redirect:/wfSecurity/showWfGroups.do";
	}
    
	/**
	 * 
	 * <p>Title: cheakwfGroup</p>   
	 * <p>Description: TODO(判断组名称是否可用)</p>  
	 * @param: @param wfGroupVO
	 * @param: @return      
	 * @return: Map<String,Object>      
	 *
	 */
	@RequestMapping("/cheakwfGroup")
	@ResponseBody
	public Map<String, Object> cheakwfGroup(WfGroupVO wfGroupVO) {
		Map<String, Object> map = new HashMap<String, Object>(16);
		boolean flag = workflowSecurityService.getWfGroupbyId(wfGroupVO);
		if (flag) {
			map.put("mag", "<font color='green'>组名可用!</font>");
		} else {
			map.put("mag", "<font color='red'>组名不可用!</font>");
		}
		logService.addLog("判断组名称【"+wfGroupVO.getId()+"】是否可用");
		return map;
	}
    
	/**
	 * 
	 * <p>Title: showWfGroups</p>   
	 * <p>Description: TODO(权限组列表)</p>  
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/showWfGroups")
	public String showWfGroups(Model model) {
		List<Group> groupList = workflowSecurityService.listWfGroup();
		model.addAttribute("groupList", groupList);
		return "/wf/workflowSecurity/showWfGroup";
	}
    
	/**
	 * 
	 * <p>Title: deleteWfGroupbyId</p>   
	 * <p>Description: TODO(删除权限组信息)</p>  
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/deleteWfGroupbyId")
	public String deleteWfGroupbyId(String id) {
		workflowSecurityService.deleteWfGroupbyId(id);
		logService.addLog("删除权限组名：【"+id+"】删除权限组完成！！！");
		return "redirect:/wfSecurity/showWfGroups.do";
	}
	
	
	/**
	 * 
	 * <p>Title: toEditMemberShip</p>   
	 * <p>Description: TODO(跳转分配权限功能页面)</p>  
	 * @param: @param id
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 *
	 */
	@RequestMapping("/toEditMemberShip")
	public String toEditMemberShip(String id, Model model) {
		Group group = workflowSecurityService.getWfGroupbyId(id);
		// List<User> userList = workflowSecurityService.listUser();
		// 查询未分配用户
		List<com.boco.xxzx.model.User> userLeftList = workflowSecurityService.listUserNotInShip(id);
		//查询已分配的用户
		List<com.boco.xxzx.model.User> userRightList = workflowSecurityService.listUserInShip(id);
		model.addAttribute("group", group);
		model.addAttribute("userLeftList", userLeftList);
		model.addAttribute("userRightList", userRightList);
		return "/wf/workflowSecurity/editMemberShip";
	}
    
	 /**
	  * 
	  * <p>Title: saveMemberShip</p>   
	  * <p>Description: TODO(分配权限功能)</p>  
	  * @param: @param s2
	  * @param: @param id
	  * @param: @return      
	  * @return: String      
	  *
	  */
	@RequestMapping("/saveMemberShip")
	@ResponseBody
	public Map<String, Object> saveMemberShip(String[] s2, String id) {
		
		Map<String, Object> map = new HashMap<String, Object>(16);
       try{
		//先删除旧的 
		workflowSecurityService.deleteMemberShip(id);
		logger.info("*****" + id);
		logger.info("*****" + s2.toString());
		//在添加新的
		workflowSecurityService.saveMemberShip(s2, id);
		map.put("mag", "success");
       }catch(Exception e){
        map.put("mag", "fail");
       }
       logService.addLog("分配权限功能完成！！！");
	   return map;
	}
}
