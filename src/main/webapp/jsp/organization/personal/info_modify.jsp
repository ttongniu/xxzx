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
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function nameCheck(){
	var name=document.getElementById("employeeName").value;
	var nameSpan=document.getElementById("nameSpan");
	var reg=/^[a-zA-Z]{1,20}$/;
	var reg2=/^[\u4e00-\u9fa5]{1,10}$/;
	if (name==null || name=="") {
		nameSpan.style.color="red";
		nameSpan.innerHTML="姓名不能为空";
		return false;
	}else if (!reg.test(name)&&!reg2.test(name)) {
		nameSpan.style.color="red";
		nameSpan.innerHTML="姓名不符合规范";
		return false;
	}else{
		nameSpan.style.color="green";
		nameSpan.innerHTML="用户名可用";
		return true;
	}	
}	
function checkForm(){
	var nc=nameCheck();
	if (nc==false) {
		return false;
	}else{
          //Ajax调用处理
          if (confirm("你的操作会修改该节点信息，是否继续？")){
    	 $.ajax({  
    		    type: "POST",  
    		    url:"${pageContext.request.contextPath}/user/modifyPersonal.do",  
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
    		        window.opener.location.reload();
    		    	window.close();
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
<input type="hidden" name="id" value="${user.id }">
<table class="data_table" width="30%" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center" class="data_table_title">姓名：</td>
          	<td class="data_table_content"><input name="employeeName" type="text" id="employeeName" class="data_table_content" value="${user.employeeName }"><span id="nameSpan"></span></td>
        </tr>
         <tr>
          <td align="center" class="data_table_title">用户名：</td>
          	<td class="data_table_content"><input name="employeeId" type="text" id="employeeId" class="data_table_content" value="${user.employeeId }" disabled="disabled"><span id="idSpan"></span></td>
        </tr>
        <tr>  
          <td align="center" class="data_table_title">Email：</td>
          	<td class="data_table_content"><input type="text" name="email" id="email" class="data_table_content" value="${user.email }" disabled="disabled"><span id="emailSpan"></span></td>
        </tr>
         <tr>  
          <td align="center" class="data_table_title">电话：</td>
          	<td class="data_table_content"><input type="text" name="mobile" id="mobile" class="data_table_content" value="${user.mobile }"></td>
        </tr>
        <tr>          
          <td align="center" class="data_table_title">部门：</td>
          <td class="data_table_content"><select name="depNum" id="depNum" disabled="disabled"  class="data_table_content" >
            <option value="${user.depNum }" selected> ${user.department.depName } </option>                     
          </select><span id="deptSpan"></span></td>
          </td>
        </tr>
      </table>
      <br>
      <div align="center">
        <input id="sbutton" type="button" value="提交" class="buttonaa" onclick="checkForm()"/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" name="Submit2" value="重置" class="buttonaa" />
      </div>
      </form>
</body>
</html>