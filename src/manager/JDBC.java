package manager;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBC {
	static Connection getConnector() {
		Context ctx = null;
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            return null;
        }
        Connection cn = null;
        try {
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HMS");
            cn = ds.getConnection();
        } catch (NamingException e) {
        	return null;
        } catch (SQLException e) {
        	return null;
        }
        return cn;
	}
}
