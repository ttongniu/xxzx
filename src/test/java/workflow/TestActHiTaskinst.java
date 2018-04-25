package workflow;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.boco.xxzx.service.ActHiTaskinstService;
import com.boco.xxzx.service.ActReDeploymentService;

import common.BaseTest;

public class TestActHiTaskinst  extends BaseTest {
       
	  @Autowired
	  private  ActHiTaskinstService actHiTaskinstService;
	  @Autowired
	  private  ActReDeploymentService   actReDeploymentService;
	  
	  /* @Test 
	  public  void test01(){
		   System.out.println("*****"+JSON.toJSONString(actHiTaskinstService.listProcInstIdFinished("3")));
	   }*/
	   
	   @Test 
	  public  void test01(){
		   System.out.println("*****"+JSON.toJSONString(actHiTaskinstService.listProcInstIdFinished("3","请假","2017-11-23","2017-11-27")));
	   }
	   
	  /* @Test 
	  public  void test02(){
		   System.out.println("*****"+JSON.toJSONString(actHiTaskinstService.listProcInstIdUnFinished("3")));
	   }*/
	   
	   @Test 
	  public  void test02(){
		   System.out.println("*****"+JSON.toJSONString(actHiTaskinstService.listProcInstIdUnFinished("3","请假","2017-11-27","2017-11-27")));
	   }
	   
	   @Test 
	  public  void test03(){
		   System.out.println("*****"+JSON.toJSONString(actHiTaskinstService.getTaskinst("5001")));
	   }  
	   
	   @Test 
	  public  void test04(){
		   System.out.println("*****"+JSON.toJSONString(actHiTaskinstService.listTaskinst("5001", "3")));
	   }  
		   
	   @Test 
	  public  void test05(){
		   System.out.println("*****"+JSON.toJSONString(actHiTaskinstService.getTaskinstState("5001")));
	   }  
	   
	   @Test 
	  public  void test06(){
		   System.out.println("*****"+JSON.toJSONString(actReDeploymentService.listActReDeployment()));
	   }  
	   
}
