package controllers.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Product;
import service.ProductService;
import serviceImpl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/user/home"})
public class UserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		List<Product> products = productService.getAllProducts();
	    
		request.setAttribute("products", products);
		request.getRequestDispatcher("/views/user/home.jsp").forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); 
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
