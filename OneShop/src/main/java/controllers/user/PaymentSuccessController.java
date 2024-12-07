package controllers.user;

import java.io.IOException;
import java.util.List;

import Jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Cart;
import models.CartDetail;
import models.Order;
import models.Product;
import models.Promotion;
import models.User;
import service.CartDetailService;
import service.CartService;
import service.OrderService;
import service.PromotionService;
import serviceImpl.CartDetailServiceImpl;
import serviceImpl.CartServiceImpl;
import serviceImpl.OrderServiceImpl;
import serviceImpl.PromotionServiceImpl;

@WebServlet(urlPatterns = {"/payment-success"})
public class PaymentSuccessController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartServiceImpl();
	private CartDetailService cartDetailService = new CartDetailServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	private PromotionService promotionService = new PromotionServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/user/paymentSuccess.jsp").forward(request, response);
	}
	
	@Override
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
			List<String> idList = (List<String>) session.getAttribute("idList");
			String paymentMethod = request.getParameter("paymentMethod");
			int totalCost = Integer.parseInt(request.getParameter("totalCost"));
			String promotionId = request.getParameter("promotionId");
			Product product = (Product) session.getAttribute("productOrder");
			
			if(product == null) {
				List<CartDetail> lstCartDetails = (List<CartDetail>) session.getAttribute("lstCartDetails");
				Cart cart = cartService.getCurrentCartByUserId(user.getUserId());
				cart.setStatus(true);
				cartService.updateCart(cart);
				
				if (lstCartDetails != null) {
			        for (CartDetail cartDetail : lstCartDetails) {
			            cartDetail.setStatus(true);
			            cartDetailService.updateCartDetail(cartDetail);
			        }
				}
				session.removeAttribute("lstCartDetails");
			} else {
				Cart cart = cartService.getCurrentCartByUserId(user.getUserId());
				CartDetail cartDetail = cartDetailService.getCartDetailById(cart.getCartId(), product.getProductId(), false);
				
				cartDetail.setStatus(true);
				cartDetailService.updateCartDetail(cartDetail);
				session.removeAttribute("productOrder");
				
				cart.setStatus(true);
				cartService.updateCart(cart);
			}
			
			boolean status = false;
			if(paymentMethod != "")
			{
				status = true;
			} else {
				paymentMethod = "Thanh toán khi giao hàng";
			}
			
			Promotion promotion = new Promotion();
			promotion.setUserId(user.getUserId());
			promotion.setPrice(totalCost);
			promotion.setStatus(false);
			promotionService.addPromotion(promotion);
			
			promotion = promotionService.getPromotionById(promotionId);
			if(promotion != null) {
				promotion.setStatus(true);
				promotionService.updatePromotion(promotion);
				totalCost = totalCost * (100 - promotion.getPrice()) / 100;
			}
			
			Order order = new Order(idList.get(0), idList.get(1), idList.get(2), promotionId, null, false, paymentMethod, status, null, totalCost);
			orderService.addOrder(order);
			
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session does not exist or has expired.");
		}
    }	
}
