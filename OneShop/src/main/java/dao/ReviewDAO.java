package dao;

import models.Review;

import java.util.List;

public interface ReviewDAO {
    boolean addReview(Review review);
    List<Review> getReviewsByProduct(String productId);
}
