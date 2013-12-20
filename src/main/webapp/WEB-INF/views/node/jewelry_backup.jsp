<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>深圳市中小企业公共服务平台@珠宝</title>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<link href="resources/css/node/style.css" rel="stylesheet" type="text/css" />
	<link href="jsLib/jquery.slider/skitter.styles.css" rel="stylesheet" type="text/css" />
	<link href="resources/css/node/index.css" rel="stylesheet" type="text/css" />
	
	
</head>
<body>
	<div class="header">
	  <!-- <div class="top">
	    <div class="logo">
	      <h1><a href="#"><span class="undis">深圳市中小企业公共服务平台@珠宝</span></a></h1>
	    </div>
	    <div class="search-bar">
	      <form action="searchService" method="post">
	        <div class="search-text">
	          <input name="name" type="text" value="申请专利" />
	          <input name="start" type="text" value="0" style="display: none;" />
	          <input name="limit" type="text" value="15" style="display: none;" />
	        </div>
	        <div class="search-btn">
	          <button></button>
	        </div>
	      </form>
	    </div>
	    <div class="member-action"><a href="/user_login">登录</a>|<a href="/register_step_1">注册</a></div>
	  </div> -->
	  <%-- <jsp:include page="top.jsp" /> --%>
	  <%-- <div class="bottom">
	    <div class="top-category">
	      <h2>全部服务分类</h2>
	    </div>
	    <ul class="top-nav">
	    	<c:forEach var="channel" items="${channelList}" varStatus="status">
				<c:choose>
					<c:when test="${channel.cname == '首页'}">
						<li class="on">
							<a href="${channel.chttp}">首页</a>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="${channel.chttp}">${channel.cname}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
	      <!-- <li class="home"><a class="on" href="">首页</a></li>
	      <li><a href="">珠宝商城</a></li>
	      <li><a href="">新闻资讯</a></li>
	      <li><a href="">珠宝知识</a></li>
	      <li><a href="">品牌加盟</a></li>
	      <li><a href="">珠宝鉴定</a></li> -->
	    </ul>
	  </div> --%>
	  <%-- <jsp:include page="/navigator" /> --%>
	</div>
	<!-- /header -->
	<div class="wrap">
	  <div class="column-l">
	    <%-- <div class="main-cate">
	      <ul id="frist_list" class="mod-cate">
	      	<c:forEach var="category" items="${categoryList}" varStatus="status">
				<li id="${category.id}" pid="${category.pid}">
					<div class="name-wrap">
						<h4 class="">
							<a href="">${category.text}</a>
						</h4>
          					<i class="arrow"></i>
					</div>
				</li>
			</c:forEach>
	      </ul>
	    </div> --%>
	    <%-- <jsp:include page="/category" /> --%>
	    <%-- <div class="column-news">
	      <h3>行业新闻</h3>
	      <ul class="list">
	      	<c:forEach items="${newsList}" var="news">
	      		<c:choose>
	      			<c:when test="${fn:length(fn:trim(news.title))>16}">
	      				<li><a href="javascript:void(0)">${fn:substring(news.title, 0, 16)}</a></li>	
	      			</c:when>
	      			<c:otherwise>
	      				<li><a href="javascript:void(0)">${fn:trim(news.title)}</a></li>		
	      			</c:otherwise>
	      		</c:choose>
			</c:forEach> 
	        <!-- <li><a href="">珠宝行业窗口平台，深圳的中小...</a></li>-->
	      </ul>
	    </div> --%>
	    <%-- <jsp:include page="/newsComponent" /> --%>
	    <%-- <div class="column-service">
	      <h3>服务排行</h3>
	      <ul class="list">
	      	<c:forEach items="${serviceList}" var="service" varStatus="status" >
	      		<li class="${status.count}">
	      			<a href="" class="type">${service.serviceName}</a>
	      			<a title="${service.organizeName}" class="name" href="">${service.organizeName}</a></li>
	      	</c:forEach>
	       <!--  <li class="s1"><a href="" class="type">宝石鉴定在在在</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
	        <li class="s2"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
	        <li class="s3"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
	        <li class="s4"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
	        <li class="s5"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
	        <li class="s6"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
	        <li class="s7"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li>
	        <li class="s8"><a href="" class="type">宝石鉴定</a><a title="深圳腾X有限公在在在司..." class="name" href="">深圳腾X有限公在在在司...</a></li> -->
	      </ul>
	    </div> --%>
	    <%-- <jsp:include page="/serviceRunklist" /> --%>
	    <!-- <div class="column-org">
	      <h3>行业机构TOP10</h3>
	      <ul class="list">
	        <li><a href="">Cartier</a></li>
	        <li><a href="">布契拉提</a></li>
	        <li><a href="">TIFFINY</a></li>
	        <li><a href="">周大福</a></li>
	        <li><a href="">梵克雅宝</a></li>
	        <li><a href="">Graff</a></li>
	        <li><a href="">伯爵</a></li>
	        <li><a href="">哈利•温斯顿</a></li>
	        <li><a href="">MIKIMOTO</a></li>
	        <li><a href="">宝格丽</a></li>
	      </ul>
	    </div> -->
	    <%-- <jsp:include page="organize.jsp" /> --%>
	  </div>
	  <!-- /column-l -->
	  <!-- <div class="column-r">
	    <div class="slider">
			<div class="box_skitter">
	    	<ul>
	        	<li><a href=""><img src="resources/images/node/slide.jpg" class="cube" width="716" height="260" alt="" title="" /></a></li>
	            <li><a href=""><img src="resources/images/node/slide.jpg" class="cubeRandom" width="716" height="260" alt="" title="" /></a></li>
	            <li><a href=""><img src="resources/images/node/slide.jpg" class="block" width="716" height="260" alt="" title="" /></a></li>
	            <li><a href=""><img src="resources/images/node/slide.jpg" class="cubeStop" width="716" height="260" alt="" title="" /></a></li>
	            <li><a href=""><img src="resources/images/node/slide.jpg" class="cubeRandom" width="716" height="260" alt="" title="" /></a></li>
	        </ul>
	        </div>
	    </div>
	    <div class="service-panel">
	      <div class="service-nav">
	          <div class="title">服务列表</div>
	          <div class="icons">
	          	<ul class="list">
	            	<li class="current"><a href="#">综合</a></li>
	                <li><a href="#">受申数量</a></li>
	                <li><a href="#">好评度</a></li>
	                <li><a href="#">更新时间</a></li>
	            </ul>
	            <ul class="show-type">
	            	<li class="s1"><a href=""><span>大图</span></a></li>
	                <li class="s2"><a href=""><span>列表</span></a></li>
	            </ul>
	          </div>
	      </div>
	      <div class="service-list">
	        <ul>
	        </ul>
	      </div>
	      <div class="load-more"><a href="javascript:void(0)">加载更多服务</a></div>
	    </div> -->
	  </div>
	  <!-- /column-r --> 
	  <div class="clearer"></div>
	</div>
	<!-- /wrap -->
	<!-- <div class="footer">
	  <p class="s1">主办 ：深圳市中小企业服务中心 | 承办：深圳市商业联合会 | 技术支持：深圳市依格欣计算机技术有限公司</p>
	  <p class="s2">备案号：粤ICP备1234567 | 增值电信业务经营许可证：B2-1234567号(ICP加挂服务)</p>
	  <p class="s2">©2012 1234567</p>
	</div> -->
	<%-- <jsp:include page="bottom.jsp" /> --%>
	<!-- javascript -->
	<script src="jsLib/jquery/jquery-1.8.2.js" type="text/javascript"></script>
	<script src="jsLib/jquery.slider/jquery.easing.1.3.js" type="text/javascript"></script>
	<script src="jsLib/jquery.slider/jquery.skitter.js" type="text/javascript"></script>
	<script src="resources/js/node/index.js" type="text/javascript"></script>
</body>
</html>
