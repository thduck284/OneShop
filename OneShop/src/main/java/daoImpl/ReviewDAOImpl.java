package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dao.ReviewDAO;
import models.Review;

public class ReviewDAOImpl implements ReviewDAO {
	
    @Override
    public boolean addReview(Review review) {
        String sql = "INSERT INTO review (reviewId, userId, productId, point, comment, reviewDate) VALUES (?, ?, ?, ?, ?, GETDATE())";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            String reviewId = UUID.randomUUID().toString().substring(0, 9); 
            review.setReviewId(reviewId);

            stmt.setString(1, review.getReviewId());
            stmt.setString(2, review.getUserId());
            stmt.setString(3, review.getProductId());
            stmt.setInt(4, review.getPoint());
            stmt.setString(5, review.getComment());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Review> getReviewsByProduct(String productId) {
        String sql = "SELECT userId, point, comment, reviewDate FROM review WHERE productId = ? ORDER BY reviewDate DESC";
        List<Review> reviews = new ArrayList<>();

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setUserId(rs.getString("userId"));
                review.setPoint(rs.getInt("point"));
                review.setComment(rs.getString("comment"));
                review.setReviewDate(rs.getTimestamp("reviewDate"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }

	@Override
	public List<Review> getReviewByUserId(String userId) {
		// TODO Auto-generated method stub
		String sql = """
		        SELECT r.reviewId, r.userId, r.productId, r.point, r.comment, r.reviewDate
		        FROM shop s
		        RIGHT JOIN product p ON s.shopId = p.shopId
		        JOIN review r ON r.productId = p.productId
		        WHERE s.userId = ?
		    """;

	    List<Review> reviews = new ArrayList<>();

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql)) {

	        stmt.setString(1, userId);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Review review = new Review();
	            review.setReviewId(rs.getString("reviewId"));
	            review.setUserId(rs.getString("userId"));
	            review.setProductId(rs.getString("productId"));
	            review.setPoint(rs.getInt("point"));
	            review.setComment(rs.getString("comment"));
	            review.setReviewDate(rs.getTimestamp("reviewDate"));
	            reviews.add(review);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return reviews;
	}
}