package controllers.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cart;
import service.CartService;
import serviceImpl.CartServiceImpl;

@WebServlet(urlPatterns = {"/admin/edit-cart"})
@MultipartConfig
public class EditCartController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartServiceImpl();
	private Cart cart = new Cart();
	private String cartId;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		cartId = request.getParameter("cartId");
		
		cart = cartService.getCartById(cartId);
		request.setAttribute("cart", cart);
		
		request.getRequestDispatcher("/views/admin/editCart.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		cart.setCartId(cartId);
		cart.setUserId((request.getParameter("userId") == null) ? cart.getUserId() : request.getParameter("userId"));
		cart.setFullName((request.getParameter("fullName") == null) ? cart.getFullName() : request.getParameter("fullName"));
		cart.setTotalPrice((int) ((request.getParameter("totalPrice") == null) ? cart.getTotalPrice() : Integer.parseInt(request.getParameter("totalPrice")))); 	
		cart.setCreatedDate(cart.getCreatedDate()); 
		
		cartService.updateCart(cart); 
		response.sendRedirect(request.getContextPath() + "/admin/cart");
    }
}
