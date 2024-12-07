package controllers.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.CartDetail;
import service.CartDetailService;
import serviceImpl.CartDetailServiceImpl;

@WebServlet(urlPatterns = {"/admin/edit-cart-detail"})
@MultipartConfig
public class EditCartDetailController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private CartDetailService cartDetailService = new CartDetailServiceImpl();
	private CartDetail cartDetail = new CartDetail();
	private String cartId;
	private String productId;
	private Boolean status;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		cartId = request.getParameter("cartId");
		productId = request.getParameter("productId");
		status = Boolean.parseBoolean(request.getParameter("status"));
		
		cartDetail = cartDetailService.getCartDetailById(cartId, productId, status);
		
		request.setAttribute("cartDetail", cartDetail);
		request.getRequestDispatcher("/views/admin/editCartDetail.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		cartDetail.setCartId(cartId);
		cartDetail.setProductId(productId);
		cartDetail.setQuantity((int)(((request.getParameter("quantity") == null) ? cartDetail.getQuantity() : request.getParameter("quantity"))));
		cartDetail.setPrice((int)((request.getParameter("price") == null) ? cartDetail.getPrice() : request.getParameter("price")));
		
		cartDetailService.updateCartDetail(cartDetail); 
		response.sendRedirect(request.getContextPath() + "/admin/edit-cart-detail");
    }
}
