package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProductService;
import serviceImpl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/admin/delete-product"})
public class DeleteProductServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String productId = request.getParameter("productId");
		  
		productService.deleteProduct(productId); 
		request.setAttribute("message", "Xóa sản phẩm thành công!");
		
		response.sendRedirect(request.getContextPath() + "/admin/product");
    }
}
