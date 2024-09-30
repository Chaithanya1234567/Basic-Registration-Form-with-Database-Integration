package com.project.module;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckEmail extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		String email=req.getParameter("email");
		if(email.length()>=10) {
			RequestDispatcher requestDispatcher=req.getRequestDispatcher("/Register");
			res.getWriter().println("Email Checked!");
//			requestDispatcher.forward(req, res);
			requestDispatcher.include(req, res);
		}else {
			res.getWriter().println("Check the email");
		}
	}
}
