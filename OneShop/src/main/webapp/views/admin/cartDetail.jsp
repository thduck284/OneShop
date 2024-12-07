<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
									<h4 style="margin-top: -50px;">Home -> Giỏ hàng -> Chi tiết giỏ hàng</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="row" style="margin: -60px 0 0 80px;">
						<div id="recent-transactions" class="col-12">
							<% String message = (String) request.getAttribute("message"); %>
								<% if (message != null) { %>
    								<div class="alert alert-info">
        								<%= message %>
    								</div>
							<% } %>		
							<h6 class="my-2">Chi tiết giỏ hàng</h6>
							<div style="margin: -60px 0 20px 970px;">
								<div class="btn-group me-2">
									<a class="btn btn-primary btn-sm"
										href="http://localhost:8080/OneShop/admin/add-cart-detail"
										style="font-size: 15px; padding: 8px 16px;">Thêm</a>
								</div>
								<div class="btn-group">
									<a href="http://localhost:8080/OneShop/admin/cart-detail" class="btn btn-warning btn-sm" 
										style="font-size: 15px; padding: 8px 22px;">Lọc</a>
								</div>
							</div>
							<div class="table-responsive" style="max-height: 500px; overflow-y: auto;  max-width: 1300px;">
								<table id="recent-orders"
									class="table table-striped table-bordered table-hover align-middle text-center" style="border: 1px solid black;">
									<thead style="background-color: black; color: white;">
										<tr>
											<th>Mã giỏ hàng</th>
											<th>Mã sản phẩm</th>
											<th>Tên sản phẩm</th>
											<th>Số lượng</th>
											<th>Giá</th>
											<th>Trạng thái</th>
											<th style="width: 100px;"></th>
										</tr>
									</thead>
									<tbody style="border: 2px solid gray;">
										<c:forEach var="dCart" items="${cartDetail}">
											<tr>
												<td style="border: 2px solid gray;">${dCart.cartId}</td>
												<td style="border: 2px solid gray;">${dCart.productId}</td>
												<td style="border: 2px solid gray;">${dCart.productName}</td>
												<td style="border: 2px solid gray;">${dCart.quantity}</td>
												<td style="border: 2px solid gray;">${dCart.price}</td>
												<td style="border: 2px solid gray;">
									                ${dCart.status == true ? 'Đã thanh toán' : 'Chưa thanh toán'}
									            </td>
												<td class="d-flex flex-column gap-0">
													<a href="${pageContext.request.contextPath}/admin/edit-cart-detail?cartId=${dCart.cartId}&productId=${dCart.productId}&status=${dCart.status}"
														class="btn btn-warning btn-sm"
														style="margin: 0 0 15px 0;">Sửa</a>
													<form action="${pageContext.request.contextPath}/admin/delete-cart-detail" 
														method="POST" style="display: inline;" onsubmit="return confirmDelete();">
														<input type="hidden" name="cartId" value="${dCart.cartId}">
 																<input type="hidden" name="productId" value="${dCart.productId}">
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
</body>
</html>