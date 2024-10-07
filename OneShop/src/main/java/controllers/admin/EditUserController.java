package controllers.admin;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

@WebServlet(urlPatterns = {"/admin/edit-customer", "/admin/edit-manager"})
@MultipartConfig
public class EditUserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();
	User user = new User();
	String userId;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		userId = request.getParameter("userId");
		try {
			user = userDAO.getUserById(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(userId);

		request.setAttribute("user", user);
		
		if (action.equals("/admin/edit-customer")) {
            request.getRequestDispatcher("/views/admin/editCustomer.jsp").forward(request, response);
        } else if (action.equals("/admin/add-manager")) {
            request.getRequestDispatcher("/views/admin/editManager.jsp").forward(request, response);
        }	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String action = request.getServletPath();
		
		String fullName = (request.getParameter("fullName") == null) ? user.getFullName() : request.getParameter("fullName");
		String phone = (request.getParameter("phone") == null) ? user.getPhoneNumber() : request.getParameter("phone"); 	
		String address = (request.getParameter("address") == null) ? user.getAddress() : request.getParameter("address"); 
		String email = (request.getParameter("email") == null) ? user.getEmail() : request.getParameter("email"); 
		String userName = (request.getParameter("userName") == null) ? user.getAccountName() : request.getParameter("userName"); 
		String password = (request.getParameter("password") == null) ? user.getPassword() : request.getParameter("password");
		String role = action.equals("/admin/edit-customer") ? "customer" : "manager";
		  
		try { 
			
			userDAO.updateUserById(userId, fullName, email, phone, address, userName, password, role); 
			
			if (action.equals("/admin/edit-customer")) {
	            request.getRequestDispatcher("/views/admin/customer.jsp").forward(request, response);
	        } else if (action.equals("/admin/add-manager")) {
	            request.getRequestDispatcher("/views/admin/manager.jsp").forward(request, response);
	        }	 
			
		} 
		catch (SQLException e) {
			e.printStackTrace(); 
			return;
		}
    }
}
