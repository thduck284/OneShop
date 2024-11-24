<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ page import="models.Product" %>
<%@ page import="models.Category" %>
<%@ page import="models.User" %>
<%@ page import="models.Shop" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Chi tiết sản phẩm</title>
<link rel="stylesheet" type="text/css" href="../static/styles/user/viewDetail.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="mt-5 ml-5 mb-5" style="width: 1300px;">
        <div class="row mb-5">
            <div class="col-md-5 ml-5 image-container">
            	<%
            		String status = (String)request.getAttribute("status");
            		User user = (User) session.getAttribute("userInfor");
            	%>
                <%
                    Product product = (Product) request.getAttribute("product");
                    if (product != null) {
                        String imageBase64 = Base64.getEncoder().encodeToString(product.getImage());
                %>
                    <img src="data:image/jpeg;base64,<%= imageBase64 %>" alt="<%= product.getProductName() %>" class="img-fluid" 
         				style="height: 400px; width: 400px; position: relative;"/>
				<button class="heart-button"
					style="position: absolute; margin: 5px 0 0 -40px; border: none; background-color: transparent;"
					title="Yêu thích"
					onclick="toggleHeart(this,'<%= product != null ? product.getProductId() : "" %>')">
					<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
						viewBox="0 0 25 25" fill="<%= "true".equals(status) ? "red" : "gray" %>" class="heart-icon">
        					<path
							d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z" />
    					</svg>
				</button>
				<%
                    } else {
                %>
                    <p>Không tìm thấy sản phẩm.</p>
                <%
                    }
                %>
            </div>
            <div class="col-lg-5" style="margin-left: -50px;">
                <div class="product-info">
                    <h2 class="product-title"><%= product != null ? product.getProductName() : "Sản phẩm không tồn tại" %></h2>
                    <div class="d-flex align-items-center mb-2">
                        <strong style="color: red; font-size: 20px; margin-right: 10px;">Giá:</strong>
                        <span style="font-size: 20px; color: red;">
                            <fmt:formatNumber value="${product.price}" type="number" pattern="#,###"/> VND
                        </span>
                        <span class="line-info ml-5">|</span>
                        <span style="margin-left: 40px;"><strong>Mã sản phẩm:</strong> <%= product != null ? product.getProductId() : "" %></span>
                    </div>
                    <p><strong>Tên danh mục:</strong> <%= request.getAttribute("category") != null ? ((Category) request.getAttribute("category")).getCategoryName() : "" %></p>
                    <p><strong>Mô tả doanh mục:</strong> <%= request.getAttribute("category") != null ? ((Category) request.getAttribute("category")).getDescription() : "" %></p>
                    <p><strong>Tên cửa hàng:</strong> <%= request.getAttribute("shop") != null ? ((Shop) request.getAttribute("shop")).getShopName() : "" %></p>
                    <div class="mt-5">
                        <strong style="margin-bottom: 20px;">Số lượng:</strong>
                        <div class="d-flex align-items-center">
                        	<button class="btn btn-secondary" onclick="decrementQuantity()">-</button>
                            <input type="number" id="quantity" value="1" min="1" class="form-control mx-2" style="width: 70px; text-align: center;" />
                            <button class="btn btn-secondary" onclick="incrementQuantity()">+</button>
                            <div class="ml-5">
							    <button type="button" class="btn btn-primary mr-5" 
							            onclick="addToCart('<%= product != null ? product.getProductId() : "" %>', document.getElementById('quantity').value, '<%= user != null ? user.getUserId() : "" %>')">
							        Thêm vào giỏ hàng
							    </button>
							    <button type="button" class="btn btn-success"
							            onclick="buyNow('<%= product != null ? product.getProductId() : "" %>', document.getElementById('quantity').value, '<%= user != null ? user.getUserId() : "" %>')">
							        Mua ngay
							    </button>								
							</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container my-4" style="margin-left: -70px;">
		    <ul class="nav nav-tabs" id="myTab" role="tablist">
		        <li class="nav-item" role="presentation">
		            <a class="nav-link active" id="description-tab" data-bs-toggle="tab" href="#description" role="tab" aria-controls="description" aria-selected="true">MÔ TẢ</a>
		        </li>
		        <li class="nav-item" role="presentation">
		            <a class="nav-link" id="payment-policy-tab" data-bs-toggle="tab" href="#payment-policy" role="tab" aria-controls="payment-policy" aria-selected="false">CHÍNH SÁCH THANH TOÁN</a>
		        </li>
		        <li class="nav-item" role="presentation">
		            <a class="nav-link" id="return-policy-tab" data-bs-toggle="tab" href="#return-policy" role="tab" aria-controls="return-policy" aria-selected="false">CHÍNH SÁCH ĐỔI TRẢ</a>
		        </li>
		        <li class="nav-item" role="presentation">
		            <a class="nav-link" id="comments-tab" data-bs-toggle="tab" href="#comments" role="tab" aria-controls="comments" aria-selected="false">BÌNH LUẬN</a>
		        </li>
		    </ul>
		</div>
    </div>
    <div class="tab-content" id="myTabContent" style="color: black; background-color: #7ADAF5; padding: 40px; border: 2px solid gray;">
        <div class="tab-pane fade show active" id="description" role="tabpanel" aria-labelledby="description-tab">
            <%= product != null ? product.getDescription() : "Mô tả sản phẩm không có sẵn." %>
        </div>
        <div class="tab-pane fade" id="payment-policy" role="tabpanel" aria-labelledby="payment-policy-tab">
            <h4>Chính Sách Thanh Toán</h4>
		    <p>Chúng tôi cung cấp nhiều phương thức thanh toán tiện lợi để khách hàng có thể dễ dàng thanh toán cho đơn hàng của mình. Dưới đây là các phương thức thanh toán mà UTESHOP hỗ trợ:</p>
		    <h5>1. Thanh Toán Qua Thẻ Tín Dụng</h5>
		    <p>Khách hàng có thể thanh toán trực tuyến bằng thẻ tín dụng hoặc thẻ ghi nợ quốc tế (Visa, MasterCard, JCB,…) qua cổng thanh toán bảo mật của chúng tôi.</p>
		    <h5>2. Thanh Toán Qua Chuyển Khoản Ngân Hàng</h5>
		    <p>Khách hàng có thể thực hiện thanh toán bằng cách chuyển khoản vào tài khoản ngân hàng của UTESHOP. Sau khi thanh toán, vui lòng gửi thông tin chuyển khoản cho chúng tôi qua email hoặc hệ thống để xác nhận đơn hàng.</p>
		    <h5>3. Thanh Toán Khi Nhận Hàng (COD)</h5>
		    <p>Khách hàng có thể lựa chọn thanh toán khi nhận hàng (COD). Sau khi nhận hàng từ đơn vị vận chuyển, khách hàng có thể thanh toán bằng tiền mặt cho nhân viên giao hàng.</p>
		    <h5>4. Chính Sách Hoàn Tiền</h5>
		    <p>Trong trường hợp bạn muốn hoàn lại sản phẩm, chúng tôi sẽ hoàn lại tiền vào tài khoản của bạn theo phương thức thanh toán đã sử dụng. Thời gian hoàn tiền sẽ được thực hiện trong vòng 5-7 ngày làm việc kể từ khi chúng tôi xác nhận yêu cầu hoàn trả.</p>
		    <h5>5. Lưu Ý</h5>
		    <p>- Mọi giao dịch thanh toán đều được bảo mật và mã hóa.</p>
		    <p>- Nếu có bất kỳ vấn đề gì về thanh toán, vui lòng liên hệ với bộ phận chăm sóc khách hàng của chúng tôi để được hỗ trợ.</p>
        </div>
        <div class="tab-pane fade" id="return-policy" role="tabpanel" aria-labelledby="return-policy-tab">
            Nội dung chính sách đổi trả.
           <p>Chúng tôi cam kết mang lại sự hài lòng cho khách hàng khi mua sắm tại UTESHOP. Nếu bạn không hài lòng với sản phẩm đã mua, bạn có thể đổi trả sản phẩm theo các chính sách dưới đây:</p>
		   <h4>1. Điều Kiện Đổi Trả</h4>
		   <p>- Sản phẩm phải còn nguyên tem, mạc và không bị hư hại, sử dụng hoặc giặt qua.</p>
		   <p>- Sản phẩm phải được đổi trong vòng <strong>7 ngày</strong> kể từ ngày nhận hàng.</p>
		   <p>- Các sản phẩm giảm giá hoặc khuyến mãi không được đổi trả, trừ khi có sự cố do lỗi sản phẩm.</p>			
		   <h4>2. Quy Trình Đổi Trả</h4>
		   <p>- Liên hệ với bộ phận chăm sóc khách hàng của UTESHOP qua số điện thoại <strong>0123 456 789</strong> hoặc email <strong>support@uteshop.com</strong> để yêu cầu đổi trả.</p>
		   <p>- Đóng gói sản phẩm cần đổi trả và gửi lại cho chúng tôi theo địa chỉ sau: <strong>UTESHOP, Số 01 Võ Văn Ngân, Phường Linh Chiểu, Thành phố Thủ Đức, Thành phố Hồ Chí Minh</strong>.</p>
		   <p>- Chúng tôi sẽ kiểm tra tình trạng sản phẩm và xác nhận việc đổi trả trong vòng 2-3 ngày làm việc.</p>			
		   <h4>3. Phí Vận Chuyển</h4>
		   <p>- Nếu đổi trả do lỗi từ phía UTESHOP, chúng tôi sẽ chịu mọi chi phí vận chuyển.</p>
		   <p>- Nếu đổi trả do lý do cá nhân, khách hàng sẽ chịu phí vận chuyển đối với sản phẩm đổi trả.</p>				
		   <h4>4. Hoàn Tiền</h4>
		   <p>- Sau khi sản phẩm được kiểm tra và xác nhận hợp lệ, chúng tôi sẽ hoàn tiền vào tài khoản của bạn trong vòng 5-7 ngày làm việc.</p>
		   <p>- Số tiền hoàn lại sẽ được chuyển qua phương thức thanh toán bạn đã sử dụng khi mua sản phẩm (thẻ tín dụng, chuyển khoản ngân hàng, v.v.).</p>			
		   <h4>5. Lưu Ý</h4>
		   <p>- Sản phẩm đã qua sử dụng hoặc bị hư hại không đủ điều kiện đổi trả.</p>
		   <p>- Chúng tôi chỉ chấp nhận đổi trả sản phẩm tại cửa hàng hoặc thông qua đơn vị vận chuyển mà chúng tôi chỉ định.</p>
		   <p>Chúng tôi luôn nỗ lực để mang đến trải nghiệm mua sắm tuyệt vời cho khách hàng. Nếu có bất kỳ thắc mắc nào về chính sách đổi trả, đừng ngần ngại liên hệ với chúng tôi.</p>
        </div>
        <div class="tab-pane fade" id="comments" role="tabpanel" aria-labelledby="comments-tab">
            Nội dung bình luận.
        </div>
    </div>
	<script>
        function toggleHeart(button, productId) {
            const heartIcon = button.querySelector('.heart-icon');
            const isFilled = heartIcon.getAttribute('fill') === 'red';
            heartIcon.setAttribute('fill', isFilled ? 'gray' : 'red');
            
            const action = isFilled ? 'delete' : 'add';
            
            const userId = "<%= user != null ? user.getUserId() : "" %>"; 
            if (!userId) {
                alert('Bạn cần đăng nhập để thêm vào danh sách yêu thích');
                return; 
            }

            fetch('/OneShop/wish-list', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    action: action,
                    userId: userId,
                    productId: productId
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to update wishlist');
                }
                return response.text();
            })
            .then(data => {
                console.log("Wishlist updated successfully");
            })
            .catch(error => {
                console.error("Error updating wishlist:", error);
            });
        } 
    </script>
    <script type="text/javascript" src="../static/scripts/cart-buy-product.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
