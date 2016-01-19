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

import ted.wsms.model.ProductModel;
import ted.wsms.model.RoleModel;
import ted.wsms.model.SupplierModel;
import ted.wsms.model.SupplyModel;
import ted.wsms.objects.SupplyObject;

@WebServlet({"/supplies/view", "/supplies/create", "/supplies/edit"})
public class SupplyController extends HttpServlet {
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
			if (path.equals("/supplies/view")) {
				view(request, response);
	        } else if (path.equals("/supplies/edit")) {
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
		String ssn = request.getParameter("ssn");

		try {
			List<SupplyObject> suppliesListObj = SupplyModel.getSuppliesBySupplier(ssn);
			
			ServletContext sc = getServletContext();
		    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/supplies/view_supplies.jsp");

		    request.setAttribute("suppliesListObj", suppliesListObj);
		    rd.forward(request, response); 	
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	private void editGet (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	

	}
	
	private void editPost (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

	}
	
	private void createGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/supplies/create_supply.jsp");

	    request.setAttribute("productObjList", ProductModel.getProducts());
	    rd.forward(request, response); 	 
	}
	
	private void createPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		float price = Float.parseFloat(request.getParameter("price"));
		String[] product = request.getParameter("product").split("-");
		String ssn = request.getParameter("ssn");
		
		System.out.println(price);
		System.out.println(product[0].trim() + " " + product[1].trim());
		System.out.println(ssn);
		
		SupplyObject supplyObj = new SupplyObject();
		
		supplyObj.setPrice(price);
		supplyObj.setProduct(ProductModel.getProduct(product[0].trim(), product[1].trim()));
		supplyObj.setSupplier(SupplierModel.getSupplier(ssn));
		
		
		String message = "", type = "";
		if ( SupplyModel.create(supplyObj) ) {
			message = "Your changes have been saved.";
			type = "success";
		} else {
			message = "Some error occured. Please try again or contact us.";
			type = "error";
		}
	
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    createGet(request, response);
	}
	
	private void delete (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String role = request.getParameter("name");
	
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/roles/edit_supply.jsp");
	   
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