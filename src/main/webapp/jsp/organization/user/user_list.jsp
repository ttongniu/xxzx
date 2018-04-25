<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel=stylesheet type=text/css
	href="${pageContext.request.contextPath}/css/css.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type=text/javascript src="WF_Daiban_files/calendar.js"></script>
<title>用户列表</title>
</head>
<script type="text/javascript">
	function add_user() {
		//window.showModalDialog("${pageContext.request.contextPath}/user/toAdd.do",document,"dialogWidth=600px;dialogHeight=250px;status:0");
		window
				.open(
						"${pageContext.request.contextPath}/user/toAdd.do",
						"人员添加",
						"height=250, width=600, top=100, left=200,z-look=yes,toolbar=no, menubar=no, scrollbars=yes, resizable=no ,alwaysRaised=yes, location=no, status=no");

	}
	function changeStatus(id, status) {
		if (confirm("是否确认操作？"))
			$
					.ajax({
						type : "POST",
						url : "${pageContext.request.contextPath}/user/changeStatus.do?id="
								+ id + "&status=" + status,
						dataType : "text",
						async : false,
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							alert(XMLHttpRequest.status);
							alert(XMLHttpRequest.readyState);
							alert(textStatus);
						},
						success : function(result) {
							alert(result);
							window.location.reload();
							window.close();
						}
					});
	}
	function chooseStatus() {
		var status = document.getElementById("status").value;
		window.location.href = "${pageContext.request.contextPath}/user/selectUser.do?status="
				+ status;
	}
function search(){
		 document.forms['form1'].action="${pageContext.request.contextPath}/user/selectCriteriaUser.do";
		 document.forms['form1'].submit();
	}
</script>
<body>
	<form id="form1">
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
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="10">
				<span> 
				<a onclick="add_user()"><img alt="新建" src="${pageContext.request.contextPath}/images/add.png"></a>
				</span> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" size=30 name="condi" placeholder="输入要查询的用户名">
					<img  title="查找" onclick="search();"  src="${pageContext.request.contextPath}/images/find.png" width="20" height="20" >
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select id="status"
					name="status" onchange="chooseStatus()">
						<option>状态选择......</option>
						<option value=1>在职</option>
						<option value=0>离职</option>
				</select>
				</td>
				</td>
			</tr>
			<tr>
				<td height="30"><font color="#FF0000"></font>&nbsp;
					<p>
						<img src="${pageContext.request.contextPath}/images/arrow-1.gif"
							width="11" height="12">&nbsp;<a href="#" class="g">员工信息列表</a>
					</p></td>
			</tr>
			<tr>
				<td bgcolor="#CCCCCC"><img
					src="${pageContext.request.contextPath}/images/space.gif" width="5"
					height="1"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form>
	<table width="100%" class="data_table" cellpadding="0" cellspacing="0">
		<tr bgColor="#ECF0F4">
			<td class="data_table_title" width="10%" height="20">
				<div align=center>序号</div>
			</td>
			<td class="data_table_title" width="10%" height="20">
				<div align=center>用户名</div>
			</td>
			<td class="data_table_title" width="10%" height="20">
				<div align=center>员工姓名</div>
			</td>
			<td class="data_table_title" width="10%" height="20">
				<div align=center>所在部门</div>
			</td>
			<td class="data_table_title" width="10%" height="20">
				<div align=center>邮箱</div>
			</td>
			<td class="data_table_title" width="10%" height="20">
				<div align=center>状态</div>
			</td>
			<td class="data_table_title" width="10%" height="20">
				<div align=center>联系方式</div>
			</td>
			<td class="data_table_title" width="10%" height="20">
				<div align=center>创建时间</div>
			</td>
			<td class="data_table_title" width="10%" height="20">
				<div align=center>操作</div>
			</td>
		</tr>
		<c:forEach var="user" items="${userList}" varStatus="status">
			<tr bgcolor="#FFFFFF">
				<td class="data_table_content_left">
					<div align=center>${status.index}</div>
				</td>
				<td class="data_table_content_left">
					<div align=center>${user.employeeId}</div>
				</td>
				<td class="data_table_content_left">
					<div align=center>${user.employeeName}</div>
				</td>
				<td class="data_table_content_left">
					<div align=center>${user.department.depName}</div>
				</td>
				<td class="data_table_content_left">
					<div align=center>${user.email}</div>
				</td>
				<td class="data_table_content_left">
					<div align=center>
						<c:choose>
							<c:when test="${user.status==0}">离职</c:when>
							<c:otherwise>在职</c:otherwise>
						</c:choose>
					</div>
				</td>
				<td class="data_table_content_left">
					<div align=center>${user.mobile}</div>
				</td>
				<td class="data_table_content_left">
					<div align=center>
						<fmt:formatDate value="${user.createTime}"
							pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" />
					</div>
				</td>
				<td class="data_table_content_left">
					<div align=center>
						<a
							href="${pageContext.request.contextPath}/user/toAuthorize.do?employeeId=${user.employeeId }&employeeName=${user.employeeName}"><img alt="授权" src="${pageContext.request.contextPath}/images/authorize.png"></a>&nbsp;&nbsp;
						<a
							href="${pageContext.request.contextPath}/user/toShow.do?userId=${user.id }"
							onclick="window.open(this.href,'_blank','height=250, width=600, top=100, left=200,z-look=yes,toolbar=no, menubar=no, scrollbars=yes, resizable=no ,alwaysRaised=yes, location=no, status=no');return false"><img alt="查看审核记录"  title="查看" src="${pageContext.request.contextPath}/images/look.png" width="25" height="25" ></a>&nbsp;&nbsp;
						<a
							href="${pageContext.request.contextPath}/user/toModify.do?userId=${user.id }"
							onclick="window.open(this.href,'_blank','height=250, width=600, top=100, left=200,z-look=yes,toolbar=no, menubar=no, scrollbars=yes, resizable=no ,alwaysRaised=yes, location=no, status=no');return false"><img alt="修改" src="${pageContext.request.contextPath}/images/change.png"></a>&nbsp;&nbsp;
						<c:if test="${user.status==0}">
							<a href="javascript:changeStatus(${user.id },${user.status})"
								target="main"><img alt="恢复" src="${pageContext.request.contextPath}/images/recover.png"></a>
						</c:if>
						<c:if test="${user.status==1}">
							<a href="javascript:changeStatus(${user.id },${user.status})"
								target="main"><img alt="离职" src="${pageContext.request.contextPath}/images/leavejob.png"></a>
						</c:if>
					</div>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>