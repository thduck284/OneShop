package controllers.admin;

import java.io.IOException;
import java.util.List;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;

@WebServlet(urlPatterns = {"/admin/product"})
public class ProductController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO = new ProductDAO();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		List<Product> products = productDAO.getAllProducts();
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		request.setAttribute("products", products);	
		request.getRequestDispatcher("/views/admin/product.jsp").forward(request, response);
	}
	
	

}
