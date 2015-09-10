package com.loaderstudio.todolist.controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loaderstudio.todolist.beans.User;
import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.exception.ValidationException;
import com.loaderstudio.todolist.ifaces.AbstractBaseController;
import com.loaderstudio.todolist.impl.DBFileImplDao;

public class FileRemoveController extends AbstractBaseController {

	private static final long serialVersionUID = 1L;
	
	private String defaultPath;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		defaultPath = getServletContext().getRealPath(Constants.BACK_SLASH) + Constants.DEFAULT_PATH;;
	}

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		String idFile = request.getParameter(Constants.PARAM_ID_FILE);
		try {
			DBFileImplDao dbFileImplDao = new DBFileImplDao();
			String folder = dbFileImplDao.removeFile(idFile);
			FileRemoveManager fileRemoveManager = new FileRemoveManager();
			String removePath = fileRemoveManager.getSavePath(user.getLogin(), folder, defaultPath);
			fileRemoveManager.doRemove(new File(removePath));
			jumpPage(Constants.SLASH + Constants.JUMP_MAIN_CONTROLLER, request, response);
		} catch (DaoException | ValidationException e) {
			jumpError(e.getMessage(), Constants.SLASH + Constants.JUMP_ERROR_MAIN_CONTROLLER, request, response);
		}
	}
}