package com.boco.xxzx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.xxzx.dao.GroupDao;
import com.boco.xxzx.dao.LogDao;
import com.boco.xxzx.dao.MenuDao;
import com.boco.xxzx.dao.RoleDao;
import com.boco.xxzx.model.Dictionary;
import com.boco.xxzx.model.GroupMenu;
import com.boco.xxzx.model.GroupRole;
import com.boco.xxzx.model.Menu;
import com.boco.xxzx.model.RoleDict;
import com.boco.xxzx.service.GroupService;
import com.boco.xxzx.service.LogService;
import com.boco.xxzx.service.MenuService;

@Service("groupService")
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private LogService logService;

	public List<Dictionary> selectGroup(Dictionary group) {
		return groupDao.selectGroup(group);
	}
	
//	public Integer modifyGroup(Group group) {
//		return groupDao.modifyGroup(group);
//	}

//	public Integer addGroup(Group group) {
//		return groupDao.addGroup(group);
//	}

//	public Integer delGroup(Integer groupId) {
//		this.delGroupNode(groupId);
//		return groupDao.delGroup(groupId);
//	}
	public Integer delGroupNode(Integer groupId) {
		return groupDao.delGroupNode(groupId);
	}

	public List<GroupRole> selectGroupRoleById(Integer groupId) {
		return groupDao.selectGroupRoleById(groupId);
	}
	public List<GroupMenu> selectGroupMenuById(Integer groupId) {
		return groupDao.selectGroupMenuById(groupId);
	}

	public String addGroupRole(Integer roleId,Integer dataId,Integer groupId) {
		String message="修改成功";
		GroupRole groupRole=new GroupRole();
       try {	
		//groupDao.deleteGroupRole(groupId);
	   
		groupRole.setDataId(dataId);
		groupRole.setGroupId(groupId);
		groupRole.setRoleId(roleId);	
		//首先添加的是组与角色的对应关系							
				Integer result=groupDao.addGroupRole(groupRole);	
				if (result==0) {
					message="修改失败";
				}	
		//然后添加的是角色与菜单的关系
				List<GroupMenu> groupMenuList=groupDao.selectGroupMenuById(groupId);
				for (GroupMenu groupMenu : groupMenuList) {
					boolean flag=true;
					RoleDict roleDict=new RoleDict();
					roleDict.setMenuId(groupMenu.getMenuId());
					roleDict.setRoleId(groupRole.getRoleId());
					roleDict.setDataId(groupRole.getDataId());
					List<RoleDict> menuList=roleDao.selectAuthorize(groupRole.getRoleId());
					for (RoleDict roleDict2 : menuList) {
						if (roleDict2.getMenuId()==groupMenu.getMenuId() 
								&& roleDict2.getDataId().equals(groupRole.getDataId())) {
							flag=false;
						}
					}
					if (flag==true) {						
						roleDao.authorizeRole(roleDict);
					}						
				
		}
} catch (Exception e) {
	message="修改失败";
	logService.addLog("保存时发生异常");
	System.out.println(e);
}
		return message;
	}
	public String deleteGroupRole(Integer roleId,Integer dataId,Integer groupId) {
		String message="修改成功";
		GroupRole groupRole=new GroupRole();
       try {	
		//groupDao.deleteGroupRole(groupId);
	   
		groupRole.setDataId(dataId);
		groupRole.setGroupId(groupId);
		groupRole.setRoleId(roleId);	
		//首先添加的是组与角色的对应关系							
				Integer result=groupDao.deleteGroupRole(groupRole);	
				if (result==0) {
					message="修改失败";
				}	
		//然后添加的是角色与菜单的关系
				List<GroupMenu> groupMenuList=groupDao.selectGroupMenuById(groupId);
				for (GroupMenu groupMenu : groupMenuList) {
					boolean flag=true;
					RoleDict roleDict=new RoleDict();
					roleDict.setMenuId(groupMenu.getMenuId());
					roleDict.setRoleId(groupRole.getRoleId());
					roleDict.setDataId(groupRole.getDataId());
					List<RoleDict> menuList=roleDao.selectAuthorize(groupRole.getRoleId());
					for (RoleDict roleDict2 : menuList) {
						if (roleDict2.getMenuId()==groupMenu.getMenuId() 
								&& roleDict2.getDataId().equals(groupRole.getDataId())) {
							flag=false;
						}
					}
					if (flag==false) {						
						roleDao.deleteAuthorizeRole(roleDict);
					}						
				
		}
} catch (Exception e) {
	message="修改失败";
	logService.addLog("保存时发生异常");
	System.out.println(e);
}
		return message;
	}
	
	public String addGroupMenu(Integer menuId,Integer groupId) {
		String message="修改成功";
		try {
			
	   GroupMenu groupMenu =new GroupMenu();
	   groupMenu.setGroupId(groupId);
	   groupMenu.setMenuId(menuId);
	   //首先添加组与菜单的关系
				Integer result=groupDao.addGroupMenu(groupMenu);	
				if (result==0) {
					message="修改失败";
				}					
		//然后添加的是角色与菜单的关系
		List<GroupRole> groupRoleList=groupDao.selectGroupRoleById(groupId);
		for (GroupRole groupRole : groupRoleList) {
			Boolean flag=true;
			RoleDict roleDict=new RoleDict();
			roleDict.setMenuId(groupMenu.getMenuId());
			roleDict.setRoleId(groupRole.getRoleId());
			roleDict.setDataId(groupRole.getDataId());
			List<RoleDict> menuList=roleDao.selectAuthorize(groupRole.getRoleId());
			for (RoleDict roleDict2 : menuList) {
				if (roleDict2.getMenuId()==groupMenu.getMenuId() 
						&& roleDict2.getDataId().equals(groupRole.getDataId())) {
					flag=false;
				}
			}
			if (flag==true) {						
				roleDao.authorizeRole(roleDict);
			}
			
		}
		
		} catch (Exception e) {
			message="修改失败";
			logService.addLog("保存时发生异常");
			System.out.println(e);
		}
		return message;
	}

	public String deleteGroupMenu(Integer menuId,Integer groupId) {
		String message="修改成功";
		try {
			
	   GroupMenu groupMenu =new GroupMenu();
	   groupMenu.setGroupId(groupId);
	   groupMenu.setMenuId(menuId);
	   //首先添加组与菜单的关系
				Integer result=groupDao.deleteGroupMenu(groupMenu);	
				if (result==0) {
					message="修改失败";
				}					
		//然后添加的是角色与菜单的关系
		List<GroupRole> groupRoleList=groupDao.selectGroupRoleById(groupId);
		for (GroupRole groupRole : groupRoleList) {
			Boolean flag=true;
			RoleDict roleDict=new RoleDict();
			roleDict.setMenuId(groupMenu.getMenuId());
			roleDict.setRoleId(groupRole.getRoleId());
			roleDict.setDataId(groupRole.getDataId());
			List<RoleDict> menuList=roleDao.selectAuthorize(groupRole.getRoleId());
			for (RoleDict roleDict2 : menuList) {
				if (roleDict2.getMenuId()==groupMenu.getMenuId() 
						&& roleDict2.getDataId().equals(groupRole.getDataId())) {
					flag=false;
				}
			}
			if (flag==false) {						
				roleDao.deleteAuthorizeRole(roleDict);
			}
			
		}
		
		} catch (Exception e) {
			message="修改失败";
			logService.addLog("保存时发生异常");
			System.out.println(e);
		}
		return message;
	}

}
