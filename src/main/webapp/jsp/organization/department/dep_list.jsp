<%@page import="com.boco.xxzx.model.Department"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></SCRIPT>
<LINK rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dtree.css">
<BODY 
style="SCROLLBAR-FACE-COLOR: #cccccc; SCROLLBAR-BASE-COLOR: #cccccc; SCROLLBAR-ARROW-COLOR: #000000; SCROLLBAR-SHADOW-COLOR: #e8e8e8; scrollbar-dark-shadow-color: #E8E8E8" 
background="images/left-bg.gif" leftMargin=0 topMargin=10 
bgColor=#f0faff
>
<DIV class=dtree>
<SCRIPT type=text/javascript>

d = new dTree('d');
d.add(0,-1,'');
<%
List<Department> depList=(List<Department>)request.getAttribute("depList");
for(int i=0;i<depList.size();i++){
%>
d.add(<%=depList.get(i).getDepId()%>,<%=depList.get(i).getParentId()%>,'<%=depList.get(i).getDepName()%>','${pageContext.request.contextPath}/department/showDeptInfo.do?depId=<%=depList.get(i).getDepId()%>','','dinfo');
<%
}
%>
	document.write(d);

</SCRIPT>
</DIV></BODY>
</html>