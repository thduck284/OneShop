package serviceImpl;

import dao.ReviewDAO;
import daoImpl.ReviewDAOImpl;
import models.Review;
import service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    ReviewDAO reviewDAO = new ReviewDAOImpl();
    @Override
    public boolean addReview(Review review) {
        return reviewDAO.addReview(review);
    }

    @Override
    public List<Review> getReviewsByProduct(String productId) {
        return reviewDAO.getReviewsByProduct(productId);
    }
}
