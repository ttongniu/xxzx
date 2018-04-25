<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.*,java.util.Date"%>
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
<!--时间插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script> 
</head>
<body>
  <table width="90%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td width="50%"><img src="${pageContext.request.contextPath}/images/leave.png" width="221" height="23"></td>
    <td width="50%" valign="bottom">[ <a href="${pageContext.request.contextPath}/leaveBill/tosaveLeaveBill.do?groupName=领导A"><b>新建请假单</b></a> 
      ]</td>
  </tr>
 <%--  <tr bgcolor="#FFD595"> 
    <td colspan="2"><img src="${pageContext.request.contextPath}/images/space.gif" width="1" height="1"></td>
  </tr> --%>
</table>
  <form  method="post" id="form1"  >
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
    <%-- <tr>
      <td height="30"><font color="#FF0000"></font>&nbsp;
        <p><img src="${pageContext.request.contextPath}/images/arrow-1.gif" width="11" height="12">&nbsp;<a href="#" class="g">流程用户组列表</a></p>      </td>
    </tr> --%>
     <tr>
            <td width="305" valign="bottom"><div align="right"> <font color="blue">查看</font>
            <c:choose>
            <c:when test="${empty state }">
            <select name="state" id="state">
            <option value="all" >全部</option>
            <option value="2" >已归档</option>
            <option value="1" selected>正在流转</option>
            <option value="0" >初始录入</option>
            </select>
            </c:when>
            <c:otherwise>
           <select name="state" id="state">
            <option value="all" <c:if test="${state=='all' }">selected</c:if> >全部</option>
            <option value="2" <c:if test="${state=='2' }">selected</c:if>>已归档</option>
            <option value="1" <c:if test="${state=='1' }">selected</c:if>>正在流转</option>
            <option value="0" <c:if test="${state=='0' }">selected</c:if>>初始录入</option>
           </select>
            </c:otherwise>
         
          </c:choose> 
        </div></td>    
    </tr>
    
   <%--  <tr>
      <td bgcolor="#CCCCCC"><img src="${pageContext.request.contextPath}/images/space.gif" width="5" height="1"></td>
    </tr>
    <tr>
      <td >&nbsp;</td>       
    </tr> --%>
  </table>
  
 <center>

  <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
    <TBODY>
       <tr><td>&nbsp;</td></tr>
      <tr>
          <TD  noWrap>
            <SPAN align="left">
        
              <font color="blue">请输入 提交时间 的时间段：</font><strong>从</strong>

    <c:if test="${not empty sdate }">
        <input id="sdate"  name="sdate" class="Wdate" value="${sdate }" size="10"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'edate\')}'})" />  
    </c:if>
    <c:if test="${empty sdate  }">
        <input id="sdate"  name="sdate" class="Wdate" value="2017-01-01" size="10"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'edate\')}'})" />  
    </c:if>
——&nbsp;<strong>到</strong>
  <c:if test="${not empty edate }">
   <input id="edate" name="edate" class="Wdate" value="${edate }"  size="10"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'sdate\')}',startDate:'#F{$dp.$D(\'sdate\',{d:+1})}'})" />  
  </c:if>
  <c:if test="${empty edate }">
  <%Date log_d=new Date();
  String log_year   = Integer.toString(log_d.getYear()+1900);
  int log_imonth  = log_d.getMonth()+1;
  int log_iday    = log_d.getDate();
  String log_month  = "";
  String log_day    = "";

  if (log_imonth<10)
    {log_month  = "0" + Integer.toString(log_d.getMonth()+1);}
  else
    {log_month  = Integer.toString(log_d.getMonth()+1);}

  if (log_iday<10)
    {log_day    = "0" + Integer.toString(log_d.getDate());}
  else
    {log_day    = Integer.toString(log_d.getDate());}

  String log_date   = log_year +"-"+ log_month +"-"+ log_day;   
  %>
  <input id="edate" name="edate" class="Wdate" value="<%=log_date%> " size="10" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'sdate\')}',startDate:'#F{$dp.$D(\'sdate\',{d:+1})}'})" />   
  </c:if>
——&nbsp; <font color="blue">单据编号关键字：</font><input name="id" id="id"  type="text" value="${id }" class="textfield-ffffff"  size="15">
        </SPAN>
          <!-- <input name="Submit" type="submit"  value="GO"> -->
          &nbsp;&nbsp;&nbsp;
       <img  title="查找" onclick="search();"  src="${pageContext.request.contextPath}/images/find.png" width="20" height="20" >
          </TD>
      </tr>
        <tr><td>&nbsp;</td></tr>
    </TBODY>
  </TABLE>

  </center>
