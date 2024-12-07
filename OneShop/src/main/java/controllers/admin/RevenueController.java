package controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import service.OrderService;
import service.UserService;
import serviceImpl.OrderServiceImpl;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin/revenue"})
public class RevenueController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private OrderService orderService = new OrderServiceImpl();	
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		List<User> listVendor = userService.getAllUserByRole("vendor");
		
		request.getSession().setAttribute("listVendor", listVendor);
		request.getRequestDispatcher("/views/admin/revenue.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String vendorId = request.getParameter("vendorIdSelected");
		
		String timePeriod = request.getParameter("timePeriod");
		String profitPercentage = request.getParameter("profitPercentage");
		String time;
		
		if ("day".equals(timePeriod)) {
	        time = request.getParameter("dayDate");
	    } else if ("month".equals(timePeriod)) {
	        time = request.getParameter("monthSelect");
	    } else if ("quarter".equals(timePeriod)) {
	        time = request.getParameter("quarterSelect");
	    } else {
	        time = request.getParameter("yearInput");
	    }
		
		int profitPercentageInt = 0; 
		profitPercentageInt = Integer.parseInt(profitPercentage);
		
		int totalCost = orderService.getVenueByDayMonthYear(vendorId, time, timePeriod);
	    
		int calculatedRevenue = totalCost * profitPercentageInt / 100;
		
		request.setAttribute("calculatedRevenue", calculatedRevenue);
	    request.getRequestDispatcher("/views/admin/revenue.jsp").forward(request, response);
	}
}