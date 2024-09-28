<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<form method="post">
			<div class="inputBox">
				<input id="uname" type="text" name="username" placeholder="Username" required/>
				<input id="pass" type="password" name="password" placeholder="Password" required/>
				<c:if test="${not empty errorMessage}">
            		<div class="error">${errorMessage}</div>
    			</c:if>
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