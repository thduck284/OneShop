package servlet;

import java.io.IOException;

import daoImpl.CategoryDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/delete-category"})
public class DeleteCategoryServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String categoryId = request.getParameter("categoryId");
		  
		categoryDAO.deleteCategory(categoryId); 
		request.setAttribute("message", "Xóa doanh mục sản phẩm thành công!");
		
		response.sendRedirect(request.getContextPath() + "/admin/category");
    }
}
