package com.boco.xxzx.service;

import java.util.Date;
import java.util.List;

import com.boco.xxzx.model.Log;

public interface LogService {
	public void addLog(String operatorName);
	
	public List<Log> selectLog(String userName,Date startDate,Date endStart);

	public List<Log> selectLogCurrent();
}
