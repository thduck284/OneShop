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
import service.CartDetailService;
import serviceImpl.CartDetailServiceImpl;

@WebServlet(urlPatterns = {"/view-order"})
public class ViewOrderController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private CartDetailService cartDetailService = new CartDetailServiceImpl();
	
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
			String userId = request.getParameter("userId");
			DefaultTableModel tbOrder = cartDetailService.getAllCartDetailByUserId(userId);
			
			session.removeAttribute("tbOrder");
			session.setAttribute("tbOrder", tbOrder);
			
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session does not exist or has expired.");
		}
    }
}
