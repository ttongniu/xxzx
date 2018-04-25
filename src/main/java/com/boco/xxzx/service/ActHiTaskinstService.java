package com.boco.xxzx.service;

import java.util.List;
import com.boco.xxzx.model.ActHiTaskinst;

public interface ActHiTaskinstService {
	/** 
	 *  
	 * <p>Title: listProcInstIdUnFinished</p>   
	 * <p>Description: (已办流转--实例列表)</p>  
	 * @param: @param assignee
	 * @param: @return      
	 * @return: List<String>      
	 *
	 */
	List<String>   listProcInstIdUnFinished(String assignee,String id,String beginDate,String endDate);
	
	/**
	 * 
	 * <p>Title: listProcInstIdFinished</p>   
	 * <p>Description: (已办归档--实例列表)</p>  
	 * @param: @param assignee
	 * @param: @return      
	 * @return: List<String>      
	 *
	 */
	List<String>   listProcInstIdFinished(String assignee,String id,String beginDate,String endDate);    	
	
	
	
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
	 List<ActHiTaskinst>   listTaskinst(String  procInstId ,String assignee);
	 /**
	  * 
	  * <p>Title: getTaskinstState</p>   
	  * <p>Description: (已办流程 现在状态)</p>  
	  * @param: @param procInstId
	  * @param: @return      
	  * @return: ActHiTaskinst      
	  *
	  */
	 ActHiTaskinst    getTaskinstState(String procInstId);
}
