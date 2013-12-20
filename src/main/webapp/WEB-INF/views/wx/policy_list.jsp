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
<title>深圳中小企业服务平台-手机版</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta name="keywords" content="" />
<meta name="description" content="" />
<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
<div id="header">
	<a href="javascript:history.go(-1)" class="up-data"></a>
	<h1>政策法规列表</h1>
	<a href="/wx/index" class="home-icon"></a>
</div>
<!-- 头部 -->
<div class="container">
	<a dataid="${param.id }" class="dataParam" style="display: none;"></a>
		<ul class="list-service" id="news-list">
			<c:forEach	 items="${policyList}" var="policyItem">
				<li class="clearfix">
					<div class="description">
						<h3><a href="wxpage/policyDetail?id=${policyItem.id }">${policyItem.title }</a></h3>
						<span class="pubdata">发布日期：${policyItem.time }</span>
					</div>
				</li>
			</c:forEach>
		</ul>
		<a start="6" type="3" href="javascript:void(0);" class="load-more">点击加载更多</a>
</div>
<jsp:include page="footer.jsp" flush="true" />
<script type="text/javascript" src="resources/js/wx/wx.js"></script>
</body>
</html>