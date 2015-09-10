package com.loaderstudio.todolist.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loaderstudio.todolist.beans.User;
import com.loaderstudio.todolist.constants.Constants;

public class LoginFilter implements Filter {
	
	public LoginFilter() {
    }
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		if (user == null) {
			session.invalidate();
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.sendRedirect(Constants.JUMP_LOGIN);
			return;
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		
	}
}