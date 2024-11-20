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
									<h4 style="margin-top: -20px;">Home -> Giỏ hàng -> Thêm giỏ hàng</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="container mt-0">
						<div class="row">
							<div id="recent-transactions" class="col-12">							
								<form action="${pageContext.request.contextPath}/admin/add-cart" method="post" enctype="multipart/form-data">
									<% String message = (String) request.getAttribute("message"); %>
									<% if (message != null) { %>
    								<div class="alert alert-info">
        								<%= message %>
    								</div>
									<% } %>
									<div class="mb-3">
										<h6 class="mb-0">Mã khách hàng:</h6>
										<input type="text" class="form-control" name="userId"
											placeholder="Nhập mã khách hàng" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Tên khách hàng:</h6>
										<input type="text" class="form-control" name="fullName"
											placeholder="Nhập tên khách hàng" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Tổng giá:</h6>
										<input type="number" class="form-control" name="totalPrice"
											placeholder="Nhập tổng giá" required />
									</div>
									<div class="d-flex justify-content-center mb-3">
										<button type="submit" class="btn btn-primary">Thêm giỏ hàng</button>
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