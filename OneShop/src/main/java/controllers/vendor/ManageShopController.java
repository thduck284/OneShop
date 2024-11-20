package controllers.vendor;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Shop;
import service.ShopService;
import serviceImpl.ShopServiceImpl;

@WebServlet(urlPatterns = {"/vendor/manage-shop", "/vendor/delete-shop"})
public class ManageShopController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ShopService shopService = new ShopServiceImpl();
	private List<Shop> shops;
	private String userId;
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		userId = request.getParameter("userId");
		shops = shopService.getAllShopsByUserId(userId);
		
		request.setAttribute("shops", shops);	
		request.getRequestDispatcher("/views/vendor/manageShop.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String shopId = request.getParameter("shopId");
		System.out.println(shopId);
		shopService.deleteShop(shopId); 
		request.setAttribute("message", "Xóa cửa hàng cửa hàng thành công!");
		shops = shopService.getAllShopsByUserId(userId);
		request.setAttribute("shops", shops);	
		request.getRequestDispatcher("/views/vendor/manageShop.jsp").forward(request, response);
    }
}
