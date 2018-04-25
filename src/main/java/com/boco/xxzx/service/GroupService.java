package com.boco.xxzx.service;

import java.util.List;

import com.boco.xxzx.model.Dictionary;
import com.boco.xxzx.model.GroupMenu;
import com.boco.xxzx.model.GroupRole;
/**
 * @author liushaoqing
 * @date 创建时间：2017年10月26日 下午4:37:13
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface GroupService {
	/**
	 * 
	 * <p>Title: selectGroup</p>   
	 * <p>Description: TODO(数据搜索方法)</p>  
	 * @param: @param Group
	 * @param: @return      
	 * @return:List
	 *
	 */
	public List<Dictionary> selectGroup(Dictionary group);
	/**
	 * 
	 * <p>Title: addGroup</p>   
	 * <p>Description: TODO(数据添加方法)</p>  
	 * @param: @param Group
	 * @param: @return      
	 * @return:Integer  
	 *
	 */
	//public Integer addGroup(Group group);
	/**
	 * 
	 * <p>Title: modifyGroup</p>   
	 * <p>Description: TODO(数据修改方法)</p>  
	 * @param: @param Group
	 * @param: @return      
	 * @return:Integer  
	 *
	 */
	//public Integer modifyGroup(Group group);
	/**
	 * 
	 * <p>Title: delGroup</p>   
	 * <p>Description: TODO(数据删除方法)</p>  
	 * @param: @param Integer
	 * @param: @return      
	 * @return:Integer  
	 *
	 */
	//public Integer delGroup(Integer groupId);
	/**
	 * 
	 * <p>Title: delGroup</p>   
	 * <p>Description: TODO(数据删除时删除相关信息)</p>  
	 * @param: @param Integer
	 * @param: @return      
	 * @return:Integer  
	 *
	 */
	public Integer delGroupNode(Integer groupId);
	/**
	 * 
	 * <p>Title: addGroupRole</p>   
	 * <p>Description: TODO(添加角色组的数据信息)</p>  
	 * @param: @param GroupRole
	 * @param: @return      
	 * String	  
	 *
	 */
	public String addGroupRole(Integer roleId,Integer dataId,Integer groupId);
	/**
	 * 
	 * <p>Title: deleteGroupRole</p>   
	 * <p>Description: TODO(删除角色组的数据信息)</p>  
	 * @param: @param GroupRole
	 * @param: @return      
	 * String	  
	 *
	 */
	public String deleteGroupRole(Integer roleId,Integer dataId,Integer groupId);
	/**
	 * 
	 * <p>Title: deleteGroupMenu</p>   
	 * <p>Description: TODO(删除角色组的功能信息)</p>  
	 * @param: @param GroupId
	 * @param: @return      
	 * String  
	 *
	 */
	public String deleteGroupMenu(Integer menuId,Integer groupId);
	/**
	 * 
	 * <p>Title: selectGroupRoleById</p>   
	 * <p>Description: TODO(按id查询授权信息)</p>  
	 * @param: @param GroupId
	 * @param: @return      
	 * @return:List  
	 *
	 */
	public List<GroupRole> selectGroupRoleById(Integer groupId);
	/**
	 * 
	 * <p>Title: selectGroupMenuById</p>   
	 * <p>Description: TODO(按id查询授权信息)</p>  
	 * @param: @param GroupId
	 * @param: @return      
	 * @return:List  
	 *
	 */
	public List<GroupMenu> selectGroupMenuById(Integer groupId);
	/**
	 * 
	 * <p>Title: addGroupMenu</p>   
	 * <p>Description: TODO(添加角色组的功能信息)</p>  
	 * @param: @param groupMenu
	 * @param: @return      
	 * Integer  
	 *
	 */
	public String addGroupMenu(Integer menuId,Integer groupId);


}
