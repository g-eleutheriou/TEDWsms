package ted.wsms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ted.wsms.model.RoleModel;
import ted.wsms.model.UserModel;
import ted.wsms.objects.UserObject;


@WebServlet({"/users/edit", "/users/view"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			if( request.getParameter("delete") != null ) {
				delete(request, response);
			} else {
				editPost(request, response);
			}
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getServletPath();
		
		try {
			
			if (path.equals("/users/view")) {
				view(request, response);
	        } else if (path.equals("/users/edit")) {
	        	editGet(request, response);
	        } else {
	        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		} 
	}
	
	private void view (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String state = request.getParameter("state");
        List<UserObject> userListObj = UserModel.getUsers(state);
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/users/view_users.jsp");

	    request.setAttribute("usersListObj", userListObj);
	    request.setAttribute("state", state);
	    rd.forward(request, response); 
	}
	
	private void editPost (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	 
		String username = request.getParameter("username");
		String state = request.getParameter("state");
		String role = request.getParameter("role");
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/users/edit_user.jsp");
	    
	    if( role.equals("false") ) {
    	    request.setAttribute("message", "Please choose a role");
    	    request.setAttribute("type", "error");
    	    request.setAttribute("state", state);
    	    rd.forward(request, response); 	
    	    return;
	    }
		
		if(state == null) 
			state = "active";
		

		UserObject userObj = new UserObject();
		
		userObj.setUsername(username);
		userObj.setState(state);
		userObj.setRole(RoleModel.getRole(role));
		
		String message = "", type = "";
		if ( UserModel.update(userObj) ) {
			message = "Your changes have been saved.";
			type = "success";
		} else {
			message = "Some error occured. Please try again or contact us.";
			type = "error";
		}

	    request.setAttribute("userObj", userObj);
	    request.setAttribute("state", state);
	    request.setAttribute("roleObjList", RoleModel.getRoles());
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response); 	 
	}
	
	private void editGet (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String username = request.getParameter("username");
		String state = request.getParameter("state");
		
        UserObject userObj = UserModel.getUser(username);
        
        if (userObj == null ) {    

        	//TODO user either manipulated url or username was deleted/changed from db by another user
        	return;
        }
        
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/users/edit_user.jsp");

	    request.setAttribute("userObj", userObj);
	    request.setAttribute("state", state);
	    request.setAttribute("roleObjList", RoleModel.getRoles());
	    rd.forward(request, response); 	 
	}	
	
	private void delete (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String username = request.getParameter("username");
	
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/users/edit_user.jsp");
	   
		String message = "", type = "";
		if( UserModel.delete(username) ) {
			message = "Your changes have been saved.";
			type = "success";
		} else {
			message = "Some error occured. Please try again or contact us.";
			type = "error";
		}
		
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response); 	
	}	

}
