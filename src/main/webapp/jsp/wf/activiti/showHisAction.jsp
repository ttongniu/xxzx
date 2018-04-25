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

</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td height="30"><div id='KB1Parent' class='parent'><img src="${pageContext.request.contextPath}/images/historyexe.png" width="257" height="25"></div></td>
    </tr>
    <tr> 
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
</table>
  
  <br><br><br>
<center>
<fieldset>
<legend>历史流程执行过程</legend>
<%-- <table width="100%" class="data_table" cellpadding="0" cellspacing="0">
	  <tr bgcolor="#ECF0F4"> 
	    <td class="data_table_title"><div align="center">序号</div></td>
		<td class="data_table_title" width="25%" height="20"><div align="center">任务节点ID</div></td>
	    <td class="data_table_title" width="25%"><div align="center">任务节点名称</div></td>
	    <td class="data_table_title" width="20%"><div align="center">开始时间</div></td>
	    <td class="data_table_title" width="20%"><div align="center">结束时间</div></td>
	  </tr>
	  <c:choose>
	      <c:when test="${not empty activityInstances}">
	       <c:forEach  items="${activityInstances }" var="activityInstance" varStatus="status">
		   <tr bgcolor="#FFFFFF"> 
			   <td class="data_table_content_left"><div align="center">${status.count }</div></td>
			   <td class="data_table_content_left" width="25%" height="20"><div align="center"> ${activityInstance.activityId }  </div></td>
			   <td class="data_table_content_left" width="25%"><div align="center">${activityInstance.activityName }</div></td>
			   <td class="data_table_content_left" width="20%"><div align="center"><fmt:formatDate value="${activityInstance.startTime }" dateStyle="long"  type="date" pattern="yyyy-MM-dd HH:mm:ss"  /> </div></td>
			   <td class="data_table_content_left" width="20%"><div align="center"><fmt:formatDate value="${activityInstance.endTime }" dateStyle="long"  type="date" pattern="yyyy-MM-dd HH:mm:ss"  /> </div></td>
		   </tr> 
		   </c:forEach> 
	      </c:when>
	      <c:otherwise>
	      <tr bgcolor="#FFFFFF"> 
			  <td colspan="5" height="20"> <div align="center"><b>暂时没有执行过程</b></div></td>
		   </tr> 
	      </c:otherwise>
	  </c:choose> 
</table> --%>
<div>
   <ul>
   <c:choose>
	      <c:when test="${not empty activityInstances}">
	       <c:forEach  items="${activityInstances }" var="activityInstance" varStatus="status">
              <li style="size:14px/1.5; font-family: Helvetica Neue;text-align: left; margin: 10px">
                                                    执行任务节点的名称：<font color="blue"> ${activityInstance.activityName }</font> -- 执行时间：<fmt:formatDate value="${activityInstance.startTime }" dateStyle="long"  type="date" pattern="yyyy-MM-dd HH:mm:ss"  />
              </li>
             <span></span>
             </c:forEach> 
	      </c:when>
	      <c:otherwise>
	      <li>暂时没有执行过程</li>
	     </c:otherwise>
	  </c:choose> 
   </ul>
 </div>
</fieldset>
</center>
<script type="text/javascript">

 

</script>


</body>
</html>