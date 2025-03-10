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
 * Servlet implementation class EditData
 */
@WebServlet("/EditData")
public class EditData extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public EditData() {
        super();
        // TODO Auto-generated constructor stub
    }


	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    PrintWriter pw = response.getWriter();
		    response.setContentType("text/html");

		    String name = request.getParameter("username");
		    
		    try {
		        Connection con = DbConnection.DbConnect();
		        String query = "SELECT * FROM Users WHERE name=?";
		        PreparedStatement stmt = con.prepareStatement(query);
		        stmt.setString(1, name);
		        ResultSet rs = stmt.executeQuery();

		        pw.print("<html><head><title>Edit User</title></head><body>");
		        pw.print("<h1>Please Modify Your Data Except Name</h1>");

		        if (rs.next()) {
		            pw.print("<form action='UpdateData' method='post'>");
		            pw.print("<input type='hidden' name='name' value='" + rs.getString(1) + "'>");
		            pw.print("Email: <input type='email' name='email' value='" + rs.getString(2) + "'><br>");
		            pw.print("Password: <input type='text' name='password' value='" + rs.getString(3) + "'><br>");
		            pw.print("Phone Number: <input type='text' name='mobile' value='" + rs.getString(4) + "'><br>");

		            // Gender Selection (pre-selected)
		            String gender = rs.getString(5);
		            pw.print("Gender: ");
		            pw.print("<input type='radio' name='gender' value='Male' " + (gender.equals("Male") ? "checked" : "") + "> Male ");
		            pw.print("<input type='radio' name='gender' value='Female' " + (gender.equals("Female") ? "checked" : "") + "> Female ");
		            
		            pw.print("<br><input type='submit' value='Update'>");
		            pw.print("</form>");
		        } else {
		            pw.print("<h3>User Not Found</h3>");
		        }

		        pw.print("</body></html>");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}


}
