package com.boco.xxzx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.xxzx.dao.LeaveBillDao;
import com.boco.xxzx.model.LeaveBill;
import com.boco.xxzx.service.LeaveBillService;
@Service("leaveBillService")
public class LeaveBillServiceImpl implements LeaveBillService{
     @Autowired
     private LeaveBillDao  leaveBillDao;
	public LeaveBill getLeaveBillById(String id) {
		Long lid=Long.valueOf(id);
		return leaveBillDao.getLeaveBillById(lid);
	}

	public void insertLeaveBill(LeaveBill leaveBill) {
		leaveBillDao.insertLeaveBill(leaveBill);
	}

	public LeaveBill updateLeaveBill(LeaveBill leaveBill) {
		leaveBillDao.updateLeaveBill(leaveBill);
		return leaveBill;
	}

	public void deleteLeaveBillById(String id) {
		Long lid=Long.valueOf(id);
		leaveBillDao.deleteLeaveBillById(lid);
	}
	public List<LeaveBill> listLeaveBill(Long id) {
		return leaveBillDao.listLeaveBill(id);
	}

	public void updateLeaveBillState(String id, String state) {
		Map<String , Object> map=new HashMap<String , Object>(16);
		map.put("id", id);
		map.put("state", state);
		leaveBillDao.updateLeaveBillState(map);
	}

	public List<LeaveBill> listLeaveBillByParameter(String id, String state, String beginDate, String endDate,Long userId) {
		Map<String , Object> map=new HashMap<String , Object>(16);
		map.put("id", id);
		map.put("state", state);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("userId", userId);
		return leaveBillDao.listLeaveBillByParameter(map);
	}	
	
}
