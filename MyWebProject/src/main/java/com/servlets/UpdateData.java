package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class UpdateData
 */
@WebServlet("/UpdateData")
public class UpdateData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpdateData() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String name = request.getParameter("name"); // Name is hidden (not editable)
		    String email = request.getParameter("email");
		    String password = request.getParameter("password");
		    String mobile = request.getParameter("mobile");
		    String gender = request.getParameter("gender");

		    try {
		        Connection con = DbConnection.DbConnect();
		        String query = "UPDATE Users SET email=?, password=?, mobile=?, gender=? WHERE name=?";
		        PreparedStatement pst = con.prepareStatement(query);
		        pst.setString(1, email);
		        pst.setString(2, password);
		        pst.setString(3, mobile);
		        pst.setString(4, gender);
		        pst.setString(5, name); 
		        int updated = pst.executeUpdate();
		        if (updated > 0) {
		            response.sendRedirect("ViewAllUsers"); 
		            } else {
		            response.getWriter().print("<h3>Update Failed</h3>");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}

}
