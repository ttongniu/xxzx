package com.boco.xxzx.service;

import java.util.List;

import com.boco.xxzx.model.LeaveBill;
/**
 * 
 * @ClassName:  LeaveBillService   
 * @Description:TODO(请假单服务类)   
 * @author: niutongtong  
 * @date:   2017年10月30日 下午4:57:17   
 *
 */
public interface LeaveBillService {
	 public   List<LeaveBill>  listLeaveBillByParameter(String id,String state,String beginDate,String endDate,Long userId);
	 
	 public List<LeaveBill> listLeaveBill(Long userId);  
	 
	 public LeaveBill   getLeaveBillById(String id);
	 
	 public  void  insertLeaveBill(LeaveBill leaveBill);
	 
	 public  LeaveBill  updateLeaveBill(LeaveBill leaveBill);
	 
	 public  void  deleteLeaveBillById(String id);
	 
	 public  void  updateLeaveBillState(String id,String state);
}
