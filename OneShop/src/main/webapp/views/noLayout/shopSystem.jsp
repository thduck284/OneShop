<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hệ Thống Cửa Hàng - UTE Shop</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f9f9f9;
    }
    header {
        background-color: #333;
        color: #fff;
        padding: 10px 0;
        text-align: center;
    }
    .container {
        width: 80%;
        margin: 20px auto;
        background: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    h1, h2 {
        color: #333;
    }
    .store-list {
        margin-top: 20px;
    }
    .store-item {
        margin-bottom: 20px;
    }
    .store-item h3 {
        margin: 0;
        color: #555;
    }
    .store-item p {
        margin: 5px 0;
        color: #777;
    }
    .footer {
        background-color: #333;
        color: #fff;
        text-align: center;
        padding: 10px 0;
        margin-top: 20px;
    }
</style>
</head>
<body>
    <header>
        <h1>Hệ Thống Cửa Hàng UTE Shop</h1>
        <p>Mang thời trang và mỹ phẩm đến gần hơn với bạn</p>
    </header>
    <div class="container">
        <h2>Các chi nhánh của chúng tôi</h2>
        <div class="store-list">
            <div class="store-item">
                <h3>Chi nhánh Thủ Đức</h3>
                <p><b>Địa chỉ:</b> Số 01 Võ Văn Ngân, Phường Linh Chiểu, Thành phố Thủ Đức, TP. Hồ Chí Minh</p>
                <p><b>Điện thoại:</b> +84 123 456 789</p>
                <p><b>Giờ mở cửa:</b> 9:00 - 21:00 (Thứ Hai - Chủ Nhật)</p>
            </div>
            <div class="store-item">
                <h3>Chi nhánh Quận 1</h3>
                <p><b>Địa chỉ:</b> Số 25 Nguyễn Huệ, Phường Bến Nghé, Quận 1, TP. Hồ Chí Minh</p>
                <p><b>Điện thoại:</b> +84 987 654 321</p>
                <p><b>Giờ mở cửa:</b> 9:00 - 21:00 (Thứ Hai - Chủ Nhật)</p>
            </div>
            <div class="store-item">
                <h3>Chi nhánh Hà Nội</h3>
                <p><b>Địa chỉ:</b> Số 10 Lê Thái Tổ, Phường Hàng Trống, Quận Hoàn Kiếm, Hà Nội</p>
                <p><b>Điện thoại:</b> +84 555 666 777</p>
                <p><b>Giờ mở cửa:</b> 9:00 - 21:00 (Thứ Hai - Chủ Nhật)</p>
            </div>
        </div>
    </div>
    <div class="footer">
        &copy; 2024 UTE Shop | Địa chỉ uy tín cho thời trang và mỹ phẩm
    </div>
</body>
</html>
