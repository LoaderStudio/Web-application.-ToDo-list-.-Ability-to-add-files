package com.loaderstudio.todolist.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.exception.DaoException;

public class DBSingleton {
	
	private static DBSingleton dbSingleton;
	
	static {
		try {
			Class.forName(Constants.DB_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			throw new DaoException(Constants.DB_CAN_NOT_LOAD_THE_DRIVER + e);
		}
	}
	
	public static synchronized DBSingleton getDbSingletone() {
		if (dbSingleton == null) {
			dbSingleton = new DBSingleton();
		}
		return dbSingleton;
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(Constants.DB_SERVER_URL, Constants.DB_SERVER_USER, Constants.DB_SERVER_PASSWORD);
	}
	
	public void closePs(PreparedStatement preparedstatement) throws DaoException {
		try {
			if (preparedstatement != null) {
				preparedstatement.close();
			}
		} catch (SQLException e) {
            throw new DaoException(Constants.DB_CAN_NOT_CLOSE_CONNECTION + e);
		}
	}
	
	public void closePss(PreparedStatement... preparedStatement) throws DaoException {
		try {
			for (PreparedStatement preparedStatement2 : preparedStatement) {
				if (preparedStatement2 != null) {
					preparedStatement2.close();
				}
			}
		} catch (SQLException e) {
			throw new DaoException(Constants.DB_CAN_NOT_CLOSE_CONNECTION + e);
		}
	}
	
	public void closeRs(ResultSet resultSet) throws DaoException {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
            throw new DaoException(Constants.DB_CAN_NOT_CLOSE_CONNECTION + e);
		}
	}
	
	public void closeCn(Connection connection) throws DaoException {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
            throw new DaoException(Constants.DB_CAN_NOT_CLOSE_CONNECTION + e);
		}
	}
}