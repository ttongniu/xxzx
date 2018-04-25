<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@page import="com.boco.xxzx.model.Menu"%>
<%@page import="com.boco.xxzx.model.Data"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/dtree.js"></script>
<link href="dtree.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/css.css"
	rel="stylesheet" type="text/css">
<title>角色授权</title>
</head>

<script>
var arrOP=[];
var opNum=0;
var arrData=[];
var dataNum=0;
var ch_text = '';
var ch_val = '';
function add_role_pow() {
	ch_val  = '';
	ch_text = '';
	for(n=0;n<document.form1.province.length;n++)
    if(document.form1.province[n].checked)
	for (m=0;m<dataNum;m++) 
		if (arrData[m][0]==document.form1.province[n].value) {
			ch_text = arrData[m][1];
			ch_val = arrData[m][0];
			
			for(i=0;i<document.form1.fun_id.length;i++)
			if(document.form1.fun_id[i].checked)
			for (j=0;j<opNum;j++)
				if (arrOP[j][0]==document.form1.fun_id[i].value) {
					form1.cur_choose.options[form1.cur_choose.length] = new Option(arrOP[j][2]+'['+ch_text+']');
					form1.cur_choose.options[form1.cur_choose.length-1].value = arrOP[j][0]+','+ch_val;
					break;
				}
			
			break;
		}
}
function remove_role_pow() {
	while (form1.cur_choose.selectedIndex != -1) {
		form1.cur_choose.options.remove(form1.cur_choose.selectedIndex);
	}
}
function clear_role_pow() {
	while (form1.cur_choose.length != 0) {
		form1.cur_choose.options.remove(0);
	}
}
function check() {
	if (document.form1.cur_choose.length == 0) {
		alert("权限列表不能为空！");
		return false;
	}
	
	for(i=0;i<document.form1.cur_choose.length;i++)
		form1.cur_choose.options[i].selected = true;
	form1.action = "rolePowAction.jsp?action=modify";
	form1.submit();
}
</script>

<body class="main_body">
	<form name="form1" id="form1" method="post" action="">
		<table class="main_table" cellpadding="0" cellspacing="0">
			<tr valign="top">
				<td width="100%">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td class="title_text"><div align="left">角色授权--${roleName}</div></td>
						</tr>
						<tr>
							<td class="title_text">&nbsp;</td>
						</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td width="40%"></td>
							<td align="center" valign="middle" width="22%">
								<fieldset style="width: 100%" align="center">
									<legend>数据</legend>
									<table border="0" cellpadding="0" cellspacing="1" width="100%"
										height="400">
										<tr>
											<td>
												<div
													style="position: absolute; height: 100%; width: 100%; overflow: auto">
													<div style="width: 100%;" id="divDataTree1">
														<script type="text/javascript">
	data = new dTree('data');
	data.add('0','-1','数据列表');
	<%
List<Data> dataList=(List<Data>)request.getAttribute("dataList");  
for(int i=0;i<dataList.size();i++){
%>
data.add(<%=dataList.get(i).getDataId()%>,0,'<input type="checkbox" name="dataId" value="<%=dataList.get(i).getDataId()%>"><%=dataList.get(i).getDataName()%>');
														<%}%>
															divDataTree1.innerHTML = data;
														</script>
													</div>
												</div>
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
							<td align="center" valign="middle" width="3%"><input
								type="button" value=" >  " title="添加" onClick="add_role_pow()"><br>
								<input type="button" value=" <  " title="删除"
								onClick="remove_role_pow()"><br> <input
								type="button" value=" << " title="清空" onClick="clear_role_pow()"></td>
							<td width="35%">
								<fieldset style="width: 100%" align="center">
									<legend>当前选择</legend>
									<select name="cur_choose" size="20" multiple id="cur_choose"
										style="">
										<option value=""></option>
									</select> <input type="hidden" name="roleId" value="${roleId}">
								</fieldset>
							</td>
						</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td align="center"><input type="button" name="Submit"
								value="提交" onClick="return check();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="reset" type="reset" id="reset" value="重置"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
