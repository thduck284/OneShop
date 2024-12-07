package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/user/infor-customer", "/vendor/infor-vendor", "/admin/infor-admin"})
public class InforUserController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private User user = new User();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = request.getSession(false);
		String servletPath = request.getServletPath();
		
		if (servletPath.equals("/vendor/infor-vendor")) {
			user = (User) httpRequest.getSession().getAttribute("vendorInfor");
		} else if (servletPath.equals("/admin/infor-admin")) {
			user = (User) session.getAttribute("adminInfor");
		} else {
			user = (User) session.getAttribute("userInfor");
		}   
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        response.getWriter().write(userJson);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String servletPath = request.getServletPath();
		
		if(servletPath.equals("/user/infor-customer")) {
			user.setUserId(user.getUserId());
		} else if (servletPath.equals("/vendor/infor-vendor")) {
			user.setUserId(user.getUserId());
		} else if (servletPath.equals("/admin/infor-admin")) {
			user.setUserId("admin");
		}
		
		user.setUserName(request.getParameter("userName"));
        user.setEmail(request.getParameter("email"));
        user.setFullName(request.getParameter("fullName"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));
        user.setAddress(request.getParameter("address"));
 
        userService.updateUser(user);
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{\"success\": true}");
        out.flush();
    }
	
}
