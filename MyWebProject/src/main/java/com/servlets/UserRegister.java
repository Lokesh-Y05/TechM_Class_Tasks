package com.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UserRegister() {
        super();
    
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String email=(String)request.getParameter("email");
		String password=(String)request.getParameter("password");
		String mobile=(String)request.getParameter("mobile");
		String gender=(String)request.getParameter("gender");
		try {
			Connection con=DbConnection.DbConnect();
			 String query="insert into Users values(?,?,?,?,?)";
			 PreparedStatement smt=con.prepareStatement(query);
			 smt.setString(1,name);
			 smt.setString(2, email);
			 smt.setString(3, password);
			 smt.setString(4, mobile);
			 smt.setString(5, gender);
			 int res=smt.executeUpdate();
			 if(res>0)
			 {  
				pw.print("Please Login");
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.include(request, response);
			 }else {
				pw.print("Registarion Failed...!");
			 }
				 
			 
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
