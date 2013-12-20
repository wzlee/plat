<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>中小企业公共服务平台--运营支撑系统</title>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="resources/css/desktop.css" />
	<link rel="stylesheet" type="text/css" href="jsLib/extjs/resources/css/ext-patch.css"/>
	<link rel="stylesheet" type="text/css" href="jsLib/extjs/shared/example.css"/>
    <link rel="stylesheet" type="text/css" href="jsLib/extjs/resources/css/ext-icon.css" />
    <link rel="stylesheet" href="jsLib/kindeditor-4.1.9/themes/default/default.css" />
</head>
<body>
    <script type="text/javascript" src="jsLib/extjs/shared/include-ext.js"></script>
    <script type="text/javascript" src="jsLib/extjs/shared/options-toolbar.js"></script>
    <script type="text/javascript" src="jsLib/extjs/shared/examples.js"></script>
    <script type="text/javascript" src="jsLib/extjs/locale/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="jsLib/kindeditor-4.1.9/kindeditor.js"></script>
	<script type="text/javascript" src="app/data/PlatMap.js"></script>
	<script type="text/javascript" src="app/service.js"></script>
</body>
</html>
