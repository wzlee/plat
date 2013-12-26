<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="${basePath}">
<title>绑定账号</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta name="keywords" content="" />
<meta name="description" content="" />
<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
<div id="header">
	<a href="" class="up-data"></a>
	<img src="resources/images/wx-logo.png" alt="">
	<a href="" class="home-icon"></a>
</div>
<!-- 头部 -->
<div class="container">
	<div class="panel">
		<div class="title"><h3>用户中心--绑定账号</h3></div>
			<div class="view-content">
			<div class="view-content">
				<p>如果您的微信账号还没有和平台账号进行绑定, 请您先进行账号绑定.</p>
			</div>
			<a class="btn-button" href="concernusers/loginVerify?openid=${weixinUser.openid}">绑定我的账号</a>
	</div>
</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>