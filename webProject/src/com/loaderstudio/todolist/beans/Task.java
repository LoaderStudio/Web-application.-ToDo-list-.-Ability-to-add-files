package com.loaderstudio.todolist.beans;

import java.util.Date;
import java.util.List;

public class Task {
	
	private int id;
	private String description;
	private Date date;
	List<FileTask> fileTask;
	
	public Task() {
		super();
	}

	public Task(int id, String description, Date date) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<FileTask> getFileTask() {
		return fileTask;
	}

	public void setFileTask(List<FileTask> fileTask) {
		this.fileTask = fileTask;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description + ", date="
				+ date + ", fileTask=" + fileTask + "]";
	}

}