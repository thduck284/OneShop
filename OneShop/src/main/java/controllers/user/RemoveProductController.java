package controllers.user;

import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import Jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Cart;
import models.User;
import service.CartDetailService;
import service.CartService;
import serviceImpl.CartDetailServiceImpl;
import serviceImpl.CartServiceImpl;

@WebServlet(urlPatterns = {"/remove-product"})
public class RemoveProductController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartServiceImpl();
	private CartDetailService cartDetailService = new CartDetailServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		HttpSession session = request.getSession(false);
		String authHeader = request.getHeader("Authorization"); 
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token không hợp lệ hoặc không được cung cấp.");
            return;
        }
        
        String token = authHeader.substring(7); 
        Claims claims;
        try {
            claims = JwtUtil.validateToken(token); 
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token không hợp lệ hoặc hết hạn.");
            return;
        }
        
        if (session != null) {
        	User user = (User) session.getAttribute("userInfor");
			String productId = request.getParameter("productId");
			
			if(user.getUserId() == null || productId == null)
			{
				return;
			}

			Cart cart = cartService.getCurrentCartByUserId(user.getUserId());
			if(cart != null) {
				cartDetailService.deleteCartDetail(cart.getCartId(), productId);
				
				DefaultTableModel cartTable = null;
				if(cart != null) {
					cartTable = cartDetailService.getCartDetailsTable(cart.getCartId());
				} 
				
				session.removeAttribute("cartTable");
				session.setAttribute("cartTable", cartTable);
			}
			
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session does not exist or has expired.");
		}
    }
}
