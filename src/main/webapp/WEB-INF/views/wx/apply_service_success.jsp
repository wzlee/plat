<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="${basePath}">
<title>深圳中小企业服务平台</title>
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
		<div class="title"><h3>申请成功</h3></div>
			<div class="view-content">
				<p>恭喜您，您已经成功申请服务:${serviceName },您的订单号是:${orderNumber },请谨记您的订单号,整个的交易环节可在我们的web网站上进行,请登录到我们的网站:www.smemall.net，进行交易.</p>
			</div>
			<a class="btn-button" href="wxPage/maxService">返回服务列表</a>
	</div>
</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>