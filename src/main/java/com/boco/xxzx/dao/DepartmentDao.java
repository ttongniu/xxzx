package com.boco.xxzx.dao;

import java.util.List;

import com.boco.xxzx.model.Department;
/**
 * @author liushaoqing
 * @date 创建时间：2017年10月26日 下午5:03:55
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface DepartmentDao {
	/**
	 * 
	 * <p>
	 * Title: selectDept
	 * </p>
	 * <p>
	 * Description: TODO(部门添加的方法)
	 * </p>
	 * 
	 * @param: @param Role
	 * @param: @return
	 * @return: List
	 *
	 */
	public List<Department> selectDept(Department department);

	/**
	 * 
	 * <p>
	 * Title: showDeptInfo
	 * </p>
	 * <p>
	 * Description: TODO(部门展示的方法)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return: Department
	 *
	 */
	public Department showDeptInfo(int depId);

	/**
	 * 
	 * <p>
	 * Title: addDept
	 * </p>
	 * <p>
	 * Description: TODO(部门添加的方法)
	 * </p>
	 * 
	 * @param: @param Department
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public Integer addDept(Department department);

	/**
	 * 
	 * <p>
	 * Title: modifyDept
	 * </p>
	 * <p>
	 * Description: TODO(部门修改的方法)
	 * </p>
	 * 
	 * @param: @param Department
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public Integer modifyDept(Department department);

	/**
	 * 
	 * <p>
	 * Title: delDept
	 * </p>
	 * <p>
	 * Description: TODO(部门删除的方法)
	 * </p>
	 * 
	 * @param: @param Integer
	 * @param: @return
	 * @return: Integer
	 *
	 */
	public Integer delDept(Integer depId);

}