</form>
<table width="100%" class="data_table" cellpadding="0" cellspacing="0">
 
  <tr bgcolor="#ECF0F4"> 
    <td class="data_table_title" nowrap><div align="center">序号</div></td>
	<td class="data_table_title" nowrap width="10%" height="20"><div align="center">请假单号</div></td>
    <td class="data_table_title" nowrap width="10%"><div align="center">请假天数</div></td>
    <td class="data_table_title" nowrap  width="20%"><div align="center">请假事由</div></td>
    <td class="data_table_title" nowrap  width="20%"><div align="center">请假备注</div></td>
    <td class="data_table_title" nowrap  width="10%"><div align="center">状态</div></td>
    <td class="data_table_title" nowrap  width="10%"><div align="center">创建时间</div></td>
    <td class="data_table_title" nowrap  width="20%"><div align="center">操作</div></td>
  </tr>
    
    <c:forEach  items="${leaveBills }" var="leaveBill" varStatus="status">
    <tr bgcolor="#FFFFFF"> 
    <td class="data_table_content_left"><div align="center">${status.count }</div></td>
	<td class="data_table_content_left" width="10%" height="20"><div align="center">${leaveBill.id }</div></td>
    <td class="data_table_content_left" width="10%"><div align="center">${leaveBill.days }</div></td>
    <td class="data_table_content_left" width="20%"><div align="center">${leaveBill.content }</div></td>
    <td class="data_table_content_left" width="20%"><div align="center">${leaveBill.remark }</div></td>
    <td class="data_table_content_left" width="10%">
    <div align="center">
      <c:choose>
        <c:when test="${leaveBill.state=='0' }">
                                  初始录入
        </c:when>
         <c:when test="${leaveBill.state=='1' }">
         <font color="green">流转中</font>       
        </c:when>
        <c:otherwise>
         <font color="red">已归档 </font>       
        </c:otherwise>
     </c:choose> 
    </div>
    </td>
     <td class="data_table_content_left" width="10%"><div align="center"><fmt:formatDate value="${leaveBill.createTime }" dateStyle="long"  type="date" pattern="yyyy-MM-dd HH:mm:ss"  /> </div></td>
    <td class="data_table_content_left" width="20%">
    <div align="center">
     <c:choose>
        <c:when test="${leaveBill.state=='0' }">
                 <a href="${pageContext.request.contextPath}/leaveBill/toEditLeaveBill.do?id=${leaveBill.id}&groupName=领导A"><img alt="修改"  title="修改" src="${pageContext.request.contextPath}/images/update.png" width="25" height="25" ></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 <a href="${pageContext.request.contextPath}/leaveBill/deleteLeaveBill.do?id=${leaveBill.id}"  onclick="javascript:return p_del()"><img alt="删除" title="删除" src="${pageContext.request.contextPath}/images/delete.png" width="25" height="25" ></a>
        </c:when>
         <c:when test="${leaveBill.state=='1' }">
                 <a href="${pageContext.request.contextPath}/leaveBill/viewHisComment.do?id=${leaveBill.id}"  ><img alt="查看审核记录"  title="查看审核记录" src="${pageContext.request.contextPath}/images/viewProcessAnnotation.png" width="25" height="25" ></a>         
        </c:when>
        <c:otherwise>
                 <a href="${pageContext.request.contextPath}/leaveBill/viewHisComment.do?id=${leaveBill.id}"><img alt="查看审核记录"  title="查看审核记录" src="${pageContext.request.contextPath}/images/viewProcessAnnotation.png" width="25" height="25" ></a>
        </c:otherwise>
     </c:choose>
    
    </div>
    </td>
    </tr> 
    </c:forEach> 
    
</table>


<script type="text/javascript">
		$("#state").change(function(){
			//alert("***");
			 document.forms['form1'].action="${pageContext.request.contextPath}/leaveBill/showLeaveBillsByParameter.do";
			 document.forms['form1'].submit();
		  });

function search(){
	 document.forms['form1'].action="${pageContext.request.contextPath}/leaveBill/showLeaveBillsByParameter.do";
	 document.forms['form1'].submit();
}

function p_del() {
	var msg = "您真的确定要删除吗？\n\n请确认！";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
} 

</script>


</body>
</html>