<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ page import="java.text.*,java.util.Date"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--引入自定义css  -->
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css">
<!--引入jQuery.js  -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<!--时间插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td height="30"><div id='KB1Parent' class='parent'><img src="${pageContext.request.contextPath}/images/sy_text_2.gif" width="257" height="25"></div></td>
    </tr>
    <tr> 
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
  </table>
  <form  id="form1" name="form1" method="post"    action="">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    
    <tr>
            <td>  
    <SPAN align="left">
        
    <font color="blue">请输入 申请时间 的时间段：</font><strong>从</strong>

    <c:if test="${not empty sdate }">
        <input id="sdate"  name="sdate" class="Wdate" value="${sdate }" size="10" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'edate\')}'})" />  
    </c:if>
    <c:if test="${empty sdate  }">
        <input id="sdate"  name="sdate" class="Wdate" value="2017-01-01" size="10" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'edate\')}'})" />  
    </c:if>
——&nbsp;<strong>到</strong>
  <c:if test="${not empty edate }">
   <input id="edate" name="edate" class="Wdate" value="${edate }" size="10" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'sdate\')}',startDate:'#F{$dp.$D(\'sdate\',{d:+1})}'})" />  
  </c:if>
  <c:if test="${empty edate }">
  <% Date log_d=new Date();
  String log_year   = Integer.toString(log_d.getYear()+1900);
  int log_imonth  = log_d.getMonth()+1;
  int log_iday    = log_d.getDate();
  String log_month  = "";
  String log_day    = "";

  if (log_imonth<10)
    {log_month  = "0" + Integer.toString(log_d.getMonth()+1);}
  else
    {log_month  = Integer.toString(log_d.getMonth()+1);}

  if (log_iday<10)
    {log_day    = "0" + Integer.toString(log_d.getDate());}
  else
    {log_day    = Integer.toString(log_d.getDate());}

  String log_date   = log_year +"-"+ log_month +"-"+ log_day;   
  %>
  <input id="edate" name="edate" class="Wdate" value="<%=log_date%> " size="10" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'sdate\')}',startDate:'#F{$dp.$D(\'sdate\',{d:+1})}'})" />   
  </c:if>
——&nbsp;<font color="blue">流程名称：</font> 
                   <c:choose>
              <c:when test="${empty id }">
                 <select name="id" id="id">
			            <c:forEach  items="${actReDeployments }" var="actReDeployment" >
			            <option value="${actReDeployment.id }" <c:if test="${actReDeployment.id== '1' }"> selected </c:if>  >${actReDeployment.name }</option>
			            </c:forEach>
		          </select>
              </c:when>
              <c:otherwise>
                   <select name="id" id="id">
			            <c:forEach  items="${actReDeployments }" var="actReDeployment" >
			            <option value="${actReDeployment.id }" <c:if test="${actReDeployment.id==id }"> selected </c:if> >${actReDeployment.name }</option>
			            </c:forEach>
		            </select>
              </c:otherwise>
           </c:choose>
</SPAN>
       &nbsp;&nbsp; <!-- <input name="Submit" type="submit"  value="GO"> -->
       <img  title="查找" onclick="search();"  src="${pageContext.request.contextPath}/images/find.png" width="20" height="20" >
      </td>
    </tr>
    <tr>
      <td >&nbsp;</td>       
    </tr>
  </table>
</form>
<table width="100%" class="data_table" cellpadding="0" cellspacing="0">
  <tr bgcolor="#ECF0F4"> 
    <td class="data_table_title"><div align="center">序号</div></td>
    <td class="data_table_title" width="15%" height="20"><div align="center">流程申请人</div></td>
    <td class="data_table_title" width="15%"><div align="center">流程名称</div></td>
    <td class="data_table_title" width="20%"><div align="center">当前状态</div></td>
    <td class="data_table_title" width="20%"><div align="center">申请时间</div></td>
    <td class="data_table_title" width="20%"><div align="center">操作</div></td>
  </tr>
    
    <c:choose>
    <c:when test="${ not empty  taskInstances }">
         <c:forEach items="${taskInstances }"  var="taskInstance" varStatus="status">
		  <tr bgcolor="#FFFFFF"> 
		    <td class="data_table_content_left"><div align="center">${status.count }</div></td>
		    <td class="data_table_content_left" width="15%" height="20"><div align="center">${taskInstance.proposer.employeeName }</div></td>
			<td class="data_table_content_left" width="15%" height="20"><div align="center">${taskInstance.name }</div></td>
		    <td class="data_table_content_left" width="20%"><div align="center"> 已归档 </div></td>
		    <td class="data_table_content_left" width="20%"><div align="center"><fmt:formatDate value="${taskInstance.startTime }" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" /> </div></td>
		    <td class="data_table_content_left" width="20%">
		    <div align="center">
		        <a  target="_blank" href="${pageContext.request.contextPath }/wf/listAction.do?taskId=${taskInstance.id }"><img alt="流程执行过程"  title="流程执行过程" src="${pageContext.request.contextPath}/images/executionProcess.png" width="25" height="25" ></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  <c:if  test="${taskInstance.procDefkey=='LeaveBill' }">  
			    <a href="${pageContext.request.contextPath }/leaveBill/leaveBillviewHisTask.do?taskId=${taskInstance.id }"><img alt="查看历史批注"  title="查看历史批注" src="${pageContext.request.contextPath}/images/viewProcessAnnotation.png" width="25" height="25" ></a>
		      </c:if>
		    </div>
		    </td>
		  </tr>
         </c:forEach>
    </c:when>
    <c:otherwise>
          <tr bgcolor="#FFFFFF"> 
		    <td class="data_table_content_left" colspan="9" height="20" >
		       <div align="center">
		         <b>暂无任务！ </b>
		       </div>
		   </td>
		  </tr>
    </c:otherwise>
    </c:choose>
</table>

<script type="text/javascript">
		$("#id").change(function(){
			//alert("***");
			 document.forms['form1'].action="${pageContext.request.contextPath}/wf/listFinishedHaveTODO.do";
			 document.forms['form1'].submit();
		  });
		function search(){
			 document.forms['form1'].action="${pageContext.request.contextPath}/wf/listFinishedHaveTODO.do";
			 document.forms['form1'].submit();
		}
</script>


</body>
</html>