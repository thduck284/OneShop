package controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Category;
import service.CategoryService;
import serviceImpl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/admin/category"})
public class CategoryController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		List<Category> categories = categoryService.getAllCategories();
		
		request.setAttribute("categories", categories);	
		request.getRequestDispatcher("/views/admin/category.jsp").forward(request, response);
	}
}
