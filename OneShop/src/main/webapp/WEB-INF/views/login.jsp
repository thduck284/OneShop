<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="icon" type="image/png" href="static/images/pic9.png">
<link rel="stylesheet" type="text/css" href="static/styles/login.css">
</head>
<body>
	<div class="loginBox">
		<img class="user" src="static/images/pic5.jpg" height="100px" width="100px">
		<a class="sign-in-here" href="http://localhost:8080/OneShop/login">Sign in here</a>
		<form action="role" method="post">
			<div class="inputBox">
				<input id="uname" type="text" name="Username" placeholder="Username">
				<input id="pass" type="password" name="Password" placeholder="Password">
			</div>
			<input type="submit" name="" value="Login">
		</form>
		<a class="forget-pw" href="http://localhost:8080/OneShop/forget-password">Forget Password<br></a>
		<div class="text-center">
			<a class="sign-up" href="http://localhost:8080/OneShop/sign-up">Sign-Up</a>
		</div>
	</div>
</body>
</html>