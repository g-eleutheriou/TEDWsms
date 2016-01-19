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

import ted.wsms.objects.MessageObject;

public class MessageModel {

    public static List<MessageObject> getMessages() throws SQLException {
    	
        List<MessageObject> messages = new ArrayList<MessageObject>();

        Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
        String query = "SELECT * FROM messages";

        PreparedStatement st = con.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while ( rs.next() ) {
            
        	MessageObject messageObj = new MessageObject();
            
        	messageObj.setId(Integer.parseInt(rs.getString("message_id")));
        	messageObj.setName(rs.getString("name"));
        	messageObj.setSurname(rs.getString("surname"));
        	messageObj.setEmail(rs.getString("email"));
        	messageObj.setSubject(rs.getString("subject"));
        	messageObj.setMessage(rs.getString("message"));
        
        	messages.add(messageObj);
        }
        
        st.close();
        con.close();
        
        return messages;
    }

    public static MessageObject getMessage(int id) throws SQLException {
    	
    	MessageObject messageObj = null;

    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "SELECT * FROM messages WHERE message_id = ?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setInt(1, id);
		ResultSet rs = st.executeQuery();
				
		if ( rs.next() ) {  

        	messageObj = new MessageObject();
            
        	messageObj.setId(Integer.parseInt(rs.getString("message_id")));
        	messageObj.setName(rs.getString("name"));
        	messageObj.setSurname(rs.getString("surname"));
        	messageObj.setEmail(rs.getString("email"));
        	messageObj.setSubject(rs.getString("subject"));
        	messageObj.setMessage(rs.getString("message"));
            
        } else {
        	messageObj = null;
        }

		st.close();
        con.close();
        
        return messageObj;
    }
    
	public static boolean delete(int id) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "DELETE FROM messages WHERE message_id=?";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setLong(1, id);
		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
    
    public static boolean create(MessageObject messageObj) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "INSERT INTO messages " +
    				   	"(name, surname, email, subject, message) " + 
    			       "VALUES " + 
    				   	"(?, ?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, messageObj.getName());
        st.setString(2, messageObj.getSurname());
        st.setString(3, messageObj.getEmail());
        st.setString(4, messageObj.getSubject());
        st.setString(5, messageObj.getMessage());

        System.out.println(messageObj.getName());
        System.out.println(messageObj.getSurname());
        System.out.println(messageObj.getEmail());
        System.out.println(messageObj.getSubject());
        System.out.println(messageObj.getMessage());
        
		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
}
