package controllers.vendor;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Order;
import models.User;
import service.OrderService;
import serviceImpl.OrderServiceImpl;

@WebServlet(urlPatterns = {"/vendor/view-order"})
public class ViewOrderController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			User user = (User) httpRequest.getSession().getAttribute("vendorInfor");
			List<Order> listOrder = orderService.getAllOrdersByUserId(user.getUserId());

			session.setAttribute("listOrder", listOrder);
		}
		
		request.getRequestDispatcher("/views/vendor/viewOrder.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    }
}	