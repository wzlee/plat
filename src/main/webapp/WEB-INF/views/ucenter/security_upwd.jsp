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
        <h3 class="top-title">修改密码</h3>
        <div class="auth-form-content">
        <input type="hidden" value="${user.id }" id="userid"/>
		<div class="control-group"><label class="control-label">原密码：</label><div class="controls"><input class="input-large" type="password" id="oldPwd"/></div></div>
		<div class="control-group"><label class="control-label">新密码：</label><div class="controls"><input class="input-large" type="password" id="newPwd"/></div></div>
		<div class="control-group"><label class="control-label">确认新密码：</label><div class="controls"><input class="input-large" type="password" id="verifyNewPwd"/></div></div>
		<div class="control-group"><label class="control-label"></label></label><div class="controls"><button class="submit" id="submit" data-loading-text="处理中...">保存</button>
		<button class="submit" id="cannel" onclick="location.href='/ucenter/security?op=88'">取消</button></div></div>
		<div style="display:none" id="success" style="padding-left:160px;">
		密码已经修改成功，<a href="/ucenter/security">返回安全中心</a>
		</div>
        </div>

    </div>
    <div class="clr"></div> 
</div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script src="jsLib/bootstrap/js/bootstrap-button.js" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/upwd.js"></script>
<script src="jsLib/jquery/jquery.md5.js" type="text/javascript"></script>
</body>
</html>