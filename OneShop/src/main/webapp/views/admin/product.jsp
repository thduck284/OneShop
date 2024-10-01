<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sản phẩm</title>
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
									<h3 style="margin-top: -20px;">Home -> Sản phẩm</h3>
								</div>
							</div>
						</div>
					</div>
					<div class="row" style="margin-left: 70px;">
						<div id="recent-transactions" class="col-12">
							<h6 class="my-2">Các sản phẩm</h6>
							<div style="margin: -40px 0 20px 900px;">
								<div class="btn-group me-2">
									<button class="btn btn-primary btn-sm" style="font-size: 15px; padding: 8px 16px;">Thêm</button>
								</div>
								<div class="btn-group">
									<button class="btn btn-warning btn-sm" style="font-size: 15px; padding: 8px 22px;">Lọc</button>
								</div>
							</div>
							<div class="card">
								<div class="card-content">
									<div class="table-responsive">
										<table id="recent-orders"
											class="table table-hover table-xl mb-0">
											<thead>
												<tr>
													<th class="border-top-0">ID</th>
													<th class="border-top-0">Tên sản phẩm</th>
													<th class="border-top-0">Giá</th>
													<th class="border-top-0">Số lượng</th>
													<th class="border-top-0">Doanh mục</th>
													<th class="border-top-0">NSX</th>
													<th class="border-top-0">Ảnh</th>
													<th class="border-top-1">Hàng động</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="info-product"><a href="#">SP1</a></td>
													<td class="info-product"><a href="#">Sản phẩm A</a></td>
													<td class="info-product"><a href="#">100.000đ</a></td>
													<td class="info-product">5</td>
													<td class="info-product">Doanh mục A</td>
													<td class="info-product">1/10/2024</td>
													<td class="info-product">Ảnh SP A</td>
													<td class="d-flex flex-column gap-2">
														<button class="btn btn-warning btn-sm" style="margin: 0 0 5px 0;">Sửa</button>
														<button class="btn btn-danger btn-sm">Xóa</button>
													</td>
												</tr>
												<tr>
													<td class="info-product"><a href="#">SP1</a></td>
													<td class="info-product"><a href="#">Sản phẩm A</a></td>
													<td class="info-product"><a href="#">100.000đ</a></td>
													<td class="info-product">5</td>
													<td class="info-product">Doanh mục A</td>
													<td class="info-product">1/10/2024</td>
													<td class="info-product">Ảnh SP A</td>
													<td class="d-flex flex-column gap-2">
														<button class="btn btn-warning btn-sm" style="margin: 0 0 5px 0;">Sửa</button>
														<button class="btn btn-danger btn-sm">Xóa</button>
													</td>
												</tr>
												<tr>
													<td class="info-product"><a href="#">SP1</a></td>
													<td class="info-product"><a href="#">Sản phẩm A</a></td>
													<td class="info-product"><a href="#">100.000đ</a></td>
													<td class="info-product">5</td>
													<td class="info-product">Doanh mục A</td>
													<td class="info-product">1/10/2024</td>
													<td class="info-product">Ảnh SP A</td>
													<td class="d-flex flex-column gap-2">
														<button class="btn btn-warning btn-sm" style="margin: 0 0 5px 0;">Sửa</button>
														<button class="btn btn-danger btn-sm">Xóa</button>
													</td>
												</tr>
												<tr>
													<td class="info-product"><a href="#">SP1</a></td>
													<td class="info-product"><a href="#">Sản phẩm A</a></td>
													<td class="info-product"><a href="#">100.000đ</a></td>
													<td class="info-product">5</td>
													<td class="info-product">Doanh mục A</td>
													<td class="info-product">1/10/2024</td>
													<td class="info-product">Ảnh SP A</td>
													<td class="d-flex flex-column gap-2">
														<button class="btn btn-warning btn-sm" style="margin: 0 0 5px 0;">Sửa</button>
														<button class="btn btn-danger btn-sm">Xóa</button>
													</td>
												</tr>
												<tr>
													<td class="info-product"><a href="#">SP1</a></td>
													<td class="info-product"><a href="#">Sản phẩm A</a></td>
													<td class="info-product"><a href="#">100.000đ</a></td>
													<td class="info-product">5</td>
													<td class="info-product">Doanh mục A</td>
													<td class="info-product">1/10/2024</td>
													<td class="info-product">Ảnh SP A</td>
													<td class="d-flex flex-column gap-2">
														<button class="btn btn-warning btn-sm" style="margin: 0 0 5px 0;">Sửa</button>
														<button class="btn btn-danger btn-sm">Xóa</button>
													</td>
												</tr>
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
</body>
</html>