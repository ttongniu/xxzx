package com.boco.xxzx.service;

import java.util.List;

import com.boco.xxzx.model.Menu;

/**
 * @author liushaoqing
 * @date 创建时间：2017年10月26日 下午4:44:33
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface MenuService {
	/**
	 * 
	 * <p>
	 * Title: selectMenu
	 * </p>
	 * <p>
	 * Description: TODO(查询菜单)
	 * </p>
	 * 
	 * @param: @param Menu
	 * @param: @return
	 * @return:List
	 *
	 */
	public List<Menu> selectMenu(Menu menu);

	/**
	 * 
	 * <p>
	 * Title: showMenuInfo
	 * </p>
	 * <p>
	 * Description: TODO(展示菜单的详细信息)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return:Menu
	 *
	 */
	public Menu showMenuInfo(int menuId);

	/**
	 * 
	 * <p>
	 * Title: addMenu
	 * </p>
	 * <p>
	 * Description: TODO(菜单添加方法)
	 * </p>
	 * 
	 * @param: @param Menu
	 * @param: @return
	 * @return:Integer
	 *
	 */
	public Integer addMenu(Menu menu);

	/**
	 * 
	 * <p>
	 * Title: modifyMenu
	 * </p>
	 * <p>
	 * Description: TODO(菜单修改方法)
	 * </p>
	 * 
	 * @param: @param Menu
	 * @param: @return
	 * @return:Integer
	 *
	 */
	public Integer modifyMenu(Menu menu);

	/**
	 * 
	 * <p>
	 * Title: delMenu
	 * </p>
	 * <p>
	 * Description: TODO(菜单删除方法)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return:Integer
	 *
	 */
	public Integer delMenu(Integer menuId);
	/**
	 * 
	 * <p>
	 * Title: delMenu
	 * </p>
	 * <p>
	 * Description: TODO(菜单删除时同时删除授权中的关联信息)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return:Integer
	 *
	 */
	public Integer delMenuNode(Integer menuId);
}
