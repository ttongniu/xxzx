package com.boco.xxzx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.xxzx.dao.RoleDao;
import com.boco.xxzx.model.Dictionary;
import com.boco.xxzx.model.RoleDict;
import com.boco.xxzx.service.LogService;
import com.boco.xxzx.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private LogService logService;

	public List<Dictionary> selectRole(Dictionary role) {
		return roleDao.selectRole(role);
	}

//	public Integer modifyRole(Dictionary role) {
//		return roleDao.modifyRole(role);
//	}
//
//	public Integer addRole(Dictionary role) {
//		return roleDao.addRole(role);
//	}
//
//	public Integer delRole(Integer roleId) {
//		this.deleteAuthorize(roleId);
//		this.delRoleNode(roleId);
//		return roleDao.delRole(roleId);
//	}
	public Integer delRoleNode(Integer roleId) {
		return roleDao.delRoleNode(roleId);
	}

	public String authorizeRole(String[] cur_choose,Integer roleId) {
			
		String message="修改成功";
		try {
		RoleDict roleDict=new RoleDict();
		roleDao.deleteAuthorize(roleId);
		for (int i = 0; i < cur_choose.length; i++) {
		String[] ids=cur_choose[i].split("-");
				roleDict.setDataId(Integer.parseInt(ids[1]));
				roleDict.setRoleId(roleId);
				roleDict.setMenuId(Integer.parseInt(ids[0]));								
				Integer result=roleDao.authorizeRole(roleDict);	
				if (result==0) {
					message="修改失败";
				}	
		}
		} catch (Exception e) {
			logService.addLog("授权时发生异常");
			System.out.println(e);
			message="修改失败";
		}
		logService.addLog("角色-"+roleId+"-授权"+message);
		return message;
	}

	public List<RoleDict> selectAuthorize(Integer roleId) {
		return roleDao.selectAuthorize(roleId);
	}

	public Integer deleteAuthorize(Integer roleId) {
		return roleDao.deleteAuthorize(roleId);
	}

	public List<Dictionary> selectUnauthorized(String employeeId) {
		return roleDao.selectUnauthorized(employeeId);
	}

}
