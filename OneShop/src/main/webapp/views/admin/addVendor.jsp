<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm quản lý</title>
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
									<h4 style="margin-top: -20px;">Home -> Người bán -> Thêm người bán</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="container mt-0">
						<div class="row">
							<div id="recent-transactions" class="col-12">
								<form
									action="${pageContext.request.contextPath}/admin/add-vendor"
									method="post" enctype="multipart/form-data">
									<% String message = (String) request.getAttribute("message"); %>
									<% if (message != null) { %>
    								<div class="alert alert-info">
        								<%= message %>
    								</div>
									<% } %>
									<div class="mb-3">
										<h6 class="mb-0">Tên chủ cửa hàng:</h6>
										<input type="text" class="form-control" name="fullName"
											placeholder="Nhập tên chủ cửa hàng" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Số điện thoại:</h6>
										<input type="text" class="form-control" name="phone"
											placeholder="Nhập số điện thoại" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Địa chỉ:</h6>
										<input type="text" class="form-control" name="address"
											placeholder="Nhập địa chỉ" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Email:</h6>
										<input type="text" class="form-control" name="email"
											placeholder="Nhập email" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Tên tài khoản:</h6>
										<input type="text" class="form-control" name="userName"
											placeholder="Nhập tên tài khoản" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Mật khẩu:</h6>
										<input type="password" class="form-control" name="password"
											placeholder="Nhập mật khẩu" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Xác nhận mật khẩu:</h6>
										<input type="password" class="form-control"
											name="confirm-password" placeholder="Nhập lại mật khẩu"
											required />
									</div>
									<div class="d-flex justify-content-center mb-3">
										<button type="submit" class="btn btn-primary">Thêm
											người bán</button>
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