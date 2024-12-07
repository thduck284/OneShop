<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<%@ page import="models.Promotion" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
        Promotion promotion = (Promotion) session.getAttribute("editPromotion");
    %>
	<% if (request.getAttribute("success") != null) { %>
		    <div class="alert alert-success" style="margin-left: -100px;">
		        <%= request.getAttribute("success") %>
		    </div>
	<% } %>
	<%
	    String formattedDate = "";
	    if (promotion != null && promotion.getExpirationDate() != null) {
	        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	        formattedDate = sdf.format(promotion.getExpirationDate());
	    }
	%>
	<div class="container mt-4 d-flex justify-content-center" style="margin: 0 0 0 -80px;">
		<div style="width: 90%; max-width: 900px;">
			<h3 class="mb-4 text-center" style="font-weight: bold;">Phiếu giảm giá</h3>
			<form action="${pageContext.request.contextPath}/vendor/edit-promotion" method="post" enctype="multipart/form-data">
				<div class="row mb-5">	
					<div class="col-md-6">
						<label for="status" class="form-label">Mã khách hàng:</label> 
						<select class="form-select" id="customerId" name="customerId" required style="width: 100%;">
						    <c:forEach var="userId" items="${userIdsSet}">
							    <option value="${userId}" ${promotion != null && promotion.userId == userId ? "selected" : ""}>
							        ${userId}
							    </option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-6">
					    <label for="status" class="form-label">Giảm %:</label>
					    <select class="form-select" id="decrease" name="decrease" required style="width: 100%;">
						    <option value="5" <%= (promotion != null && promotion.getPrice() == 5) ? "selected" : "" %>>Giảm 5%</option>
						    <option value="10" <%= (promotion != null && promotion.getPrice() == 10) ? "selected" : "" %>>Giảm 10%</option>
						    <option value="15" <%= (promotion != null && promotion.getPrice() == 15) ? "selected" : "" %>>Giảm 15%</option>
						    <option value="20" <%= (promotion != null && promotion.getPrice() == 20) ? "selected" : "" %>>Giảm 20%</option>
						    <option value="25" <%= (promotion != null && promotion.getPrice() == 25) ? "selected" : "" %>>Giảm 25%</option>
						    <option value="30" <%= (promotion != null && promotion.getPrice() == 30) ? "selected" : "" %>>Giảm 30%</option>
						</select>
					</div>
				</div>
				<div class="row mb-5">
					<div class="col-md-12">
						<label for="description" class="form-label">Trạng thái:</label>
						<select class="form-select" id="status" name="status" required style="width: 100%;">
				            <option value="5" <%= (promotion != null && !promotion.getStatus()) ? "selected" : "" %>>Chưa sử dụng</option>
				            <option value="10" <%= (promotion != null && promotion.getStatus()) ? "selected" : "" %>>Đã sử dụng</option>
				        </select>
					</div>
				</div>
				<div class="row mb-5">
				    <div class="col-md-12">
				        <label for="description" class="form-label">Ngày hết hạn:</label>
				        <input type="date" class="form-control" id="expiredate" name="expiredate" required style="width: 100%;"
				    		value="<%= formattedDate %>">
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