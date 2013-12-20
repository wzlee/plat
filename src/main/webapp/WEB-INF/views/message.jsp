<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">
<title>平台</title>
<link href="../resources/css/style.css" rel="stylesheet" type="text/css">
<link href="../resources/css/404.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="header">
		<div class="container">
			<div class="logo pull-left">
				<a href=""><img src="../resources/images/logo.png"></a>
			</div>
			<div class="search-box pull-left">
				<input class="input" type="text">
				<button class="btn" type="button"></button>
			</div>
			<ul class="top-navs pull-right">
			</ul>
		</div>
	</div>
	<!-- /header -->
	<div class="wrap">
		<div class="container">
			<div class="message">
				<div class="sad"></div>
				<h1 class="notice">${message }</h1>
				<p class="notice">服务平台</p>
				<p class="goback">
					<a href="javascript:history.go(-1);"><img
						src="../resources/images/back_btn.png" /></a>
				</p>
			</div>
		</div>
		<!-- /container -->
	</div>
</body>
</html>
