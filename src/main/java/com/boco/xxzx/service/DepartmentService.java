package com.boco.xxzx.service;

import java.util.List;

import com.boco.xxzx.model.Department;
/**
 * @author liushaoqing
 * @date 创建时间：2017年10月26日 下午4:37:13
 * @version 1.0
 * @parameter
 * @since
 * @return
 */

public interface DepartmentService {
	/**
	 * 
	 * <p>Title: selectDept</p>   
	 * <p>Description: TODO(查询部门)</p>  
	 * @param: @param Department 
	 * @param: @return      
	 * @return:List
	 *
	 */
	public List<Department> selectDept(Department department);
	/**
	 * 
	 * <p>Title: showDeptInfo</p>   
	 * <p>Description: TODO(展示部门的详细信息)</p>  
	 * @param: @param Integer
	 * @param: @return      
	 * @return:Department 
	 *
	 */
	public Department showDeptInfo(int depId);
	/**
	 * 
	 * <p>Title: addDept</p>   
	 * <p>Description: TODO(部门添加方法)</p>  
	 * @param: @param Department
	 * @param: @return      
	 * @return:Integer  
	 *
	 */
	public Integer addDept(Department department);
	/**
	 * 
	 * <p>Title: modifyDept</p>   
	 * <p>Description: TODO(部门修改方法)</p>  
	 * @param: @param Department
	 * @param: @return      
	 * @return:Integer  
	 *
	 */
	public Integer modifyDept(Department department);
	/**
	 * 
	 * <p>Title: delDept</p>   
	 * <p>Description: TODO(部门删除方法)</p>  
	 * @param: @param Integer
	 * @param: @return      
	 * @return:Integer  
	 *
	 */
	public Integer delDept(Integer depId);


}
