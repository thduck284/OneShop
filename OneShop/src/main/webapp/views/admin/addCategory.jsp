<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm doanh mục</title>
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
									<h4 style="margin-top: -20px;">Home -> Doanh mục -> Thêm doanh mục</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="container mt-0">
						<div class="row">
							<div id="recent-transactions" class="col-12">							
								<form action="${pageContext.request.contextPath}/admin/add-category" method="post" enctype="multipart/form-data">
									<% String message = (String) request.getAttribute("message"); %>
									<% if (message != null) { %>
    								<div class="alert alert-info">
        								<%= message %>
    								</div>
									<% } %>
									<div class="mb-3">
										<h6 class="mb-0">Tên hãng sản xuất:</h6>
										<input type="text" class="form-control" name="categoryName"
											placeholder="Nhập tên hãng sản xuất" required />
									</div>
									<div class="mb-3">
										<h6 class="mb-0">Mô tả:</h6>
										<input type="text" class="form-control" name="description"
											placeholder="Mô tả hãng sản xuất" required />
									</div>
									<div class="d-flex justify-content-center mb-3">
										<button type="submit" class="btn btn-primary">Thêm doanh mục</button>
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