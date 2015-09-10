package com.loaderstudio.todolist.beans;

public class FileTask {

	private int id;
	private int idUser;
	private int idTask;
	private String name;
	private String folder;
	
	public FileTask() {
		super();
	}

	public FileTask(int id, int idUser, int idTask, String name, String folder) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idTask = idTask;
		this.name = name;
		this.folder = folder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdTask() {
		return idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	@Override
	public String toString() {
		return "FileTask [id=" + id + ", idUser=" + idUser + ", idTask=" + idTask + ", name=" + name + ", folder=" + folder + "]";
	}	
}