package servlet;

import java.io.IOException;

import daoImpl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/delete-customer", "/admin/delete-vendor"})
public class DeleteUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	UserDAOImpl userDAO = new UserDAOImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String action = request.getServletPath();
		
		String userId = request.getParameter("userId");
		  
		userDAO.deleteUser(userId); 
		request.setAttribute("message", "Xóa người dùng thành công!");
		
		if (action.equals("/admin/delete-customer")) {
            request.getRequestDispatcher("/views/admin/customer.jsp").forward(request, response);
        } else if (action.equals("/admin/delete-vendor")) {
            request.getRequestDispatcher("/views/admin/vendor.jsp").forward(request, response);
        }	
	}
}
