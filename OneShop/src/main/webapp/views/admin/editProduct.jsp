<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật sản phẩm</title>
<link rel="stylesheet" type="text/css" href="../static/styles/admin/assets/css/product.css">
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
									<h4 style="margin-top: -20px;">Home -> Sản phẩm -> Cập nhật sản phẩm</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="container mt-0">
						<div class="row">
							<div id="recent-transactions" class="col-12">
								<% String message = (String) request.getAttribute("message"); %>
								<% if (message != null) { %>
    								<div class="alert alert-info">
        								<%= message %>
    								</div>
								<% } %>
								<%@ page import="models.Product" %>	
								<%@ page import="java.util.Base64" %>	
								<%
    								Product product = (Product) request.getAttribute("product");
								%>														
								<form action="${pageContext.request.contextPath}/admin/edit-product" method="post" enctype="multipart/form-data">
									<div class="mb-3">
										<h6 class="mb-0">Mã hãng sản xuất:</h6>
										<input type="text" class="form-control" name="categoryId"
											value="<%= product.getCategoryId() %>" placeholder="Nhập mã hãng sản xuất" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Mã cửa hàng:</h6>
										<input type="text" class="form-control" name="shopId"
											value="<%= product.getShopId() %>" placeholder="Nhập mã cửa hàng buôn bán sản phẩm" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Tên sản phẩm:</h6>
										<input type="text" class="form-control" name="productName"
											value="<%= product.getProductName() %>" placeholder="Nhập tên sản phẩm" required />
									</div>
									<div class="mb-3">
       									<h6 class="mb-0">Ảnh:</h6>
       				 					<input type="file" class="form-control" name="image" />
        								<% if (product.getImage() != null) { %>
            								<img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(product.getImage()) %>" 
                 								alt="Product Image" style="width:100px; height:100px;" />
       									<% } %>
    								</div>
									<div class="mb-3">
										<h6 class="mb-0">Giá:</h6>
										<input type="number" class="form-control" name="price"
											value="<%= product.getPrice() %>" placeholder="Nhập giá sản phẩm" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Số lượng:</h6>
										<input type="number" class="form-control" name="quantity"
											value="<%= product.getQuantity() %>" placeholder="Nhập số lượng" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Mô tả:</h6>
										<input type="text" class="form-control" name="description"
											value="<%= product.getDescription() %>" placeholder="Mô tả sản phẩm" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Ngày sản xuất:</h6>
										<input type="date" class="form-control" name="createdDate"
											value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(product.getCreatedDate())%>" required />
									</div>
									<div class="d-flex justify-content-center mb-3">
										<button type="submit" class="btn btn-primary">Cập nhật sản phẩm</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>