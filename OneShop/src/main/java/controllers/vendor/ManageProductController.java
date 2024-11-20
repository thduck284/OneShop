package controllers.vendor;

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

@WebServlet(urlPatterns = {"/vendor/manage-product", "/vendor/delete-product"})
public class ManageProductController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();
	private List<Product> products;
	private String userId;
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		userId = request.getParameter("userId");
		
		products = productService.getAllProductsByUserId(userId);

		request.setAttribute("products", products);
		request.getRequestDispatcher("/views/vendor/manageProduct.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String productId = request.getParameter("productId");
		System.out.println(productId);
		productService.deleteProduct(productId); 
		request.setAttribute("message", "Xóa cửa hàng cửa hàng thành công!");
		products = productService.getAllProductsByUserId(userId);
		request.setAttribute("products", products);	
		request.getRequestDispatcher("/views/vendor/manageProduct.jsp").forward(request, response);
    }
}
