<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="static/styles/homePage.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css">
<link rel="icon" type="image/png" href="static/images/pic8.png">
<script type="text/javascript" src="static/scripts/homePage.js"></script>
<title>One Shop</title>
</head>
<body>
	<div class="contact">
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-telephone" viewBox="0 0 16 16">
        	<path
				d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.6 17.6 0 0 0 4.168 6.608 17.6 17.6 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.68.68 0 0 0-.58-.122l-2.19.547a1.75 1.75 0 0 1-1.657-.459L5.482 8.062a1.75 1.75 0 0 1-.46-1.657l.548-2.19a.68.68 0 0 0-.122-.58zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.68.68 0 0 0 .178.643l2.457 2.457a.68.68 0 0 0 .644.178l2.189-.547a1.75 1.75 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.6 18.6 0 0 1-7.01-4.42 18.6 18.6 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877z" />
    	</svg>
		<p>0365946542</p>
	</div>
	<div class="header">
		<img class="header-image" src="static/images/title-image.png" width="180" height="120"> 
		<input class="search_input" type="text" placeholder="Nhập từ khóa tìm kiếm sản phẩm, danh mục">
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
  			<path
				d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
		</svg>
		<a class="login" href="http://localhost:8080/OneShop/login">Đăng nhập / Đăng ký</a>
		<div class="box-cart">
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
  				<path
					d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2" />
			</svg>
			<p class="cart">Giỏ hàng</p>
		</div>
	</div>
	<div class="under-header">
		<a class="home-page" href="http://localhost:8080/OneShop/home-page">Trang chủ</a>
		<a class="review" href="#">Giới thiệu</a>
		<div class="dropdown">
			<a class="product" href="#">Sản phẩm</a>
			<div class="dropdown-content">
				<a href="#">Chăm sóc da mặt</a> 
				<a href="#">Chăm sóc body</a> 
				<a href="#">Chăm sóc mắt môi</a> 
				<a href="#">Chăm sóc mái tóc</a> 
				<a href="#">Chăm sóc vóc dáng</a>
			</div>
		</div>
		<div class="dropdown">
			<a class="trademark" href="#">Thương hiệu</a>
			<div class="dropdown-content">
				<a href="#">Laura Sunshine</a> 
				<a href="#">Hebora</a> 
				<a href="#">UME</a> 
				<a href="#">MiungLab</a> 
				<a href="#">Evamost</a>
				<a href="#">Velerie Skin</a>
			</div>
		</div>
		<a class="promotion" href="#">Khuyến mãi</a>
	</div>
	<div class="image-review">
		<img id="pic1" class="pic" src="static/images/pic1.jpg"> 
		<img id="pic2" class="pic" src="static/images/pic2.jpg">
		<button id="prevBtn" class="flickity-button flickity-prev-next-button previous" type="button" aria-label="Previous">
			<svg class="flickity-button-icon" viewBox="0 0 100 100"> <path d="M 10,50 L 60,100 L 70,90 L 30,50  L 70,10 L 60,0 Z" class="arrow"></path></svg>
		</button>
		<button id="nextBtn" class="flickity-button flickity-prev-next-button next" type="button" aria-label="Next">
			<svg class="flickity-button-icon" viewBox="0 0 100 100"> <path d="M 10,50 L 60,100 L 70,90 L 30,50  L 70,10 L 60,0 Z" class="arrow" transform="translate(100, 100) rotate(180)"></path></svg>
		</button>
	</div>
	<img class="pic3" src="static/images/pic3.jpg" width="425" height="175"> 
	<img class="pic4" src="static/images/pic4.jpg" width="425" height="175">
</body>
</html>