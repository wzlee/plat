<%@ page language="java" import="java.util.*,com.eaglec.plat.domain.base.User" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%	
	User user = (User)request.getSession().getAttribute("user");
	String username = null;
	String email = null;
	if(user!=null){
		username =user.getUserName();
		email= user.getEmail();
	}
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>深圳市中小企业公共服务平台@服装</title>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<link href="resources/css/node/style.css" rel="stylesheet" type="text/css" />
	<link href="jsLib/jquery.slider/skitter.styles.css" rel="stylesheet" type="text/css" />
	<link href="resources/css/node/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header">
		<!-- 头部搜索栏,logo -->
	  	<%-- <jsp:include page="../../head.jsp" flush="true" /> --%>
	  	<%@ include file="../../layout/node/top.html"%>
	  	<!-- 导航栏 -->
	  	<jsp:include page="/navigator" flush="true">
	  		<jsp:param  name="cid" value="2"/>
	  	</jsp:include>
	</div>
	<!-- /header -->
	<div class="wrap">
	  <div class="column-l">
	  	<!-- 服务分类组件 -->
	    <div class="all-cate-column">
	    	<ul class="mod-cate" id="frist_list">
	    		<span class="cate-loading"></span>
	    	</ul>
	    </div>
	    <!-- 新闻组件 -->
	    <jsp:include page="/newsComponent" />
	    <!-- 服务排行组件 -->
	    <jsp:include page="/serviceRunklist" />
	    <!-- 服务机构排行组件 -->
	    <jsp:include page="../../layout/node/organize.jsp" />
	  </div>
	  <div class="column-r">
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
	    </div>
	  </div>
	  <div class="clearer"></div>
	  </div>
	<!-- 底部 -->
	<%@ include file="../../layout/node/bottom.html"%>
	<script src="jsLib/jquery/jquery-1.8.2.js" type="text/javascript"></script>
	<script src="jsLib/jquery/jquery.lazyload.min.js" type="text/javascript"></script>
	<script src="jsLib/jquery.slider/jquery.easing.1.3.js" type="text/javascript"></script>
	<script src="jsLib/jquery.slider/jquery.skitter.js" type="text/javascript"></script>
	<script src="resources/js/node/index.js" type="text/javascript"></script>
</body>
</html>
