<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
	<%@ include file = "/common/user/header.jsp" %>
	<sitemesh:write property="body"/>
	<%@ include file = "/common/user/footer.jsp" %>
</body>
</html>