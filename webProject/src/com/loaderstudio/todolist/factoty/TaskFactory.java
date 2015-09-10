package com.loaderstudio.todolist.factoty;

import com.loaderstudio.todolist.dao.ITaskDao;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.impl.DBTaskImplDao;

public class TaskFactory {
	
	public TaskFactory() {
		super();
	}
	
	public static ITaskDao getClassFromFactory() throws DaoException {
		return new DBTaskImplDao();
	}
}