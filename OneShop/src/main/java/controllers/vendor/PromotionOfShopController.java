package controllers.vendor;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Promotion;
import service.PromotionService;
import serviceImpl.PromotionServiceImpl;

@WebServlet(urlPatterns = {"/vendor/promotion-of-shop", "/vendor/delete-promotion"})
public class PromotionOfShopController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private PromotionService promotionService = new PromotionServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	
		List<Promotion> listPromotion = promotionService.getAllPromotion();
		
		request.setAttribute("listPromotion", listPromotion);
		request.getRequestDispatcher("/views/vendor/promotionOfShop.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String promotionId = request.getParameter("promotionId");
		
		promotionService.deletePromotion(promotionId);
		
		response.sendRedirect(request.getContextPath() + "/vendor/promotion-of-shop");
		request.setAttribute("success", "Xóa phiếu giảm giá thành công");
    }
}
