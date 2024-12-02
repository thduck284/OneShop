package controllers.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import models.CartDetail;
import models.Product;
import models.Promotion;
import service.CartDetailService;
import service.CartService;
import service.ProductService;
import service.PromotionService;
import serviceImpl.CartDetailServiceImpl;
import serviceImpl.CartServiceImpl;
import serviceImpl.ProductServiceImpl;
import serviceImpl.PromotionServiceImpl;

@WebServlet(urlPatterns = {"/awaiting-payment"})
public class AwaitingPaymentController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartServiceImpl();
	private CartDetailService cartDetailService = new CartDetailServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private PromotionService promotionService = new PromotionServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/noLayout/payment.jsp").forward(request, response);
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
        	String id = "ORD" + UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        	String userId = request.getParameter("userId");
            String productId = request.getParameter("productId");
            
            Cart cart = cartService.getCurrentCartByUserId(userId);
			String cartId = cart.getCartId();
			
			List<Promotion> promotion = promotionService.getAllPromotion(userId);
			
			List<String> idList = new ArrayList<>();
	        idList.add(id);       
	        idList.add(userId);   
	        idList.add(cartId);
	        
	        session.removeAttribute("promotion");
			session.setAttribute("promotion", promotion);
	        session.removeAttribute("idList");
			session.setAttribute("idList", idList);
            
            if(productId != null) {
            	int quantity = Integer.parseInt(request.getParameter("quantity"));
            	 
    			if(userId == null || productId == null)
    			{
    				return;
    			}
    			
    			Product product = productService.getProductById(productId);
    			product.setQuantity(quantity);
    			
    			session.removeAttribute("lstCartDetails");
    	        session.removeAttribute("productOrder");
    			session.setAttribute("productOrder", product);
    			
            } else {
            	DefaultTableModel tableModel = null;
            	tableModel = (cartId == null) ? null : cartDetailService.getCartDetailsTable(cartId);
    			List<CartDetail> lstCartDetails = new ArrayList<>();;
    		    for (int i = 0; i < tableModel.getRowCount(); i++) {
    		        CartDetail cartDetail = new CartDetail();
    		        cartDetail.setCartId(cartId);
    		        cartDetail.setProductId((String) tableModel.getValueAt(i, 1));
    		        cartDetail.setProductName((String) tableModel.getValueAt(i, 2)); 
    		        cartDetail.setQuantity((Integer) tableModel.getValueAt(i, 4)); 
    		        cartDetail.setPrice((Integer) tableModel.getValueAt(i, 3)); 
    		        lstCartDetails.add(cartDetail);
    		    }
    		    
    		    session.removeAttribute("productOrder");
    			session.removeAttribute("lstCartDetails");
    			session.setAttribute("lstCartDetails", lstCartDetails);
            }
			
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session does not exist or has expired.");
		}
    }	
}
