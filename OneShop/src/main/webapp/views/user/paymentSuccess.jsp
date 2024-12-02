<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body {
        font-family: Arial, sans-serif;
        text-align: center;
        background-color: #f8f9fa;
        color: #343a40;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        justify-content: center;
        height: 100vh;
    }
    .container {
        max-width: 500px;
        margin: 0 auto;
        padding: 20px;
        background: #ffffff;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
    }
    h1 {
        color: #28a745;
    }
    p {
        font-size: 18px;
        margin: 20px 0;
    }
    a {
        text-decoration: none;
        color: #ffffff;
        background-color: #007bff;
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 16px;
    }
    a:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
	<div class="container">
        <h1>Thanh Toán Thành Công!</h1>
        <p>Cảm ơn bạn đã mua hàng tại OneShop. Đơn hàng của bạn đã được xử lý thành công.</p>
        <a href="http://localhost:8080/OneShop/user/home">Quay lại Trang Chủ</a>
    </div>
</body>
</html>