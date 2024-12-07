<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container" style="margin: 0 0 0 -120px;">
        <h3 class="text-center mb-4" style="font-weight: bold;">Danh sách các khách hàng</h3>
        <form action="${pageContext.request.contextPath}/vendor/search-customer" method="GET" style="margin: 20px 0;">
		    <input type="text" name="searchQuery" placeholder="Tìm kiếm khách hàng (theo mã KH, tên KH và địa chỉ)..." class="form-control" style="margin: 15px 0 0 100px; width: 700px; display: inline-block;">
		    <button type="submit" class="btn btn-success" style="background-color: #1EC481; font-size: 15px; color: black; margin-top: -5px; display: inline-block;">Tìm kiếm</button>
		</form>
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover align-middle text-center" style="width: 1000px; max-height: 400px;">
                <thead class="table-dark">
				    <tr>
				        <th>Mã KH</th>
				        <th>Tên KH</th>
				        <th>Email</th>
				        <th>Số điện thoại</th>
				        <th>Địa chỉ</th>
				    </tr>
				</thead>
				<tbody>
				    <%
				        List<User> listCustomer = (List<User>) session.getAttribute("listCustomer");
				        if (listCustomer != null && !listCustomer.isEmpty()) {
				            for (User customer : listCustomer) {
				                String userId = customer.getUserId();
				                String fullName = customer.getFullName();
				                String email = customer.getEmail();
				                String phone = customer.getPhoneNumber();
				                String address = customer.getAddress();
				    %>
				                <tr>
				                    <td><%= userId %></td>
				                    <td><%= fullName %></td>
				                    <td><%= email %></td>
				                    <td><%= phone %></td>
				                    <td><%= address %></td>
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
                    url: '${pageContext.request.contextPath}/vendor/search-customer',
                    method: 'GET',
                    data: { searchQuery: searchQuery },
                    success: function(response) {
                        console.log(response);

                        var tbody = $("table tbody"); 
                        tbody.empty(); 

                        if (Array.isArray(response) && response.length > 0) {
                            $.each(response, function(index, customer) {
                                var row = $("<tr>");
                                row.append($("<td>").text(customer.userId).css("border", "2px solid gray"));
                                row.append($("<td>").text(customer.fullName).css("border", "2px solid gray"));
                                row.append($("<td>").text(customer.email).css("border", "2px solid gray"));
                                row.append($("<td>").text(customer.phoneNumber).css("border", "2px solid gray"));
                                row.append($("<td>").text(customer.address).css("border", "2px solid gray"));
                                tbody.append(row); 
                            });
                        } else {
                            tbody.append("<tr><td colspan='5' class='text-center'>Không tìm thấy khách hàng nào.</td></tr>");
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