<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<title>Forget Password</title>
</head>
<body style="background-color: #F6BCBA;">
    <form id="forgetPasswordForm" class="container p-4 border rounded shadow-lg" style="margin-top: 100px; height: 500px; max-width: 700px; background-color: #E3AADD;">
	   <h1 class="text-center mb-5">Quên mật khẩu</h1>
	   <div class="mb-4">
	       <label for="email" class="form-label">Email:</label>
	       <input type="email" name="email" id="email" class="form-control" placeholder="Nhập email" required />
	   </div>
	   <div class="mb-4">
	       <label for="pw" class="form-label">Nhập mật khẩu mới:</label>
	       <input type="password" name="pw" id="pw" class="form-control" placeholder="Nhập mật khẩu mới" required />
	   </div>
	   <div class="mb-4">
	       <label for="confirmPw" class="form-label">Xác nhận mật khẩu:</label>
	       <input type="password" name="confirmPw" id="confirmPw" class="form-control" placeholder="Xác nhận mật khẩu" required />
	   </div>
	   <div id="responseMessage" class="mt-1 text-left" style="color: red;"></div>
	   <button type="button" id="submitButton" class="mt-3 btn btn-primary w-100">Xác nhận</button>
	</form>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#submitButton').click(function () {
                const email = $('#email').val();
                const pw = $('#pw').val();
                const confirmPw = $('#confirmPw').val();

                $.ajax({
                    url: '/OneShop/forget-password',
                    type: 'POST',
                    data: {
                        email: email,
                        pw: pw,
                        confirmPw: confirmPw
                    },
                    success: function (response) {
                        if (response.success) {
                            $('#responseMessage').css('color', 'green');
                            $('#responseMessage').text(response.message);
                        } else {
                            $('#responseMessage').css('color', 'red');
                            $('#responseMessage').text(response.message);
                        }
                    },
                    error: function () {
                        $('#responseMessage').text('Đã xảy ra lỗi, vui lòng thử lại sau.');
                    }
                });
            });
        });
    </script>
</body>
</html>