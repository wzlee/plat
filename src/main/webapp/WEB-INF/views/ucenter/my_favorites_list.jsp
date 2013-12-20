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
<title>深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/style.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/icon.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
</head>
	<body>
		<c:set value='<%=request.getSession().getAttribute("user")%>' var="user"/>
		<jsp:include page="header.jsp" flush="true"/>
		<!-- /header -->
		<jsp:include page="head.jsp" flush="true"/>
		<!-- /user-header -->
		<!-- /container -->
		<div class="main-container">
		   <!-- 左边菜单 -->
		   	<jsp:include page="left.jsp" flush="true" />
		    <div class="main-column">
		    	<div><h3 class="top-title">我的收藏</h3></div>    	
		        <div style="padding: 10px 0px 0px 0px;">			
					<table id="my_favorites_list" style="width:800px;height:684px">
					</table>			
				</div>         
		    </div>
		    	
		</div>
		<!-- /container -->
		<jsp:include page="../layout/footer.jsp" flush="true"/>
		<script type="text/javascript" src="resources/js/ucenter/main.js"></script> 
		<script type="text/javascript" src="resources/js/ucenter/my_favorites_list.js"></script>
	</body>
</html>