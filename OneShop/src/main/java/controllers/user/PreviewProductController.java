package controllers.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Review;
import service.ReviewService;
import serviceImpl.ReviewServiceImpl;

@WebServlet("/review")  
public class PreviewProductController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	ReviewService reviewService = new ReviewServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String productId = request.getParameter("productId");

            if (productId == null || productId.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"error\":\"Product ID is required.\"}");
                return;
            }

            List<Review> reviews = reviewService.getReviewsByProduct(productId);
            out.write(new Gson().toJson(reviews));
        } catch (Exception e) {
     
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"error\":\"An error occurred while processing the request.\"}");
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String userId = request.getParameter("userId");
        String productId = request.getParameter("productId");
        String comment = request.getParameter("comment");
        String pointStr = request.getParameter("point");

        if (userId == null || userId.trim().isEmpty() || productId == null || productId.trim().isEmpty() ||
                comment == null || comment.trim().isEmpty() || pointStr == null || pointStr.trim().isEmpty()) {
            sendErrorResponse(out, "Thông tin không đầy đủ.");
            return;
        }

        int point;
        try {
            point = Integer.parseInt(pointStr);  
        } catch (NumberFormatException e) {
            sendErrorResponse(out, "Điểm đánh giá không hợp lệ.");
            return;
        }

        Review review = new Review();
        review.setUserId(userId);
        review.setProductId(productId);
        review.setComment(comment);
        review.setPoint(point);

        boolean isAdded = reviewService.addReview(review);

        if (isAdded) {
            sendSuccessResponse(out, "Bình luận đã được thêm.");
        } else {
            sendErrorResponse(out, "Đã xảy ra lỗi khi thêm bình luận.");
        }
    }

    private void sendSuccessResponse(PrintWriter out, Object data) {
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(data);
        out.write("{\"success\": true, \"message\": " + jsonResponse + "}");
    }

    private void sendErrorResponse(PrintWriter out, String message) {
        Gson gson = new Gson();
        out.write("{\"success\": false, \"message\": \"" + message + "\"}");
    }
}
