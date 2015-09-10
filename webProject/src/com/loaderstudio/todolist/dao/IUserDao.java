package com.loaderstudio.todolist.dao;

import com.loaderstudio.todolist.beans.User;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.exception.ValidationException;

public interface IUserDao {

	User getUser(String login, String password) throws DaoException;
	void addUser(String login, String email, String password) throws ValidationException, DaoException;

}