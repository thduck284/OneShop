package controllers.login;

import java.io.IOException;

import daoImpl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO = new UserDAOImpl(); 

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValidUser = false;

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            isValidUser = (userDAO.checkDuplicate("username", username) && userDAO.checkDuplicate("password", password)); 
        }

        if (isValidUser) {
        	User user = new User();
        	user = userDAO.getInforUser(username);
        	request.getSession().setAttribute("userInfor", user);
        	
        	if ("customer".equals(user.getRole())) {
        	    response.sendRedirect(request.getContextPath() + "/user/home");
        	} else if ("manager".equals(user.getRole())) {
        	    response.sendRedirect(request.getContextPath() + ("/manager-overview.com"));
        	} else {
        	    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        	}
        	
        } else {
        	
        	request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không chính xác.");
        	request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
        }
    }
}
