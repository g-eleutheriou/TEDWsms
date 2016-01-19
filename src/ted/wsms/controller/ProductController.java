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
import ted.wsms.model.SupplierModel;
import ted.wsms.model.TransactionModel;
import ted.wsms.model.TypeModel;
import ted.wsms.model.WarehouseModel;
import ted.wsms.objects.ProductObject;
import ted.wsms.objects.TransactionObject;
import ted.wsms.objects.WarehouseObject;

@WebServlet({"/products/view", "/products/edit", "/products/create"})
public class ProductController extends HttpServlet {
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
			if (path.equals("/products/view")) {
				view(request, response);
	        } else if (path.equals("/products/edit")) {
	        	editGet(request, response);
	        } else {
	        	createGet(request, response);
	        }
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		} 
	}
	
	private void view(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
			
		String option = request.getParameter("option");
		String input = request.getParameter("input");
		List<ProductObject> productsListObj;
		
		if(option == null) {
			productsListObj = ProductModel.getProducts();
		} else if(option.equals("description")) {
			productsListObj = ProductModel.getProductsByDescription(input);
		} else if(option.equals("serial_number")) {
			productsListObj = ProductModel.getProductsBySerialNumber(input);
		} else if(option.equals("supplier")) {
			productsListObj = ProductModel.getProductsBySupplier(input);
		} else if(option.equals("name")) {
			productsListObj = ProductModel.getProductsByName(input);
		} else {
			productsListObj = ProductModel.getProductsByWarehouse(input);
			request.setAttribute("warehouse", "&warehouse=" + input); 
		}
		
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/products/view_products.jsp");

	    request.setAttribute("productsListObj", productsListObj); 
	    rd.forward(request, response); 	 
	}
	
	private void editGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String name = request.getParameter("name");
		String serial_number = request.getParameter("serial_number");
		
		ProductObject productObj = ProductModel.getProduct(name, serial_number);
		
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/products/edit_product.jsp");

	    request.setAttribute("productObj", productObj);
	    request.setAttribute("typeObjList", TypeModel.getTypes());
	    rd.forward(request, response); 	 
	}
	
	private void editPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

		String name = request.getParameter("name");
		String serial_number = request.getParameter("serial_number");
	
		String product_type = request.getParameter("type");
		String description = request.getParameter("description");
		String weight = request.getParameter("weight");
		String volume = request.getParameter("volume");
		String dimensions = request.getParameter("dimensions");
	
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/products/edit_product.jsp");
	
		ProductObject productObj = new ProductObject();
		
		productObj.setDescription(description);
		productObj.setDimensions(dimensions);
		productObj.setName(name);
		productObj.setSerialNumber(serial_number);
		productObj.setType(TypeModel.getType(product_type));
		productObj.setVolume(volume);
		productObj.setWeight(weight);
		
		String message = "", type = "";
		if ( ProductModel.update(productObj) ) {
			message = "Your changes have been saved.";
			type = "success";
		} else {
			message = "Some error occured. Please try again or contact us.";
			type = "error";
		}

	    request.setAttribute("productObj", productObj);
	    request.setAttribute("typeObjList", TypeModel.getTypes());
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response); 
 
	}
	
	private void createGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/products/create_product.jsp");

	    request.setAttribute("typeObjList", TypeModel.getTypes());
	    request.setAttribute("supplierObjList", SupplierModel.getSuppliers());
	    request.setAttribute("warehouseObjList", WarehouseModel.getWarehouses());
	    rd.forward(request, response); 	 
	}
	
	private void createPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String name = request.getParameter("name");
		String serial_number = request.getParameter("serial_number");
	
		String product_type = request.getParameter("type");
		String description = request.getParameter("description");
		String weight = request.getParameter("weight");
		String volume = request.getParameter("volume");
		String dimensions = request.getParameter("dimensions");
		
		String supplier = request.getParameter("supplier");
		String warehouse = request.getParameter("warehouse");
		String quantity = request.getParameter("quantity");
	
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/products/create_product.jsp");
	
		ProductObject productObj = new ProductObject();
		
		productObj.setDescription(description);
		productObj.setDimensions(dimensions);
		productObj.setName(name);
		productObj.setSerialNumber(serial_number);
		productObj.setType(TypeModel.getType(product_type));
		productObj.setVolume(volume);
		productObj.setWeight(weight);
		
		String message = "", type = "";
		
		if( !ProductModel.exists(name, serial_number) ) {
		
			if ( !ProductModel.create(productObj) ) {
				message = "Some error occured. Please try again or contact us.";
				type = "error";
			} else {
				
				message = "Your changes have been saved.";
				type = "success";
				
				if( !quantity.equals("") && !warehouse.equals("no_input") && !supplier.equals("no_input") ) {
					
					TransactionObject transactionObj = new TransactionObject();
					WarehouseObject warehouseObj = new WarehouseObject();
					warehouseObj.setName(warehouse);
					
					transactionObj.setProduct(productObj);
					transactionObj.setQuantity(quantity);
					transactionObj.setAction("insertion");
					transactionObj.setTargetWarehouse(warehouseObj);
					
					if( !TransactionModel.createTransaction(transactionObj) ) {
						message = "Some error occured. Please try again or contact us.";
						type = "error";
					}
				}
			}
		} else {
			message = "This products already exists.";
			type = "error";
		}
		
	    request.setAttribute("productObj", productObj);
	    request.setAttribute("typeObjList", TypeModel.getTypes());
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response); 
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String name = request.getParameter("name");
		String serial_number = request.getParameter("serial_number");
	
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/products/edit_product.jsp");
	
		ProductObject productObj = new ProductObject();
		
		String message = "", type = "";
		if ( ProductModel.delete(name, serial_number) ) {
			message = "Your changes have been saved.";
			type = "success";
		} else {
			message = "Some error occured. Please try again or contact us.";
			type = "error";
		}

	    request.setAttribute("productObj", productObj);
	    request.setAttribute("typeObjList", TypeModel.getTypes());
	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    rd.forward(request, response); 
	}
}
