<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% if (request.getAttribute("success") != null) { %>
	    <div class="alert alert-success" style="margin-left: -100px;">
	        <%= request.getAttribute("success") %>
	    </div>
	<% } %>
	<div class="container mt-4 d-flex justify-content-center" style="margin: 0 0 0 -80px;">
		<div style="width: 90%; max-width: 900px;">
			<h3 class="mb-4 text-center" style="font-weight: bold;">Phiếu giảm giá</h3>
			<form action="${pageContext.request.contextPath}/vendor/add-promotion" method="post" enctype="multipart/form-data">
				<div class="row mb-5">	
					<div class="col-md-6">
						<label for="status" class="form-label">Mã khách hàng:</label> <select
							class="form-select" id="customerId" name="customerId" required
							style="width: 100%;">
							<option selected disabled>Chọn khách hàng tiềm năng</option>
							<c:forEach var="userId" items="${userIdsSet}">
                                <option value="${userId}">${userId}</option>
                            </c:forEach>
						</select>
					</div>
					<div class="col-md-6">
						<label for="status" class="form-label">Giảm %:</label> <select
							class="form-select" id="decrease" name="decrease" required
							style="width: 100%;">
							<option selected disabled>Lựa chọn</option>
                            <option value="5">Giảm 5%</option>
                            <option value="10">Giảm 10%</option>
                            <option value="15">Giảm 15%</option>
                            <option value="20">Giảm 20%</option>
                            <option value="25">Giảm 25%</option>
                            <option value="30">Giảm 30%</option>
						</select>
					</div>
				</div>
				<div class="row mb-5">
					<div class="col-md-12">
						<label for="description" class="form-label">Ngày hết hạn:</label>
                        <input type="date" class="form-control" id="expiredate" name="expiredate" required style="width: 100%;"></input>
					</div>
				</div>
				<div class="d-flex justify-content-end" style="transform: translateX(-330px);">
					<button type="submit" class="btn btn-success mt-3" style=" background-color: #28a745;">Thêm Phiếu Giảm Giá</button>
				</div>
			</form>
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7VVf0SzP+UlwzwyFw4klZ+khcAhmGn8So7rH2yKD5nMjNA2" crossorigin="anonymous"></script>
</body>
</html>