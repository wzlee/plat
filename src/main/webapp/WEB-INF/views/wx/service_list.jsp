<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>   
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="${basePath}">
<title>${serviceCategory.text }</title>
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
		<div class="search-bar">
			<form action="wxpage/search" method="post">
				<input type="hidden" name="type" value="service"/>
				<input type="hidden" name="limit" value="6">
				<input type="text" name="title"/>
				<button type="submit">搜索</button>
			</form>
		</div>
		<a dataid="${param.id }" class="dataParam" style="display: none;"></a>
		<ul class="list-service">
		<c:choose>
		<c:when test="${serviceList.size()>0}">
		<c:forEach items="${serviceList}" var="serviceItem">
	      		<li class="clearfix">
					<a href="wxpage/serviceDetail?id=${serviceItem.id }" class="thumb">
						<c:if test="${serviceItem.picture.contains('http')}">
							<img src="${serviceItem.picture }" alt="">
						</c:if>
						<c:if test="${!serviceItem.picture.contains('http')}">
							<img src="upload/${serviceItem.picture }" alt="">
						</c:if>
					</a>
					<div class="description">
						<h3><a href="wxpage/serviceDetail?id=${serviceItem.id }">${serviceItem.serviceName}</a></h3>
						<div class="info">¥:<span class="red">${serviceItem.costPrice}</span></div>
						<a href="wxpage/serviceDetail?id=${serviceItem.id }" class="website"></a>
					</div>
				</li>	
	        </c:forEach>
		</c:when>
		<c:otherwise>
			<p>抱歉，${serviceCategory.text }类别下暂时没有服务.</p>
		</c:otherwise>
		</c:choose>
			
		</ul>
		<a id="serviceMoreBut" start="6" type="1" href="javascript:void(0);" class="load-more">点击加载更多</a>
</div>
<jsp:include page="footer.jsp" flush="true" />
<script type="text/javascript" src="resources/js/wx/wx.js"></script>
</body>
</html>