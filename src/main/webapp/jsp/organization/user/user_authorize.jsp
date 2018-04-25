<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/dtree.js"></script>
<link href="dtree.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/css.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<title>角色授权</title>
</head>

<script>
	var arrOP = [];
	var left_id = [], left_name = [];
	var flag = 0;
	var opNum = 0;
	var arrData = [];
	var dataNum = 0;
	var ch_text = '';
	var ch_val = '';
	function addItem() {
		var total_choose = document.getElementById('total_choose');
		var cur_choose = document.getElementById('cur_choose');
		var num = total_choose.options.length;
		for (var i = 0; i < num; i++) {
			var tempOption = total_choose.options[i];
			if (tempOption.selected) {
				total_choose.removeChild(tempOption);
				cur_choose.appendChild(tempOption);
			}
		}
	}

	function removeItem() {
		var total_choose = document.getElementById('total_choose');
		var cur_choose = document.getElementById('cur_choose');
		var num = cur_choose.options.length;
		for (var i = 0; i < num; i++) {
			var tempOption = cur_choose.options[i];
			if (tempOption.selected) {
				cur_choose.removeChild(tempOption);
				total_choose.appendChild(tempOption);
			}
		}
	}
	function clear_role_pow() {
		var total_choose = document.getElementById('total_choose');
		var cur_choose = document.getElementById('cur_choose');
		var num = cur_choose.options.length;
		for (var i = 0; i < num; i++) {
			var tempOption = cur_choose.options[0];
			cur_choose.removeChild(tempOption);
			total_choose.appendChild(tempOption);

		}
	}
	function check() {
		if (document.form1.cur_choose.length == 0) {
			alert("权限列表不能为空！");
			return false;
		}

		for (i = 0; i < document.form1.cur_choose.length; i++)
			form1.cur_choose.options[i].selected = true;
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/user/authorizeUser.do",
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
				window.location.reload();
			}
		});
	}
	function selectUser() {
		var sel = document.getElementById("total_choose");
		if (flag == 0) {
			for (var m = 0; m < sel.length; m++) {
				left_id[m] = sel.options[m].value;
				left_name[m] = sel.options[m].text;
			}
			flag = 1;
		}

		var findname = document.getElementById("find").value;
		sel.length = 0;
		for (var i = 0; i < left_id.length; i++) {
			var no = new Option();
			// 筛选左侧列表名单
			var name_left = left_name[i];
			if (name_left.indexOf(findname) != -1) {
				no.value = left_id[i];
				no.text = left_name[i];
				sel.options[sel.options.length] = no;
			} else if (findname == "") {
				no.value = left_id[i];
				no.text = left_name[i];
				sel.options[sel.options.length] = no;
			}/**/
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
	<form name="form1" id="form1" method="post" action="">
		<table cellpadding="0" cellspacing="8" align="center">
			<tr>
				<td>
					<fieldset style="width: 100px" align="center">
						<legend>所有角色</legend>
						<select name="total_choose" size="20" multiple id="total_choose"
							style="" ondblclick="addItem()">
							<c:forEach var="role" items="${roleList }">
								<option value="${role.id}">${role.value }</option>
							</c:forEach>
						</select> <input type="hidden" name="employeeId" value="${employeeId}">
					</fieldset>
				</td>
				<td align="center"><input type="button" value=" >  " title="添加"
					onClick="addItem()"><br> <input type="button"
					value=" <  " title="删除" onClick="removeItem()"><br> <input
					type="button" value=" << " title="清空" onClick="clear_role_pow()">
				</td>
				<td>
					<fieldset style="width: 100px" align="center">
						<legend>当前选择</legend>
						<select name="cur_choose" size="20" multiple id="cur_choose"
							style="" ondblclick="removeItem()">
							<c:forEach var="userAndRole" items="${userAndRoleList }">
								<option value="${userAndRole.roleId}">${userAndRole.role.value }</option>
							</c:forEach>
						</select>
					</fieldset>
				</td>
			</tr>
			<tr>
				<td><input type="text" id="find" name="find"><input
					type="button" id="button1" value="查询" onclick="selectUser()"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="button" name="Submit" value="提交"
					onClick="return check();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="reset" type="reset" id="reset" value="重置"></td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>