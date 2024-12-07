package controllers.vendor;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Review;
import models.User;
import service.ReviewService;
import serviceImpl.ReviewServiceImpl;

@WebServlet(urlPatterns = {"/vendor/review-product"})
public class ReviewProductController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ReviewService reviewService = new ReviewServiceImpl();	
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			User user = (User) httpRequest.getSession().getAttribute("vendorInfor");
			List<Review> listReview = reviewService.getReviewByUserId(user.getUserId());

			session.setAttribute("listReview", listReview);
		}
		
		request.getRequestDispatcher("/views/vendor/reviewProduct.jsp").forward(request, response);
	}

}
