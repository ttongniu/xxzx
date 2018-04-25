<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>

<html>
<base href="<%=basePath%>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<script type="text/javascript">
	function yzSubmit(){
		var form1=document.getElementById("form1");
		var userID=document.getElementById("userID");
		var password=document.getElementById("password");
		var rand=document.getElementById("rand").value;
		var flag=false;
		if(userID.value==""||password.value==""){
			alert("用户名或密码不能为空！");
			form1.action="jsp/login.jsp?employeeId="+userID.value;
		}else if(rand==""){
			alert("验证码不能为空！");
			form1.action="jsp/login.jsp?employeeId="+userID.value;
		}else{
			flag=true;
			form1.action="login/login.do";
		}
		if(flag){
			form1.submit();
		}
	}
	function load(){
		if (window != top){
			top.location.href = location.href;
		}
	}
</script>
</head>
<body bgcolor="#ffffff" topmargin="0"  onload="javascript:load();">
	<form id="form1" method="post" style="margin-top: 8%">
		<table width="100" height="90%" border="0" align="center"
			cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
			<tr>
				<td bgcolor="#FFFFFF"><table width="596" border="0"
						align="center" cellpadding="0" cellspacing="0">
						<tr background="images/login_r1_c1-1.gif">
							<td height="95" colspan="3" background="<%=basePath%>images/login_r1_c1-1.gif">&nbsp;</td>
						</tr>
						<tr>
							<td width="248" height="62" bgcolor="#f4f7f9">&nbsp;</td>
							<td height="62" colspan="2" background="images/login_r2_c2.gif">&nbsp;</td>
						</tr>
						<tr>
							<td height="200" colspan="2" background="images/login_r3_c1.gif">&nbsp;</td>
							<td bgcolor="#f4f7f9"><form name="form1" method="post"
									action="login_db.jsp">
									<table width="100%" border="0" cellspacing="4" cellpadding="4">
										<tr>
											<td height="25" colspan="4">&nbsp;&nbsp;<img
												src="images/login_text.gif" width="99" height="17"></td>
										</tr>
										<tr>
											<td width="32%">
												<div align="right">
													<font size="2">用户名：</font>
												</div>
											</td>
											<td width="31%" colspan="2">
												<input name="employeeId"  id="userID" type="text"  size="15">
											</td>
											<td width="37%">&nbsp;</td>
										</tr>
										<tr>
											<td>
												<div align="right">
													<font size="2">密&nbsp;&nbsp;码：</font>
												</div>
											</td>
											<td colspan="2">
												<input name="password" id="password" type="password" value="" size="15">
											</td>
											<td></td>
										</tr>
										<tr>
											<td>
												<div align="right">
													<font size="2">验证码：</font>
												</div>
											</td>
											<td>
												<input type=text name="rand" id="rand" maxlength=4 value="" size="5">
											</td>
											<td><img border=0 src="jsp/randomNumber.jsp" ></td>
											<td>
												<input name="imageField" type="image" src="images/login_go.gif" width="25" height="25" border="0" onclick="javascript:yzSubmit();">
											</td>
										</tr>
									</table>
								</form>
								</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="2" align="center"><font color="red" size="2px" >${error}</font></td>
						</tr>
						<tr bgcolor="afc0d0">
							<td height="23" colspan="3"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>