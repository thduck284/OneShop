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
    <div class="container mt-4" style="margin: 0 0 0 -200px;">
        <div style="width: 120%; max-width: 1200px;">
            <h3 class="mb-6 text-center" style="margin: -48px 0 30px 50px;">Các sản phẩm đang bán</h3>
            <div class="table-container" style="max-height: 500px; overflow-y: auto; overflow-x: auto;">
                <table class="table table-striped" style="width: 150%;">
                    <thead>
                        <tr>
                            <th style="width: 13%;">Tên sản phẩm</th>
                            <th style="width: 8%;">Mã SP</th>
                            <th style="width: 10%;">Mã HSX</th>
                            <th style="width: 10%;">Mã cửa hàng</th>
                            <th style="width: 9%;">Ảnh</th>
                            <th style="width: 10%;">Giá</th>
                            <th style="width: 7%;">Số lượng</th>
                            <th style="width: 18%;">Mô tả</th>
                            <th style="width: 8%;">Ngày SX</th>
                            <th style="width: 10%;">Hành động</th> 
                        </tr>
                    </thead>
                    <tbody>
                    	<%
                    	User user = (User) session.getAttribute("vendorInfor");
                    						String userId = user != null ? user.getUserId() : "";
                    	%>
                        <%
                        List<models.Product> products = (List<models.Product>) request.getAttribute("products");
                        if (products != null) {
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
                                         alt="Product Image" style="width:100px; height:100px;" />
                                <% } else { %>
                                    Không có ảnh
                                <% } %>
                            </td>
                            <td>
                                <fmt:formatNumber value="<%= product.getPrice() %>" type="number" pattern="#,##0" /> đ
                            </td>
                            <td><%= product.getQuantity() %></td>
                            <td><%= product.getDescription() %></td>
                            <td><%= product.getCreatedDate() != null 
       							? new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(product.getCreatedDate()) 
       							: "Không có ngày tạo" %></td>
                            <td class="d-flex flex-column align-items-center">
                                <a href="<%=request.getContextPath()%>/vendor/edit-product?productId=<%=product.getProductId()%>&userId=<%=userId%>"
									style="margin-top: 10px; font-weight: bold;" class="btn btn-warning btn-sm mb-1">Sửa</a>
								<form action="<%= request.getContextPath() %>/vendor/delete-product" method="post" style="display:inline;">
                                    <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm"
                                        style="color: black; margin: 15px 0 6px 0; font-weight: bold;" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này không?');">Xóa</button>
                                </form>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="9" class="text-center">Không có dữ liệu</td>
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
