<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <td height="30"><div id='KB1Parent' class='parent'><img src="${pageContext.request.contextPath}/images/right_sw_1_1.gif" width="257" height="25"></div></td>
    </tr>
    <tr> 
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
</table>
  
  <br><br><br>
<center side="font_title">  <font color="blue" > 权限用户添加</font>
  <hr align="center" width="600" size="1" noshade="noshade" color="#336699" />
</center>
<table width="800" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#003366">
<form id="form1" name="form1" method="post" action="${pageContext.request.contextPath}/wfSecurity/saveWfUser.do">
  <tr>
    <td width="30%" align="right" bgcolor="#FFFFFF">用户名称：</td>
    <td width="70%"  bgcolor="#FFFFFF">
      <input size="50" type="text" name="id" id="id"  />
      <span id="result"> </span>
    </td>
    
  </tr>
  <tr>
    <td width="30%"  align="right" bgcolor="#FFFFFF">用户邮箱：</td>
    <td width="70%"  bgcolor="#FFFFFF">
      <input name="type" type="email" id="email" size="50" />
    </td>
  </tr> 
  

  <tr>
    <td colspan="2" align="center" bgcolor="#FFFFFF">
    <input type="submit" name="button" id="button" value="保存" /> &nbsp;&nbsp;
    <input type="button" name="button" id="button" onclick="javascript:history.go(-1)" value="返回" />
    </td>
    
  </tr>
  </form>
</table>

<script type="text/javascript">

function cheakGroup(){  
    $.ajax({  
    data:"id="+$("#id").val(),  
    type:"post",  
    dataType: 'json',  
    url:"${pageContext.request.contextPath}/wfSecurity/cheakwfGroup.do",  
    error:function(data){  
        alert("出错了！！:"+data.mag);  
    },  
    success:function(data){  
       // alert("success:"+data.mag);  
        $("#result").html(data.mag) ;  
    }  
    });  
}  

</script>


</body>
</html>