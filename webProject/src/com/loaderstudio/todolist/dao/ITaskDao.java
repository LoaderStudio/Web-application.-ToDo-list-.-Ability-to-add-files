package com.loaderstudio.todolist.dao;

import java.util.List;

import com.loaderstudio.todolist.beans.Task;

public interface ITaskDao {
	
	public List<Task> getTasks(String login, String section);
	public void addTasks(String login, String taskDescription, String taskDate);
	public void fixRemoveTasks(String taskDescription, String section);
	
}