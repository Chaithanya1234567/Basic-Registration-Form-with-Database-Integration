package com.project.module;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Register extends HttpServlet 
{
	private String db_url="jdbc:mysql://LocalHost:3306/jeepractice"; 
	private String db_userName="root";
	private String db_pswd="Chaithu";
	private Connection con;
	private PreparedStatement pstmt;
	@Override
	protected void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		int phone=Integer.parseInt(req.getParameter("phone"));
		String password=req.getParameter("password");
		String conformPassword=req.getParameter("conformPassword");
		String address=req.getParameter("address");
		String sql="insert into `user` (`name`,`email`,`phone`,`password`,`address`) values (?,?,?,?,?)";
		if(password.equals(conformPassword)) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(db_url,db_userName,db_pswd);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setInt(3, phone);
			pstmt.setString(4,password);
			pstmt.setString(5, address);
			int x=pstmt.executeUpdate();
			if(x!=0) {
				res.getWriter().println("Registered :)");
			}else {
				res.getWriter().println("Not Registered");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		}else {
			res.getWriter().println("password and conformPassword don't match");
		}
	}

}
