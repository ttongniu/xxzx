package com.boco.xxzx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.xxzx.dao.UserDao;
import com.boco.xxzx.model.User;
import com.boco.xxzx.model.UserAndRole;
import com.boco.xxzx.service.LogService;
import com.boco.xxzx.service.UserService;
/** 
 * @author   liushaoqing 
 * @date 创建时间：2017年10月26日 下午4:38:11 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */

@Service("userService")
public class UserServiceImpl implements UserService{
	 @Autowired
     private UserDao userDao;
	 @Autowired
     private LogService logService;
	public List<User> login(User user){
     	return userDao.login(user);
     }
	public Integer addUser(User user) {		
		return userDao.addUser(user);
	}
	public Integer delUser(Integer id) {
		return userDao.delUser(id);
	}
	public Integer modifyUser(User user) {
		return userDao.modifyUser(user);
	}
	public List<User> selectUser(User user) {
		return userDao.selectUser(user);
	}
	public Integer changeStatus(User user) {
		return userDao.changeStatus(user);

	}
	public User getUserByUserId(Integer id) {
		return userDao.getUserByUserId(id);

	}
	public String authorizeUser(Integer[] cur_choose,String employeeId) {
		String message="修改成功";
		try {
		
		userDao.delUserAndRole(employeeId);
		UserAndRole userAndRole=new UserAndRole();
		for (int i = 0; i < cur_choose.length; i++) {
				userAndRole.setRoleId(cur_choose[i]);
				userAndRole.setEmployeeId(employeeId);								
				Integer result=userDao.authorizeUser(userAndRole);	
				if (result==0) {
					message="修改失败";
				}	
		}
		} catch (Exception e) {
			logService.addLog("授权时发生异常："+e);
			message="修改失败";
		}
		logService.addLog("人员"+employeeId+"授权"+message);
		return message;
	}
	public List<UserAndRole> selectAuthorize(String employeeId) {
		return userDao.selectAuthorize(employeeId);
	}
	public Integer delUserAndRole(String employeeId) {
		return userDao.delUserAndRole(employeeId);

	}
	public List<User> selectUserWithRole(User user) {
		return userDao.selectUserWithRole(user);
	}
	public List<User> selectCriteriaUser(String condi) {
		return userDao.selectCriteriaUser(condi);
	}
	public User selectUserById(Integer id) {
		return userDao.selectUserById(id);
	}
	public Integer modifyUserPwd(User user) {
		return userDao.modifyUserPwd(user);
	}   
	public Integer modifyPersonal(User user) {
		return userDao.modifyPersonal(user);
	} 
}
