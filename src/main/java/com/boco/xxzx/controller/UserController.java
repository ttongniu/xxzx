package com.boco.xxzx.controller;

import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.boco.xxzx.model.Department;
import com.boco.xxzx.model.Dictionary;
import com.boco.xxzx.model.User;
import com.boco.xxzx.model.UserAndRole;
import com.boco.xxzx.service.DepartmentService;
import com.boco.xxzx.service.LogService;
import com.boco.xxzx.service.MenuService;
import com.boco.xxzx.service.RoleService;
import com.boco.xxzx.service.UserService;
import com.boco.xxzx.utils.util.MD5Encoding;
import com.boco.xxzx.utils.session.SessionContext;

/**
 * @author liushaoqing
 * @date 创建时间：2017年10月26日 下午4:37:13
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private LogService logService;
	/**
	 * 
	 * <p>
	 * Title: toAdd
	 * </p>
	 * <p>
	 * Description: TODO(跳转添加页面)
	 * </p>
	 * 
	 * @param: @param
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd() {
		ModelAndView mav = new ModelAndView("organization/user/user_add");
		Department department = new Department();
		List<Department> depList = departmentService.selectDept(department);
		mav.addObject("depList", depList);
		return mav;
	}

	/**
	 * 
	 * <p>
	 * Title: toShow
	 * </p>
	 * <p>
	 * Description: TODO(跳转展示页面)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/toShow")
	public ModelAndView toShow(Integer userId) {
		ModelAndView mav = new ModelAndView("organization/user/user_show");
		User user = new User();
		user.setId(userId);
		User wuser = userService.selectUser(user).get(0);
		mav.addObject("user", wuser);
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
	@RequestMapping("/toModify")
	public ModelAndView toModify(Integer userId) {
		ModelAndView mav = new ModelAndView("organization/user/user_modify");
		User wuser;
		if (userId==null) {
			 wuser= SessionContext.getCurrentUser();
		}else{
			User user = new User();
		user.setId(userId);
		 wuser = userService.selectUser(user).get(0);
		}
		List<Department> depList = departmentService.selectDept(new Department());
		mav.addObject("depList", depList);
		mav.addObject("user", wuser);
		return mav;
	}
	/**
	 * 
	 * <p>
	 * Title: toAuthorize
	 * </p>
	 * <p>
	 * Description: TODO(跳转修改页面)
	 * </p>
	 * 
	 * @param: @param Integer
	 * * @param: @param String
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/toAuthorize")
	public ModelAndView toAuthorize(String employeeId,String employeeName) {
		ModelAndView mav=new ModelAndView("organization/user/user_authorize");
		List<Dictionary> roleList=roleService.selectUnauthorized(employeeId);
		List<UserAndRole> userAndRoleList=userService.selectAuthorize(employeeId);
		mav.addObject("employeeId", employeeId);
		mav.addObject("employeeName", employeeName);
		mav.addObject("userAndRoleList", userAndRoleList);
		mav.addObject("roleList", roleList);
;		return mav;
		
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
	@RequestMapping("/personalModify")
	public ModelAndView personalModify(Integer userId) {
		ModelAndView mav = new ModelAndView("organization/personal/info_modify");
		User wuser;
		if (userId==null) {
			 wuser= SessionContext.getCurrentUser();
		}else{
		User user = new User();
		user.setId(userId);
		 wuser = userService.selectUser(user).get(0);
		}		
		mav.addObject("user", wuser);
		return mav;
	}
	/**
	 * 
	 * <p>
	 * Title: addUser
	 * </p>
	 * <p>
	 * Description: TODO(添加用户方法)
	 * </p>
	 * 
	 * @param: @param User
	 * @param: @return
	 * @return:String
	 *
	 */
	@RequestMapping(value = "/addUser", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUser(User user) {
		user.setCreateTime(new Date());
		String message = "";
		MD5Encoding md5=new MD5Encoding();
		String md5Password=md5.getMD5ofStr("123456");
		user.setPassword(md5Password);
		Integer isSuccess = userService.addUser(user);
		if (isSuccess != null) {
			message = "添加成功";
			logService.addLog("添加人员"+user.getEmployeeId()+"成功");
		} else {
			message = "添加失败";
			logService.addLog("添加人员"+user.getEmployeeId()+"失败");
		}
		return message;
	}

	/**
	 * 
	 * <p>
	 * Title: delUser
	 * </p>
	 * <p>
	 * Description: TODO(离职用户方法)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/delUser")
	public ModelAndView delUser(Integer id) {
		ModelAndView mav = new ModelAndView();
		Integer isSuccess = userService.delUser(id);
		if (isSuccess == 1) {
			mav.addObject("message", "操作成功");
		} else {
			mav.addObject("message", "操作失败");
		}
		return mav;

	}

	/**
	 * 
	 * <p>
	 * Title: modifyUser
	 * </p>
	 * <p>
	 * Description: TODO(修改用户方法)
	 * </p>
	 * 
	 * @param: @param User
	 * @param: @return
	 * @return:String
	 *
	 */
	@RequestMapping(value = "/modifyUser", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modifyUser(User user) {
		String  eId= SessionContext.getCurrentUser().getEmployeeId();
		user.setModifyTime(new Date());
		user.setModifier(eId);
		String message = "";
		Integer isSuccess = userService.modifyUser(user);
		if (isSuccess != null) {
			message = "修改成功";
			logService.addLog("修改人员"+user.getEmployeeId()+"成功");
		} else {
			message = "修改失败";
			logService.addLog("修改人员"+user.getEmployeeId()+"失败");
		}
		return message;
	}
	/**
	 * 
	 * <p>
	 * Title: modifyUser
	 * </p>
	 * <p>
	 * Description: TODO(修改用户方法)
	 * </p>
	 * 
	 * @param: @param User
	 * @param: @return
	 * @return:String
	 *
	 */
	@RequestMapping(value = "/modifyPersonal", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modifyPersonal(User user) {
		String  eId= SessionContext.getCurrentUser().getEmployeeId();
		user.setModifyTime(new Date());
		user.setModifier(eId);
		String message = "";
		Integer isSuccess = userService.modifyPersonal(user);
		if (isSuccess != null) {
			message = "修改成功";
			logService.addLog("修改人员"+user.getEmployeeId()+"成功");
		} else {
			message = "修改失败";
			logService.addLog("修改人员"+user.getEmployeeId()+"失败");
		}
		return message;
	}
	/**
	 * 
	 * <p>
	 * Title: modifyPwd
	 * </p>
	 * <p>
	 * Description: TODO(修改用户密码)
	 * </p>
	 * 
	 * @param: @param Integer id,String newPwd
	 * @param: @return
	 * @return:String
	 *
	 */
	@RequestMapping(value = "/modifyPwd", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modifyPwd(String newPwd) {
		String  eId= SessionContext.getCurrentUser().getEmployeeId();
		Integer uId= SessionContext.getCurrentUser().getId();
		User user =new User();
		user.setModifyTime(new Date());
		user.setModifier(eId);
		user.setId(uId);
		MD5Encoding md5=new MD5Encoding();
		String md5Password=md5.getMD5ofStr(newPwd);
		user.setPassword(md5Password);
		String message = "";
		Integer isSuccess = userService.modifyUserPwd(user);
		if (isSuccess != null) {
			message = "修改成功";
			logService.addLog("修改人员密码"+user.getEmployeeId()+"成功");
		} else {
			message = "修改失败";
			logService.addLog("修改人员密码"+user.getEmployeeId()+"失败");
		}
		return message;
	}

	/**
	 * 
	 * <p>
	 * Title: selectUser
	 * </p>
	 * <p>
	 * Description: TODO(用户查询方法)
	 * </p>
	 * 
	 * @param: @param User
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/selectUser")
	public ModelAndView selectUser(User user) {
		ModelAndView mav = new ModelAndView("organization/user/user_list");
		List<User> userList = userService.selectUser(user);
		mav.addObject("userList", userList);
		return mav;
	}
	/**
	 * 
	 * <p>
	 * Title: selectCriteriaUser
	 * </p>
	 * <p>
	 * Description: TODO(用户查询方法)
	 * </p>
	 * 
	 * @param: @param User
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/selectCriteriaUser")
	public ModelAndView selectCriteriaUser(String condi) {
		ModelAndView mav = new ModelAndView("organization/user/user_list");
		List<User> userList = userService.selectCriteriaUser(condi);
		mav.addObject("userList", userList);
		return mav;
	}

	/**
	 * 
	 * <p>
	 * Title: toAdd
	 * </p>
	 * <p>
	 * Description: TODO(状态变更)
	 * </p>
	 * 
	 * @param: @param User
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping(value="/changeStatus", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String changeStatus(User user) {
		String  eId= SessionContext.getCurrentUser().getEmployeeId();
		user.setModifyTime(new Date());
		user.setModifier(eId);
		String message="";
		if (user.getStatus() == 0) {
			user.setStatus(1);
		} else {
			user.setStatus(0);
		}
		int result=userService.changeStatus(user);
		if (result==0) {
			message="操作失败";
			logService.addLog("人员离职"+user.getEmployeeId()+"失败");
		}else{
			message="操作成功";
			logService.addLog("人员离职"+user.getEmployeeId()+"成功");
		}
		return message;
	}
	
	

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
	@RequestMapping(value="/authorizeUser",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String authorizeUser(Integer[] cur_choose,String employeeId) {							
				String message=userService.authorizeUser( cur_choose, employeeId);			
		return message;	
	
	}
	/**
	 * 
	 * <p>
	 * Title: pwdModify
	 * </p>
	 * <p>
	 * Description: TODO(跳转至密码修改)
	 * </p>
	 * 
	 * @param: @param String[]
	 * * @param: @param String
	 * @param: @return
	 * @return:String
	 *
	 */
	@RequestMapping("/pwdModify")
	public ModelAndView pwdModify() {
		ModelAndView mav=new ModelAndView("organization/personal/password_modify");
		
		return mav;	

	}
	/**
	 * 
	 * <p>
	 * Title: verifyPwd
	 * </p>
	 * <p>
	 * Description: TODO(验证密码)
	 * </p>
	 * 
	 * @param: @param String
	 * * @param: @param String
	 * @param: @return
	 * @return:String
	 *
	 */
	@RequestMapping(value="/verifyPwd",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String verifyPwd(String writePwd) {
		String flag;
		Integer  eId= SessionContext.getCurrentUser().getId();
		User user=userService.selectUserById(eId);
		MD5Encoding md5=new MD5Encoding();
		String Password=user.getPassword();
		String writeOPwd=md5.getMD5ofStr(writePwd);
		if (Password.equals(writeOPwd)) {
			flag="一致";
		} else {
            flag="不一致";
		}
		
		return flag;	
	
	}
}