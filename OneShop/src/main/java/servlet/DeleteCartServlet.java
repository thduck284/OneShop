package servlet;

import java.io.IOException;

import daoImpl.CartDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/delete-cart"})
public class DeleteCartServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	CartDAOImpl cartDAO = new CartDAOImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String cartId = request.getParameter("cartId");
		  
		cartDAO.deleteCart(cartId); 
		request.setAttribute("message", "Xóa giỏ hàng thành công!");
		
		response.sendRedirect(request.getContextPath() + "/admin/cart");
    }
}
