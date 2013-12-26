<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<base href="${basePath}">
	<title>用户绑定</title>
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
			<p>请先将您的微信账号和平台账号进行绑定,绑定账号后,您可以使用微信账号在平台上畅享一号通式的服务
			</p>
				<div class="binding clearfix">
					<div class="fl">
						<h3>平台账号</h3>
						<form id="bindUserForm" action="concernusers/userBoundPlat" method="post">
							<input type="hidden" id="openid" name="openid" value="${weixinUser.openid}">
							<input type="text" id="username" />
							<div class="alert" id="w-username"></div><!-- 验证错误后，在alert 后加上类名error -->
							<input type="password" id="password" /><br />
							<div class="alert" id="w-pwd"></div><!-- 验证错误后，在alert 后加上类名error -->
							<input class="scode" type="text" id="authcode" placeholder="验证码" /> 
							<img class="check-code" alt="点击刷新" src="public/authcode" onclick="this.src='public/authcode?'+new Date();" width="55" height="20" /><br />
							<div class="alert" id="w-check"></div><!-- 验证错误后，在alert 后加上类名error -->
							<input id="bindUserBut" type="button" value="绑定账号" class="btn-button" /> 
							<input id="resetBind" type="reset" value="取消" class="btn-button" />
						</form>
					</div>
					<div class="bfc">
						<img src="${weixinUser.headimgurl}" alt="" class="user-face">
						<span class="user-name">${weixinUser.nickname }</span>-${weixinUser.city }
						<!-- ${weixinUser.sex }-->
						
					</div>
				</div>		
		</div>		
	</div>
	<jsp:include page="footer.jsp" flush="true" />
	<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="jsLib/jquery/jquery.md5.js"></script>
	<script type="text/javascript" src="jsLib/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="resources/js/wx/wx.js"></script>
</body>
</html>

