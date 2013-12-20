<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<title>中小企业公共服务平台-${name }</title>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<style>
		body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;overflow: hidden;}
	</style>
</head>
<body>
	<iframe id="${code}"  name="name" src='${src }' width='100%' height='100%' frameborder='0'></iframe>
</body>
</html>