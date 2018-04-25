package com.boco.xxzx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boco.xxzx.model.Log;
import com.boco.xxzx.service.LogService;

@Controller
@RequestMapping("/log")
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@RequestMapping("log")
	public ModelAndView getLogs(String username,String startTime,String endTime) throws Exception{
		ModelAndView view=new ModelAndView("log/log_list");
		String userName="";
		Date startDate=null;
		Date endDate=null;
		if (!"".equals(username)&&username!=null) {
			userName=username;
		}
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		if (!"".equals(startTime)&&startTime!=null) {
			try {
				startDate=format.parse(startTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!"".equals(endTime)&&endTime!=null) {
			try {
				endDate=format.parse(endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<Log> logList=logService.selectLog(userName, startDate, endDate);
		for (int i = 0; i < logList.size(); i++) {
			Log log=logList.get(i);
			SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String operatorTime= format2.format(log.getOperatorTime());
			log.setOperatorDate(operatorTime);
			logList.set(i, log);
		}
		view.addObject("logs", logList);
		view.addObject("username", username);
		view.addObject("sdate", startTime);
		view.addObject("edate", endTime);
		logService.addLog("查看日志信息");
		return view;
	}
	
	@RequestMapping("logCurrent")
	public ModelAndView getLogsCurrent(){
		ModelAndView view=new ModelAndView("log/log_list");
		List<Log> logList=logService.selectLogCurrent();
		for (int i = 0; i < logList.size(); i++) {
			Log log=logList.get(i);
			SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String operatorTime= format2.format(log.getOperatorTime());
			log.setOperatorDate(operatorTime);
			logList.set(i, log);
		}
		view.addObject("logs", logList);
		logService.addLog("查看日志信息");
		return view;
	}
	
}
