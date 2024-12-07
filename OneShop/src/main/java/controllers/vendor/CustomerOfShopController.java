package controllers.vendor;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/vendor/customer-of-shop"})
public class CustomerOfShopController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			User user = (User) httpRequest.getSession().getAttribute("vendorInfor");
			List<User> listCustomer = userService.getCustomerByUserId(user.getUserId());
			
			Set<String> seenUserIds = new HashSet<>();
            List<User> uniqueCustomers = listCustomer.stream()
                .filter(customer -> seenUserIds.add(customer.getUserId()))  
                .collect(Collectors.toList());
            
			
			session.setAttribute("listCustomer", uniqueCustomers);
		}
		
		request.getRequestDispatcher("/views/vendor/customerOfShop.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    }
}
