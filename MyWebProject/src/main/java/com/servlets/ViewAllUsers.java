package com.servlets;

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
 * Servlet implementation class ViewAllUsers
 */
@WebServlet("/ViewAllUsers")
public class ViewAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ViewAllUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		try {
			Connection con=DbConnection.DbConnect();
			String query="Select * from Users";
			PreparedStatement smt=con.prepareStatement(query);
			ResultSet res=smt.executeQuery();
			pw.print("<h1>All Registered Users</h1>");
			pw.print("<table  border='1' cellpadding='5' cellspacing='0'>");
			pw.print("<tr>"
					+  "<th>Name</th> "
					+  " <th>Email</th>"
					+  " <th>Password</th>"
					+  " <th>Mobile</th>"
					+  " <th>Gender</th>"
					+  " <th>Edit_Data</th>"
					+  " <th>Delete_Data</th>"
					+"</tr>");
			while(res.next())
			{
				pw.print("<tr>"
						+ "<td> "+   res.getString(1)    + "</td>"
						+ "<td>" +   res.getString(2)    + "</td>"
						+ "<td>" +   res.getString(3)    + "</thd>"
						+ "<td>" +   res.getString(4)    + "</td>"
						+ "<td>" +   res.getString(5)    + "</td>"
						+ "<td>" +   " <a href='EditData?username=" + res.getString(1) + "'>Edit</a> "+ "</td>"
						+"<td>" + "<a href='DeleteData?username=" + res.getString(1) + "'>Delete</a>"+ "</td>"
						+ "</tr>");
			}
					pw.print("</table>");
					pw.print("<a href='index.html'>Home</a>");
		}catch(Exception e)
		{
			e.printStackTrace();
		}


	}

}
