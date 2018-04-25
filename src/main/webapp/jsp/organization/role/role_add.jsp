<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	$(function() {
		//按钮单击时执行
		$("#sbutton").click(function() {

			//Ajax调用处理
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/role/addRole.do",
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
		});
	});
</script>
<body>
	<table width="60%" border="0" cellspacing="0" cellpadding="0">
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
		<table width="60%" align="center" class="data_table" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="right" class="data_table_title">角色名称：</td>
				<td class="data_table_content"><input name="roleName"
					type="text" id="roleName" class="data_table_content" value=""></td>
			</tr>
			<tr>
				<td align="right" class="data_table_title">角色描述：</td>
				<td class="data_table_content"><textarea name="roleDesc"
						id="roleDesc"></textarea></td>
			</tr>
		</table>
		<div align="center">
			<br> <input id="sbutton" type="button" value="提交"
				class="buttonaa" /> &nbsp;&nbsp;<input type="reset" name="Submit2"
				value="重置" class="buttonaa" />
		</div>

	</form>
</body>
</html>