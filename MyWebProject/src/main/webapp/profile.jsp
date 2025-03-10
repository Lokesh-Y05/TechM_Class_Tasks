<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile Page</title>
</head>
<body>
 <%
    HttpSession hs = request.getSession();
    String username = (String)hs.getAttribute("username");
%>
<h1>Welcome, <%= username %>!</h1>
<a href="index.html">Logout</a>
</body>
</html>