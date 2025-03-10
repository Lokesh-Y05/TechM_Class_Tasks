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
import java.sql.ResultSet;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter pw=response.getWriter();
            response.setContentType("text/html");
            String email=(String)request.getParameter("mail");
            String password=(String)request.getParameter("password");
            
            try {
            	Connection con=ConnectJdbc.ConnectDb();
            	String query="select * from Users where email=? and pass=? ";
            	PreparedStatement smt=con.prepareStatement(query);
            	smt.setString(1, email);
            	smt.setString(2, password);
            	ResultSet res=smt.executeQuery();
            	
            	if(res.next())
            	{
            		RequestDispatcher rd=request.getRequestDispatcher("profile.jsp");
            		rd.forward(request, response);
            	}else
            		pw.print("Login failed !");
            	
            }catch(Exception e)
            {
            	e.printStackTrace();
            }
	}

	

}
