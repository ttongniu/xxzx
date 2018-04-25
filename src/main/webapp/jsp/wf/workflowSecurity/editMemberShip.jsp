<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--引入自定义css  -->
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css">
<!--引入jQuery.js  -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<!--引入lab2.js  -->
<script src="${pageContext.request.contextPath}/js/lab2.js"></script>
<!--引入layer.js  -->
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>

<style type="text/css">
/* .s1{width:120px;}
#d1{width:800px;height:400px;background-color:#F5DEB3;margin:0 auto;}
#d3{padding-left:50px;} */
fieldset
{
  border: 1px solid #61B5CF;
  margin-top: 16px;
  padding: 8px;
}
legend
{
  font: bold 12px Arial, Helvetica, sans-serif;
  color: #00008B;
  background-color: #FFFFFF;
}

</style>

</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td height="30"><div id='KB1Parent' class='parent'><img src="${pageContext.request.contextPath}/images/wfPrivilege.png" width="257" height="25"></div></td>
    </tr>
    <tr> 
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
</table>
  <br><br><br>
<center side="font_title">  <font color="blue" > 权限用户组管理</font>
  <hr align="center" width="600" size="1" noshade="noshade" color="#336699" />
</center>
<center>
<fieldset >
<legend>权限用户组基本信息</legend>
<table width="800" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#003366" >
  
  <tr bgcolor="#F5DEB3">
    <td width="30%" align="right" >用户组名称：</td>
    <td width="70%"  >
      <input size="50" type="text" name="id" id="id" readonly="readonly" value="${group.id }" />     
    </td>
  </tr>
  <tr bgcolor="#F5DEB3">
    <td  align="right" >用户组描述：</td>
    <td > <textarea rows="4" cols="40" id="name" name="name"  readonly="readonly">${group.name }</textarea> </td>
  </tr>
  
  <!--  <tr>
    <td colspan="2" align="center" bgcolor="#FFFFFF">

    </td>
  </tr> -->
</table>
</fieldset>

   <!--  <div id="d1"> -->
    <fieldset>
    <legend>权限用户组赋权 </legend>
   <!--  <div id="d3"> -->
	   <form  id="form1" name="form1" method="post">
		<table cellpadding="0" cellspacing="8" align="center">
		    <input size="50" type="hidden" name="id" id="id" readonly="readonly" value="${group.id }" />    
			<tr>
				<td width="100">可选用户</td>
				<td width="100">&nbsp;</td>
				<td width="100">权限用户</td>
			</tr>
			<tr>
				<td >
				   
					<select id="s1" name="s1" style="width:150px; height:220px;" multiple="multiple">
						<c:forEach items="${userLeftList }" var="userLeft">
						<option   value="${userLeft.id }">${userLeft.employeeName }</option>
						</c:forEach>
					</select>
				</td>
				<td align="center">
					<p><input id="b1" type="button" class="s1" value="----&gt;" /></p>
					<!-- <p><input type="button" id="b2" class="s1" value="--&gt;&gt;" /></p> -->
					<p><input type="button" id="b3" class="s1" value="&lt;----" /></p>
					<p><input type="button" id="b4" class="s1" value="&lt;&lt;--" /></p>
				</td>
				<td><select id="s2" name="s2" style="width:150px;height:220px;" multiple="multiple">
				       <c:forEach items="${userRightList }" var="userRight">
						<option   value="${userRight.id }">${userRight.employeeName }</option>
					   </c:forEach>
				    </select>
				</td>
			</tr>
			
			<tr>
			<td colspan="3" align="left">按名字：<input type="text"  id="findname" name="findname"> <input type="button" name="but2" id="but2"  value="查询" /></td>
		
			
			</tr>
			<tr></tr>
			<!-- <tr>
				<td colspan="3" align="center">
				     <input type="button" name="but1" id="but1" value="保存"  /> &nbsp;&nbsp;
                     <input type="button" name="but" id="but"  value="返回" />
				</td>
			</tr> -->
			
		</table>
	</form>
	<!-- </div> -->
  </fieldset>
  </center>
<!-- </div> -->
    <div align="center">  <span >
       <input type="button" name="but1" id="but1" value="保存"  /> &nbsp;&nbsp;
       <input type="button" name="but" id="but"  value="返回" />
       </span>
     </div>
    <script type="text/javascript">  
    var left_id=[],left_name=[];
	var flag=0;
    $("#but2").click(
    		//按照名字查询
        function (){
        	
        	 var  sel=  document.getElementById("s1");
        	if(flag==0){
        		for(var m=0;m<sel.length;m++){
        			left_id[m]=	sel.options[m].value;
        			left_name[m]=sel.options[m].text;
        		}
        		flag=1;
        	}
        	
        	var findname=$("#findname").val();
        	//alert("***"+findname);
        	sel.length = 0;
        	for(var i=0;i<left_id.length;i++){
        		var no = new Option();
                // 筛选左侧列表名单
        		var name_left = left_name[i];
        		 if(name_left.indexOf(findname)!=-1)
         	    {
                     no.value = left_id[i];
                     no.text  = left_name[i];
                     sel.options[sel.options.length] = no;
         		} 
        		 else if(findname=="")
         		{
                     no.value = left_id[i];
                     no.text  = left_name[i];
                     sel.options[sel.options.length] = no;
         		}/**/
        	}
        }		
    );
      
    $("#but").click(
         function (){
        	 window.location.href="${pageContext.request.contextPath}/wfSecurity/showWfGroups.do";
         }		
    
    );
    
    $("#but1").click(
       function  (){
    	   var  sel=  document.getElementById("s2");
    	   
    	  if(sel.options.length==0){
    		  alert("至少选一个！！");
    		  return;
    	  }else{
    		  for(var i=0;i<sel.options.length;i++){
    	   	    	sel.options[i].selected=true;
    	   	   }
    		  $.ajax({  
  			    data:"id="+$("#id").val()+"&s2="+ $("#s2").val(),  //
  			    type:"post",  
  			    dataType: 'json',  
  			    url:"${pageContext.request.contextPath}/wfSecurity/saveMemberShip.do",  
  			    error:function(data){  
  			    	layer.msg(data.mag,{icon: 2,time: 2*1000});
  			    },  
  			    success:function(data){ 
  			    	if(data.mag=="success"){
  			    		layer.msg('保存成功！！！',{icon: 1,time: 2*1000});
  			    	}else{
  			    		layer.msg('保存失败！！！',{icon: 2,time: 2*1000});
  			    	}
  			    	
  			    }  
  			    });  
    	  }
       }		
    ); 
    </script>
</body>
</html>