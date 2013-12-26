<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String[] images ={"w-flag.png","w-finance.png","w-cy.png","w-user.png","w-tech.png","w-comment.png","w-graph.png","w-eye.png","th-large.png"};
	request.setAttribute("imageList", images);
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="${basePath}">
<title>服务机构分类</title>
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
		<div class="panel">
			<ul class="columns">
				<c:forEach varStatus="iamge" items="${orgCategroyList }" var="orgCategoryItem">
					<li>
						<a href="wxpage/middleOrg?id=${orgCategoryItem.id }"><div class="panels">
						<img src="resources/images/${imageList[iamge.index] }" alt="">
						<h3>${orgCategoryItem.text }</h3>
					</div></a>
				</li>
				</c:forEach>												
			</ul>
		</div>		
</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>