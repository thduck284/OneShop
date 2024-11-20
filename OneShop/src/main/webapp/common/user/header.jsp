<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %> 
<%@ page import="java.util.Base64" %>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<div class="contact">
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-telephone" viewBox="0 0 16 16">
        	<path
				d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.6 17.6 0 0 0 4.168 6.608 17.6 17.6 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.68.68 0 0 0-.58-.122l-2.19.547a1.75 1.75 0 0 1-1.657-.459L5.482 8.062a1.75 1.75 0 0 1-.46-1.657l.548-2.19a.68.68 0 0 0-.122-.58zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.68.68 0 0 0 .178.643l2.457 2.457a.68.68 0 0 0 .644.178l2.189-.547a1.75 1.75 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.6 18.6 0 0 1-7.01-4.42 18.6 18.6 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877z" />
    	</svg>
		<p>Hot line: 0123456789</p>
	</div>
	<div class="header" style="background-color: #7ADAF5;">
		<%
			HttpSession checkSession = request.getSession(false);
		    User loggedInUser = null;
		
		    if (checkSession != null) {
		        loggedInUser = (User) checkSession.getAttribute("userInfor");
		    }
		%>
		<img class="header-image" src="../static/images/logo.png" width=300 height=190>
		<input class="search_input" type="text" placeholder="Nhập từ khóa tìm kiếm sản phẩm, danh mục">
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
  			<path
				d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
		</svg>
		<div class="dropdown" style="margin-left: -45px;">
			<a class="login" href="<%= (loggedInUser == null) ? request.getContextPath() + "/login" : "http://localhost:8080/OneShop/user/home" %>">
				<div class="account-info" style="display: flex; align-items: center;">
		            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
		                <path d="M8 0a4 4 0 1 1 0 8 4 4 0 0 1 0-8zm0 1a3 3 0 1 0 0 6 3 3 0 0 0 0-6zM0 14s1-3 4-3h8c3 0 4 3 4 3v1H0v-1z"/>
		            </svg>
		            <span style="margin: 0 30px 0 5px;">
		                <%
		                    if (loggedInUser != null) {
		                        out.print(loggedInUser.getFullName());
		                    } else {
		                        out.print("Login");
		                    }
		                %>
		            </span>
        		</div>
			</a>
			<% if (loggedInUser != null) { %>
			<div class="dropdown-content" style="margin-left: -5px; min-width: 140px;">
				<a class="login-content" href="http://localhost:8080/OneShop/customer/customer-info">Tài khoản của tôi</a> 
				<a class="login-content" href="<%=request.getContextPath()%>/logout?role=customer">Đăng xuất</a>
			</div>
			<% } else { %>
			<% } %>
		</div>
		<div class="wishlist" style="margin: 25px 20px 0 -50px;">
		    <a href="javascript:void(0)" onclick="togglePopupWishList()" title="Yêu thích">
		        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-heart" viewBox="0 0 16 16">
		            <path d="M8 12s3-3.12 5-5.7C14.42 4.29 13.49 2 11.5 2 10.05 2 8 4.75 8 4.75S6 2 4.5 2C2.51 2 1.58 4.29 3 6.3C5 8.88 8 12 8 12z"/>
		        </svg>
		    </a>
		</div>
		<div id="popupOverlay" class="popup-overlay">
		    <div class="popup-content">
		        <span onclick="togglePopupWishList()" class="close-btn">&times;</span>
		        <h4 class="mt-3">Danh sách các sản phẩm yêu thích</h4>
	        	<div class="wishlist-table-container" style="max-height: 500px; overflow-y: auto;">
			        <table class="table mt-2">
		                <thead>
		                    <tr>
		                        <th>Ảnh</th>
		                        <th>Tên sản phẩm</th>
		                        <th>Giá</th>
		                        <th>Hàng động</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <%
			                    List<models.Product> products = (List<models.Product>) session.getAttribute("wishList");
			                    if (products != null) {
			                        for (models.Product product : products) {
			                            String base64Image = "";
			                            if (product.getImage() != null) {
			                                base64Image = Base64.getEncoder().encodeToString(product.getImage());
			                            }
		                    %>
		                    <tr>
		                        <td>
		                            <% if (!base64Image.isEmpty()) { %>
		                                <img src="data:image/jpeg;base64,<%= base64Image %>" 
		                                     alt="Product Image" style="width:100px; height:100px;" />
		                            <% } else { %>
		                                Không có ảnh
		                            <% } %>
		                        </td>
		                        <td><%= product.getProductName() %></td>
		                        <td>
		                            <fmt:formatNumber value="<%= product.getPrice() %>" type="number" pattern="#,##0" /> đ
		                        </td>
		                        <td>
		                        	<a href="<%= request.getContextPath() %>/user/view-detail-product?productId=<%= product.getProductId() %>&userId=<%= loggedInUser.getUserId() %>" 
   										class="btn btn-primary">Xem chi tiết</a>
								</td>
		                    </tr>
		                    <%
		                            }
		                        } else {
		                    %>
		                    <tr>
		                        <td colspan="4">Không có sản phẩm nào trong danh sách yêu thích.</td>
		                    </tr>
		                    <%
		                        }
		                    %>
		                </tbody>
		           	</table> 
				</div> 
			</div>
		</div>
		<div class="dropdown" style="margin-left: 50px;">
			<div class="box-cart" style="margin-top: -30px;">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="black" class="bi bi-cart" viewBox="0 0 16 16">
	  				<path
						d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2" />
				</svg>
				<a class="cart" href="#" style="margin: 55px 0 0 5px; color: black; text-decoration: none;">Giỏ hàng</a>
				<div class="dropdown-content" style="margin-left: -5px; min-width: 170px;">
					<a class="login-content" href="javascript:void(0)" onclick="togglePopupCart()">Chưa thanh toán</a> 
					<a class="login-content" href="javascript:void(0)" onclick="togglePopupOrder()">Đơn hàng</a>
				</div>
			</div>
		</div>
		<div id="popupCartOverlay" class="popup-overlay">
		    <div class="popup-content">
		    	<span onclick="togglePopupCart()" class="close-btn">&times;</span>
		        <div class="cart-table-container" style="max-height: 500px; overflow-y: auto;">
			        <table class="table mt-2">
		                <thead>
		                    <tr>
		                        <th>Ảnh</th>
		                        <th>Tên sản phẩm</th>
		                        <th>Giá</th>
		                        <th>Hàng động</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <%
			                    /* List<models.Product> products = (List<models.Product>) session.getAttribute("wishList"); */
			                    if (products != null) {
			                        for (models.Product product : products) {
			                            String base64Image = "";
			                            if (product.getImage() != null) {
			                                base64Image = Base64.getEncoder().encodeToString(product.getImage());
			                            }
		                    %>
		                    <tr>
		                        <td>
		                            <% if (!base64Image.isEmpty()) { %>
		                                <img src="data:image/jpeg;base64,<%= base64Image %>" 
		                                     alt="Product Image" style="width:100px; height:100px;" />
		                            <% } else { %>
		                                Không có ảnh
		                            <% } %>
		                        </td>
		                        <td><%= product.getProductName() %></td>
		                        <td>
		                            <fmt:formatNumber value="<%= product.getPrice() %>" type="number" pattern="#,##0" /> đ
		                        </td>
		                        <td>
		                        	<a href="<%= request.getContextPath() %>/user/view-detail-product?productId=<%= product.getProductId() %>&userId=<%= loggedInUser.getUserId() %>" 
   										class="btn btn-primary">Xem chi tiết</a>
								</td>
		                    </tr>
		                    <%
		                            }
		                        } else {
		                    %>
		                    <tr>
		                        <td colspan="4">Không có sản phẩm nào trong giỏ hàng.</td>
		                    </tr>
		                    <%
		                        }
		                    %>
		                </tbody>
		           	</table> 
				</div> 
			</div>
		</div>
		<div id="popupOrderOverlay" class="popup-overlay">
		    <div class="popup-content">
		    	<span onclick="togglePopupOrder()" class="close-btn">&times;</span>
		        <div class="order-table-container" style="max-height: 500px; overflow-y: auto;">
			        <table class="table mt-2">
		                <thead>
		                    <tr>
		                        <th>Ảnh</th>
		                        <th>Tên sản phẩm</th>
		                        <th>Giá</th>
		                        <th>Hàng động</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <%
			                    /* List<models.Product> products = (List<models.Product>) session.getAttribute("wishList"); */
			                    if (products != null) {
			                        for (models.Product product : products) {
			                            String base64Image = "";
			                            if (product.getImage() != null) {
			                                base64Image = Base64.getEncoder().encodeToString(product.getImage());
			                            }
		                    %>
		                    <tr>
		                        <td>
		                            <% if (!base64Image.isEmpty()) { %>
		                                <img src="data:image/jpeg;base64,<%= base64Image %>" 
		                                     alt="Product Image" style="width:100px; height:100px;" />
		                            <% } else { %>
		                                Không có ảnh
		                            <% } %>
		                        </td>
		                        <td><%= product.getProductName() %></td>
		                        <td>
		                            <fmt:formatNumber value="<%= product.getPrice() %>" type="number" pattern="#,##0" /> đ
		                        </td>
		                        <td>
		                        	<a href="<%= request.getContextPath() %>/user/view-detail-product?productId=<%= product.getProductId() %>&userId=<%= loggedInUser.getUserId() %>" 
   										class="btn btn-primary">Xem chi tiết</a>
								</td>
		                    </tr>
		                    <%
		                            }
		                        } else {
		                    %>
		                    <tr>
		                        <td colspan="4">Chưa từng mua sản phẩm nào.</td>
		                    </tr>
		                    <%
		                        }
		                    %>
		                </tbody>
		           	</table> 
				</div> 
			</div>
		</div>  
	</div>
	<div class="under-header" style="background-color: #7ADAF5; margin: 20px 0 -35px 120px;">
		<a class="product" href="http://localhost:8080/OneShop/user/home" style="margin-right: 30px;">Trang chủ</a>
		<div class="dropdown">
			<a class="product" href="#">Túi sách</a>
			<div class="dropdown-content">
				<a href="#">Túi xách tay</a> 
				<a href="#">Túi đeo chéo</a> 
				<a href="#">Túi ba lô</a> 
				<a href="#">Túi cầm tay</a> 
				<a href="#">Túi đi du lịch</a>
			</div>
		</div>
		<div class="dropdown">
			<a class="product" href="#">Nước hoa</a>
			<div class="dropdown-content">
				<a href="#">Nước hoa Unisex</a> 
				<a href="#">Nước hoa Niche</a> 
				<a href="#">Nước hoa Mini</a> 
				<a href="#">Nước hoa Intense</a> 
				<a href="#">Nước hoa dạng xịt</a> 
				<a href="#">Nước hoa lăn</a> 
			</div>
		</div>
		<div class="dropdown">
			<a class="product" href="#">Mỹ phẩm</a>
			<div class="dropdown-content">
				<a href="#">Chăm sóc da mặt</a>
				<a href="#">Tẩy tế bào chết</a> 
				<a href="#">Kem chống nắng</a> 
				<a href="#">Kem dưỡng da</a> 
				<a href="#">Kem dưỡng ẩm</a> 
				<a href="#">Serum dưỡng da</a>
			</div>
		</div>
		<div class="dropdown">
			<a class="product" href="#">Son môi</a>
			<div class="dropdown-content">
				<a href="#">Son li</a> 
				<a href="#">Son kem</a> 
				<a href="#">Son dưỡng</a> 
				<a href="#">Son nước</a> 
				<a href="#">Son tint</a> 
				<a href="#">Son bóng</a> 
			</div>
		</div>
		<div class="dropdown">
			<a class="product" href="#">Trang sức</a>
			<div class="dropdown-content">
				<a href="#">Dây chuyền</a> 
				<a href="#">Bông tai</a> 
				<a href="#">Vòng tay</a> 
				<a href="#">Khuyên tai</a> 
				<a href="#">Nhẫn</a> 
				<a href="#">Vòng đeo tay</a> 
			</div>
		</div>
		<div class="dropdown">
			<a class="product" href="#">Thời trang</a>
			<div class="dropdown-content">
				<a href="#">Áo sơ mi</a> 
				<a href="#">Áo thun</a> 
				<a href="#">Áo polo</a> 
				<a href="#">Áo phông</a>
				<a href="#">Quần jeans</a> 
				<a href="#">Quần short</a>
			</div>
		</div>
		<div class="dropdown">
			<a class="promotion" href="#">Khuyến mãi</a>
		</div>
	</div>
	
	<script>
	    function togglePopupWishList() {
	        const userId = "<%= loggedInUser != null ? loggedInUser.getUserId() : "" %>"; 
            if (!userId) {
                alert('Bạn cần đăng nhập để thêm vào danh sách yêu thích');
                return; 
            }
            
            var popup = document.getElementById("popupOverlay");
	        if (popup.style.display === "none" || popup.style.display === "") {
	            popup.style.display = "block";
	        } else {
	            popup.style.display = "none";
	        }
			
		    fetch('/OneShop/view-wish-list', {
		        method: 'POST',
		        headers: {
		            'Content-Type': 'application/x-www-form-urlencoded'
		        },
		        body: new URLSearchParams({
                    userId: userId,
                })
		    })
		    .then(response => {
		        if (response.ok) {
		            console.log('User ID sent successfully and wishlist saved to session.');
		        } else {
		            console.error('Failed to send User ID.');
		        }
		    })
		    .catch(error => console.error('Error:', error));
	    }
	    
	    function togglePopupCart() {
	        const userId = "<%= loggedInUser != null ? loggedInUser.getUserId() : "" %>"; 
            if (!userId) {
                alert('Bạn cần đăng nhập để thêm vào danh sách yêu thích');
                return; 
            }
            
            var popup = document.getElementById("popupCartOverlay");
	        if (popup.style.display === "none" || popup.style.display === "") {
	            popup.style.display = "block";
	        } else {
	            popup.style.display = "none";
	        }
			
		    /* fetch('/OneShop/view-wish-list', {
		        method: 'POST',
		        headers: {
		            'Content-Type': 'application/x-www-form-urlencoded'
		        },
		        body: new URLSearchParams({
                    userId: userId,
                })
		    })
		    .then(response => {
		        if (response.ok) {
		            console.log('User ID sent successfully and wishlist saved to session.');
		        } else {
		            console.error('Failed to send User ID.');
		        }
		    })
		    .catch(error => console.error('Error:', error)); */
	    }
	    
	    function togglePopupOrder() {
	        const userId = "<%= loggedInUser != null ? loggedInUser.getUserId() : "" %>"; 
            if (!userId) {
                alert('Bạn cần đăng nhập để thêm vào danh sách yêu thích');
                return; 
            }
            
            var popup = document.getElementById("popupOrderOverlay");
	        if (popup.style.display === "none" || popup.style.display === "") {
	            popup.style.display = "block";
	        } else {
	            popup.style.display = "none";
	        }
			
		    /* fetch('/OneShop/view-wish-list', {
		        method: 'POST',
		        headers: {
		            'Content-Type': 'application/x-www-form-urlencoded'
		        },
		        body: new URLSearchParams({
                    userId: userId,
                })
		    })
		    .then(response => {
		        if (response.ok) {
		            console.log('User ID sent successfully and wishlist saved to session.');
		        } else {
		            console.error('Failed to send User ID.');
		        }
		    })
		    .catch(error => console.error('Error:', error)); */
	    }
	</script>
</body>
</html>