package com.boco.xxzx.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;












import com.boco.xxzx.model.Dictionary;
import com.boco.xxzx.model.Menu;
import com.boco.xxzx.model.RoleDict;
import com.boco.xxzx.service.GroupService;
import com.boco.xxzx.service.DictionaryService;
import com.boco.xxzx.service.LogService;
import com.boco.xxzx.service.MenuService;
import com.boco.xxzx.service.RoleService;
import com.boco.xxzx.utils.session.SessionContext;
import com.sun.tools.doclets.formats.html.markup.DocType;
/**
 * @author liushaoqing
 * @date 创建时间：2017年10月26日 下午4:38:48
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private LogService logService;
	/**
	 * 
	 * <p>
	 * Title: selectRole
	 * </p>
	 * <p>
	 * Description: TODO(角色查询方法)
	 * </p>
	 * 
	 * @param: @param Role
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/selectRole")
	public ModelAndView selectRole(Dictionary role) {
		ModelAndView mav=new ModelAndView();
		List<Dictionary> roleList=roleService.selectRole(role);
		mav.addObject("roleList", roleList);
		mav.setViewName("organization/role/role_list");
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
	 * @param: @param Role
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
//	@RequestMapping("/toAdd")
//	public ModelAndView toAdd () {
//		ModelAndView mav=new ModelAndView("organization/role/role_add");		
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
//	public ModelAndView toModify (Integer roleId) {
//		ModelAndView mav=new ModelAndView("organization/role/role_modify");
//		Dictionary role=new Dictionary();
//		role.setId(roleId);
//		Dictionary wrole=roleService.selectRole(role).get(0);	
//		mav.addObject("role", wrole);
//		return mav;		
//	}
	/**
	 * 
	 * <p>
	 * Title: toAuthorize
	 * </p>
	 * <p>
	 * Description: TODO(跳转授权页面)
	 * </p>
	 * 
	 * @param: @param Integer
	 * * @param: @param String
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/toAuthorize")
	public ModelAndView toAuthorize(Integer roleId,String roleName) {
		ModelAndView mav=new ModelAndView("organization/role/role_authorize1");
		List<Menu> menuList=menuService.selectMenu(new Menu());
		List<Dictionary> dataList=dictionaryService.listDictionaryByCode("qx");
		List<RoleDict> roleDictList=roleService.selectAuthorize(roleId);
		mav.addObject("roleId", roleId);
		mav.addObject("roleName", roleName);
		mav.addObject("menuList", menuList);
		mav.addObject("dataList", dataList);
		mav.addObject("roleDictList", roleDictList);
		return mav;
		
	}
	/**
	 * 
	 * <p>
	 * Title: addRole
	 * </p>
	 * <p>
	 * Description: TODO(添加角色方法)
	 * </p>
	 * 
	 * @param: @param Role
	 * @param: @return
	 * @return:String
	 *
	 */
//	@RequestMapping(value="/addRole",produces="text/html;charset=UTF-8")
//	@ResponseBody
//	public String addRole(Role role) {
//		String message="";
//		role.setCreateTime(new Date());
//		Integer result=roleService.addRole(role);
//		if (result!=0) {
//			message="添加成功";
//			logService.addLog("添加角色"+role.getRoleName()+"成功");
//		}else {
//			message="添加失败";
//			logService.addLog("添加角色"+role.getRoleName()+"失败");
//		}
//		return message;	
//	}
	
	/**
	 * 
	 * <p>
	 * Title: modifyRole
	 * </p>
	 * <p>
	 * Description: TODO(修改角色方法)
	 * </p>
	 * 
	 * @param: @param Role
	 * @param: @return
	 * @return:String
	 *
	 */
//	@RequestMapping(value="/modifyRole",produces="text/html;charset=UTF-8")
//	@ResponseBody
//	public String modifyRole(Role role) {
//		String message="";
//		String  eId= SessionContext.getCurrentUser().getEmployeeId();
//		role.setModifyTime(new Date());
//		role.setModifier(eId);
//		Integer result=roleService.modifyRole(role);
//		if (result!=0) {
//			message="修改成功";
//			logService.addLog("修改角色"+role.getRoleName()+"成功");
//		}else {
//			message="修改失败";
//			logService.addLog("修改角色"+role.getRoleName()+"失败");
//		}
//		return message;	
//	}
	/**
	 * 
	 * <p>
	 * Title: deleteRole
	 * </p>
	 * <p>
	 * Description: TODO(删除)
	 * </p>
	 * 
	 * @param: @param Role
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
//	@RequestMapping(value="/delRole",produces="text/html;charset=UTF-8")
//	@ResponseBody
//	public String delRole (Integer roleId) {
//		String message="";
//		try {
//			int result=roleService.delRole(roleId);
//			if (result!=0) {
//				message="删除成功";
//				logService.addLog("删除角色"+roleId+"成功");
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			message="删除角色失败";
//			logService.addLog("删除角色"+roleId+"失败");
//		}
//		return message;
//	}
	/**
	 * 
	 * <p>
	 * Title: authorizeRole
	 * </p>
	 * <p>
	 * Description: TODO(角色授权)
	 * </p>
	 * 
	 * @param: @param String[]
	 * * @param: @param String
	 * @param: @return
	 * @return:String
	 *
	 */
//	@RequestMapping(value="/authorizeRole",produces="text/html;charset=UTF-8")
//	@ResponseBody
//	public String authorizeRole(String[] cur_choose,Integer roleId) {
//		String message="修改成功";
//		boolean flag=true;
//		List<RoleDict> roleDictList=roleService.selectAuthorize(roleId);
//		RoleDict roleDict=new RoleDict();
//		for (int i = 0; i < cur_choose.length; i++) {
//		flag=true;
//		String[] ids=cur_choose[i].split(",");
//		for (int j = 0; j < roleDictList.size(); j++) {
//			if (roleDictList.get(j).getMenuId()==Integer.parseInt(ids[0]) && roleDictList.get(j).getDataId()==Integer.parseInt(ids[1])) {
//			flag=false;
//			}
//		}
//			if (flag==true) {
//				roleDict.setDataId(Integer.parseInt(ids[1]));
//				roleDict.setRoleId(roleId);
//				roleDict.setMenuId(Integer.parseInt(ids[0]));								
//				Integer result=roleService.authorizeRole(roleDict);	
//				if (result==0) {
//					message="修改失败";
//				}}		
//		}
//
//		return message;	
//		
//	}
	@RequestMapping(value="/authorizeRole",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String authorizeRole(String[] cur_choose,Integer roleId) {								
				String message=roleService.authorizeRole(cur_choose, roleId);	
				
		return message;	
		
	}
}
