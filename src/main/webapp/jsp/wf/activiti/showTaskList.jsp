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
      <td height="30"><div id='KB1Parent' class='parent'><img src="${pageContext.request.contextPath}/images/sy_text_1.gif" width="257" height="25"></div></td>
    </tr>
    <tr> 
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
  </table>
<%-- <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="30"><font color="#FF0000"></font>&nbsp;
        <p><img src="${pageContext.request.contextPath}/images/arrow-1.gif" width="11" height="12">&nbsp;<a href="#" class="g">任务办理列表</a></p>      </td>
    </tr>
    <tr>
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>       
    </tr>
  </table> --%>

<table width="100%" class="data_table" cellpadding="0" cellspacing="0">
  <tr bgcolor="#ECF0F4"> 
    <td class="data_table_title"><div align="center">序号</div></td>
    <td class="data_table_title" width="15%" height="20"><div align="center">流程申请人</div></td>
    <td class="data_table_title" width="15%"><div align="center">流程名称</div></td>
    <td class="data_table_title" width="15%"><div align="center">任务名称</div></td>
    <td class="data_table_title" width="15%"><div align="center">办理人</div></td>
    <td class="data_table_title" width="15%"><div align="center">创建时间</div></td>
    <td class="data_table_title" width="20%"><div align="center">操作</div></td>
  </tr>
    
    <c:choose>
    <c:when test="${ not empty  tasks }">
         <c:forEach items="${tasks }"  var="task" varStatus="status">
		  <tr bgcolor="#FFFFFF"> 
		    <td class="data_table_content_left"><div align="center">${status.count }</div></td>
		    <td class="data_table_content_left" width="15%" height="20"><div align="center">${task.proposer }</div></td>
		    <td class="data_table_content_left" width="15%"><div align="center">${task.deploymentName }</div></td>
		    <td class="data_table_content_left" width="15%"><div align="center">${task.name }</div></td>
		    <td class="data_table_content_left" width="15%"><div align="center">${task.assignee }</div></td>
		    <td class="data_table_content_left" width="15%"><div align="center"><fmt:formatDate value="${task.createTime }" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" /> </div></td>
		    <td class="data_table_content_left" width="20%">
		    <div align="center">
	           <c:if test="${task.proceKey =='LeaveBill'}">
	             <a href="${pageContext.request.contextPath }/wf/toLeaveBillForm.do?taskId=${task.id }&taskName=${task.name }">
	                  <img alt="办理任务"  title="办理任务" src="${pageContext.request.contextPath}/images/handle.png" width="25" height="25" ></a>
	           </c:if>
	           &nbsp;&nbsp;&nbsp;&nbsp;
			     <a target="_blank" href="${pageContext.request.contextPath }/wf/viewCurrentImage.do?taskId=${task.id }"> 
			    <img   alt="查看当前流程图"  title="查看当前流程图" src="${pageContext.request.contextPath}/images/viewDiagram.png" width="25" height="25" > 
			     </a> 
		    </div>
		    </td>
		  </tr>
         </c:forEach>
    </c:when>
    <c:otherwise>
          <tr bgcolor="#FFFFFF"> 
		    <td class="data_table_content_left" colspan="8" height="20" >
		       <div align="center">
		         <b>暂无任务办理 ！ </b>
		       </div>
		   </td>
		  </tr>
    </c:otherwise>
    </c:choose>
</table>

<script type="text/javascript">

</script>


</body>
</html>