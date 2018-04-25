package com.boco.xxzx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.boco.xxzx.model.User;
import com.boco.xxzx.service.LogService;
import com.boco.xxzx.service.UserService;
import com.boco.xxzx.utils.util.MD5Encoding;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService  userService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private LogService logService;
	@RequestMapping("/login")
	public String login(User user,String rand){
		String url="";
		//获取session中的验证码
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session=request.getSession();
		
		request.setAttribute("username", user.getEmployeeId());
		String yzm=(String) session.getAttribute("rand");
		if(!yzm.equals(rand)){
			request.setAttribute("error", "验证码不正确");
			url="login";
		}else{
			List<User> userList=userService.selectUser(user);//查询所有的用户
			List<User> userList2=userService.selectUserWithRole(user);//查询已授权的用户
			if (userList.size()>0) {
				MD5Encoding md5=new MD5Encoding();
				String md5Password=md5.getMD5ofStr(user.getPassword());
				User user1=userList.get(0);
				if (user1.getStatus()==0) {
					//用户为离职状态
					request.setAttribute("error", "用户名或密码不正确");
					url="login";
				}
				if (!md5Password.equals(user1.getPassword())) {
					//密码错误
					request.setAttribute("error", "用户名或密码不正确");
					url="login";
				}else if (userList2.size()==0) {//如果所有的用户中有，而授权用户中没有，则为未授权
					request.setAttribute("error", "用户未授权");
					url="login";
				}else{
					User user2=userList2.get(0);
					session.setAttribute("user", user2);
					/**
					 * 待办事宜数量
					 */
					Long num=taskService.createTaskQuery().taskAssignee(String.valueOf(user1.getId())).count();
					session.setAttribute("num", num);
					/*登录日志*/
					logService.addLog("登录系统");
					url="redirect:/jsp/index.jsp";
				}
			}else{
				//用户名错误
				request.setAttribute("error", "用户名或密码不正确");
				url="login";
			}
		}
		return url;
	}
	
	@RequestMapping("/loginOut")
	public ModelAndView loginOut(HttpSession session){
		ModelAndView view=new ModelAndView("login");
		logService.addLog("退出系统");
		session.invalidate();
		return view;
	}
	
	@RequestMapping("/loginTo")
	public String loginTo(HttpSession session){
		logService.addLog("退出系统");
		session.invalidate();
		return "redirect:/jsp/login.jsp";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "login";
	}
	
}
