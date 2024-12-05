package controllers.user;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Review;
import service.ReviewService;
import serviceImpl.ReviewServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/review")  // URL sửa lại là /review cho phù hợp với mã JS
public class PreviewProductController extends HttpServlet {
    ReviewService reviewService = new ReviewServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Thiết lập kiểu trả về JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Lấy productId từ query string
            String productId = request.getParameter("productId");

            // Kiểm tra nếu productId không được cung cấp
            if (productId == null || productId.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"error\":\"Product ID is required.\"}");
                return;
            }

            // Gọi service để lấy danh sách đánh giá
            List<Review> reviews = reviewService.getReviewsByProduct(productId);

            // Trả về danh sách đánh giá dưới dạng JSON
            out.write(new Gson().toJson(reviews));
        } catch (Exception e) {
            // Xử lý lỗi nếu xảy ra
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

        // Lấy các tham số từ request (dữ liệu gửi từ form)
        String userId = request.getParameter("userId");
        String productId = request.getParameter("productId");
        String comment = request.getParameter("comment");
        String pointStr = request.getParameter("point");

        // Kiểm tra và xử lý dữ liệu
        if (userId == null || userId.trim().isEmpty() || productId == null || productId.trim().isEmpty() ||
                comment == null || comment.trim().isEmpty() || pointStr == null || pointStr.trim().isEmpty()) {
            sendErrorResponse(out, "Thông tin không đầy đủ.");
            return;
        }

        int point;
        try {
            point = Integer.parseInt(pointStr);  // Chuyển đổi điểm sang int
        } catch (NumberFormatException e) {
            sendErrorResponse(out, "Điểm đánh giá không hợp lệ.");
            return;
        }

        // Tạo đối tượng Review
        Review review = new Review();
        review.setUserId(userId);
        review.setProductId(productId);
        review.setComment(comment);
        review.setPoint(point);

        // Thêm bình luận vào hệ thống
        boolean isAdded = reviewService.addReview(review);

        if (isAdded) {
            sendSuccessResponse(out, "Bình luận đã được thêm.");
        } else {
            sendErrorResponse(out, "Đã xảy ra lỗi khi thêm bình luận.");
        }
    }

    // Phương thức để gửi phản hồi thành công với JSON
    private void sendSuccessResponse(PrintWriter out, Object data) {
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(data);
        out.write("{\"success\": true, \"message\": " + jsonResponse + "}");
    }

    // Phương thức để gửi phản hồi lỗi với JSON
    private void sendErrorResponse(PrintWriter out, String message) {
        Gson gson = new Gson();
        out.write("{\"success\": false, \"message\": \"" + message + "\"}");
    }

}
