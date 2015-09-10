package com.loaderstudio.todolist.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.loaderstudio.todolist.constants.Constants;

public class FileUploadManager {
	
	private String savePath;
	private String fileName;
	private String formName;
	
	public FileUploadManager() {
		super();
	}
	
	public FileUploadManager(String savePath, String fileName, String formName) {
		super();
		this.savePath = savePath;
		this.fileName = fileName;
		this.formName = formName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFilename(String filename) {
		this.fileName = filename;
	}
	
	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
		int end = formName.indexOf(Constants.END_ELEMENT_VALUE);
		formName = formName.substring(Constants.CONTENT_DISPOSITION_FILE_UPLOAD.length(), end);
	}

	public void doUpload(HttpServletRequest request) throws IOException {
		
		ServletInputStream in = request.getInputStream();
		byte[] line = new byte[128];
		int i = in.readLine(line, 0, 128);
		int boundarylength = i - 2;
		String boundary = new String(line, 0, boundarylength);
		
		while (i != -1) {
			String newLine = new String(line, 0, i);
			if (newLine.startsWith(Constants.CONTENT_DISPOSITION_FILE_UPLOAD)) {
				
				if (newLine.indexOf(Constants.FILE_NAME_AND_QUOTE) != -1) {
				
					String s = new String(line, 0, i-2);
					
					int pos = s.indexOf(Constants.FILE_NAME_AND_QUOTE);
					String filePath = s.substring(pos + 10, s.length() - 1);
					pos = filePath.lastIndexOf(Constants.BACK_SLASH);
					if (pos != -1) {
						fileName = filePath.substring(pos + 1);
					} else {
						fileName = filePath;
					}
					if (!"".equals(fileName)) {
						int end = newLine.indexOf(Constants.END_ELEMENT_VALUE);
						formName = newLine.substring(Constants.CONTENT_DISPOSITION_FILE_UPLOAD.length(),	end);
						
						i = in.readLine(line, 0, 128);
						i = in.readLine(line, 0, 128);
						i = in.readLine(line, 0, 128);
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						newLine = new String(line, 0, i);
						while (i != -1 && !newLine.startsWith(boundary)) {
							buffer.write(line, 0, i);
							i = in. readLine(line, 0, 128);
							newLine = new String(line, 0, i);
						}
						try {
							RandomAccessFile f = getRandomAccessFile();
							byte[] bytes= buffer.toByteArray();
							f.write(bytes, 0, bytes.length - 2);
							f.close();
						}
						catch (Exception e) {}
					}
				}
			}
			i = in.readLine(line, 0, 128);
		}
	}
	
	public String generateFolder() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			int k = (int) (Math.random()*10);
			buffer.append(k);
		}
		return buffer.toString();	
	}
	
	private RandomAccessFile getRandomAccessFile() throws IOException {
		File file = new File(getSavePath() + fileName);
		File dir = file.getParentFile();
		if (!file.exists()) {
			dir.mkdirs();
		}
		RandomAccessFile  f = new RandomAccessFile (getSavePath() + fileName, "rw");
		return f;
	}
}