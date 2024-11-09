<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-4 d-flex justify-content-center" style="margin: 0 0 0 -80px;">
		<div style="width: 90%; max-width: 900px;">
			<h3 class="mb-4 text-center">Thông tin cửa hàng</h3>
			<form>
				<div class="row mb-5">
					<div class="col-md-6">
						<label for="shopName" class="form-label">Tên cửa hàng:</label> <input
							type="text" class="form-control" id="shopName" name="shopName"
							placeholder="Nhập tên cửa hàng" required style="width: 100%;">
					</div>
					<div class="col-md-6">
						<label for="description" class="form-label">Mô tả:</label>
						<textarea class="form-control" id="description" name="description"
							rows="1" placeholder="Nhập mô tả cửa hàng" required
							style="width: 100%;"></textarea>
					</div>
				</div>
				<div class="row mb-5">
					<div class="col-md-6">
						<label for="status" class="form-label">Trạng thái:</label> <select
							class="form-select" id="status" name="status" required
							style="width: 100%;">
							<option selected disabled>Chọn trạng thái</option>
							<option value="active">Hoạt động</option>
							<option value="inactive">Không hoạt động</option>
						</select>
					</div>
					<div class="col-md-6">
						<label for="createdAt" class="form-label">Ngày tạo:</label> <input
							type="date" class="form-control" id="createdAt" name="createdAt"
							required style="width: 100%;">
					</div>
				</div>
				<div class="row mb-5">
					<div class="col-md-6">
						<label for="bankAccount" class="form-label">Tài khoản ngân
							hàng:</label> <input type="text" class="form-control" id="bankAccount"
							name="bankAccount" placeholder="Nhập tài khoản ngân hàng"
							required style="width: 100%;">
					</div>
					<div class="col-md-6">
						<label for="bankName" class="form-label">Tên ngân hàng:</label> <input
							type="text" class="form-control" id="bankName" name="bankName"
							placeholder="Nhập tên ngân hàng" required style="width: 100%;">
					</div>
				</div>
				<div class="d-flex justify-content-end" style="transform: translateX(-370px);">
					<button type="submit" class="btn btn-primary mt-3" style=" background-color: #3498db;">Lưu
						thông tin</button>
				</div>
			</form>
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7VVf0SzP+UlwzwyFw4klZ+khcAhmGn8So7rH2yKD5nMjNA2" crossorigin="anonymous"></script>
</body>
</html>