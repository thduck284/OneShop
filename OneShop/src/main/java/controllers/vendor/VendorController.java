package controllers.vendor;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Order;
import models.User;
import service.OrderService;
import service.UserService;
import serviceImpl.OrderServiceImpl;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/vendor/home"})
public class VendorController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderServiceImpl();
	private UserService userService = new UserServiceImpl();	
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = request.getSession(false);
		User user = new User();
		
		if(session != null) {
			user = (User) httpRequest.getSession().getAttribute("vendorInfor");
		}
		
		List<Order> listOrder = orderService.getAllOrdersByUserId(user.getUserId());
		Set<String> customerSet = new HashSet<>();
		Set<String> productSet = new HashSet<>();
		int orderCount = 0;
		
		for (Order order : listOrder) {
            orderCount++;  
            customerSet.add(order.getUserId());  
        }
		int customerCount = customerSet.size();
		List<Order> top3Orders = getTop3Orders(listOrder);
		DefaultTableModel tableOrder = orderService.getAllDetailOrdersByUserId(user.getUserId());
		
		for (int row = 0; row < tableOrder.getRowCount(); row++) {
            String productId = String.valueOf(tableOrder.getValueAt(row, 2)); 
            productSet.add(productId);  
        }
		
		int productCount = productSet.size();
		int currentMonth = LocalDate.now().getMonthValue();
		String currentMonthStr = String.valueOf(currentMonth);
		int venue = orderService.getVenueByDayMonthYear(user.getUserId(), currentMonthStr, "month");
		
		session.setAttribute("productCount", productCount);
		session.setAttribute("venue", venue);
		session.setAttribute("customerCount", customerCount);
		session.setAttribute("orderCount", orderCount);
		session.setAttribute("top3Orders", top3Orders);
		request.getRequestDispatcher("/views/vendor/home.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); 
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }
	
	private List<Order> getTop3Orders(List<Order> orders) {

        orders.sort((o1, o2) -> Integer.compare(o2.getTotalCost(), o1.getTotalCost()));
        
        List<Order> top3Orders = new ArrayList<>();
        for (int i = 0; i < 3 && i < orders.size(); i++) {
            top3Orders.add(orders.get(i));
            User user = userService.getUserById(orders.get(i).getUserId());
            orders.get(i).setUserId(user.getFullName());
        }
        return top3Orders;
    }
}