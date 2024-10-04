<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sản phẩm</title>
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
									<h4 style="margin-top: -50px;">Home -> Sản phẩm</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="row" style="margin: -60px 0 0 20px;">
						<div id="recent-transactions" class="col-12">
							<% String message = (String) request.getAttribute("message"); %>
								<% if (message != null) { %>
    								<div class="alert alert-info">
        								<%= message %>
    								</div>
							<% } %>		
							<h6 class="my-2">Các sản phẩm</h6>
							<div style="margin: -60px 0 20px 1100px;">
								<div class="btn-group me-2">
									<a class="btn btn-primary btn-sm"
										href="http://localhost:8080/OneShop/admin/add-product"
										style="font-size: 15px; padding: 8px 16px;">Thêm</a>
								</div>
								<div class="btn-group">
									<button class="btn btn-warning btn-sm" style="font-size: 15px; padding: 8px 22px;">Lọc</button>
								</div>
							</div>
							<div class="card">
								<div class="card-content">
									<div class="table-responsive">
										<div class="table-wrapper">
											<table id="recent-orders"
												class="table table-hover table-xl mb-0">
												<thead>
													<tr>
														<th class="border-top-0">ID</th>
														<th class="border-top-0">Tên sản phẩm</th>
														<th class="border-top-0">Giá</th>
														<th class="border-top-0">Số lượng</th>
														<th class="border-top-0">Hãng sản xuất</th>
														<th class="border-top-0">Ảnh</th>
														<th class="border-top-0">Mô tả</th>
														<th class="border-top-0">NSX</th>
														<th class="border-top-0">Hàng động</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="product" items="${products}">
														<tr>
															<td><a class="border-top-1">${product.productId}</a></td>
															<td><a>${product.productName}</a></td>
															<td><a>${product.price}đ</a></td>
															<td><a>${product.quantity}</a></td>
															<td><a>${product.categoryId}</a></td>
															<td><img src="../image?id=${product.productId}"
																style="width: 100px; height: 100px;"></td>
															<td style="max-width: 200px; word-wrap: break-word;">
																<a>${product.description}</a>
															</td>
															<td><a>${product.createdDate}</a></td>
															<td class="d-flex flex-column gap-0">
																<a href="http://localhost:8080/OneShop/admin/edit-product?productId=${product.productId}"
																	class="btn btn-warning btn-sm"
																	style="margin: 15px 0 25px 0;">Sửa</a>
																<button class="btn btn-danger btn-sm">Xóa</button>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>