package com.boco.xxzx.dao;
import java.util.List;
import java.util.Map;

import com.boco.xxzx.model.LeaveBill;
/**
 * 
 * @ClassName:  LeaveBillDao   
 * @Description:TODO(请假单接口)   
 * @author: niutongtong  
 * @date:   2017年10月30日 下午4:48:38   
 *
 */
public interface LeaveBillDao {
	/**
	 * 
	 * <p>Title: listLeaveBillByParameter</p>   
	 * <p>Description: TODO(请假单列表多条件查询)</p>  
	 * @param: @param map
	 * @param: @return      
	 * @return: List<LeaveBill>      
	 *
	 */
	 public List<LeaveBill> listLeaveBillByParameter(Map<String,Object> map);
	 /**
	  * 
	  * <p>Title: listLeaveBill</p>   
	  * <p>Description: TODO(请假单列表)</p>  
	  * @param: @return      
	  * @return: List<LeaveBill>      
	  *
	  */
	 public List<LeaveBill> listLeaveBill(Long userId);
	 /**
	  * 
	  * <p>Title: getLeaveBillById</p>   
	  * <p>Description: TODO(根据id查询单个请假单)</p>  
	  * @param: @param id
	  * @param: @return      
	  * @return: LeaveBill      
	  *
	  */
	 public LeaveBill   getLeaveBillById(Long id);
	 /**
	  * 
	  * <p>Title: insertLeaveBill</p>   
	  * <p>Description: TODO(保存请假单)</p>  
	  * @param: @param leaveBill      
	  * @return: void      
	  *
	  */
	 public  void  insertLeaveBill(LeaveBill leaveBill);
	 /**
	  * 
	  * <p>Title: updateLeaveBill</p>   
	  * <p>Description: TODO(更新请假单)</p>  
	  * @param: @param leaveBill      
	  * @return: void      
	  *
	  */
	 public  void  updateLeaveBill(LeaveBill leaveBill);
	 /**
	  * 
	  * <p>Title: deleteLeaveBillById</p>   
	  * <p>Description: TODO(通过id删除请假单)</p>  
	  * @param: @param id      
	  * @return: void      
	  *
	  */
	 public  void  deleteLeaveBillById(Long id);
	 /**
	  * 
	  * <p>Title: updateLeaveBillState</p>   
	  * <p>Description: TODO(更新状态)</p>  
	  * @param: @param map      
	  * @return: void      
	  *
	  */
	 public  void  updateLeaveBillState(Map<String, Object> map);
}
