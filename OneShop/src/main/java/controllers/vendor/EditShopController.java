package controllers.vendor;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Shop;
import service.ShopService;
import serviceImpl.ShopServiceImpl;

@WebServlet(urlPatterns = {"/vendor/edit-shop"})
@MultipartConfig
public class EditShopController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ShopService shopService = new ShopServiceImpl();
	private Shop shop = new Shop();
	private String shopId;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		shopId = request.getParameter("shopId");
		
		shop = shopService.getShopById(shopId);
		request.setAttribute("shop", shop);
		request.getRequestDispatcher("/views/vendor/editShop.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		shop.setUserId(request.getParameter("userId"));
		System.out.println(request.getParameter("userId"));
		shop.setShopName(request.getParameter("shopName")); 	
		shop.setDescription(request.getParameter("description"));
		shop.setStatus(request.getParameter("status")); 
		shop.setCreatedDate(shop.getCreatedDate());
		  
		shopService.updateShop(shop); 
		
		request.setAttribute("message", "Cập nhật thông tin cửa hàng thành công!");
		request.getRequestDispatcher("/views/vendor/editShop.jsp").forward(request, response);
    }
}
