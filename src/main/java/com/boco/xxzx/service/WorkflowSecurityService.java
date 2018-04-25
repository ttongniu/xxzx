package com.boco.xxzx.service;

import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

import com.boco.xxzx.vo.WfGroupVO;
/**
 * 
 * @ClassName:  WorkflowSecurityService   
 * @Description:TODO(权限管理服务接口)   
 * @author: niutongtong  
 * @date:   2017年9月27日 上午11:16:19   
 *
 */
public interface WorkflowSecurityService {
	       /**
	        * 
	        * <p>Title: saveWorkflowGroup</p>   
	        * <p>Description: TODO(部署流程定义)</p>  
	        * @param: @param wfGroupVO      
	        * @return: void      
	        *
	        */
           void saveWorkflowGroup(WfGroupVO wfGroupVO );
           
           /**
            * 
            * <p>Title: getWfGroupbyId</p>   
            * <p>Description:查找权限组信息 </p>  
            * @param: @param wfGroupVO
            * @param: @return      
            * @return: boolean      
            *
            */
           boolean  getWfGroupbyId(WfGroupVO wfGroupVO );
          
           /**
            * 
            * <p>Title: listWfGroup</p>   
            * <p>Description:权限组列表信息 </p>  
            * @param: @return      
            * @return: List<Group>      
            *
            */
           List<Group>  listWfGroup();   
           
           /**
            * 
            * <p>Title: deleteWfGroupbyId</p>   
            * <p>Description:删除权限组信息 </p>  
            * @param: @param id      
            * @return: void      
            *
            */
           void  deleteWfGroupbyId(String  id);
          
           /**
            * 
            * <p>Title: getWfGroupbyId</p>   
            * <p>Description:查找权限组信息通过ID </p>  
            * @param: @param id
            * @param: @return      
            * @return: Group      
            *
            */
           Group   getWfGroupbyId(String  id);
          
           /**
            * 
            * <p>Title: listUser</p>   
            * <p>Description:用户列表 </p>  
            * @param: @return      
            * @return: List<User>      
            *
            */
           List<User>  listUser();
          
           /**
            * 
            * <p>Title: saveWorkflowUser</p>   
            * <p>Description: 保存用户信息</p>  
            * @param: @param id
            * @param: @param email      
            * @return: void      
            *
            */
		   void saveWorkflowUser(String id, String email);    
		  
		   /**
		    * 
		    * <p>Title: saveMemberShip</p>   
		    * <p>Description:添加权限关联 </p>  
		    * @param: @param userIds
		    * @param: @param groupId      
		    * @return: void      
		    *
		    */
		   
		   void saveMemberShip(String[] userIds,String groupId);
		   
		   /**
		    * 
		    * <p>Title: deleteMemberShip</p>   
		    * <p>Description:删除权限关联 </p>  
		    * @param: @param groupId      
		    * @return: void      
		    *
		    */
		   void deleteMemberShip(String groupId);
		   
		   /**
		    * 
		    * <p>Title: listUserNotInShip</p>   
		    * <p>Description:未赋权用户 </p>  
		    * @param: @param groupId
		    * @param: @return      
		    * @return: List<com.boco.xxzx.model.User>      
		    *
		    */
		   List<com.boco.xxzx.model.User> listUserNotInShip(String groupId);
		  
		   /**
		    * 
		    * <p>Title: listUserInShip</p>   
		    * <p>Description:已赋权用户 </p>  
		    * @param: @param groupId
		    * @param: @return      
		    * @return: List<com.boco.xxzx.model.User>      
		    *
		    */
		   List<com.boco.xxzx.model.User> listUserInShip(String groupId);
}
