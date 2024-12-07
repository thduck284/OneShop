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
	<div class="container-fluid mt-5" style="margin-right: 36px; width: 1250px;">
	    <div class="row">
	        <div class="col-md-4">
	            <div class="card" style="background-color: #46DFB1;">
	                <div class="card-body">
	                    <h4 class="card-title" style="font-weight: bold; color: #213A58;">Người Bán</h4>
	                     <p class="card-text" style="font-weight: bold; color: red;">Số lượng người bán: ${vendorCount}</p>
	                    <a href="${pageContext.request.contextPath}/admin/vendor" class="btn btn-primary">Quản lý Người Bán</a>
	                </div>
	            </div>
	        </div>
	        <div class="col-md-4">
	            <div class="card" style="background-color: #46DFB1;">
	                <div class="card-body">
	                    <h4 class="card-title" style="font-weight: bold; color: #213A58;">Khách Hàng</h4>
	                    <p class="card-text" style="font-weight: bold; color: red;">Số lượng khách hàng: ${customerCount}</p>
	                    <a href="${pageContext.request.contextPath}/admin/customer" class="btn btn-primary">Quản lý Khách Hàng</a>
	                </div>
	            </div>
	        </div>
	        <div class="col-md-4 mb-4">
	            <div class="card" style="background-color: #46DFB1;">
	                <div class="card-body">
	                    <h4 class="card-title" style="font-weight: bold; color: #213A58;">Sản Phẩm</h4>
	                    <p class="card-text" style="font-weight: bold; color: red;">Số lượng sản phẩm: ${productCount}</p>
	                    <a href="${pageContext.request.contextPath}/admin/product" class="btn btn-primary">Quản lý Sản Phẩm</a>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="row mt-5">
	    	<div class="col-md-4">
	            <div class="card" style="background-color: #46DFB1;">
	                <div class="card-body">
	                    <h4 class="card-title" style="font-weight: bold; color: #213A58;">Giỏ hàng</h4>
	                    <p class="card-text" style="font-weight: bold; color: red;">Số lượng giỏ hàng: ${cartCount}</p>
	                    <a href="${pageContext.request.contextPath}/admin/cart" class="btn btn-primary">Quản lý Đơn Hàng</a>
	                </div>
	            </div>
	        </div>
	        <div class="col-md-4">
	            <div class="card" style="background-color: #46DFB1;">
	                <div class="card-body">
	                    <h4 class="card-title" style="font-weight: bold; color: #213A58;">Đơn Hàng</h4>
	                    <p class="card-text" style="font-weight: bold; color: red;">Số lượng đơn hàng: ${orderCount}</p>
	                    <a href="${pageContext.request.contextPath}/admin/order" class="btn btn-primary">Quản lý Đơn Hàng</a>
	                </div>
	            </div>
	        </div>
	        <div class="col-md-4">
	            <div class="card" style="background-color: #46DFB1;">
	                <div class="card-body">
	                    <h4 class="card-title" style="font-weight: bold; color: #213A58;">Doanh Thu</h4>             
	                    <p class="card-text" style="font-weight: bold; color: red;">
                            Tổng doanh thu:
                            <fmt:formatNumber value="${revenue}" type="number" pattern="#,##0" /> VND
                        </p>
	                    <a href="${pageContext.request.contextPath}/admin/revenue" class="btn btn-primary">Xem Doanh Thu</a>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
</html>
