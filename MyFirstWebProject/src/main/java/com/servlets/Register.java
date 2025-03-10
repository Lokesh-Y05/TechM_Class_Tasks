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
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Register() {
        super();
        // TODO Auto-generated constructor stub.
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter pw=response.getWriter();
	    response.setContentType("text/html");
	    String firstName=request.getParameter("FirstName");
	    String secondName=request.getParameter("SecondName");
	    String gender=(String)request.getParameter("gender");
	    String phoneNumber=(String)request.getParameter("phone");
	    String password=(String)request.getParameter("password");
	    String email=(String)request.getParameter("mail");
		try {
			Connection con=ConnectJdbc.ConnectDb();
			String query="insert into Users values(?,?,?,?,?,?)";
			PreparedStatement smt=con.prepareStatement(query);
			smt.setString(1, firstName);
			smt.setString(2, secondName);
			smt.setString(3, gender);
			smt.setString(4, phoneNumber);
			smt.setString(5, email);
			smt.setString(6, password);
			int res=smt.executeUpdate();
			if(res>0)
			{
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.forward(request, response);
				
			}else
				pw.print("Registration Failed!");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
