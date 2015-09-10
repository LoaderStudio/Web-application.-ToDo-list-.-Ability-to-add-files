package com.loaderstudio.todolist.impl;

import java.util.HashMap;
import java.util.Map;

import com.loaderstudio.todolist.beans.User;
import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.dao.IUserDao;
import com.loaderstudio.todolist.exception.DaoException;
import com.loaderstudio.todolist.exception.ValidationException;

public class HardcodedUserImplDAO implements IUserDao {
	
	private static Map<User,String> map = new HashMap<User,String>();;
	
	public HardcodedUserImplDAO() {
		super();
		if (map.size() == 0) {
			map.put(new User("111", "111@email.com"), "111");
			map.put(new User("222", "222@email.com"), "222");
		}
	}
	
	@Override
	public User getUser(String login, String password) throws DaoException {
		
		for (Map.Entry<User,String> mapList: map.entrySet()) {
			if (mapList.getKey().getLogin().equals(login) && mapList.getValue().equals(password)) {
				return new User(login);
			}
		}
		throw new DaoException(Constants.ERROR_LOGIN_OR_PASSWORD_INCORRECT);
	}
	
	@Override
	public void addUser(String login, String email, String password) throws ValidationException {
		
		User user = new User(login);
		synchronized (HardcodedUserImplDAO.class) {
			if (map.containsKey(user) && map.get(user).equals(password)) {
				throw new ValidationException(Constants.ERROR_LOGIN_OR_EMAIL_IS_BUSY);
			}
			map.put(user, password);
		}
	}	
}