<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Promotion" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% if (request.getAttribute("success") != null) { %>
		    <div class="alert alert-success" style="margin-left: -100px;">
		        <%= request.getAttribute("success") %>
		    </div>
	<% } %>
	<a href="${pageContext.request.contextPath}/vendor/add-promotion" class="btn btn-success float-right btn-lg"
		style="margin-left: 750px; background-color: #28a745; padding: 5px 17px; font-size: 17px;">Thêm</a>
	<div class="container" style="margin: -30px 0 0 -100px;">
        <h3 class="text-center mb-4" style="font-weight: bold;">Danh sách các phiếu khuyến mãi</h3>
        <form action="${pageContext.request.contextPath}/vendor/search-promotion" method="GET" style="margin: 20px 0;">
		    <input type="text" name="searchQuery" placeholder="Tìm kiếm phiếu KM (theo mã phiếu và mã KH)..." class="form-control" style="margin: 15px 0 0 100px; width: 700px; display: inline-block;">
		    <button type="submit" class="btn btn-success" style="background-color: #1EC481; font-size: 15px; color: black; margin-top: -5px; display: inline-block;">Tìm kiếm</button>
		</form>
        <div class="table-responsive" style="max-height: 400px; overflow-y: auto;">
            <table class="table table-striped table-bordered table-hover align-middle text-center" style="width: 900px;">
                <thead class="table-dark">
				    <tr>
				        <th>Mã KM</th>
				        <th>Mã KH</th>
				        <th>Giảm %</th>
				        <th>Trạng thái</th>
				        <th>Ngày hết hạn</th>
				        <th></th>
				    </tr>
				</thead>
				<tbody>
				    <%
				        List<Promotion> listPromotion = (List<Promotion>) request.getAttribute("listPromotion");
				        if (listPromotion != null && !listPromotion.isEmpty()) {
				            for (Promotion promotion : listPromotion) {
				    %>
				                <tr>
				                    <td><%= promotion.getPromotionId() %></td>
				                    <td><%= promotion.getUserId() %></td>
				                    <td><%= "Giảm " + promotion.getPrice() + "%" %></td>
				                    <td><%= promotion.getStatus() ? "Chưa sử dụng" : "Đã sử dụng" %></td>
				                    <td><%= promotion.getExpirationDate() != null ? 
				                            new java.text.SimpleDateFormat("yyyy-MM-dd").format(promotion.getExpirationDate()) : "Không có ngày" %>
				                    </td>
				                    <td><a href="${pageContext.request.contextPath}/vendor/edit-promotion?promotionId=<%= promotion.getPromotionId() %>"
										class="btn btn-warning btn-sm" style="background-color: yellow;">Sửa</a>
										<form action="${pageContext.request.contextPath}/vendor/delete-promotion?promotionId=<%= promotion.getPromotionId() %>" method="post" style="display: inline;">
											<input type="hidden" name="shopId" value="<%=promotion.getPromotionId()%>">
											<button type="submit" class="btn btn-danger btn-sm" style="background-color: red;" onclick="return confirm('Bạn có chắc chắn muốn xóa cửa hàng này không?');">Xóa</button>
										</form>
									</td>
				                </tr>
				    <%
				            }
				        } else {
				    %>
				            <tr>
				                <td colspan="5" class="text-center">Không có dữ liệu</td>
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
	                url: '${pageContext.request.contextPath}/vendor/search-promotion',
	                method: 'GET',
	                data: { searchQuery: searchQuery },
	                success: function(response) {
	                    console.log(response);
	
	                    var tbody = $("table tbody"); 
	                    tbody.empty(); 
	
	                    if (Array.isArray(response) && response.length > 0) {
	                        $.each(response, function(index, promotion) {
	                            var row = $("<tr>");
	                            row.append($("<td>").text(promotion.promotionId).css("border", "2px solid gray"));
	                            row.append($("<td>").text(promotion.userId).css("border", "2px solid gray"));
	                            row.append($("<td>").text("Giảm " + promotion.price + "%").css("border", "2px solid gray"));
	                            row.append($("<td>").text(promotion.status ? "Chưa sử dụng" : "Đã sử dụng").css("border", "2px solid gray"));
	                            row.append($("<td>").text(promotion.expirationString).css("border", "2px solid gray"));
	                            var actions = $("<td>").addClass("text-center");
	                            actions.append('<a href="${pageContext.request.contextPath}/vendor/edit-promotion?promotionId=' + promotion.promotionId + '" class="btn btn-warning btn-sm" style="margin-right: 5px; background-color: #ffc107;">Sửa</a>');
	                            actions.append('<form action="${pageContext.request.contextPath}/vendor/delete-promotion" method="POST" style="display: inline;" onsubmit="return confirm(\'Bạn có chắc chắn muốn xóa khuyến mãi này không?\');">\n' +
	                                '<input type="hidden" name="promotionId" value="' + promotion.promotionId + '">\n' +
	                                '<button type="submit" class="btn btn-danger btn-sm" style="margin-right: 5px; background-color: #dc3545;">Xóa</button>\n' +
	                                '</form>');
	                            row.append(actions);
	                            tbody.append(row); 
	                        });
	                    } else {
	                        tbody.append("<tr><td colspan='6' class='text-center'>Không tìm thấy phiếu khuyến mãi nào.</td></tr>");
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