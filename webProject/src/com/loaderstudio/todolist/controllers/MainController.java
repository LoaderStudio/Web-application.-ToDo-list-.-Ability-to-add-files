package com.loaderstudio.todolist.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loaderstudio.todolist.beans.Task;
import com.loaderstudio.todolist.beans.User;
import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.ifaces.AbstractBaseController;
import com.loaderstudio.todolist.impl.DBTaskImplDao;

public class MainController extends AbstractBaseController {
	
	private static final long serialVersionUID = 1L;
	
	final static String KEY_FIX = "FIX";
	final static String KEY_REMOVE = "REMOVE";
	final static String KEY_CLEAN = "CLEAN";
	final static String KEY_UNFIX = "UNFIX";
	final static String KEY_RESTORE = "RESTORE";
	final static String KEY_TODAY = "TODAY";

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		String section = request.getParameter(Constants.PARAM_REFERENCE);
		if (user == null) {
			jumpPage(Constants.SLASH + Constants.JUMP_MAIN, request, response);
			return;
		}
		if (section == null) {
			section = KEY_TODAY;
		}
		if (KEY_FIX.equals(section) || 
				KEY_REMOVE.equals(section) || 
				KEY_CLEAN.equals(section) || 
				KEY_UNFIX.equals(section) || 
				KEY_RESTORE.equals(section)) {
			section = (String) session.getAttribute(Constants.ATTRIB_SECTION);
		}
		session.setAttribute(Constants.ATTRIB_SECTION, section);
		try {
			DBTaskImplDao taskDAO = new DBTaskImplDao(); 
			List<Task> tasks = taskDAO.getTasks(user.getLogin(), section);
			request.setAttribute(Constants.ATTRIB_TASKS, tasks);
			request.setAttribute(Constants.PARAM_REFERENCE, section);
			jumpPage(Constants.SLASH + Constants.JUMP_TASKS, request, response);
		} catch (DaoException e) {
			request.setAttribute(Constants.ATTRIB_TASKS, new ArrayList<Task>());
			jumpError(e.getMessage(), Constants.SLASH + Constants.JUMP_ERROR_MAIN_CONTROLLER, request, response);
		}
	}
}