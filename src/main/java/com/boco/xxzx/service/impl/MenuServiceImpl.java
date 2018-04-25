package com.boco.xxzx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.xxzx.dao.MenuDao;
import com.boco.xxzx.model.Menu;
import com.boco.xxzx.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;

	public List<Menu> selectMenu(Menu menu) {
		return menuDao.selectMenu(menu);
	}

	public Menu showMenuInfo(int menuId) {
		return menuDao.showMenuInfo(menuId);
	}
	
	public Integer modifyMenu(Menu menu) {
		return menuDao.modifyMenu(menu);
	}

	public Integer addMenu(Menu menu) {
		return menuDao.addMenu(menu);
	}

	public Integer delMenu(Integer menuId) {
		return menuDao.delMenu(menuId);
	}
	public Integer delMenuNode(Integer menuId) {
		return menuDao.delMenuNode(menuId);
	}
}
