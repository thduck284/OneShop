package controllers.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;
import models.User;
import models.WishList;
import service.ProductService;
import service.UserService;
import service.WishListService;
import serviceImpl.ProductServiceImpl;
import serviceImpl.UserServiceImpl;
import serviceImpl.WishListServiceImpl;

@WebServlet(urlPatterns = {"/wish-list"})
public class WishListController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private WishListService wishListService = new WishListServiceImpl();
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
    	String userId = request.getParameter("userId");
        String productId = request.getParameter("productId");
        
        if(userId == null || productId == null)
        {
        	return;
        }
        
        if(action.equals("add")) 
        {
        	UserService userService = new UserServiceImpl();
        	User user = new User();
        	user = userService.getUserById(userId);
        	
        	ProductService productService = new ProductServiceImpl();
        	Product product = new Product();
            product = productService.getProductById(productId);
            
            WishList wishList = new WishList();
            wishList.setUserId(userId);
            wishList.setProductId(productId);
            wishList.setProductName(product.getProductName());
            wishList.setUserName(user.getUserName());
            
        	wishListService.addWishList(wishList);
        } else {
        	wishListService.deleteWishList(userId, productId);
        }
        
    }
}
