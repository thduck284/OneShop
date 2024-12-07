package controllers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cart;
import service.CartService;
import service.CategoryService;
import service.OrderService;
import service.ProductService;
import service.UserService;
import serviceImpl.CartServiceImpl;
import serviceImpl.CategoryServiceImpl;
import serviceImpl.OrderServiceImpl;
import serviceImpl.ProductServiceImpl;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin/search-customer", "/admin/search-vendor", "/admin/search-product",
		"/admin/search-category", "/admin/search-cart", "/admin/search-order"})
public class SearchController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();
	private UserService userService = new UserServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	private CartService cartService = new CartServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		String searchQuery = request.getParameter("searchQuery");
        String servletPath = request.getServletPath();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        List<?> results = null;

        if ("/admin/search-product".equals(servletPath)) {
            results = productService.searchProductsByKeyword(searchQuery);
        } else if ("/admin/search-customer".equals(servletPath)) {
            results = userService.searchUser(searchQuery, "customer");
        } else if ("/admin/search-vendor".equals(servletPath)) {
            results = userService.searchUser(searchQuery, "vendor");
        } else if ("/admin/search-category".equals(servletPath)) {
            results = categoryService.searchCategory(searchQuery);
        } else if ("/admin/search-cart".equals(servletPath)) {
        	List<Cart> cartList = cartService.searchCart(searchQuery);
        	List<Map<String, Object>> cartDataList = new ArrayList<>();
        	
        	for (Cart cart : cartList) {
                Map<String, Object> cartDataMap = new HashMap<>();
                cartDataMap.put("cartId", cart.getCartId());
                cartDataMap.put("userId", cart.getUserId());
                cartDataMap.put("fullName", cart.getFullName());
                cartDataMap.put("totalPrice", cart.getTotalPrice());
                cartDataMap.put("createdDate", cart.getFormattedCreatedDate());
                cartDataMap.put("status", cart.getStatus());
                cartDataList.add(cartDataMap);
            }
        	
            results = cartDataList;
        } else if ("/admin/search-order".equals(servletPath)) {
            results = orderService.searchOrder(searchQuery);
        }
        System.out.println(results);
        if (results != null) {
            String jsonResponse = new ObjectMapper().writeValueAsString(results);
            response.getWriter().write(jsonResponse);
        } else {
            response.getWriter().write("[]"); 
        }
	}
}
