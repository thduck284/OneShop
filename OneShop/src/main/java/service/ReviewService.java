package service;

import java.util.List;

import models.Review;

public interface ReviewService {
	boolean addReview(Review review);
    List<Review> getReviewsByProduct(String productId);
    List<Review> getReviewByUserId(String userId);
}
