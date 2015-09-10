package com.loaderstudio.todolist.controllers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loaderstudio.todolist.beans.FileTask;
import com.loaderstudio.todolist.beans.User;
import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.dao.IFileDao;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.factoty.FileFactory;
import com.loaderstudio.todolist.ifaces.AbstractBaseController;

public class FileDownloadController extends AbstractBaseController {

	private static final long serialVersionUID = 1L;

	private String defaultPath;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		defaultPath = getServletContext().getRealPath(Constants.SLASH) + Constants.DEFAULT_PATH;
	}

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String fileID = request.getParameter(Constants.PARAM_REFERENCE);
		int idFile = Integer.valueOf(fileID);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		IFileDao fileDao = null;
		FileTask fileOpen = null;
		java.io.FileInputStream fileInputStream = null;
		
		try {
			
			fileDao = FileFactory.getClassFromFactory();
			fileOpen = fileDao.getFile(idFile);
			String fileFolder = fileOpen.getFolder();
			String fileName = fileOpen.getName();
			String uploadPath = getFullPath(user, fileName, fileFolder);
			
			response.setContentType(Constants.CONTENT_TYPE);
			response.setHeader(Constants.CONTENT_DISPOSITION_FILE_DOWNLOAD, Constants.ATTACHMENT + fileName + Constants.QUOTE);
			
			File file = new File(uploadPath);
			fileInputStream = new java.io.FileInputStream(file);
			
			OutputStream os = response.getOutputStream();
			try {
				byte[] buff = new byte[512];
				int read;
				while((read = fileInputStream.read(buff)) != -1)
					os.write(buff, 0, read);
		    } catch(Exception ex) {
		        ex.printStackTrace();
		    } finally {
		        if(fileInputStream != null) fileInputStream.close();
		    }
		} catch (DaoException e) {
			jumpError(e.getMessage(), Constants.SLASH + Constants.JUMP_ERROR_TASK, request, response);
			//править второй аргумент
		} finally {
			fileInputStream.close();
		}

	}

	private String getFullPath(User user, String fileName, String folder) {
		StringBuilder sb = new StringBuilder(defaultPath);
		sb.append(user.getLogin());
		sb.append(Constants.BACK_SLASH);
		sb.append(folder);
		sb.append(Constants.BACK_SLASH);
		sb.append(fileName);
		return sb.toString();
	}

}
