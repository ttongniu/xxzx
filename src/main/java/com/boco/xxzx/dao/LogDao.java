package com.boco.xxzx.dao;

import java.util.List;
import java.util.Map;

import com.boco.xxzx.model.Log;

public interface LogDao {
	public void addLog(Log log);
	public List<Log> selectLog(Map map);
	public List<Log> selectLogCurrent();
}
	