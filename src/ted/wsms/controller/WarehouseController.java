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

import ted.wsms.model.WarehouseModel;
import ted.wsms.objects.WarehouseObject;

@WebServlet({"/warehouses/edit", "/warehouses/view", "/warehouses/create"})
public class WarehouseController extends HttpServlet {
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
			
			if (path.equals("/warehouses/view")) {
				view(request, response);
	        } else if (path.equals("/warehouses/edit")) {
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

		List<WarehouseObject> warehousesListObj;
		
		String name = request.getParameter("name");
		String serial_number = request.getParameter("serial_number");
		
		if( name != null && serial_number != null ) {
			warehousesListObj = WarehouseModel.getWarehousesByProduct(name, serial_number);
		} else {
			warehousesListObj = WarehouseModel.getWarehouses();
		}
		
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/warehouses/view_warehouses.jsp");

	    request.setAttribute("warehousesListObj", warehousesListObj);
	    rd.forward(request, response); 	 		
	}
	
	private void editGet (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String name = request.getParameter("name");
		
        WarehouseObject warehouseObj = WarehouseModel.getWarehouse(name);
        
        if (warehouseObj == null ) {    
        	//TODO user either manipulated url or username was deleted/changed from db by another user
        	return;
        }	
        
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/warehouses/edit_warehouse.jsp");

	    request.setAttribute("warehouseObj", warehouseObj);
	    rd.forward(request, response); 	
	}

	private void editPost (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		String state = request.getParameter("state");
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/warehouses/edit_warehouse.jsp");
		
		WarehouseObject warehouseObj = new WarehouseObject();
		
		warehouseObj.setName(name);
		warehouseObj.setDescription(description);
		warehouseObj.setLocation(location);
		warehouseObj.setState(state);
		
		String message = "", type = "";
		if ( !WarehouseModel.update(warehouseObj) ) {
			message = "Some error occured. Please try again or contact us.";
			type = "error";
		} else {
			message = "Your changes have been saved.";
			type = "success";
		}
	
		request.setAttribute("warehouseObj", warehouseObj);
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response);
	}
	
	private void createGet (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/warehouses/create_warehouse.jsp");

	    rd.forward(request, response); 	
	}
	
	private void createPost (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		String state = request.getParameter("state");
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/warehouses/create_warehouse.jsp");
		
		WarehouseObject warehouseObj = new WarehouseObject();
		
		warehouseObj.setName(name);
		warehouseObj.setDescription(description);
		warehouseObj.setLocation(location);
		warehouseObj.setState(state);
		
		String message = "", type = "";
		if( !WarehouseModel.exists(name) ) {
			if ( !WarehouseModel.create(warehouseObj) ) {
				message = "Some error occured. Please try again or contact us.";
				type = "error";
			} else {
				message = "Your changes have been saved.";
				type = "success";
			}
		} else {
			message = "This warehouse already exists.";
			type = "error";
		}
		
		request.setAttribute("warehouseObj", warehouseObj);
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response);
	}
	
	private void delete (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String warehouse = request.getParameter("name");
	
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/warehouses/edit_warehouse.jsp");
	   
		String message = "", type = "";
		if( WarehouseModel.delete(warehouse) ) {
			message = "Some error occured. Please try again or contact us.";
			type = "error";
		} else {
			message = "Your changes have been saved.";
			type = "success";
		}
		
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response); 	
	}
}
