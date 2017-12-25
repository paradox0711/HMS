package register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.JDBC;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Connection con = JDBC.getConnection(getServletContext());
		String username = request.getParameter("username");  
	     String idCard = request.getParameter("idCard");
	     String phone = request.getParameter("phone");
	     String password = request.getParameter("password");
	     String rpsw = request.getParameter("rpsw");
	     String sex = request.getParameter("sex");
	     int gender=0;
	     if(sex.equals("男")) gender=1;
	     	else gender=2;
	     if(username==null||username.trim().isEmpty()){  
	            request.setAttribute("msg", "帐号不能为空");  
	            request.getRequestDispatcher("/register.jsp").forward(request, response);  
	            return;  
	        }
	       if(idCard==null||idCard.trim().isEmpty()){  
	            request.setAttribute("msg", "身份证号不能为空");  
	            request.getRequestDispatcher("/register.jsp").forward(request, response);  
	            return;  
	        }
	       if(phone==null||phone.trim().isEmpty()){  
	            request.setAttribute("msg", "手机号不能为空");  
	            request.getRequestDispatcher("/register.jsp").forward(request, response);  
	            return;  
	        }
	      if(password==null||password.trim().isEmpty()){  
	            request.setAttribute("msg", "密码不能为空");  
	            request.getRequestDispatcher("/register.jsp").forward(request, response);  
	            return;  
	        }  
	      if(!password.equals(rpsw)){  
	            request.setAttribute("msg", "两次输入的密码不同");  
	            request.getRequestDispatcher("/register.jsp").forward(request, response);  
	            return;  
	        } 
	      Users u=new Users();
	      	u.setIdCard(idCard);
	      	u.setUsername(username);
	      	u.setPassword(password);
	      	u.setPhone(phone);
	      	u.setSex(gender);;  
	      try { 	
		    	PreparedStatement pstmt=con.prepareStatement("INSERT INTO 用户  VALUES(?,?,?,?,?,?)");
		      	pstmt.setString(1, u.getIdCard());  
	            pstmt.setString(2, u.getUsername()); 
	            pstmt.setString(3, u.getPassword());
	            pstmt.setInt(4, u.getSex());
	            pstmt.setInt(5, 0);
	            pstmt.setString(6, u.getPhone());
	            pstmt.executeUpdate();     
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		    }   
	      request.setAttribute("msg", "恭喜："+username+"，注册成功");  
	        request.getRequestDispatcher("/register.jsp").forward(request, response);  
	}
	

}
