package manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import global.JDBC;

/**
 * Servlet implementation class SetRoomType
 */
@WebServlet("/SetRoomType")
public class SetRoomType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetRoomType() {
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
		String roomType = request.getParameter("roomtype");
		Integer capacity = Integer.valueOf(request.getParameter("capacity"));
		Integer surplus = Integer.valueOf(request.getParameter("surplus"));
		Integer price = Integer.valueOf(request.getParameter("price"));
		try {
			String sql = "INSERT INTO 客房类型 (类型, 容量, 余量, 价格) VALUES (?, ?, ?, ?);";
			Connection cn = JDBC.getConnection(getServletContext());
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1, roomType);
			st.setInt(2, capacity);
			st.setInt(3, surplus);
			st.setInt(4, price);
			st.execute();
			st.close();
            cn.close();
            res.put("status", 200);
		} catch (SQLException e) {
			res.put("status", e.getMessage());
		}
		response.getWriter().print(res);
	}

}
