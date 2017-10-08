<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
// Store data in session variables
if(request.getParameter("execution") != null) {
	if(!request.getParameter("attribute").equals("")) {
		if(request.getParameter("value").equals("")){
			session.removeAttribute(request.getParameter("attribute"));
		}
		else {
			session.setAttribute(request.getParameter("attribute"),request.getParameter("value"));
		}
	}
	session = request.getSession(false);
}
// Just logged in
else if(request.getParameter("login")!= null && request.getParameter("password")!=null){
	if(request.getParameter("login").equals("Aux") && request.getParameter("password").equals("pass")) {
		session = request.getSession();
		session.setMaxInactiveInterval(30);
	}
	else response.sendRedirect("Login.jsp");
}
// Log in failed
else {
	response.sendRedirect("Login.jsp");
} %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Process</title>
</head>
<body>
	<form action='Process.jsp' method='POST'>
		Attribute : <input type='text' name='attribute'/>
		Value : <input type='text' name='value'/>
		<input type='submit' value='Execute'/>
		<input type='hidden' name='execution' value='true'/> 
	</form>
	<form action='Logout.jsp' method='POST'>
		<input type='submit' value='Log out'/> 
	</form>
</body>
</html>