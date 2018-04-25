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
  <table width="90%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td width="50%"><img src="${pageContext.request.contextPath}/images/edit-info-b-5_r2_c2.gif" width="221" height="23"></td>
    <td width="50%" valign="bottom">[ <a href="${pageContext.request.contextPath}/wfSecurity/toSaveWfGroup.do"><b>添加权限组</b></a> 
      ]</td>
  </tr>
  <tr bgcolor="#FFD595"> 
    <td colspan="2"><img src="${pageContext.request.contextPath}/images/space.gif" width="1" height="1"></td>
  </tr>
</table>

<table  width="100%" class="data_table" cellpadding="0" cellspacing="0">
 
  <tr bgcolor="#ECF0F4"> 
    <td class="data_table_title"><div align="center">序号</div></td>
	<td class="data_table_title" width="30%" height="20"><div align="center">组名称</div></td>
    <td class="data_table_title" width="10%"><div align="center">组版本</div></td>
    <td class="data_table_title" width="30%"><div align="center">组描述</div></td>
    <td class="data_table_title" width="20%"><div align="center">操作</div></td>
  </tr>
    <c:choose>
    <c:when test="${not empty groupList }">
    <c:forEach  items="${groupList }" var="group" varStatus="status">
    <tr bgcolor="#FFFFFF"> 
    <td class="data_table_content_left"><div align="center">${status.count }</div></td>
	<td class="data_table_content_left" width="30%" height="20"><div align="center"><a href="${pageContext.request.contextPath}/wfSecurity/toEditMemberShip.do?id=${group.id}">${group.id }</a></div></td>
    <td class="data_table_content_left" width="10%"><div align="center">${group.revision }</div></td>
    <td class="data_table_content_left" width="30%"><div align="center">${group.name }</div></td>
    <td class="data_table_content_left" width="20%"><div align="center">
    <a href="${pageContext.request.contextPath}/wfSecurity/toEditMemberShip.do?id=${group.id}"  ><img alt="修改"  title="修改" src="${pageContext.request.contextPath}/images/update.png" width="25" height="25" ></a>  &nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/wfSecurity/deleteWfGroupbyId.do?id=${group.id}"  onclick="javascript:return p_del()"><img alt="删除" title="删除" src="${pageContext.request.contextPath}/images/delete.png" width="25" height="25" ></a>
    </div></td>
    </tr> 
    </c:forEach>
    </c:when>
    <c:otherwise>
	    <tr bgcolor="#FFFFFF"> 
	    <td class="data_table_content_left" colspan="5" height="20">
	    <div align="center">
	                             暂无权限组信息
        </div>
	    </td>
	    </tr> 
    </c:otherwise>
    </c:choose>
</table>


<script type="text/javascript">

function p_del() {
	var msg = "您真的确定要删除吗？\n\n请确认！";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
} 

</script>


</body>
</html>