<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="static/styles/forgetPassword.css">
<script type="text/javascript" src="static/scripts/forgetPassword.js"></script>
<title>Forget Password</title>
</head>
<body>
	<form method="POST">
		<div class="container">
			<h1>Quên mật khẩu</h1>
			<hr>
			<div class="infor">
                <label class="email" style="margin-left: 215px;"> Email: </label> 
                <input type="text" style="margin-left: 10px;" name="email" placeholder="Nhập email" class="email-field" onblur="checkField('email', this.value)" required /> 
                <span id="email-error"></span> 
            </div>			
			<div class="account">
				<label class="pw"> Nhập mật khẩu mới: </label> 
				<input type="password" style="margin-left: 10px;" name="pw" placeholder="Nhập mật khẩu" class="pw-field" required /> 
			</div>	
			<div class="account">
				<label class="pw" style="margin-left: 100px;"> Xác nhận mật khẩu: </label> 
				<input type="password" style="margin-left: 10px;" name="pw" placeholder="Xác nhật mật khẩu" class="pw-field" required /> 
			</div>	
			<button type="submit" class="submit">Xác nhận</button>
		</div>
	</form>
</body>
</html>