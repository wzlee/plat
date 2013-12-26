<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="${basePath}">
<title>用户中心</title>
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
			<img src="upload/${user.enterprise.photo }" alt="" class="fl">
			<div class="bfc">
				<div class="name"><strong>${user.userName }</strong>&nbsp;|&nbsp;${user.enterprise.name } <br></div>
				<img src="resources/images/approve.jpg" alt="">
			</div>
		</div>	
			<ul class="menu-item clearfix">
			<c:if test="${user.enterprise.type>2 }">
				<li>
					<a href="wxpage/userInfo"><img src="resources/images/people-b.png" alt=""></a>
					<a href="wxpage/userInfo" class="a-link">我的资料</a>
				</li>
				<li>
					<a href="wxpage/userCollect"><img src="resources/images/wx-collect.png" alt=""></a>
					<a href="wxpage/userCollect" class="a-link">我的收藏</a>
				</li>
				<li>
					<a href="wxpage/userService"><img src="resources/images/wx-serv2.png" alt=""></a>
					<a href="wxpage/userService" class="a-link">我的服务</a>
				</li>
				<li>
					<a href="wxpage/userBid"><img src="resources/images/icon1.png" alt=""></a>
					<a href="wxpage/userBid" class="a-link">我的招标</a>
				</li>
				<li>
					<a href="concernusers/pageSkip?page=wx/user_unbound&openid=${concernUsers.wxUserToken}"><img src="resources/images/wx-unbound.png" alt=""></a>
					<a href="concernusers/pageSkip?page=wx/user_unbound&openid=${concernUsers.wxUserToken}" class="a-link">解除绑定</a>
				</li>
			</c:if>
			<c:if test="${user.enterprise.type==1 }">	
				<li>
					<a href="wxpage/userInfo"><img src="resources/images/people-b.png" alt=""></a>
					<a href="wxpage/userInfo" class="a-link">我的资料</a>
				</li>
				<li>
					<a href="wxpage/userCollect"><img src="resources/images/wx-collect.png" alt=""></a>
					<a href="wxpage/userCollect" class="a-link">我的收藏</a>
				</li>
			</c:if>	
			<c:if test="${user.enterprise.type==2 }">
				<li>
					<a href="wxpage/userInfo"><img src="resources/images/people-b.png" alt=""></a>
					<a href="wxpage/userInfo" class="a-link">我的资料</a>
				</li>
				<li>
					<a href="wxpage/userCollect"><img src="resources/images/wx-collect.png" alt=""></a>
					<a href="wxpage/userCollect" class="a-link">我的收藏</a>
				</li>
				<li>
					<a href="wxpage/userBid"><img src="images/icon1.png" alt=""></a>
					<a href="wxpage/userBid" class="a-link">我的招标</a>
				</li>
			</c:if>
			</ul>
			<div class="title"><h3>我的收藏</h3></div>
			<div class="view-info clearfix">
				<c:forEach items="${myFavortiesList }" var="favortiesItem">
					<div class="pic fl">
						<img src="upload/${favortiesItem.picture }" alt="">					
					</div>
					<div class="info">
						<div class="row">
							<div class="col one">服务名称：</div>
							<div class="col">${favortiesItem.serviceName }</div>
						</div>
						<div class="row">
							<div class="col one">服务类别：</div>
							<div class="col">${favortiesItem.categoryName }</div>
						</div>
						<div class="row ">
							<div class="col one">收藏时间：</div>
							<div class="col">${favortiesItem.time }</div>
						</div>
						<a href="myFavorites/deleteFromWX?id=${favortiesItem.id}" class="btn-button">取消收藏</a>
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