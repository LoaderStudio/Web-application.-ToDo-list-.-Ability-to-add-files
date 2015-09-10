package com.loaderstudio.todolist.controllers;

import com.loaderstudio.todolist.constants.Constants;
import com.loaderstudio.todolist.exception.ValidationException;

public class Validation {
		
	public Validation() {
		super();
	}

	protected static void checkLoginInput(String login, String password) throws ValidationException {
		
		if (login == null || password == null) {
			throw new ValidationException(Constants.ERROR_LOGIN_OR_PASSWORD_ABSENT);
		}
		login = login.trim();
		if (Constants.KEY_EMPTY.equals(login)) {
			throw new ValidationException(Constants.ERROR_LOGIN_EMPTY);
		}
	}
	
	protected static void checkRegisterInput(String login, String email, String password, String confirmPassword) throws ValidationException {
		
		if (login == null || email == null || password == null || confirmPassword == null) {
			throw new ValidationException(Constants.ERROR_LOGIN_EMAIL_PASSWORD_OR_CONFIRMPASSWORD_ABSENT);
		}
		login = login.trim();
		if (Constants.KEY_EMPTY.equals(login)) {
			throw new ValidationException(Constants.ERROR_LOGIN_EMPTY);
		}
		email = email.trim();
		if (Constants.KEY_EMPTY.equals(email)) {
			throw new ValidationException(Constants.ERROR_EMAIL_EMPTY);
		}
		if (!password.equals(confirmPassword)) {
			throw new ValidationException(Constants.ERROR_PASSWORD_DOES_NOT_EQUALS_CONFIRMPASSWORD);
		}
		
	}

	protected static void checkAddTask(String taskDescription, String taskDate) {
		
		final String DATE_PATTERN = "[0-9]{2}-[0-9]{2}-[0-9]{4}";
		final String DESCRIPTION_PATTERN = ".{1,255}";
		
		if (taskDescription == null || taskDate == null) {
			throw new ValidationException(Constants.ERROR_DESCRIPTOIN_OR_DATE_ABSENT);
		}
		taskDescription = taskDescription.trim();
		if (Constants.KEY_EMPTY.equals(taskDescription)) {
			throw new ValidationException(Constants.ERROR_DESCRIPTOIN_EMPTY);
		}
		if (!taskDate.matches(DATE_PATTERN)) {
			throw new ValidationException(Constants.ERROR_INCORRECT_DATE);
		}
		if (!taskDescription.matches(DESCRIPTION_PATTERN)) {
			throw new ValidationException(Constants.ERROR_INCORRECT_DESCRIPTION);
		}
	}
}