package com.boco.xxzx.dao;


import java.util.Map;

/**
 * 
 * @ClassName:  MemberShipDao   
 * @Description:TODO(权限组赋权)   
 * @author: niutongtong  
 * @date:   2017年10月18日 下午5:20:25   
 *
 */
public interface MemberShipDao {
	 /**
	    * 
	    * <p>Title: deleteMemberShip</p>   
	    * <p>Description:删除权限关联 </p>  
	    * @param: @param groupId      
	    * @return: void      
	    *
	    */
	   void deleteMemberShip(String groupId);
	   
	   /**
	    * 
	    * <p>Title: saveMemberShip</p>   
	    * <p>Description:添加权限关联 </p>  
	    * @param: @param userId
	    * @param: @param groupId      
	    * @return: void      
	    *
	    */
	   
	   void saveMemberShip(Map<String,Object> map);
	   
	  
}
