package com.boco.xxzx.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.xxzx.dao.LogDao;
import com.boco.xxzx.model.Log;
import com.boco.xxzx.service.LogService;
import com.boco.xxzx.utils.session.SessionContext;

@Service("logService")
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao logDao;
	
	public void addLog(String operatorName) {
		Log log=new Log();
		if (SessionContext.getCurrentUser()!=null) {		
			log.setUser(SessionContext.getCurrentUser());
			log.setOperatorName(operatorName);
			InetAddress address=null;
			try {
				address=InetAddress.getLocalHost();
				String ips=address.getHostAddress();
				log.setOperatorIP(ips);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			logDao.addLog(log);
		}
	}

	public List<Log> selectLog(String userName,Date startDate,Date endStart) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userName", "%"+userName+"%");
		map.put("startDate", startDate);
		map.put("endStart", endStart);
		return logDao.selectLog(map);
	}

	public List<Log> selectLogCurrent() {
		return logDao.selectLogCurrent();
	}

}
