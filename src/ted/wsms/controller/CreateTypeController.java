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

import ted.wsms.model.TypeModel;
import ted.wsms.objects.TypeObject;

@WebServlet("/type/create")
public class CreateTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/create_type.jsp");
			
		TypeObject typeObj = new TypeObject();
		
		typeObj.setName(name);
		typeObj.setDescription(description);
		
		String message = "", type = "";
		
		try {
			if ( TypeModel.create(typeObj) ) {
				message = "Your changes have been saved.";
				type = "success";
			} else {
				message = "Some error occured. Please try again or contact us.";
				type = "error";
			}
		} catch (SQLException e) {
			message = "Some error occured. Please try again or contact us.";
			type = "error";
			
			e.printStackTrace();
		}
		
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response); 	 
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/create_type.jsp");
	    
	    rd.forward(request, response); 	 
	}
}
