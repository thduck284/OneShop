<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %> 
<%@ page import="java.util.Base64" %>
<%@ page import="javax.swing.table.DefaultTableModel" %>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css">
<script type="text/javascript" src="../static/scripts/user/header.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="contact">
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-telephone" viewBox="0 0 16 16">
        	<path
				d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.6 17.6 0 0 0 4.168 6.608 17.6 17.6 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.68.68 0 0 0-.58-.122l-2.19.547a1.75 1.75 0 0 1-1.657-.459L5.482 8.062a1.75 1.75 0 0 1-.46-1.657l.548-2.19a.68.68 0 0 0-.122-.58zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.68.68 0 0 0 .178.643l2.457 2.457a.68.68 0 0 0 .644.178l2.189-.547a1.75 1.75 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.6 18.6 0 0 1-7.01-4.42 18.6 18.6 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877z" />
    	</svg>
		<p>Hot line: 10101110011111110011010101110</p>
		<p style="margin-left: 420px;">Cái nết đánh chết cái đẹp, cái đẹp đánh dẹp cái túi  -  服饰是外壳，风格是灵魂</p>
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
		<form action="<%=request.getContextPath()%>/user/search-product" method="GET">
		    <input class="search_input" type="text" name="keyword" placeholder="Nhập từ khóa tìm kiếm sản phẩm, danh mục" required>
		    <button type="submit" style="display: none;">
		        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
		            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
		        </svg>
		    </button>
		</form>
		<div class="dropdown" style="margin-left: 80px;">
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
				<a class="login-content" data-toggle="modal" data-target="#exampleModal" id="openModalButton">
				    <i class="ft-user"></i> Tài khoản của tôi
				</a>
				<a class="login-content" href="<%=request.getContextPath()%>/logout?role=customer">Đăng xuất</a>
			</div>
			<% } else { %>
			<% } %>
		</div>
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		    <div class="modal-dialog modal-lg" role="document" style="width: 650px; margin-top: 50px;">
		        <div class="modal-content">
		            <div class="modal-header" style="background-color: #0C6478;">
		                <h5 class="modal-title" id="exampleModalLabel" style="color: white;">Chỉnh sửa thông tin khách hàng</h5>
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true">&times;</span>
		                </button>
		            </div>
		            <div class="modal-body" style="background-color: #15919B;">
		                <h3 style="text-align: center; color: white;">Thông tin khách hàng</h3>
		                <form id="vendorForm">
		                	<div class="form-group">
					            <label for="userId" style="color: white;">Mã khách hàng:</label>
					            <strong id="userId" class="form-control"><%= loggedInUser.getUserId() %></strong>
					        </div>
		                    <div class="form-group" style="margin-top: 15px;">
		                        <label for="userName" style="color: white;">Tên tài khoản:</label>
		                        <input type="text" class="form-control" id="userName">
		                    </div>
		                    <div class="form-group" style="margin-top: 15px;">
		                        <label for="email" style="color: white;">Email:</label>
		                        <input type="email" class="form-control" id="email">
		                    </div>
		                    <div class="form-group" style="margin-top: 15px;">
		                        <label for="fullName" style="color: white;">Họ và tên:</label>
		                        <input type="text" class="form-control" id="fullName">
		                    </div>
		                    <div class="form-group" style="margin-top: 15px;">
		                        <label for="phoneNumber" style="color: white;">Số điện thoại:</label>
		                        <input type="text" class="form-control" id="phoneNumber">
		                    </div>
		                    <div class="form-group" style="margin-top: 15px;">
		                        <label for="address" style="color: white;">Địa chỉ:</label>
		                        <input type="text" class="form-control" id="address">
		                    </div>
		                </form>
		            </div>
		            <div class="modal-footer" style="background-color: #0C6478;">
		                <button type="button" class="btn-primary" style="padding: 5px 25px;" data-dismiss="modal" aria-label="Close">
		                	<span aria-hidden="true">Đóng</span>
		                </button>
		                <button type="button" class="btn-primary" id="saveChangesButton" style="padding: 5px 25px; background-color: #20c997;">Lưu thay đổi</button>
		            </div>
		        </div>
		    </div>
		</div>
		<div class="wishlist" style="margin: 25px 20px 0 -50px;">
		    <a href="javascript:void(0)" title="Yêu thích">
		        <svg xmlns="http://www.w3.org/2000/svg" onclick="togglePopupWishList()" width="20" height="20" fill="red" class="bi bi-heart" viewBox="0 0 16 16">
		            <path d="M8 12s3-3.12 5-5.7C14.42 4.29 13.49 2 11.5 2 10.05 2 8 4.75 8 4.75S6 2 4.5 2C2.51 2 1.58 4.29 3 6.3C5 8.88 8 12 8 12z"/>
		        </svg>
		    </a>
		</div>
		<div id="popupOverlay" class="popup-overlay">
		    <div class="popup-content">
		        <span onclick="closeButton()" class="close-btn">&times;</span>
		        <h4 class="mt-3">Danh sách các sản phẩm yêu thích</h4>
	        	<div class="wishlist-table-container" style="max-height: 500px; overflow-y: auto;">
			        <table class="table mt-2">
		                <thead>
		                    <tr>
		                        <th>Ảnh</th>
		                        <th>Tên sản phẩm</th>
		                        <th>Giá</th>
		                        <th></th>
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
		    <div class="popup-content" style="width: 1100px;">
		    	<span onclick="closeCartButton()" class="close-btn">&times;</span>
		    	<h4 class="mt-3">Danh sách các sản phẩm trong giỏ hàng</h4>
		        <div class="cart-table-container" style="max-height: 450px; overflow-y: auto;">
				    <table class="table mt-2">
				        <thead>
				            <tr>
				                <th>Ảnh</th>
				                <th>Tên sản phẩm</th>
				                <th>Giá</th>
				                <th>Số lượng</th>
				                <th>Tổng tiền</th>
				                <th></th>
				                <th>Trạng thái</th>
				                <th></th>
				            </tr>
				        </thead>
				        <tbody class="mt-5">
				            <%
				                DefaultTableModel cartTable = (DefaultTableModel) session.getAttribute("cartTable");
				                if (cartTable != null && cartTable.getRowCount() > 0) {
				                    for (int i = 0; i < cartTable.getRowCount(); i++) {
				                        String base64Image = (String) cartTable.getValueAt(i, 0);
				                        String productId = (String) cartTable.getValueAt(i,1);
				                        String productName = (String) cartTable.getValueAt(i, 2);
				                        int price = (int) cartTable.getValueAt(i, 3);
				                        int quantity = (int) cartTable.getValueAt(i, 4);
				                        int status = (int) cartTable.getValueAt(i, 5);
				                        int totalPrice = price * quantity;
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
				                <td><%= productName %></td>
				                <td>
				                    <fmt:formatNumber value="<%= price %>" type="number" pattern="#,##0" /> đ
				                </td>
				                <td><%= quantity %></td>
				                <td>
		                            <fmt:formatNumber value="<%= totalPrice %>" type="number" pattern="#,##0" /> đ
		                        </td>
		                        <td>
								    <a href="<%= request.getContextPath() %>/user/view-detail-product?productId=<%= productId %>&userId=<%= loggedInUser.getUserId() %>" 
								       class="btn btn-primary">Xem chi tiết</a>
								</td>
				                <td>
		                            <button id="payButton<%= i %>" class="btn btn-success"
									        onclick="processPayment(<%= i %>, <%= status %>, '<%= productId %>', <%= quantity %>)"
									        data-status="<%= status %>">
									    <%= (status == 1) ? "Đang giao hàng" : "Thanh toán" %>
									</button>
		                        </td>
		                        <td>
						            <button class="btn btn-danger" onclick="removeProduct('<%= productId %>')">Xóa</button>
						        </td>
				            </tr>
				            <%
				                    }
				                } else {
				            %>
				            <tr>
				                <td colspan="5">Không có sản phẩm nào trong giỏ hàng.</td>
				            </tr>
				            <%
				                }
				            %>
				        </tbody>
				    </table>
				</div>
				<div class="text-center mt-3">
	                <button class="btn btn-success" onclick="processFinalPayment()">
	                    Thanh toán tất cả
	                </button>
	            </div>
			</div>
		</div>
		<div id="popupOrderOverlay" class="popup-overlay">
		    <div class="popup-content" style="width: 1100px;">
		    	<span onclick="closeOrderButton()" class="close-btn">&times;</span>
		    	<h4 class="mt-3">Danh sách các sản phẩm đã mua</h4>
		        <div class="order-table-container" style="max-height: 450px; overflow-y: auto;">
			        <table class="table mt-2">
		                <thead>
				            <tr>
				            	<th>Ảnh</th>
				                <th>Tên sản phẩm</th>
				                <th>Giá</th>
				                <th>Số lượng</th>
				                <th>Tổng tiền</th>
				                <th>Phương thức TT</th>
				                <th></th>
				            </tr>
				        </thead>
				        <tbody class="mt-5">
				            <%
				                DefaultTableModel orderTable = (DefaultTableModel) session.getAttribute("tbOrder");
				                if (orderTable != null && orderTable.getRowCount() > 0) {
				                    for (int i = 0; i < orderTable.getRowCount(); i++) {
				                    	String base64Image = (String) orderTable.getValueAt(i, 0);
				                    	String productId = (String) orderTable.getValueAt(i,1);
				                        String productName = (String) orderTable.getValueAt(i, 2);
				                        int price = (int) orderTable.getValueAt(i, 3);
				                        int quantity = (int) orderTable.getValueAt(i, 4);
				                        int totalPrice = price * quantity;
				                        String paymentMethod = (String) orderTable.getValueAt(i, 5);
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
				                <td><%= productName %></td>
				                <td>
				                    <fmt:formatNumber value="<%= price %>" type="number" pattern="#,##0" /> đ
				                </td>
				                <td><%= quantity %></td>
				                <td>
		                            <fmt:formatNumber value="<%= totalPrice %>" type="number" pattern="#,##0" /> đ
		                        </td>
		                        <td><%= paymentMethod %></td>
		                        <td>
								    <a href="<%= request.getContextPath() %>/user/view-detail-product?productId=<%= productId %>&userId=<%= loggedInUser.getUserId() %>" 
								       class="btn btn-primary">Xem chi tiết</a>
								</td>
				            </tr>
				            <%
				                    }
				                } else {
				            %>
				            <tr>
				                <td colspan="5">Không có sản phẩm nào đã thanh toán.</td>
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
		<a class="product" href="http://localhost:8080/OneShop/user/product" style="margin-right: 30px;">Trang chủ</a>
		<div class="dropdown"> 
		    <a class="product" href="<%= request.getContextPath() %>/user/product-by-category?category=group1">Túi sách</a>
		    <div class="dropdown-content">
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE1">Túi xách tay</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE2">Túi đeo chéo</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE3">Túi ba lô</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE4">Túi cầm tay</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE5">Túi đi du lịch</a>
		    </div>
		</div>    
		<div class="dropdown">
		    <a class="product" href="<%= request.getContextPath() %>/user/product-by-category?category=group2">Nước hoa</a>
		    <div class="dropdown-content">
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE6">Nước hoa Unisex</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE7">Nước hoa Niche</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE8">Nước hoa Mini</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE9">Nước hoa Intense</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE10">Nước hoa dạng xịt</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE11">Nước hoa lăn</a> 
		    </div>
		</div>    
		<div class="dropdown">
		    <a class="product" href="<%= request.getContextPath() %>/user/product-by-category?category=group3">Mỹ phẩm</a>
		    <div class="dropdown-content">
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE12">Chăm sóc da mặt</a>
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE13">Tẩy tế bào chết</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE14">Kem chống nắng</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE15">Kem dưỡng da</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE16">Kem dưỡng ẩm</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE17">Serum dưỡng da</a>
		    </div>
		</div>    
		<div class="dropdown">
		    <a class="product" href="<%= request.getContextPath() %>/user/product-by-category?category=group4">Son môi</a>
		    <div class="dropdown-content">
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE18">Son li</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE19">Son kem</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE20">Son dưỡng</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE21">Son nước</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE22">Son tint</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE23">Son bóng</a> 
		    </div>
		</div>    
		<div class="dropdown">
		    <a class="product" href="<%= request.getContextPath() %>/user/product-by-category?category=group5">Trang sức</a>
		    <div class="dropdown-content">
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE24">Dây chuyền</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE25">Bông tai</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE26">Vòng tay</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE27">Khuyên tai</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE28">Nhẫn</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE29">Vòng đeo tay</a> 
		    </div>
		</div>        
		<div class="dropdown">
		    <a class="product" href="<%= request.getContextPath() %>/user/product-by-category?category=group6">Thời trang</a>
		    <div class="dropdown-content">
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE30">Áo sơ mi</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE31">Áo thun</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE32">Áo polo</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE33">Áo phông</a>
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE34">Quần jeans</a> 
		        <a href="<%= request.getContextPath() %>/user/product-by-category?category=CATE35">Quần short</a>
		    </div>
		</div>
		<div class="dropdown">
			<a class="promotion" href="#">Khuyến mãi</a>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"></script>
	<script>
	    function fetchCustomerInfo() {
	        $.ajax({
	            url: '/OneShop/user/infor-customer', 
	            type: 'GET',
	            dataType: 'json',
	            success: function(data) {
	            	$('#userId').val(data.userId);
	                $('#userName').val(data.userName);
	                $('#email').val(data.email);
	                $('#fullName').val(data.fullName);
	                $('#phoneNumber').val(data.phoneNumber);
	                $('#address').val(data.address);
	                $('#exampleModal').modal('show');
	            },
	            error: function(xhr, status, error) {
	                alert("Có lỗi xảy ra khi lấy dữ liệu.");
	            }
	        });
	    }
	    
	    function saveCustomerInfo() {
	        var customerData = {
	            userName: $('#userName').val(),
	            email: $('#email').val(),
	            fullName: $('#fullName').val(),
	            phoneNumber: $('#phoneNumber').val(),
	            address: $('#address').val(),
	        };   
	        $.ajax({
	            url: '/OneShop/user/infor-customer',  
	            type: 'POST',
	            dataType: 'json',
	            data: customerData,  
	            success: function(response) {
	                if (response.success) {
	                    alert("Cập nhật thông tin thành công!");
	                    $('#exampleModal').modal('hide');
	                } else {
	                    alert("Có lỗi xảy ra khi cập nhật thông tin.");
	                }
	            },
	            error: function(xhr, status, error) {
	                alert("Có lỗi xảy ra khi gửi dữ liệu.");
	            }
	        });
	    }
	    
	    $(document).ready(function() {	    	
	    	$('#exampleModal').on('hidden.bs.modal', function () {
	    	    $('.modal-backdrop').remove(); 
	    	    $('body').removeClass('modal-open'); 
	    	});
	    	
	        $('#openModalButton').on('click', function() {
	            fetchCustomerInfo();
	        });
	        
	        $('#saveChangesButton').on('click', function() {
	            saveCustomerInfo();
	        });
	    });
	</script>
	<script>
		const userId = "<%= loggedInUser != null ? loggedInUser.getUserId() : "" %>";
		function togglePopup(popupId, localStorageKey, apiEndpoint) {
		    const token = localStorage.getItem('customerToken');
		    
		    if (!userId) {
		        alert('Bạn cần đăng nhập để thực hiện hành động này');
		        return;
		    }

		    if (!token) {
		        alert('Token không tồn tại. Vui lòng đăng nhập lại.');
		        return;
		    }

		    const popup = document.getElementById(popupId);

		    if (popup.style.display === "none" || popup.style.display === "") {
		        popup.style.display = "block";
		    } else {
		        popup.style.display = "none";
		    }

		    if (apiEndpoint) {
		        fetch(apiEndpoint, {
		            method: 'POST',
		            headers: {
		                'Content-Type': 'application/x-www-form-urlencoded',
		                'Authorization': 'Bearer ' + token
		            },
		            body: new URLSearchParams({
		                userId: userId
		            })
		        })
		        .then(response => {
		            if (response.ok) {
		                return response.json();
		            } else if (response.status === 401) {
		                alert('Token không hợp lệ hoặc hết hạn. Vui lòng đăng nhập lại.');
		                localStorage.removeItem('customerToken');
		                window.location.href = '/OneShop/login';
		            } else {
		                console.error('Lỗi khi gửi yêu cầu, mã lỗi: ' + response.status);
		                alert('Có lỗi xảy ra khi kết nối với hệ thống. Vui lòng thử lại sau.');
		            }
		        })
		        .catch(error => {
		            console.error('Lỗi:', error);
		        })
		        .finally(() => {
		            localStorage.setItem(localStorageKey, 'true');
		            setTimeout(() => {
		                location.reload();  
		            }, 0);
		        });
		    }
		}

		function closePopup(popupId, localStorageKey) {
		    var popup = document.getElementById(popupId);
		    if (popup.style.display === "none" || popup.style.display === "") {
		        popup.style.display = "block";
		        localStorage.setItem(localStorageKey, 'false');
		    } else {
		        popup.style.display = "none";
		    }
		}

		window.onload = function() {
		    if (localStorage.getItem('showPopup') === 'true') {
		        var popup = document.getElementById("popupOverlay");
		        popup.style.display = "block";
		        localStorage.setItem('showPopup', 'false');
		    }
		    if (localStorage.getItem('showCartPopup') === 'true') {
		        var popupCart = document.getElementById("popupCartOverlay");
		        popupCart.style.display = "block";
		        localStorage.setItem('showCartPopup', 'false');
		    }
		    if (localStorage.getItem('showOrderPopup') === 'true') {
		        var popupCart = document.getElementById("popupOrderOverlay");
		        popupCart.style.display = "block";
		        localStorage.setItem('showOrderPopup', 'false');
		    }
		};

		function togglePopupWishList() {
		    togglePopup("popupOverlay", "showPopup", "/OneShop/view-wish-list");
		}

		function togglePopupCart() {
		    togglePopup("popupCartOverlay", "showCartPopup", "/OneShop/view-cart");
		}
		
		function togglePopupOrder() {
		    togglePopup("popupOrderOverlay", "showOrderPopup", "/OneShop/view-order");
		}

		function closeButton() {
		    closePopup("popupOverlay", "showPopup");
		}

		function closeCartButton() {
		    closePopup("popupCartOverlay", "showCartPopup");
		}
		
		function closeOrderButton() {
		    closePopup("popupOrderOverlay", "showOrderPopup");
		}
		
		function processPayment(index, status, productId, quantity) {
			const token = localStorage.getItem('customerToken');

		    if (!userId) {
		        alert('Bạn cần đăng nhập để thanh toán.');
		        return;
		    }

		    if (!token) {
		        alert('Token không tồn tại. Vui lòng đăng nhập lại.');
		        window.location.href = '/OneShop/login';
		        return;
		    }
			
		    fetch('/OneShop/awaiting-payment', {
		        method: 'POST',
		        headers: {
		        	'Content-Type': 'application/x-www-form-urlencoded',
		            'Authorization': 'Bearer ' + token
		        },
		        body: new URLSearchParams({
		        	userId: userId,      
		            productId: productId,
		            quantity: quantity,
		            status: status 
		        })
		    })
		    .then(response => {
		        if (response.ok) {
		        	window.location.href = '/OneShop/awaiting-payment';
		        } else if (response.status === 401) {
		            alert('Token không hợp lệ hoặc đã hết hạn. Vui lòng đăng nhập lại.');
		            localStorage.removeItem('customerToken');
		            window.location.href = '/OneShop/login';
		        } else {
		            console.error('Lỗi thanh toán: ' + response.status);
		            alert('Có lỗi xảy ra khi thanh toán. Vui lòng thử lại sau.');
		        }
		    })
		    .catch(error => {
		        console.error('Lỗi:', error);
		        alert('Có lỗi xảy ra khi kết nối với hệ thống.');
		    });
		}
		
		function processFinalPayment() {
			const token = localStorage.getItem('customerToken');

		    if (!userId) {
		        alert('Bạn cần đăng nhập để thanh toán.');
		        return;
		    }

		    if (!token) {
		        alert('Token không tồn tại. Vui lòng đăng nhập lại.');
		        window.location.href = '/OneShop/login';
		        return;
		    }
			
		    fetch('/OneShop/awaiting-payment', {
		        method: 'POST',
		        headers: {
		        	'Content-Type': 'application/x-www-form-urlencoded',
		            'Authorization': 'Bearer ' + token
		        },
		        body: new URLSearchParams({
		        	userId: userId     
		        })
		    })
		    .then(response => {
		        if (response.ok) {
		        	window.location.href = '/OneShop/awaiting-payment';
		        } else if (response.status === 401) {
		            alert('Token không hợp lệ hoặc đã hết hạn. Vui lòng đăng nhập lại.');
		            localStorage.removeItem('customerToken');
		            window.location.href = '/OneShop/login';
		        } else {
		            console.error('Lỗi thanh toán: ' + response.status);
		            alert('Có lỗi xảy ra khi thanh toán. Vui lòng thử lại sau.');
		        }
		    })
		    .catch(error => {
		        console.error('Lỗi:', error);
		        alert('Có lỗi xảy ra khi kết nối với hệ thống.');
		    });
		}
		
		function removeProduct(productId) {
	        const token = localStorage.getItem('customerToken');
	        
	        if (!token) {
	            alert('Bạn cần đăng nhập để xóa sản phẩm.');
	            return;
	        }

	        const confirmation = confirm('Bạn có chắc chắn muốn xóa sản phẩm này không?');
	        if (!confirmation) {
	            return; 
	        }

	        fetch('/OneShop/remove-product', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/x-www-form-urlencoded',
	                'Authorization': 'Bearer ' + token
	            },
	            body: new URLSearchParams({
	                productId: productId
	            })
	        })
	        .then(response => {
	            if (response.ok) {
	                alert('Sản phẩm đã được xóa thành công.');
	            } else {
	                alert('Có lỗi xảy ra khi xóa sản phẩm. Vui lòng thử lại.');
	            }
	        })
	        .catch(error => {
	            console.error('Lỗi:', error);
	            alert('Có lỗi xảy ra khi xóa sản phẩm. Vui lòng thử lại.');
	        });
	    }
	</script>
</body>
</html>
