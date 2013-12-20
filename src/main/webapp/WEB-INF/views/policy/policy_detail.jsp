<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.eaglec.com/jsp/user/functions" prefix="my"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>政策法规-深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/main/common.css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/mall.css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/policy.css" />
  <script src="jsLib/video-js/video.js"></script>
	<!-- <script type="text/javascript">
	    // 页面加载完成  才加载VideoJS的标签
	    videojs.setupAllWhenReady();
	</script> -->	
    <script>
	    videojs.options.flash.swf = "jsLib/video-js/video-js.swf";
	</script>
</head>

<body>
<jsp:include page="../layout/head.jsp" flush="true" />
	<div class="wrap clearfix">
		<div class="crumb-nav">
			<a href="">首页 </a>&gt; <a href="policy/index"> 政策法规</a>&gt;<a href="policy/list?policyId=${policyCategory.id }">${policyCategory.text }</a>&gt;
			<span class="current">${policy.title }</span>
		</div>
		<div class="recommend-block fl" >
			<h1 class="policy-h1">${policy.title }</h1>
			<div class="policy-info">
				<span><a href="policy/list?policyId=${policyCategory.id }">${policyCategory.text }</a></span>
				<span>${policy.time}</span>
				<span>来源：${policy.source }</span>
				<c:if test="${policy.policyCategory.type==1 }">
					<input type="button" value="立即申请" class="input-link"></input>
				</c:if>
			</div>
			
			<div class="pic-center">
				<c:choose>
						<c:when test="${policy.fileType =='video' && ''!= policy.uploadFile && null!= policy.uploadFile}">
						<div class="vd">
					    <!-- Begin VideoJS -->
						  <div class="video-js-box" style="margin:20px auto">
						    <!-- Using the Video for Everybody Embed Code http://camendesign.com/code/video_for_everybody -->
						    <video id="example_video_1" class="video-js" width="640" height="264" 
						    	controls="controls" preload="none">
						    	<c:if test="${policy.uploadFile.contains('http')}">
						    		<source src="${policy.uploadFile  }" type='video/mp4' />
						    	</c:if>
						      	<c:if test="${!policy.uploadFile.contains('http')}">
						    		<source src="upload/${policy.uploadFile  }" type='video/mp4' />
						    	</c:if>
						      <!-- <source src="http://video-js.zencoder.com/oceans-clip.webm" type='video/webm; codecs="vp8, vorbis"' />
						      <source src="http://video-js.zencoder.com/oceans-clip.ogv" type='video/ogg; codecs="theora, vorbis"' /> -->
						      <!-- 如果浏览器不兼容HTML5则使用flash播放 -->
						      <!-- <object id="flash_fallback_1" class="vjs-flash-fallback" width="640" height="264" type="application/x-shockwave-flash"
						        data="http://releases.flowplayer.org/swf/flowplayer-3.2.1.swf">
						        <param name="movie" value="http://releases.flowplayer.org/swf/flowplayer-3.2.1.swf" />
						        <param name="allowfullscreen" value="true" />
						        <param name="flashvars" 
						        	value='config={"playlist":["http://video-js.zencoder.com/oceans-clip.png", {"url": "http://localhost:8080/upload/barsandtone.flv","autoPlay":false,"autoBuffering":true}]}' />
						        <img src="http://video-js.zencoder.com/oceans-clip.png" width="640" height="264" alt="Poster Image"
						          title="No video playback capabilities." />
						      </object> -->
						    </video>
						  </div>
						  <!-- End VideoJS -->
						</div>  
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose></div>
			<div class="lit">${policy.description }</div>
		
		<div class="entry">${policy.text }</div>
		</div>
		<div class="column-l fr" >
			<div class="hot-recomment">
			<div class="title"><h3>热门信息</h3></div>
				<ul>
				<c:forEach items="${hotPolicy }" var="hotPolicy">
					<li><a href="policy/detail?id=${hotPolicy.id }">${hotPolicy.title }</a></li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</div>
<jsp:include page="../layout/footer.jsp" flush="true" />
</body>
</html>