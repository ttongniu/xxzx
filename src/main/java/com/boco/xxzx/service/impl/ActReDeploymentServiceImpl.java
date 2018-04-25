package com.boco.xxzx.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boco.xxzx.dao.ActReDeploymentDao;
import com.boco.xxzx.model.ActReDeployment;
import com.boco.xxzx.service.ActReDeploymentService;
/**
 * 
 * @ClassName:  ActReDeploymentServiceImpl   
 * @Description:(这里用一句话描述这个类的作用)   
 * @author: niutongtong  
 * @date:   2017年12月4日 下午4:04:13   
 *
 */
@Service("actReDeploymentService")
public class ActReDeploymentServiceImpl implements ActReDeploymentService {
     @Autowired
	private   ActReDeploymentDao  actReDeploymentDao; 
     
	public List<ActReDeployment> listActReDeployment() {
		return actReDeploymentDao.listActReDeployment();
	}

}
