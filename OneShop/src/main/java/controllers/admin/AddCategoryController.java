package controllers.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Category;
import service.CategoryService;
import serviceImpl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/admin/add-category"})
@MultipartConfig
public class AddCategoryController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	CategoryService categoryService = new CategoryServiceImpl();
	Category category = new Category();
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		request.getRequestDispatcher("/views/admin/addCategory.jsp").forward(request, response);	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		category.setCategoryId(request.getParameter("categoryId")); 
		category.setCategoryName(request.getParameter("categoryName")); 
		category.setDescription(request.getParameter("description")); 
		  
		categoryService.addCategory(category); 
		request.setAttribute("message", "Thêm doanh mục sản phẩm thành công!");
		
		request.getRequestDispatcher("/views/admin/addCategory.jsp").forward(request, response);
    }
}
