package controllers.user;


import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cart;
import models.CartDetail;
import models.Product;
import models.User;
import service.CartDetailService;
import service.CartService;
import service.ProductService;
import service.UserService;
import serviceImpl.CartDetailServiceImpl;
import serviceImpl.CartServiceImpl;
import serviceImpl.ProductServiceImpl;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/user/add-to-cart"})
@MultipartConfig
public class AddCartController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserService  userService = new UserServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private CartService cartService = new CartServiceImpl();
	private CartDetailService cartDetailService = new CartDetailServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String userId = request.getParameter("userId");
		String productId = request.getParameter("productId");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		User user = new User();
		user = userService.getUserById(userId);
		
		Product product = new Product();
		product = productService.getProductById(productId);
		
		String id = "CART" + UUID.randomUUID().toString().replace("-", "").substring(0, 5);
		int totalCost = (cartService.getCartById(id) == null ? 0 : cartService.getCartById(id).getTotalPrice());
		int currentTotalCost = product.getPrice()*quantity + totalCost;
		int currentQuantity = (cartDetailService.getCartDetailById(id, productId) == null ? quantity : cartDetailService.getCartDetailById(id, productId).getQuantity() + quantity);
		
		Cart cart = new Cart(id, userId, user.getFullName(), currentTotalCost, null);
		cartService.addCart(cart);
		
		CartDetail cartDetail = new CartDetail(id, productId, product.getProductName(), currentQuantity, product.getPrice(), false);
		cartDetailService.addCartDetail(cartDetail);
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\": true, \"cartItemCount\": " + currentQuantity + "}");
    }
}
