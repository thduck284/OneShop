<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách cửa hàng</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>
	<div class="container" style="margin: 0 0 0 -120px;">	
		<h3 class="text-center mb-4" style="font-weight: bold;">Các cửa hàng đang quản lý</h3>
		<form action="${pageContext.request.contextPath}/vendor/search-shop" method="GET" style="margin: 20px 0;">
		    <input type="text" name="searchQuery" placeholder="Tìm kiếm cửa hàng (theo shopId, userId và shopName)..." class="form-control" style="margin: 15px 0 0 100px; width: 700px; display: inline-block;">
		    <button type="submit" class="btn btn-success" style="background-color: #1EC481; font-size: 15px; color: black; margin-top: -5px; display: inline-block;">Tìm kiếm</button>
		</form>
		<div class="table-responsive">
			<table
				class="table table-striped table-bordered table-hover align-middle text-center"
				style="width: 1200px; max-height: 400px;">
				<thead class="table-dark">
					<tr>
						<th>Shop ID</th>
						<th>User ID</th>
						<th>Tên Cửa Hàng</th>
						<th>Mô Tả</th>
						<th>Trạng Thái</th>
						<th>Ngày Tạo</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
					List<models.Shop> shops = (List<models.Shop>) request.getAttribute("shops");
					if (shops != null) {
						for (models.Shop shop : shops) {
					%>
					<tr>
						<td style="border: 2px solid gray;"><%=shop.getShopId()%></td>
						<td style="border: 2px solid gray;"><%=shop.getUserId()%></td>
						<td style="border: 2px solid gray;"><%=shop.getShopName()%></td>
						<td style="border: 2px solid gray;"><%=shop.getDescription()%></td>
						<td style="border: 2px solid gray;"><%=shop.getStatus()%></td>
						<td style="border: 2px solid gray;"><%=shop.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))%></td>
						<td><a href="${pageContext.request.contextPath}/vendor/edit-shop?shopId=<%= shop.getShopId() %>"
							class="btn btn-warning btn-sm" style="background-color: yellow;">Sửa</a>
							<form action="${pageContext.request.contextPath}/vendor/delete-shop" method="post" style="display: inline;">
								<input type="hidden" name="shopId" value="<%=shop.getShopId()%>">
								<button type="submit" class="btn btn-danger btn-sm" style="background-color: red;" onclick="return confirm('Bạn có chắc chắn muốn xóa cửa hàng này không?');">Xóa</button>
							</form></td>
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
	                url: '${pageContext.request.contextPath}/vendor/search-shop',
	                method: 'GET',
	                data: { searchQuery: searchQuery },
	                success: function(response) {
	                    console.log(response);
	
	                    var tbody = $("table tbody"); 
	                    tbody.empty(); 
	
	                    if (Array.isArray(response) && response.length > 0) {
	                        $.each(response, function(index, shop) {
	                            var row = $("<tr>");
	                            row.append($("<td>").text(shop.shopId).css("border", "2px solid gray"));
	                            row.append($("<td>").text(shop.userId).css("border", "2px solid gray"));
	                            row.append($("<td>").text(shop.shopName).css("border", "2px solid gray"));
	                            row.append($("<td>").text(shop.description).css("border", "2px solid gray"));
	                            row.append($("<td>").text(shop.status).css("border", "2px solid gray"));
	                            row.append($("<td>").text(shop.createdDate).css("border", "2px solid gray"));
	                            var actions = $("<td>").addClass("text-center");
	                            actions.append('<a href="${pageContext.request.contextPath}/vendor/edit-shop?shopId=' + shop.shopId + '" class="btn btn-warning btn-sm" style="margin-right: 5px; background-color: #ffc107;">Sửa</a>');
	                            actions.append('<form action="${pageContext.request.contextPath}/vendor/delete-shop" method="POST" style="display: inline;" onsubmit="return confirm(\'Bạn có chắc chắn muốn xóa cửa hàng này không?\');">\n' +
	                                '<input type="hidden" name="shopId" value="' + shop.shopId + '">\n' +
	                                '<button type="submit" class="btn btn-danger btn-sm" style="margin-right: 5px; background-color: #dc3545;">Xóa</button>\n' +
	                                '</form>');
	                            row.append(actions);
	                            tbody.append(row); 
	                        });
	                    } else {
	                        tbody.append("<tr><td colspan='7' class='text-center'>Không tìm thấy cửa hàng nào.</td></tr>");
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7VVf0SzP+UlwzwyFw4klZ+khcAhmGn8So7rH2yKD5nMjNA2" crossorigin="anonymous"></script>
</body>
</html>
