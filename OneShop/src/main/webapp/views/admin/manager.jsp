<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý</title>
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
									<h4 style="margin-top: -50px;">Home -> Quản lý</h4>
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
							<h6 class="my-2">Các quản lý</h6>
							<div style="margin: -60px 0 20px 1100px;">
								<div class="btn-group me-2">
									<a class="btn btn-primary btn-sm"
										href="http://localhost:8080/OneShop/admin/add-manager"
										style="font-size: 15px; padding: 8px 16px;">Thêm</a>
								</div>
								<div class="btn-group">
									<a href="http://localhost:8080/OneShop/admin/manager"
										class="btn btn-warning btn-sm"
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
														<th class="border-top-0">ID</th>
														<th class="border-top-0">Tên khách hàng</th>
														<th class="border-top-0">Số điện thoại</th>
														<th class="border-top-0">Email</th>
														<th class="border-top-0">Tên tài khoản</th>
														<th class="border-top-0">Mật khẩu</th>
														<th class="border-top-0">Địa chỉ</th>
														<th class="border-top-0">Ngày tạo</th>
														<th class="border-top-0">Hàng động</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="manager" items="${users}">
														<tr>
															<td><a class="border-top-1">${manager.userId}</a></td>
															<td><a>${manager.fullName}</a></td>
															<td><a>${manager.phoneNumber}</a></td>
															<td><a>${manager.email}</a></td>
															<td><a>${manager.accountName}</a></td>
															<td><a>${manager.password}</a></td>
															<td style="max-width: 300px; word-wrap: break-word;">
																<a>${manager.address}</a>
															</td>
															<td><a>${manager.createdDate}</a></td>
															<td class="d-flex flex-column gap-0"><a
																href="${pageContext.request.contextPath}/admin/edit-manager?userId=${manager.userId}"
																class="btn btn-warning btn-sm"
																style="margin: 15px 0 25px 0;">Sửa</a>
																<form
																	action="${pageContext.request.contextPath}/admin/delete-manager"
																	method="POST" style="display: inline;"
																	onsubmit="return confirmDelete();">
																	<input type="hidden" name="managerId"
																		value="${manager.userId}">
																	<button type="submit" class="btn btn-danger btn-sm"
																		style="padding: 10px 30px;">Xóa</button>
																</form> <script>
																	function confirmDelete() {
																		return confirm("Bạn có chắc chắn muốn xóa người dùng này không?");
																	}
																</script></td>
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