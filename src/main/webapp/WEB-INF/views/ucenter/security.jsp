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
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/order.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
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
        <h3 class="top-title">账号安全</h3>
		<ul class="account-safe clearfix">
			<li class="s1"> 您可以随时修改您的登录密码以提高账号的安全性 。<br />
			<div class="link-img"><a href="ucenter/security_upwd?op=88" ><img src="resources/images/ucenter/user-lockd.png" />修改密码</a></div>
			
			</li>
			
			<li class="s2">验证邮箱后,可用邮箱找回密码,接收平台服务信息等 <br />
			<div class="link-img"><a href="ucenter/security_uemail?op=88"  class="link-img"><img src="resources/images/ucenter/user-mail.png" />邮箱验证</a></div>
			</li>
		</ul>
    </div>
    <div class="clr"></div> 
</div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
</body>
</html>