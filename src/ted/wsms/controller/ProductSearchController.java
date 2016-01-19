package ted.wsms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ted.wsms.model.ProductModel;
import ted.wsms.model.SupplierModel;
import ted.wsms.objects.ProductObject;
import ted.wsms.objects.SupplierObject;

@WebServlet("/products/search")
public class ProductSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String auto  = request.getParameter("autocomplete");

		if(auto == null) {
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/view/componentbased/search_product.jsp");
			rd.forward(request, response); 	 
		} else {
			
			String q = request.getParameter("q").toLowerCase();
			String option = request.getParameter("option");
			boolean flag = true;
			
			try {
			
				JSONArray list = new JSONArray();
				List<ProductObject> prodList = ProductModel.getProducts();
				
				if( option.equals("name") ) {
					
					for (ProductObject productObject : prodList) {
						
						if(productObject.getName().toLowerCase().contains(q))	{
							flag = false;
							JSONObject obj = new JSONObject();
							obj.put("id", productObject.getName());
							obj.put("text", productObject.getName());
						
							list.add( obj );
						}
					}
	
				} else if( option.equals("serial_number") ) {
					
					for (ProductObject productObject : prodList) {
						if(productObject.getSerialNumber().toLowerCase().contains(q))	{
							flag = false;
							JSONObject obj = new JSONObject();
							obj.put("id", productObject.getSerialNumber());
							obj.put("text", productObject.getSerialNumber());
							list.add( obj );
						}
					}
				} else if( option.equals("supplier") ) {
					
					for (SupplierObject supplierObject : SupplierModel.getSuppliers()) {
						
						String line = supplierObject.getSsn() + " - " + supplierObject.getName() + " " + supplierObject.getSurname();
						if(line.toLowerCase().contains(q)) {
							flag = false;
							JSONObject obj = new JSONObject();
							obj.put("id", supplierObject.getSsn());
							obj.put("text", line);
							list.add( obj );
						}
					}
				} else if( option.equals("description") ) {
					
					for (ProductObject productObject : prodList) {
						if(productObject.getDescription().toLowerCase().contains(q)) {
							flag = false;
							JSONObject obj = new JSONObject();
							obj.put("id", productObject.getDescription());
							obj.put("text", productObject.getDescription());
							list.add( obj );
						}
					}					
					
				} else {
					flag = false;
					JSONObject obj = new JSONObject();
					obj.put("id", "No input");
					obj.put("text", "No results found...");
					list.add( obj );
				}
				
				if(flag) {
					JSONObject obj = new JSONObject();
					obj.put("id", "No input");
					obj.put("text", "No results found...");
					list.add( obj );
				}
				
				response.setContentType("application/json");   
				PrintWriter out = response.getWriter();
				out.print(list.toJSONString());
				out.flush(); 	 
				out.close();
				
			} catch (SQLException e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
		}
	}
}
