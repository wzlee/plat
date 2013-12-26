<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="${basePath}">
<title>政策法规</title>
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
			<ul class="columns col-policy">
			<c:forEach items="${policyCategoryList }" var="policyCategoryItem">
					<li>
						<a href="wxpage/middlePolicy?id=${policyCategoryItem.id }"><div class="panels">
						<h3>${policyCategoryItem.text }</h3>
					</div></a>
				</li>
			</c:forEach>								
			</ul>
	</div>		
</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>