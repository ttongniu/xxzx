package com.boco.xxzx.dao;

import java.util.List;

import com.boco.xxzx.model.User;
import com.boco.xxzx.model.UserAndRole;

/**
 * 
 * @ClassName: UserDao
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: niutongtong
 * @date: 2017年10月17日 下午8:04:31
 *
 */
public interface UserDao {
	/**
	 * 
	 * <p>
	 * Title: login
	 * </p>
	 * <p>
	 * Description: TODO(这里用一句话描述这个方法的作用)
	 * </p>
	 * 
	 * @param: @param user
	 * @param: @return
	 * @return: List<User>
	 *
	 */
	public List<User> login(User user);

	/**
	 * 
	 * <p>
	 * Title: addUser
	 * </p>
	 * <p>
	 * Description: TODO(用户添加方法)
	 * </p>
	 * 
	 * @param: @param User
	 * @param: @return
	 * @return:Integer
	 *
	 */
	public Integer addUser(User user);

	/**
	 * 
	 * <p>
	 * Title: delUser
	 * </p>
	 * <p>
	 * Description: TODO(用户删除方法)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return:Integer
	 *
	 */
	public Integer delUser(Integer id);

	/**
	 * 
	 * <p>
	 * Title: modifyUser
	 * </p>
	 * <p>
	 * Description: TODO(用户修改方法)
	 * </p>
	 * 
	 * @param: @param User
	 * @param: @return
	 * @return:Integer
	 *
	 */
	public Integer modifyUser(User user);

	/**
	 * 
	 * <p>
	 * Title: selectUser
	 * </p>
	 * <p>
	 * Description: TODO(用户搜索方法)
	 * </p>
	 * 
	 * @param: @param User
	 * @param: @return
	 * @return:List
	 *
	 */
	public List<User> selectUser(User user);
	/**
	 * 
	 * <p>
	 * Title: selectUserWithRole
	 * </p>
	 * <p>
	 * Description: TODO(用户搜索方法，包括查询用户的角色，用于session)
	 * </p>
	 * 
	 * @param: @param User
	 * @param: @return
	 * @return:List
	 *
	 */
	public List<User> selectUserWithRole(User user);

	/**
	 * 
	 * <p>
	 * Title: addUser
	 * </p>
	 * <p>
	 * Description: TODO(变更用户状态)
	 * </p>
	 * 
	 * @param: @param User
	 * @param: @return
	 * @return:Integer
	 *
	 */
	public Integer changeStatus(User user);

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
	public Integer authorizeUser(UserAndRole userAndRole);

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
	public List<UserAndRole> selectAuthorize(String employeeId);
	/**
	 * 
	 * <p>
	 * Title: delUserAndRole
	 * </p>
	 * <p>
	 * Description: TODO(根据员工用户名来删除授权信息)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public Integer delUserAndRole(String employeeId);

	/**
	 * 
	 * <p>
	 * Title: listUserNotInShip
	 * </p>
	 * <p>
	 * Description: TODO(未赋权)
	 * </p>
	 * 
	 * @param: @param groupId
	 * @param: @return
	 * @return: List<User>
	 *
	 */
	List<User> listUserNotInShip(String groupId);

	/**
	 * 
	 * <p>
	 * Title: listUserInShip
	 * </p>
	 * <p>
	 * Description: TODO(已赋权)
	 * </p>
	 * 
	 * @param: @param groupId
	 * @param: @return
	 * @return: List<User>
	 *
	 */
	List<User> listUserInShip(String groupId);
	  /**
	   * 
	   * <p>Title: getUserByUserId</p>   
	   * <p>Description: (获取用户信息通过id)</p>  
	   * @param: @param id
	   * @param: @return      
	   * @return: User      
	   *
	   */
	  public User  getUserByUserId(Integer id);
	  /**
	   * 
	   * <p>Title: selectCriteriaUser</p>   
	   * <p>Description: (获取用户信息:条件查询)</p>  
	   * @param: @param String
	   * @param: @return      
	   * @return: List      
	   *
	   */
	public List<User> selectCriteriaUser(String condi);
	  /**
	   * 
	   * <p>Title: selectCriteriaUser</p>   
	   * <p>Description: (获取用户信息:根据id查询)</p>  
	   * @param: @param String
	   * @param: @return      
	   * @return: User      
	   *
	   */
	public User selectUserById(Integer id);
	 /**
	   * 
	   * <p>Title: selectCriteriaUser</p>   
	   * <p>Description: (获取用户信息:根据id查询)</p>  
	   * @param: @param User
	   * @param: @return      
	   * @return: Integer      
	   *
	   */

	public Integer modifyUserPwd(User user);
	/**
	   * 
	   * <p>Title: selectCriteriaUser</p>   
	   * <p>Description: (获取用户信息:个人信息修改)</p>  
	   * @param: @param User
	   * @param: @return      
	   * @return: User      
	   *
	   */
	public Integer modifyPersonal(User user);
	
}
