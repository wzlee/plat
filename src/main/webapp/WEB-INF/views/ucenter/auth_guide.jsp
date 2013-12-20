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
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
</head>

<body>
<jsp:include page="header.jsp" flush="true"/>
<!-- /header -->
<jsp:include page="head.jsp" flush="true"/>
<!-- /user-header -->
<!-- /container -->
<div class="main-container">
    <!-- 左边菜单 -->
   	<jsp:include page="left.jsp" flush="true" />
    <div class="main-column">
        <h3 class="top-title">认证管理</h3>
        <div class="auth-guide-content">
            <p>您还未申请企业实名认证，实名认证成功后方可在平台申请服务。</p>
            <p>您也可以直接申请认证为服务机构，成功通过认证后，可以在平台发布服务。</p>
            <div class="auth-links">
                <a href="ucenter/auth_form?op=2" class="real-name">申请实名认证</a>
                <a href="ucenter/auth_form2?op=2" class="real-service">申请服务机构认证</a>
            </div>
        </div>
    </div>
    <div class="clr"></div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
</body>
</html>