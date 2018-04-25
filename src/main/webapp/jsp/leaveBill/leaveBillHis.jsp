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
<table width="800" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#003366">
   <input name="id" id="id" value="${leaveBill.id }" type="hidden"> 
  <tr> 
    <td width="30%" align="right" bgcolor="#FFFFFF">请假天数：</td>
    
    <td width="70%"  bgcolor="#FFFFFF">
      <input size="50" type="text" name="days" id="days" value="${leaveBill.days }"/>
      <span id="result"> </span>
    </td>   
  </tr>
  <tr>
    <td width="30%" align="right" bgcolor="#FFFFFF">请假事由：</td>
    
    <td width="70%"  bgcolor="#FFFFFF">
      <input size="50" type="text" name="content" id="content" value="${leaveBill.content }" />
      <span id="result"> </span>
    </td>
    
  </tr>
 
  <tr>
    <td  align="right" bgcolor="#FFFFFF">备注：</td>
    <td bgcolor="#FFFFFF"> <textarea rows="4" cols="40" id="remark" name="remark" >${leaveBill.remark }</textarea> </td>
  </tr>

  <tr>
    <td colspan="2" align="center" bgcolor="#FFFFFF">
   <input type="button" name="button" id="button" onclick="javascript:history.go(-1)" value="返回" />
    </td>
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
			   <td width="25%" height="20"><div align="center"> <fmt:formatDate value="${comment.time }" dateStyle="long"  type="date" pattern="yyyy-MM-dd HH:mm:ss"  />  </div></td>
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
              <li style="size:14px/1.5; font-family: Helvetica Neue;text-align: left; margin: 10px">
              ${comment.userId } 的意见： ${comment.fullMessage }    <fmt:formatDate value="${comment.time }" dateStyle="long"  type="date" pattern="yyyy-MM-dd HH:mm:ss"  />
              </li>
             <span></span>
             </c:forEach> 
	      </c:when>
	      <c:otherwise>
	      <li>暂时没有批注信息</li>
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