package com.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserLogin() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String email=(String)request.getParameter("email");
		String password=(String)request.getParameter("password");
		try{
			Connection con=DbConnection.DbConnect();
			String query="select * from Users where email=? and password=?";
			PreparedStatement smt=con.prepareStatement(query);
			smt.setString(1, email);
			smt.setString(2, password);
			ResultSet res=smt.executeQuery();
			HttpSession hs=request.getSession();
			
			if(res.next())
			{   
				  String name = res.getString("name");
		         hs.setAttribute("username", name);
				pw.print("<h1 style='color:green' >Login Successfull</h1>");
				RequestDispatcher rd=request.getRequestDispatcher("profile.jsp");
				rd.include(request, response);
			}else
			{
				pw.print("<h1 style='color:red'>Login Failed</h1>");
				RequestDispatcher rd=request.getRequestDispatcher("register.html");
				rd.include(request, response);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	

}
