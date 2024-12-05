package service;

import models.Review;

import java.util.List;

public interface ReviewService {
    boolean addReview(Review review);
    List<Review> getReviewsByProduct(String productId);
}
