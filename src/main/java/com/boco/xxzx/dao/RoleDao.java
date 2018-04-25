package com.boco.xxzx.dao;

import java.util.List;

import com.boco.xxzx.model.Dictionary;
import com.boco.xxzx.model.RoleDict;
/**
 * @author liushaoqing
 * @date 创建时间：2017年10月26日 下午5:04:59
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface RoleDao {
	/**
	 * 
	 * <p>
	 * Title: selectRole
	 * </p>
	 * <p>
	 * Description: TODO(角色搜索的方法)
	 * </p>
	 * 
	 * @param: @param Role
	 * @param: @return
	 * @return: List<User>
	 *
	 */
	public List<Dictionary> selectRole(Dictionary role);
	/**
	 * 
	 * <p>
	 * Title: addRole
	 * </p>
	 * <p>
	 * Description: TODO(角色添加的方法)
	 * </p>
	 * 
	 * @param: @param Role
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public Integer addRole(Dictionary role);
	/**
	 * 
	 * <p>
	 * Title: modifyRole
	 * </p>
	 * <p>
	 * Description: TODO(角色修改的方法)
	 * </p>
	 * 
	 * @param: @param Role
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public Integer modifyRole(Dictionary role);
	/**
	 * 
	 * <p>
	 * Title: delRole
	 * </p>
	 * <p>
	 * Description: TODO(角色删除的方法)
	 * </p>
	 * 
	 * @param: @param Role
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public Integer delRole(Integer roleId);
	/**
	 * 
	 * <p>
	 * Title: delRoleNode
	 * </p>
	 * <p>
	 * Description: TODO(角色删除同时删除与之关联的user与role的对应关系)
	 * </p>
	 * 
	 * @param: @param integer
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public Integer delRoleNode(Integer roleId);
	/**
	 * 
	 * <p>
	 * Title: authorizeRole
	 * </p>
	 * <p>
	 * Description: TODO(授权方法)
	 * </p>
	 * 
	 * @param: @param RoleDict
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public Integer authorizeRole(RoleDict roleDict);
	/**
	 * 
	 * <p>
	 * Title: selectAuthorize
	 * </p>
	 * <p>
	 * Description: TODO(查询已拥有的授权信息)
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
	/**
	 * 
	 * <p>
	 * Title: deleteAuthorize
	 * </p>
	 * <p>
	 * Description: TODO(查询已拥有的授权信息)
	 * </p>
	 * 
	 * @param: @param roleDict
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public void deleteAuthorizeRole(RoleDict roleDict);


}
