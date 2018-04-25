<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--引入自定义css  -->
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css">
<!--引入jQuery.js  -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td height="30"><div id='KB1Parent' class='parent'><img src="${pageContext.request.contextPath}/images/right_sw_1_1.png" width="257" height="25"></div></td>
    </tr>
    <tr> 
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
  </table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="30"><font color="#FF0000"></font>&nbsp;
        <p><img src="${pageContext.request.contextPath}/images/arrow-1.gif" width="11" height="12">&nbsp;<a href="#" class="g">部署信息管理列表</a></p>      </td>
    </tr>
    <tr>
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
    <tr>
      <td bgcolor="#ebebf1">&nbsp;</td>       
    </tr>
  </table>

<table width="100%" class="data_table" cellpadding="0" cellspacing="0">
 
  <tr bgcolor="#ECF0F4"> 
    <td class="data_table_title"><div align="center">序号</div></td>
	<td class="data_table_title" width="20%" height="20"><div align="center">部署ID</div></td>
    <td class="data_table_title" width="30%"><div align="center">流程名称</div></td>
    <td class="data_table_title" width="27%"><div align="center">发布时间</div></td>
    <td class="data_table_title" width="21%"><div align="center">操作</div></td>
  </tr>
  <c:choose>
  <c:when test="${ not empty deploymentList }">
  <c:forEach items="${deploymentList }"  var="deployment" varStatus="status">
  <tr bgcolor="#FFFFFF"> 
    <td class="data_table_content_left"><div align="center">${status.count }</div></td>
	<td class="data_table_content_left" width="20%" height="20"><div align="center">${deployment.id }</div></td>
    <td class="data_table_content_left" width="30%"><div align="center">${deployment.name }</div></td>
    <td class="data_table_content_left" width="27%"><div align="center"><fmt:formatDate value="${deployment.deploymentTime }" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" /> </div></td>
    <td class="data_table_content_left" width="21%">
    <div align="center">
    <a href="${pageContext.request.contextPath}/wf/delDeployment.do?deploymentId=${deployment.id }" onclick="javascript:return p_del()"><img alt="删除" title="删除" src="${pageContext.request.contextPath}/images/delete.png" width="25" height="25" ></a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/wf/download.do?filePath=${deployment.filePath }" ><img alt="下载"  title="下载" src="${pageContext.request.contextPath}/images/download.png" width="25" height="25" ></a>
    </div>
    </td>
  </tr>
</c:forEach>
</c:when>
<c:otherwise>
   <tr bgcolor="#FFFFFF"> 
    <td class="data_table_content_left" colspan="5" height="20" >
    <div align="center">暂无部署信息列表</div>
    </td>
  </tr>
</c:otherwise>
 </c:choose>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="30"><font color="#FF0000"></font>&nbsp;
        <p><img src="${pageContext.request.contextPath}/images/arrow-1.gif" width="11" height="12">&nbsp;<a href="#" class="g">流程定义信息列表</a></p>      </td>
    </tr>
    <tr>
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
    <tr>
      <td bgcolor="#ebebf1">&nbsp;</td>       
    </tr>
  </table>

