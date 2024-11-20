package controllers.admin;

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

@WebServlet(urlPatterns = {"/admin/shop"})
public class ShopController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ShopService ShopService = new ShopServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		List<Shop> shops = ShopService.getAllShops();

		request.setAttribute("Shops", shops);	
		request.getRequestDispatcher("/views/admin/shop.jsp").forward(request, response);
	}

}
