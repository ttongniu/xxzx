<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看当前流程图</title>
</head>
<body>
<!-- 1.获取到规则流程图 -->
<div style="width: 800;height: 600"><img style="position: absolute;top: 0px;left: 0px;" src="${pageContext.request.contextPath}/wf/viewImage.do?deploymentId=${deploymentId }&imageName=${imageName }"></div>

<!-- 2.根据当前活动的坐标，动态绘制DIV -->
<div style="position:absolute; border:2px solid red;top:${y}px; left:${x}px; width:${width }px; height:${height}px;"></div>

<!-- <span> <input type="button" name="button" id="button" onclick="javascript:window.history.go(-1);" value="返回" /> </span> -->

</body>
</html>