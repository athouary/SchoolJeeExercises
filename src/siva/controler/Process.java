package siva.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Process
 */
@WebServlet("/Process")
public class Process extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Process() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("<!DOCTYPE html>\n" + 
				"<html>" + 
				"<head>" + 
				"<title>Process</title>" + 
				"</head>" +
				"<body>");
		response.getWriter().println("Session ID: " + this.session.getId());
		response.getWriter().println("<form action='Process' method='POST'>"
				+ "Attribute : <input type='text' name='attribute'/>"
				+ "Value : <input type='text' name='value'/>" +
				" <input type='submit' value='Execute'/>"
				+ "<input type='hidden' name='execution' value='true'/>" + 
				"</form>");
		response.getWriter().println("<form action='Logout' method='POST'>" +
				" <input type='submit' value='Log out'/>" + 
				"</form>");
		response.getWriter().println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			this.session = request.getSession(false);
			doGet(request, response);
		}
		// Just logged in
		else if(request.getParameter("login").equals("Aux") && request.getParameter("password").equals("pass")) {
			this.session = request.getSession();
			this.session.setMaxInactiveInterval(30);
			doGet(request, response);
		}
		// Log in failed
		else {
			response.sendRedirect("Login");
		}
	}
}
