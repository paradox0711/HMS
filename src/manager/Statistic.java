package manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import global.JDBC;

/**
 * Servlet implementation class Statistic
 */
@WebServlet("/Statistic")
public class Statistic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Statistic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		JSONObject res = new JSONObject();
		Connection cn = JDBC.getConnection(getServletContext());
		try {
			String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA = 'hms'; ";
			PreparedStatement st = cn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			ArrayList<String> tables = new ArrayList<String>();
			while (rs.next()) {
				if (!rs.getString(1).equals("×¡¿Í_¶©µ¥")) tables.add(rs.getString(1));	
			}
			for (String table : tables) {
				String[] allFields = getFields(cn, table);
				ArrayList<String> fields = new ArrayList<String>();
				for (String s : allFields) if (!s.equals("ÃÜÂë")) fields.add(s);
				res.put(table, fields);
			}
			rs.close();
			st.close();
			cn.close();
		} catch (SQLException e) {
			// pass
		}
		response.getWriter().print(res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		JSONObject res = new JSONObject();
		String table = request.getParameter("table");
		String field = request.getParameter("field");
		String queryValue = request.getParameter("queryvalue");
		Connection cn = JDBC.getConnection(getServletContext());
		try {
			String sql = "SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = ? AND TABLE_NAME = ? AND TABLE_SCHEMA = 'hms'; ";
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1, field);
			st.setString(2, table);
			ResultSet rs = st.executeQuery();
			String dataType = null;
			while (rs.next()) {
				dataType = rs.getString(1);
				break;
			}
			
			sql = "SELECT * FROM " + table + "  WHERE " + field + " LIKE '%" + queryValue + "%'; ";
			Statement statement = cn.createStatement();
			rs = statement.executeQuery(sql);
			int rowNum = 0;
			while (rs.next()) rowNum++;
			int colNum = rs.getMetaData().getColumnCount();
			String[][] result = new String[rowNum+1][colNum];
			result[0] = getFields(cn, table);
			for (int row = 1; row <= rowNum; row++) {
				rs.absolute(row);
				for(int col = 0; col < colNum; col++) result[row][col] = rs.getString(col+1);
			}
			res.put("queryresult", result);
		} catch (SQLException e) {
			res.put("status", e.getMessage());
		}
		response.getWriter().print(res);
	}
	
	private String[] getFields(Connection cn, String table) {
		ArrayList<String> fields = new ArrayList<String>();
		try {
			String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND TABLE_SCHEMA = 'hms'; ";
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1, table);
			ResultSet rs = st.executeQuery();
			while (rs.next()) fields.add(rs.getString(1));
			rs.close();
			st.close();
		} catch (SQLException e) {
			// pass
		}
		return fields.toArray(new String[fields.size()]);
	}
}
