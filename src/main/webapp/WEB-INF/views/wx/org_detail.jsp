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
<title>服务详情-深圳中小企业服务平台-手机版</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta name="keywords" content="" />
<meta name="description" content="" />
<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
<div id="header">
	<a href="javascript:history.go(-1)" class="up-data"></a>
	<h1>服务机构详情</h1>
	<a href="/wx/index" class="home-icon"></a>
</div>
<!-- 头部 -->
<div class="container">
	<div class="panel">
		<div class="view-info clearfix">
			<div class="pic fl">
				<img src="http://dummyimage.com/115x100/660066/ffffff" alt="">
				
			</div>
			<div class="info">
				<h1 class="view-h1">${enterprise.name }</h1>
				<div class="row">
					<div class="col one">经营模式：</div>
					<div class="col">
						<c:choose>
							<c:when test="${enterprise.businessPattern =='1' }">
                        		生产型
                        	</c:when>
									<c:when test="${enterprise.businessPattern == '2'}">
                        		贸易型
                        	</c:when>
									<c:when test="${enterprise.businessPattern == '3'}">
                        		服务型
                        	</c:when>
									<c:when test="${enterprise.businessPattern == '4'}">
                        		生产型、贸易型
                        	</c:when>
									<c:when test="${enterprise.businessPattern == '5'}">
                        		贸易型、服务型
                        	</c:when>
									<c:when test="${enterprise.businessPattern == '6'}">
                        		生产型、贸易型、服务型
                        	</c:when>
							<c:otherwise>
                        		其它
                        	</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="row">
					<div class="col one">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</div>
					<div class="col">${enterprise.tel }</div>
				</div>
				<div class="row ">
					<div class="col one">联系人：</div>
					<div class="col">${enterprise.linkman }</div>
				</div>
				<div class="row">
					<div class="col one">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</div>
					<div class="col">${enterprise.address }</div>
				</div>
			</div>
		</div>
		<div class="title"><h3>机构简介</h3></div>
		<p>
			${enterprise.profile}
		</p>
		<div class="title"><h3>其它服务</h3></div>
		    <ul class="list-service">
			<c:forEach items="${serviceList }" var="orgServiceItem">
				<li class="clearfix">
					<a href="wxpage/serviceDetail?id=${orgServiceItem.id }" class="thumb">
						<c:if test="${orgServiceItem.picture.contains('http')}">
							<img src="${orgServiceItem.picture }" alt="">
						</c:if>
						<c:if test="${!orgServiceItem.picture.contains('http')}">
							<img src="upload/${orgServiceItem.picture }" alt="">
						</c:if>
					</a>
					<div class="description">
						<h3><a href="">${orgServiceItem.serviceName }</a></h3>
						<div class="info">¥:<span class="red">${orgServiceItem.costPrice }</span></div>
						<a href="" class="website"></a>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>