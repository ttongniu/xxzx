<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--引入自定义css  -->
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css">
<!--引入jQuery.js  -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<!--引入lab2.js  -->
<script src="${pageContext.request.contextPath}/js/lab2.js"></script>

</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td height="30"><div id='KB1Parent' class='parent'><img src="${pageContext.request.contextPath}/images/leave.png" width="257" height="25"></div></td>
    </tr>
    <tr> 
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
</table>
  
  <br><br><br>
<center side="font_title">  <font color="blue" > 请假单</font>
  <hr align="center" width="600" size="1" noshade="noshade" color="#336699" />
</center>
<center>
<fieldset>
<legend>请假单基本信息</legend>
 <form  id="form1"  action="" method="post">
<table width="800" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#003366">
   <input name="id" id="id" value="${leaveBill.id }" type="hidden"> 
  <tr> 
    <td width="30%" align="right" bgcolor="#FFFFFF">请假天数：</td>
    
    <td width="70%"  bgcolor="#FFFFFF">
      <input size="50" type="text" name="days" id="days"  ${flag }  value="${leaveBill.days }"/>
      <span id="result"> </span>
    </td>   
  </tr>
  <tr>
    <td width="30%" align="right" bgcolor="#FFFFFF">请假事由：</td>
    
    <td width="70%"  bgcolor="#FFFFFF">
      <input size="50" type="text" name="content" id="content" ${flag }  value="${leaveBill.content }" />
      <span id="result"> </span>
    </td>
    
  </tr>
 
  <tr>
    <td  align="right" bgcolor="#FFFFFF">备注：</td>
    <td bgcolor="#FFFFFF"> <textarea rows="4" cols="40" id="remark" name="remark"  ${flag } >${leaveBill.remark }</textarea> </td>
  </tr>
  </table>
</fieldset>

<fieldset>
<legend>请假单历史批注信息</legend>
<%-- <table width="80%" border="0" cellpadding="0" cellspacing="1"   align="center" bgcolor="#999999">
 
	  <tr bgcolor="#ebebf1"> 
	    <td><div align="center">序号</div></td>
		<td width="25%" height="20"><div align="center">时间</div></td>
	    <td width="25%"><div align="center">批注人</div></td>
	    <td width="40%"><div align="center">批注信息</div></td>
	  </tr>
	  <c:choose>
	      <c:when test="${not empty commentList}">
	       <c:forEach  items="${commentList }" var="comment" varStatus="status">
		   <tr bgcolor="#FFFFFF"> 
			   <td><div align="center">${status.count }</div></td>
			   <td width="25%" height="20"><div align="center"><fmt:formatDate value="${comment.time }" dateStyle="long"  type="date" pattern="yyyy-MM-dd HH:mm:ss"  /> </div></td>
			   <td width="25%"><div align="center">${comment.userId }</div></td>
			   <td width="40%"><div align="center">${comment.fullMessage }</div></td>
		   </tr> 
		   </c:forEach> 
	      </c:when>
	      <c:otherwise>
	      <tr bgcolor="#FFFFFF"> 
			  <td colspan="4" height="20"> <div align="center"><b>暂时没有批注信息</b></div></td>
		   </tr> 
	      </c:otherwise>
	  </c:choose>    
</table> --%>
   <div>
   <ul>
   <c:choose>
	      <c:when test="${not empty commentList}">
	       <c:forEach  items="${commentList }" var="comment" varStatus="status">
            <li style="size:14px/1.5; font-family: Helvetica Neue;text-align: left;margin: 10px">
            ${comment.userId } 的意见： ${comment.fullMessage }    <fmt:formatDate value="${comment.time }" dateStyle="long"  type="date" pattern="yyyy-MM-dd HH:mm:ss"  />
             </li>
           </c:forEach> 
	      </c:when>
	      <c:otherwise>
	      <li>暂时没有批注信息</li>
	     </c:otherwise>
	  </c:choose> 
   </ul>
   </div>

</fieldset>

<fieldset>
<legend>流程审批</legend>
         <input type="hidden"  id="taskId" name="taskId" value="${taskId }">
         <input type="hidden"  id="taskName" name="taskName" value="${taskName }">
        <table cellpadding="0" cellspacing="8" align="center">
            <tr>
              <td  align="right" bgcolor="#FFFFFF"> <font color="blue">当前审批操作：</font></td>
              <td bgcolor="#FFFFFF" colspan="2"> ${taskName } </td>
            </tr>
	         <tr>
              <td  align="right" bgcolor="#FFFFFF"><font color="blue">下一步审批操作：</font></td>
              <td bgcolor="#FFFFFF" colspan="2">
              <c:if test="${empty outCome }">
              <c:forEach items="${ outComeList}" var="outcome" varStatus="state">
              <input type="radio" id="outCome" name="outCome" onclick="changesp(this);"   <c:if test="${state.index==0 }">checked='checked'</c:if>    value="${outcome }">${outcome }
              </c:forEach>
              </c:if>
              <c:if test="${not empty outCome  }">
              <c:forEach items="${ outComeList}" var="outcome" varStatus="state">
              <input type="radio" id="outCome" name="outCome" onclick="changesp(this);"   <c:if test="${outCome eq outcome}">checked='checked'</c:if>    value="${outcome }">${outcome }
              </c:forEach>
              </c:if>
              </td>
            </tr>
			<tr>
              <td  align="right" bgcolor="#FFFFFF"><font color="blue">审批意见：</font></td>
              <td bgcolor="#FFFFFF" colspan="2"> <textarea rows="4" cols="40" id="comment" name="comment" ></textarea> </td>
            
            </tr>
			<c:if test="${not empty userList }">
			<tr>
				<td width="100"><font color="blue">选择审批人</font></td>
				<td width="100">&nbsp;</td>
				<td width="100"><font color="blue">已选审批人</font></td>
			</tr>
			<tr>
				<td >
				   
					<select id="s1" name="s1" style="width:150px; height:220px;" multiple="multiple" >
						<c:forEach items="${userList }" var="user">
						<option   value="${user.id }">${user.employeeName }</option>
						</c:forEach>
					</select>
				</td>
				<td align="center">
					<p><input id="b1" type="button" class="s1" value="----&gt;" /></p>
					<!-- <p><input type="button" id="b2" class="s1" value="--&gt;&gt;" /></p> -->
					<p><input type="button" id="b3" class="s1" value="&lt;----" /></p>
					<p><input type="button" id="b4" class="s1" value="&lt;&lt;--" /></p>
				</td>
				<td><select id="s2" name="s2" style="width:150px;height:220px;" multiple="multiple">
					  
				    </select>
				</td>
			</tr>
			<tr>
			<td colspan="3" align="left"><font color="blue">查找审批人：</font><input type="text"  id="findname" name="findname"> <input type="button" name="but2" id="but2"  value="查询" /></td>
			</tr>
			<tr></tr>
			</c:if>
		</table>       
