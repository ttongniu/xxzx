<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.text.*,java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<%-- <base href="<%=basePath%>"> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志列表</title>
<link href="<%=basePath%>/css/css.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/My97DatePicker/WdatePicker.js"></script> 
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td height="30"><div id='KB1Parent' class='parent'><img src="<%=basePath%>/images/right_rz_1_1.jpg" width="257" height="25"></div></td>
    </tr>
    <tr> 
      <td bgcolor="#CCCCCC"><img src="<%=basePath%>/images/space.gif" width="5" height="1"></td>
    </tr>
  </table>
 <form action="<%=basePath%>/log/log.do" method="post" >
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="30"><font color="#FF0000"></font>&nbsp;
        <p><span style="margin-left: 10px;"><strong>操作人：</strong><input style="width:100px;" type="search" id="username" name="username" value="${username}"></span>
        <span style="margin-left: 100px;">
        	<strong>从</strong>
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
  				String log_date_one=log_year +"-"+ log_month +"-"+ (log_d.getDate()+1);
  			%>
        	<c:if test="${not empty sdate}">
        		<input id="sdate" style="width:100px;" name="startTime"  class="Wdate" value="${sdate }"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'edate\')}'})" />
        	</c:if>
        	<c:if test="${empty sdate}">
        		<input id="sdate" style="width:100px;" name="startTime"  class="Wdate" value="<%=log_date%>"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'edate\')}'})" />
        	</c:if>
<strong>到</strong>
			<c:if test="${not empty edate}">
    			<input id="edate" name="endTime" style="width:100px;" class="Wdate" value="${edate }"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'sdate\')}',startDate:'#F{$dp.$D(\'sdate\',{d:+1})}'})" />
        	</c:if>
        	<c:if test="${empty edate}">
        		<input id="edate" name="endTime" style="width:100px;" class="Wdate" value="<%=log_date_one%>"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'sdate\')}',startDate:'#F{$dp.$D(\'sdate\',{d:+1})}'})" />
        	</c:if>
        </span><input style="margin-left:50px;" name="Submit" type="submit"  value="GO">
        </p>      
      </td>
      
    </tr>
    <tr>
      <td bgcolor="#CCCCCC"><img src="<%=basePath%>/images/space.gif" width="5" height="1"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>       
    </tr>
  </table>
</form>
<table width="100%" class="data_table" cellpadding="0" cellspacing="0">
  <tr bgcolor="#ECF0F4"> 
    <td class="data_table_title"><div align="center">序号</div></td>
    <td class="data_table_title" height="20" width="60px;"><div align="center">操作人</div></td>
    <td class="data_table_title" ><div align="center">操作任务</div></td>
	<td class="data_table_title"  style="width:150px;"><div align="center">操作时间</div></td>
    <td class="data_table_title" ><div align="center">操作电脑IP</div></td>
  </tr>
    
    <c:choose>
    <c:when test="${ not empty  logs }">
         <c:forEach items="${logs }"  var="log" varStatus="status">
		  <tr bgcolor="#FFFFFF"> 
		    <td class="data_table_content_left"><div align="center">${status.index+1 }</div></td>
		    <td class="data_table_content_left" height="20"><div align="center">${log.user.employeeName }</div></td>
		    <td class="data_table_content_left" ><div >${log.operatorName}</div></td>
			<td class="data_table_content_left" height="20"><div align="center">${log.operatorDate }</div></td>
		    <td class="data_table_content_left" ><div align="center">${log.operatorIP }</div></td>
		  </tr>
         </c:forEach>
    </c:when>
    <c:otherwise>
          <tr bgcolor="#FFFFFF"> 
		    <td class="data_table_content_left" colspan="8" height="20" >
		       <div align="center">
		         <b>暂无日志信息 ！ </b>
		       </div>
		   </td>
		  </tr>
    </c:otherwise>
    </c:choose>
</table>

</body>
</html>