package com.loaderstudio.todolist.controllers;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.dao.ITaskDao;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.factoty.TaskFactory;
import com.loaderstudio.todolist.ifaces.AbstractBaseController;

public class FixRemoveController extends AbstractBaseController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String section = request.getParameter(Constants.PARAM_REFERENCE);
			String nameParameter = null;
			String[] value = null;
			Enumeration<?> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				nameParameter = (String) parameterNames.nextElement();
				value = request.getParameterValues(nameParameter);
				if (Constants.PARAM_CHECKBOX.equals(nameParameter)) {
					for (String idTask : value) {
						ITaskDao taskDAO = TaskFactory.getClassFromFactory();
						taskDAO.fixRemoveTasks(idTask, section);
					}
				}
			}
			jumpPage(Constants.SLASH + Constants.JUMP_MAIN_CONTROLLER, request, response);
			
		} catch (DaoException e) {
			jumpError(e.getMessage(), Constants.SLASH + Constants.JUMP_ERROR_MAIN_CONTROLLER, request, response);
		}
	}
}