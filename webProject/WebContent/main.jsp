<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/style.css" rel="stylesheet">
	<title>Main page</title>
</head>
<body>
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/>
		<hr>
	</c:if>
	<%@ include file="header.jsp"%>
	<div id="slogan">
		Welcome to <span>main page!</span>
	</div>
	<%@ include file="footer.jsp"%>
	<img id="main_page_plan" alt="plan.png" src="images/plan.png">
</body>
</html>