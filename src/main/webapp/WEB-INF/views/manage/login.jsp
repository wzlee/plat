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
<title>深圳市中小企业公共服务平台-后台登陆</title>
<base href="<%=basePath%>">
	<link href="resources/css/global.css" rel="stylesheet" type="text/css" />
	<link href="resources/css/login.css?v=20130523" rel="stylesheet" type="text/css" />
	<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="login-bg">
		<div class="login-b" style="opacity: 1;">
			<img id="login_bg"
				src="resources/images/login/bg.jpg"
				style="display: block; width: 100%; height: auto; margin-left: 0; opacity: 1; visibility: visible;" />
		</div>
		<div id="login-content">
			<div id="wrap-hd" style="opacity: 1; visibility: visible;">
				<div class="logo"
					style="background: url(http://weibo.pservice.cn/addons/theme/stv1/_static/image/logo.png) no-repeat;"></div>
				<div class="login-guide">
					<p>深圳市中小企业公共服务平台<br/>
						<span style="font-size:40px;color:#606;float:right;">-网络运营支撑系统</span>
					</p>
				</div>
				<div class="s-login">
					<form id="ajax_login_form" method="post" action="public/login">
						<div class="login-bd">
							<ul class="clearfix" model-node="login_input">
								<li class="s-row" style="z-index: 100">
									<div class="input">
										<label class="l-login">帐号</label>
										<div>
											<input id="account_input" type="text" class="s-txt1" autocomplete="off" />
											<div class="txt-list on-changes" style="z-index: 999">
												<p>请选择或继续输入...</p>
												<ul>
													<li email="" rel="show"></li>
													<li email="" rel="show"></li>
												</ul>
											</div>
										</div>
									</div>
								</li>
								<li class="s-row">
									<div class="input">
										<label class="l-login">密码</label> <input id="pwd_input" type="password" class="s-txt1" autocomplete="off" />
									</div>
								</li>
								<li class="actionBtn"><a href="javascript:;" onclick="$('#ajax_login_form').submit();" class="btn-login" data-loading-text="登录中...">登录</a></li>
								<li class="s-row1">
									<a class="auto left" event-node="login_remember" href="javascript:;">
										<span class="check-ok">
											<input type="hidden" name="keeplogining" value="1" />
										</span>
										下次自动登录
									</a>
								</li>
							</ul>
						</div>
					</form>
					<div id="js_login_input" style="display: none" class="error-box"></div>
				</div>
			</div>
		</div>
		<div id="footer"
			style="opacity: 1; visibility: visible; margin: 0; bottom: 0; position: absolute; width: 100%; height: 50px;">
			<div class="footer-wrap"
				style="left: 50%; margin-left: -300px; position: absolute; top: 0; width: 560px; border: none">
				<p>©2013 深圳市中小企业公共服务平台 版权所有</p>
			</div>
		</div>
	</div>
	<script src="jsLib/jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
	<script src="resources/js/public/module.js" type="text/javascript"></script>
	<script src="jsLib/jquery/jquery.cookie.js" type="text/javascript"></script>
	<script src="jsLib/jquery/jquery.md5.js" type="text/javascript"></script>
	<script src="jsLib/jquery/jquery.form.js" type="text/javascript"></script>
	<script src="jsLib/bootstrap/js/bootstrap-button.js" type="text/javascript"></script>
	<script src="resources/js/manage/login.js" type="text/javascript"></script>
</body>
</html>