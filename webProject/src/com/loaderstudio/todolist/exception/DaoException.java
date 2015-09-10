package com.loaderstudio.todolist.exception;

public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}
	
	public DaoException(String error) {
		super(error);
	}	
}