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

@WebServlet(urlPatterns = {"/admin/edit-category"})
@MultipartConfig
public class EditCategoryController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	CategoryService categoryService = new CategoryServiceImpl();
	Category category = new Category();
	String categoryId;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		categoryId = request.getParameter("categoryId");
		
		category = categoryService.getCategoryById(categoryId);
		request.setAttribute("category", category);
		
		request.getRequestDispatcher("/views/admin/editCategory.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		category.setCategoryId(categoryId);
		category.setCategoryName((request.getParameter("categoryName") == null) ? category.getCategoryName() : request.getParameter("categoryName"));
		category.setDescription((request.getParameter("description") == null) ? category.getDescription() : request.getParameter("description")); 
		
		categoryService.updateCategory(category); 
		response.sendRedirect(request.getContextPath() + "/admin/category");
    }
}
