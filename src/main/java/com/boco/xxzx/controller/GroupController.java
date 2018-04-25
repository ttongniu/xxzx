package com.boco.xxzx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.boco.xxzx.model.Dictionary;
import com.boco.xxzx.model.GroupMenu;
import com.boco.xxzx.model.GroupRole;
import com.boco.xxzx.model.Menu;
import com.boco.xxzx.model.RoleDict;
import com.boco.xxzx.service.DictionaryService;
import com.boco.xxzx.service.GroupService;
import com.boco.xxzx.service.MenuService;
import com.boco.xxzx.service.RoleService;

/**
 * @author liushaoqing
 * @date 创建时间：2017年10月26日 下午4:37:13
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private MenuService menuService;

	/**
	 * 
	 * <p>
	 * Title: selectGroup
	 * </p>
	 * <p>
	 * Description: TODO(数据查询方法)
	 * </p>
	 * 
	 * @param: @param Group
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/selectGroup")
	public ModelAndView selectGroup(Dictionary group) {
		ModelAndView mav = new ModelAndView();
		List<Dictionary> groupList = groupService.selectGroup(group);
		mav.addObject("groupList", groupList);
		mav.setViewName("organization/group/group_list");
		return mav;
	}

	/**
	 * 
	 * <p>
	 * Title: toAdd
	 * </p>
	 * <p>
	 * Description: TODO(跳转添加页面)
	 * </p>
	 * 
	 * @param: @param Group
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
//	@RequestMapping("/toAdd")
//	public ModelAndView toAdd() {
//		ModelAndView mav = new ModelAndView("organization/group/group_add");
//		return mav;
//	}

	/**
	 * 
	 * <p>
	 * Title: toModify
	 * </p>
	 * <p>
	 * Description: TODO(跳转修改页面)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
//	@RequestMapping("/toModify")
//	public ModelAndView toModify(Integer groupId) {
//		ModelAndView mav = new ModelAndView("organization/group/group_modify");
//		Dictionary group = new Dictionary();
//		group.setGroupId(groupId);
//		Dictionary wgroup = groupService.selectGroup(group).get(0);
//		mav.addObject("group", wgroup);
//		return mav;
//	}

	/**
	 * 
	 * <p>
	 * Title: addGroup
	 * </p>
	 * <p>
	 * Description: TODO(添加数据方法)
	 * </p>
	 * 
	 * @param: @param Group
	 * @param: @return
	 * @return:String
	 *
	 */
//	@RequestMapping(value = "/addGroup", produces = "text/html;charset=UTF-8")
//	@ResponseBody
//	public String addGroup(Group group) {
//		String message = "";
//		group.setCreateTime(new Date());
//		Integer result = groupService.addGroup(group);
//		if (result != null) {
//			message = "添加成功";
//		} else {
//			message = "添加失败";
//		}
//		return message;
//	}

	/**
	 * 
	 * <p>
	 * Title: modifyGroup
	 * </p>
	 * <p>
	 * Description: TODO(修改数据方法)
	 * </p>
	 * 
	 * @param: @param Group
	 * @param: @return
	 * @return:String
	 *
	 */
//	@RequestMapping(value = "/modifyGroup", produces = "text/html;charset=UTF-8")
//	@ResponseBody
//	public String modifyGroup(Group group) {
//		String message = "";
//		group.setModifyTime(new Date());
//		Integer result = groupService.modifyGroup(group);
//		if (result != null) {
//			message = "修改成功";
//		} else {
//			message = "修改失败";
//		}
//		return message;
//	}

	/**
	 * 
	 * <p>
	 * Title: deleteGroup
	 * </p>
	 * <p>
	 * Description: TODO(删除)
	 * </p>
	 * 
	 * @param: @param Group
	 * @param: @return
	 * @return:String
	 *
	 */
//	@RequestMapping(value="/delGroup", produces = "text/html;charset=UTF-8")
//	@ResponseBody
//	public String delGroup(Integer groupId) {
//		String message;
//		try {
//			groupService.delGroup(groupId);
//			message="删除成功";
//		} catch (Exception e) {
//			message="删除失败";
//		}
//		return message;
//	}
/**
 * 
 * <p>
 * Title: toModify
 * </p>
 * <p>
 * Description: TODO(跳转修改页面)
 * </p>
 * 
 * @param: @param Integer
 * @param: @return
 * @return:ModelAndView
 *
 */
