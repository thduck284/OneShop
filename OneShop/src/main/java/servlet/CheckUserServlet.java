package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckUserServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO = new UserDAO(); 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        boolean isUsernameTaken = false;
        boolean isEmailTaken = false;
        boolean isPhoneTaken = false;

        if (username != null && !username.isEmpty()) {
            isUsernameTaken = userDAO.checkDuplicate("username", username);
        }
        if (email != null && !email.isEmpty()) {
            isEmailTaken = userDAO.checkDuplicate("email", email);
        }
        if (phone != null && !phone.isEmpty()) {
            isPhoneTaken = userDAO.checkDuplicate("phone", phone);
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{");
        out.print("\"isUsernameTaken\": " + (isUsernameTaken ? "true" : "false") + ",");
        out.print("\"isEmailTaken\": " + (isEmailTaken ? "true" : "false") + ",");
        out.print("\"isPhoneTaken\": " + (isPhoneTaken ? "true" : "false"));
        out.print("}");
        out.flush();
    }
}

