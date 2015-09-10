<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page import="com.loaderstudio.todolist.constants.Constants"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/style.css" rel="stylesheet">
	<title>Register</title>
</head>
<body>
	<%@ include file="header_clean.jsp"%>
	<div id="slogan">
		Register <span> page</span>
	</div>
	<div id="register_page">
		<div id="error">
			<c:if test="${not empty errorMessage}">
				<c:out value="${errorMessage}"/>
			</c:if>
		</div>
		<div id="enter_registeration_data">
			<img alt="enter_registeration_data" src="images/question.png">
			<span>
				<%= Constants.ENTER_REGISTER_INFORMATION %>
			</span>
		</div>
		<form name="registerForm" method="POST" action="<c:url value='/register'/>">
			<div id="login">
				<img id="img_login" alt="login" src="images/shade.png">
				<span>Login:</span>
				<img id="img_input" alt="login" src="images/form.png">
				<input type="text" name=<%= Constants.KEY_LOGIN %>>
			</div>
			<div id="email">
				<img id="img_email" alt="email" src="images/shade.png">
				<span>Email:</span>
				<img id="img_input" alt="email" src="images/form.png">
				<input type="text" name=<%= Constants.KEY_EMAIL %>>
			</div>
			<div id="password">
				<img id="img_password" alt="password" src="images/shade.png">
				<span>Password:</span>
				<img id="img_input" alt="password" src="images/form.png">
				<input type="password" name=<%= Constants.KEY_PASSWORD %>>
			</div>
			<div id="confirm_password">
				<img id="img_confirm_password" alt="confirm_password" src="images/shade_big.png">
				<span>Confirm <br/>password:</span>
				<img id="img_input" alt="confirm_password" src="images/form.png">
				<input type="password" name=<%= Constants.KEY_CONFIRM_PASSWORD %>>
			</div>
			<div id="register">
				<input type="submit" name="register" value="Register">
			</div>
			<div id="clear">
				<input type="reset" value="Clear">
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
	<img id="register_page_spot_left" alt="spot_left" src="images/foto.png">
	<img id="register_page_spot_right" alt="spot_right" src="images/pencils_2.png">
	<%@ include file="footer.jsp"%>
</body>
</html>