@RequestMapping("/maintainData")
public ModelAndView maintainData(Integer groupId) {
	ModelAndView mav = new ModelAndView("organization/group/group_data");
	List<GroupRole> groupRoleList=groupService.selectGroupRoleById(groupId);
	List<Dictionary> roleList=roleService.selectRole(new Dictionary());
	List<Dictionary> dcList=dictionaryService.listDictionaryByCode("qx");
	mav.addObject("roleList", roleList);
	mav.addObject("dcList", dcList);
	mav.addObject("groupId", groupId);
	mav.addObject("groupRoleList", groupRoleList);
	return mav;
}
/**
 * 
 * <p>
 * Title: toModify
 * </p>
 * <p>
 * Description: TODO(跳转修改页面)
 * </p>
 * 
 * @param: @param Integer
 * @param: @return
 * @return:ModelAndView
 *
 */
@RequestMapping("/maintainMenu")
public ModelAndView maintainMenu(Integer groupId) {
	ModelAndView mav = new ModelAndView("organization/group/group_menu");
//	List<GroupRole> groupRoleList=this.selectGroupRoleById(groupId);
//	List<Role> roleList=roleService.selectRole(new Role());
//	List<Dictionary> dcList=dictionaryService.listDictionaryByCode("qx");
//	mav.addObject("roleList", roleList);
//	mav.addObject("dcList", dcList);
	mav.addObject("groupId", groupId);
//	mav.addObject("groupRoleList", groupRoleList);
	List<GroupMenu> groupMenuList=groupService.selectGroupMenuById(groupId);
	List<Menu> menuList=menuService.selectMenu(new Menu());
	mav.addObject("menuList", menuList);
	mav.addObject("groupMenuList", groupMenuList);
	return mav;
}
/**
 * 
 * <p>
 * Title: addGroupRole
 * </p>
 * <p>
 * Description: TODO(角色组角色授权)
 * </p>
 * 
 * @param: @param String[] cur_choose,Integer groupId
 * @param: @return
 * @return:String
 *
 */
@RequestMapping(value="/addGroupRole",produces="text/html;charset=UTF-8")
@ResponseBody
public String addGroupRole(Integer roleId,Integer dataId,Integer groupId) {
								
			String message=groupService.addGroupRole(roleId, dataId,groupId);	

	return message;	
	
}
/**
 * 
 * <p>
 * Title: addGroupRole
 * </p>
 * <p>
 * Description: TODO(删除角色组角色授权)
 * </p>
 * 
 * @param: @param String[] cur_choose,Integer groupId
 * @param: @return
 * @return:String
 *
 */
@RequestMapping(value="/deleteGroupRole",produces="text/html;charset=UTF-8")
@ResponseBody
public String deleteGroupRole(Integer roleId,Integer dataId,Integer groupId) {
								
			String message=groupService.deleteGroupRole(roleId, dataId,groupId);	

	return message;	
	
}
/**
 * 
 * <p>
 * Title: addGroupRole
 * </p>
 * <p>
 * Description: TODO(删除角色组角色授权)
 * </p>
 * 
 * @param: @param String[] cur_choose,Integer groupId
 * @param: @return
 * @return:String
 *
 */
@RequestMapping(value="/deleteGroupMenu",produces="text/html;charset=UTF-8")
@ResponseBody
public String deleteGroupMenu(Integer menuId,Integer groupId) {
								
			String message=groupService.deleteGroupMenu(menuId, groupId);	

	return message;	
	
}
/**
 * 
 * <p>
 * Title: addGroupMenu
 * </p>
 * <p>
 * Description: TODO(他角色组角色授权)
 * </p>
 * 
 * @param: @param String[] cur_choose,Integer groupId
 * @param: @return
 * @return:String
 *
 */
@RequestMapping(value="/addGroupMenu",produces="text/html;charset=UTF-8")
@ResponseBody
public String addGroupMenu(Integer menuId,Integer groupId) {
	
			String message=groupService.addGroupMenu(menuId,groupId);	

	return message;	
	
}
}
