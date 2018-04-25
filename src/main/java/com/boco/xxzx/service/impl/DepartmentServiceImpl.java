package com.boco.xxzx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.xxzx.dao.DepartmentDao;
import com.boco.xxzx.model.Department;
import com.boco.xxzx.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;

	public List<Department> selectDept(Department department) {
		return departmentDao.selectDept(department);
	}

	public Department showDeptInfo(int depId) {
		return departmentDao.showDeptInfo(depId);
	}
	
	public Integer modifyDept(Department department) {
		return departmentDao.modifyDept(department);
	}

	public Integer addDept(Department department) {
		return departmentDao.addDept(department);
	}

	public Integer delDept(Integer depId) {
		return departmentDao.delDept(depId);
	}

}
