package controllers.login;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/sign-up"})
public class SignUpController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private User user = new User();

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.getRequestDispatcher("/views/login/signUp.jsp").forward(request, response);	 
	}	
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		user.setFullName(request.getParameter("firstname") + " " + request.getParameter("lastname"));
		user.setPhoneNumber(request.getParameter("phone")); 
		user.setAddress(request.getParameter("address")); 
		user.setEmail(request.getParameter("email")); 
		user.setUserName(request.getParameter("username")); 
		user.setPassword(request.getParameter("pw"));
		user.setRole("customer");     
        
        boolean isUsernameTaken = false;
		boolean isEmailTaken = false;
		boolean isPhoneTaken = false;

		if (user.getUserName() != null && !user.getUserName().isEmpty()) {
		    isUsernameTaken = userService.checkDuplicate("username", user.getUserName());
		}
		if (user.getEmail() != null && !user.getEmail().isEmpty()) {
		    isEmailTaken = userService.checkDuplicate("email", user.getEmail());
		}
		if (user.getPhoneNumber() != null && !user.getPhoneNumber().isEmpty()) {
		    isPhoneTaken = userService.checkDuplicate("phone", user.getPhoneNumber());
		}
		
		if(!isUsernameTaken && !isEmailTaken && !isPhoneTaken)
		{
			userService.addUser(user);
			request.getRequestDispatcher("/views/login/signUpSuccess.jsp").forward(request, response);
		}
	}
}
