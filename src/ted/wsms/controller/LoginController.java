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


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/standalone/signin.jsp");
	    rd.forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			
	        UserObject userLogin = UserModel.getUser(username, password);
	        
	        if (userLogin == null) {   

        	    request.setAttribute("message", "No account was found with these credentials. Please check your username and password.");
        	    doGet(request, response); 
	        	 
        	} else if( userLogin.getState().equals("inactive") ) { 
        	
        	    request.setAttribute("message", "Your account is pending moderation.");
        	    doGet(request, response); 
        	    
        	} else {
        		
        		HttpSession session = request.getSession(true);
        		
                response.setContentType("text/html;charset=UTF-8");
                
                session.setAttribute("userLogin", userLogin);
                
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/welcome"));
        	} 			
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} 
	}
}
