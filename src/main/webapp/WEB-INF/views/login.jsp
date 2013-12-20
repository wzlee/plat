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
<title>用户登陆-深圳市中小企业公共服务平台</title>
<link href="resources/css/main/style.css" rel="stylesheet" type="text/css" />
<link href="resources/css/main/user.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="login-wrap">
	    <div class="register-header">
	        <div class="register-logo"><h1><a href="#"><span class="hide">深圳市中小企业公共服务平台</span></a></h1></div>
	        <div class="register-title">用户登陆</div>
	        <!-- <div class="register-link">如需帮助，请<a href="javascript:void();" class="f-purple" onclick="alert('暂未开放');">联系客服</a></div>  -->	        
	    </div>
	    <!-- /register-header -->
	    <div class="login-content">
	        <div class="login-panel">
	            <div class="login-form">
	                <h6 id="errorMsg" class="error" style="display:none;"></h6>
	                <form id="loginform" action="verify" method="post">
		                <input name="redirect_url" type="hidden" value="${redirect_url}"/>
		                <div class="control-row">
		                    <label class="title">用户名：<a href="user/find_user?direction=${direction }" class="forgot-password">找回用户名？</a></label>
		                    <div class="controls">
		                    	<input id="username" class="input-large" type="text" placeholder="输入用户名..."/>
	                    	</div>
		                </div>
		                <div class="control-row">
		                    <label class="title">密码：<a href="user/find_password?direction=${direction }" class="forgot-password">忘记密码？</a></label>
		                    <div class="controls">
		                    	<input id="password" class="input-large" type="password" placeholder="输入密码..."/>
	                    	</div>
		                </div>
		                <div class="control-row">
		                    <label class="title">验证码：</label>
		                    <div class="controls">
		                    	<input class="input-small" type="text" id="authcode" placeholder="输入验证码..."/>
		                    	<img class="checkcode" alt="点击刷新" src="public/authcode" onclick="this.src='public/authcode?'+new Date();" width="100" height="26" />
	                    	</div>
		                </div>
		                <div class="control-login">
		                	<button class="login-button" data-loading-text="登录中...">登录</button>
		                </div>
	                </form>
	                <div class="control-link">
	                    <p>还不是注册用户？<a href="signup" class="f-purple"><strong>免费注册</strong></a></p>
	                </div>
	            </div>
	        </div>
	    </div>
    	<!-- /login-content -->
	<div class="user-footer">
        <p class="s1">主管部门 ：深圳市中小企业服务中心 | 建设单位：深圳市商业联合会 | 技术支持：深圳市依格欣计算机技术有限公司</p>
        <p class="s2">备案号：粤ICP备13083911号 | 增值电信业务经营许可证：B2-1234567号(ICP加挂服务)  &copy;2012 1234567</p>
    </div>
    </div>
	<script src="jsLib/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="jsLib/jquery/jquery.md5.js" type="text/javascript"></script>
	<script src="jsLib/jquery/jquery.form.js" type="text/javascript"></script>
	<script src="jsLib/bootstrap/js/bootstrap-button.js" type="text/javascript"></script>
	<script type="text/javascript" src="resources/js/main/jquery.placeholder.min.js"></script>
	<script type="text/javascript" src="resources/js/login.js"></script>
</body>
</html>