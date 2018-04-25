package workflow;

import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.boco.xxzx.model.User;
import com.boco.xxzx.service.WorkflowSecurityService;
import com.boco.xxzx.service.WorkflowService;

import common.BaseTest;

public class WorkflowTest extends BaseTest{
	    @Autowired
	    private  WorkflowSecurityService workflowSecurityService;
	    @Autowired
	    private  WorkflowService  workflowService;
	    
	    @Autowired
		private RuntimeService runtimeService;//运行时服务
	    
	    @Autowired
		private TaskService taskService;//任务服务
	    
	    @Test
	    public void test05(){
	    	Task task =workflowService.saveStartProcessReturnTask("LeaveBill", "005");
	        System.out.println("******"+task.getId());
	    }
	    
	    
	    @Test
	    public void   test01(){
	    	 String id="会签";
	        System.out.println(JSON.toJSONString(workflowSecurityService.getWfGroupbyId(id)));	
	    }
	    /**
         * 启动一个请假流程
         */
        @Test
        public void start(){
         	//流程定义的key  
            String processDefinitionKey = "test01"; 
            //与正在执行的流程实例和执行对象相关的Service
            ProcessInstance pi =runtimeService.startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动  
            System.err.println("流程实例ID:"+pi.getId());//流程实例ID      
            System.err.println("流程定义ID:"+pi.getProcessDefinitionId());//流程定义ID   
        }
        @Test
        public void Savestart(){
        	 workflowService.saveStartProcess("test01", "001");
        }   
        /*@Test
        public void todotest(){
            List<Task>   tasks=	workflowService.listTODO();
            for(Task task:tasks){
            	System.err.println("*****"+task.getId());
            	System.err.println("*****"+task.getAssignee());
            }
        }*/
        @Test
        public void completeTask(){
        	taskService.complete("92506");
        	//taskService.complete(taskId, variables);
        	System.out.println("**********92506完成任务**");
        }
        @Test
        public void outcome(){
        	 List<String>  outcome  =  workflowService.findOutComeListByTaskId("75002");
        	 System.err.println("*****************"+JSON.toJSONString(outcome));
        }
        
        @Test
        public void xyz(){
        	 Map<String , Object>   codes = workflowService.findCoordingByTask("75002");
        	 System.err.println("****"+JSON.toJSONString(codes));
        }
        @Test
        public void compeleteTaskByoutcome(){
        	String taskId="175003";
        	String outcome="归档了啊！！！";
        	String message="归档";
        	String userId="ntt06";
        	String assignee="ntt07";
        	workflowService.completeTaskByoutcome(taskId, outcome, message, userId, assignee);
        	System.out.println("*******"+taskId+"任务完成！！！");
        }
        @Test
        public void findCommentByTaskId(){
        	List<Comment>  comments= workflowService.findCommentByTaskId("175003");
        	System.out.println("********"+JSON.toJSONString(comments));
        	for(Comment comment:comments){
        		System.err.println(comment.getTaskId());
        		System.err.println(comment.getUserId());
        		System.err.println(comment.getTime());
        		System.err.println(comment.getFullMessage());
        		System.err.println("**********=========");
        	}
        }
      /*  @Test
        public void haveTodo(){
        	String assginee="ntt";
        	List<HistoricTaskInstance> instances =   workflowService.listHaveTODO(assginee);
        	System.out.println("********"+JSON.toJSONString(instances));
        }   */   
        
        @Test
        public void savemaship(){
        	String[]  userids={"1","2"};
        	workflowSecurityService.saveMemberShip(userids, "会签");
        }
        
        @Test
        public void listin(){
        	List<User> users =  workflowSecurityService.listUserInShip("会签");
        	System.out.println(JSON.toJSONString(users));
        }
        @Test
        public void listnotin(){
        	List<User> users =  workflowSecurityService.listUserNotInShip("会签");
        	System.out.println(JSON.toJSONString(users));
        }
        
}
