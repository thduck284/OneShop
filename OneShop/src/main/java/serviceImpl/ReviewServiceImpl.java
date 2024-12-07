package serviceImpl;

import java.util.List;

import dao.ReviewDAO;
import daoImpl.ReviewDAOImpl;
import models.Review;
import service.ReviewService;

public class ReviewServiceImpl implements ReviewService{

	ReviewDAO reviewDAO = new ReviewDAOImpl();
	
    @Override
    public boolean addReview(Review review) {
        return reviewDAO.addReview(review);
    }

    @Override
    public List<Review> getReviewsByProduct(String productId) {
        return reviewDAO.getReviewsByProduct(productId);
    }

	@Override
	public List<Review> getReviewByUserId(String userId) {
		// TODO Auto-generated method stub
		return reviewDAO.getReviewByUserId(userId);
	}
}
