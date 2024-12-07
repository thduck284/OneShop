<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="refresh" content="10"> -->
<title>Trang chủ</title>
<script src="https://kit.fontawesome.com/ae360af17e.js" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="../static/styles/user/viewDetail.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../static/styles/user/footer.css">
<link rel="stylesheet" type="text/css" href="../static/styles/user/header.css">
</head>
<body>
	<%@ include file= "/common/user/header.jsp"%>
	<div class="container-fluid">
        <div class="row">
			<div class="col-md-8" style="margin: 90px 0 0 103px;;"> 
                <sitemesh:write property="body"/>
            </div>
        </div>
    </div>
	<%@ include file= "/common/user/footer.jsp"%>
	<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
