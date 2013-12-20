<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<title>全部服务分类-深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="resources/css/main/common.css" />
	<link type="text/css" rel="stylesheet" href="resources/css/main/category.css" />
</head>
<body>
	<jsp:include page="layout/head.jsp" flush="true" />
	<div class="wrap">
		<div class="crumb-nav">首页 &gt; <span class="current">全部服务分类</span></div>
		<h3 class="category-title">全部服务分类</h3>
		<div class="category-container">
			<ul>
				<c:forEach var="category" items="${categoryList}" varStatus="status">
					<li>
						<dl>
							<dt>
								<a href="javascript:void(0)">${category.text}</a>
							</dt>
							<c:forEach var="cateChild" items="${category.children}" varStatus="status">
								<dd>
									<a href="service/paging?cid=${cateChild.id}" target="_blank">${cateChild.text}</a>
								</dd>
							</c:forEach>
						</dl>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<!-- /wrap -->
	<jsp:include page="layout/footer.jsp" flush="true" />
	
	<script src="jsLib/jquery/jquery.md5.js" type="text/javascript"></script>
	<script type="text/javascript" src="resources/js/main/jquery.placeholder.min.js"></script>
	<script type="text/javascript" src="resources/js/main/jquery.masonry.min.js"></script>
	<script type="text/javascript" src="resources/js/main/category.js"></script>
</body>
</html>