package workflow;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Comment;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.boco.xxzx.controller.LeaveBillController;
import com.boco.xxzx.dao.LeaveBillDao;
import com.boco.xxzx.model.LeaveBill;
import com.boco.xxzx.model.User;
import com.boco.xxzx.service.LeaveBillService;
import com.boco.xxzx.service.WorkflowSecurityService;
import com.boco.xxzx.service.WorkflowService;
import com.boco.xxzx.utils.constant.Constant;

import common.BaseTest;

public class Mytest01 extends BaseTest {
	    Logger logger=Logger.getLogger(this.getClass());
	     @Autowired
	     private LeaveBillDao  leaveBillDao;
	     @Autowired
	     private LeaveBillService leaveBillService;
	     @Autowired
	     private  WorkflowSecurityService workflowSecurityService;
	     
	     @Autowired
	     private WorkflowService  workflowService;
	  
	     
	    LeaveBillController billController=new LeaveBillController();
	    
	   /* @Test
	     public  void listLeaveBillByParameter(){
	    	 System.out.println(JSON.toJSONString(leaveBillService.listLeaveBillByParameter("1", "1", "2017-10-27", "2017-10-27")));
	     }*/
	    @Test
	    public  void  findLeaveBillByHisTaskId(){
	    	LeaveBill leaveBill = workflowService.findLeaveBillByHisTaskId("57540");
	    	System.out.println("*****"+JSON.toJSONString(leaveBill));
	    }
	    
	    @Test
	    public void findCommentByHisTaskId(){
	    	List<Comment> commentList = workflowService.findCommentByHisTaskId("57540");
	    	System.out.println(JSON.toJSONString(commentList));
	    }
	    
	    
	   /* @Test
	    public void  haveToDO (){
	    	String assginee="ntt";
	    	List<HistoricTaskInstance> instances  =	workflowService.listHaveTODO(assginee);
	    	System.out.println(JSON.toJSONString(instances));
	    	for(HistoricTaskInstance instance:instances){
	    		System.out.println(instance.getAssignee());
	    		System.out.println(instance.getCategory());
	    		System.out.println(instance.getId());
	    		System.out.println(instance.getName());
	    		System.out.println(instance.getCreateTime());
	    		System.out.println(instance.getStartTime());
	    		System.out.println(instance.getEndTime());
	    		System.out.println(instance.getWorkTimeInMillis());
	    		System.out.println(instance.getClaimTime());
	    		System.out.println("********************");
	    	}
	    }*/
	    
	     @Test
	     public void saveMemberShip(){
	    	String[] userIds={"2","3"};
	    	String groupId="会签";
	     workflowSecurityService.saveMemberShip(userIds, groupId);
	     System.err.println("**********");
	     }
	     
	     @Test
	     public void deteteMemberShip(){
	    	String groupId="会签";
	     workflowSecurityService.deleteMemberShip(groupId);
	     System.err.println("**********");
	     }
	     
	     @Test
	    public  void  listnotin(){
	    	String groupId="会签";
	    	List<User> users=	workflowSecurityService.listUserNotInShip(groupId);
	    	System.err.println(JSON.toJSONString(users));
	    } 
	    @Test
	    public  void  listin(){
	    	String groupId="会签";
	    	List<User> users=	workflowSecurityService.listUserInShip(groupId);
	    	System.err.println(JSON.toJSONString(users));
	    }
	    @Test
	    public void getLeaveBillById(){
	    	long  id=3L;
	        LeaveBill leaveBill = leaveBillDao.getLeaveBillById(id);
	        System.out.println(JSON.toJSONString(leaveBill) );
	    }
	    @Test
	    public void getLeaveBillByIds(){
	  
	        LeaveBill leaveBill = leaveBillService.getLeaveBillById("3");
	        System.out.println(JSON.toJSONString(leaveBill) );
	    }
	    
	    @Test
	    public void insertLeaveBill(){
	    	User user=new User();
	        user.setId(2);
	        LeaveBill leaveBill=new LeaveBill();
	        leaveBill.setDays(6);
	        leaveBill.setContent("ffffffff1");
	        leaveBill.setRemark("gggggg2");
	        leaveBill.setState("0");
	        leaveBill.setUser(user);
	        leaveBillDao.insertLeaveBill(leaveBill);
	        
	    }
	    
	    @Test
	    public void updateLeaveBill(){
	        LeaveBill leaveBill=new LeaveBill();
	        leaveBill.setId(6L);
	        leaveBill.setDays(6);
	        leaveBill.setContent("ffffffff1");
	        leaveBill.setRemark("gggggg2");
	        leaveBill.setState("5");
	        leaveBillDao.updateLeaveBill(leaveBill);
	        
	    }
	    
	    @Test
	    public void deleteLeaveBill(){
	        Long id=6L;
	        leaveBillDao.deleteLeaveBillById(id);
	    }
	   /* @Test
	    public void listLeaveBill(){
	     List<LeaveBill> leaveBills = leaveBillDao.listLeaveBill();
	     System.out.println(JSON.toJSONString(leaveBills));
	    }*/
	    @Test
	    public void update(){
	    	  Map<String,Object> map=new HashMap<String, Object>();
	    	  map.put("state",Constant.FormKey.PROCESS_FLOW );
	    	  map.put("id", 3L);
	    	  leaveBillDao.updateLeaveBillState(map);
	    	  System.out.println("***********");
	    }
	    @Test
	    public void updateservice(){
	    	leaveBillService.updateLeaveBillState("3", Constant.FormKey.PROCESS_FILE);
	    	System.out.println("*************");
	    }
	    @Test 
	    public void viewHisComment(){
	    	String id="21";
	    	LeaveBill leaveBill =  leaveBillService.getLeaveBillById(id);
	   //model.addAttribute("leaveBill", leaveBill);
	    	logger.info("***"+JSON.toJSONString(leaveBill));
	    	//获取对象的名称
			String objectName = leaveBill.getClass().getSimpleName();
			//组织流程表中的字段中的值
			String objId = objectName+"."+id;
	    	List<Comment> commentList =	workflowService.findCommentByObjId(objId);
	    //	model.addAttribute("commentList", commentList);
	    	logger.info("****"+JSON.toJSONString(commentList));
	    }
	    @Test
	    public void findLeaveBillByTaskId(){
	       //LeaveBill leaveBill = workflowService.findLeaveBillByTaskId();
	    //	List<String> outComeList=  workflowService.findOutComeListByTaskId("22517");
	    	List<Comment> commentList = workflowService.findCommentByTaskId("22517");
	    	System.out.println("*************"+JSON.toJSONString(commentList));
	    }
}
