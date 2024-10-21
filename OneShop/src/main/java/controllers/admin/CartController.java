package controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cart;
import service.CartService;
import serviceImpl.CartServiceImpl;

@WebServlet(urlPatterns = {"/admin/cart"})
public class CartController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		List<Cart> carts = cartService.getAllCarts();

		request.setAttribute("carts", carts);	
		request.getRequestDispatcher("/views/admin/cart.jsp").forward(request, response);
	}
}
