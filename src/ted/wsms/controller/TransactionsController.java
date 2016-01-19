package ted.wsms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ted.wsms.model.ProductModel;
import ted.wsms.model.TransactionModel;
import ted.wsms.model.WarehouseModel;
import ted.wsms.objects.TransactionObject;
import ted.wsms.objects.WarehouseObject;


@WebServlet({"/transactions/view", "/transactions/create", "/transactions/quantity"})
public class TransactionsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getServletPath();

		try {
			if (path.equals("/transactions/create")) {
				createPost(request, response);
	        } else if (path.equals("/transactions/quantity")) {
	        	getQuantity(request, response);
	        } else {
	        	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	        }
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		} 
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getServletPath();

		try {
			if (path.equals("/transactions/view")) {
				view(request, response);
	        } else {
	        	createGet(request, response);
	        }
		}catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
	
	private void view(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String warehouse_name = request.getParameter("name");
		List<TransactionObject> transactionsListObj = WarehouseModel.getTransactionsByWarehouse(warehouse_name);
		
    	ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/transactions/view_transactions.jsp");

	    request.setAttribute("transactionsListObj", transactionsListObj); 
	    rd.forward(request, response); 	 
    	
	}
	
	private void createGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	
		ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/transactions/create_transaction.jsp");

	    if(request.getParameter("warehouse") != null) {
	    	List<WarehouseObject> warehouseObjList = new ArrayList<WarehouseObject>();
	    	warehouseObjList.add( WarehouseModel.getWarehouse(request.getParameter("warehouse")) );
	    	request.setAttribute("warehouseObjListSource", warehouseObjList); 
	    	request.setAttribute("warehouseObjListTarget", WarehouseModel.getWarehouses());
	    }
	    else {
	    	request.setAttribute("warehouseObjListSource", WarehouseModel.getWarehouses()); 
	    	request.setAttribute("warehouseObjListTarget", WarehouseModel.getWarehouses());
	    }
	    request.setAttribute("productObjList", ProductModel.getProducts());
	    rd.forward(request, response); 
	}
	
	private void createPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String warehouse_target = request.getParameter("warehouse_target");
		String warehouse_source = request.getParameter("warehouse_source");
		String[] product = request.getParameter("product").split("-");
		String quantity = request.getParameter("quantity");
		String action = request.getParameter("action");
		
		TransactionObject transactionObj = new TransactionObject();
		WarehouseObject warehouseObjTarget = null, warehouseObjSource = null;
		
		if( warehouse_target != null ) {
			warehouseObjTarget = new WarehouseObject();
			warehouseObjTarget.setName(warehouse_target);
		}

		if( warehouse_source != null ) {
			warehouseObjSource = new WarehouseObject();
			warehouseObjSource.setName(warehouse_source);
		}
		
		transactionObj.setTargetWarehouse(warehouseObjTarget);
		transactionObj.setSourceWarehouse(warehouseObjSource);
		transactionObj.setProduct(ProductModel.getProduct(product[0].trim(), product[1].trim()));
		transactionObj.setQuantity(quantity);
		transactionObj.setAction(action);
		
		String message="", type="";
		if( !TransactionModel.createTransaction(transactionObj) ) {
			message = "Some error occured. Please try again or contact us.";
			type = "error";
		} else {
			message = "Your changes have been saved.";
			type = "success";				
		}

	    request.setAttribute("message", message);
	    request.setAttribute("type", type);
	    createGet(request, response); 
	}
	
	private void getQuantity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

		String[] product = request.getParameter("product").split("-");
		String warehouse_name = request.getParameter("warehouse_name");
	
		int quantity = ProductModel.getQuantityOfProductByWarehouse(warehouse_name, product[0].trim(), product[1].trim());
		
		response.setContentType("application/text");   
		PrintWriter out = response.getWriter();
		out.print(quantity);
		out.flush(); 	 
		out.close();
	}
}
