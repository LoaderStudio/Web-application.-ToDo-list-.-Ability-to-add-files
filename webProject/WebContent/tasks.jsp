<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="css/style.css" rel="stylesheet">
	<script type="text/javascript" src="js/tasks.js" ></script>
	<title>Task List</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div id="slogan">
		Task <span> page</span>
	</div>
	<div id="tasks_page">
		<div id="menu">
			<img alt="menu" src="images/notebook.png">
			<form name="timeForm" ACTION="main">
				<input type=hidden name=<%= Constants.PARAM_REFERENCE %> value="default">
				<img id="today_img" alt="checkbox" src="images/checkbox.png">
				<a id="today" href="JavaScript:sendTimeForm('TODAY')">Today</a> &nbsp;&nbsp;
				<img id="tomorrow_img" alt="checkbox" src="images/checkbox.png">
				<a id="tomorrow" href="JavaScript:sendTimeForm('TOMORROW')">Tomorrow</a> &nbsp;&nbsp;
				<img id="someday_img" alt="checkbox" src="images/checkbox.png">
				<a id="someday" href="JavaScript:sendTimeForm('SOMEDAY')">Someday</a> &nbsp;&nbsp;
				<img id="fixed_img" alt="checked" src="images/checked.png">
				<a id="fixed" href="JavaScript:sendTimeForm('FIXED')">Fixed</a> &nbsp;&nbsp;
				<a id="recycle" href="JavaScript:sendTimeForm('RECYCLE')"><img id="recycle_img" alt="recycle" src="images/bin.png"></a> 
			</form>
		</div>
		<div id="day">
					<img name="day_img" id="day_img" alt="day" src="images/${reference}.png">		
		</div>
		<div id="taskForm">
			<%-- <form name="fileForm" enctype="multipart/form-data" action="fileUpload" method="post"> --%>
			<form name="taskForm" ACTION="fixRemove">
				<table id="tableTask" cellpadding="0" cellspacing="0">
					<tr>
						<th width="5%"></th>
						<c:choose>
							<c:when test="${reference == 'TODAY'}">
								<th width="50%">
									<div id="task_1">
										<img name="shade" id="shade_img" alt="shade_img" src="images/shade.png">
										<span>
											Task
										</span>
									</div>
								</th>
					    	</c:when>
					    	<c:when test="${reference == 'TOMORROW'}">
					    		<th width="50%">
					    			<div id="task_1">
						    			<img name="shade" id="shade_img" alt="shade_img" src="images/shade.png">
						    			<span>
						    				Task
						    			</span>	
					    			</div>
								</th>
					    	</c:when>
					     	<c:otherwise>
					     		<th width="30%">
					     			<div id="task_2">
						    			<img name="shade" id="shade_img" alt="shade_img" src="images/shade.png">
					     				<span>
					     					Task
					     				</span>
					     			</div>
					     		</th>
								<th width="20%">
									<div id="data">
						    			<img name="shade" id="shade_img" alt="shade_img" src="images/shade.png">
					     				<span>
											Data
										</span>
									</div>
								</th>
							 </c:otherwise>
						</c:choose>
						<th width="40%">
							<div id="file_div">
						    	<img name="shade" id="shade_img" alt="shade_img" src="images/shade.png">
						    	<span>
						    		File
						    	</span>
						    </div>
						</th>
						
					</tr>
					
					<c:forEach var="task" items="${tasks}">
						<tr>
							<td><input type="checkbox" name=<%= Constants.PARAM_CHECKBOX %> value="${task.id}"></td>
							<td id="background_td"><c:out value="${task.description}"></c:out></td>
							<c:choose>
								<c:when test="${reference == 'TODAY'}">
						    	</c:when>
						    	<c:when test="${reference == 'TOMORROW'}">
						    	</c:when>
						     	<c:otherwise>
						     		<td id="background_td"><c:out value="${task.date}"></c:out></td>
								</c:otherwise>
							</c:choose>
							<td id="background_td">
								<c:forEach var="file" items="${task.fileTask}">
							 		<c:choose>
							 			<c:when test="${file.idTask == task.id}">
							 				<a style="color:#FF513A" href="JavaScript: removeFun('${file.id}')">[x]</a>
							 				&nbsp;
											<a href="JavaScript:sendDownloadForm('${file.id}')">${file.name}</a><br>
							 			</c:when>
							 			<c:otherwise>
							 		 	</c:otherwise>
									</c:choose>	
								</c:forEach>
								<br>
								<a href="JavaScript:showFun('${task.id}')">upload</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<br/>
				<div id="action">
					<c:choose>
						<c:when test="${reference == 'RECYCLE'}">
							<input type=hidden name=<%= Constants.PARAM_REFERENCE %> value="default">
							<div id="restore">
								<a href="JavaScript:sendTaskForm('RESTORE')">
									<img id="restore_img" alt="restore" src="images/button.png">
									<span id="restore_text">Restore</span>
								</a>
							</div>
							<div id="empty_recycle">
					       		<a href="JavaScript:sendTaskForm('CLEAN')">
					       			<img id="empty_recycle_img" alt="empty_recycle" src="images/button.png">
					       			<span id="empty_recycle_text">Delete</span>
					       		</a>
					       	</div> 
				    	</c:when>
				    	<c:when test="${reference == 'FIXED'}">
				    		<input type=hidden name=<%= Constants.PARAM_REFERENCE %> value="default">
				    		<div id="unfixed"> 
					       		<a href="JavaScript:sendTaskForm('UNFIX')">
					       			<img id="unfixed_img" alt="unfixed" src="images/button.png">
					       			<span id="unfixed_text">Unfixed</span>
					       		</a>
					       	</div>
					       	<div id="remove1">
					       		<a href="JavaScript:sendTaskForm('REMOVE')">
					       			<img id="remove1_img" alt="remove1" src="images/button.png">
					       			<span id="remove1_text">Remove</span>
					       		</a>
					       	</div> 
				    	</c:when>
				     	<c:otherwise>
							<input type=hidden name=<%= Constants.PARAM_REFERENCE %> value="default">
							<div id="add_task"> 
								<a href="addTask.jsp">
									<img id="add_task_img" alt="add_task" src="images/button.png">
									<span id="add_task_text">Add task</span>
								</a>
							</div>
							<div id="fix">	
								<a href="JavaScript:sendTaskForm('FIX')">
									<img id="fix_img" alt="fix" src="images/button.png">
									<span id="fix_text">Fix</span>
								</a>
							</div>
							<div id="remove2">
								<a href="JavaScript:sendTaskForm('REMOVE')">
									<img id="remove2_img" alt="remove2" src="images/button.png">
									<span id="remove2_text">Remove</span>
								</a>
							</div> 
						</c:otherwise>
					</c:choose>
				</div>		
			</form>
		</div>
		<div id="uploadBlock" style="display: none;" class="file_upload" align="center">
			<img alt="uploadBlock_img" src="images/area.png">
			<form id="uploadForm" name="uploadForm" action="fileUpload" enctype="multipart/form-data" method="post" >
				<input type="file" id="file"/> 
				<a href="JavaScript:sendUploadForm()">do upload</a>
			</form>
		</div>
		<form name="removeForm" action="fileRemove" method=post>
			<input type=hidden name="idFile" value="default">
		</form>
		<form name="sendDownloadForm" action="fileDownload" method=post>
			<input type=hidden name="reference" value="default">
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>

