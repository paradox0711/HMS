package manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import global.JDBC;

/**
 * Servlet implementation class SetAccount
 */
@WebServlet("/SetAccount")
public class SetAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pass
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		JSONObject res = new JSONObject();
		res.put("status", "�ڲ�����");
		String[] actions = {"addstaff", "removestaff", "removeuser"};
		String action = request.getParameter("action");
		Connection cn = JDBC.getConnection(getServletContext());
		if (action.equals(actions[0])) {
			String staffName = request.getParameter("staffname");
			String role = request.getParameter("role");
			String staffid = JDBC.getStaffid(getServletContext(), role);
			if (staffid != null) {
				try {
					String sql = "INSERT INTO Ա�� (Ա����, ����, ְλ, ����) VALUES(?, ?, ?, ?); ";
					PreparedStatement st = cn.prepareStatement(sql);
					st.setString(1, staffid);
					st.setString(2, staffName);
					st.setString(3, role);
					st.setString(4, staffid);
					st.execute();
					st.close();
					cn.close();
					res.put("staffid", staffid);
				} catch (SQLException e) {
					res.put("status", e.getMessage());
				}
			}
		}
		
		if (action.equals(actions[1])) {
			String staffid = request.getParameter("staffid");
			try {
				String sql = "DELETE FROM Ա�� WHERE Ա���� = ?; ";
				PreparedStatement st = cn.prepareStatement(sql);
				st.setString(1, staffid);
				st.execute();
				st.close();
				cn.close();
				res.put("staffid", staffid);
			} catch (SQLException e) {
				res.put("status", e.getMessage());
			}
		}
		
		if (action.equals(actions[2])) {
			String idcard = request.getParameter("idcard");
			try {
				String sql = "DELETE FROM �û� WHERE ���֤�� = ?; ";
				PreparedStatement st = cn.prepareStatement(sql);
				st.setString(1, idcard);
				st.execute();
				st.close();
				cn.close();
				res.put("idcard", idcard);
			} catch (SQLException e) {
				res.put("status", e.getMessage());
			}
		}

		response.getWriter().print(res);
	}

}
