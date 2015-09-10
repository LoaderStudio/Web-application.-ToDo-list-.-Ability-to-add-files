package com.loaderstudio.todolist.dao;

import com.loaderstudio.todolist.beans.FileTask;

public interface IFileDao {

	public void addFile(String login, int idTask, String fileName, String generatedFolder);
	public String removeFile(String idFileParam);
	public FileTask getFile(int idFile);

}
