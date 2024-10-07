package controllers.admin;

import java.io.IOException;
import java.sql.SQLException;

import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/add-category"})
@MultipartConfig
public class AddCategoryController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	CategoryDAO categoryDAO = new CategoryDAO();
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		request.getRequestDispatcher("/views/admin/addCategory.jsp").forward(request, response);	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String categoryId = request.getParameter("categoryId"); 
		String categoryName = request.getParameter("categoryName"); 
		String description = request.getParameter("description"); 
		  
		try { 
			
			categoryDAO.addCategory(categoryId, categoryName, description); 
			request.setAttribute("message", "Thêm doanh mục sản phẩm thành công!");
		} 
		catch (SQLException e) {
			e.printStackTrace(); 
			request.setAttribute("message", "Lỗi khi thêm doanh mục sản phẩm!");
			return;
		}
		
		request.getRequestDispatcher("/views/admin/addCategory.jsp").forward(request, response);
    }
}
