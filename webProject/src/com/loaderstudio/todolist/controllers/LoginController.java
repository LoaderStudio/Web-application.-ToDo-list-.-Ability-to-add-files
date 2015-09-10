package com.loaderstudio.todolist.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loaderstudio.todolist.beans.User;
import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.dao.IUserDao;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.exception.ValidationException;
import com.loaderstudio.todolist.factoty.UserFactory;
import com.loaderstudio.todolist.ifaces.AbstractBaseController;

public class LoginController extends AbstractBaseController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(Constants.KEY_LOGIN);
		String password = request.getParameter(Constants.KEY_PASSWORD);
		try {
			Validation.checkLoginInput(login, password);
			IUserDao userDao = UserFactory.getClassFromFactory();
			User user = userDao.getUser(login, password);
			HttpSession session = request.getSession();
			session.setAttribute(Constants.KEY_USER, user);
			jumpPage(Constants.SLASH + Constants.JUMP_MAIN_CONTROLLER, request, response);
		} catch (ValidationException | DaoException e) {
			jumpError(e.getMessage(), Constants.SLASH + Constants.JUMP_LOGIN, request, response);
		}
	}	
}