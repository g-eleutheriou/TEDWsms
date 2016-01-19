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
import javax.servlet.http.HttpSession;

import ted.wsms.model.UserModel;
import ted.wsms.objects.UserObject;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/profile.jsp");

		try {
			
			UserObject userObj = new UserObject();
			
			userObj.setEmail(email);
			userObj.setSurname(surname);
			userObj.setUsername(username);
			userObj.setName(name);
			userObj.setPassword(password);
			
			String message = "", type = "";
			if ( !UserModel.updateProfile(userObj) ) {
				message = "Some error occured. Please try again or contact us.";
				type = "error";
			} else {
				message = "Your changes are complete.";
				type = "success";
			}

    	    request.setAttribute("message", message);
    	    request.setAttribute("type", type);
    	    rd.forward(request, response); 	 
    	    
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
	
        UserObject userObj = (UserObject)session.getAttribute("userLogin");
        
        if (userObj == null ) {    
        	//TODO user either manipulated url or username was deleted/changed from db by another user
        	return;
        }
        
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/profile.jsp");

	    request.setAttribute("userObj", userObj);
	    rd.forward(request, response); 	
	}
}
