package global;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class JDBC {
	private static ArrayList<String> roles = new ArrayList<String>(Arrays
			.asList("经理", "前台"));
	
	public static Connection getConnection(ServletContext sc) {
		Context ctx = null;
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            return null;
        }
        Connection cn = null;
        try {
            DataSource ds = (DataSource)sc.getAttribute("dataSource");
            if (ds == null) {
            	ds = (DataSource)ctx.lookup("java:comp/env/jdbc/HMS");
            	sc.setAttribute("dataSource", ds);
            }
            cn = ds.getConnection();
        } catch (NamingException e) {
        	// pass
        } catch (SQLException e) {
        	// pass
        }
        return cn;
	}
	
	public static String getStaffid(ServletContext sc, String role) {
		Integer roleid = roles.indexOf(role);
		String newStaffid = null;
		try {
			Connection cn = getConnection(sc);
			String sql = "SELECT 员工号 FROM 员工 WHERE 员工号 LIKE ?; ";
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1,roleid+"%");
			ResultSet rs = st.executeQuery();
			Integer count = 0;
			String currentStaffid = null;
			Integer currentRoleNumber = null, newRoleNumber = count + 1;
			while (rs.next()) {
				currentStaffid = rs.getString("员工号");
				currentRoleNumber = Integer.valueOf(currentStaffid.substring(1, currentStaffid.length()));
				if (currentRoleNumber > count + 1) {
					newRoleNumber = count + 1;
					break;
				}
				newRoleNumber = currentRoleNumber + 1;
				count = currentRoleNumber;
			}
			newStaffid = roleid + new DecimalFormat("000").format(newRoleNumber);
			st.close();
			cn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
		return newStaffid;
	}
	
	public static String getOrderid() {
		Date genTime = new Date( );
	      SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMMddhhmmssS");
	      return sdf.format(genTime);
	}
	
	public static boolean isRoomEmpty(String roomid) {
		return false;
	}
}
