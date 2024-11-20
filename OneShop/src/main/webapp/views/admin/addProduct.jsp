<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sản phẩm</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
									<h4 style="margin-top: -20px;">Home -> Sản phẩm -> Thêm sản phẩm</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="container mt-0">
						<div class="row">
							<div id="recent-transactions" class="col-12">							
								<form action="${pageContext.request.contextPath}/admin/add-product" method="post" enctype="multipart/form-data">
									<% String message = (String) request.getAttribute("message"); %>
									<% if (message != null) { %>
    								<div class="alert alert-info">
        								<%= message %>
    								</div>
									<% } %>
									<div class="mb-3">
										<h6 class="mb-0">Mã hãng sản xuất:</h6>
										<select class="form-control" name="categoryId" required>
											<option value="">Chọn hãng sản xuất</option>
											<% 
    											List<String> listCategoryId = (List<String>) request.getAttribute("listCategoryId");
    											if (listCategoryId != null && !listCategoryId.isEmpty()) { 
        											for (String categoryId : listCategoryId) {
											%>
            											<option value="<%= categoryId %>"><%= categoryId %></option>
											<% 
        											} 
    											} else { 
											%>
    											<option value="">Không có hãng sản xuất nào</option>
											<% 
    											}
											%>
										</select>
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Mã cửa hàng:</h6>
										<select class="form-control" name="shopId" required>
											<option value="">Chọn mã cửa hàng</option>
											<% 
    											List<String> listShopId = (List<String>) request.getAttribute("listShopId");
    											if (listShopId != null && !listShopId.isEmpty()) { 
        											for (String shopId : listShopId) {
											%>
            											<option value="<%= shopId %>"><%= shopId %></option>
											<% 
        											} 
    											} else { 
											%>
    											<option value="">Không có mã cửa hàng nào</option>
											<% 
    											}
											%>
										</select>
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Tên sản phẩm:</h6>
										<input type="text" class="form-control" name="productName"
											placeholder="Nhập tên sản phẩm" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Ảnh:</h6>
										<input type="file" class="form-control" name="image" />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Giá:</h6>
										<input type="number" class="form-control" name="price"
											placeholder="Nhập giá sản phẩm" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Số lượng:</h6>
										<input type="number" class="form-control" name="quantity"
											placeholder="Nhập số lượng" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Mô tả:</h6>
										<input type="text" class="form-control" name="description"
											placeholder="Mô tả sản phẩm" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Ngày sản xuất:</h6>
										<input type="date" class="form-control" name="createdDate"
											required />
									</div>
									<div class="d-flex justify-content-center mb-3">
										<button type="submit" class="btn btn-primary">Thêm
											sản phẩm</button>
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