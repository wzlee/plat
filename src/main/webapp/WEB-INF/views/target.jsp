<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<title>深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="resources/css/ucenter/page.css" />
</head>

<body>
	<jsp:include page="layout/head.jsp" flush="true"/>
<div class="wrap">
<div class="crumb-nav">首页 &gt;  <span class="current">平台介绍</span></div>
<div class="banner"><img src="resources/images/ucenter/banner.jpg" width="960" height="288" /></div>
<div class="main-container">
    <ul class="sidebar">
      <li><a href="/about">关于平台</a></li>
      <li class="current"><a href="">平台目标</a></li>
      <li><a href="">平台文化</a></li>
      <li><a href="">平台动态</a></li>
      <li><a href="">人才招聘</a></li>
      <li><a href="">广告服务</a></li>
      <li><a href="/contact">联系我们</a></li>
    </ul>
    <div class="main-column">
      <h3 class="summary">平台目标</h3>
      <div class="article">
        <p>深圳市中小企业公共服务平台坚持以提供企业最优质、最安全、最便捷的服务为宗旨，以实现提供中小企业“找得着、用得起、有保障”的公共服务为目标，为我市中小企业提供一体化、专业化、协同化的公共服务，促进我市中小企业健康快速发展。</p>
      	<div class="target">
      	<h3>具体目标体现如下：</h3>
      	<p><strong>找得着：</strong></p>
      	<ol>
      		<li>服务导航功能齐备，支持网站、呼叫中心、移动APP、应用软件常规服务导航功能</li>
      		<li>彰显服务价值，在常规服务导航的基础上，依托于数据挖掘和分析，实现服务个性化推送、定制，从找服务到服务随时就在身边，提升平台服务水准</li>
      	</ol>
      	<p><strong>用得起：</strong></p>
      	<ol>
      		<li>以产业集群为单位，集合社会各方资源为企业提供服务，企业所付出的单位成本远远低于单个企业获取资源所需成本</li>
      		<li>细分服务：基础服务、增值服务、个性服务，建议：基础服务免费，增值、个性服务收费，普通会员免费，高级 会员收费</li>
      	</ol>
      	<p><strong>有保障：</strong></p>
      	<ol>
      		<li>严格服务接入机制</li>
      		<li>制定完善的服务监控、考核机制</li>
      		<li>持续优化，不断探索，健全服务协同机制</li>
      	</ol>
      	</div>
      </div>
    </div>
    <div class="clearer"></div>
</div>
<!-- /main-container -->
</div>
<!-- /wrap -->
	<jsp:include page="layout/footer.jsp" flush="true"/>
</body>
</html>