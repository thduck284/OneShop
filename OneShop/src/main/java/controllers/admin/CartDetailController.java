package controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.CartDetail;
import service.CartDetailService;
import serviceImpl.CartDetailServiceImpl;

@WebServlet(urlPatterns = {"/admin/cart-detail"})
public class CartDetailController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private CartDetailService detailCartService = new CartDetailServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		List<CartDetail> cartDetail = detailCartService.getAllCartDetail();
		
		request.setAttribute("cartDetail", cartDetail);	
		request.getRequestDispatcher("/views/admin/cartDetail.jsp").forward(request, response);
	}
}
