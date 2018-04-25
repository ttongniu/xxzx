<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<title>功能树结点信息修改</title>
</head>
 
<script language="JavaScript"> 
function checkChange(){
	var status=document.getElementById("flag");
	if (status.checked==true) {
		status.value="1";
	}else{
		status.value="0";
	}
}
function modify_node() {
	var form1=document.getElementById("form1");
	var menuId=document.getElementById("menuId").value;
	if (form1.menuName.value == "") {
		alert("菜单名不能为空！");
		form1.menuName.focus();
		return false;
	}
	if (form1.url.value == "") {
		alert("菜单路径不能为空！");
		form1.url.focus();
		return false;
	}
	if (confirm("你的操作会修改该节点信息，是否继续？"))
		$.ajax({  
		    type: "POST",  
		    url:"${pageContext.request.contextPath}/menu/modifyMenu.do?",  
		    dataType: "text",
		    data:$('#form1').serialize(),  
		    async: false,
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
		        alert(XMLHttpRequest.status);
		        alert(XMLHttpRequest.readyState);
		        alert(textStatus);
		    },
		    success: function(result) {  
		        alert(result);
		        window.parent.location.reload();
		    }  
		  });
		return false;
}
function add_menu(){
	var parentId=document.getElementById("menuId").value;
    //window.showModalDialog("${pageContext.request.contextPath}/user/toAdd.do",document,"dialogWidth=600px;dialogHeight=250px;status:0");
    window.open("${pageContext.request.contextPath}/menu/toAdd.do?parentId="+parentId, "菜单添加","height=250, width=600, top=100, left=200,z-look=yes,toolbar=no, menubar=no, scrollbars=yes, resizable=no ,alwaysRaised=yes, location=no, status=no" );
}
function delete_menu(){
    var menuId=document.getElementById("menuId").value;
if (confirm("你的操作会修改该节点及其子节点，是否继续？"))
$.ajax({  
    type: "POST",  
    url:"${pageContext.request.contextPath}/menu/delMenu.do?menuId="+menuId,  
    dataType: "text",
    data:$('#form1').serialize(),  
    async: false,
    error: function(XMLHttpRequest, textStatus, errorThrown) {
        alert(XMLHttpRequest.status);
        alert(XMLHttpRequest.readyState);
        alert(textStatus);
    },
    success: function(result) {  
        alert(result);
        window.parent.location.reload();
    }  
  });
return false;
}
</script>
 
<body class="main_body">
<form id="form1" name="form1" method="post" action="">
<table class="main_table" cellpadding="0" cellspacing="0">
  <tr  valign="top">
    <td width="100%"><table class="data_table" width="100%" cellpadding="0" cellspacing="0">
      <tr>
        <td colspan="2" class="data_table_title"><div align="center"></div></td>
      </tr>
      <tr>
        <td class="data_table_title" width="20%">功能结点ID：</td>
        <td class="data_table_content" width="80%">${menu.menuId }&nbsp;
         <input id="menuId" name="menuId" type="hidden" value="${menu.menuId}"/>
        </td>
      </tr>
      <tr>
        <td class="data_table_title">节点名称：</td>
        <td class="data_table_content">
		<input id="menuName" name="menuName" id="menuName" type="text" class="data_table_content" style="width:100%" value="${menu.menuName }" maxlength="50"></td>
      </tr>
      <tr>
        <td class="data_table_title">节点路径：</td>
        <td class="data_table_content">
		<input id="url" name="url" type="text" class="data_table_content" style="width:100%" value="${menu.url }" maxlength="100"></td>
      </tr>
	  <tr>
        <td class="data_table_title">是否显示：</td>
        <td class="data_table_content">
        <c:if test="${menu.flag==1}">
        <input name="flag" type="checkbox" class="data_table_content" id="flag" value="1"  checked="checked" onclick="checkChange()" >
        </c:if>
         <c:if test="${menu.flag==0}">
        <input name="flag" type="checkbox" class="data_table_content" id="flag" value="0" onclick="checkChange()"  >
        </c:if>
        </td>
      </tr>     
    </table>
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center">&nbsp;</td>
        </tr>
        <tr>
          <td align="center">
		  
		  <input type="button" name="add_node" value="添加下级节点" onClick="add_menu()"/>
		  &nbsp;&nbsp;<input type="button" name="Submit" value="修改" onClick="return modify_node();"/>
		  &nbsp;&nbsp;<input type="button" name="Submit" value="删除" onClick="return delete_menu();"/>
          &nbsp;&nbsp;<input type="reset" name="Submit2" value="重置" onClick=""/></td>
        </tr>
      </table></td>
  </tr>
</table>
</form>
</body>
</html>