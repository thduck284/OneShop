package controllers.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Product;
import models.User;
import service.ProductService;
import service.UserService;
import service.WishListService;
import serviceImpl.ProductServiceImpl;
import serviceImpl.UserServiceImpl;
import serviceImpl.WishListServiceImpl;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private WishListService wishListService = new WishListServiceImpl();
	private ProductService productService = new ProductServiceImpl();

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		HttpSession session = request.getSession(false);
		
    	String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValidUser = false;

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            isValidUser = userService.validUser(username, password);
        }

        if (isValidUser) {
        	User user = new User();
        	user = userService.getInforUser(username);
        	
        	if ("customer".equals(user.getRole())) {
        		List<String> productIdList = wishListService.getAllWishListByUserId(user.getUserId());
    			List<Product> products = new ArrayList<>();

    			for (String productId : productIdList) {
    				Product product = productService.getProductById(productId);
    				if (product != null) {
    					products.add(product);
    				}
    			}

    			session.setAttribute("wishList", products);
        		request.getSession().setAttribute("userInfor", user);
                response.sendRedirect(request.getContextPath() + "/user/home");
            } else if ("vendor".equals(user.getRole())) {
            	request.getSession().setAttribute("vendorInfor", user);
                response.sendRedirect(request.getContextPath() + "/vendor/home");
            } else {
            	request.getSession().setAttribute("adminInfor", user);
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            }
        		
     
        } else {
        	
        	request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không chính xác.");
        	request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
        }
    }
}
