<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Left</title>
</head>
<body>
	<div
		class="main-menu menu-fixed menu-dark menu-bg-default rounded menu-accordion menu-shadow" style="width: 150px;">
		<div class="main-menu-content">
			<a class="navigation-brand d-none d-md-block d-lg-block d-xl-block""
				href="index.html"><img class="brand-logo"
				alt="CryptoDash admin logo"
				src="../static/styles/admin/app-assets/images/logo/icon.jpg" width="200px"></a>
			<ul class="navigation navigation-main" id="main-menu-navigation"
				data-menu="menu-navigation">
				<li class="active"><a
					href="http://localhost:8080/OneShop/admin/dashboard"><i
						class="icon-grid"></i><span class="menu-title" data-i18n="">Dashboard</span></a>
				</li>
				<li class=" nav-item"><a href="http://localhost:8080/OneShop/admin/product"><i
						class="fa-solid fa-box-open"></i><span class="menu-title" data-i18n="">Sản phẩm</span></a></li>
				<li class=" nav-item"><a href="http://localhost:8080/OneShop/admin/customer"><i
						class="fa-solid fa-user"></i><span class="menu-title" data-i18n="">Khách hàng</span></a>
				</li>
				<li class=" nav-item"><a href="http://localhost:8080/OneShop/admin/manager"><i
						class="fa-solid fa-briefcase"></i><span class="menu-title" data-i18n="">Quản lý</span></a>
				</li>
				<li class=" nav-item"><a href="http://localhost:8080/OneShop/admin/category"><i
						class="fa-solid fa-box"></i><span class="menu-title" data-i18n="">Doanh mục</span></a></li>
				<li class=" nav-item"><a href="http://localhost:8080/OneShop/admin/order"><i
						class="fa-solid fa-shopping-cart"></i><span class="menu-title" data-i18n="">Đặt hàng</span></a>
				</li>
				<li class=" nav-item"><a href="http://localhost:8080/OneShop/admin/revenue"><i
						class="fa-solid fa-chart-line"></i><span class="menu-title" data-i18n="">Doanh thu</span></a>
				</li>
				<li class=" nav-item"><a href="#"><i
						class="icon-user-following"></i><span class="menu-title"
						data-i18n="">Tài khoản</span></a>
					<ul class="menu-content">
						<li><a class="menu-item" href="account-profile.html">Profile</a>
						</li>
						<li><a class="menu-item" href="account-login-history.html">Lịch sử đăng nhập</a></li>
						<li class="navigation-divider"></li>
						<li><a class="menu-item" href="#">Misc</a>
							<ul class="menu-content">
								<li><a class="menu-item" href="account-login.html">Login</a>
								</li>
								<li><a class="menu-item" href="account-register.html">Register</a>
								</li>
							</ul></li>
					</ul></li>
			</ul>
		</div>
	</div>
</body>
</html>