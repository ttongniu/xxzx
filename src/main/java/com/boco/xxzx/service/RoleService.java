package com.boco.xxzx.service;

import java.util.List;

import com.boco.xxzx.model.Dictionary;
import com.boco.xxzx.model.RoleDict;
/**
 * @author liushaoqing
 * @date 创建时间：2017年10月26日 下午4:37:13
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface RoleService {
	/**
	 * 
	 * <p>Title: selectRole</p>   
	 * <p>Description: TODO(角色搜索方法)</p>  
	 * @param: @param Role
	 * @param: @return      
	 * @return:List
	 *
	 */
	public List<Dictionary> selectRole(Dictionary role);
	/**
	 * 
	 * <p>Title: addRole</p>   
	 * <p>Description: TODO(角色添加方法)</p>  
	 * @param: @param Role
	 * @param: @return      
	 * @return:Integer  
	 *
	 */
	//public Integer addRole(Dictionary role);
	/**
	 * 
	 * <p>Title: modifyRole</p>   
	 * <p>Description: TODO(角色修改方法)</p>  
	 * @param: @param Role
	 * @param: @return      
	 * @return:Integer  
	 *
	 */
	//public Integer modifyRole(Dictionary role);
	/**
	 * 
	 * <p>Title: delRole</p>   
	 * <p>Description: TODO(角色删除同时删除用户授权中的关联信息)</p>  
	 * @param: @param Integer
	 * @param: @return      
	 * @return:Integer  
	 *
	 */
	public Integer delRoleNode(Integer roleId);
	/**
	 * 
	 * <p>Title: delRole</p>   
	 * <p>Description: TODO(角色删除方法)</p>  
	 * @param: @param Integer
	 * @param: @return      
	 * @return:Integer  
	 *
	 */
	//public Integer delRole(Integer roleId);
	/**
	 * 
	 * <p>
	 * Title: authorizeRole
	 * </p>
	 * <p>
	 * Description: TODO(角色授权的方法)
	 * </p>
	 * 
	 * @param: @param RoleDict
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public String authorizeRole(String[] cur_choose,Integer roleId);
	/**
	 * 
	 * <p>
	 * Title: authorizeRole
	 * </p>
	 * <p>
	 * Description: TODO(查询已拥有的角色授权信息)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return: List
	 *
	 */
	public List<RoleDict> selectAuthorize(Integer roleId);
	/**
	 * 
	 * <p>
	 * Title: deleteAuthorize
	 * </p>
	 * <p>
	 * Description: TODO(查询已拥有的授权信息)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public Integer deleteAuthorize(Integer roleId);
	/**
	 * 
	 * <p>
	 * Title: selectUnauthorized
	 * </p>
	 * <p>
	 * Description: TODO(查询已拥有的授权信息)
	 * </p>
	 * 
	 * @param: @param String
	 * @param: @return
	 * @return: List
	 *
	 */
	public List<Dictionary> selectUnauthorized(String employeeId);
}
