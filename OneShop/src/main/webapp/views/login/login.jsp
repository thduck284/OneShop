<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="static/styles/login.css">
<link href="static/scripts/login.js">
</head>
<body>
	<div class="loginBox">
		<a class="sign-in-here" href="http://localhost:8080/LoginProject/login">Sign in here</a>
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