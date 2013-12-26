<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="${basePath}">
<title>我的收藏</title>
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
			<div class="user-info clearfix">
				<img src="${user.enterprise.photo }" alt="" class="fl">
				<div class="bfc">
					<div class="name"><strong>${user.userName }</strong>&nbsp;|&nbsp;${user.enterprise.name } <br></div>
					<img src="images/approve.jpg" alt="">
				</div>
			</div>
			<div class="title"><h3>我的收藏</h3></div>
			<div class="view-info clearfix">
			<c:forEach items="${myFavortiesList }" var="item">
				<div class="pic fl">
					<img src="upload/${item.picture }" alt="">					
				</div>
				<div class="info">
					<div class="row">
						<div class="col one">服务名称：</div>
						<div class="col">${item.serviceName }</div>
					</div>
					<div class="row">
						<div class="col one">服务类别：</div>
						<div class="col">${item.categoryName }</div>
					</div>
					<div class="row ">
						<div class="col one">收藏时间：</div>
						<div class="col">${item.time }</div>
					</div>
					<a href="myFavorites/deleteFromWX?id=${item.id}" class="btn-button">取消收藏</a>
				</div>	
			</c:forEach>
			</div>
			<a start="6" type="6" href="javascript:void(0);" class="load-more">点击加载更多</a>	
	</div>		
</div>
<jsp:include page="footer.jsp" flush="true" />
<script type="text/javascript" src="resources/js/wx/wx.js"></script>
</body>
</html>