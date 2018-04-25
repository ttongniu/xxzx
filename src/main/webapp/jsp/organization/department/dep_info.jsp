<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css">
<title>功能树结点信息修改</title>
</head>
 
<script language="JavaScript"> 
	function depNumCheck(){
		var depNum=document.getElementById("depNum").value;
		var depNumSpan=document.getElementById("depNumSpan");
		var reg=/^[a-zA-Z1-9]{1,6}$/;
		if (depNum==null || depNum=="") {
			depNumSpan.style.color="red";
			depNumSpan.innerHTML="部门号不能为空";
			return false;
		}else if (!reg.test(depNum)) {
			depNumSpan.style.color="red";
			depNumSpan.innerHTML="部门号不符合规范";
			return false;
		}else{
			depNumSpan.style.color="green";
			depNumSpan.innerHTML="部门号可用";
			return true;
		}	
	}
	function depNameCheck(){
		var depName=document.getElementById("depName").value;
		var depNameSpan=document.getElementById("depNameSpan");
		var reg=/^[\u4e00-\u9fa5a-z1-9]{1,10}$/;
		if (depName==null || depName=="") {
			depNameSpan.style.color="red";
			depNameSpan.innerHTML="部门名称不能为空";
			return false;
		}else if (!reg.test(depName)) {
			depNameSpan.style.color="red";
			depNameSpan.innerHTML="部门名称不符合规范";
			return false;
		}else{
			depNameSpan.style.color="green";
			depNameSpan.innerHTML="部门名称可用";
			return true;
		}	
	}
function modify_node() {
	if (confirm("你的操作会修改该节点信息，是否继续？")){
		var mc=depNameCheck();
    	var nc=depNumCheck();
    	if (mc==false||nc==false) {
    		return false;
    	}else{
		$.ajax({  
		    type: "POST",  
		    url:"${pageContext.request.contextPath}/department/modifyDept.do?",  
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
}}}
function add_dept(){
	var parentId=document.getElementById("depId").value;
    //window.showModalDialog("${pageContext.request.contextPath}/user/toAdd.do",document,"dialogWidth=600px;dialogHeight=250px;status:0");
    window.open("${pageContext.request.contextPath}/department/toAdd.do?parentId="+parentId, "角色添加","height=250, width=600, top=100, left=200,z-look=yes,toolbar=no, menubar=no, scrollbars=yes, resizable=no ,alwaysRaised=yes, location=no, status=no" );
}
function delete_dept(){
    var depId=document.getElementById("depId").value;
if (confirm("你的操作会修改该节点及其子节点，并删除角色列表中该功能的授权，是否继续？"))
$.ajax({  
    type: "POST",  
    url:"${pageContext.request.contextPath}/department/delDept.do?depId="+depId,  
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
        <td class="data_table_content" width="80%">${department.depId }&nbsp;
        <input id="depId" name="depId" type="hidden" value="${department.depId}"/>
        </td>
      </tr>
      <tr>
        <td class="data_table_title">部门号：</td>
        <td class="data_table_content">
		<input id="depNum" name="depNum" type="text" class="data_table_content" style="width:100%" value="${department.depNum }" maxlength="50"><span id="depNumSpan"></span></td>
      </tr>
      <tr>
        <td class="data_table_title">部门名称：</td>
        <td class="data_table_content">
		<input id="depName" name="depName" type="text" class="data_table_content" style="width:100%" value="${department.depName }" maxlength="100"><span id="depNameSpan"></span></td>
      </tr>
	  <tr>
        <td class="data_table_title">启用：</td>
        <td class="data_table_content">
        <c:if test="${department.flag==1 }">
                <input id="flag" name="flag" type="checkbox" class="data_table_content" id="viewable" value="1" checked="checked" > 
        </c:if>
        <c:if test="${department.flag==0 }">
                <input id="flag" name="flag" type="checkbox" class="data_table_content" id="viewable" value="0" > 
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
		  
		  <input type="button" name="add_node" value="添加下级节点" onClick="add_dept()"/>
		  &nbsp;&nbsp;<input type="button" id="mod" name="mod" value="修改" onClick="return modify_node();"/>
		  &nbsp;&nbsp;<input type="button" id="del" name="del" value="删除" onclick="return delete_dept()"/>
          &nbsp;&nbsp;<input type="reset" name="Submit2" value="重置" onClick=""/></td>
        </tr>
      </table></td>
  </tr>
</table>
</form>
</body>
</html>