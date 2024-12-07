<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Các Câu Hỏi Thường Gặp - UTE Shop</title>
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
        padding: 15px 0;
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
    .faq-section {
        margin-top: 20px;
    }
    .faq-item {
        margin-bottom: 20px;
        border-bottom: 1px solid #ddd;
        padding-bottom: 10px;
    }
    .faq-item h3 {
        margin: 0;
        color: #555;
        cursor: pointer;
    }
    .faq-item p {
        margin: 5px 0;
        color: #777;
        display: none;
    }
    .faq-item h3:hover {
        color: #007BFF;
    }
    .footer {
        background-color: #333;
        color: #fff;
        text-align: center;
        padding: 10px 0;
        margin-top: 20px;
    }
</style>
<script>
    function toggleAnswer(id) {
        var answer = document.getElementById(id);
        if (answer.style.display === "none") {
            answer.style.display = "block";
        } else {
            answer.style.display = "none";
        }
    }
</script>
</head>
<body>
    <header>
        <h1>Các Câu Hỏi Thường Gặp</h1>
        <p>Giải đáp nhanh các thắc mắc của bạn</p>
    </header>
    <div class="container">
        <div class="faq-section">
            <div class="faq-item">
                <h3 onclick="toggleAnswer('faq1')">1. Làm thế nào để đặt hàng tại UTE Shop?</h3>
                <p id="faq1">Bạn có thể đặt hàng trực tiếp trên website của chúng tôi hoặc đến các chi nhánh gần nhất để mua sắm. Chúng tôi cung cấp nhiều phương thức thanh toán để tiện lợi nhất cho bạn.</p>
            </div>
            <div class="faq-item">
                <h3 onclick="toggleAnswer('faq2')">2. Chính sách đổi trả sản phẩm như thế nào?</h3>
                <p id="faq2">Sản phẩm có thể được đổi trả trong vòng 7 ngày kể từ ngày nhận hàng với điều kiện còn nguyên vẹn và có hóa đơn mua hàng.</p>
            </div>
            <div class="faq-item">
                <h3 onclick="toggleAnswer('faq3')">3. Tôi có thể kiểm tra tình trạng đơn hàng không?</h3>
                <p id="faq3">Bạn có thể kiểm tra tình trạng đơn hàng bằng cách đăng nhập vào tài khoản của mình trên website hoặc liên hệ với tổng đài chăm sóc khách hàng của chúng tôi.</p>
            </div>
            <div class="faq-item">
                <h3 onclick="toggleAnswer('faq4')">4. UTE Shop có chương trình khuyến mãi không?</h3>
                <p id="faq4">Chúng tôi thường xuyên tổ chức các chương trình khuyến mãi. Hãy theo dõi website và các kênh truyền thông của chúng tôi để không bỏ lỡ ưu đãi!</p>
            </div>
            <div class="faq-item">
                <h3 onclick="toggleAnswer('faq5')">5. Tôi cần hỗ trợ, liên hệ thế nào?</h3>
                <p id="faq5">Bạn có thể liên hệ qua hotline: +84 123 456 789 hoặc email: support@shop.com. Chúng tôi luôn sẵn sàng hỗ trợ bạn.</p>
            </div>
        </div>
    </div>
    <div class="footer">
        &copy; 2024 UTE Shop | Đáp ứng mọi nhu cầu thời trang và mỹ phẩm của bạn
    </div>
</body>
</html>
