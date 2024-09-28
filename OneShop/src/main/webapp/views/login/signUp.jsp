<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="static/styles/login/signUp.css">
<link rel="icon" type="image/png" href="static/images/pic10.jpg">
<script type="text/javascript" src="static/scripts/signUp.js"></script>
</head>
<body>
	<form method="POST">
		<div class="container">
			<h1>Đăng kí thông tin khách hàng</h1>
			<hr>
			<h2>Thông tin khách hàng</h2>
			<div class="customer-name">			
				<label> Họ, tên đệm: </label> 
				<input type="text" name="firstname" placeholder="Nhập họ và tên đệm" class="name-field" required /> 
				<label> Tên: </label> 
				<input type="text" name="lastname" placeholder="Nhập tên" class="name-field" required />
			</div>
			<div class="infor">
                <label class="email"> Email: </label> 
                <input type="text" style="margin-left: 69px;" name="email" placeholder="Nhập email" class="email-field" onblur="checkField('email', this.value)" required /> 
                <span id="email-error" style="color:red;"></span> 
            </div>
			<div class="infor">
                <label class="phone-number"> Số điện thoại: </label> 
                <input type="text" style="margin-left: 5px;" name="phone" placeholder="Nhập số điện thoại" class="phnumber-field" onblur="checkField('phone', this.value)" required /> 
                <span id="phone-error" style="color:red;"></span> 
            </div>
			<div class="infor">
				<label class="address"> Địa chỉ: </label> 
				<input type="text" style="margin-left: 56px;" name="address" placeholder="Nhập địa chỉ" class="address-field" required /> 
			</div>					
			<h2>Thông tin đăng nhập</h2>			
			<div class="account">
                <label class="user-account"> Tên đăng nhập: </label> 
                <input type="text" style="margin-left: 5px;" name="username" placeholder="Nhập tên đăng nhập" class="user-account-field" onblur="checkField('username', this.value)" required /> 
                <span id="username-error" style="color:red;"></span> 
            </div>
			<div class="account">
				<label class="pw"> Mật khẩu: </label> 
				<input type="password" style="margin-left: 46px;" name="pw" placeholder="Nhập mật khẩu" class="pw-field" required /> 
			</div>	
			<button type="submit" class="submit">Xác nhận</button>
		</div>
	</form>
</body>
</html>