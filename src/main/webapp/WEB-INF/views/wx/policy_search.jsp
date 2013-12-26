<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="${basePath}">
<title>政策法规搜索结果</title>
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
				<input type="hidden" name="type" value="policy" />
				<input type="hidden" name="limit" value="6" />
				<input type="text" id="title" name="title" value="${content}" />
				<button type="submit">搜索</button>
			</form>
			</div>
		<div class="hide-in on"><!-- 需要显示此段内容时，给改类添加类名“on” -->
			<c:if test="${resultList.size()>0 }">
			<h2>搜索结果</h2>
			</c:if>
			<hr>
			<c:if test="${resultList.size()<1 }">
				<p>没有找到任何跟"关键词"相关的内容。您可以尝试使用其他关键词进行搜索，或<a href="/wx/index">返回 首页</a>。</p>
			</c:if>
		</div>
		<ul class="list-service" id="news-list">
			<c:forEach	 items="${resultList}" var="policyItem">
				<li class="clearfix">
					<div class="description">
						<h3><a href="wxpage/policyDetail?id=${policyItem.id }">${policyItem.title }</a></h3>
						<span class="pubdata">发布日期：${policyItem.time }</span>
					</div>
				</li>
			</c:forEach>
		</ul>
		<a start="6" type="policy" href="javascript:void(0);" class="search_load-more">点击加载更多</a>
</div>
<jsp:include page="footer.jsp" flush="true" />
<script type="text/javascript" src="resources/js/wx/wx.js"></script>
</body>
</html>