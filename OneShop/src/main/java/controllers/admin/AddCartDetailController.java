package controllers.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.CartDetail;
import service.CartDetailService;
import serviceImpl.CartDetailServiceImpl;

@WebServlet(urlPatterns = {"/admin/add-cart-detail"})
@MultipartConfig
public class AddCartDetailController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	CartDetailService detailCartService = new CartDetailServiceImpl();
	CartDetail cartDetail = new CartDetail();
	
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		request.getRequestDispatcher("/views/admin/addCartDetail.jsp").forward(request, response);	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		cartDetail.setProductId(request.getParameter("cartId"));
		cartDetail.setProductId(request.getParameter("productId"));
		cartDetail.setProductName(request.getParameter("productName")) ;
		cartDetail.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		cartDetail.setPrice(Integer.parseInt(request.getParameter("price")));
	
		detailCartService.addCartDetail(cartDetail); 
		request.setAttribute("message", "Thêm chi tiết giỏ hàng thành công!");
		
		request.getRequestDispatcher("/views/admin/addCartDetail.jsp").forward(request, response);
    }
}
