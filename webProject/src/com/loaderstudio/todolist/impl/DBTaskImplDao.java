package com.loaderstudio.todolist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.loaderstudio.todolist.beans.FileTask;
import com.loaderstudio.todolist.beans.Task;
import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.constants.EConstants;
import com.loaderstudio.todolist.dao.ITaskDao;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.exception.UserException;
import com.loaderstudio.todolist.exception.ValidationException;
import com.loaderstudio.todolist.singleton.DBSingleton;

public class DBTaskImplDao extends DBSingleton implements ITaskDao {
	
	final static int COLUMN_ONE = 1;
	final static int COLUMN_THREE = 3;
	final static int COLUMN_FOUR = 4;
	
	final static int PARAMETER_ONE = 1;
	final static int PARAMETER_TWO = 2;
	final static int PARAMETER_THREE = 3;
	
	@Override
	public List<Task> getTasks(String login, String section) {
		
		Connection cn = null;
		ResultSet rs = null;
		ResultSet rsFiles = null;
		PreparedStatement psSelectTask = null;
		List<Task> tasks = new ArrayList<Task>();
		PreparedStatement psSelectFile = null;
		List<FileTask> fileTasks = new ArrayList<FileTask>();

		String sectionEnum = null;
		
		try {
			cn = getConnection();
			try {
				sectionEnum = EConstants.valueOf(section).getSelect();
			} catch (IllegalArgumentException e) {
				sectionEnum = EConstants.valueOf(Constants.KEY_TODAY).getSelect();
			}
			psSelectTask = cn.prepareStatement(sectionEnum);
			psSelectFile = cn.prepareStatement(Constants.SQL_SELECT_FILES);
			psSelectTask.setString(PARAMETER_ONE, login);
			psSelectFile.setString(PARAMETER_ONE, login);
			
			rs = psSelectTask.executeQuery();
			
			int id;
			String description;
			Date date;
			Task task;
			
			while (rs.next()) {
				id = rs.getInt(COLUMN_ONE);
				description = rs.getString(COLUMN_THREE);
				date = rs.getDate(COLUMN_FOUR);
				task = new Task(id, description, date);
				
				psSelectFile.setInt(2, id);
				rsFiles = psSelectFile.executeQuery();
				while (rsFiles.next()) {
					int idFile = rsFiles.getInt(1);
					int idUser = rsFiles.getInt(2);
					String Name = rsFiles.getString(4);
					FileTask f = new FileTask(idFile, idUser, id, Name, null);
					fileTasks.add(f);
				}
				
				tasks.add(task);
				
				task.setFileTask(fileTasks);
				
			}
			return tasks;
		} catch (SQLException e) {
			System.err.println(e);
			return tasks;
		} finally {
			closeRs(rs);
			closePs(psSelectTask);
			closeCn(cn);
		}
	}

	@Override
	public void addTasks(String login, String taskDescription, String taskDate) throws DaoException {
		
		Connection cn = null;
		PreparedStatement psSelect = null;
		PreparedStatement psInsert = null;
		ResultSet rs = null;
		int id = -1;
		
		try {
			cn = getConnection();
			psSelect = cn.prepareStatement(Constants.SQL_SELECT_USER_ID);
			psInsert = cn.prepareStatement(Constants.SQL_INSERT_TASK);
			
			SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_PATTERN);
			java.util.Date uDate = null;
			try {
				uDate = sdf.parse(taskDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(uDate.getTime());
			
			psSelect.setString(PARAMETER_ONE, login);
			psInsert.setString(PARAMETER_TWO, taskDescription);
			psInsert.setDate(PARAMETER_THREE, sqlDate);
			synchronized (DBTaskImplDao.class) {
				rs = psSelect.executeQuery();
				if (rs.next()) {
					id = rs.getInt(COLUMN_ONE);
					psInsert.setInt(PARAMETER_ONE, id);
				} else {
					throw new UserException(Constants.USER_NOT_FOUND);
				}
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

	@Override
	public void fixRemoveTasks(String taskIdString, String section) {
		
		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement psUpdate = null;
		int taskId = -1;
		
		try {
			cn = getConnection();
			psUpdate = cn.prepareStatement(EConstants.valueOf(section).getSelect());
			taskId = Integer.parseInt(taskIdString);
			psUpdate.setInt(PARAMETER_ONE, taskId);
			synchronized (DBTaskImplDao.class) {
				psUpdate.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new ValidationException(Constants.ERROR_PARSE_INT_EXCEPTION);
		} finally {
			closeRs(rs);
			closePs(psUpdate);
			closeCn(cn);
		}
	}
	
}