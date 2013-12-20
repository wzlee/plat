<%@page import="com.eaglec.plat.utils.Common"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.eaglec.plat.domain.auth.Manager" session="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String username = ((Manager)request.getSession().getAttribute("manager")).getUsername();
	Object menu = request.getSession().getAttribute(username);
%>
<html>
<head>
	<title>中小企业公共服务平台--运营支撑系统</title>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="jsLib/extjs/resources/css/ext-patch.css"/>
	<link rel="stylesheet" type="text/css" href="jsLib/extjs/shared/example.css"/>
    <link rel="stylesheet" type="text/css" href="jsLib/extjs/resources/css/ext-icon.css" />
	<link rel="stylesheet" href="jsLib/jquery.fancybox/jquery.fancybox.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="jsLib/kindeditor-4.1.9/themes/default/default.css" />
</head>
<body>
    <div id="loading-mask" style=""></div>
   	<div id="loading">
       <div class="loading-indicator">
           <img src="jsLib/extjs/resources/themes/images/cloud-loading.gif" width="45" height="30" style="margin:4px 4px;float:left;vertical-align:top;"/>
           <a href="./" target="_blank">欢迎登录运营支撑系统</a>
           <br>
           <span id="loading-msg">加载样式资源中...</span>
       </div>
    </div>
    <div id="scan-image" style="display: none;">
    	<a href="resources/images/nopic.gif" class="fancybox"></a>
    </div>
    <script type="text/javascript">document.getElementById('loading-msg').innerHTML = '加载JS资源中...';</script>
    <script type="text/javascript" src="jsLib/extjs/shared/include-ext.js"></script>
    <script type="text/javascript" src="jsLib/extjs/shared/options-toolbar.js"></script>
    <script type="text/javascript" src="jsLib/extjs/shared/examples.js"></script>
    <script type="text/javascript" src="jsLib/extjs/locale/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="jsLib/kindeditor-4.1.9/kindeditor.js"></script>
    <script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="jsLib/jquery/jquery.md5.js" ></script>
    <script type="text/javascript" src="jsLib/jquery.fancybox/jquery.fancybox.pack.js"></script>
    <script type="text/javascript">document.getElementById('loading-msg').innerHTML = '系统初始化中...';</script>
	<script type="text/javascript" src="app/data/PlatMap.js"></script>
    <script>
    	var manager = <%=request.getAttribute("manager") %>;
    	var menu = <%=menu %>;
    	var PlatUrl = '<%=request.getAttribute("CENTER_WEBSITE") %>';
    </script>
	<script type="text/javascript" src="app/app.js"></script>
	<script type="text/javascript">
    	$(function () {
   	        $('.fancybox').fancybox();
    	});
    </script>
</body>
</html>
