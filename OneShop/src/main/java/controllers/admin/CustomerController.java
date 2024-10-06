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

@WebServlet(urlPatterns = {"/admin/customer"})
public class CustomerController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserDAO productDAO = new UserDAO();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		List<User> customers = productDAO.getAllUserByRole("customer");

		request.setAttribute("customers", customers);	
		request.getRequestDispatcher("/views/admin/customer.jsp").forward(request, response);
	}
}
