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
    <link href="jsLib/video-js/video-js.css" rel="stylesheet" type="text/css">
  
    <script src="jsLib/video-js/video.js"></script>
	<!-- <script type="text/javascript">
	    // 页面加载完成  才加载VideoJS的标签
	    videojs.setupAllWhenReady();
	</script> -->	
</head>

<body>
	<div><p>${message }</p></div>
	<div class="vd">
    <!-- Begin VideoJS -->
	  <div class="video-js-box" style="margin:20px auto">
	    <!-- Using the Video for Everybody Embed Code http://camendesign.com/code/video_for_everybody -->
	    <video id="example_video_1" class="video-js" width="640" height="264" 
	    	controls="controls" preload="none" poster="http://video-js.zencoder.com/oceans-clip.png">
	      <source src="upload/oceans-clip.mp4" type='video/mp4' />
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
</body>
</html>