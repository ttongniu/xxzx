<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<link rel=stylesheet type=text/css
		href="${pageContext.request.contextPath}/css/css.css">
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
	<table width="60%" align="center" class="data_table" cellpadding="0"
		cellspacing="0">
		<tr>
			<td align="right" class="data_table_title">用户名：</td>
			<td class="data_table_content"><input name="employeeId"
				type="text" id="employeeId" class="data_table_content"
				value="${user.employeeId }"></td>
		</tr>
		<tr>
			<td align="right" class="data_table_title">姓名：</td>
			<td class="data_table_content"><input name="employeeName"
				type="text" id="employeeName" class="data_table_content"
				value="${user.employeeName }"></td>
		</tr>
		<tr>
			<td align="right" class="data_table_title">Email：</td>
			<td class="data_table_content"><input type="text" name="email"
				id="email" class="data_table_content" value="${user.email }"></td>
		</tr>
		<tr>
			<td align="right" class="data_table_title">mobile：</td>
			<td class="data_table_content"><input type="text" name="mobile"
				id="mobile" class="data_table_content" value="${user.mobile }"></td>
		</tr>
		<tr>
			<td align="right" class="data_table_title">部门：</td>
			<td class="data_table_content"><input type="text" name="depName"
				id="depName" class="data_table_content"
				value="${user.department.depName }"></td>
		</tr>
	</table>
</body>
</html>