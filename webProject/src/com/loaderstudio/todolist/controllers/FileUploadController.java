package com.loaderstudio.todolist.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loaderstudio.todolist.beans.User;
import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.dao.IFileDao;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.factoty.FileFactory;
import com.loaderstudio.todolist.ifaces.AbstractBaseController;

public class FileUploadController extends AbstractBaseController {

	private static final long serialVersionUID = 1L;

	String startPath;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		startPath = getServletContext().getRealPath(Constants.SLASH) + Constants.DEFAULT_PATH;
	}

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		FileUploadManager fileUploadManager = new FileUploadManager();
		String savePath = getSavePath(user);
		IFileDao fileDao = null;
		String generatedFolder = fileUploadManager.generateFolder();
		try {
			fileDao = FileFactory.getClassFromFactory();
			fileUploadManager.setSavePath(savePath + generatedFolder + Constants.BACK_SLASH);
			fileUploadManager.doUpload(request);
			String fileName = fileUploadManager.getFileName();
			int idTask = Integer.valueOf(fileUploadManager.getFormName());
			fileDao.addFile(user.getLogin(), idTask, fileName, generatedFolder);
			jumpPage(Constants.SLASH + Constants.JUMP_MAIN_CONTROLLER, request, response);
		} catch (DaoException | NumberFormatException e) {
			jumpError(e.getMessage(), Constants.SLASH + Constants.JUMP_ERROR_MAIN_CONTROLLER, request, response);
		}
	}
	
	private String getSavePath(User user) {
		StringBuilder sb = new StringBuilder(startPath);
		sb.append(user.getLogin());
		sb.append(Constants.BACK_SLASH);
		return sb.toString();
	}
}