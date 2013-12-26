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
	<link type="text/css" rel="stylesheet" href="resources/css/main/common.css" />
	<link type="text/css" rel="stylesheet" href="resources/css/main/index.css" />
</head>

<body>
<c:set value='<%=request.getSession().getAttribute("user")%>' var="user"/>
	<jsp:include page="layout/head.jsp" flush="true"/>
	<div class="banner">
		<div class="user-panel">
			<c:choose>
				<c:when test="${empty user}">
					<div class="login-form">
						<h3>
							登录<span class="name">SMEmall</span>
						</h3>
						<div class="show-error" style="display: none;">
							<div class="text">账户名或登录密码不正确，请重 新输入。</div>
						</div>
						<form id="userlogin" action="verify" method="post">
							<input name="redirect_url" type="hidden" value="/"/>
							<div class="controls">
								<p class="s2">
									<input class="xlarge" id="username" name="username" type="text" placeholder="用户名"/>
								</p>
							</div>
							<div class="controls">
								<p class="s2">
									<input class="xlarge" id="password" type="password" placeholder="密码"/>
								</p>
							</div>
							<div class="controls">
								<p class="s2">
									<input class="large" type="text" name="authcode" id="checkcode" placeholder="验证码"/> <img class="check-code"
										alt="点击刷新" src="public/authcode" onclick="this.src='public/authcode?'+new Date();" width="70" height="28" />
								</p>
							</div>
							<button class="login-btn" data-loading-text="登录中...">登录</button>
							
							<div class="login-links">
								<div class="s1"><a class="register-link" href="signup">免费注册</a>
								</div>
								<div class="s2"><a href="user/find_password?direction=${direction }">忘记密码?</a><a href="user/find_user?direction=${direction }">忘记用户名?</a></div>
							</div>
						</form>
					</div>
				</c:when>
				<c:otherwise>
				    <%-- 
				    	禅道bug#215，产品确认登录后不需要显示浮框
				    	<div class="success-status" >
				        <h3>欢迎来到SMEmall服务导航轮盘</h3>
				        <p class="s1">您当前账户是：</p>
				        <p class="s2">${user.email }</p>
				        <p class="s3">
				        	<c:if test="${loginEnterprise.type >= 3}"><a href="ucenter/service_list?op=5">查看我的服务</a></c:if>
				        	<c:if test="${loginEnterprise.type < 3}"><a href="ucenter/auth?op=2">查看我的服务</a></c:if>
				        </p>
				    	</div> 
				    --%>
				</c:otherwise>
			</c:choose>
		</div>
	<jsp:include page="/cms/loadBanner" flush="true" />
	</div>
	<!-- /banner -->
<div class="scroll-news">
  <div class="scroll-panel">
    <div class="scroll-title">新闻:</div>
    <ul class="scroll-list">
    	<c:forEach items="${newsList}" var="news">
    		<li><a href="news/getOneNewsDetails?id=${news.id}" target="_blank">${news.title}</a></li>
    	</c:forEach>
    </ul>
    <div class="wheel-link"><img src="/resources/images/main/round-shape.gif" alt="" /><a href="/test/xinlunpan" target="_blank">服务导航轮盘</a></div>
  </div>
</div>
<!-- /scroll-news -->
	<div class="wrap">
		<div class="column-l">
		    <div class="all-cate-column">
		    <div class="cate-info">全部服务分类</div>
		    	
		    	<ul class="mod-cate" id="frist_list">
		    		<span class="cate-loading"></span>
		    	</ul>
		    </div>
		    <div class="news-column">
		      <div class="head">
		        <div class="title">行业新闻</div>
		        <div class="more"><a href="news/getNewsList?start=0" target="_blank">更多></a></div>
		      </div>
		      <ul class="list">
		      	<c:forEach items="${newsList}" var="news">
		      		<li><a href="news/getOneNewsDetails?id=${news.id}" target="_blank">•${news.title}</a></li>
		      	</c:forEach>
		      </ul>
		    </div>
		    <!-- /news-column -->
		</div>
		<div class="column-r">
    <div class="window-items">
      <div class="cate">窗口平台</div>
      <div class="items-row window-items">
        <jsp:include page="layout/win_wlan.jsp"></jsp:include>
      </div>
    </div>
    <!-- /window-items -->
			<div class="service-column">
				<h3 class="cate-name">
					<a href="">热门服务</a>
				</h3>
				<div class="service-list">
					<ul>
						<c:forEach var="hot" items="${hotService}" varStatus="status">
							<li>
								<input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${hot.enterprise.id }"/>
								<a href="service/detail?id=${hot.id }" target="_blank">
									<%--<img class="lazy" src="resources/images/service-loading.gif" data-original="upload/${hot.picture}" width="228" height="190" alt="${hot.serviceName }" title="${hot.serviceName }" onerror="this.src='resources/images/service/default_service_pic.gif'" /> --%>
									<c:if test="${hot.picture.contains('http')}">
										<img src="${hot.picture}" width="228" height="190" alt="${hot.serviceName }" title="${hot.serviceName }" onerror="this.src='resources/images/service/default_service_pic.gif'" />
									</c:if>
									<c:if test="${!hot.picture.contains('http')}">
										<img src="upload/${hot.picture}" width="228" height="190" alt="${hot.serviceName }" title="${hot.serviceName }" onerror="this.src='resources/images/service/default_service_pic.gif'" />
									</c:if>									
								</a>
								<div class="content">
									<h3 class="name">
										<a href="service/detail?id=${hot.id }" target="_blank">${hot.serviceName}</a>
									</h3>
									<div class="intro">${hot.serviceProcedure}</div>
								</div>
								<h3 class="apply">
									<a class="apply-btn" target="_blank" href="service/detail?id=${hot.id }">查看详情</a>
								</h3>
							</li>
						</c:forEach>
					</ul>
				</div>
				<h3 class="cate-name">
					<a href="">最新服务</a>
				</h3>
				<div class="service-list">
					<ul>
						<c:forEach var="news" items="${newService}" varStatus="status">
							<li>
								 <input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${news.enterprise.id }"/> 
								<a href="service/detail?id=${news.id }" target="_blank">
									<c:if test="${news.picture.contains('http')}">
										<img src="${news.picture}" width="228" height="190" alt="${news.serviceName }" title="${news.serviceName }" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
									</c:if>
									<c:if test="${!news.picture.contains('http')}">
										<img src="upload/${news.picture}" width="228" height="190" alt="${news.serviceName }" title="${news.serviceName }" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
									</c:if>									
								</a>
								<div class="content">
									<h3 class="name">
										<a href="service/detail?id=${news.id }" target="_blank">${news.serviceName}</a>
									</h3>
									<div class="intro">${news.serviceProcedure}</div>
								</div>
								<h3 class="apply">
										<a class="apply-btn" target="_blank" href="service/detail?id=${news.id }">查看详情
      									</a>
								</h3>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<!-- /column-r -->
		<div class="clearer"></div>
	</div>
	<!-- /wrap -->
	<jsp:include page="layout/footer.jsp" flush="true"/>
	
	<script src="jsLib/jquery/jquery.md5.js" type="text/javascript"></script>
	<script src="jsLib/jquery/jquery.lazyload.min.js" type="text/javascript"></script>
	<script src="jsLib/bootstrap/js/bootstrap-button.js" type="text/javascript"></script>
	<script type="text/javascript" src="resources/js/main/jquery.placeholder.min.js"></script>
	<script type="text/javascript" src="resources/js/main/jcarousellite.js"></script>
	<script type="text/javascript" src="resources/js/index.js?v=20131203"></script>
</body>
</html>