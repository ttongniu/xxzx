<%@page import="com.boco.xxzx.model.GroupMenu"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.boco.xxzx.model.Menu"%>
<%@page import="com.boco.xxzx.model.Dictionary"%>
<%@page import="java.util.List"%>
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
	var opNum = 0;
	var arrData = [];
	var dataNum = 0;
	var ch_text = '';
	var ch_val = '';
	function check() {
		var menuId=document.getElementsByName("menuId");
		if (menuId.length == 0) {
			alert("没有菜单被选中！");
			return false;
		}

		for (i = 0; i < menuId.length; i++)
			form1.menuId[i].selected = true;
		 $.ajax({  
 		    type: "POST",  
 		    url:"${pageContext.request.contextPath}/group/addGroupMenu.do",
 		    dataType: "text",
 		    data:$('#form1').serialize(),  
 		    async: false,
 		    error: function(XMLHttpRequest, textStatus, errorThrown) {
                 alert(XMLHttpRequest.status);
                 alert(XMLHttpRequest.readyState);
                 alert(textStatus);
             },
 		    success: function(result) {  
 		        window.location.reload();
 		    }  
 		  });
	}
	function checkStatus(no,chkBox){
		if (chkBox.checked==true) {
			var menuId=chkBox.value;
			 $.ajax({  
		 		    type: "POST",  
		 		    url:"${pageContext.request.contextPath}/group/addGroupMenu.do?menuId="+menuId+"&groupId=${groupId}",
		 		    dataType: "text",
		 		    async: false,
		 		    error: function(XMLHttpRequest, textStatus, errorThrown) {
		                 alert(XMLHttpRequest.status);
		                 alert(XMLHttpRequest.readyState);
		                 alert(textStatus);
		             },
		 		    success: function(result) {  
		 		    	 window.location.reload();
		 		    }  
		 		  });
		checkPar(chkBox);//当子目录选中时,父目录也选中
		var chks = document.getElementsByTagName('input');//得到所有input
		for(var i=0;i <chks.length;i++){
if(chks[i].name == 'menuId'){//得到所名为check的input
if(chks[i].className == no){//ID等于传进父目录ID时
	 
                chks[i].checked = chkBox.checked;//保持选中状态和父目录一致
                checkStatus(chks[i].value,chks[i]);//递归保持所有的子目录选中
                }
}
}
		}
		if (chkBox.checked==false) {
			var menuId=chkBox.value;
			 $.ajax({  
		 		    type: "POST",  
		 		    url:"${pageContext.request.contextPath}/group/deleteGroupMenu.do?menuId="+menuId+"&groupId=${groupId}",
		 		    dataType: "text",
		 		    async: false,
		 		    error: function(XMLHttpRequest, textStatus, errorThrown) {
		                 alert(XMLHttpRequest.status);
		                 alert(XMLHttpRequest.readyState);
		                 alert(textStatus);
		             },
		 		    success: function(result) {  
		 		    	 window.location.reload();		 		    }  
		 		  });
		checkPar(chkBox);//当子目录选中时,父目录也选中
		var chks = document.getElementsByTagName('input');//得到所有input
		for(var i=0;i <chks.length;i++){
if(chks[i].name == 'menuId'){//得到所名为check的input
if(chks[i].className == no){//ID等于传进父目录ID时
                chks[i].checked = chkBox.checked;//保持选中状态和父目录一致
                checkStatus(chks[i].value,chks[i]);//递归保持所有的子目录选中
                }
}
}
		}
		} 
	function checkPar(chkBox) {
		if(chkBox.name == 'menuId' && chkBox.checked && chkBox.className != 0){//判断本单击为选中,并且不是根目录,则选中父目录
			var chkObject = document.getElementById("ch"+chkBox.className);//得到父目录对象
			if (chkObject.selected==false) {		
			$.ajax({  
			    type: "POST",  
			    url:"${pageContext.request.contextPath}/group/addGroupMenu.do?menuId="+chkBox.value+"&groupId=${groupId}",
			    dataType: "text",
			    async: false,
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
	              alert(XMLHttpRequest.status);
	              alert(XMLHttpRequest.readyState);
	              alert(textStatus);
	          },
			    success: function(result) {  
			    	 window.location.reload();
			    }  
			  });
			}
			chkObject.checked=true;
			checkPar(chkObject);
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
		<table align="center">
			<tr>
				<td width="25%">
					<fieldset style="height: 400px; width: 70%;">
						<legend>操作</legend>
						<table border="0" cellpadding="0" cellspacing="1" width="100%"
							height="400">
							<tr>
								<td>
									<div
										style="height: 400px; width: 100%; overflow: auto; background-color: white;"
										id="divObjectTree1"></div>
								</td>
							</tr>
						</table>
						<script type="text/javascript">
d = new dTree('d');
d.add(0,-1,'');
<%
List<Menu> menuList=(List<Menu>)request.getAttribute("menuList");
List<GroupMenu> groupMenuList=(List<GroupMenu>)request.getAttribute("groupMenuList");
Boolean flag;
for(int i=0;i<menuList.size();i++){
	flag=true;
	for(int j=0;j<groupMenuList.size();j++){
      if(menuList.get(i).getMenuId()==groupMenuList.get(j).getMenuId()){
    	  flag=false;
      }
	}
	if(flag==true){
%>
d.add(<%=menuList.get(i).getMenuId()%>,<%=menuList.get(i).getParentId()%>,'<input type="checkbox" name="menuId" id="ch<%=menuList.get(i).getMenuId()%>" class="<%=menuList.get(i).getParentId()%>" value="<%=menuList.get(i).getMenuId()%>" onClick="checkStatus(<%=menuList.get(i).getMenuId()%>,this)"><input type="hidden" name="menuName" value="<%=menuList.get(i).getMenuName()%>"/><%=menuList.get(i).getMenuName()%>');
<%
	}else{
		%>
d.add(<%=menuList.get(i).getMenuId()%>,<%=menuList.get(i).getParentId()%>,'<input type="checkbox" name="menuId" id="ch<%=menuList.get(i).getMenuId()%>" class="<%=menuList.get(i).getParentId()%>" value="<%=menuList.get(i).getMenuId()%>" checked="checked" onClick="checkStatus(<%=menuList.get(i).getMenuId()%>,this)"><input type="hidden" name="menuName" value="<%=menuList.get(i).getMenuName()%>"/><%=menuList.get(i).getMenuName()%>');
						<%}
}%>
							divObjectTree1.innerHTML = d;
						</script>
					</fieldset> <input type="hidden" name="groupId" value="${groupId}">
				</td>
			</tr>
		</table>
		<br>
		<table align="center">
			<tr>
				<td><input type="button" name="Submit" value="提交"
					onClick="return check();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="reset" type="reset" id="reset" value="重置"></td>
			</tr>
		</table>
	</form>
</body>
</html>