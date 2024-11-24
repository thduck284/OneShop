<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="models.User" %>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
    <div class="wrapper" style="background-color: #7ADAF5;">
		<%
		User user = (User) session.getAttribute("vendorInfor");
			String userId = user != null ? user.getUserId() : "";
		%>
		<aside id="sidebar">
            <div class="h-50">
                <ul class="sidebar-nav">
                	<li class="sidebar-item">
    					<a href="http://localhost:8080/OneShop/vendor/home" class="sidebar-link"><i class="fa-solid fa-home pe-2" style="margin-right: 1px;"></i>Trang chủ</a>
					</li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#manage"
                            aria-expanded="false" aria-controls="manage">
                            <i class="fa-solid fa-store pe-2"></i>Quản lý shop
                        </a>
                        <ul id="manage" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="http://localhost:8080/OneShop/vendor/register-shop" class="sidebar-link">Đăng ký shop</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="${pageContext.request.contextPath}/vendor/manage-shop?userId=<%= userId %>" class="sidebar-link">Quản lý shop</a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#product"
                            aria-expanded="false" aria-controls="product">
                            <i class="fa-solid fa-store pe-2"></i>Quản lý sản phẩm
                        </a>
                        <ul id="product" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                        	<li class="sidebar-item">
                                <a href="${pageContext.request.contextPath}/vendor/sell-product?userId=<%= userId %>" class="sidebar-link">Đăng bán sản phẩm</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="http://localhost:8080/OneShop/vendor/manage-product?userId=<%= userId %>" class="sidebar-link">Danh sách sản phẩm</a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#order"
                            aria-expanded="false" aria-controls="order">
                            <i class="fa-solid fa-receipt pe-2" style="margin-right: 7px;"></i>Quản lý đơn hàng
                        </a>
                        <ul id="order" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">Đơn hàng</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">Chi tiết đơn hàng</a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item">
    					<a href="#" class="sidebar-link"><i class="fa-solid fa-users pe-2" style="margin-right: 1px;"></i>Khách hàng</a>
					</li>
					<li class="sidebar-item">
    					<a href="#" class="sidebar-link">
        					<i class="fas fa-tags pe-2" style="margin-right: 6px;"></i>Khuyến mãi
    					</a>
					</li>
					<li class="sidebar-item">
    					<a href="#" class="sidebar-link">
        					<i class="fas fa-dollar-sign pe-2"></i>Doanh thu
    					</a>
					</li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#auth"
                            aria-expanded="false" aria-controls="auth">
                            <i class="fa-regular fa-user pe-2"></i>
                            Auth
                        </a>
                        <ul id="auth" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">Login</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">Register</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </aside>
        <div class="main">
    		<nav class="navbar navbar-expand px-3 border-bottom">
        		<button class="btn" type="button" data-bs-theme="dark" style="margin: 0 0 10px 5px;">
            		<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="black" class="bi bi-list" viewBox="0 0 16 16">
                		<path fill-rule="evenodd" d="M2 3.5a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1H2.5a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1H2.5a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1H2.5a.5.5 0 0 1-.5-.5z"/>
            		</svg>
        		</button>
    		</nav>
		</div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
    <script src="../static/scripts/vendor/left-vendor.js"></script>
</body>
</html>
