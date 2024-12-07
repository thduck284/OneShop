package dao;

import java.util.List;

import models.Review;

public interface ReviewDAO {
	boolean addReview(Review review);
    List<Review> getReviewsByProduct(String productId);
    List<Review> getReviewByUserId(String userId);
}
