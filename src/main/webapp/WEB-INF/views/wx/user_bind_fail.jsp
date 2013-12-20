<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = "";
	basePath =request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	basePath ="http://wx.smemall.net/";
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">
<title>用户授权失败-深圳中小企业服务平台</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta name="keywords" content="" />
<meta name="description" content="" />
<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
<div id="header">
	<a href="javascript:history.go(-1)" class="up-data"></a>
	<h1>用户授权失败</h1>
	<a href="/wx/index" class="home-icon"></a>
</div>
<!-- 头部 -->
<div class="container">
	<div class="panel user-page">
		<div class="clearfix">
			<img src="http://dummyimage.com/100x100/660066/ffffff&text=logo" alt="" class="fl">
			<div class="bfc">
				<h2>深圳中小企业公共服务平台</h2>
				<p>找的着,用的起,有保障 <br>企业成长孵化器</p>
			</div>
		</div>
		<div class="autho">
			<h3>授权登陆失败</h3>
			<p>由于网络原因,您的本次登陆没能成功,请重新授权登陆,以保障后续的应用。</p>
			<a href="" class="btn-button">使用微信号登录平台</a>
		</div>	
	</div>		
</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>