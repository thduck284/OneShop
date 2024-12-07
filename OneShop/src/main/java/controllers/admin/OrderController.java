package controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Order;
import service.OrderService;
import serviceImpl.OrderServiceImpl;

@WebServlet(urlPatterns = {"/admin/order"})
public class OrderController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		List<Order> orders = orderService.getAllOrders();

		request.setAttribute("orders", orders);	
		request.getRequestDispatcher("/views/admin/order.jsp").forward(request, response);
	}
}