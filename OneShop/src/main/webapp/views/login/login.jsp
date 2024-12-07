<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="static/styles/login/login.css">
<link rel="icon" type="image/png" href="static/images/pic9.png">
<link href="static/scripts/login.js">
</head>
<body>
	<div class="loginBox">
		<img class="user" src="static/images/pic5.jpg" height="100px" width="100px">
		<a class="sign-in-here" href="http://localhost:8080/OneShop/login">Sign in here</a>
		<form id="loginForm">
		    <div class="inputBox">
				<input id="uname" type="text" name="username" placeholder="Username" required/>
				<input id="pass" type="password" name="password" placeholder="Password" required/>
				<div class="error-message" style="display:none; color:red;"></div>
			</div>
		    <input type="submit" value="Login" />
		</form>
		<a class="forget-pw" href="http://localhost:8080/OneShop/forget-password">Forget Password<br></a>
		<div class="text-center">
			<a class="sign-up" href="http://localhost:8080/OneShop/sign-up">Sign-Up</a>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$('#loginForm').on('submit', function (e) {
		    e.preventDefault(); 
		    const username = $('#uname').val();
		    const password = $('#pass').val();
		
		    $.ajax({
		        url: '/OneShop/login',
		        type: 'POST',
		        data: { username, password },
		        success: function (data) { 
		            if (data.role === 'customer') {
		            	localStorage.setItem('customerToken', data.token);
		                window.location.href = '/OneShop/user/product';
		            } else if (data.role === 'vendor') {
		            	localStorage.setItem('vendorToken', data.token);
		                window.location.href = '/OneShop/vendor/home';
		            } else if (data.role === 'admin') {
		            	localStorage.setItem('adminToken', data.token);
		                window.location.href = '/OneShop/admin/dashboard';
		            }
		        },
		        error: function (xhr) {
	                const errorMessage = xhr.responseJSON.error || 'Có lỗi xảy ra. Vui lòng thử lại!';
	                $('.error-message').text(errorMessage).show();
	            }
		    });
		});
	</script>
</body>
</html>
