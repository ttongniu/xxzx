<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet type=text/css
	href="${pageContext.request.contextPath}/css/css.css">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<SCRIPT  type=text/javascript 
src="WF_Daiban_files/calendar.js"></SCRIPT>
<script type="text/javascript">
function add_group(){
    //window.showModalDialog("${pageContext.request.contextPath}/user/toAdd.do",document,"dialogWidth=600px;dialogHeight=250px;status:0");
    window.open("${pageContext.request.contextPath}/group/toAdd.do", "角色添加",
    "height=250, width=600, top=100, left=200,z-look=yes,toolbar=no, menubar=no, scrollbars=yes, resizable=no ,alwaysRaised=yes, location=no, status=no");
}
function del_group(str) {
	if (confirm("是否确认删除，此操作会同时删除有关的全部信息？")){
		$.ajax({  
		    type: "POST",  
		    url:"${pageContext.request.contextPath}/group/delGroup.do?groupId="+str,  
		    dataType: "text",
		    async: false,
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
		        alert(XMLHttpRequest.status);
		        alert(XMLHttpRequest.readyState);
		        alert(textStatus);
		    },
		    success: function(result) {  
		        alert(result);
		        window.location.reload();
		    }  
		  });	
	}
}
function search(){
	 document.forms['form1'].action="${pageContext.request.contextPath}/group/selectGroup.do";
	 document.forms['form1'].submit();
}
</script>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30"><div id='KB1Parent' class='parent'>
					<img
						src="${pageContext.request.contextPath}/images/right_sw_1_1.gif"
						width="257" height="25">
				</div></td>
		</tr>
		<tr>
			<td bgcolor="#CCCCCC"><img
				src="${pageContext.request.contextPath}/images/space.gif" width="5"
				height="1"></td>
		</tr>
	</table>
<form id="form1">
<span><a onclick="add_group()"><img alt="新建" src="${pageContext.request.contextPath}/images/add.png"></a></span> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" size=30  name="groupName" placeholder="输入要查询的数据"> 
<img  title="查找" onclick="search();"  src="${pageContext.request.contextPath}/images/find.png" width="20" height="20" >
</form>
<table width="100%" class="data_table" cellpadding="0" cellspacing="0">
  <tbody>
  <tr bgcolor="#ECF0F4"> 
     <td class="data_table_title" width="10%" height="20">
      <div align=center>序号</div></td>
     <td class="data_table_title" width="10%" height="20">
      <div align=center>数据权限</div></td>
       <td class="data_table_title" width="10%" height="20">
      <div align=center>数据描述</div></td>
        <td class="data_table_title" width="10%" height="20">
      <div align=center>操作</div></td>
 </tr>
  <c:forEach var="group" items="${groupList}" varStatus="status" >
  <tr bgcolor="#FFFFFF">
     <td class="data_table_content_left" width="10%" height="20">
      <div align=center>${status.index}</div></td>
     <td class="data_table_content_left" width="10%" height="20">
      <div align=center>${group.value}</div></td>
       <td class="data_table_content_left" width="10%" height="20">
      <div align=center>${group.description}</div></td>
      <td class="data_table_content_left" width="10%" height="20">
      
      <div align=center>
      <a href="${pageContext.request.contextPath}/group/maintainData.do?groupId=${group.id }"><img alt="数据权限维护" src="${pageContext.request.contextPath}/images/authorize.png"></a>
      <a href="${pageContext.request.contextPath}/group/maintainMenu.do?groupId=${group.id }"><img alt="功能权限维护" src="${pageContext.request.contextPath}/images/authorize.png"></a>
      <a href="${pageContext.request.contextPath}/group/toModify.do?groupId=${group.id }" onclick="window.open(this.href,'_blank','height=250, width=600, top=100, left=200,z-look=yes,toolbar=no, menubar=no, scrollbars=yes, resizable=no ,alwaysRaised=yes, location=no, status=no');return false"><img alt="修改" src="${pageContext.request.contextPath}/images/change.png"></a>&nbsp;&nbsp;
          <a href="javascript:del_group(${group.id })" target="main"><img alt="删除" src="${pageContext.request.contextPath}/images/delete2.png"></a>
      </div></td>
  </tr>
      </c:forEach>
</tbody></table></body>
</html>