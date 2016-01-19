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

import ted.wsms.objects.TypeObject;

public class TypeModel {

    public static List<TypeObject> getTypes() throws SQLException {
    	
        List<TypeObject> types = new ArrayList<TypeObject>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        
   
	        con = ConnectionSingleton.INSTANCE.getConnection();
	        
	        String query = "SELECT * FROM product_type";

        st = con.prepareStatement(query);

        rs = st.executeQuery();

        while ( rs.next() ) {
            
        	TypeObject typeObj = new TypeObject();
            
        	typeObj.setName(rs.getString("name"));
        	typeObj.setDescription(rs.getString("description"));
        
        	types.add(typeObj);
        }
    
    	rs.close();
    	st.close();
    	con.close();	
    	
        return types;
    }

	public static TypeObject getType(String name) throws SQLException {
    	
    	TypeObject typeObj = null;
    	Connection con = null;
    	PreparedStatement st = null;
    	ResultSet rs = null;
    	
    
    	con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "SELECT * " +
				 	   "FROM product_type " + 
				 	   "WHERE name = ?";

    	st = con.prepareStatement(query);
				
        st.setString(1, name);
		rs = st.executeQuery();
				
		if ( rs.next() ) {  

			typeObj = new TypeObject();
            
			typeObj.setName(rs.getString("name"));
			typeObj.setDescription(rs.getString("description"));
            
        } else {
        	typeObj = null;
        }
		
		rs.close();
    	st.close();
    	con.close();	
    	
		return typeObj;
			
		
    }
    
    public static boolean create(TypeObject typeObj) throws SQLException {

    	Connection con = null;
    	PreparedStatement st = null;
    	
    	
		con = ConnectionSingleton.INSTANCE.getConnection();
    
    	String query = "INSERT INTO product_type " +
			   			"(name, description) " + 
			   		   "VALUES " + 
			   		    "(?, ?)";
	
		st = con.prepareStatement(query);
				
		st.setString(1, typeObj.getName());
		st.setString(2, typeObj.getDescription());
		
		int rs = st.executeUpdate();
		
		if(rs != 1) {
			return false;
		}

    	st.close();
    	con.close();	
	
		return true;
	}
}
