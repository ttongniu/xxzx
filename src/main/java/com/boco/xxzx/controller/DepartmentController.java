package com.boco.xxzx.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;






import com.boco.xxzx.model.Department;
import com.boco.xxzx.service.DepartmentService;
import com.boco.xxzx.service.LogService;
import com.boco.xxzx.utils.session.SessionContext;
/**
 * @author liushaoqing
 * @date 创建时间：2017年10月26日 下午5:35:23
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private LogService logService;
	/**
	 * 
	 * <p>
	 * Title: selectDept
	 * </p>
	 * <p>
	 * Description: TODO(部门查询方法)
	 * </p>
	 * 
	 * @param: @param Department
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/selectDept")
	public ModelAndView selectDept(Department department){
		ModelAndView mav=new ModelAndView();
		List<Department> depList=departmentService.selectDept(department);
		mav.addObject("depList", depList);
		return mav;
	}
	/**
	 * 
	 * <p>
	 * Title: showDept
	 * </p>
	 * <p>
	 * Description: TODO(部門展示方法)
	 * </p>
	 * 
	 * @param: @param Department
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/showDept")
	public ModelAndView showDept(Department department){
		ModelAndView mav=new ModelAndView("organization/department/dep_list");
		List<Department> depList=departmentService.selectDept(department);
		mav.addObject("depList", depList);
		return mav;
	}
	/**
	 * 
	 * <p>
	 * Title: toModifyDept
	 * </p>
	 * <p>
	 * Description: TODO(跳转修改页面)
	 * </p>
	 * 
	 * @param: @param Department
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/toModifyDept")
	public ModelAndView toModifyDept(Department department){
		ModelAndView mav=new ModelAndView("organization/department/dep_modify");
		department.setFlag(1);
		List<Department> depList=departmentService.selectDept(department);
		mav.addObject("depList", depList);
		return mav;
	}
	/**
	 * 
	 * <p>
	 * Title: showDeptInfo
	 * </p>
	 * <p>
	 * Description: TODO(展示部门的详细信息)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/showDeptInfo")
	public ModelAndView showDeptInfo(int depId){
		ModelAndView mav=new ModelAndView();
		Department department=departmentService.showDeptInfo(depId);
		mav.addObject("department", department);
		mav.setViewName("organization/department/dep_info");
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
	 * @param: @param Department
	 * @param: @return
	 * @return:ModelAndView
	 *
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd (Department department) {
		ModelAndView mav=new ModelAndView("organization/department/dep_add");
		mav.addObject("department", department);
		return mav;		
	}
	/**
	 * 
	 * <p>
	 * Title: addDepartment
	 * </p>
	 * <p>
	 * Description: TODO(添加部门方法)
	 * </p>
	 * 
	 * @param: @param Department
	 * @param: @return
	 * @return:String
	 *
	 */
	@RequestMapping(value="/addDept",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addDept(Department department) {
		String message="";
		department.setCreateTime(new Date());
		if (department.getFlag()==null) {
			department.setFlag(0);
		}
		Integer result=departmentService.addDept(department);
		if (result!=null) {
			message="添加成功";
			logService.addLog("部门"+department.getDepName()+"添加成功");
		}else {
			message="添加失败";
			logService.addLog("部门"+department.getDepName()+"添加失败");
		}
		return message;	
	}
	/**
	 * 
	 * <p>
	 * Title: modifyDepartment
	 * </p>
	 * <p>
	 * Description: TODO(修改部门方法)
	 * </p>
	 * 
	 * @param: @param Department
	 * @param: @return
	 * @return:String
	 *
	 */
	@RequestMapping(value="/modifyDept",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String modifyDept(Department department) {
		String message="";
		String  eId= SessionContext.getCurrentUser().getEmployeeId();
		department.setModifyTime(new Date());
		department.setModifier(eId);
		department.setModifyTime(new Date());
		if (department.getFlag()==null) {
			department.setFlag(0);
		}
		Integer result=departmentService.modifyDept(department);
		if (result!=null) {
			message="修改成功";
			logService.addLog("部门"+department.getDepName()+"修改成功");
		}else {
			message="修改失败";
			logService.addLog("部门"+department.getDepName()+"修改失败");
		}
		return message;	
	}
	/**
	 * 
	 * <p>
	 * Title: deleteDepartment
	 * </p>
	 * <p>
	 * Description: TODO(删除)
	 * </p>
	 * 
	 * @param: @param Department
	 * @param: @return
	 * @return:String
	 */
	@RequestMapping(value="/delDept",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delDept (Integer depId) {
		String message="";
		try {
			Integer result=departmentService.delDept(depId);
			if (result!=null) {
				message="删除成功";
				logService.addLog("部门"+depId+"删除成功");
			}else {
				message="删除失败";
				logService.addLog("部门"+depId+"删除失败");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			message="删除时出错";
			logService.addLog("部门"+depId+"删除时系统报错");
		}
		return message;	
	}
	

}
