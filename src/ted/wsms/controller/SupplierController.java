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

import ted.wsms.model.SupplierModel;
import ted.wsms.objects.SupplierObject;

@WebServlet({"/suppliers/edit", "/suppliers/view", "/suppliers/create"})
public class SupplierController extends HttpServlet {
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
			if (path.equals("/suppliers/view")) {
				view(request, response);
	        } else if (path.equals("/suppliers/edit")) {
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
		
		List<SupplierObject> suppliersListObj = SupplierModel.getSuppliers();
    	
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/suppliers/view_suppliers.jsp");

	    request.setAttribute("suppliersListObj", suppliersListObj);
	    rd.forward(request, response); 	 
	}
	
	private void editGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String ssn = request.getParameter("ssn");
		
        SupplierObject supplierObj = SupplierModel.getSupplier(ssn);
        
        if (supplierObj == null ) {    
        	//TODO user either manipulated url or username was deleted/changed from db by another user
        	return;
        }	
        
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/suppliers/edit_supplier.jsp");

	    request.setAttribute("supplierObj", supplierObj);
	    rd.forward(request, response); 	 	
	}
	
	private void editPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String address = request.getParameter("address");
		String ssn = request.getParameter("ssn");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/suppliers/edit_supplier.jsp");
		
		SupplierObject supplierObj = new SupplierObject();
		
		supplierObj.setName(name);
		supplierObj.setSurname(surname);
		supplierObj.setAdress(address);
		supplierObj.setSsn(ssn);
		supplierObj.setPhone(phone);
		supplierObj.setEmail(email);
		
		String message = "", type = "";
		if ( SupplierModel.update(supplierObj) ) {
			message = "Your changes have been saved.";
			type = "success";
		} else {
			message = "Some error occured. Please try again or contact us.";
			type = "error";
		}

		request.setAttribute("supplierObj", supplierObj);
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response); 	
	}
	
	private void createGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/suppliers/create_supplier.jsp");

	    rd.forward(request, response); 	
	}
	
	private void createPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String address = request.getParameter("address");
		String ssn = request.getParameter("ssn");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/suppliers/create_supplier.jsp");
		
		SupplierObject supplierObj = new SupplierObject();
		
		supplierObj.setName(name);
		supplierObj.setSurname(surname);
		supplierObj.setAdress(address);
		supplierObj.setSsn(ssn);
		supplierObj.setPhone(phone);
		supplierObj.setEmail(email);
		
		String message = "", type = "";
		if( !SupplierModel.exists(ssn) ) {
			if ( !SupplierModel.create(supplierObj) ) {
				message = "Some error occured. Please try again or contact us.";
				type = "error";
			} else {
				message = "Your changes have been saved.";
				type = "success";
			}
		} else {
			message = "This supplier already exists.";
			type = "error";
		}
		
		request.setAttribute("supplierObj", supplierObj);
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response); 	
	}
	
	private void delete (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String supplier = request.getParameter("ssn");
	
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/suppliers/edit_supplier.jsp");
	   
		String message = "", type = "";
		if( SupplierModel.delete(supplier) ) {
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
