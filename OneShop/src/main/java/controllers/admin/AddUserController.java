package controllers.admin;

import java.io.IOException;
import java.sql.SQLException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/add-customer", "/admin/add-manager"})
@MultipartConfig
public class AddUserController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAO();

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		String action = request.getServletPath();
        if (action.equals("/admin/add-customer")) {
            request.getRequestDispatcher("/views/admin/addCustomer.jsp").forward(request, response);
        } else if (action.equals("/admin/add-manager")) {
            request.getRequestDispatcher("/views/admin/addManager.jsp").forward(request, response);
        }	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		  String action = request.getServletPath();
		  
		  String fullName = request.getParameter("fullName"); 
		  String phone = request.getParameter("phone"); 
		  String address = request.getParameter("address"); 
		  String email = request.getParameter("email"); 
		  String userName = request.getParameter("userName"); 
		  String password = request.getParameter("password");
		  String role = action.equals("/admin/add-customer") ? "customer" : "manager";
		  
		  try {
			  
			  userDAO.addUser(fullName, email, phone, address, userName, password, role); 
			  request.setAttribute("message", "Thêm người dùng thành công!"); 
		  } 
		  catch (SQLException e) { 
			  e.printStackTrace();
			  request.setAttribute("message", "Lỗi khi thêm người dùng!"); 
			  return; 
		  }
		  
	        if (action.equals("/admin/add-customer")) {
	            request.getRequestDispatcher("/views/admin/addCustomer.jsp").forward(request, response);
	        } else if (action.equals("/admin/add-manager")) {
	            request.getRequestDispatcher("/views/admin/addManager.jsp").forward(request, response);
	        }
    }
}
