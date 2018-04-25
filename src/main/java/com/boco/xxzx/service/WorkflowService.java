package com.boco.xxzx.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import com.boco.xxzx.model.LeaveBill;
import com.boco.xxzx.vo.MyTaskVO;

/**
 * 
 * @ClassName:  WorkflowService   
 * @Description:TODO(流程服务接口)   
 * @author: niutongtong  
 * @date:   2017年9月28日 下午4:27:24   
 *
 */
public interface WorkflowService {
	/**
	 * 
	 * <p>Title: saveNewDeploye</p>   
	 * <p>Description: TODO(部署流程定义)</p>  
	 * @param: @param file
	 * @param: @param filename      
	 * @return: void      
	 *
	 */
     
	void saveNewDeploye(File file, String filename);
	/**
	 * 
	 * <p>Title: listDeployment</p>   
	 * <p>Description:查询部署对象信息，对应表（act_re_deployment ）</p>  
	 * @param: @return      
	 * @return: List<Deployment>      
	 *
	 */
	List<Deployment> listDeployment();
    /**
     * 
     * <p>Title: listProcessDefinition</p>   
     * <p>Description: 查询流程定义的信息，对应表（act_re_procdef）</p>  
     * @param: @return      
     * @return: List<ProcessDefinition>      
     *
     */
	List<ProcessDefinition> listProcessDefinition();
	/**
	 * 
	 * <p>Title: getImageInputStream</p>   
	 * <p>Description:使用部署对象ID和资源图片名称，获取图片的输入流 </p>  
	 * @param: @param deploymentId
	 * @param: @param imageName
	 * @param: @return      
	 * @return: InputStream      
	 *
	 */
	InputStream getImageInputStream(String deploymentId, String imageName);
   /**
    * 
    * <p>Title: deleteProcessDefinitionByDeploymentId</p>   
    * <p>Description:使用部署对象ID，删除流程定义 </p>  
    * @param: @param deploymentId      
    * @return: void      
    *
    */  
	void deleteProcessDefinitionByDeploymentId(String deploymentId);
	/**
	 * 
	 * <p>Title: saveStartProcess</p>   
	 * <p>Description: TODO()</p>  
	 * @param: @param key 流程定义的key
	 * @param: @param id   表单id   
	 * @return: void      
	 *
	 */
	void saveStartProcess(String key,String id);
	
	
	/**
	 * 
	 * <p>Title: saveStartProcess</p>   
	 * <p>Description: TODO()</p>  
	 * @param: @param key 流程定义的key
	 * @param: @param id   表单id   
	 * @return: void      
	 *
	 */
	Task saveStartProcessReturnTask(String key,String id);
	/**
	 * 
	 * <p>Title: listTODO</p>   
	 * <p>Description: TODO(1是指派给你的，专门的指派流程节点；2是在候选组中，符合抢签权限的人。)</p>  
	 * @param: @return      
	 * @return: List<Task>      
	 *
	 */
	List<MyTaskVO>  listTODO();
	/**
	 * 
	 * <p>Title: completeTask</p>   
	 * <p>Description: TODO(完成任务！！！)</p>  
	 * @param: @param taskId
	 * @param: @param variables      
	 * @return: void      
	 *
	 */
	public void completeTask(String taskId, Map<String,Object> variables);
	/**
	 * 
	 * <p>Title: completeTaskByoutcome</p>   
	 * <p>Description: TODO(按照连线名称完成任务)</p>  
	 * @param: @param taskId
	 * @param: @param outcome
	 * @param: @param message
	 * @param: @param userId  
	 * @param: @param assignee  
	 * @return:  processInstanceId    
	 *
	 */
	public String completeTaskByoutcome(String taskId, String outcome, String message, String userId,String assignee);
	/**
	 * 
	 * <p>Title: isProcessEnd</p>   
	 * <p>Description: TODO(判断流程任务是否归档)</p>  
	 * @param: @param taskId
	 * @param: @return      
	 * @return: boolean      
	 *
	 */
	public boolean isProcessEnd(String taskId);
	/**
	 * 
	 * <p>Title: findOutComeListByTaskId</p>   
	 * <p>Description: TODO(已知任务ID，查询processDefinitionEntity对象，
	 * 从而获取当前任务完成之后的连线名称，并放置到List<String>集合中)</p>  
	 * @param: @param taskId
	 * @param: @return      
	 * @return: List<String>      
	 *
	 */
	public   List<String>  findOutComeListByTaskId(String taskId);
	/**
	 * 
	 * <p>Title: findCoordingByTask</p>   
	 * <p>Description: TODO(二：查看当前活动，获取当期活动对应的坐标x,y,width,height，
	 *       将4个值存放到Map<String,Object>中
    		 map集合的key：表示坐标x,y,width,height
    		 map集合的value：表示坐标对应的值)</p> 
     * @param: @param taskId		  
	 * @param: @return      
	 * @return: Map<String,Object>      
	 *
	 */
	public Map<String, Object>   findCoordingByTask(String taskId);
	/**
	 * 
	 * <p>Title: findCommentByLeaveBillId</p>   
	 * <p>Description: TODO(使用请假单ID，查询历史批注信息)</p>  
	 * @param: @param objId(objectName+"."+id)
	 * @param: @return      
	 * @return: List<Comment>      
	 *
	 */
	public List<Comment> findCommentByObjId(String objId);
	/**
	 * 
	 * <p>Title: findCommentByTaskId</p>   
	 * <p>Description: TODO(使用任务 id 查找 批注信息)</p>  
	 * @param: @param taskId
	 * @param: @return      
	 * @return: List<Comment>      
	 *
	 */
	public List<Comment>   findCommentByTaskId(String taskId);
	/**
	 * 
	 * <p>Title: findCommentByTaskId</p>   
	 * <p>Description: TODO(使用历史任务 id 查找 批注信息)</p>  
	 * @param: @param taskId
	 * @param: @return      
	 * @return: List<Comment>      
	 *
	 */
	public List<Comment>   findCommentByHisTaskId(String taskId);
	/**
	 * 
	 * <p>Title: HaveTODO</p>   
	 * <p>Description: TODO(已办任务)</p>  
	 * @param: @return      
	 * @return: List<Task>      
	 *
	 */
	public List<MyTaskVO> listHaveTODO(String taskName);
	/**
	 * 
	 * <p>Title: findProcessDefinitionByTaskId</p>   
	 * <p>Description: TODO(根据任务ID查询流程定义对象)</p>  
	 * @param: @param taskId
	 * @param: @return      
	 * @return: ProcessDefinition      
	 *
	 */
    public  ProcessDefinition    findProcessDefinitionByTaskId(String taskId);
     /**
      * 
      * <p>Title: findLeaveBillByHisTaskId</p>   
      * <p>Description: TODO(根据历史任务Id关联查询表单数据)</p>  
      * @param: @param taskId
      * @param: @return      
      * @return: LeaveBill      
      *
      */
    public LeaveBill findLeaveBillByHisTaskId(String taskId);
    /**
     * 
     * <p>Title: findLeaveBillByTaskId</p>   
     * <p>Description: TODO(根据任务Id关联查询表单数据)</p>  
     * @param: @param taskId
     * @param: @return      
     * @return: LeaveBill      
     *
     */
    public  LeaveBill   findLeaveBillByTaskId(String taskId);      
	/**
	 * 
	 * <p>Title: listAction</p>   
	 * <p>Description: TODO(根据任务id查询流程实例的具体执行过程)</p>  
	 * @param: @param taskId
	 * @param: @return      
	 * @return: List<HistoricActivityInstance>      
	 *
	 */
    public    List<HistoricActivityInstance>  listAction(String taskId);
    
	public    List<MyTaskVO> listFinishedHaveTODO(String assignee, String taskName);
	
	public    List<MyTaskVO> listunFinishedHaveTODO(String assignee, String taskName);
	
	/**
	 * 
	 * <p>Title: findDeploymentList</p>   
	 * <p>Description: (这里用一句话描述这个方法的作用)</p>  
	 * @param: @return      
	 * @return: List<Deployment>      
	 *
	 */
	List<Deployment> findDeploymentList();
}
