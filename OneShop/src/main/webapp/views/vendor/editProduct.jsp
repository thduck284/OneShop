<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ page import="models.Product" %>	
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Trang chủ</title> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" integrity="sha384-1CmrxMRARb6aLqgBO7VVf0SzP+UlwzwyFw4klZ+khcAhmGn8So7rH2yKD5nMjNA2" crossorigin="anonymous">
</head>
<body>
	<div class="container mt-4 d-flex justify-content-center" style="margin: 0 0 0 -80px;">
		<div style="width: 100%; max-width: 900px;">
			<% String message = (String) request.getAttribute("message"); %>
	        <% if (message != null) { %>
	            <div class="alert alert-success" role="alert">
	                <%= message %>
	            </div>
	        <% } %>
			<h3 class="mb-4 text-center" style="margin: -48px 0 10px 0; font-weight: bold;">Chỉnh sửa thông tin sản phẩm</h3>
			<%
    			Product product = (Product) request.getAttribute("product");
			%>	
			<form action="${pageContext.request.contextPath}/vendor/edit-product"
				method="post" enctype="multipart/form-data">
				<div class="row mb-5">
					<div class="col-md-6">
						<label for="shopId" class="form-label">Mã cửa hàng:</label> 
						<select class="form-select" id="shopId" name="shopId" required>
							<option disabled>Chọn mã cửa hàng</option>
							<c:forEach var="shopId" items="${listShopId}">
								<option value="${shopId}" ${shopId == product.shopId ? "selected" : ""}>${shopId}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-6">
						<label for="categoryId" class="form-label">Mã danh mục sản xuất:</label> 
						<select class="form-select" id="categoryId" name="categoryId" required>
							<option disabled>Chọn mã hãng sản xuất</option>
							<c:forEach var="categoryId" items="${listCategoryId}">
								<option value="${categoryId}" ${categoryId == product.categoryId ? "selected" : ""}>${categoryId}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-md-12">
						<label for="productName" class="form-label">Tên sản phẩm:</label>
						<input type="text" class="form-control" id="productName" name="productName" value="${product.productName}" required>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-md-6">
						<label for="price" class="form-label">Giá:</label> 
						<input type="number" class="form-control" id="price" name="price" value="${product.price}" required>
					</div>
					<div class="col-md-6">
						<label for="quantity" class="form-label">Số lượng:</label> 
						<input type="number" class="form-control" id="quantity" name="quantity" value="${product.quantity}" required>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-md-12">
						<label for="description" class="form-label">Mô tả sản phẩm:</label>
						<textarea class="form-control" id="description" name="description" rows="3" required>${product.description}</textarea>
					</div>
				</div>
				<div class="mb-3">
					<label for="image" class="form-label">Ảnh:</label>
				 	<input type="file" class="form-control" name="image" />
 					<% if (product != null && product.getImage() != null) { %>
    					<img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(product.getImage()) %>" 
         					alt="Product Image" style="width:100px; height:100px; margin-top: 10px;" />
					<% } %>
				</div>
				<div class="row mb-3">
					<div class="col-md-12">
						<label for="createdDate" class="form-label">Ngày sản xuất:</label> 
						<input type="date" class="form-control" id="createdDate" name="createdDate" value="${product.createdDate}" required>
					</div>
				</div>
				<div class="d-flex">
					<button type="submit" class="btn btn-primary" style="margin: 20px 0 0 400px; color: black; font-weight: bold;">Cập nhật</button>
				</div>
			</form>
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7VVf0SzP+UlwzwyFw4klZ+khcAhmGn8So7rH2yKD5nMjNA2" crossorigin="anonymous"></script>
</body>
</html>
