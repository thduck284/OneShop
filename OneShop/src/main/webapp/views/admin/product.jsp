<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title> 
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
							<form action="${pageContext.request.contextPath}/admin/search-product" method="GET" style="margin: 20px 0;">
							    <input type="text" name="searchQuery" placeholder="Tìm sản phẩm theo tên, danh mục..." class="form-control" style="margin: 15px 0 0 100px; width: 800px; display: inline-block;">
							    <button type="submit" class="btn btn-success" style="font-size: 15px; color: black; margin-top: -5px; display: inline-block;">Tìm kiếm</button>
							</form>					
							<div style="margin: -62px 0 20px 1050px;">
								<div class="btn-group me-2">
									<a class="btn btn-primary btn-sm"
										href="http://localhost:8080/OneShop/admin/add-product"
										style="font-size: 15px; padding: 12px 22px;">Thêm</a>
								</div>
								<div class="btn-group">
									<a href="http://localhost:8080/OneShop/admin/product" class="btn btn-warning btn-sm" 
										style="font-size: 15px; padding: 12px 28px;">Lọc</a>
								</div>
							</div>
							<div class="table-responsive" style="max-height: 500px; overflow-y: auto;  max-width: 1300px;">
								<table id="recent-orders"
									class="table table-striped table-bordered table-hover align-middle text-center" style="border: 1px solid black;">
									<thead style="background-color: black; color: white;">
										<tr>
											<th style="width: 20px;">ID</th>
											<th>Tên sản phẩm</th>
											<th>Giá</th>
											<th>Số lượng</th>
											<th>Ảnh</th>
											<th>Hãng sản xuất</th>
											<th>Mã cửa hàng </th>
											<th style="width: 200px;">Mô tả</th>
											<th>NSX</th>
											<th></th>
										</tr>
									</thead>
									<tbody style="border: 2px solid gray;">
										<c:forEach var="product" items="${products}">
											<tr>
												<td style="border: 2px solid gray;">${product.productId}</td>
												<td style="border: 2px solid gray;">${product.productName}</td>
												<td style="border: 2px solid gray;"><fmt:formatNumber value="${product.price}" type="number" pattern="#,###"/> đ</td>
												<td style="border: 2px solid gray;">${product.quantity}</td>
												<td style="border: 2px solid gray;">
                                                    <c:if test="${not empty product.image}">
                                                        <img src="data:image/jpeg;base64,${fn:escapeXml(Base64.getEncoder().encodeToString(product.image))}" 
                                                             alt="Product Image" style="width:100px; height:100px;" />
                                                    </c:if>
                                                </td>
                                                <td style="border: 2px solid gray;">${product.categoryId}</td>
                                                <td style="border: 2px solid gray;">${product.shopId}</td>
												<td style="border: 2px solid black; word-wrap: break-word;">${product.description}</td>
												<td style="border: 2px solid gray;">${product.createdDate}</td>
												<td class="d-flex flex-column gap-0">
													<a href="${pageContext.request.contextPath}/admin/edit-product?productId=${product.productId}"
														class="btn btn-warning btn-sm"
														style="margin: 15px 0 25px 0;">Sửa</a>
													<form action="${pageContext.request.contextPath}/admin/delete-product" 
														method="POST" style="display: inline;" onsubmit="return confirmDelete();">
														<input type="hidden" name="productId" value="${product.productId}">
														<button type="submit" class="btn btn-danger btn-sm" style="padding: 10px 25px;">Xóa</button>
													</form>
													<script>
														function confirmDelete() {
															return confirm("Bạn có chắc chắn muốn xóa sản phẩm này không?");
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
                    url: '${pageContext.request.contextPath}/admin/search-product',
                    method: 'GET',
                    data: { searchQuery: searchQuery },
                    success: function(response) {
                        console.log(response);  
                        var tbody = $("#recent-orders tbody");
                        tbody.empty();
                        if (Array.isArray(response) && response.length > 0) {
                            $.each(response, function(index, product) {
                                var row = $("<tr>");
                                row.append($("<td>").text(product.productId).css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
                                row.append($("<td>").text(product.productName).css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
                                row.append($("<td>").text(new Intl.NumberFormat().format(product.price) + ' đ').css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
                                row.append($("<td>").text(product.quantity).css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
                                row.append($("<td>").html('<img src="data:image/jpeg;base64,' + product.image + '" style="width:100px; height:100px;">').css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
                                row.append($("<td>").text(product.categoryId).css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
                                row.append($("<td>").text(product.shopId).css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
                                row.append($("<td>").text(product.description).css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
                                row.append($("<td>").text(new Date(product.createdDate).toLocaleDateString()).css({
	                                "border": "2px solid gray",
	                                "padding": "8px"
	                            }));
                                var actions = $("<td>").addClass("d-flex flex-column gap-0");
                                actions.append('<a href="${pageContext.request.contextPath}/admin/edit-product?productId=' + product.productId + '" class="btn btn-warning btn-sm" style="margin: 15px 0 25px 0;">Sửa</a>');
                                actions.append('<form action="${pageContext.request.contextPath}/admin/delete-product" method="POST" style="display: inline;" onsubmit="return confirmDelete();"><input type="hidden" name="productId" value="' + product.productId + '"><button type="submit" class="btn btn-danger btn-sm" style="padding: 7px 50px;">Xóa</button></form>');

                                row.append(actions);
                                tbody.append(row);
                            });
                        } else {
                            tbody.append("<tr><td colspan='10'>Không tìm thấy sản phẩm nào.</td></tr>");
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