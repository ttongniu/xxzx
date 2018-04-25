<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
<center side="font_title">  <font color="blue" > 请假单添加</font>
  <hr align="center" width="600" size="1" noshade="noshade" color="#336699" />

<form id="form1" name="form1" method="post" action="${pageContext.request.contextPath}/leaveBill/saveLeaveBill.do">
<table width="800" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#003366">

  <tr>
    <td width="30%" align="right" bgcolor="#FFFFFF">请假天数：</td>
    
    <td width="70%"  bgcolor="#FFFFFF">
      <input size="50" type="text" name="days" id="days" />
      <span id="result"> </span>
    </td>
    
  </tr>
  <tr>
    <td width="30%" align="right" bgcolor="#FFFFFF">请假事由：</td>
    
    <td width="70%"  bgcolor="#FFFFFF">
      <input size="50" type="text" name="content" id="content"  />
      <span id="result"> </span>
    </td>
    
  </tr>
  <tr>
    <td  align="right" bgcolor="#FFFFFF">备注：</td>
    <td bgcolor="#FFFFFF"> <textarea rows="4" cols="40" id="remark" name="remark"></textarea> </td>
  </tr>
 
</table>

<fieldset>
<legend>流程审批</legend>
         <input   type="hidden" id="outCome" name="outCome" value="${groupName}">
        <table cellpadding="0" cellspacing="8" align="center">
            <tr>
              <td  align="right" bgcolor="#FFFFFF"> <font color="blue">当前审批操作：</font></td>
              <td bgcolor="#FFFFFF" colspan="2"> 申请人</td>
            
            </tr>
	         <tr>
              <td  align="right" bgcolor="#FFFFFF"><font color="blue">下一步审批操作：</font></td>
              <td bgcolor="#FFFFFF" colspan="2">
                  ${groupName}
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
			 <tr>
    <td colspan="3" align="center" bgcolor="#FFFFFF">
    <input type="button" name="but1" id="but1" value="保存&提交" /> &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="submit" name="button" id="button" value="保存&草稿" /> &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" name="button" id="button" onclick="javascript:history.go(-1)" value="返回" />
    </td>
  </tr>
	</c:if>
		</table>       
</fieldset>
</center>
</form>
<script type="text/javascript"> 
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

	 $("#but1").click(
		       function  (){
		    	   var flag=true;
		    	   var  sel=  document.getElementById("s2");
		    	   var comment=$("#comment").val();
		    	   if(comment==''){
		    		   alert("审批意见不能为空！！！");
			    		  flag=false;
			          $("#comment").focus();
		    	   }
		    	  if(sel.options.length==0){
		    		  alert("至少选一个！！");
		    		  flag=false;
		    		  return;
		    	  }else{
		    		  for(var i=0;i<sel.options.length;i++){
		    	   	    	sel.options[i].selected=true;
		    	   	   }
		    	  }
		    	 
		    	  var msg = "任务将要提交！！！\n请确认！";
		    		if (confirm(msg)==true){
		    			flag=true;
		    		}else{
		    			flag=false;
		    		}
		    	  
		    		 if(flag){
		    			 var url = "${pageContext.request.contextPath}/leaveBill/submitLeaveBillTask.do";  
		    			 //更改form的action  
		    			 $("#form1").attr("action", url); 
		    			 $("#form1").submit();
		    			 $("#but3").attr("disabled","disabled");
		    		 }
		    	  
		       }		
		    ); 
	
</script>


</body>
</html>