<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="models.Order" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="app-content content">
		<div class="content-wrapper">
			<div class="content-header row"></div>
			<div class="content-body">
				<div class="row match-height">
					<div class="col-xl-8 col-12">
						<div class="card card-transparent">
							<div class="card-header card-header-transparent py-20">
								<div class="btn-group dropdown">
									<h4 style="margin-top: -50px;">Home -> Đơn hàng</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="row" style="margin: -60px 0 0 20px;">
						<div id="recent-transactions" class="col-12">
							<% String message = (String) request.getAttribute("message"); %>
								<% if (message != null) { %>
    								<div class="alert alert-info">
        								<%= message %>
    								</div>
							<% } %>		
							<form action="${pageContext.request.contextPath}/admin/search-order" method="GET" style="margin: 20px 0;">
							    <input type="text" name="searchQuery" placeholder="Tìm kiếm đơn hàng..." class="form-control" style="margin: 15px 0 0 100px; width: 900px; display: inline-block;">
							    <button type="submit" class="btn btn-success" style="font-size: 15px; color: black; margin-top: -5px; display: inline-block;">Tìm kiếm</button>
							</form>	
							<div style="margin: -62px 0 20px 1140px;">
								<div class="btn-group">
									<a href="http://localhost:8080/OneShop/admin/order" class="btn btn-warning btn-sm" 
										style="font-size: 15px; padding: 12px 28px;">Lọc</a>
								</div>
							</div>
							<div class="table-responsive" style="max-height: 500px; overflow-y: auto;  max-width: 1300px;">
								<table id="recent-orders"
									class="table table-striped table-bordered table-hover align-middle text-center" style="border: 1px solid black;">
									<thead style="background-color: black; color: white;">
										<tr>
											<th style="width: 20px;">Mã ĐH</th>
											<th style="width: 20px;">Mã KH</th>
											<th style="width: 20px;">Mã GH</th>
											<th style="width: 20px;">Mã GG</th>
											<th>Tổng tiền</th>
											<th>Phương thức TT</th>
											<th>Trạng thái TT</th>
											<th>Ngày mua</th>
										</tr>
									</thead>
									<tbody style="border: 2px solid gray;">
										<c:forEach var="order" items="${orders}">
											<tr>
												<td style="border: 2px solid gray;">${order.orderId}</td>
												<td style="border: 2px solid gray;">${order.userId}</td>
												<td style="border: 2px solid gray;">${order.cartId}</td>
												<td style="border: 2px solid gray;">${order.promoId}</td>
												<td style="border: 2px solid gray;"><fmt:formatNumber value="${order.totalCost}" type="number" pattern="#,###"/> đ</td>
												<td style="border: 2px solid black; word-wrap: break-word;">${order.paymentMethod}</td>
												<td style="border: 2px solid gray;">
									                ${order.paymentStatus == true ? 'Đã thanh toán' : 'Chưa thanh toán'}
									            </td>
												<td style="border: 2px solid gray;">${order.paymentDate}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
        $(document).ready(function(){
            $("form").submit(function(event){
                event.preventDefault(); 
                var searchQuery = $("input[name='searchQuery']").val(); 
                $.ajax({
                    url: '${pageContext.request.contextPath}/admin/search-order',
                    method: 'GET',
                    data: { searchQuery: searchQuery },
                    success: function(response) {
                        console.log(response);  
                        var tbody = $("#recent-orders tbody");
                        tbody.empty();
                        if (Array.isArray(response) && response.length > 0) {
                            $.each(response, function(index, order) {
                                var row = $("<tr>");
                                row.append($("<td>").text(order.orderId).css({"border": "2px solid gray", "padding": "8px"}));
                                row.append($("<td>").text(order.userId).css({"border": "2px solid gray", "padding": "8px"}));
                                row.append($("<td>").text(order.cartId).css({"border": "2px solid gray", "padding": "8px"}));
                                row.append($("<td>").text(order.promoId).css({"border": "2px solid gray", "padding": "8px"}));
                                row.append($("<td>").text(new Intl.NumberFormat().format(order.totalCost) + ' đ').css({"border": "2px solid gray", "padding": "8px"}));
                                row.append($("<td>").text(order.paymentMethod).css({"border": "2px solid gray", "padding": "8px"}));
                                row.append($("<td>").text(order.paymentStatus ? 'Đã thanh toán' : 'Chưa thanh toán').css({"border": "2px solid gray", "padding": "8px"}));
                                row.append($("<td>").text(new Date(order.paymentDate).toLocaleDateString()).css({"border": "2px solid gray", "padding": "8px"}));
                                tbody.append(row);
                            });
                        } else {
                            tbody.append("<tr><td colspan='8'>Không tìm thấy đơn hàng nào.</td></tr>");
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