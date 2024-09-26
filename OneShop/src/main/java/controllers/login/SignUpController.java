package controllers.login;

import java.io.IOException;
import java.sql.SQLException;

import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/sign-up"})
public class SignUpController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
	}	
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    String firstName = request.getParameter("firstname");
	    String lastName = request.getParameter("lastname");
	    String fullName = firstName + " " + lastName;
	    String email = request.getParameter("email");
	    String phone = request.getParameter("phone");
	    String address = request.getParameter("address");
	    String username = request.getParameter("username");
	    String password = request.getParameter("pw");
	    
	    UserDAO userDAO = new UserDAO();
	    
	    try {
	       
	        userDAO.addUser(fullName, email, phone, address, username, password, "customer");
			/*
			 * RequestDispatcher dispatcher =
			 * request.getRequestDispatcher("/WEB-INF/views/signUpSuccess.jsp");
			 * dispatcher.forward(request, response);
			 * response.sendRedirect(request.getContextPath() + "/signUpSuccess.jsp");
			 */
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	        request.setAttribute("error", "Có lỗi xảy ra trong quá trình đăng ký. Vui lòng thử lại.");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login/signUpFail.jsp");
	        dispatcher.forward(request, response);
	    }
	}
}
