package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CartDetailService;
import serviceImpl.CartDetailServiceImpl;

@WebServlet(urlPatterns = {"/admin/delete-cart-detail"})
public class DeleteCartDetailServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	CartDetailService cartDetailService = new CartDetailServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String cartId = request.getParameter("cartId");
		String productId = request.getParameter("productId");
		  
		cartDetailService.deleteCartDetail(cartId, productId); 
		request.setAttribute("message", "Xóa chi tiết giỏ hàng thành công!");
		
		response.sendRedirect(request.getContextPath() + "/admin/cart-detail");
    }
}
