<%@ page import="com.loaderstudio.todolist.constants.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/style.css" rel="stylesheet"> 
	<title>Add Task</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div id="slogan">
		Add task <span> page</span>
	</div>
	<div id="add_task_page">
		<div id="error">
			<c:if test="${not empty errorMessage}">
				<c:out value="${errorMessage}"/>
			</c:if>
		</div>
		<div id="enter_description_date">
			<img alt="enter_description_date" src="images/question.png">
			<span>
				<%= Constants.ENTER_DESCRIPTION_DATE %>
			</span>
		</div>
		<form name="addTask" ACTION="addTask">
			<div id="description">
				<img id="img_description" alt="description" src="images/shade_big.png">
				<span>Description:<span id="small">(max 255 symbol)</span></span>
				<img id="img_input" alt="description" src="images/form.png">
				<input type="text" name="<%=Constants.KEY_TASK_DESCRIPTOIN%>" id="taskDescription" value="New task" onclick="this.select();lcs(this)">
			</div>
			<div id="date">
				<img id="img_date" alt="date" src="images/shade.png">
				<span>Date:</span>
				<img id="img_input" alt="date" src="images/form.png">
				<input type="text" name="<%=Constants.KEY_TASK_DATE%>" value="day-month-year" onclick="this.select();lcs(this)">
			</div>
			<div id="select">
				<input type="submit" name="Add_Task" value="Add Task">
			</div>
			
		</form>
		<div id="back">
			<form action="<c:url value='/main'/>" id="formBack">
				<button type="submit" name="back" value="Back">Back</button>
				<span>Back</span>
			</form>
		</div>			
	</div>
	<img id="add_task_page_sticker_left" alt="sticker_left" src="images/sticker.png">
	<img id="add_task_page_clips_right" alt="clips_right" src="images/clips.png">
	<img id="add_task_page_cap_right" alt="cap_right" src="images/cap.png">
	<img id="add_task_page_marker_right" alt="marker_right" src="images/marker.png">
	<%@ include file="footer.jsp"%>
</body>
</html>