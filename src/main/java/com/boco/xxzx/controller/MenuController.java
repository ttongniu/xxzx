package com.boco.xxzx.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;












import com.boco.xxzx.model.Dictionary;
import com.boco.xxzx.model.Menu;
import com.boco.xxzx.model.RoleDict;
import com.boco.xxzx.model.User;
import com.boco.xxzx.service.LogService;
import com.boco.xxzx.service.MenuService;
import com.boco.xxzx.service.RoleService;
import com.boco.xxzx.utils.session.SessionContext;


@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private LogService logService;
	/**
	 * 
	 * <p>
	 * Title: selectAllMenu
	 * </p>
	 * <p>
	 * Description: TODO(菜单查询方法)
	 * </p>
	 * 
	 * @param: @param Menu
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/selectAllMenu")
	public ModelAndView selectAllMenu(Menu menu){
		ModelAndView mav=new ModelAndView("toolbar_all");
		List<Menu> menuList=menuService.selectMenu(menu);
		mav.addObject("menuList", menuList);
		return mav;
	}
	/**
	 * 
	 * <p>
	 * Title: selectMenu
	 * </p>
	 * <p>
	 * Description: TODO(菜单查询方法)
	 * </p>
	 * 
	 * @param: @param Menu
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/selectMenu")
	public ModelAndView selectMenu(){
		//获取session中的验证码
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		List<Menu> menuList=new ArrayList<Menu>();
		List<Integer> menuIdList =new ArrayList<Integer>();
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		List<Dictionary> roleList=user.getRoleList();
		for (Dictionary role : roleList) {
			List<RoleDict> roleDictList=roleService.selectAuthorize(role.getId());
			for (RoleDict roleDict : roleDictList) {
				boolean flag=true;
				for (Integer menuId : menuIdList) {
					if (roleDict.getMenuId()==menuId) {
						flag=false;
					}
				}
				if (flag) {						
					Menu menu=menuService.showMenuInfo(roleDict.getMenuId());
					if (menu.getFlag()==1) {						
						menuIdList.add(roleDict.getMenuId());
						menuList.add(menu);
					}
				}
			}
		}	
		ModelAndView mav=new ModelAndView("toolbar_all");
		mav.addObject("menuList", menuList);
		return mav;
	}
	/**
	 * 
	 * <p>
	 * Title: showMenu
	 * </p>
	 * <p>
	 * Description: TODO(菜单展示)
	 * </p>
	 * 
	 * @param: @param Menu
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/showMenu")
	public ModelAndView showMenu(Menu menu){
		ModelAndView mav=new ModelAndView("organization/menu/menu_list");
		List<Menu> menuList=menuService.selectMenu(menu);
		mav.addObject("menuList", menuList);
		return mav;
	}
	/**
	 * 
	 * <p>
	 * Title: selectMenu
	 * </p>
	 * <p>
	 * Description: TODO(菜单修改方法)
	 * </p>
	 * 
	 * @param: @param Menu
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/toModifyMenu")
	public ModelAndView toModifyMenu(Menu menu){
		ModelAndView mav=new ModelAndView("organization/menu/menu_modify");
		List<Menu> menuList=menuService.selectMenu(menu);
		mav.addObject("menuList", menuList);
		return mav;
	}
	/**
	 * 
	 * <p>
	 * Title: showMenuInfo
	 * </p>
	 * <p>
	 * Description: TODO(菜单详细信息展示)
	 * </p>
	 * 
	 * @param: @param Menu
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/showMenuInfo")
	public ModelAndView showMenuInfo(int menuId){
		ModelAndView mav=new ModelAndView();
		Menu menu=menuService.showMenuInfo(menuId);
		mav.addObject("menu", menu);
		mav.setViewName("organization/menu/menu_info");
		return mav;
	}
	/**
	 * 
	 * <p>
	 * Title: toAdd
	 * </p>
	 * <p>
	 * Description: TODO(跳转添加页面)
	 * </p>
	 * 
	 * @param: @param Menu
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd (Menu menu) {
		ModelAndView mav=new ModelAndView("organization/menu/menu_add");
		mav.addObject("menu", menu);
		return mav;		
	}
	/**
	 * 
	 * <p>
	 * Title: addMenu
	 * </p>
	 * <p>
	 * Description: TODO(添加菜单方法)
	 * </p>
	 * 
	 * @param: @param Menu
	 * @param: @return
	 * @return:String
	 *
	 */
	@RequestMapping(value="/addMenu",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addMenu(Menu menu) {
		String message="";
		menu.setCreateTime(new Date());
		if (menu.getFlag()==null) {
			menu.setFlag(0);
		}
		Integer result=menuService.addMenu(menu);
		if (result!=0) {
			message="添加成功";
			logService.addLog("菜单"+menu.getMenuName()+"添加成功");
		}else {
			message="添加失败";
			logService.addLog("菜单"+menu.getMenuName()+"添加失败");
		}
		return message;	
	}
	/**
	 * 
	 * <p>
	 * Title: modifyMenu
	 * </p>
	 * <p>
	 * Description: TODO(修改菜单方法)
	 * </p>
	 * 
	 * @param: @param Menu
	 * @param: @return
	 * @return:String
	 *
	 */
	@RequestMapping(value="/modifyMenu",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String modifyMenu(Menu menu) {
		String message="";
		String  eId= SessionContext.getCurrentUser().getEmployeeId();
		menu.setModifyTime(new Date());
		menu.setModifier(eId);
		menu.setModifyTime(new Date());
		if (menu.getFlag()==null) {
			menu.setFlag(0);
		}
		Integer result=menuService.modifyMenu(menu);
		if (result!=0) {
			message="修改成功";
			logService.addLog("菜单"+menu.getMenuName()+"修改成功");
		}else {
			message="修改失败";
			logService.addLog("菜单"+menu.getMenuName()+"修改失败");
		}
		return message;	
	}
	/**
	 * 
	 * <p>
	 * Title: deleteMenu
	 * </p>
	 * <p>
	 * Description: TODO(删除)
	 * </p>
	 * 
	 * @param: @param Menu
	 * @param: @return
	 * @return:String
	 */
	@RequestMapping(value="/delMenu",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delMenu (Integer menuId) {
		String message="";
		try {
			Integer result=menuService.delMenuNode(menuId);
			Integer result2=menuService.delMenu(menuId);
			if (result!=null && result2!=null) {
				message="删除成功";
				logService.addLog("菜单"+menuId+"删除成功");
			}else {
				message="删除失败";
				logService.addLog("菜单"+menuId+"删除失败");
			}			
		} catch (Exception e) {
			// TODO: handle exception
			message="删除时出错";
			logService.addLog("菜单"+menuId+"删除时系统报错");
		}
		return message;	
	}
	

}

