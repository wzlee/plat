<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String path = request.getContextPath();
	String basePath = "";
	basePath =request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	basePath ="http://wx.smemall.net/";
	String[] images ={"w-flag.png","w-finance.png","w-cy.png","w-user.png","w-tech.png","w-comment.png","w-graph.png","w-eye.png","th-large.png"};
	request.setAttribute("imageList", images);
	
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">
<title>服务机构-深圳中小企业服务平台</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta name="keywords" content="" />
<meta name="description" content="" />
<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
<div id="header">
	<a href="javascript:history.go(-1)" class="up-data"></a>
	<h1>全部机构分类</h1>
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
				<!--  <li>
					<a href=""><div class="panels">
						<img src="resources/images/w-flag.png" alt="">
						<h3>信息服务</h3>
					</div></a>
				</li>
				<li>
					<a href=""><div class="panels">
						<img src="resources/images/w-finance.png" alt="">
						<h3>融资服务</h3>
					</div></a>
				</li>
				<li>
					<a href=""><div class="panels">
						<img src="resources/images/w-cy.png" alt="">
						<h3>创业服务</h3>
					</div></a>
				</li>
				<li>
					<a href=""><div class="panels">
						<img src="resources/images/w-user.png" alt="">
						<h3>人才与培训</h3>
					</div></a>
				</li>
				<li>
					<a href=""><div class="panels">
						<img src="resources/images/w-tech.png" alt="">
						<h3>技术创新</h3>
					</div></a>
				</li>
				<li>
					<a href=""><div class="panels">
						<img src="resources/images/w-comment.png" alt="">
						<h3>管理咨询</h3>
					</div></a>
				</li>
				<li>
					<a href=""><div class="panels">
						<img src="resources/images/w-graph.png" alt="">
						<h3>市场开拓</h3>
					</div></a>
				</li>	
				<li>
					<a href=""><div class="panels">
						<img src="resources/images/w-eye.png" alt="">
						<h3>法律服务</h3>
					</div></a>
				</li>	
				<li>
					<a href=""><div class="panels">
						<img src="resources/images/th-large.png" alt="">
						<h3>其他服务</h3>
					</div></a>
				</li>	-->															
			</ul>
		</div>		
</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>