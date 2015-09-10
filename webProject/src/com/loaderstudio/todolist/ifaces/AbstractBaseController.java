package com.loaderstudio.todolist.ifaces;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loaderstudio.todolist.constants.Constants;

 public abstract class AbstractBaseController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    performTask(request, response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    performTask(request, response);
	}   	  	    

	abstract protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    protected void jumpPage(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	    rd.forward(request, response);
	}
    
    protected void jumpPageRedirect(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.include(request, response);
		response.sendRedirect(request.getContextPath() + url);
	}
    
    protected void jumpError(String message, String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
}