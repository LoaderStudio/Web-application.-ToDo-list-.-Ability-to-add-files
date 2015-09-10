package com.loaderstudio.todolist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.loaderstudio.todolist.beans.User;
import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.dao.IUserDao;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.exception.ValidationException;
import com.loaderstudio.todolist.singleton.DBSingleton;

public class DBUserImplDao extends DBSingleton implements IUserDao {
	
	final static int PARAMETER_ONE = 1;
	final static int PARAMETER_TWO = 2;
	final static int PARAMETER_THREE = 3;

	public DBUserImplDao() {
		super();
	}

	@Override
	public User getUser(String name, String password) throws DaoException {
	
		Connection cn = null;
		ResultSet rS = null;
		PreparedStatement pS = null;
		
		try {
			cn = getConnection();
			pS = cn.prepareStatement(Constants.SQL_SELECT_USERS_WHERE_LOGIN_AND_PASSWORD);
			pS.setString(PARAMETER_ONE, name);
			pS.setString(PARAMETER_TWO, password);
			rS = pS.executeQuery();
			if (rS.next()) {
				return new User(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeRs(rS);
			closePs(pS);
			closeCn(cn);
		}
		throw new DaoException(Constants.ERROR_LOGIN_OR_PASSWORD_INCORRECT);
	}

	@Override
	public void addUser(String login, String email, String password) throws ValidationException, DaoException {

		Connection cn = null;
		PreparedStatement pStSelect = null;
		PreparedStatement pStInsert = null;
		ResultSet rS = null;

		try {
			cn = getConnection();
			
			pStSelect = cn.prepareStatement(Constants.SQL_SELECT_USERS_WHERE_LOGIN_AND_EMAIL);
			pStInsert = cn.prepareStatement(Constants.SQL_INSERT_INTO_USERS);
			pStSelect.setString(PARAMETER_ONE, login);
			pStSelect.setString(PARAMETER_TWO, email);
			pStInsert.setString(PARAMETER_ONE, login);
			pStInsert.setString(PARAMETER_TWO, email);
			pStInsert.setString(PARAMETER_THREE, password);
			
			synchronized (DBUserImplDao.class) {
				rS = pStSelect.executeQuery();
				if (rS.next()) {
					throw new ValidationException(Constants.ERROR_LOGIN_OR_EMAIL_IS_BUSY);
				}
				pStInsert.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeRs(rS);
			closePss(pStInsert, pStSelect);
			closeCn(cn);
		}
	}
}