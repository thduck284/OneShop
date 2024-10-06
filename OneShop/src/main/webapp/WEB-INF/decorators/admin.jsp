<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<link rel="apple-touch-icon" href="../static/styles/admin/app-assets/images/ico/apple-icon-120.png">
<link href="https://fonts.googleapis.com/css?family=Muli:300,300i,400,400i,600,600i,700,700i|Comfortaa:300,400,500,700" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../static/styles/admin/app-assets/css/vendors.css">
<link rel="stylesheet" type="text/css" href="../static/styles/admin/app-assets/vendors/css/charts/chartist.css">
<link rel="stylesheet" type="text/css" href="../static/styles/admin/app-assets/css/app.css">
<link rel="stylesheet" type="text/css" href="../static/styles/admin/app-assets/css/core/menu/menu-types/vertical-compact-menu.css">
<link rel="stylesheet" type="text/css" href="../static/styles/admin/app-assets/vendors/css/cryptocoins/cryptocoins.css">
<link rel="stylesheet" type="text/css" href="../static/styles/admin/app-assets/css/pages/timeline.css">
<link rel="stylesheet" type="text/css" href="../static/styles/admin/assets/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet" type="text/css" href="../static/styles/admin/assets/css/product.css">
</head>
<body>
	<%@ include file = "/common/admin/header.jsp" %>
	<sitemesh:write property="body"/>
	<%@ include file = "/common/admin/left.jsp" %>
	<%@ include file = "/common/admin/footer.jsp" %>
    <script src="../static/styles/admin/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>
    <script src="../static/styles/admin/app-assets/vendors/js/charts/chartist-plugin-tooltip.min.js" type="text/javascript"></script>
    <script src="../static/styles/admin/app-assets/vendors/js/timeline/horizontal-timeline.js" type="text/javascript"></script>
    <script src="../static/styles/admin/app-assets/js/core/app-menu.js" type="text/javascript"></script>
    <script src="../static/styles/admin/app-assets/js/core/app.js" type="text/javascript"></script>
    <script src="../static/styles/admin/app-assets/js/scripts/pages/dashboard-ico.js" type="text/javascript"></script>
</body>
</html>