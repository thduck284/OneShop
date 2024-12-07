<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="models.Order" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-4 d-flex justify-content-center" style="margin: 0 0 0 -80px;">
		<div style="width: 90%; max-width: 900px; margin-left: -70px;">
			<div class="row mb-4 text-center">
		        <div class="col-md-3">
		            <div class="card text-white bg-info mb-3">
		                <div class="card-body">
		                    <h5 class="card-title">Khách hàng</h5>
		                    <p class="card-text"><%= session.getAttribute("customerCount") %></p>
		                </div>
		            </div>
		        </div>
		        <div class="col-md-3">
		            <div class="card text-white bg-success mb-3">
		                <div class="card-body">
		                    <h5 class="card-title">Đơn hàng</h5>
		                    <p class="card-text"><%= session.getAttribute("orderCount") %></p>
		                </div>
		            </div>
		        </div>
		        <div class="col-md-3">
		            <div class="card text-white bg-primary mb-3">
		                <div class="card-body">
		                    <h5 class="card-title">Sản phẩm</h5>
		                    <p class="card-text"><%= session.getAttribute("productCount") %></p>
		                </div>
		            </div>
		        </div>
		        <div class="col-md-3">
		            <div class="card text-white bg-warning mb-3">
		                <div class="card-body">
		                    <h5 class="card-title">Doanh thu</h5>
		                    <p class="card-text"><%= session.getAttribute("venue") %></p>
		                </div>
		            </div>
		        </div>
		    </div>
		    <h4 class="mt-5">Top 3 đơn hàng gần đây</h4>
		    <table class="table table-hover">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Tên Khách Hàng</th>
                    <th>Ngày</th>
                    <th>Tổng tiền</th>
                    <th>Trạng thái</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Order> top3Orders = (List<Order>) session.getAttribute("top3Orders");
                    if (top3Orders != null) {
                        int count = 1;
                        for (Order order : top3Orders) {
                            if (count > 3) break;
                %>
                    <tr>
                        <td><%= count %></td>
                        <td><%= order.getUserId() %></td>
                        <td><%= order.getPaymentDate() != null ? order.getPaymentDate().toString() : "N/A" %></td>
                        <td><%= order.getTotalCost() %></td>
                        <td><span class="badge <%= order.getPaymentStatus() ? "bg-success" : "bg-warning" %>">
                            <%= order.getPaymentStatus() ? "Đã thanh toán" : "Chưa thanh toán" %></span></td>
                    </tr>
                <%
                            count++;
                        }
                    }
                %>
            </tbody>
        </table>
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7VVf0SzP+UlwzwyFw4klZ+khcAhmGn8So7rH2yKD5nMjNA2" crossorigin="anonymous"></script>
</body>
</html>