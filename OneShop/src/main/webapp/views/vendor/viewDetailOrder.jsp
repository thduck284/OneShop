<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="javax.swing.table.DefaultTableModel" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container" style="margin: 0 0 0 -120px;">
        <h3 class="text-center mb-4" style="font-weight: bold;">Chi tiết các đơn hàng</h3>
        <form action="${pageContext.request.contextPath}/vendor/search-detail-order" method="GET" style="margin: 20px 0;">
		    <input type="text" name="searchQuery" placeholder="Tìm kiếm cửa hàng (theo tên KH, tên SP và tên CH)..." class="form-control" style="margin: 15px 0 0 100px; width: 700px; display: inline-block;">
		    <button type="submit" class="btn btn-success" style="background-color: #1EC481; font-size: 15px; color: black; margin-top: -5px; display: inline-block;">Tìm kiếm</button>
		</form>
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover align-middle text-center" style="width: 1200px; max-height: 400px;">
                <thead class="table-dark">
				    <tr>
				        <th>Mã ĐH</th>
				        <th>Tên KH</th>
				        <th>Tên SP</th>
				        <th>Tên CH</th>
				        <th>Đơn giá</th>
				        <th>Số lượng</th>
				        <th>Ngày TT</th>
				    </tr>
				</thead>
				<tbody>
                    <%
                        DefaultTableModel listOrder = (DefaultTableModel) session.getAttribute("tableOrder");
                        if (listOrder != null && listOrder.getRowCount() > 0) {
                            for (int i = 0; i < listOrder.getRowCount(); i++) {
                                String orderId = (String) listOrder.getValueAt(i, 0);
                                String fullName = (String) listOrder.getValueAt(i, 1);
                                String productName = (String) listOrder.getValueAt(i, 2);
                                String shopName = (String) listOrder.getValueAt(i, 3);
                                Double price = (Double) listOrder.getValueAt(i, 4);
                                Integer quantity = (Integer) listOrder.getValueAt(i, 5);
                                java.sql.Timestamp paymentDate = (java.sql.Timestamp) listOrder.getValueAt(i, 6);
                    %>
                                <tr>
                                    <td><%= orderId %></td>
                                    <td><%= fullName %></td>
                                    <td><%= productName %></td>
                                    <td><%= shopName %></td>
                                    <td>
		                            	<fmt:formatNumber value="<%= price %>" type="number" pattern="#,##0" /> đ
		                        	</td>
                                    <td><%= quantity %></td>
                                    <td>
                                        <%= paymentDate != null 
                                            ? new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(paymentDate) 
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
                    url: '${pageContext.request.contextPath}/vendor/search-detail-order',  
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
                                row.append($("<td>").text(order.fullName).css("border", "2px solid gray"));
                                row.append($("<td>").text(order.productName).css("border", "2px solid gray"));
                                row.append($("<td>").text(order.shopName).css("border", "2px solid gray"));
                                row.append($("<td>").text(order.price).css("border", "2px solid gray"));
                                row.append($("<td>").text(order.quantity).css("border", "2px solid gray"));
                                row.append($("<td>").text(order.paymentString).css("border", "2px solid gray")); 
                                tbody.append(row); 
                            });
                        } else {
                            tbody.append("<tr><td colspan='7' class='text-center'>Không tìm thấy đơn hàng nào.</td></tr>");
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