/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ted.wsms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ted.wsms.objects.TransactionObject;

public class TransactionModel {
	
	public static boolean createTransaction(TransactionObject transactionObj) throws SQLException {
    	
    	Connection con = ConnectionSingleton.INSTANCE.getConnection();
        
    	String query = "INSERT INTO history_transactions " +
    				   	"(product_name, product_serial_number, source_warehouse_name, target_warehouse_name, quantity, action) " + 
    			       "VALUES " + 
    				   	"(?, ?, ?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(query);
				
        st.setString(1, transactionObj.getProduct().getName());
        st.setString(2, transactionObj.getProduct().getSerialNumber());
        if( transactionObj.getSourceWarehouse() != null){
        	st.setString(3, transactionObj.getSourceWarehouse().getName());
        }
        else{
        	st.setString(3, null);
        }
        if( transactionObj.getTargetWarehouse() != null){
        	st.setString(4, transactionObj.getTargetWarehouse().getName());
        }   
        else{
        	st.setString(4, null);
        }
        st.setString(5, transactionObj.getQuantity());
        st.setString(6, transactionObj.getAction());
        
		int rs = st.executeUpdate();
				
		st.close();
        con.close();
		
		if(rs != 1) {
			return false;
		}
		
        return true;
    }
}
