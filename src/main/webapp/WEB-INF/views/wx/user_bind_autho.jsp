<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>  
<html>
<head>
	<meta charset="UTF-8">
	<base href="${basePath}">
	<title>用户绑定授权</title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	
	<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
	<div id="header">
		<a href="javascript:history.go(-1)" class="up-data"></a>
		<img src="resources/images/wx-logo.png" alt="">
		<a href="/wx/index" class="home-icon"></a>
	</div>
	<!-- 头部 -->
	<div class="container">
		<div class="panel user-page">
			<div class="clearfix">
				<img src="${weixinUser.headimgurl}" alt="" class="fl">
				<div class="bfc">
					<h2>深圳中小企业公共服务平台</h2>
					<p>找的着,用的起,有保障 <br>企业成长孵化器</p>
				</div>
			</div>
			<div class="autho">
				<h3>该应用公共账号将获得以下授权</h3>
				<p><input type="checkbox" name="" id="" checked="checked" value="checkbox">同意使用基本资料登陆此应用</p>
				<a href="wx/index" class="btn-button">取消</a>
				<a openid="${concernUsers.wxUserToken}" id="accreditLoginBut" href="javascript:void(0);" class="btn-button">授权登录</a>
			</div>		
		</div>	
		<span class="hide-in">该账号已被禁用</span><!-- 默认隐藏，如需显示内容，请添加类名“on” -->
	</div>
	<jsp:include page="footer.jsp" flush="true" />
	<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="resources/js/wx/wx.js"></script>
</body>
</html>