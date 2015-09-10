package com.loaderstudio.todolist.controllers;

import java.io.File;

import com.loaderstudio.todolist.constants.Constants;

public class FileRemoveManager {
	
	public void doRemove(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				File f = new File(dir, children[i]);
				doRemove(f);
			}
			dir.delete();
		} else
			dir.delete();
	}
	
	public String getSavePath(String user, String folder, String defaultPath) {
		StringBuilder sb = new StringBuilder(defaultPath);
		sb.append(user);
		sb.append(Constants.BACK_SLASH);
		sb.append(folder);
		sb.append(Constants.BACK_SLASH);
		return sb.toString();
	}
}