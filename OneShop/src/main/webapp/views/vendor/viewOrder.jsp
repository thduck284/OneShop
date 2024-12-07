<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Order" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container" style="margin: 0 0 0 -120px;">
        <h3 class="text-center mb-4" style="font-weight: bold;">Danh sách các đơn hàng</h3>
        <form action="${pageContext.request.contextPath}/vendor/search-order" method="GET" style="margin: 20px 0;">
		    <input type="text" name="searchQuery" placeholder="Tìm kiếm cửa hàng (theo orderId, cartId và userId)..." class="form-control" style="margin: 15px 0 0 100px; width: 700px; display: inline-block;">
		    <button type="submit" class="btn btn-success" style="background-color: #1EC481; font-size: 15px; color: black; margin-top: -5px; display: inline-block;">Tìm kiếm</button>
		</form>
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover align-middle text-center" style="width: 1200px; max-height: 400px;">
                <thead class="table-dark">
				    <tr>
				        <th>Mã ĐH</th>
				        <th>Mã KH</th>
				        <th>Mã GH</th>
				        <th>Mã KM</th>
				        <th>Phương thức TT</th>
				        <th>Tổng tiền</th>
				        <th>Trạng thái TT</th>
				        <th>Ngày</th>
				    </tr>
				</thead>
				<tbody>
                    <%
                        List<Order> listOrder = (List<Order>) session.getAttribute("listOrder");
                        if (listOrder != null && !listOrder.isEmpty()) {
                            for (Order order : listOrder) {
                    %>
                                <tr>
                                    <td><%= order.getOrderId() %></td>
                                    <td><%= order.getUserId() %></td>
                                    <td><%= order.getCartId() %></td>
                                    <td><%= order.getPromoId() != null ? order.getPromoId() : "Không có mã KM" %></td>
                                    <td><%= order.getPaymentMethod() %></td>
                                    <td>
		                            	<fmt:formatNumber value="<%= order.getTotalCost() %>" type="number" pattern="#,##0" /> đ
		                        	</td>
                                    <td><%= order.getPaymentStatus() ? "Đã thanh toán" : "Chưa thanh toán" %></td>
                                    <td>
                                        <%= order.getPaymentDate() != null 
                                            ? new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(order.getPaymentDate()) 
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
	                url: '${pageContext.request.contextPath}/vendor/search-order',
	                method: 'GET',
	                data: { searchQuery: searchQuery },
	                success: function(response) {
	                    console.log(response);
	
	                    var tbody = $("table tbody"); 
	                    tbody.empty(); 
	
	                    if (Array.isArray(response) && response.length > 0) {
	                        $.each(response, function(index, order) { 
	                            var row = $("<tr>");
	                            row.append($("<td>").text(order.orderId).css("border", "2px solid gray"));
	                            row.append($("<td>").text(order.userId).css("border", "2px solid gray"));
	                            row.append($("<td>").text(order.cartId).css("border", "2px solid gray"));
	                            row.append($("<td>").text(order.promoId != null ? order.promoId : "Không có mã KM").css("border", "2px solid gray"));
	                            row.append($("<td>").text(order.paymentMethod).css("border", "2px solid gray"));
	                            row.append($("<td>").text(order.totalCost).css("border", "2px solid gray"));
	                            row.append($("<td>").text(order.paymentStatus ? "Đã thanh toán" : "Chưa thanh toán").css("border", "2px solid gray"));
	                            row.append($("<td>").text(order.paymentDate ? new Date(order.paymentDate).toLocaleString() : "Không có ngày").css("border", "2px solid gray"));
	                            var actions = $("<td>").addClass("text-center");
	                            row.append(actions);
	                            tbody.append(row); 
	                        });
	                    } else {
	                        tbody.append("<tr><td colspan='8' class='text-center'>Không tìm thấy đơn hàng nào.</td></tr>");
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