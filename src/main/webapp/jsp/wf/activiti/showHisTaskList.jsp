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
      <td height="30"><div id='KB1Parent' class='parent'><img src="${pageContext.request.contextPath}/images/right_sw_1_1.gif" width="257" height="25"></div></td>
    </tr>
    <tr> 
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
  </table>
  
  
<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
  </table>
  <form action="${pageContext.request.contextPath}/wf/listHaveTODO.do">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    
    <tr>
      <td>  &nbsp;&nbsp;
       <font color="blue">任务名称：</font><input name="taskName" id="taskName"  type="text" value="${taskName }" class="textfield-ffffff"  size="15"> 
            &nbsp;&nbsp; <input name="Submit" type="submit"  value="GO">
      </td>
    </tr>
    <tr>
      <td>&nbsp;</td>       
    </tr>
  </table>
</form>
<table  width="100%" class="data_table" cellpadding="0" cellspacing="0">
  <tr bgcolor="#ECF0F4"> 
    <td class="data_table_title"><div align="center">序号</div></td>
    <td class="data_table_title" width="10%" height="20"><div align="center">流程申请人</div></td>
    <td class="data_table_title" width="10%"><div align="center">流程名称</div></td>
	<td class="data_table_title" width="10%" height="20"><div align="center">任务ID</div></td>
    <td class="data_table_title" width="10%"><div align="center">任务名称</div></td>
    <td class="data_table_title" width="10%"><div align="center">审批开始时间</div></td>
    <td class="data_table_title" width="10%"><div align="center">审批结束时间</div></td>
    <td class="data_table_title" width="10%"><div align="center">办理人</div></td>
    <td class="data_table_title" width="25%"><div align="center">操作</div></td>
  </tr>
    
    <c:choose>
    <c:when test="${ not empty  taskInstances }">
         <c:forEach items="${taskInstances }"  var="taskInstance" varStatus="status">
		  <tr bgcolor="#FFFFFF"> 
		    <td class="data_table_content_left"><div align="center">${status.count }</div></td>
		    <td class="data_table_content_left" width="10%" height="20"><div align="center">${taskInstance.proposer }</div></td>
		    <td class="data_table_content_left" width="10%"><div align="center">${taskInstance.deploymentName }</div></td>
			<td class="data_table_content_left" width="10%" height="20"><div align="center">${taskInstance.id }</div></td>
		    <td class="data_table_content_left" width="10%"><div align="center">${taskInstance.name }</div></td>
		    <td class="data_table_content_left" width="10%"><div align="center"><fmt:formatDate value="${taskInstance.createTime }" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" /> </div></td>
		    <td class="data_table_content_left" width="10%"><div align="center"><fmt:formatDate value="${taskInstance.endTime }" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" /> </div></td>
		    <td class="data_table_content_left" width="10%"><div align="center">${taskInstance.assignee }</div></td>
		    <td class="data_table_content_left" width="25%">
		    <div align="center">
		        <a   target="_blank" href="${pageContext.request.contextPath }/wf/listAction.do?taskId=${taskInstance.id }">流程执行过程</a> &nbsp;&nbsp;&nbsp;
			    <a href="${pageContext.request.contextPath }/leaveBill/leaveBillviewHisTask.do?taskId=${taskInstance.id }">查看流程历史批注</a>&nbsp;&nbsp;&nbsp;
			    <a href="${pageContext.request.contextPath}/wf/viewImageHis.do?taskId=${taskInstance.id }"
			onClick="javascript:window.open(this.href,'_blank','height=600,width=800,top=150,left=400,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');return false" >查看流程图</a>
		    </div>
		    </td>
		  </tr>
         </c:forEach>
    </c:when>
    <c:otherwise>
          <tr bgcolor="#FFFFFF"> 
		    <td  class="data_table_content_left" colspan="9" height="20" >
		       <div align="center">
		         <b>暂无任务！ </b>
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