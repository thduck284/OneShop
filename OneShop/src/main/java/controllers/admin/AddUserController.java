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

@WebServlet(urlPatterns = {"/admin/add-customer", "/admin/add-vendor"})
@MultipartConfig
public class AddUserController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private User user = new User();

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		String action = request.getServletPath();
        if (action.equals("/admin/add-customer")) {
            request.getRequestDispatcher("/views/admin/addCustomer.jsp").forward(request, response);
        } else if (action.equals("/admin/add-vendor")) {
            request.getRequestDispatcher("/views/admin/addVendor.jsp").forward(request, response);
        }	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		  String action = request.getServletPath();
		  
		  user.setFullName(request.getParameter("fullName")); 
		  user.setPhoneNumber(request.getParameter("phone")); 
		  user.setAddress(request.getParameter("address")); 
		  user.setEmail(request.getParameter("email")); 
		  user.setUserName(request.getParameter("userName")); 
		  user.setPassword(request.getParameter("password"));
		  user.setRole(action.equals("/admin/add-customer") ? "customer" : "vendor");
		  
		  userService.addUser(user); 
		  request.setAttribute("message", "Thêm người dùng thành công!");
		  
	        if (action.equals("/admin/add-customer")) {
	            request.getRequestDispatcher("/views/admin/addCustomer.jsp").forward(request, response);
	        } else if (action.equals("/admin/add-vendor")) {
	            request.getRequestDispatcher("/views/admin/addVendor.jsp").forward(request, response);
	        }
    }
}
