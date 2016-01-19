package ted.wsms.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.ConnectionPool;

public enum ConnectionSingleton {
    INSTANCE;
    private DataSource conPool;
    
    private ConnectionSingleton() 
    {
        try 
        {   
            Context ic = new InitialContext();
            Context envCtx = (Context) ic.lookup("java:comp/env");
            conPool = (DataSource) envCtx.lookup("jdbc/mydb");
        } catch (NamingException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection() throws SQLException 
    {
        return conPool.getConnection();
    }
}
