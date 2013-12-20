<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>深圳市中小企业公共服务平台--提示页面</title>
<link href="resources/css/main/style.css" rel="stylesheet" type="text/css" />
<link href="resources/css/main/error.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="error-404">
    <div class="error-404-title"></div>
    <div class="error-404-message">非常抱歉，您访问的页面不存在， <a href=".">返回首页</a></div>
</div>
</body>
</html>