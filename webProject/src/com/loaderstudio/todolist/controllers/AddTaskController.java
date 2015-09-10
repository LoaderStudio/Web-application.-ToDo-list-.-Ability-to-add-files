package com.loaderstudio.todolist.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loaderstudio.todolist.beans.User;
import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.dao.ITaskDao;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.exception.UserException;
import com.loaderstudio.todolist.exception.ValidationException;
import com.loaderstudio.todolist.factoty.TaskFactory;
import com.loaderstudio.todolist.ifaces.AbstractBaseController;

public class AddTaskController extends AbstractBaseController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		String taskDescription = request.getParameter(Constants.KEY_TASK_DESCRIPTOIN);
		String taskDate = request.getParameter(Constants.KEY_TASK_DATE);
		
		try {
			Validation.checkAddTask(taskDescription, taskDate);
			ITaskDao taskDAO = TaskFactory.getClassFromFactory();
			taskDAO.addTasks(user.getLogin(), taskDescription, taskDate);
			jumpPage(Constants.SLASH + Constants.JUMP_MAIN_CONTROLLER, request, response);
		} catch (DaoException | UserException | ValidationException e) {
			jumpError(e.getMessage(), Constants.SLASH + Constants.JUMP_ERROR_ADD_TASK, request, response);
		}
	}
}