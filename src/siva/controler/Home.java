package siva.controler;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		Enumeration<String> en=request.getParameterNames();
		response.getWriter().println("<!DOCTYPE html>\n" + 
				"<html>" + 
				"<head>" + 
				"<title>Lab1</title>" + 
				"</head>" +
				"<body>");
		while(en.hasMoreElements())
		{
			String param=en.nextElement();
			String value=request.getParameter(param);
			response.getWriter().println("<form action='Home' method='GET' >" + 
					" Login: <input type='text' name='login' />" + 
					" Password: <input type='password' name='password' />" + 
					" <input type='submit' value='Connect' />" + 
					"</form>");
		};
		response.getWriter().println("</ul></body>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
