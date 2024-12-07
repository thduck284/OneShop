package controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Order;
import service.CartService;
import service.OrderService;
import service.ProductService;
import service.UserService;
import serviceImpl.CartServiceImpl;
import serviceImpl.OrderServiceImpl;
import serviceImpl.ProductServiceImpl;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin/dashboard"})
public class DashboardController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private CartService cartService = new CartServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		int vendorCount = userService.countUsersByRole("vendor");
		int customerCount = userService.countUsersByRole("customer");
		int productCount = productService.countProducts();
		int cartCount = cartService.countCarts();
		int orderCount = orderService.countOrders();
		List<Order> listOrder = orderService.getAllOrders();
		int revenue = 0;
		
		for(Order order : listOrder) {
			revenue += order.getTotalCost();
		}
		
		request.setAttribute("vendorCount", vendorCount);
		request.setAttribute("customerCount", customerCount);
		request.setAttribute("productCount", productCount);
		request.setAttribute("cartCount", cartCount);
		request.setAttribute("orderCount", orderCount);
		request.setAttribute("revenue", revenue);
		request.getRequestDispatcher("/views/admin/dashboard.jsp").forward(request, response);	 
	}
}
