package controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin/customer", "/admin/vendor"})
public class UserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		String action = request.getServletPath();
		String role = action.equals("/admin/customer") ? "customer" : "vendor";
	    
		List<User> users = userService.getAllUserByRole(role);
		request.setAttribute("users", users);	
		
        if (action.equals("/admin/customer")) {
            request.getRequestDispatcher("/views/admin/customer.jsp").forward(request, response);
        } else if (action.equals("/admin/vendor")) {
            request.getRequestDispatcher("/views/admin/vendor.jsp").forward(request, response);
        }	 
	}
}