<table width="100%" class="data_table" cellpadding="0" cellspacing="0">
  <tr bgcolor="#ECF0F4"> 
    <td class="data_table_title"><div align="center">序号</div></td>
	<td class="data_table_title" width="10%" height="20"><div align="center">流程定义ID</div></td>
    <td class="data_table_title" width="10%" height="20"><div align="center">名称</div></td>
    <td class="data_table_title" width="10%" bgcolor="#ebebf1"><div align="center">流程定义Key</div></td>
    <td class="data_table_title" width="10%" bgcolor="#ebebf1"><div align="center">版本</div></td>
    <td class="data_table_title" width="18%"><div align="center">规则文件名称</div></td>
    <td class="data_table_title" width="20%"><div align="center">规则图片名称</div></td>
    <td class="data_table_title" width="10%"><div align="center">部署ID</div></td>
    <td class="data_table_title" width="10%"><div align="center">操作</div></td>
  </tr> 
  
  <c:choose>
  <c:when test="${not empty processDefinitionList }">
  <c:forEach  items="${processDefinitionList }" var="processDefinition" varStatus="status">
  <tr bgcolor="#FFFFFF"> 
    <td class="data_table_content_left"><div align="center">${status.count }</div></td>
	<td class="data_table_content_left" width="10%" height="20"><div align="center">${processDefinition.id }</div></td>
    <td class="data_table_content_left" width="10%" height="20"><div align="center">${processDefinition.name }</div></td>
    <td class="data_table_content_left" width="10%" ><div align="center">${processDefinition.key }</div></td>
    <td class="data_table_content_left" width="10%" ><div align="center">${processDefinition.version }</div></td>
    <td class="data_table_content_left" width="18%"><div align="center">${processDefinition.resourceName }</div></td>
    <td class="data_table_content_left" width="20%"><div align="center">${processDefinition.diagramResourceName }</div></td>
    <td class="data_table_content_left" width="10%"><div align="center">${processDefinition.deploymentId }</div></td>
    <td class="data_table_content_left" width="10%"><div align="center"><a   href="${pageContext.request.contextPath}/wf/viewImage.do?deploymentId=${processDefinition.deploymentId }
			&imageName=${processDefinition.diagramResourceName }" 
			onClick="javascript:window.open(this.href,'_blank','height=600,width=800,top=150,left=400,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');return false" ><img alt="查看流程图"  title="查看流程图" src="${pageContext.request.contextPath}/images/process.png" width="25" height="25" ></a>  </div></td>
  </tr>
  </c:forEach>
  </c:when>
 <c:otherwise>
     <tr bgcolor="#FFFFFF"> 
    <td class="data_table_content_left" colspan="9" height="20">
    <div align="center">暂无流程定义信息 </div>
    </td>
    </tr>
 </c:otherwise>
   </c:choose>
</table>
   
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="30"><font color="#FF0000"></font>&nbsp;
        <p><img src="${pageContext.request.contextPath}/images/arrow-1.gif" width="11" height="12">&nbsp;<a href="#" class="g">部署流程定义</a></p>      </td>
    </tr>
    <tr>
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
    <tr>
      <td bgcolor="#ebebf1">&nbsp;</td>       
    </tr>
  </table>
 <form action=""  method="post"  enctype="multipart/form-data"  id="form1">
 <table width="22%" border="0" cellpadding="0" cellspacing="0" >
  <tr > 
    <td width="50%" height="20"><div align="right"><font color="blue">流程名称：</font> </div></td>
	<td width="50%" height="20"><input type="text" id="filename" name="filename"></td>
    
  </tr>  
  <tr>
  <td width="50%" height="20"><div align="right"><font color="blue">流程文件：</font></div></td>
	<td width="50%" height="20"><input type="file"  id="file" name="file"  onchange="checkFileExt(this.value)"></td>
  </tr>	
   <tr>
   <td  height="20"></td>
  <td  height="20"><div align="left"><input id="bs" type="button" style="color: blue" value="部署流程" ></div></td>
  </tr>		
</table>
</form> 

<script type="text/javascript">

function p_del() {
	var msg = "您真的确定要删除吗？\n\n请确认！";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
} 

  $("#bs").click(
		function (){
			var fname=$("#filename").val();
			var file=$("#file").val();
			
			if(fname.length==0){
				alert("流程名称不能为空!");
				$("#filename").focus();
				return;
			}
			
			if(file.length==0){
				alert("请选择文件!");
				$("#file").focus();
				return;
			}
			 document.forms['form1'].action="${pageContext.request.contextPath}/wf/saveDeploy.do";
			 document.forms['form1'].submit();
		}
		);

//判断上传文件的扩展名
function checkFileExt(filename)
{
 var flag = false; //状态
 var arr = ["zip"];
 //取出上传文件的扩展名
 var index = filename.lastIndexOf(".");
 var ext = filename.substr(index+1);
 //循环比较
 for(var i=0;i<arr.length;i++)
 {
  if(ext == arr[i])
  {
   flag = true; //一旦找到合适的，立即退出循环
   break;
  }
 }
 //条件判断
 if(flag)
 {
     $("#bs").removeAttr("disabled");
 }else
 {
	 $("#bs").attr("disabled","disabled");
	 alert("文件格式不符。仅支持zip格式文件！！！");
  
 }
}

</script>


</body>
</html>