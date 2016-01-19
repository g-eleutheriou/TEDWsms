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

import ted.wsms.objects.SupplyObject;

public class SupplyModel {

    public static List<SupplyObject> getSuppliesBySupplier(String ssn) throws SQLException {
    	
        List<SupplyObject> supplies = new ArrayList<SupplyObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT * FROM product_has_supplier " +
        				"WHERE supplier_ssn=?";

        PreparedStatement st = con.prepareStatement(query);

        st.setString(1, ssn);
        
        ResultSet rs = st.executeQuery();

        while ( rs.next() ) {
            
        	SupplyObject supply = new SupplyObject();
            
        	supply.setPrice(Integer.parseInt(rs.getString("price")));
        	supply.setProduct(ProductModel.getProduct((rs.getString("product_name")), rs.getString("product_serial_number")));
        	supply.setSupplier(SupplierModel.getSupplier(rs.getString("supplier_ssn")));
        
        	supplies.add(supply);
        }
        
        st.close();
        con.close();
        
        return supplies;
    }
    
    
public static List<SupplyObject> getSuppliesByProduct(String name ,String serial_number) throws SQLException {
    	
        List<SupplyObject> supplies = new ArrayList<SupplyObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT * FROM product_has_supplier " +
        				"WHERE product_name=? AND product_serial_number=?";

        PreparedStatement st = con.prepareStatement(query);

        st.setString(1, name);
        st.setString(2, serial_number);
        
        ResultSet rs = st.executeQuery();

        while ( rs.next() ) {
            
        	SupplyObject supply = new SupplyObject();
            
        	supply.setPrice(Integer.parseInt(rs.getString("price")));
        	supply.setProduct(ProductModel.getProduct((rs.getString("product_name")),rs.getString("product_serial_number")));
        	supply.setSupplier(SupplierModel.getSupplier(rs.getString("supplier_ssn")));
        
        	supplies.add(supply);
        }
        
        st.close();
        con.close();
        
        return supplies;
    }

    
	public static boolean delete(SupplyObject supplyObj) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "DELETE FROM product_has_supplier " +
    			"WHERE product_name=? AND product_serial_number=? AND supplier_ssn=?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, supplyObj.getProduct().getName());
        st.setString(2, supplyObj.getProduct().getSerialNumber());
        st.setString(3, supplyObj.getSupplier().getSsn());
		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
    
    public static boolean create(SupplyObject supplyObj) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "INSERT INTO product_has_supplier " +
    				   	"(product_name, product_serial_number, supplier_ssn, price) " + 
    			       "VALUES " + 
    				   	"(?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, supplyObj.getProduct().getName());
        st.setString(2, supplyObj.getProduct().getSerialNumber());
        st.setString(3, supplyObj.getSupplier().getSsn());
        st.setFloat(4, supplyObj.getPrice());

        
		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
}
