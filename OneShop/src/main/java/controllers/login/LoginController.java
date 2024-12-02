package controllers.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Jwt.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Cart;
import models.Product;
import models.User;
import service.CartDetailService;
import service.CartService;
import service.ProductService;
import service.UserService;
import service.WishListService;
import serviceImpl.CartDetailServiceImpl;
import serviceImpl.CartServiceImpl;
import serviceImpl.ProductServiceImpl;
import serviceImpl.UserServiceImpl;
import serviceImpl.WishListServiceImpl;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private WishListService wishListService = new WishListServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private CartService cartService = new CartServiceImpl();
	private CartDetailService cartDetailService = new CartDetailServiceImpl();

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
        		request.getSession().setAttribute("userInfor", user);
            } else if ("vendor".equals(user.getRole())) {
            	request.getSession().setAttribute("vendorInfor", user);
            } else {
            	request.getSession().setAttribute("adminInfor", user);
            }
        	
        	String token = JwtUtil.generateToken(user);
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write("{\"token\": \"" + token + "\", \"role\": \"" + user.getRole() + "\"}");
	        response.setStatus(HttpServletResponse.SC_OK);
     
        } else {
        	response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"Tên đăng nhập hoặc mật khẩu không chính xác.\"}");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
