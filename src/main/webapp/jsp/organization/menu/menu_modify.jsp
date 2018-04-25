<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.boco.xxzx.model.Menu"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/dtree.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/dtree.css">
<title>Insert title here</title>
</head>
</head>
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
	<form action="" method="post" name="Form1">
		<table class="main_table" cellpadding="0" cellspacing="0">
			<tr valign="top">
				<td width="792">				
					
								<fieldset style="height:420px;width:30%; margin-left: 300px;">
					<legend>菜单</legend>
			    <table border="0" cellpadding="0" cellspacing="1" width="100%" height="400">
                   	<tr>
						<td>
				
				<div style="height:400px;width:100%;overflow: auto;" id="divTree"></div>
				</td></tr></table>
					<script>
					d = new dTree('d');
					d.add(0,-1,'');
					<%
					List<Menu> menuList=(List<Menu>)request.getAttribute("menuList");
					for(int i=0;i<menuList.size();i++){
					%>
					d.add(<%=menuList.get(i).getMenuId()%>,<%=menuList.get(i).getParentId()%>,'<%=menuList.get(i).getMenuName()%>','${pageContext.request.contextPath}/menu/showMenuInfo.do?menuId='+<%=menuList.get(i).getMenuId()%>,'','minfo');
					<%
					}
					%>
					divTree.innerHTML=d;
												</script>
                                              </fieldset>		
							</td>
							<td width="50%">
							<fieldset style="height:420px;width:50%;">
							<iframe id="minfo" name="minfo"
									src="${pageContext.request.contextPath}/menu/showMenuInfo.do?menuId=1"
									width="100%" style="height: 100%" marginwidth=0 marginheight=0
									frameborder=0 align="center"></iframe></td>
						</fieldset>
						</tr>
					</table>
	</form>
</body>


</html>