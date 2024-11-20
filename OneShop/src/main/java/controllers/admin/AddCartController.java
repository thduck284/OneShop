package controllers.admin;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cart;
import service.CartService;
import serviceImpl.CartServiceImpl;

@WebServlet(urlPatterns = {"/admin/add-cart"})
@MultipartConfig
public class AddCartController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	CartService cartService = new CartServiceImpl();
	Cart cart = new Cart();
	
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		request.getRequestDispatcher("/views/admin/addCart.jsp").forward(request, response);	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		cart.setUserId(request.getParameter("userId")) ;
		cart.setFullName(request.getParameter("fullName")); 	
		cart.setTotalPrice(Integer.parseInt(request.getParameter("totalPrice"))); 	
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		cart.setCreatedDate(currentDateTime);
		  
		cartService.addCart(cart); 
		request.setAttribute("message", "Thêm giỏ hàng thành công!");
		
		request.getRequestDispatcher("/views/admin/addCart.jsp").forward(request, response);
    }
}
