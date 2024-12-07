<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
									<h4 style="margin-top: -20px;">Home -> Giỏ hàng -> Chi tiết giỏ hàng -> Cập nhật</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="container mt-0">
						<div class="row">
							<div id="recent-transactions" class="col-12">
								<%
								String message = (String) request.getAttribute("message");
								%>
								<%
								if (message != null) {
								%>
    								<div class="alert alert-info">
        								<%=message%>
    								</div>
								<%
								}
								%>
								<%@ page import="models.CartDetail" %>	
								<%
									CartDetail cartDetail = (CartDetail) request.getAttribute("cartDetail");
								%>						
								<form action="${pageContext.request.contextPath}/admin/edit-cart" method="post" enctype="multipart/form-data">
									<div class="mb-3">
										<h6 class="mb-0">Mã sản phẩm:</h6>
										<input type="text" class="form-control" name="productId"
											value="<%= cartDetail.getProductId() %>" placeholder="Nhập mã sản phẩm" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Tên sản phẩm:</h6>
										<input type="text" class="form-control" name="productName"
											value="<%= cartDetail.getProductName() %>" placeholder="Nhập tên sản phẩm" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Số lượng:</h6>
										<input type="number" class="form-control" name="quantity"
											value="<%= cartDetail.getQuantity() %>" placeholder="Nhập số lượng" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Giá:</h6>
										<input type="number" class="form-control" name="price"
											value="<%= cartDetail.getPrice() %>" placeholder="Nhập giá sản phẩm" required />
									</div>
									<div class="d-flex justify-content-center mb-3">
										<button type="submit" class="btn btn-primary">Cập nhật CTGH</button>
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