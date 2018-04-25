<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<link rel=stylesheet type=text/css
	href="${pageContext.request.contextPath}/css/css.css">
<script type="text/javascript">
	function idCheck() {
		var id = document.getElementById("employeeId").value;
		var idSpan = document.getElementById("idSpan");
		var reg = /^[a-zA-Z]{6,12}$/;
		if (id == null || id == "") {
			idSpan.style.color = "red";
			idSpan.innerHTML = "用户名不能为空";
			return false;
		} else if (!reg.test(id)) {
			idSpan.style.color = "red";
			idSpan.innerHTML = "用户名不符合规范";
			return false;
		} else {
			idSpan.style.color = "green";
			idSpan.innerHTML = "用户名可用";
			return true;
		}
	}
	function nameCheck() {
		var name = document.getElementById("employeeName").value;
		var nameSpan = document.getElementById("nameSpan");
		var reg = /^[a-zA-Z]{1,20}$/;
		var reg2 = /^[\u4e00-\u9fa5]{1,10}$/;
		if (name == null || name == "") {
			nameSpan.style.color = "red";
			nameSpan.innerHTML = "姓名不能为空";
			return false;
		} else if (!reg.test(name) && !reg2.test(name)) {
			nameSpan.style.color = "red";
			nameSpan.innerHTML = "姓名不符合规范";
			return false;
		} else {
			nameSpan.style.color = "green";
			nameSpan.innerHTML = "用户名可用";
			return true;
		}
	}
	function emailCheck() {
		var email = document.getElementById("email").value;
		var emailSpan = document.getElementById("emailSpan");
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		if (email == null || email == "") {
			emailSpan.style.color = "red";
			emailSpan.innerHTML = "邮箱不能为空";
			return false;
		} else if (!reg.test(email)) {
			emailSpan.style.color = "red";
			emailSpan.innerHTML = "邮箱不符合规范";
			return false;
		} else {
			emailSpan.style.color = "green";
			emailSpan.innerHTML = "邮箱可用";
			return true;
		}
	}
	function deptCheck() {
		var dept = document.getElementById("depNum").value;
		var deptSpan = document.getElementById("deptSpan");
		if (dept == null || dept == "") {
			deptSpan.style.color = "red";
			deptSpan.innerHTML = "部门不能为空";
			return false;
		} else {
			deptSpan.style.color = "green";
			deptSpan.innerHTML = "部门已选";
			return true;
		}
	}
	function checkForm() {
		var dc = deptCheck();
		var ic = idCheck();
		var ec = emailCheck();
		var nc = nameCheck();
		if (dc == false || ic == false || nc == false || ec == false) {
			return false;
		} else {
			//Ajax调用处理
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/user/addUser.do",
				dataType : "text",
				data : $('#form1').serialize(),
				async : false,
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(XMLHttpRequest.status);
					alert(XMLHttpRequest.readyState);
					alert(textStatus);
				},
				success : function(result) {
					alert(result);
					window.opener.location.reload();
					window.close();
				}
			});
		}
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
		<table width="60%" align="center" cellpadding="1" cellspacing="1"
			border="0">
			<tr>
				<td align="center" class="data_table_title">用户名： </td>
				<td class="data_table_content"><input
					name="employeeId" type="text" id="employeeId"
					class="data_table_content" value="" onblur="idCheck()"><span
					id="idSpan"></span></td>
			</tr>
			<tr>
				<td align="center" class="data_table_title">姓名：</td>
				<td class="data_table_content"> <input
					name="employeeName" type="text" id="employeeName"
					class="data_table_content" value="" onblur="nameCheck()"><span
					id="nameSpan"></span></td>
			</tr>
			<tr>
				<td align="center" class="data_table_title">Email：</td>
				<td class="data_table_content"> <input
					type="text" name="email" id="email" class="data_table_content"
					value="" onblur="emailCheck()"><span id="emailSpan"></span></td>
			</tr>
			<tr>
				<td align="center" class="data_table_title">电话：</td>
				<td class="data_table_content"> <input
					type="text" name="mobile" id="mobile" class="data_table_content"
					value=""></td>
			</tr>
			<tr>
				<td align="center" class="data_table_title">部门：</td>
				<td class="data_table_content"> <select
					name="depNum" id="depNum" class="data_table_content"
					onchange="deptCheck()">
						<option value="" selected>----- 请选择部门 -----</option>
						<c:forEach var="dept" items="${depList }">
							<option value="${dept.depNum}">${dept.depName}</option>
						</c:forEach>
				</select><span id="deptSpan"></span>
				</td>
			</tr>
		</table>
		<div align="center">
			<input id="sbutton" type="button" value="提交" class="buttonaa"
				onclick="checkForm()" /> <input type="reset" name="Submit2"
				value="重置" class="buttonaa" />
		</div>

	</form>
</body>
</html>