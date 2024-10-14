/*package controllers.admin;

import java.io.IOException;
import java.util.List;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Order;

@WebServlet(urlPatterns = {"/admin/order"})
public class OrderController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO = new OrderDAO();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		List<Order> orders = orderDAO.getAllProducts();

		request.setAttribute("orders", orders);	
		request.getRequestDispatcher("/views/admin/order.jsp").forward(request, response);
	}
}*/
