<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="models.Category" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Giỏ hàng</title>
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
									<h4 style="margin-top: -50px;">Home -> Giỏ hàng</h4>
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
							<h6 class="my-2">Các giỏ hàng</h6>
							<div style="margin: -60px 0 20px 970px;">
								<div class="btn-group me-2"> 
    								<a href="http://localhost:8080/OneShop/admin/cart-detail" class="btn btn-success btn-sm" 
        								style="font-size: 15px; padding: 8px 16px;">Xem chi tiết</a>
								</div>
								<div class="btn-group me-2">
									<a class="btn btn-primary btn-sm"
										href="http://localhost:8080/OneShop/admin/add-cart"
										style="font-size: 15px; padding: 8px 16px;">Thêm</a>
								</div>
								<div class="btn-group">
									<a href="http://localhost:8080/OneShop/admin/cart" class="btn btn-warning btn-sm" 
										style="font-size: 15px; padding: 8px 22px;">Lọc</a>
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
														<th class="border-top-0" style="width: 15%;">Mã giỏ hàng</th>
														<th class="border-top-0" style="width: 15%;">Mã khách hàng</th>
														<th class="border-top-0" style="width: 20%;">Tên khách hàng</th>
														<th class="border-top-0" style="width: 15%;">Tổng giá</th>
														<th class="border-top-0" style="width: 20%;">Ngày tạo</th>
														<th class="border-top-0">Hàng động</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="cart" items="${carts}">
														<tr>
															<td><a class="border-top-1">${cart.cartId}</a></td>
															<td><a>${cart.userId}</a></td>
															<td><a>${cart.userName}</a></td>
															<td><a>${cart.totalPrice}</a></td>
															<td>${cart.formattedCreatedDate}</td>
															<td class="d-flex flex-column gap-0">
																<a href="${pageContext.request.contextPath}/admin/edit-cart?cartId=${cart.cartId}"
																	class="btn btn-warning btn-sm"
																	style="margin: 0 0 15px 0;">Sửa</a>
																<form action="${pageContext.request.contextPath}/admin/delete-cart" 
																	method="POST" style="display: inline;" onsubmit="return confirmDelete();">
																	<input type="hidden" name="cartId" value="${cart.cartId}">
																	<button type="submit" class="btn btn-danger btn-sm" style="padding: 7px 52px;">Xóa</button>
																</form>
																<script>
																	function confirmDelete() {
																		return confirm("Bạn có chắc chắn muốn xóa giỏ hàng này không?");
																	}
																</script>
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