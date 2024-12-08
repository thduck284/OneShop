package controllers.login;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/forget-password"})
public class ForgetPasswordController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl(); 

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
	    request.getRequestDispatcher("/views/login/forgetPassword.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String newPassword = request.getParameter("pw");
        String confirmPassword = request.getParameter("confirmPw");
        StringBuilder jsonResponse = new StringBuilder();
 
        if (email == null || email.isEmpty() || newPassword == null || newPassword.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            jsonResponse.append("{\"success\":false,\"message\":\"Điền tất cả thông tin.\"}");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            jsonResponse.append("{\"success\":false,\"message\":\"Xác nhận mật khẩu không trùng khớp.\"}");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        boolean isEmailValid = userService.checkDuplicate("email", email);

        if (isEmailValid) {
            userService.resetPassword(email, newPassword);
            jsonResponse.append("{\"success\":true,\"message\":\"Thay đổi mật khẩu thành công.\"}");
        } else {
            jsonResponse.append("{\"success\":false,\"message\":\"Email không tồn tại.\"}");
        }

        response.getWriter().write(jsonResponse.toString());
    }
}
