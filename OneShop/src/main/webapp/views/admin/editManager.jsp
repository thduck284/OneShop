<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa quản lý</title>
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
									<h4 style="margin-top: -20px;">Home -> Quản lý -> Cập nhật thông tin quản lý</h4>
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
								<%@ page import="models.User" %>		
								<%
    								User manager = (User) request.getAttribute("user");
								%>						
								<form action="${pageContext.request.contextPath}/admin/edit-manager" method="post" enctype="multipart/form-data">
									<div class="mb-3">
										<h6 class="mb-0">Tên quản lý:</h6>
										<input type="text" class="form-control" name="fullName"
											value="<%= manager.getFullName() %>" placeholder="Nhập tên quản lý" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Số điện thoại:</h6>
										<input type="text" class="form-control" name="phone"
											value="<%= manager.getPhoneNumber() %>" placeholder="Nhập số điện thoại quản lý" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Địa chỉ:</h6>
										<input type="text" class="form-control" name="address"
											value="<%= manager.getAddress() %>" placeholder="Nhập địa chỉ quản lý" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Email:</h6>
										<input type="text" class="form-control" name="email"
											value="<%= manager.getEmail() %>" placeholder="Nhập email quản lý" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Tên tài khoản:</h6>
										<input type="text" class="form-control" name="userName"
											value="<%= manager.getUserName() %>" placeholder="Nhập tên tài khoản quản lý" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Mật khẩu:</h6>
										<input type="text" class="form-control" name="password"
											value="<%= manager.getPassword() %>" placeholder="Nhập mật khẩu quản lý" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Xác nhận mật khẩu:</h6>
										<input type="text" class="form-control" name="confirm-password"
											value="<%= manager.getPassword() %>" placeholder="Xác nhận mật khẩu" required />
									</div>
									<div class="d-flex justify-content-center mb-3">
										<button type="submit" class="btn btn-primary">Cập nhật</button>
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