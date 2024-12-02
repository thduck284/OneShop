package controllers.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;
import service.ProductService;
import serviceImpl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/user/search-product"})
public class SearchProductController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword"); 
        List<Product> lstProduct = productService.searchProductsByKeyword(keyword);

        request.setAttribute("searchProduct", lstProduct);  
        request.getRequestDispatcher("/views/user/searchProduct.jsp").forward(request, response);
	}
}
