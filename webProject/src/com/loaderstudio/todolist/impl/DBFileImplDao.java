package com.loaderstudio.todolist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.loaderstudio.todolist.beans.FileTask;
import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.dao.IFileDao;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.exception.ValidationException;
import com.loaderstudio.todolist.singleton.DBSingleton;

public class DBFileImplDao extends DBSingleton implements IFileDao {
	
	final static int COLUMN_ONE = 1;
	final static int COLUMN_TWO = 2;
	final static int COLUMN_THREE = 3;
	
	final static int PARAMETER_ONE = 1;
	final static int PARAMETER_TWO = 2;
	final static int PARAMETER_THREE = 3;
	final static int PARAMETER_FOUR = 4;

	@Override
	public void addFile(String login, int idTask, String fileName, String folder) {
		PreparedStatement psSelect = null;
		ResultSet rs = null;
		Connection cn = null;
		try {
			cn = getConnection();
			int idUser = getIdUser(cn, login);
			PreparedStatement psInsert = cn.prepareStatement(Constants.SQL_INSERT_FILETASK);
			psInsert.setInt(PARAMETER_ONE, idUser);
			psInsert.setInt(PARAMETER_TWO, idTask);
			psInsert.setString(PARAMETER_THREE, fileName);
			psInsert.setString(PARAMETER_FOUR, folder);

			synchronized (DBFileImplDao.class) {
				psInsert.executeUpdate();
			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		} finally {
			closeRs(rs);
			closePs(psSelect);
			closeCn(cn);
		}
		
	}
	
	private int getIdUser(Connection cn, String login) throws DaoException, SQLException {
		
		int idUser = 0;
		ResultSet rs = null;
		PreparedStatement psSelectIdUser = null;
		try {
			psSelectIdUser = cn.prepareStatement(Constants.SQL_SELECT_USER_ID);
			psSelectIdUser.setString(PARAMETER_ONE, login);
			rs = psSelectIdUser.executeQuery();
			if (rs.next()) {
				idUser = rs.getInt(COLUMN_ONE);
			}
			closeRs(rs);
			return idUser;
		} finally {
			closeRs(rs);
			closePs(psSelectIdUser);
		
		}
	}

	@Override
	public String removeFile(String idFileParam) {
		Connection cn = null;
		String folder = null;
		ResultSet rs = null;
		PreparedStatement psSelect = null;
		PreparedStatement psUpdate = null;
		try {
			cn = getConnection();
			int idFile = Integer.parseInt(idFileParam);
			psSelect = cn.prepareStatement(Constants.SQL_SELECT_FOLDER);
			psUpdate = cn.prepareStatement(Constants.SQL_REMOVE_FILE);
			psSelect.setInt(PARAMETER_ONE, idFile);
			psUpdate.setInt(PARAMETER_ONE, idFile);

			rs = psSelect.executeQuery();
			if (rs.next()) {
				folder = rs.getString(COLUMN_ONE);
			}

			synchronized (DBFileImplDao.class) {
				psUpdate.executeUpdate();
			}

			return folder;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new ValidationException(Constants.EXCEPTIONS_PARSE_INT);
		} finally {
			closeCn(cn);
			closePss(psSelect, psUpdate);
			closeRs(rs);
		}
	}

	@Override
	public FileTask getFile(int idFileParam) throws DaoException {
		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement psSelectFile = null;
		FileTask f = null;
		try {
			cn = getConnection();
			psSelectFile = cn.prepareStatement(Constants.SQL_SELECT_FILE);
			psSelectFile.setInt(PARAMETER_ONE, idFileParam);

			rs = psSelectFile.executeQuery();
			while (rs.next()) {
				int idFile = rs.getInt(COLUMN_ONE);
				String name = rs.getString(COLUMN_TWO);
				String folder = rs.getString(COLUMN_THREE);
				f = new FileTask(idFile, 0, idFile, name, folder);
			}
			return f;
		} catch (SQLException e) {
			System.err.println(e);
			return f;
		} finally {
			closeCn(cn);
			closePs(psSelectFile);
			closeRs(rs);
		}
	}

}