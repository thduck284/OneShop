package controllers.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import Jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Product;
import service.ProductService;
import service.WishListService;
import serviceImpl.ProductServiceImpl;
import serviceImpl.WishListServiceImpl;

@WebServlet(urlPatterns = {"/view-wish-list"})
public class ViewWishListController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private WishListService wishListService = new WishListServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	
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
			if(userId == null)
			{
				return;
			}

			List<String> productIdList = wishListService.getAllWishListByUserId(userId);
			List<Product> products = new ArrayList<>();

			for (String productId : productIdList) {
				Product product = productService.getProductById(productId);
				if (product != null) {
					products.add(product);
				}
			}

			session.removeAttribute("wishList");
			session.setAttribute("wishList", products);
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session does not exist or has expired.");
		}
    }
}
