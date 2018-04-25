package com.boco.xxzx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.xxzx.dao.MemberShipDao;
import com.boco.xxzx.dao.UserDao;
import com.boco.xxzx.service.WorkflowSecurityService;
import com.boco.xxzx.vo.WfGroupVO;

/**
 * 
 * @ClassName: WorkflowSecurityServiceImpl
 * @Description:TODO(流程权限服务方法实现)
 * @author: niutongtong
 * @date: 2017年9月27日 上午10:18:17
 *
 */
@Service("workflowSecurityService")
public class WorkflowSecurityServiceImpl implements WorkflowSecurityService {
	// 用户和用户组的支持
	@Autowired
	private IdentityService identityService;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MemberShipDao memberShipDao;

	public void saveWorkflowGroup(WfGroupVO wfGroupVO) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setId(wfGroupVO.getId());
		groupEntity.setType(wfGroupVO.getType());
		groupEntity.setName(wfGroupVO.getName());
		identityService.saveGroup(groupEntity);

	}

	public boolean getWfGroupbyId(WfGroupVO wfGroupVO) {
		boolean flag = true;// 默认无
		Group group = identityService.createGroupQuery()//
				                     .groupId(wfGroupVO.getId())//
				                     .singleResult();
		if (group != null) {
			flag = false;
		}
		return flag;
	}

	public List<Group> listWfGroup() {
		List<Group> groups = identityService.createGroupQuery()//
				                            .list();
		return groups;
	}

	public void deleteWfGroupbyId(String id) {
		memberShipDao.deleteMemberShip(id);
		identityService.deleteGroup(id);
	}

	public Group getWfGroupbyId(String id) {
		return identityService.createGroupQuery()//
				              .groupId(id)//
				              .singleResult();
	}

	public List<User> listUser() {
		List<User> users = identityService.createUserQuery()//
				                          .list();

		return users;
	}

	public void saveWorkflowUser(String id, String email) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(id);
		userEntity.setEmail(email);
		identityService.saveUser(userEntity);

	}

	public void saveMemberShip(String[] userIds, String groupId) {
		for (String userId : userIds) {
		Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId", userId);
        map.put("groupId", groupId);
        memberShipDao.saveMemberShip(map);
		}
		/* 再添加 
		for (String userId : userIds) {
			identityService.createMembership(userId, groupId);
		}*/

	}

	public List<com.boco.xxzx.model.User> listUserNotInShip(String groupId) {

		return userDao.listUserNotInShip(groupId);
	}

	public List<com.boco.xxzx.model.User> listUserInShip(String groupId) {

		return userDao.listUserInShip(groupId);
	}

	public void deleteMemberShip(String groupId) {
		
		memberShipDao.deleteMemberShip(groupId);
		/*// 查询
		List<User> users = identityService.createUserQuery()//
				.memberOfGroup(groupId)//
				.list();
		// 删除
		for (User user : users) {
			identityService.deleteMembership(user.getId(), groupId);
		}*/
	}

}
