<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="${basePath}">
<title>平台动态</title>
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
		<ul class="list-service" id="news-list">
			<c:forEach items="${wXNewsList }" var="newsItem">
				<li class="clearfix">
				<a href="wxpage/wXNewsDetail?id=${newsItem.id }" class="thumb"><img src="upload/${newsItem.picture }" alt=""></a>
				<div class="description">
					<h3><a href="wxpage/wXNewsDetail?id=${newsItem.id }">${newsItem.title }</a></h3>
					<p>${newsItem.description }</p>
					<span class="pubdata">发布日期：${newsItem.pubdate }</span>
				</div>
				</li>
			</c:forEach>
		
		</ul>
	<a start="6" type="4" href="javascript:void(0);" class="load-more">点击加载更多</a>
</div>
<jsp:include page="footer.jsp" flush="true" />
<script type="text/javascript" src="resources/js/wx/wx.js"></script>
</body>
</html>