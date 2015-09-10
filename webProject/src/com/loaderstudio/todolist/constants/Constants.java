package com.loaderstudio.todolist.constants;

public interface Constants {
	
	String SLASH = "/";
	String BACK_SLASH = "\\";

	String JUMP_INDEX = "index.jsp";
	String JUMP_LOGIN = "login.jsp";
	String JUMP_REGISTER_ERROR = "register.jsp";
	String JUMP_TASKS = "tasks.jsp";
	String JUMP_MAIN = "main.jsp";
	String JUMP_MAIN_CONTROLLER = "main";
	String JUMP_ERROR_MAIN_CONTROLLER = "main";
	String JUMP_ERROR_ADD_TASK = "addTask.jsp";
	String JUMP_ERROR_TASK = "task.jsp";
	
	String KEY_USER = "user";
	String KEY_LOGIN = "login";
	String KEY_PASSWORD = "password";
	String KEY_EMAIL = "email";
	String KEY_CONFIRM_PASSWORD = "confirmPassword";
	String KEY_TASK_DESCRIPTOIN = "taskDescription";
	String KEY_TASK_DATE = "taskData";
	String KEY_ERROR_MESSAGE = "errorMessage";
	String KEY_EMPTY = "";
	String KEY_TODAY = "TODAY";
		
	String ERROR_LOGIN_EMPTY = "Login is empty.";
	String ERROR_EMAIL_EMPTY = "Email is empty.";
	String ERROR_LOGIN_OR_PASSWORD_ABSENT = "Login or password are absent.";
	String ERROR_LOGIN_OR_PASSWORD_INCORRECT = "Login or password are incorrect";
	String ERROR_LOGIN_EMAIL_PASSWORD_OR_CONFIRMPASSWORD_ABSENT = "Login, email, password or confirm password are absent.";
	String ERROR_PASSWORD_DOES_NOT_EQUALS_CONFIRMPASSWORD = "Passwords are not equal.";
	String ERROR_LOGIN_OR_EMAIL_IS_BUSY = "Login or email is busy.";
	String ERROR_DESCRIPTOIN_OR_DATE_ABSENT = "Description or date are absent.";
	String ERROR_DESCRIPTOIN_EMPTY = "Description is empty.";
	String ERROR_INCORRECT_DATE = "Incorrect date. Example: 30-01-2015";
	String ERROR_INCORRECT_DESCRIPTION = "Incorrect description.";
	String ERROR_PARSE_INT_EXCEPTION = "Parse INT exception.";
	
	String EXCEPTIONS_PARSE_INT = "Parce exception idFile!"; 
	
	String SQL_SELECT_USERS_WHERE_LOGIN_AND_PASSWORD = "SELECT login FROM users WHERE login = ? AND password = ?";
	String SQL_SELECT_USERS_WHERE_LOGIN_AND_EMAIL = "SELECT id FROM users WHERE login = ? OR email = ?";
	String SQL_INSERT_INTO_USERS = "INSERT INTO users (login, email, password) VALUES (?, ?, ?)";
	String SQL_SELECT_USER_ID = "SELECT id FROM users WHERE login = ?";
	String SQL_INSERT_TASK = "INSERT INTO tasks (usersId, description, date) VALUES (?, ?, ?)";
	String SQL_INSERT_FILETASK = "INSERT INTO files (userId, taskId, fileName, folder) VALUES (?, ?, ?, ?)";
	String SQL_SELECT_FOLDER = "SELECT files.folder FROM files WHERE files.id = ?";
	String SQL_REMOVE_FILE = "DELETE files FROM files WHERE files.id = ?";
	String SQL_SELECT_FILE = "SELECT files.id, files.fileName, files.folder FROM files WHERE files.id = ?";
	String SQL_SELECT_FILES = "SELECT files.id, files.userId, files.taskId, files.fileName FROM files,users WHERE users.id = files.userId AND users.login = ? AND files.taskId = ?";
	
	String DB_CLASS_NAME = "org.gjt.mm.mysql.Driver";
	String DB_SERVER_URL = "jdbc:mysql://localhost/webproject";
	String DB_SERVER_USER = "web";
	String DB_SERVER_PASSWORD = "web";
	String DB_CAN_NOT_CLOSE_CONNECTION = "Can't close connection.";
	String DB_CAN_NOT_LOAD_THE_DRIVER = "Can not load the driver.";
	
	String DEVELOPED_BY = "Developed by Samuseu Artsiom";
	String DEVELOPER_EMAIL = "loaderstudio@gmail.com";
	
	String PARAM_REFERENCE = "reference";
	String PARAM_CHECKBOX = "checkbox";
	String PARAM_ID_FILE = "idFile";
	
	String ATTRIB_SECTION = "section";
	String ATTRIB_TASKS = "tasks";
	
	String USER_NOT_FOUND = "User not found";

	String HELLO_GUEST = "Hello, guest! Login or register, please.";
	String ENTER_LOGIN_PASSWORD = "Please, enter your login and password."; 
	String ENTER_REGISTER_INFORMATION = "Please, enter your registration data.";
	String ENTER_DESCRIPTION_DATE = "Please, enter the description and date for task.";
	
	String DATE_PATTERN = "dd-mm-yyyy";
	
	String CONTENT_TYPE = "APPLICATION/OCTET-STREAM";
	String CONTENT_DISPOSITION_FILE_DOWNLOAD = "Content-Disposition";
	String ATTACHMENT = "attachment; filename=\"";
	String QUOTE = "\"";
	String DEFAULT_PATH = "WEB-INF\\files\\";
	String END_ELEMENT_VALUE = "\";";
	String CONTENT_DISPOSITION_FILE_UPLOAD = "Content-Disposition: form-data; name=\"";
	String FILE_NAME_AND_QUOTE = "filename=\"";
}