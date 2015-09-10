package com.loaderstudio.todolist.factoty;

import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.impl.DBFileImplDao;

public class FileFactory {
	
	public FileFactory() {
		super();
	}
	
	public static DBFileImplDao getClassFromFactory() throws DaoException {
		return new DBFileImplDao();
	}

}
