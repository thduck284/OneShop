<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../static/styles/vendor/header.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css">
<%@ page import="models.User" %> 
<title>Insert title here</title>
</head>
<body>
	<div class="contact">
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-telephone" viewBox="0 0 16 16">
        	<path
				d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.6 17.6 0 0 0 4.168 6.608 17.6 17.6 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.68.68 0 0 0-.58-.122l-2.19.547a1.75 1.75 0 0 1-1.657-.459L5.482 8.062a1.75 1.75 0 0 1-.46-1.657l.548-2.19a.68.68 0 0 0-.122-.58zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.68.68 0 0 0 .178.643l2.457 2.457a.68.68 0 0 0 .644.178l2.189-.547a1.75 1.75 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.6 18.6 0 0 1-7.01-4.42 18.6 18.6 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877z" />
    	</svg>
		<p>Hot line: 0365946542</p>
	</div>
	<div class="header" style="background-color: #7ADAF5;">
		<img class="header-image" src="../static/images/logo.png" width=300 height=190>
		<input class="search_input" type="text" placeholder="Nhập từ khóa tìm kiếm sản phẩm, danh mục">
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
  			<path
				d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
		</svg>
		<div class="dropdown" style="margin-left: -40px;">
			<%
			User loggedInUser = (User) session.getAttribute("vendorInfor");
			%>
			<a class="login" href="<%= (loggedInUser == null) ? request.getContextPath() + "/login" : "http://localhost:8080/OneShop/vendor/home" %>">
				<%
					if (loggedInUser != null) {
						out.print("Xin chào, " + loggedInUser.getFullName());
					} else {
						out.print("Đăng nhập / Đăng ký");
					}
				%>
			</a>
			<% if (loggedInUser != null) { %>
			<div class="dropdown-content" style="margin-left: -5px;">
				<a class="login-content" href="http://localhost:8080/OneShop/vendor/vendor-info">Tài khoản của tôi</a> 
				<a class="login-content" href="<%=request.getContextPath()%>/logout?role=vendor">Đăng xuất</a>
			</div>
			<% } else { %>
			<% } %>
		</div>
		<div class="box-cart" style="margin-top: -30px;">
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="black" class="bi bi-cart" viewBox="0 0 16 16">
  				<path
					d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2" />
			</svg>
			<p class="cart" style="margin: 55px 0 0 5px; color: black;">Giỏ hàng</p>
		</div>
	</div>
	<div class="under-header" style="background-color: #7ADAF5; margin: 0 0 -35px 380px;">
		<div class="dropdown">
			<a class="product" href="#">Túi sách</a>
			<div class="dropdown-content">
				<a href="#">Túi xách tay</a> 
				<a href="#">Túi đeo chéo</a> 
				<a href="#">Túi ba lô</a> 
				<a href="#">Túi cầm tay</a> 
				<a href="#">Túi đi du lịch</a>
			</div>
		</div>
		<div class="dropdown">
			<a class="product" href="#">Nước hoa</a>
			<div class="dropdown-content">
				<a href="#">Nước hoa Unisex</a> 
				<a href="#">Nước hoa Niche</a> 
				<a href="#">Nước hoa Mini</a> 
				<a href="#">Nước hoa Intense</a> 
				<a href="#">Nước hoa dạng xịt</a> 
				<a href="#">Nước hoa lăn</a> 
			</div>
		</div>
		<div class="dropdown">
			<a class="product" href="#">Mỹ phẩm</a>
			<div class="dropdown-content">
				<a href="#">Chăm sóc da mặt</a>
				<a href="#">Tẩy tế bào chết</a> 
				<a href="#">Kem chống nắng</a> 
				<a href="#">Kem dưỡng da</a> 
				<a href="#">Kem dưỡng ẩm</a> 
				<a href="#">Serum dưỡng da</a>
			</div>
		</div>
		<div class="dropdown">
			<a class="product" href="#">Son môi</a>
			<div class="dropdown-content">
				<a href="#">Son li</a> 
				<a href="#">Son kem</a> 
				<a href="#">Son dưỡng</a> 
				<a href="#">Son nước</a> 
				<a href="#">Son tint</a> 
				<a href="#">Son bóng</a> 
			</div>
		</div>
		<div class="dropdown">
			<a class="product" href="#">Trang sức</a>
			<div class="dropdown-content">
				<a href="#">Dây chuyền</a> 
				<a href="#">Bông tai</a> 
				<a href="#">Vòng tay</a> 
				<a href="#">Khuyên tai</a> 
				<a href="#">Nhẫn</a> 
				<a href="#">Vòng đeo tay</a> 
			</div>
		</div>
		<div class="dropdown">
			<a class="product" href="#">Thời trang</a>
			<div class="dropdown-content">
				<a href="#">Áo sơ mi</a> 
				<a href="#">Áo thun</a> 
				<a href="#">Áo polo</a> 
				<a href="#">Áo phông</a>
				<a href="#">Quần jeans</a> 
				<a href="#">Quần short</a>
			</div>
		</div>
		<div class="dropdown">
			<a class="promotion" href="#">Khuyến mãi</a>
		</div>
	</div>
</body>
</html>