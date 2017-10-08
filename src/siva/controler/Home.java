package siva.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private int state = 1; 

    /**
     * Default constructor. 
     */
    public Home() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("<!DOCTYPE html>\n" + 
				"<html>" + 
				"<head>" + 
				"<title>Login</title>" + 
				"</head>" +
				"<body>");
		switch(state) {
			case 1 :
				response.getWriter().println("<form action='Home' method='POST'>" + 
						" Login: <input type='text' name='login'/>" + 
						" Password: <input type='password' name='password'/>" + 
						" <input type='submit' value='Connect'/>" + 
						"</form>");
				break;
			case 2 :
				response.getWriter().println("<form action='Home' method='POST'>"
						+ "Attribute : <input type='text' name='attribute'/>"
						+ "Value : <input type='text' name='value'/>" +
						" <input type='submit' value='Execute'/>"
						+ "<input type='hidden' name='execution' value='true'/>" + 
						"</form>");
				response.getWriter().println("<form action='Home' method='POST'>" +
						" <input type='submit' value='Log out'/>" + 
						"</form>");
				break;
			case 3 :
				this.session.invalidate();
				response.getWriter().println("Successfully logged out. <a href='Home'>Go to login page</a>");
				state = 1;
				break;
			default :
				response.getWriter().println("Unexcpected behaviour");

		}
		response.getWriter().println("</body></html>");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Store data in session variables
		this.session = request.getSession(false);
		if(this.session!=null && request.getParameter("execution") != null) {
			if(!request.getParameter("attribute").equals("")) {
				if(request.getParameter("value").equals("")){
					session.removeAttribute(request.getParameter("attribute"));
				}
				else {
					session.setAttribute(request.getParameter("attribute"),request.getParameter("value"));
				}
			}
			state = 2;
			doGet(request, response);
		}
		// Just logged in
		else if (request.getParameter("login")!=null && request.getParameter("password")!=null) {			
			if(request.getParameter("login").equals("Aux") && request.getParameter("password").equals("pass")) {
				this.session = request.getSession();
				this.session.setMaxInactiveInterval(30);
				state = 2;
				doGet(request, response);
			}
			else {
				state = 1;
				doGet(request, response);
			}
		}
		// Log in failed
		else {
			state = 3;
			doGet(request, response);
		}
	}

}
