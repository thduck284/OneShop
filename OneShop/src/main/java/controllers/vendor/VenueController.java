package controllers.vendor;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import service.OrderService;
import serviceImpl.OrderServiceImpl;

@WebServlet(urlPatterns = {"/vendor/venue"})
public class VenueController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderServiceImpl();	
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/vendor/venue.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = request.getSession(false);
		User user = new User();
		
		if(session != null) {
			user = (User) httpRequest.getSession().getAttribute("vendorInfor");
		}
		
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
		
		int totalCost = orderService.getVenueByDayMonthYear(user.getUserId(), time, timePeriod);
	    
		int calculatedRevenue = totalCost * profitPercentageInt / 100;
		
		request.setAttribute("calculatedRevenue", calculatedRevenue);
	    request.getRequestDispatcher("/views/vendor/venue.jsp").forward(request, response);
	}
}
