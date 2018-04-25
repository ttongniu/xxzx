<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel=stylesheet type=text/css
	href="${pageContext.request.contextPath}/css/css.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
	function depNumCheck() {
		var depNum = document.getElementById("depNum").value;
		var depNumSpan = document.getElementById("depNumSpan");
		var reg = /^[a-zA-Z]{1,6}$/;
		if (depNum == null || depNum == "") {
			depNumSpan.style.color = "red";
			depNumSpan.innerHTML = "部门编号不能为空";
			return false;
		} else if (!reg.test(depNum)) {
			depNumSpan.style.color = "red";
			depNumSpan.innerHTML = "部门编号不符合规范";
			return false;
		} else {
			depNumSpan.style.color = "green";
			depNumSpan.innerHTML = "部门编号可用";
			return true;
		}
	}
	function depNameCheck() {
		var depName = document.getElementById("depName").value;
		var depNameSpan = document.getElementById("depNameSpan");
		var reg = /^[\u4e00-\u9fa5a-z]{1,10}$/;
		if (depName == null || depName == "") {
			depNameSpan.style.color = "red";
			depNameSpan.innerHTML = "部门名不能为空";
			return false;
		} else if (!reg.test(depName)) {
			depNameSpan.style.color = "red";
			depNameSpan.innerHTML = "部门名不符合规范";
			return false;
		} else {
			depNameSpan.style.color = "green";
			depNameSpan.innerHTML = "部门编号可用";
			return true;
		}
	}
	function checkForm() {
		var mc = depNameCheck();
		var nc = depNumCheck();
		if (mc == false || nc == false) {
			return false;
		} else {
			//Ajax调用处理
			$
					.ajax({
						type : "POST",
						url : "${pageContext.request.contextPath}/department/addDept.do",
						dataType : "text",
						data : $('#form1').serialize(),
						async : false,
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							alert(XMLHttpRequest.status);
							alert(XMLHttpRequest.readyState);
							alert(textStatus);
						},
						success : function(result) {
							alert(result);
							window.opener.parent.location.reload();
							window.close();
						}
					});
		}
	}
</script>
<body class="main_body">
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
	<form id="form1" name="form1" method="post" action="">
		<table class="main_table" cellpadding="0" cellspacing="0"
			align="center">
			<tr valign="top">
				<td width="100%"><table class="data_table" width="100%"
						cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="2" class="data_table_title"><div align="center"></div></td>
						</tr>
						<tr>
							<td class="data_table_content" width="80%"><input
								type="hidden" name="parentId" value="${department.parentId }" /></td>
						</tr>
						<tr>
							<td class="data_table_title">部门号：</td>
							<td class="data_table_content"><input name="depNum"
								type="text" class="data_table_content" onblur="depNumCheck()"><span
								id="depNumSpan"></span></td>
						</tr>
						<tr>
							<td class="data_table_title">部门名称：</td>
							<td class="data_table_content"><input name="depName"
								type="text" class="data_table_content" onblur="depNameCheck()"><span
								id="depNameSpan"></span></td>
						</tr>
						<tr>
							<td class="data_table_title">是否启用：</td>
							<td class="data_table_content"><input name="flag"
								type="checkbox" class="data_table_content" id="viewable"
								value="1"></td>
						</tr>
					</table>
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td align="center">&nbsp;</td>
						</tr>
						<tr>
							<td align="center"><input type="button" id="sbutton"
								name="sbutton" value="添加节点" onclick="checkForm()" />
								&nbsp;&nbsp;<input type="reset" name="Submit2" value="重置"
								onClick="" /></td>
						</tr>
					</table></td>
			</tr>
		</table>
	</form>
</body>
</html>