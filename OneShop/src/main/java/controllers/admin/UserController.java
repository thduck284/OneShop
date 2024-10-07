package controllers.admin;

import java.io.IOException;
import java.util.List;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

@WebServlet(urlPatterns = {"/admin/customer", "/admin/manager"})
public class UserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAO();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		String action = request.getServletPath();
		String role = action.equals("/admin/customer") ? "customer" : "manager";
	    
		List<User> users = userDAO.getAllUserByRole(role);
		request.setAttribute("users", users);	
		
        if (action.equals("/admin/customer")) {
            request.getRequestDispatcher("/views/admin/customer.jsp").forward(request, response);
        } else if (action.equals("/admin/manager")) {
            request.getRequestDispatcher("/views/admin/manager.jsp").forward(request, response);
        }	 
	}
}
