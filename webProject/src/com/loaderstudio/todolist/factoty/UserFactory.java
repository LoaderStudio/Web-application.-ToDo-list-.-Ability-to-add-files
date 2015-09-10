package com.loaderstudio.todolist.factoty;

import com.loaderstudio.todolist.dao.IUserDao;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.impl.DBUserImplDao;
import com.loaderstudio.todolist.impl.HardcodedUserImplDAO;

public class UserFactory {
	
	public UserFactory() {
		super();
	}
	
	public static IUserDao getClassFromFactory() throws DaoException {
		//return new HardcodedUserImplDAO();
		return new DBUserImplDao();
	}
}