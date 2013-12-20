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
	<title>企业服务导航盘-深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
</head>

<body>
<jsp:include page="layout/head.jsp" flush="true" />
<div style ="margin:auto;width:1024px;position: relative;z-index:0;height: 780px;overflow: hidden;">
	<div id="index_hype_container" style="position:relative;overflow:hidden;width:1024px;height:950px;margin-top:-160px;">
		<script type="text/javascript" charset="utf-8" src="resources/html/index.hyperesources/index_hype_generated_script.js?74238"></script>
	</div>
</div>
<jsp:include page="layout/footer.jsp" flush="true" />
</body>
</html>