<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page import="com.loaderstudio.todolist.constants.Constants"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="css/style.css" rel="stylesheet">
	<title>Login</title>
</head>
<body>
	<%@ include file="header_clean.jsp"%>
	<div id="slogan">
		Login <span> page</span>
	</div>
	<div id="login_page">
		<div id="error">
			<c:if test="${not empty errorMessage}">
				<c:out value="${errorMessage}"/>
			</c:if>
		</div>
		<div id="enter_login_password">
			<img alt="enter_login_password" src="images/question.png">
			<span>
				<%= Constants.ENTER_LOGIN_PASSWORD %>
			</span>
		</div>
		<form name="loginForm" method="POST" action="<c:url value='/login'/>">
			<div id="login">
				<img id="img_login" alt="login" src="images/shade.png">
				<span>Login:</span>
				<img id="img_input" alt="login" src="images/form.png">
				<input type="text" name=<%= Constants.KEY_LOGIN %> value="">
			</div>
			<div id="password">
				<img id="img_password" alt="login" src="images/shade.png">
				<span>Password:</span>
				<img id="img_input" alt="login" src="images/form.png">
				<input type="password" name=<%= Constants.KEY_PASSWORD %> value="">
			</div>
			<div id="select">
				<input type="submit" name="Enter" value="Enter">
			</div>
		</form>
		<br/>
		<div id="back">
			<form action="<c:url value='/index.jsp'/>" id="formBack">
				<button type="submit" name="back" value="Back">Back</button>
				<span>Back</span>
			</form>
		</div>
	</div>
	<img id="login_page_spot_left" alt="spot_left" src="images/spot_left.png">
	<img id="login_page_spot_right" alt="spot_right" src="images/spot_right.png">
	<%@ include file="footer.jsp"%>
</body>
</html>