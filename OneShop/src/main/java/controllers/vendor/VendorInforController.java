package controllers.vendor;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/vendor/vendor-info"})
public class VendorInforController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		User user = new User();
		/* user = userService.getInforUser(username); */
    	request.getSession().setAttribute("userInfor", user);
		
		request.getRequestDispatcher("/views/vendor/no-layout/infoVendor.jsp").forward(request, response);
	}
}