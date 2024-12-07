<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Review" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container" style="margin: 0 0 0 -120px;">
        <h3 class="text-center mb-4" style="font-weight: bold;">Danh sách các đơn hàng</h3>
        <form action="${pageContext.request.contextPath}/vendor/search-review" method="GET" style="margin: 20px 0;">
		    <input type="text" name="searchQuery" placeholder="Tìm kiếm cửa hàng (theo mã ĐG, mã KH và mã SP)..." class="form-control" style="margin: 15px 0 0 100px; width: 700px; display: inline-block;">
		    <button type="submit" class="btn btn-success" style="background-color: #1EC481; font-size: 15px; color: black; margin-top: -5px; display: inline-block;">Tìm kiếm</button>
		</form>
        <div class="table-responsive">
            <table class="table table-striped table-breviewed table-hover align-middle text-center" style="width: 1200px; max-height: 400px;">
                <thead class="table-dark">
				    <tr>
				        <th>Mã ĐG</th>
				        <th>Mã KH</th>
				        <th>Mã SP</th>
				        <th>Điểm</th>
				        <th>Bình luận</th>
				        <th>Ngày</th>
				    </tr>
				</thead>
				<tbody>
                    <%
                        List<Review> listReview = (List<Review>) session.getAttribute("listReview");
                        if (listReview != null && !listReview.isEmpty()) {
                            for (Review review : listReview) {
                    %>
                                <tr>
                                    <td><%= review.getReviewId() %></td>
                                    <td><%= review.getUserId() %></td>
                                    <td><%= review.getProductId() %></td>
                                    <td><%= review.getPoint() %></td>
                                    <td><%= review.getComment() %></td>
                                    <td>
                                        <%= review.getReviewDate() != null 
                                            ? new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(review.getReviewDate()) 
                                            : "Không có ngày" %>
                                    </td>
                                </tr>
                    <%
                            }
                        } else {
                    %>
                            <tr>
                                <td colspan="7" class="text-center">Không có dữ liệu</td>
                            </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
	    $(document).ready(function() {
	        $("form").submit(function(event) {
	            event.preventDefault(); 
	            var searchQuery = $("input[name='searchQuery']").val(); 	
	            $.ajax({
	                url: '${pageContext.request.contextPath}/vendor/search-review',
	                method: 'GET',
	                data: { searchQuery: searchQuery },
	                success: function(response) {
	                    console.log(response);
	
	                    var tbody = $("table tbody");
	                    tbody.empty(); 
	
	                    if (Array.isArray(response) && response.length > 0) {
	                        $.each(response, function(index, review) {
	                            var row = $("<tr>");
	                            row.append($("<td>").text(review.reviewId));
	                            row.append($("<td>").text(review.userId));
	                            row.append($("<td>").text(review.productId));
	                            row.append($("<td>").text(review.point));
	                            row.append($("<td>").text(review.comment));
	                            row.append($("<td>").text(review.reviewDate 
	                                ? new Date(review.reviewDate).toLocaleString() 
	                                : "Không có ngày"));
	                            tbody.append(row);
	                        });
	                    } else {
	                        tbody.append("<tr><td colspan='6' class='text-center'>Không tìm thấy đánh giá nào.</td></tr>");
	                    }
	                },
	                error: function(xhr, status, error) {
	                    console.error("Lỗi AJAX: ", error);
	                    alert('Có lỗi xảy ra khi tìm kiếm.');
	                }
	            });
	        });
	    });
	</script>
</body>
</html>