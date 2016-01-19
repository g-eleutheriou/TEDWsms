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

import ted.wsms.objects.TransactionObject;
import ted.wsms.objects.WarehouseObject;

public class WarehouseModel {

    public static List<WarehouseObject> getWarehouses() throws SQLException {
    	
        List<WarehouseObject> warehouses = new ArrayList<WarehouseObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT * FROM warehouse";

        PreparedStatement st = con.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while ( rs.next() ) {
            
        	WarehouseObject warehouseObj = new WarehouseObject();
            
        	warehouseObj.setName(rs.getString("name"));
        	warehouseObj.setDescription(rs.getString("description"));
        	warehouseObj.setLocation(rs.getString("location"));
        	warehouseObj.setState(rs.getString("state"));

        	warehouses.add(warehouseObj);
        }
        
        st.close();
        con.close();
        
        return warehouses;
    }

    public static WarehouseObject getWarehouse(String name) throws SQLException {
    	
    	WarehouseObject warehouseObj = null;

    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        String query = "SELECT * " +
					   "FROM warehouse " + 
				 	   "WHERE name = ? ";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, name);
		ResultSet rs = st.executeQuery();
				
		if ( rs.next() ) {  

			warehouseObj = new WarehouseObject();
            
        	warehouseObj.setName(rs.getString("name"));
        	warehouseObj.setDescription(rs.getString("description"));
        	warehouseObj.setLocation(rs.getString("location"));
        	warehouseObj.setState(rs.getString("state"));

            
        } else {
        	warehouseObj = null;
        }

		st.close();
        con.close();
        
        return warehouseObj;
    }
    
    public static List<WarehouseObject> getWarehousesByProduct(String name, String serial_number) throws SQLException {
    	
        List<WarehouseObject> warehouses = new ArrayList<WarehouseObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT warehouse_name FROM warehouse_has_product WHERE product_name=? AND product_serial_number=?";

        PreparedStatement st = con.prepareStatement(query);
        
        st.setString(1, name);
        st.setString(2, serial_number);

        ResultSet rs = st.executeQuery();
        
        query = "SELECT * FROM warehouse WHERE name = ?";

        while ( rs.next() ) {

        	st = con.prepareStatement(query);
            
            st.setString(1, rs.getString("warehouse_name"));

            ResultSet rs2 = st.executeQuery();
            if( rs2.next() ) {

            	WarehouseObject	warehouseObj = new WarehouseObject();

            	warehouseObj.setName(rs2.getString("name"));
        		warehouseObj.setDescription(rs2.getString("description"));
        		warehouseObj.setLocation(rs2.getString("location"));
        		warehouseObj.setState(rs2.getString("state"));

        		warehouses.add(warehouseObj);
            }
        }
        
        st.close();
        con.close();

        return warehouses;
    }
    
    public static boolean update(WarehouseObject warehouseObj) throws SQLException {
        
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        String query = "UPDATE warehouse " + 
        						 "SET name=?, description=?, " +
        						     "location=?, state=?, " + 
        						 "WHERE name=?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, warehouseObj.getName());
        st.setString(2, warehouseObj.getDescription());
        st.setString(3, warehouseObj.getLocation());
        st.setString(4, warehouseObj.getState());
        st.setString(5, warehouseObj.getName());
        
		int rs = st.executeUpdate();
		
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
    
    
    public static boolean delete(String name) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "DELETE FROM warehouse WHERE name=?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, name);

		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
    
    public static List<TransactionObject> getTransactionsByWarehouse(String name) throws SQLException {
    	
    	List<TransactionObject> transactions = new ArrayList<TransactionObject>();

    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        String query = "SELECT * " +
					   "FROM history_transactions " + 
				 	   "WHERE source_warehouse_name = ? " +
				 	   "OR target_warehouse_name = ?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, name);
        st.setString(2, name);
		ResultSet rs = st.executeQuery();
				
		while ( rs.next() ) {  

			TransactionObject transactionObj = new TransactionObject();
			
			transactionObj.setSourceWarehouse(WarehouseModel.getWarehouse(rs.getString("source_warehouse_name")));
			transactionObj.setTargetWarehouse(WarehouseModel.getWarehouse(rs.getString("target_warehouse_name")));
			transactionObj.setProduct(ProductModel.getProduct(rs.getString("product_name"), rs.getString("product_serial_number")));
			transactionObj.setQuantity(rs.getString("quantity"));
			transactionObj.setAction(rs.getString("action"));
			transactionObj.setDate(rs.getString("date"));     
			
			transactions.add(transactionObj);
        } 

		st.close();
        con.close();
        
        return transactions;
    }
    
	public static boolean create(WarehouseObject warehouseObj) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "INSERT INTO warehouse " +
    				   	"(name, description, location, state) " + 
    			       "VALUES " + 
    				   	"(?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, warehouseObj.getName());
        st.setString(2, warehouseObj.getDescription());
        st.setString(3, warehouseObj.getLocation());
        st.setString(4, warehouseObj.getState());
        
		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
	
    public static boolean exists(String name) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "SELECT 1 " +
				       "FROM warehouse " + 
				 	   "WHERE name = ?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, name);

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
}