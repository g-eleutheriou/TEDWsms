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

import ted.wsms.objects.RoleObject;

public class RoleModel {

    public static List<RoleObject> getRoles() throws SQLException {
    	
        List<RoleObject> roles = new ArrayList<RoleObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT * FROM role";

        PreparedStatement st = con.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while ( rs.next() ) {
            
        	RoleObject roleObj = new RoleObject();
            
        	roleObj.setDescription(rs.getString("description"));
        	roleObj.setMessagesPermissions(rs.getString("messages_permission"));
        	roleObj.setName(rs.getString("name"));
        	roleObj.setProductsPermissions(rs.getString("products_permission"));
        	roleObj.setRolesPermissions(rs.getString("roles_permission"));
        	roleObj.setSuppliersPermissions(rs.getString("suppliers_permission"));
        	roleObj.setUsersPermissions(rs.getString("users_permission"));
        	roleObj.setWarehousesPermissions(rs.getString("warehouses_permission"));
        	
        	roles.add(roleObj);
        }
        
        st.close();
        con.close();
        
        return roles;
    }

    public static RoleObject getRole(String name) throws SQLException {
    	
    	RoleObject roleObj = null;

    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        String query = "SELECT * " +
				 	   "FROM role " + 
				 	   "WHERE name = ?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, name);
		ResultSet rs = st.executeQuery();
				
		if ( rs.next() ) {  

            roleObj = new RoleObject(); 
            
        	roleObj.setDescription(rs.getString("description"));
        	roleObj.setMessagesPermissions(rs.getString("messages_permission"));
        	roleObj.setName(rs.getString("name"));
        	roleObj.setProductsPermissions(rs.getString("products_permission"));
        	roleObj.setRolesPermissions(rs.getString("roles_permission"));
        	roleObj.setSuppliersPermissions(rs.getString("suppliers_permission"));
        	roleObj.setUsersPermissions(rs.getString("users_permission"));
        	roleObj.setWarehousesPermissions(rs.getString("warehouses_permission"));
            
        } else {
        	roleObj = null;
        }

		st.close();
        con.close();
        
        return roleObj;
    }
    
    public static boolean update(RoleObject roleObj) throws SQLException {
        
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        String query = "UPDATE role " + 
        			   "SET description=?, warehouses_permission=?, " +
        				   "products_permission=?, suppliers_permission=?, " + 
        				   "roles_permission=?, messages_permission=?, users_permission=?" + 
        			   "WHERE name=?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, roleObj.getDescription());
        st.setString(2, roleObj.getWarehousesPermissions());
        st.setString(3, roleObj.getProductsPermissions());
        st.setString(4, roleObj.getSuppliersPermissions());
        st.setString(5, roleObj.getRolesPermissions());
        st.setString(6, roleObj.getMessagesPermissions());
        st.setString(7, roleObj.getUsersPermissions());
        st.setString(8, roleObj.getName());
        
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
        
    	String query = "DELETE FROM role WHERE name=?";

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
	
    public static boolean create(RoleObject roleObj) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "INSERT INTO role " +
    				   	"(name, description, warehouses_permission, " +
    				   	"products_permission, suppliers_permission, " +
    				   	"roles_permission, messages_permission, users_permission) " + 
    			       "VALUES " + 
    				   	"(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, roleObj.getName());
        st.setString(2, roleObj.getDescription());
        st.setString(3, roleObj.getWarehousesPermissions());
        st.setString(4, roleObj.getProductsPermissions());
        st.setString(5, roleObj.getSuppliersPermissions());
        st.setString(6, roleObj.getRolesPermissions());
        st.setString(7, roleObj.getMessagesPermissions());
        st.setString(8, roleObj.getUsersPermissions());
        
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
				       "FROM role " + 
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

