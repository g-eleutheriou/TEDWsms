package ted.wsms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ted.wsms.objects.SupplierObject;

public class SupplierModel {
	
	public static boolean create(SupplierObject supplierObj) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "INSERT INTO supplier " +
    				   	"(name, surname, address, ssn, phone, email) " + 
    			       "VALUES " + 
    				   	"(?, ?, ?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, supplierObj.getName());
        st.setString(2, supplierObj.getSurname());
        st.setString(3, supplierObj.getAddress());
        st.setString(4, supplierObj.getSsn());
        st.setString(5, supplierObj.getPhone());
        st.setString(6, supplierObj.getEmail());
        
		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }

    public static List<SupplierObject> getSuppliers() throws SQLException {
    	
        List<SupplierObject> suppliers = new ArrayList<SupplierObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT * FROM supplier";

        PreparedStatement st = con.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while ( rs.next() ) {
            
        	SupplierObject supplierObj = new SupplierObject();
            
        	supplierObj.setName(rs.getString("name"));
        	supplierObj.setSurname(rs.getString("surname"));
        	supplierObj.setAdress(rs.getString("address"));
        	supplierObj.setSsn(rs.getString("ssn"));
        	supplierObj.setPhone(rs.getString("phone"));
        	supplierObj.setEmail(rs.getString("email"));

        	suppliers.add(supplierObj);
        }
        
        st.close();
        con.close();
        
        return suppliers;
    }

    public static SupplierObject getSupplier(String ssn) throws SQLException {
    	
    	SupplierObject supplierObj = null;

    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        String query = "SELECT * " +
					   "FROM supplier " + 
				 	   "WHERE ssn = ?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, ssn);

		ResultSet rs = st.executeQuery();
				
		if ( rs.next() ) {  

			supplierObj = new SupplierObject();
            
			supplierObj.setName(rs.getString("name"));
			supplierObj.setSurname(rs.getString("surname"));
			supplierObj.setAdress(rs.getString("address"));
			supplierObj.setSsn(rs.getString("ssn"));
			supplierObj.setPhone(rs.getString("phone"));
			supplierObj.setEmail(rs.getString("email"));

            
        } else {
        	supplierObj = null;
        }

		st.close();
        con.close();
        
        return supplierObj;
    }
    
    public static boolean update(SupplierObject supplierObj) throws SQLException {
        
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        String query = "UPDATE supplier " + 
        						 "SET name=?, surname=?, " +
        						     "address=?," + 
        						     "phone=?, email=?" + 
        						 "WHERE ssn=?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, supplierObj.getName());
        st.setString(2, supplierObj.getSurname());
        st.setString(3, supplierObj.getAddress());
        st.setString(4, supplierObj.getPhone());
        st.setString(5, supplierObj.getEmail());
        st.setString(6, supplierObj.getSsn());
        
		int rs = st.executeUpdate();
		
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
    
    
    public static boolean delete(String ssn) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "DELETE FROM supplier WHERE ssn=?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, ssn);
		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
    
    public static boolean exists(String ssn) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "SELECT 1 " +
				       "FROM supplier " + 
				 	   "WHERE ssn = ?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, ssn);
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