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
    <div class="container mt-4 d-flex justify-content-center" style="margin: 0 0 0 -110px;">
        <div style="width: 100%; max-width: 900px;">
            <h3 class="mb-4 text-center">Các cửa hàng đang quản lý</h3>
            <div style="max-height: 400px; overflow-y: auto;">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Shop ID</th>
                            <th>User ID</th>
                            <th>Tên Cửa Hàng</th>
                            <th>Mô Tả</th>
                            <th>Trạng Thái</th>
                            <th>Ngày Tạo</th>
                            <th>Hành động</th> 
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        List<models.Shop> shops = (List<models.Shop>) request.getAttribute("shops");
                        if (shops != null) {
                            for (models.Shop shop : shops) {
                        %>
                        <tr>
                            <td><%= shop.getShopId() %></td>
                            <td><%= shop.getUserId() %></td>
                            <td><%= shop.getShopName() %></td>
                            <td><%= shop.getDescription() %></td>
                            <td><%= shop.getStatus() %></td>
                            <td><%= shop.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) %></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/vendor/edit-shop?shopId=<%= shop.getShopId() %>" class="btn btn-warning btn-sm">Sửa</a>
                                <form action="${pageContext.request.contextPath}/vendor/delete-shop" method="post" style="display:inline;">
    								<input type="hidden" name="shopId" value="<%= shop.getShopId() %>">
    								<button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa cửa hàng này không?');">Xóa</button>
								</form>
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
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7VVf0SzP+UlwzwyFw4klZ+khcAhmGn8So7rH2yKD5nMjNA2" crossorigin="anonymous"></script>
</body>
</html>