package com.boco.xxzx.dao;

import java.util.List;
import java.util.Map;

import com.boco.xxzx.model.ActHiTaskinst;

public interface ActHiTaskinstDao {
	/**
	 *  
	 * <p>Title: listProcInstIdUnFinished</p>   
	 * <p>Description: (已办流转--实例列表)</p>  
	 * @param: @param assignee
	 * @param: @return      
	 * @return: List<String>      
	 *
	 */
	List<String>   listProcInstIdUnFinished(Map<String ,Object> map);
	
	/**
	 * 
	 * <p>Title: listProcInstIdFinished</p>   
	 * <p>Description: (已办归档--实例列表)</p>  
	 * @param: @param assignee
	 * @param: @return      
	 * @return: List<String>      
	 *
	 */
	List<String>   listProcInstIdFinished(Map<String ,Object> map);    	  
	 /**
	  *      
	  * <p>Title: getTaskinst</p>   
	  * <p>Description: (查询已办 实例  申请人和提交时间)</p>  
	  * @param: @param procInstId
	  * @param: @return      
	  * @return: ActHiTaskinst      
	  *
	  */
	 ActHiTaskinst   getTaskinst(String procInstId);
	 
	 /**
	  * 
	  * <p>Title: listTaskinst</p>   
	  * <p>Description: (已办流程名称 、现在待办人 )</p>  
	  * @param: @param map
	  * @param: @return      
	  * @return: List<ActHiTaskinst>      
	  *
	  */
	 List<ActHiTaskinst>   listTaskinst(Map<String,String> map);
	 
	 /**
	  * 
	  * <p>Title: listTaskinstState</p>   
	  * <p>Description: (已办流程 现在状态)</p>  
	  * @param: @param procInstId
	  * @param: @return      
	  * @return: List<ActHiTaskinst>      
	  *
	  */
	 List<ActHiTaskinst>    listTaskinstState(String procInstId);
	
}
