<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Header</title>
</head>
<body class="vertical-layout vertical-compact-menu 2-columns menu-expanded fixed-navbar" data-open="click" data-menu="vertical-compact-menu" data-col="2-columns">
    <nav class="header-navbar navbar-expand-md navbar navbar-with-menu navbar-without-dd-arrow fixed-top navbar-light navbar-bg-color">
        <div class="navbar-wrapper">
            <div class="navbar-header d-md-none">
                <ul class="nav navbar-nav flex-row">
                    <li class="nav-item mobile-menu d-md-none mr-auto">
                        <a class="nav-link nav-menu-main menu-toggle hidden-xs" href="#">
                            <i class="ft-menu font-large-1"></i>
                        </a>
                    </li>
                    <li class="nav-item d-md-none">
                        <a class="navbar-brand" href="#">
                            <img class="brand-logo d-none d-md-block" alt="CryptoDash admin logo" src="../static/styles/admin/app-assets/images/logo/logo.png">
                            <img class="brand-logo d-sm-block d-md-none" alt="CryptoDash admin logo sm" src="../static/styles/admin/app-assets/images/logo/logo-sm.png">
                        </a>
                    </li>
                    <li class="nav-item d-md-none">
                        <a class="nav-link open-navbar-container" data-toggle="collapse" data-target="#navbar-mobile">
                            <i class="la la-ellipsis-v"></i>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="navbar-container">
                <div class="collapse navbar-collapse" id="navbar-mobile">
                    <ul class="nav navbar-nav mr-auto float-left">
                        <li class="nav-item d-none d-md-block">
                            <a class="nav-link nav-menu-main menu-toggle hidden-xs" href="#">
                                <i class="ft-menu"></i>
                            </a>
                        </li>
                        <li class="nav-item nav-search">
                            <a class="nav-link nav-link-search" href="#">
                                <i class="ficon ft-search"></i>
                            </a>
                            <div class="search-input">
                                <input class="input" type="text" placeholder="Tìm kiếm">
                            </div>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav float-right">
                        <li class="dropdown dropdown-language nav-item">
                            <a class="dropdown-toggle nav-link" id="dropdown-flag" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="flag-icon flag-icon-vn"></i>
                                <span class="selected-language"></span>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="dropdown-flag">
                                <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-vn"></i> Viet Nam</a>
                                <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-jp"></i> Japanese</a>
                                <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-kr"></i> Korea</a>
                                <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-gb"></i> English</a>
                                <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-fr"></i> French</a>
                                <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-cn"></i> Chinese</a>
                                <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-de"></i> German</a>
                            </div>
                        </li>
                        <li class="dropdown dropdown-notification nav-item">
                            <a class="nav-link nav-link-label" href="#" data-toggle="dropdown">
                                <i class="ficon ft-bell"></i>
                                <span class="badge badge-pill badge-default badge-danger badge-default badge-up badge-glow">5</span>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-media dropdown-menu-right">
                                <li class="dropdown-menu-header">
                                    <h6 class="dropdown-header m-0">
                                        <span class="grey darken-2">Thông báo</span>
                                    </h6>
                                    <span class="notification-tag badge badge-default badge-danger float-right m-0">5 thông báo mới</span>
                                </li>
                                <li class="scrollable-container media-list w-100">
                                    <a href="#">
                                        <div class="media">
                                            <div class="media-left align-self-center">
                                                <i class="ft-plus-square icon-bg-circle bg-cyan"></i>
                                            </div>
                                            <div class="media-body">
                                                <h6 class="media-heading">Bạn có đơn hàng mới!</h6>
                                                <p class="notification-text font-small-3 text-muted">Florentino vừa mua hàng của bạn.</p>
                                                <small><time class="media-meta text-muted" datetime="2015-06-11T18:29:20+08:00">30 phút trước</time></small>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li class="dropdown-menu-footer">
                                    <a class="dropdown-item text-muted text-center" href="#">Đọc tất cả thông báo</a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown dropdown-notification nav-item">
                            <a class="nav-link nav-link-label" href="#">
                                <i class="ficon icon-wallet"></i>
                            </a>
                        </li>
                        <li class="dropdown dropdown-user nav-item">
                            <a class="dropdown-toggle nav-link dropdown-user-link" href="#" data-toggle="dropdown">
                                <span class="avatar avatar-online">
                                    <img src="../static/styles/admin/app-assets/images/portrait/small/avt.webp" alt="avatar">
                                </span>
                                <span class="mr-1">Elon <span class="user-name text-bold-700">Musk</span></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="http://localhost:8080/OneShop/admin/dashboard">
                                    <i class="ft-award"></i> Admin
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" data-toggle="modal" data-target="#exampleModal" id="openModalButton">
								    <i class="ft-user"></i> Profile
								</a>
                                <a class="dropdown-item" href="#">
                                    <i class="icon-wallet"></i> Ví của tôi
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="ft-check-square"></i> Giao dịch
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="<%=request.getContextPath()%>/logout?role=admin">
                                    <i class="ft-power"></i> Logout
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	    <div class="modal-dialog modal-lg" role="document" style="width: 650px; margin-top: 40px;">
	        <div class="modal-content">
	            <div class="modal-header" style="background-color: #0C6478;">
	                <h5 class="modal-title" id="exampleModalLabel" style="color: white;">Chỉnh sửa thông tin Admin</h5>
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body" style="background-color: #15919B;">
	                <h3 style="text-align: center; color: white;">Thông tin Admin</h3>
	                <form id="adminForm">
	                    <div class="form-group">
	                        <label for="userName" style="color: white;">Tên tài khoản:</label>
	                        <input type="text" class="form-control" id="userName">
	                    </div>
	                    <div class="form-group">
	                        <label for="email" style="color: white;">Email:</label>
	                        <input type="email" class="form-control" id="email">
	                    </div>
	                    <div class="form-group">
	                        <label for="fullName" style="color: white;">Họ và tên:</label>
	                        <input type="text" class="form-control" id="fullName">
	                    </div>
	                    <div class="form-group">
	                        <label for="phoneNumber" style="color: white;">Số điện thoại:</label>
	                        <input type="text" class="form-control" id="phoneNumber">
	                    </div>
	                    <div class="form-group">
	                        <label for="address" style="color: white;">Địa chỉ:</label>
	                        <input type="text" class="form-control" id="address">
	                    </div>
	                </form>
	            </div>
	            <div class="modal-footer" style="background-color: #0C6478;">
	                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
	                <button type="button" class="btn btn-primary" id="saveChangesButton">Lưu thay đổi</button>
	            </div>
	        </div>
	    </div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	    function fetchAdminInfo() {
	        $.ajax({
	            url: '/OneShop/admin/infor-admin', 
	            type: 'GET',
	            dataType: 'json',
	            success: function(data) {
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
	    
	    function saveAdminInfo() {
	        var adminData = {
	            userName: $('#userName').val(),
	            email: $('#email').val(),
	            fullName: $('#fullName').val(),
	            phoneNumber: $('#phoneNumber').val(),
	            address: $('#address').val(),
	        };   
	        $.ajax({
	            url: '/OneShop/admin/infor-admin',  
	            type: 'POST',
	            dataType: 'json',
	            data: adminData,  
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
	        $('#openModalButton').on('click', function() {
	            fetchAdminInfo();
	        });
	        
	        $('#saveChangesButton').on('click', function() {
	            saveAdminInfo();
	        });
	    });
	</script>
</body>
</html>
