package controllers.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin/edit-customer", "/admin/edit-vendor", "/vendor/edit-vendor"})
@MultipartConfig
public class EditUserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	User user = new User();
	String userId;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		userId = request.getParameter("userId");
		user = userService.getUserById(userId);

		request.setAttribute("user", user);
		
		if (action.equals("/admin/edit-customer")) {
            request.getRequestDispatcher("/views/admin/editCustomer.jsp").forward(request, response);
        } else if (action.equals("/admin/edit-vendor")) {
            request.getRequestDispatcher("/views/admin/editVendor.jsp").forward(request, response);
        }	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String action = request.getServletPath();
		
		user.setUserId(userId);
		user.setFullName((request.getParameter("fullName") == null) ? user.getFullName() : request.getParameter("fullName"));
		user.setPhoneNumber((request.getParameter("phone") == null) ? user.getPhoneNumber() : request.getParameter("phone")); 	
		user.setAddress((request.getParameter("address") == null) ? user.getAddress() : request.getParameter("address")); 
		user.setEmail((request.getParameter("email") == null) ? user.getEmail() : request.getParameter("email")); 
		user.setUserName((request.getParameter("userName") == null) ? user.getUserName() : request.getParameter("userName")); 
		user.setPassword((request.getParameter("password") == null) ? user.getPassword() : request.getParameter("password"));
		user.setRole(action.equals("/admin/edit-customer") ? "customer" : "vendor");
		  
		userService.updateUser(user); 
		
		if (action.equals("/admin/edit-customer")) {
		    request.getRequestDispatcher("/views/admin/customer.jsp").forward(request, response);
		} else if (action.equals("/admin/edit-vendor")) {
		    request.getRequestDispatcher("/views/admin/vendor.jsp").forward(request, response);
		}
    }
}
