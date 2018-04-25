package com.boco.xxzx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.xxzx.dao.ActHiTaskinstDao;
import com.boco.xxzx.dao.UserDao;
import com.boco.xxzx.model.ActHiTaskinst;
import com.boco.xxzx.model.User;
import com.boco.xxzx.service.ActHiTaskinstService;
@Service("actHiTaskinstService")
public class ActHiTaskinstServiceImpl implements ActHiTaskinstService{

    @Autowired
	private ActHiTaskinstDao actHiTaskinstDao;
    @Autowired
    private UserDao  userDao;
    public List<String> listProcInstIdUnFinished(String assignee,String id,String beginDate,String endDate) {
		Map<String ,Object> map=new HashMap<String, Object>();
		map.put("assignee", assignee);
		map.put("id", id);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		return actHiTaskinstDao.listProcInstIdUnFinished(map);
	}

	public List<String> listProcInstIdFinished(String assignee,String id,String beginDate,String endDate) {
		Map<String ,Object> map=new HashMap<String, Object>();
		map.put("assignee", assignee);
		map.put("id", id);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		return actHiTaskinstDao.listProcInstIdFinished(map);
	}

	
	
	public ActHiTaskinst getTaskinst(String procInstId) {
		
		return actHiTaskinstDao.getTaskinst(procInstId);
	}

	public List<ActHiTaskinst> listTaskinst(String procInstId, String assignee) {
		Map<String,String>  map=new HashMap<String, String>();
		map.put("procInstId", procInstId);
		map.put("assignee",assignee);
		return actHiTaskinstDao.listTaskinst(map);
	}

	public ActHiTaskinst getTaskinstState(String procInstId) {
		ActHiTaskinst actHiTaskinst = new ActHiTaskinst();
		List<ActHiTaskinst>  actHiTaskinstList=actHiTaskinstDao.listTaskinstState(procInstId);
		String assignee="";
		for(ActHiTaskinst acthiTaskinst:actHiTaskinstList){
			User user = userDao.getUserByUserId(Integer.valueOf(acthiTaskinst.getAssignee()));  
			assignee += user.getEmployeeName()+",";
		}
		actHiTaskinst.setName(actHiTaskinstList.get(0).getName());
		actHiTaskinst.setAssignee(assignee.substring(0, assignee.length()-1));
		return actHiTaskinst;
	}

}
