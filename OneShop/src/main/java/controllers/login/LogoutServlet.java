package controllers.login;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);

        if (session != null) {
        	String role = request.getParameter("role");

            if ("customer".equals(role)) {
                session.removeAttribute("wishList");
                session.removeAttribute("userInfor");
            } else if ("vendor".equals(role)) {
                session.removeAttribute("vendorInfor");
            } else if ("admin".equals(role)) {
                session.removeAttribute("adminInfor");
            }
        }

        response.sendRedirect(request.getContextPath() + "/login");
    }
}
