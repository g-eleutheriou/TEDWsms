/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ted.wsms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ted.wsms.objects.ProductObject;



public class ProductModel {
	
	public static boolean create(ProductObject productObj) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "INSERT INTO product " +
    				   	"(name, serial_number, description, dimensions, volume, weight, type) " + 
    			       "VALUES " + 
    				   	"(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, productObj.getName());
        st.setString(2, productObj.getSerialNumber());
        st.setString(3, productObj.getDescription());
        st.setString(4, productObj.getDimensions());
        st.setString(5, productObj.getVolume());
        st.setString(6, productObj.getWeight());
        st.setString(7, productObj.getType().getName());
        
		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
	
	
	public static boolean delete(String name, String serial_number) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "DELETE FROM product WHERE name=? AND serial_number=?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, name);
        st.setString(2, serial_number);
		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }

    public static List<ProductObject> getProducts() throws SQLException {
    	
        List<ProductObject> products = new ArrayList<ProductObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT * FROM product";

        PreparedStatement st = con.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while ( rs.next() ) {
            
        	ProductObject productObj = new ProductObject();
            
        	productObj.setDescription(rs.getString("description"));
        	productObj.setSerialNumber(rs.getString("serial_number"));
        	productObj.setName(rs.getString("name"));
        	productObj.setDimensions(rs.getString("dimensions"));
        	productObj.setVolume(rs.getString("volume"));
        	productObj.setWeight(rs.getString("weight"));
        	productObj.setType(TypeModel.getType(rs.getString("type")));
        	productObj.setQuantity(ProductModel.getQuantityOfProduct(rs.getString("name"),rs.getString("serial_number") ));
        	
        	products.add(productObj);
        }
        
        st.close();
        con.close();
        
        return products;
    }
    
    
    public static List<ProductObject> getProductsBySupplier(String ssn) throws SQLException {
    	
        List<ProductObject> products = new ArrayList<ProductObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT product_name,product_serial_number FROM product_has_supplier WHERE supplier_ssn=?";

        PreparedStatement st = con.prepareStatement(query);
        
        st.setString(1, ssn);

        ResultSet rs = st.executeQuery();
        
        query="SELECT * FROM product WHERE name=? AND serial_number=?";

        while ( rs.next() ) {
        	          
        	ProductObject productObj;
        	
        	st = con.prepareStatement(query);
            
            st.setString(1, rs.getString("product_name"));
            st.setString(2, rs.getString("product_serial_number"));
                        
            ResultSet rs2 = st.executeQuery();
            
            if ( rs2.next() ) {  
            	
            	productObj = new ProductObject();
            	
	        	productObj.setDescription(rs2.getString("description"));
	        	productObj.setSerialNumber(rs2.getString("serial_number"));
	        	productObj.setName(rs2.getString("name"));
	        	productObj.setDimensions(rs2.getString("dimensions"));
	        	productObj.setVolume(rs2.getString("volume"));
	        	productObj.setWeight(rs2.getString("weight"));
	        	productObj.setType(TypeModel.getType(rs2.getString("type")));
	        	productObj.setQuantity(ProductModel.getQuantityOfProduct(rs2.getString("name"),rs2.getString("serial_number") ));
	
	        	products.add(productObj);
            } else {
            	productObj = null;
            }
        }
        
        st.close();
        con.close();
        
        return products;
    }
    
    public static List<ProductObject> getProductsByDescription(String description) throws SQLException {
    	
        List<ProductObject> products = new ArrayList<ProductObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT * FROM product WHERE description LIKE ?";

        PreparedStatement st = con.prepareStatement(query);
        
        st.setString(1, description);

        ResultSet rs = st.executeQuery();

        while ( rs.next() ) {
            
        	ProductObject productObj = new ProductObject();
            
        	productObj.setDescription(rs.getString("description"));
        	productObj.setSerialNumber(rs.getString("serial_number"));
        	productObj.setName(rs.getString("name"));
        	productObj.setDimensions(rs.getString("dimensions"));
        	productObj.setVolume(rs.getString("volume"));
        	productObj.setWeight(rs.getString("weight"));
        	productObj.setType(TypeModel.getType(rs.getString("type")));
        	productObj.setQuantity(ProductModel.getQuantityOfProduct(rs.getString("name"),rs.getString("serial_number") ));

        	products.add(productObj);
        }
        
        st.close();
        con.close();
        
        return products;
    }
     
    public static List<ProductObject> getProductsBySerialNumber(String serial_number) throws SQLException {
    	
        List<ProductObject> products = new ArrayList<ProductObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT * FROM product WHERE serial_number=?";

        PreparedStatement st = con.prepareStatement(query);
        
        st.setString(1, serial_number);

        ResultSet rs = st.executeQuery();

        while ( rs.next() ) {
            
        	ProductObject productObj = new ProductObject();
            
        	productObj.setDescription(rs.getString("description"));
        	productObj.setSerialNumber(rs.getString("serial_number"));
        	productObj.setName(rs.getString("name"));
        	productObj.setDimensions(rs.getString("dimensions"));
        	productObj.setVolume(rs.getString("volume"));
        	productObj.setWeight(rs.getString("weight"));
        	productObj.setType(TypeModel.getType(rs.getString("type")));
        	productObj.setQuantity(ProductModel.getQuantityOfProduct(rs.getString("name"),rs.getString("serial_number") ));

        	products.add(productObj);
        }
        
        st.close();
        con.close();
        
        return products;
    }
    
    public static List<ProductObject> getProductsByName(String name) throws SQLException {
    	
        List<ProductObject> products = new ArrayList<ProductObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT * FROM product WHERE name=?";

        PreparedStatement st = con.prepareStatement(query);
        
        st.setString(1, name);

        ResultSet rs = st.executeQuery();

        while ( rs.next() ) {
            
        	ProductObject productObj = new ProductObject();
            
        	productObj.setDescription(rs.getString("description"));
        	productObj.setSerialNumber(rs.getString("serial_number"));
        	productObj.setName(rs.getString("name"));
        	productObj.setDimensions(rs.getString("dimensions"));
        	productObj.setVolume(rs.getString("volume"));
        	productObj.setWeight(rs.getString("weight"));
        	productObj.setType(TypeModel.getType(rs.getString("type")));
        	productObj.setQuantity(ProductModel.getQuantityOfProduct(rs.getString("name"),rs.getString("serial_number") ));

        	products.add(productObj);
        }
        
        st.close();
        con.close();
        
        return products;
    }

    public static ProductObject getProduct(String name, String serial_number) throws SQLException {
    	
    	ProductObject productObj = null;

    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        String query = "SELECT * " +
					   "FROM product " + 
				 	   "WHERE name = ? " +
				 	   "AND serial_number = ?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, name);
        st.setString(2, serial_number);
		ResultSet rs = st.executeQuery();
				
		if ( rs.next() ) {  

			productObj = new ProductObject();
            
        	productObj.setDescription(rs.getString("description"));
        	productObj.setSerialNumber(rs.getString("serial_number"));
        	productObj.setName(rs.getString("name"));
        	productObj.setDimensions(rs.getString("dimensions"));
        	productObj.setVolume(rs.getString("volume"));
        	productObj.setWeight(rs.getString("weight"));
        	productObj.setType(TypeModel.getType(rs.getString("type")));
        	productObj.setQuantity(ProductModel.getQuantityOfProduct(rs.getString("name"),rs.getString("serial_number") ));
            
        } else {
        	productObj = null;
        }

		st.close();
        con.close();
        
        return productObj;
    }
    
    public static boolean update(ProductObject productObj) throws SQLException {
        
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "UPDATE product " + 
        			   "SET description=?, dimensions=?, type=?, volume=?, weight=?" +
        			   "WHERE name=? AND serial_number=?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, productObj.getDescription());
        st.setString(2, productObj.getDimensions());
        st.setString(3, productObj.getType().getName());
        st.setString(4, productObj.getVolume());
        st.setString(5, productObj.getWeight());
        st.setString(6, productObj.getName());
        st.setString(7, productObj.getSerialNumber());

		int rs = st.executeUpdate();
		
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
    
    public static List<ProductObject> getProductsByWarehouse(String name) throws SQLException {
    	
        List<ProductObject> products = new ArrayList<ProductObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT product_name,product_serial_number FROM warehouse_has_product WHERE warehouse_name=?";

        PreparedStatement st = con.prepareStatement(query);
        
        st.setString(1, name);

        ResultSet rs = st.executeQuery();
        
        query="SELECT * FROM product WHERE name=? AND serial_number=?";

        while ( rs.next() ) {
        	          
        	ProductObject productObj;
        	
        	st = con.prepareStatement(query);
            
            st.setString(1, rs.getString("product_name"));
            st.setString(2, rs.getString("product_serial_number"));
                        
            ResultSet rs2 = st.executeQuery();
            
            if( rs2.next() ) {
            
            	productObj = new ProductObject();
            	
            	productObj.setDescription(rs2.getString("description"));
        		productObj.setSerialNumber(rs2.getString("serial_number"));
        		productObj.setName(rs2.getString("name"));
        		productObj.setDimensions(rs2.getString("dimensions"));
        		productObj.setVolume(rs2.getString("volume"));
        		productObj.setWeight(rs2.getString("weight"));
        		productObj.setType(TypeModel.getType(rs2.getString("type")));
        		productObj.setQuantity(ProductModel.getQuantityOfProductByWarehouse(name,rs2.getString("name"),rs2.getString("serial_number") ));
        	
        		products.add(productObj);
        	
            } else {
            	productObj = null;
            }
        }
        
        st.close();
        con.close();
        
        return products;
    }
    
    public static boolean exists(String name, String serial_number) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "SELECT 1 " +
				       "FROM product " + 
				 	   "WHERE name = ? " +
				       "AND serial_number = ?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, name);
        st.setString(2, serial_number);
		ResultSet rs = st.executeQuery();
		
		boolean flag;
		
		if ( rs.next() ) {
			flag = true;
        } else {
        	flag = false;
        }
		
		st.close();
        con.close();
        
        return flag;
    }    
    
	public static int getQuantityOfProduct(String name, String serial_number) throws SQLException {
    	
        int counter = 0;

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT quantity FROM warehouse_has_product WHERE product_name=? AND product_serial_number=?";

        PreparedStatement st = con.prepareStatement(query);
        
        st.setString(1, name);
        st.setString(2, serial_number);

        ResultSet rs = st.executeQuery();

        if( rs.next() ) {
        	counter = rs.getInt(1);
        }
        
        st.close();
        con.close();
        
        return counter;
    }
	
	
	public static int getQuantityOfProductByWarehouse(String warehouse_name, String name, String serial_number) throws SQLException {
    	
        int counter = 0;

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT quantity FROM warehouse_has_product WHERE product_name=? AND product_serial_number=? AND warehouse_name=?";

        PreparedStatement st = con.prepareStatement(query);
        
        st.setString(1, name);
        st.setString(2, serial_number);
        st.setString(3, warehouse_name);

        ResultSet rs = st.executeQuery();

        if( rs.next() ) {
        	counter = rs.getInt(1);
        }
        
        st.close();
        con.close();
        
        return counter;
    }
}
