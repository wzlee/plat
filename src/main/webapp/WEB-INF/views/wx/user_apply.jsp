<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>服务申请-深圳中小企业服务平台</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta name="keywords" content="" />
<meta name="description" content="" />
<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
<div id="header">
	<a href="" class="up-data"></a>
	<h1>服务申请</h1>
	<a href="" class="home-icon"></a>
</div>
<!-- 头部 -->
<div class="container">
	<div class="panel">
		<p class="gray">请您确认以下信息，并确保信息的真实性，服务申请提交成功后，我们将与您线下联系具体事宜。</p>
			<form action="" method="post" class="apply-service">
				<div class="row"><div class="col one">申请服务：</div>3D打印服务</div>
				<div class="row"><div class="col one">价格：</div>100</div>
				<div class="row"><div class="col one">联系人：</div><input type="text" name="" class=""></div>
				<div class="row"><div class="col one">电话：</div><input type="text" name="" class=""></div>
				<div class="row"><div class="col one">邮箱：</div><input type="text" name="" class=""></div>
				<div class="row"><div class="col one">买家备注：</div><textarea name="" id="" ></textarea></div>
				<div class="row"><div class="col one">&nbsp;</div><div class="col"><input type="submit" class="btn-button" value="确认提交"><input type="reset" class="btn-button" value="取消"></div></div>

			</form>
		
	</div>
</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>