package daoImpl;

import dao.ReviewDAO;
import models.Review;
import java.util.UUID;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {
    @Override
    public boolean addReview(Review review) {
        String sql = "INSERT INTO review (reviewId, userId, productId, point, comment, reviewDate) VALUES (?, ?, ?, ?, ?, GETDATE())";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            String reviewId = UUID.randomUUID().toString().substring(0, 10); // Cắt lấy 10 ký tự đầu
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
}
