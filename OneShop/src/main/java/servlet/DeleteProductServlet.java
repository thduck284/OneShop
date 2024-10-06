package servlet;

import java.io.IOException;
import java.sql.SQLException;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/delete-product"})
public class DeleteProductServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ProductDAO productDAO = new ProductDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String productId = request.getParameter("productId");
		  
		try { 
			
			productDAO.deleteProductById(productId); 
			request.setAttribute("message", "Xóa sản phẩm thành công!");
		} 
		catch (SQLException e) {
			e.printStackTrace(); 
			request.setAttribute("message", "Lỗi khi Xóa sản phẩm!");
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/admin/product");
    }
}
