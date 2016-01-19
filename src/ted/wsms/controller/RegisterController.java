package ted.wsms.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ted.wsms.model.UserModel;
import ted.wsms.objects.UserObject;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/standalone/signup.jsp");
	    rd.forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String state = "inactive";

		try {
			
			ServletContext sc = getServletContext();
 
			UserObject userObj = new UserObject();
			
			userObj.setEmail(email);
			userObj.setName(name);
			userObj.setPassword(password);
			userObj.setState(state);
			userObj.setSurname(surname);
			userObj.setUsername(username);
			
			if( !UserModel.create(userObj) ) {
				
				RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/standalone/signup.jsp");
				if( UserModel.exists(username) ) {
		    	    request.setAttribute("message", "This username is already in use. Please try another one.");
				} else {
					request.setAttribute("message", "Check again your submission form. Some error occured.");
				}
	    	    rd.forward(request, response); 
			} else {	
				RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/standalone/notify.jsp");
				rd.forward(request, response); 
			}	
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

}