</fieldset>
</form>
</center>
   <c:if test="${ not empty userList }">
     <div align="center">  <span >
       <input type="button" name="but1" id="but1" value="审批"  /> &nbsp;&nbsp;
       <input type="button" name="but" id="but"  onclick="javascript:history.go(-1)" value="返回" />
       </span>
     </div>
     </c:if>
     <c:if test="${empty userList }">
      <div align="center">  <span >
       <input type="button" name="but3" id="but3" value="审批"  /> &nbsp;&nbsp;
       <input type="button" name="but" id="but"  onclick="back(this);"  value="返回" />
       </span>
     </div>
    </c:if>
<script type="text/javascript">
      
      function  back(){
    	  window.location.href="${pageContext.request.contextPath}/wf/listTODO.do";
      }
      
   $("#but3").click(
	       function  (){
	    	   var flag=true;
	    	
	    	   var comment=$("#comment").val();
	    	   if(comment==''){
	    		   alert("审批意见不能为空！！！");
		    		  flag=false;
		          $("#comment").focus();
	    	   }
	    	   var msg = "任务流程流转！！！\n请确认！";
	    		if (confirm(msg)==true){
	    			flag=true;
	    		}else{
	    			flag=false;
	    		}
	    	   
	    		 if(flag){
	    			 var url = "${pageContext.request.contextPath}/wf/completeTask.do";  
	    			 //更改form的action  
	    			 $("#form1").attr("action", url); 
	    			 $("#form1").submit();
	    			 $("#but3").attr("disabled","disabled");
	    		 }
	    	  }
	       		
	    ); 
      
      $("#but1").click(
	       function  (){
	    	   var flag=true;
	    	   var  sel=  document.getElementById("s2");
	    	   var comment=$("#comment").val();
	    	   var outCome= $("#outCome:checked ").val()
	    	   var substr='会签';
	    	   if(comment==''){
	    		   alert("审批意见不能为空！！！");
		    		  flag=false;
		          $("#comment").focus();
	    	   }
	    	  if(sel.options.length==0){
	    		  alert("至少选一个！！");
	    		  flag=false;
	    		  return;
	    	  }if(outCome.indexOf(substr) == -1){
	    		  if(sel.options.length>=2){
		    		  alert("此步骤只可选一个！！");
		    		  flag=false;
		    		  return;
		    	  } 
	    	  }
	    		  for(var i=0;i<sel.options.length;i++){
	    	   	    	sel.options[i].selected=true;
	    	   	   
	    	  }
	    	  
	    	  var msg = "任务将要流转！！！\n请确认！";
	    		if (confirm(msg)==true){
	    			flag=true;
	    		}else{
	    			flag=false;
	    		}
	    		
	    		 if(flag){
	    			 var url = "${pageContext.request.contextPath}/wf/completeTask.do";  
	    			 //更改form的action  
	    			 $("#form1").attr("action", url); 
	    			 $("#form1").submit();
	    			 $("#but1").attr("disabled","disabled");
	    		 }
	       }		
	    ); 
           
      function   changesp(o){
    	  var url = "${pageContext.request.contextPath}/wf/toLeaveBillForm.do";  
			 //更改form的action  
			 $("#form1").attr("action", url); 
    	  $("#form1").submit();
      }
      
			var left_id=[],left_name=[];
			var flag=0;
			$("#but2").click(
					//按照名字查询
			    function (){
			    	
			    	 var  sel=  document.getElementById("s1");
			    	if(flag==0){
			    		for(var m=0;m<sel.length;m++){
			    			left_id[m]=	sel.options[m].value;
			    			left_name[m]=sel.options[m].text;
			    		}
			    		flag=1;
			    	}
			    	
			    	var findname=$("#findname").val();
			    	//alert("***"+findname);
			    	sel.length = 0;
			    	for(var i=0;i<left_id.length;i++){
			    		var no = new Option();
			            // 筛选左侧列表名单
			    		var name_left = left_name[i];
			    		 if(name_left.indexOf(findname)!=-1)
			     	    {
			                 no.value = left_id[i];
			                 no.text  = left_name[i];
			                 sel.options[sel.options.length] = no;
			     		} 
			    		 else if(findname=="")
			     		{
			                 no.value = left_id[i];
			                 no.text  = left_name[i];
			                 sel.options[sel.options.length] = no;
			     		}/**/
			    	}
			    }		
			);
</script>
</body>
</html>