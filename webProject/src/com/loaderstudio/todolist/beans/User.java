package com.loaderstudio.todolist.beans;

public class User {
	
	private String login;
	private String email;
	
	public User() {
		super();
	}
	
	public User(String login) {
		super();
		this.login = login;
	}

	public User(String login, String email) {
		super();
		this.login = login;
		this.email = email;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", email=" + email + "]";
	}

}