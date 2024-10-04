<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sản phẩm</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
									<h4 style="margin-top: -20px;">Home -> Sản phẩm -> Thêm sản phẩm</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="container mt-0">
						<div class="row">
							<div id="recent-transactions" class="col-12">							
								<form action="${pageContext.request.contextPath}/admin/add-product" method="post" enctype="multipart/form-data">
									<div class="mb-3">
										<h6 class="mb-0">Tên sản phẩm:</h6>
										<input type="text" class="form-control" name="productName"
											placeholder="Nhập tên sản phẩm" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Ảnh:</h6>
										<input type="file" class="form-control" name="image" />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Giá:</h6>
										<input type="text" class="form-control" name="price"
											placeholder="Nhập giá sản phẩm" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Số lượng:</h6>
										<input type="number" class="form-control" name="quantity"
											placeholder="Nhập số lượng" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Mã hãng sản xuất:</h6>
										<input type="text" class="form-control" name="categoryId"
											placeholder="Nhập hãng sản xuất" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Mô tả:</h6>
										<input type="text" class="form-control" name="description"
											placeholder="Mô tả sản phẩm" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Ngày sản xuất:</h6>
										<input type="date" class="form-control" name="createdDate"
											required />
									</div>
									<div class="d-flex justify-content-center mb-3">
										<button type="submit" class="btn btn-primary">Thêm
											sản phẩm</button>
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