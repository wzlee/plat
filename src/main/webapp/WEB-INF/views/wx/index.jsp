<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	//request.getSession().getServletContext().setAttribute("basePath", "http://192.168.0.73/");
 	request.getSession().getServletContext().setAttribute("basePath", "http://wx.smemall.net/");
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="${basePath}">
<title>深圳中小企业服务平台</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta name="keywords" content="" />
<meta name="description" content="" />
<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
<div id="header">
	<img src="resources/images/wx-logo.png" alt="">
</div>
<!-- 头部 -->
<div class="container">
	<div class="panel">
		<div class="title">
			<h3>&raquo;快速入口</h3>
		</div>
		<ul class="menu-item clearfix">
			<c:choose>
			   <c:when test="${user != null && concernUsers != null}">
			   		<li>
						<a href="wxpage/userIndex"><img src="resources/images/people-b.png" alt=""></a>
						<a href="wxpage/userIndex" class="a-link">用户中心</a>
					</li>
			   </c:when>
			   <c:when test="${user == null && concernUsers != null}">
			   		<li>
						<a href="concernusers/loginVerify?openid=${weixinUser.openid}"><img src="resources/images/people-c.png" alt="授权登录"></a>
						<a href="concernusers/loginVerify?openid=${weixinUser.openid}" class="a-link">授权登录</a>
					</li>
			   </c:when>
			   <c:otherwise>
				   <li>
						<a href="concernusers/loginVerify?openid=${weixinUser.openid}"><img src="resources/images/people-a.png" alt="绑定账号"></a>
						<a href="concernusers/loginVerify?openid=${weixinUser.openid}" class="a-link">绑定账号</a>
					</li>
			   </c:otherwise>
			</c:choose>
			
			<li>
				<a href="wxpage/maxService"><img src="resources/images/wx-serv.png" alt=""></a>
				<a href="wxpage/maxService" class="a-link">服务资源</a>
			</li>
			<li>
				<a href="wxpage/maxOrg"><img src="resources/images/wx-org.png" alt=""></a>
				<a href="wxpage/maxOrg" class="a-link">服务机构</a>
			</li>
			<li>
				<a href="wxpage/maxPolicy"><img src="resources/images/wx-policy.png" alt=""></a>
				<a href="wxpage/maxPolicy" class="a-link">政策法规</a>
			</li>
			<li>
				<a href="wxpage/platInfo"><img src="resources/images/wx-at.png" alt=""></a>
				<a href="wxpage/platInfo" class="a-link">关于平台</a>
			</li>
			<li>
				<a href="wxpage/wXNewsList"><img src="resources/images/wx-news.png" alt=""></a>
				<a href="wxpage/wXNewsList" class="a-link">平台动态</a>
			</li>
		</ul>
		<div class="title">
			<h3>&raquo;热门推荐<span class="sub-span"></span></h3>
		</div>
		<div class="push-news">
			<c:forEach items="${articleList }" var="articleItem">
				<div class="clearfix">
				<a href="wx/index" class="thumb"><img src="upload/${articleItem.picUrl }" alt=""></a>
				<div class="bfc">
					<h3><a href="javascript:void(0);">${articleItem.title }</a></h3>
					<p>${articleItem.description }</p>
					<span class="pubdata">${articleItem.pubdate }</span>
				</div>
			</div>
			</c:forEach>
		</div>
		<div class="tabs-block">
			<ul id="tabs" class="tabs three">
				<li class="active">政策法规</li>
				<li>服务资源</li>
				<li class="last">服务机构</li>
			</ul>
			<div id="tabs-content">
				<div class="hide" style="display:block">
					<ul class="disc">
					<c:forEach items="${indexPolicyList }" var="policyItem">
						<li><a href="wxpage/policyDetail?id=${policyItem.id }">${policyItem.title }</a></li>
					</c:forEach>
					</ul>
				</div>
				<div class="hide">
					<ul class="disc">
						<c:forEach items="${indexServiceList }" var="serviceItem">
						<li><a href="wxpage/serviceDetail?id=${serviceItem.id }">${serviceItem.serviceName }</a></li>
					</c:forEach>
					</ul>
				</div>
				<div class="hide">
					<ul class="disc">
						<c:forEach items="${indexEnterpriseList }" var="enterItem">
						<li><a href="wxpage/orgDetail?id=${enterItem.id }">${enterItem.name }</a></li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
	<div id="footer">
		<div class="copyright">
Copyright &copy;2013
		</div>
	</div>
	<a href="#header" id="gotop">返回顶部</a>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
  <script>window.jQuery || document.write('<script src="jsLib/jquery/jquery-1.7.1.min.js"><\/script>')</script>	
  <script type="text/javascript" src="resources/js/web-docs.js"></script>
</body>
</html>