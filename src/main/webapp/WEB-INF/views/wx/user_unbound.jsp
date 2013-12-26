<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<base href="${basePath}">
	<title>解除绑定</title>
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
			<div class="view-content">
				<p>解除平台账号后,您将只拥有浏览权限, 而无法在微网站上做任何操作,如果您确定解除绑定平台账号请点击下面解除绑定按钮.</p>
			</div>
			<a openid="${concernUsers.wxUserToken}" cuid="${concernUsers.id}" id="unbundling" class="btn-button" href="javascript:void(0);">解除绑定</a>&nbsp;&nbsp;
			<a href="wxpage/userIndex">返回用户中心</a>
			
		</div>
	</div>
	<jsp:include page="footer.jsp" flush="true" />
	<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="resources/js/wx/wx.js"></script>
</body>
</html>