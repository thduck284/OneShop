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
							<form action="${pageContext.request.contextPath}/admin/search-cart" method="GET" style="margin: 20px 0;">
							    <input type="text" name="searchQuery" placeholder="Tìm kiếm giỏ hàng..." class="form-control" style="margin: 15px 0 0 100px; width: 700px; display: inline-block;">
							    <button type="submit" class="btn btn-success" style="font-size: 15px; color: black; margin-top: -5px; display: inline-block;">Tìm kiếm</button>
							</form>	
							<div style="margin: -62px 0 20px 930px;">
								<div class="btn-group me-2"> 
    								<a href="http://localhost:8080/OneShop/admin/cart-detail" class="btn btn-success btn-sm" 
        								style="font-size: 15px; padding: 12px 22px;">Xem chi tiết</a>
								</div>
								<div class="btn-group me-2">
									<a class="btn btn-primary btn-sm"
										href="http://localhost:8080/OneShop/admin/add-cart"
										style="font-size: 15px; padding: 12px 22px;">Thêm</a>
								</div>
								<div class="btn-group">
									<a href="http://localhost:8080/OneShop/admin/cart" class="btn btn-warning btn-sm" 
										style="font-size: 15px; padding: 12px 28px;">Lọc</a>
								</div>
							</div>
							<div class="table-responsive" style="max-height: 500px; overflow-y: auto;  max-width: 1300px;">
								<table id="recent-orders"
									class="table table-striped table-bordered table-hover align-middle text-center" style="border: 1px solid black;">
									<thead style="background-color: black; color: white;">
										<tr>
											<th style="width: 20px;">Mã GH</th>
											<th>Mã khách hàng</th>
											<th>Tên khách hàng</th>
											<th>Tổng giá</th>
											<th>Ngày tạo</th>
											<th>Trạng thái</th>
											<th style="width: 100px;"></th>
										</tr>
									</thead>
									<tbody style="border: 2px solid gray;">
										<c:forEach var="cart" items="${carts}">
											<tr>
												<td style="border: 2px solid gray;">${cart.cartId}</td>
												<td style="border: 2px solid gray;">${cart.userId}</td>
												<td style="border: 2px solid gray;">${cart.fullName}</td>
												<td style="border: 2px solid gray;">${cart.totalPrice}</td>
												<td style="border: 2px solid gray;">${cart.formattedCreatedDate}</td>
												<td style="border: 2px solid gray;">
									                ${cart.status == true ? 'Đã thanh toán' : 'Chưa thanh toán'}
									            </td>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
	    $(document).ready(function(){
	        $("form").submit(function(event){
	            event.preventDefault();
	            var searchQuery = $("input[name='searchQuery']").val(); 
	            $.ajax({
	                url: '${pageContext.request.contextPath}/admin/search-cart',
	                method: 'GET',
	                data: { searchQuery: searchQuery },
	                success: function(response) {
	                    console.log(response);  
	                    var tbody = $("#recent-orders tbody");
	                    tbody.empty();
	                    if (Array.isArray(response) && response.length > 0) {
	                        $.each(response, function(index, cart) {
	                            var row = $("<tr>");
	                            row.append($("<td>").text(cart.cartId).css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
	                            row.append($("<td>").text(cart.userId).css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
	                            row.append($("<td>").text(cart.fullName).css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
	                            row.append($("<td>").text(cart.totalPrice).css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
	                            row.append($("<td>").text(cart.createdDate).css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
	                            row.append($("<td>").html(cart.status == true ? 'Đã thanh toán' : 'Chưa thanh toán').css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
	                            var actions = $("<td>").addClass("d-flex flex-column gap-0");
	                            actions.append('<a href="${pageContext.request.contextPath}/admin/edit-cart?cartId=' + cart.cartId + '" class="btn btn-warning btn-sm" style="margin: 0 0 15px 0;">Sửa</a>');
	                            actions.append('<form action="${pageContext.request.contextPath}/admin/delete-cart" method="POST" style="display: inline;" onsubmit="return confirmDelete();"><input type="hidden" name="cartId" value="' + cart.cartId + '"><button type="submit" class="btn btn-danger btn-sm" style="padding: 7px 52px;">Xóa</button></form>');
	                            row.append(actions);
	                            tbody.append(row);
	                        });
	                    } else {
	                        tbody.append("<tr><td colspan='7'>Không tìm thấy giỏ hàng nào.</td></tr>");
	                    }
	                },
	                error: function(xhr, status, error) {
	                    console.error("Lỗi AJAX: ", error);  
	                    alert('Có lỗi xảy ra khi tìm kiếm.');
	                }
	            });
	        });
	    });
	</script>
</body>
</html>
