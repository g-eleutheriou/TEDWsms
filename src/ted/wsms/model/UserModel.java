package ted.wsms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ted.wsms.objects.RoleObject;
import ted.wsms.objects.UserObject;

public class UserModel {

    public static List<UserObject> getUsers(String state) throws SQLException {
    	
        List<UserObject> users = new ArrayList<UserObject>();
        
        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT * " +
				 	   "FROM user " + 
				 	   "WHERE state = ?";

        PreparedStatement st = con.prepareStatement(query);

        st.setString(1, state);

        ResultSet rs = st.executeQuery();

        while ( rs.next() ) {
            
        	UserObject userObj = new UserObject();
            
        	userObj.setUsername(rs.getString("username"));
        	userObj.setPassword(rs.getString("password"));
        	userObj.setName(rs.getString("name"));
        	userObj.setSurname(rs.getString("surname"));
        	userObj.setEmail(rs.getString("email"));
        	
        	RoleObject roleObj = RoleModel.getRole(rs.getString("role"));
        	userObj.setRole(roleObj);
        	
        	users.add(userObj);
        }
        
        st.close();
        con.close();
        return users;
    }

    public static UserObject getUser(String username, String password) throws SQLException {
    	
    	UserObject userObj = null;
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "SELECT * " +
				 	   "FROM user " + 
				 	   "WHERE username = ? " + 
				 	   "AND password = ?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, username);
        st.setString(2, getHash(password));
		ResultSet rs = st.executeQuery();
				
		if ( rs.next() ) {  

            userObj = new UserObject();      
            
        	userObj.setUsername(rs.getString("username"));
        	userObj.setPassword(rs.getString("password"));
        	userObj.setName(rs.getString("name"));
        	userObj.setState(rs.getString("state"));
        	userObj.setSurname(rs.getString("surname"));
        	userObj.setEmail(rs.getString("email"));
        	userObj.setRole(RoleModel.getRole(rs.getString("role")));
        	
        } else {
        	userObj = null;
        }

		st.close();
        con.close();
        
        return userObj;
    }
    
    public static UserObject getUser(String username) throws SQLException {
    	
    	UserObject userObj = null;

    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "SELECT * " +
				 	   "FROM user " + 
				 	   "WHERE username = ?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, username);
		ResultSet rs = st.executeQuery();
				
		if ( rs.next() ) {  

            userObj = new UserObject();  
            
        	userObj.setUsername(rs.getString("username"));
        	userObj.setPassword(rs.getString("password"));
        	userObj.setName(rs.getString("name"));
        	userObj.setSurname(rs.getString("surname"));
        	userObj.setEmail(rs.getString("email"));
        	userObj.setRole(RoleModel.getRole(rs.getString("role")));
        	
        } else {
        	userObj = null;
        }

		st.close();
        con.close();
        
        return userObj;
    }
    
    public static boolean exists(String username) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "SELECT 1 " +
				       "FROM user " + 
				 	   "WHERE username = ?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, username);
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
    
    public static boolean update(UserObject userObj) throws SQLException {
    
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "UPDATE user " + 
        			   "SET state=?, role=?" + 
        			   "WHERE username=?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, userObj.getState());
        st.setString(2, userObj.getRole().getName());
        st.setString(3, userObj.getUsername());
        
		int rs = st.executeUpdate();
		
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }

    public static boolean updateProfile(UserObject userObj) throws SQLException {
    
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "UPDATE user " + 
        			   "SET name=?, surname=?, email=?, password=? " + 
        			   "WHERE username=?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, userObj.getName());
        st.setString(2, userObj.getSurname());
        st.setString(3, userObj.getEmail());
        st.setString(4, getHash(userObj.getPassword()));
        st.setString(5, userObj.getUsername());
        
		int rs = st.executeUpdate();
		
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    } 
    
    public static boolean delete(String username) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "DELETE FROM user WHERE username=?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, username);
		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
    
    public static boolean create(UserObject userObj) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "INSERT INTO user " +
    				   	"(username, password, name, surname, email, state) " + 
    			       "VALUES " + 
    				   	"(?, ?, ?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, userObj.getUsername());
        st.setString(2, getHash(userObj.getPassword()));
        st.setString(3, userObj.getName());
        st.setString(4, userObj.getSurname());
        st.setString(5, userObj.getEmail());
        st.setString(6, userObj.getState());
        
		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
    
public static String getHash(String password) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "SELECT SHA(?) ";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, password);
        
        ResultSet rs = st.executeQuery();
		
		if ( rs.next() ) {  
			return rs.getString(1);
        	
        } else {
        	 return null;
        }
    }
}
