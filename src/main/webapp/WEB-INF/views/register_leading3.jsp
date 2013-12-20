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
	<title>深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="resources/css/main/style.css" />
	<link type="text/css" rel="stylesheet" href="resources/css/main/user.css" />
</head>

<body>
<div class="register-wrap">
<div class="register-header">
	<div class="register-logo"><h1><a href="#"><span class="hide">深圳市中小企业公共服务平台</span></a></h1></div>
    <div class="register-title">用户注册</div>
    <!-- <div class="register-link">已经是会员？请<a href="login?direct_url=./" class="f-purple">登录</a></div> -->
</div>
<!-- /register-header -->
<div class="reg-success">
    <div class="reg-success-content">
        <h2><span class="username">HTM</span>恭喜您已经成功注册SMEmall！</h2>
        <p class="tip">此账号作为企业admin账号，可以设定企业员工子账号</p>
    </div>
 	<p class="text">为了保障您的账户安全，并能正常使用SMEmall平台服务，获得更精准的商业机会，我们建议您立即完善以下信息：</p>    
</div>
<!-- /register-success -->
<div class="attest-panel">      
	<div class="photo">
		<img src="resources/images/main/badge01.png" alt="" />
	</div>    
	<div class="attest-right">
		<strong class="attest-name">服务机构认证</strong>
		<p class="attest-info">平台安全认证，尊享服务推广优势、采购资源匹配优势，更有独特识别标识。认证后企业可在平台发布服务信息，让更多采购商找到您。</p>
		<a href="ucenter/auth_form2" class="attest-now">立即申请</a>
	 	 <div class="next-work">您还可以：<a href="ucenter/security_uemail">邮箱验证</a>  |  <a href="ucenter/user_manage?uid=${user.id }">完善资料</a>  |  <a href="./">返回平台首页</a></div>
 	 </div>
</div>
<!-- /验证 -->
<div class="user-footer">
       <p class="s1">主管部门 ：深圳市中小企业服务中心 | 建设单位：深圳市商业联合会 | 技术支持：深圳市依格欣计算机技术有限公司</p>
       <p class="s2">备案号：粤ICP备13083911号 | 增值电信业务经营许可证：B2-1234567号(ICP加挂服务)  &copy;2012 1234567</p>
</div>
</div>
</body>
</html>