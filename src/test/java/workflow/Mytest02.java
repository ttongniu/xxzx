package workflow;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.boco.xxzx.service.LogService;
import com.boco.xxzx.utils.session.SessionContext;

import common.BaseTest;

public class Mytest02 extends BaseTest{
	
	@Autowired
	private LogService logService;
	@Test
	public void log(){
		//logService.addLog("3","水电费附件的撒发生的");
		System.out.println("**********");
	}
	
     @Test  
	public void   getCurrUser(){
		System.out.println("******"+ JSON.toJSONString(SessionContext.getCurrentUser()));  
	  }
     
     
     
}
