<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ page import="java.util.Base64" %>
<%@ page import="models.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
    .product-container {
        transition: all 0.3s ease;
    }
    .container:hover {
        width: 200px;
        height: auto;
        padding: 10px 15px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
    }
</style>
</head>
<body>
	<div style="width: 1387px; margin-left: -45px;">
	    <%
	        User user = (User) session.getAttribute("userInfor");
	        if (user != null) {
	            request.setAttribute("userId", user.getUserId());
	        }
	    %>
	    <h2 class="text-left" style="margin: 20px 0 30px 0;">Danh Sách Sản Phẩm</h2>
	    <div class="btn-group" style="margin-bottom: 20px;">
	        <a href="?filter=new&page=1&pageSize=5" style="margin-right: 25px; padding: 10px 15px;" class="btn btn-primary ${filter == 'new' ? 'active' : ''}">Sản phẩm mới</a>
	        <a href="?filter=best-selling&page=1&pageSize=5" style="margin-right: 25px; padding: 10px 30px;" class="btn btn-success ${filter == 'best-selling' ? 'active' : ''}">Bán chạy</a>
	        <a href="?filter=top-rated&page=1&pageSize=5" style="margin-right: 25px; padding: 10px 30px; color: white;" class="btn btn-info ${filter == 'top-rated' ? 'active' : ''}">Đánh giá cao</a>
	        <a href="?filter=favorite&page=1&pageSize=5" style="padding: 10px 30px; z-index: 0; color: white;" class="btn btn-warning ${filter == 'favorite' ? 'active' : ''}">Yêu thích</a>
	    </div>
	    <div class="row">
	        <c:forEach var="product" items="${products}">
	            <div class="col-md-2 container" style="margin: 50px 47px 20px 0;">
	                <div class="text-center">
	                    <a href="${pageContext.request.contextPath}/user/view-detail-product?productId=${product.productId}&userId=${userId}	"
	                       style="color: inherit; text-decoration: none;"> <c:if
	                            test="${not empty product.image}">
	                        <img
	                                src="data:image/jpeg;base64,${fn:escapeXml(Base64.getEncoder().encodeToString(product.image))}"
	                                style="width: 200px; height: 200px;" alt="Product Image"
	                                class="product-image" />
	                    </c:if>
	                        <h5 class="card-title">${product.productName}</h5>
	                        <p style="font-size: 20px;">
	                            <fmt:formatNumber value="${product.price}" type="number" pattern="#,###" /> VND
	                        </p>
	                    </a>
	                </div>
	            </div>
	        </c:forEach>
	    </div>
	    <nav aria-label="Page navigation">
	        <ul class="pagination justify-content-center">
	            <c:if test="${currentPage > 1}">
	                <li class="page-item">
	                    <a class="page-link"
	                       href="?filter=${filter}&page=${currentPage - 1}&pageSize=${pageSize}"
	                       aria-label="Previous">
	                        &laquo;
	                    </a>
	                </li>
	            </c:if>
	            <c:forEach begin="${startPage}" end="${endPage}" var="i">
	                <li class="page-item ${i == currentPage ? 'active' : ''}">
	                    <a class="page-link"
	                       href="?filter=${filter}&page=${i}&pageSize=${pageSize}">
	                            ${i}
	                    </a>
	                </li>
	            </c:forEach>
	            <c:if test="${currentPage < totalPages}">
	                <li class="page-item">
	                    <a class="page-link"
	                       href="?filter=${filter}&page=${currentPage + 1}&pageSize=${pageSize}"
	                       aria-label="Next">
	                        &raquo;
	                    </a>
	                </li>
	            </c:if>
	        </ul>
	    </nav>
	</div>
</body>
</html>