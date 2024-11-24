package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Cart;
import models.CartDetail;
import service.CartDetailService;
import service.CartService;
import serviceImpl.CartDetailServiceImpl;
import serviceImpl.CartServiceImpl;

@WebServlet(urlPatterns = {"/view-cart"})
public class ViewCartController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartServiceImpl();
	private CartDetailService cartDetailService = new CartDetailServiceImpl();
	private Cart cart = new Cart();
	private List<CartDetail> cartDetail;
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			String userId = request.getParameter("userId");
			
			if(userId == null)
			{
				return;
			}

			cart = cartService.getCartByUserId(userId);
			cartDetail = cartDetailService.getCartDetailByCartId(userId);
			
			session.removeAttribute("cart");
			session.setAttribute("cart", cart);
			session.removeAttribute("cartDetail");
			session.setAttribute("cartDetail", cartDetail);
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session does not exist or has expired.");
		}
    }
}
