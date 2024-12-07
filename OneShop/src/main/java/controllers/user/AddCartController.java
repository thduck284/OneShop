package controllers.user;


import java.io.IOException;
import java.util.List;
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
		
		Cart currentCart = cartService.getCurrentCartByUserId(userId);
		
		if(currentCart == null)
		{
			String id = "CART" + UUID.randomUUID().toString().replace("-", "").substring(0, 5);
			int totalCost = (cartService.getCartById(id) == null ? 0 : cartService.getCartById(id).getTotalPrice());
			int currentTotalCost = product.getPrice()*quantity + totalCost;
			int currentQuantity = (cartDetailService.getCartDetailById(id, productId, false) == null ? quantity : cartDetailService.getCartDetailById(id, productId, false).getQuantity() + quantity);	
			
			Cart cart = new Cart(id, userId, user.getFullName(), currentTotalCost, null, false);
			cartService.addCart(cart);
			
			CartDetail cartDetail = new CartDetail(id, productId, product.getProductName(), currentQuantity, product.getPrice(), false);
			cartDetailService.addCartDetail(cartDetail);
		} else {
			currentCart.setTotalPrice(currentCart.getTotalPrice() + quantity * product.getPrice());
			cartService.updateCart(currentCart);
			
			List<CartDetail> cartDetails = cartDetailService.getCartDetailByCartId(currentCart.getCartId());	
			
			boolean flag = false;

            for (CartDetail detail : cartDetails) {
                if (detail.getProductId().equals(productId)) {
                	int currentQuantity;
                	if(detail.getStatus() == false) {
                		currentQuantity = detail.getQuantity() + quantity;
                		cartDetailService.updateCartDetail(new CartDetail(currentCart.getCartId(), productId, product.getProductName(), currentQuantity, product.getPrice(), false));
                		flag = true;
                	} 
                }
            }
            
            if(!flag) {
        		cartDetailService.addCartDetail(new CartDetail(currentCart.getCartId(), productId, product.getProductName(), quantity, product.getPrice(), false));
            }
		}
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\": true}");
    }
}
