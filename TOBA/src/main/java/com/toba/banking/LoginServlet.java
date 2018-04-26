package com.toba.banking;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.getRequestDispatcher("/WEB-INF/views/login.html").forward(request, response);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName("J.");
		user.setLastName("Smith");
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("user", user);
		
		if(username.equals("jsmith@toba.com") && password.equals("letmein"))
			request.getRequestDispatcher("/WEB-INF/views/account_activity.jsp").forward(request, response);
		else
			request.getRequestDispatcher("/WEB-INF/views/login_failure.html").forward(request, response);
	}

}