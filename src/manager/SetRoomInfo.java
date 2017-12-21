package manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class setRoomInfo
 */
@WebServlet("/SetRoomInfo")
public class SetRoomInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetRoomInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		JSONObject res = new JSONObject();
		Connection cn = JDBC.getConnector();
		String sql = "SELECT roomtype FROM roomtypes;";
		try {
			PreparedStatement st = cn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			ArrayList<String> roomTypes = new ArrayList<String>();
			while (rs.next()) {
				roomTypes.add(rs.getString("roomtype"));
			}
            rs.close();
			st.close();
            cn.close();
    		res.put("roomtypes", roomTypes);
		} catch (SQLException e) {
			res.put("error", e.getMessage());
		}

        response.getWriter().print(res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
