package com.boco.xxzx.dao;

import java.util.List;

import com.boco.xxzx.model.ActReDeployment;

public interface ActReDeploymentDao {
	  /**
	   * 
	   * <p>Title: listActReDeployment</p>   
	   * <p>Description: (已部署的流程)</p>  
	   * @param: @return      
	   * @return: List<ActReDeployment>      
	   *
	   */
	  List<ActReDeployment>  listActReDeployment();
	  
}
