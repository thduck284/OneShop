<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Base64" %>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="models.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách cửa hàng</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container" style="margin: 0 0 0 -120px;">
        <h3 class="text-center mb-4" style="font-weight: bold;">Các sản phẩm đang bán</h3>
        <form action="${pageContext.request.contextPath}/vendor/search-product" method="GET" style="margin: 20px 0;">
		    <input type="text" name="searchQuery" placeholder="Tìm kiếm sản phẩm (theo tên sản phẩm, mô tả)..." class="form-control" style="margin: 15px 0 0 70px; width: 700px; display: inline-block;">
		    <button type="submit" class="btn btn-success" style="background-color: #1EC481; font-size: 15px; color: black; margin-top: -5px; display: inline-block;">Tìm kiếm</button>
		</form>
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover align-middle text-center" style="width: 1200px; max-height: 400px;">
                <thead class="table-dark">
                    <tr>
                        <th>Tên sản phẩm</th>
                        <th>Mã SP</th>
                        <th>Mã HSX</th>
                        <th>Mã cửa hàng</th>
                        <th>Ảnh</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Mô tả</th>
                        <th>Ngày SX</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    User user = (User) session.getAttribute("vendorInfor");
                    String userId = user != null ? user.getUserId() : "";
                    List<models.Product> products = (List<models.Product>) request.getAttribute("products");
                    if (products != null && !products.isEmpty()) {
                        for (models.Product product : products) {
                            String base64Image = "";
                            if (product.getImage() != null) {
                                base64Image = Base64.getEncoder().encodeToString(product.getImage());
                            }
                    %>
                    <tr>
                        <td><%= product.getProductName() %></td>
                        <td><%= product.getProductId() %></td>
                        <td><%= product.getCategoryId() %></td>
                        <td><%= product.getShopId() %></td>
                        <td>
                            <% if (!base64Image.isEmpty()) { %>
                                <img src="data:image/jpeg;base64,<%= base64Image %>" 
                                     alt="Product Image" class="img-thumbnail" style="width: 300px; height: 100px;">
                            <% } else { %>
                                Không có ảnh
                            <% } %>
                        </td>
                        <td><fmt:formatNumber value="<%= product.getPrice() %>" type="number" pattern="#,##0" /> đ</td>
                        <td><%= product.getQuantity() %></td>
                        <td><%= product.getDescription() %></td>
                        <td><%= product.getCreatedDate() != null 
                            ? new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(product.getCreatedDate()) 
                            : "Không có ngày tạo" %></td>
                        <td>
                            <a href="<%=request.getContextPath()%>/vendor/edit-product?productId=<%=product.getProductId()%>&userId=<%=userId%>"
                               class="btn btn-warning btn-sm" style="background-color: yellow;">
                                <i class="bi bi-pencil-square"></i> Sửa
                            </a>
                            <form action="<%= request.getContextPath() %>/vendor/delete-product" method="post" style="display:inline;">
                                <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                                <button type="submit" class="btn btn-danger btn-sm" style="background-color: red;"
                                    onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này không?');">
                                    <i class="bi bi-trash-fill"></i> Xóa
                                </button>
                            </form>
                        </td>
                    </tr>
                    <% 
                        }
                    } else { 
                    %>
                    <tr>
                        <td colspan="10" class="text-center">Không có dữ liệu</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
        $(document).ready(function () {
            $("form").submit(function (event) {
                event.preventDefault();
                var searchQuery = $("input[name='searchQuery']").val();

                $.ajax({
                    url: '${pageContext.request.contextPath}/vendor/search-product',
                    method: 'GET',
                    data: { searchQuery: searchQuery },
                    success: function (response) {
                        var tbody = $("table tbody");
                        tbody.empty();

                        if (Array.isArray(response) && response.length > 0) {
                            $.each(response, function (index, product) {
                                var row = $("<tr>");
                                row.append($("<td>").text(product.productName));
                                row.append($("<td>").text(product.productId));
                                row.append($("<td>").text(product.categoryId));
                                row.append($("<td>").text(product.shopId));
                                row.append($("<td>").html(product.image ? 
                                	    "<img src='data:image/jpeg;base64," + product.image + "' class='img-thumbnail' style='width: 130px; height: 100px;'>" : 
                                	    "Không có ảnh"));
                                row.append($("<td>").text(product.price));
                                row.append($("<td>").text(product.quantity));
                                row.append($("<td>").text(product.description));
                                row.append($("<td>").text(product.formattedCreatedDate));
                                var actions = $("<td>").addClass("text-center");                            
                                actions.append('<a href="${pageContext.request.contextPath}/vendor/edit-product?productId=' + product.productId + '" class="btn btn-warning btn-sm" style="margin-right: 5px; background-color: #ffc107;">Sửa</a>');
	                            actions.append('<form action="${pageContext.request.contextPath}/vendor/delete-product" method="POST" style="display: inline;" onsubmit="return confirm(\'Bạn có chắc chắn muốn xóa sản phẩm này không?\');">\n' +
	                                '<input type="hidden" name="productId" value="' + product.productId + '">\n' +  
	                                '<button type="submit" class="btn btn-danger btn-sm" style="background-color: #dc3545;">Xóa</button>\n' +
	                                '</form>');
	                                row.append(actions);
	                                tbody.append(row);
                            });
                        } else {
                            tbody.append("<tr><td colspan='10' class='text-center'>Không tìm thấy sản phẩm nào.</td></tr>");
                        }
                    },
                    error: function (xhr, status, error) {
                        console.error("Lỗi AJAX: ", error);
                        alert("Có lỗi xảy ra khi tìm kiếm.");
                    }
                });
            });
        });
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css"></script>
    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7VVf0SzP+UlwzwyFw4klZ+khcAhmGn8So7rH2yKD5nMjNA2" crossorigin="anonymous"></script> -->
</body>
</html>
