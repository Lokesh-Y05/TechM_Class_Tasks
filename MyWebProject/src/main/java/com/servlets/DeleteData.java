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
 * Servlet implementation class DeleteData
 */
@WebServlet("/DeleteData")
public class DeleteData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteData() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter pw=response.getWriter();
            response.setContentType("text/html");
            String name=(String)request.getParameter("username");
            try {
            	Connection con=DbConnection.DbConnect();
            	
            	String query="delete from Users where name=?";
            	PreparedStatement smt=con.prepareStatement(query);
            	smt.setString(1,name);
            	int res=smt.executeUpdate();
            	if(res>0)
            	{   
            		RequestDispatcher rd=request.getRequestDispatcher("ViewAllUsers");
            		rd.forward(request, response);
            		
            	}
            	
            }catch(Exception e)
            {
            	e.printStackTrace();
            }
          }

}
