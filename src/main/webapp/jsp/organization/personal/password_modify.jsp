<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
function oldPwdCheck(){
	var oldPwd=document.getElementById("oldPwd").value;
	var oldPwdSpan=document.getElementById("oldPwdSpan");
	 $.ajax({  
		    type: "POST",  
		    url:"${pageContext.request.contextPath}/user/verifyPwd.do?writePwd="+oldPwd,  
		    dataType: "text",
		    async: false,
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
             alert(XMLHttpRequest.status);
             alert(XMLHttpRequest.readyState);
             alert(textStatus);
         },
		    success: function(result) {  
		    	if (result=="一致") {
		    		oldPwdSpan.style.color="green";
		    		oldPwdSpan.innerHTML="正确";
				return true;					
				}else{
					oldPwdSpan.style.color="red";
					oldPwdSpan.innerHTML="旧密码输入错误";
					return false;
				}
		    }  
		  });
	
}
function newPwdCheck(){
	var newPwd=document.getElementById("newPwd").value;
	var newPwdSpan=document.getElementById("newPwdSpan");
	if (newPwd==null || newPwd=="") {
		newPwdSpan.style.color="red";
		newPwdSpan.innerHTML="新密码不能为空";
		return false;
	}else{
		newPwdSpan.style.color="green";
		newPwdSpan.innerHTML="正确";
		return true;
	}	
}
function checkForm(){
	var oc=oldPwdCheck();
	var nc=newPwdCheck();
	if (nc==false||oc==false) {
		return false;
	}else{
          //Ajax调用处理
          if (confirm("你的操作会修改该节点信息，是否继续？")){
    	 $.ajax({  
    		    type: "POST",  
    		    url:"${pageContext.request.contextPath}/user/modifyPwd.do",  
    		    dataType: "text",
    		    data:$('#form1').serialize(),  
    		    async: false,
    		    error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                },
    		    success: function(result) {  
    		        window.location.reload();
    		    }  
    		  });}
}}
</script>
<body>
<form id="form1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td height="30"><div id='KB1Parent' class='parent'><img src="${pageContext.request.contextPath}/images/right_sw_1_1.gif" width="257" height="25"></div></td>
    </tr>
    <tr> 
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
</table> 
<input type="hidden" name="id" value="${eId }">
<table  width="100%" align="center" class="data_table" cellpadding="0" cellspacing="0">
         <tr>
          <td align="center" class="data_table_title">旧密码：
          	<input name="oldPwd" type="password" id="oldPwd" class="data_table_content" onblur="oldPwdCheck()"><span id="oldPwdSpan"></span></td></td>
        </tr>
        <tr>
          <td align="center" class="data_table_title">新密码：
          	<input name="newPwd" type="password" id="newPwd" class="data_table_content" onblur="newPwdCheck()"><span id="newPwdSpan"></span></td></td>
        </tr>
        <tr>
          <td align="center"><input id="sbutton" type="button" value="提交" class="buttonaa" onclick="checkForm()"/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" name="Submit2" value="重置" class="buttonaa" />
          </td>
        </tr>
      </table>
      </form>
</body>
</html>