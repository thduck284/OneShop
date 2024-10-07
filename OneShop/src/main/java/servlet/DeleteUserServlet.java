package servlet;

import java.io.IOException;
import java.sql.SQLException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/delete-customer", "/admin/delete-manager"})
public class DeleteUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String action = request.getServletPath();
		
		String userId = request.getParameter("userId");
		  
		try { 
			
			userDAO.deleteUserById(userId); 
			request.setAttribute("message", "Xóa người dùng thành công!");
		} 
		catch (SQLException e) {
			e.printStackTrace(); 
			request.setAttribute("message", "Lỗi khi Xóa người dùng!");
			return;
		}
		
		if (action.equals("/admin/customer")) {
            request.getRequestDispatcher("/views/admin/customer.jsp").forward(request, response);
        } else if (action.equals("/admin/manager")) {
            request.getRequestDispatcher("/views/admin/manager.jsp").forward(request, response);
        }	
	}
}
