<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>注册成功-深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/main/style.css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/user.css" />

<script src="jsLib/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="jsLib/jquery/jquery.form.js" type="text/javascript"></script>
<script src="jsLib/bootstrap/js/bootstrap-button.js" type="text/javascript"></script>
</head>

<body>
<div class="register-wrap">
<div class="register-header">
	<div class="register-logo"><h1><a href="#"><span class="hide">深圳市中小企业公共服务平台</span></a></h1></div>
    <div class="register-title">用户注册</div>
   <!--  <div class="register-link">已经是会员？请<a href="login?direct_url=./" class="f-purple">登录</a></div> -->
</div>
<!-- /register-header -->
<div class="register-success">
    <div class="success-content">
        <h2><span class="username">HTM</span>恭喜您已经成功注册SMEmall！</h2>
        <p class="tip">此账号作为企业管理员账号，可以设定企业员工子账号</p>
        <div class="suggest-panel">
            <p class="text">为了保障您的账户安全，并能正常使用SMEmall平台服务，获得更精准的商业机会，我们建议您立即完善以下信息：</p>
            <p class="links"><a class="cer-v" href="ucenter/auth">实名认证</a><a class="email-v" href="ucenter/auth">邮箱验证</a></p>
        </div>
        <div class="next-do">您还可以:<a href="ucenter/auth">完善资料</a> | <a href="./">返回平台首页</a></div>
    </div>
</div>
<!-- /register-success -->
<div class="user-footer">
       <p class="s1">主管部门 ：深圳市中小企业服务中心 | 建设单位：深圳市商业联合会 | 技术支持：深圳市依格欣计算机技术有限公司</p>
       <p class="s2">备案号：粤ICP备13083911号 | 增值电信业务经营许可证：B2-1234567号(ICP加挂服务)  &copy;2012 1234567</p>
</div>
</div>
</body>
</html>