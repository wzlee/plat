<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户注册</title>
<link href="resources/css/main/style.css" rel="stylesheet"
	type="text/css" />
<link href="jsLib/artDialog-5.0.3/skins/default.css" rel="stylesheet"
	type="text/css" />
<link href="resources/js/main/Selecter/jquery.fs.selecter.css"
	rel="stylesheet" type="text/css" />
<link href="resources/css/main/user.css" rel="stylesheet"
	type="text/css" />

</head>

<body>
<div class="register-wrap">
	<div class="register-header">
		<div class="register-logo"><h1><a href="#"><span class="hide">深圳市中小企业公共服务平台</span></a></h1></div>
	    <div class="register-title">用户注册</div>
	   <!--  <div class="register-link">已经是会员？请<a href="login?direct_url=./" class="f-purple">登录</a></div> -->
	</div>
	<!-- /register-header -->
	<div class="register-success org">
	    <div class="success-content">
	        <p>您的信息已提交，我们将在3个工作日之内完成审核，并将审核结果以电话和邮件的方式通知，敬请留意查收！</p>
	      
	      
	        <div class="next-do"><a href="./" class="">进入平台首页</a></div>
	    </div>
	</div>
<div class="user-footer">
       <p class="s1">主管部门 ：深圳市中小企业服务中心 | 建设单位：深圳市商业联合会 | 技术支持：深圳市依格欣计算机技术有限公司</p>
       <p class="s2">备案号：粤ICP备13083911号 | 增值电信业务经营许可证：B2-1234567号(ICP加挂服务)  &copy;2012 1234567</p>
</div>

</div>
</body>
</html>