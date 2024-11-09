package controllers.vendor;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Shop;
import service.ShopService;
import serviceImpl.ShopServiceImpl;

@WebServlet(urlPatterns = {"/vendor/register-shop"})
@MultipartConfig
public class RegisterShopController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ShopService shopService = new ShopServiceImpl();
	private Shop shop = new Shop();
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		request.getRequestDispatcher("/views/vendor/registerShop.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		shop.setUserId(request.getParameter("userId"));
		System.out.println(request.getParameter("userId"));
		shop.setShopName(request.getParameter("shopName")); 	
		shop.setDescription(request.getParameter("description"));
		shop.setStatus(request.getParameter("status")); 
		LocalDateTime currentDateTime = LocalDateTime.now();
		shop.setCreatedDate(currentDateTime);
		  
		shopService.addShop(shop); 
		request.setAttribute("message", "Đăng kí cửa hàng thành công!");
		request.getRequestDispatcher("/views/vendor/registerShop.jsp").forward(request, response);	
    }
}