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
import ted.wsms.objects.RoleObject;

@WebServlet({"/roles/edit", "/roles/view", "/roles/create"})
public class RoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if( request.getParameter("delete") != null ) {
				delete(request, response);
			} else if( request.getParameter("create") != null ) {
				createPost(request, response);
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
			if (path.equals("/roles/view")) {
				view(request, response);
	        } else if (path.equals("/roles/edit")) {
	        	editGet(request, response);
	        } else {
	        	createGet(request, response);
	        }
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		} 
	}
	
	private void view (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<RoleObject> rolesListObj = RoleModel.getRoles();
    	
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/roles/view_roles.jsp");

	    request.setAttribute("rolesListObj", rolesListObj);
	    rd.forward(request, response); 	 
	}
	
	private void editGet (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	
		String name = request.getParameter("name");
		
        RoleObject roleObj = RoleModel.getRole(name);
        
        if (roleObj == null ) {    
        	//TODO user either manipulated url or username was deleted/changed from db by another user
        	return;
        }	
        
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/roles/edit_role.jsp");

	    request.setAttribute("roleObj", roleObj);
	    rd.forward(request, response); 	 
	}
	
	private void editPost (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String warehouses = request.getParameter("warehouses");
		String products = request.getParameter("products");
		String suppliers = request.getParameter("suppliers");
		String messages = request.getParameter("messages");
		String roles = request.getParameter("roles");
		String users = request.getParameter("users");
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/roles/edit_role.jsp");
	    
	    if( name.equals("") || description.equals("") ) {
    	    request.setAttribute("message", "Please choose a role");
    	    request.setAttribute("type", "error");
    	    rd.forward(request, response); 	
    	    return;
	    }
		
		RoleObject roleObj = new RoleObject();
		
		roleObj.setName(name);
		roleObj.setDescription(description);
		roleObj.setWarehousesPermissions(warehouses);
		roleObj.setProductsPermissions(products);
		roleObj.setSuppliersPermissions(suppliers);
		roleObj.setMessagesPermissions(messages);
		roleObj.setRolesPermissions(roles);
		roleObj.setUsersPermissions(users);
		
		String message = "", type = "";
		if ( RoleModel.update(roleObj) ) {
			message = "Your changes have been changed.";
			type = "success";
		} else {
			message = "Some error occured. Please try again or contact us.";
			type = "error";
		}

		request.setAttribute("roleObj", roleObj);
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response); 	 
	}
	
	private void createGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/roles/create_role.jsp");

	    rd.forward(request, response); 	 
	}
	
	private void createPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String warehouses = request.getParameter("warehouses");
		String products = request.getParameter("products");
		String suppliers = request.getParameter("suppliers");
		String messages = request.getParameter("messages");
		String roles = request.getParameter("roles");
		String users = request.getParameter("users");
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/roles/create_role.jsp");
	    
		RoleObject roleObj = new RoleObject();
		
		roleObj.setName(name);
		roleObj.setDescription(description);
		roleObj.setWarehousesPermissions(warehouses);
		roleObj.setProductsPermissions(products);
		roleObj.setSuppliersPermissions(suppliers);
		roleObj.setMessagesPermissions(messages);
		roleObj.setRolesPermissions(roles);
		roleObj.setUsersPermissions(users);
		
		String message = "", type = "";
		if( !RoleModel.exists(name) ) {
			if ( RoleModel.create(roleObj) ) {
				message = "Your changes have been saved.";
				type = "success";
			} else {
				message = "Some error occured. Please try again or contact us.";
				type = "error";
			}
		} else {
			message = "This role already exists.";
			type = "error";
		}
		
		request.setAttribute("roleObj", roleObj);
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response);
	}
	
	private void delete (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String role = request.getParameter("name");
	
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/roles/edit_role.jsp");
	   
		String message = "", type = "";
		if( RoleModel.delete(role) ) {
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
