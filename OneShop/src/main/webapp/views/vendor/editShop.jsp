<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="models.Shop" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Trang chủ</title> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4 d-flex justify-content-center" style="margin: 0 0 0 -80px;">
        <div style="width: 90%; max-width: 900px;">
        	<% String message = (String) request.getAttribute("message"); %>
	        <% if (message != null) { %>
	            <div class="alert alert-success" role="alert">
	                <%= message %>
	            </div>
	        <% } %>
            <h3 class="mb-4 text-center" style="font-weight: bold;">Thông tin cửa hàng</h3>

            <%
                Shop shop = (Shop) request.getAttribute("shop");
                String userId = shop != null ? shop.getUserId() : "";
                String shopName = shop != null ? shop.getShopName() : "";
                String description = shop != null ? shop.getDescription() : "";
                String status = shop != null ? shop.getStatus() : "";
            %>

            <form action="${pageContext.request.contextPath}/vendor/edit-shop" method="post" enctype="multipart/form-data">
                <div class="row mb-5">
                    <div class="col-md-6">
                        <label for="shopName" class="form-label">Mã chủ cửa hàng:</label> 
                        <strong class="form-control" style="display: block; padding: 0.375rem 0.75rem; border: 1px solid #ced4da; border-radius: 0.25rem; background-color: #e9ecef; color: #495057;"> 
                            <%= userId %> 
                        </strong>
                        <input type="hidden" name="userId" value="<%= userId %>">
                    </div>
                    <div class="col-md-6">
                        <label for="status" class="form-label">Trạng thái:</label>
                        <select class="form-select" id="status" name="status" required style="width: 100%;">
                            <option disabled>Chọn trạng thái</option>
                            <option value="active" <%= "active".equals(status) ? "selected" : "" %>>Hoạt động</option>
                            <option value="inactive" <%= "inactive".equals(status) ? "selected" : "" %>>Không hoạt động</option>
                        </select>
                    </div>
                </div>
                <div class="row mb-5">
                    <div class="col-md-12">
                        <label for="shopName" class="form-label">Tên cửa hàng:</label>
                        <input type="text" class="form-control" id="shopName" name="shopName" value="<%= shopName %>"
                            rows="1" placeholder="Nhập tên cửa hàng" required style="width: 100%;">
                    </div>
                </div>
                <div class="row mb-5">
                    <div class="col-md-12">
                        <label for="description" class="form-label">Mô tả:</label>
                        <input type="text" class="form-control" id="description" name="description"
                            value="<%= description %>" placeholder="Mô tả về cửa hàng (nếu muốn)" required style="width: 100%;">
                    </div>
                </div>
                <div class="d-flex justify-content-end" style="transform: translateX(-370px);">
                    <button type="submit" class="btn btn-primary mt-3" style=" background-color: #3498db;">Lưu
                        thông tin</button>
                </div>
            </form>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
