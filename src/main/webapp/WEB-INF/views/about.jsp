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
<div class="crumb-nav">首页 &gt;  <span class="current">关于平台</span></div>
<div class="banner"><img src="resources/images/ucenter/banner.jpg" width="960" height="288" /></div>
<div class="main-container">
    <ul class="sidebar">
      <li class="current"><a href="/about">关于平台</a></li>
      <li><a href="/target">平台目标</a></li>
      <li><a href="">平台文化</a></li>
      <li><a href="">平台动态</a></li>
      <li><a href="">人才招聘</a></li>
      <li><a href="">广告服务</a></li>
      <li><a href="/contact">联系我们</a></li>
    </ul>
    <div class="main-column">
      <h3 class="summary">关于平台</h3>
      <div class="article">
        <p>深圳市中小企业公共服务平台（英文名称为Shenzhen Small and Medium Enterprises Public Service mall，简写为“SMEmall”）是根据工信部中小企业公共服务平台网络的建设要求，按照深圳市政府关于促进中小企业发展的政策精神，开发的一个颇具“创新精神”的建设理念，为深圳市中小企业提供优质公共服务的平台。</p>
        <p>SMEmall主旨是企业成长的孵化器，服务机构的助推器，公共管理的加速器。</p>
        <p>平台的建立在解决中小企业共性需求，畅通信息渠道，改善经营管理，提高发展质量，增强中小企业核心竞争力，帮助企业做大做强，实现创新发展等方面发挥着重要支撑作用，对改善企业发展环境，促进社会资源优化配置和专业化分工协作，推动共性关键技术的转移与应用，逐步形成社会化、市场化、专业化的公共服务体系和长效机制具有重要现实意义。</p>
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