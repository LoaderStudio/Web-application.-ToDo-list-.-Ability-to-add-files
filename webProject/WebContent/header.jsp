<%@page import="com.loaderstudio.todolist.constants.Constants"%>
<%@ taglib uri="/jstl/core" prefix="c"%> 

<div id="header_page">
	<div id="hello_guest">
		<c:if test="${empty user.login}">
			<img alt="login" src="images/question.png">
			<span>
				<%=Constants.HELLO_GUEST %>
			</span>
		</c:if>
		
		<c:if test="${!empty user.login}">
			<div id="user">
				<img alt="user" src="images/shade_blue.png">
				<span>
					User: 
					<strong>
						${user.login}
					</strong>
				</span>
			</div>
		</c:if>
	</div>
	
	<c:if test="${empty user.login || user.login == ''}">
		<div id="login">
			<img alt="login" src="images/button.png">
			<a href="<c:url value='/login.jsp'/>">Login</a>
		</div>
		<div id="register">
			<img alt="login" src="images/button.png">
			<a href="<c:url value='/register.jsp'/>">Register</a>
		</div>
	</c:if>
	<c:if test="${!empty user.login}">
		<div id="logout">
			<img alt="user" src="images/button_blue.png">
			<a href="<c:url value='/logout'/>" style="float: right;">Logout</a>
		</div>
	</c:if>	
</div>