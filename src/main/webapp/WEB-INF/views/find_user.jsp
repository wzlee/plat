<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回用户名-深圳市中小企业公共服务平台</title>
<base href="<%=basePath%>">
<link href="resources/css/main/style.css" rel="stylesheet" type="text/css" />
<link href="resources/css/main/user.css" rel="stylesheet" type="text/css" />
<script src="jsLib/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="jsLib/jquery/jquery.form.js" type="text/javascript"></script>
<script src="jsLib/bootstrap/js/bootstrap-button.js" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/main/find_user.js"></script>
<script type="text/javascript">
	var direction='${direction }';
</script>
</head>

<body>
<div class="register-wrap">
<div class="register-header">
	<div class="register-logo"><h1><a href="#"><span class="hide">深圳市中小企业公共服务平台</span></a></h1></div>
    <div class="register-title">找回用户名</div>
    <div class="register-link">已经是注册用户？请<a href="login?redirect_url=./" class="f-purple">登录</a></div>
</div>
<!-- /register-header -->
<div class="register-content">
    <form id="register-form"  method="post">
	<div class="control-group">
    	<label class="control-label" for="email">电子邮箱：</label>
        <div class="controls"><input name="email" id="email" type="text" value="" class="xxlarge" /></div>
    </div>
    <div class="control-group">
        <label class="control-label" for="checkcode"></label>
        <div class="controls"><button type="button" class="register-btn" data-loading-text="提交中...">提交</button></div>
    </div>
    </form>
</div>
<!-- /register-content -->
<div class="user-footer">
       <p class="s1">主管部门 ：深圳市中小企业服务中心 | 建设单位：深圳市商业联合会 | 技术支持：深圳市依格欣计算机技术有限公司</p>
       <p class="s2">备案号：粤ICP备13083911号 | 增值电信业务经营许可证：B2-1234567号(ICP加挂服务)  &copy;2012 1234567</p>
</div>
</div>
</body>
</html>