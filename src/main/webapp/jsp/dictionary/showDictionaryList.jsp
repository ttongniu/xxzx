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
      <td height="30"><div id='KB1Parent' class='parent'><img src="${pageContext.request.contextPath}/images/dictionary.png" width="257" height="25"></div></td>
      <td height="10"><span> <a href="${pageContext.request.contextPath}/dict/toSaveDictionary.do"><img alt="新建" title="新建"  src="${pageContext.request.contextPath}/images/add.png" width="25" height="25"></a> </span></td>
    </tr>
   
  </table>
  <form  id="form1" name="form1" method="post"    action="">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">    
    <tr>
    <td>  
    <SPAN align="left">       
   <font color="blue">字典类别代码：</font> 
                  
              <c:choose>
              <c:when test="${empty code }">
                 <select name="code" id="code">
                        <option value="all"  selected >all</option>
			            <c:forEach  items="${codeList }" var="coder" >
			            <option value="${coder }" >${coder }</option>
			            </c:forEach>
		          </select>
              </c:when>
              <c:otherwise>
                   <select name="code" id="code">
                         <option value="all"   >all</option>
			            <c:forEach  items="${codeList }" var="coder" >
			            <option value="${coder }" <c:if test="${coder==code }"> selected </c:if> >${coder }</option>
			            </c:forEach>
		            </select>
              </c:otherwise>
          </c:choose> 
</SPAN>
       &nbsp;&nbsp; <!-- <input name="Submit" type="submit"  value="GO"> -->
       
        <font color="blue">数据值：</font> 
        <input id="value" name="value" type="text"  value="${value }">
       <img  title="查找" onclick="search();"  src="${pageContext.request.contextPath}/images/find.png" width="20" height="20" >
      </td>
    </tr>
    <tr>
      <td >&nbsp;</td>       
    </tr>
  </table>
</form>

<table width="100%" class="data_table" cellpadding="0" cellspacing="0">
  <tr bgcolor="#ECF0F4"> 
    <td class="data_table_title"><div align="center">序号</div></td>
    <td class="data_table_title" width="10%" height="20"><div align="center">字典类别代码</div></td>
    <td class="data_table_title" width="10%"><div align="center">数据键</div></td>
	<td class="data_table_title" width="10%" height="20"><div align="center">数据值</div></td>
    <td class="data_table_title" width="10%"><div align="center">数据描述</div></td>
    <td class="data_table_title" width="15%"><div align="center">创建时间</div></td>
    <td class="data_table_title" width="20%"><div align="center">最后修改人</div></td>
    <td class="data_table_title" width="20%"><div align="center">操作</div></td>
  </tr>
    
    <c:choose>
    <c:when test="${ not empty  dictionaries }">
         <c:forEach items="${dictionaries }"  var="dictionarie" varStatus="status">
		  <tr bgcolor="#FFFFFF"> 
		    <td class="data_table_content_left"><div align="center">${status.count }</div></td>
		    <td class="data_table_content_left" width="10%" height="20"><div align="center">${dictionarie.code }</div></td>
		    <td class="data_table_content_left" width="10%"><div align="center">${dictionarie.key }</div></td>
			<td class="data_table_content_left" width="10%" height="20"><div align="center">${dictionarie.value }</div></td>
		    <td class="data_table_content_left" width="10%"><div align="center">${dictionarie.description }</div></td>
		    <td class="data_table_content_left" width="15%"><div align="center"><fmt:formatDate value="${dictionarie.createTime }" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" /> </div></td>
		    <td class="data_table_content_left" width="20%"><div align="center">${dictionarie.lastModifyUser.employeeName }</div></td>
		    <td class="data_table_content_left" width="20%">
		    <div align="center">
		       <a href="${pageContext.request.contextPath}/dict/toEditDictionary.do?id=${dictionarie.id}"  > <img alt="修改" title="修改" src="${pageContext.request.contextPath}/images/update.png" width="25" height="25"></a>  &nbsp;&nbsp;&nbsp;
               <a href="${pageContext.request.contextPath}/dict/removeDictionary.do?id=${dictionarie.id}"  onclick="javascript:return p_del()"><img alt="删除" title="删除" src="${pageContext.request.contextPath}/images/delete.png" width="25" height="25"></a>            
		    </div>
		    </td>
		  </tr>
         </c:forEach>
    </c:when>
    <c:otherwise>
          <tr bgcolor="#FFFFFF"> 
		    <td class="data_table_content_left" colspan="8" height="20" >
		       <div align="center">
		         <b>暂无数据字典信息 ！ </b>
		       </div>
		   </td>
		  </tr>
    </c:otherwise>
    </c:choose>
</table>

<script type="text/javascript">
function p_del() {
	var msg = "您真的确定要删除吗？\n\n请确认！";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
} 
$("#code").change(function(){
	//alert("***");
	 document.forms['form1'].action="${pageContext.request.contextPath}/dict/listDictionary.do";
	 document.forms['form1'].submit();
 });
function search(){
	 document.forms['form1'].action="${pageContext.request.contextPath}/dict/listDictionary.do";
	 document.forms['form1'].submit();
}

</script>


</body>
</html>