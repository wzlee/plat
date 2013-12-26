<%@ page language="java" import="java.util.*,com.eaglec.plat.domain.base.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Object systime = System.currentTimeMillis();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link href="jsLib/artDialog-5.0.3/skins/default.css" rel="stylesheet" type="text/css" />
<link href="jsLib/artDialog-5.0.3/skins/login.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/style.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/order.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />

<style type="text/css">
	
</style>
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
        <h3 class="top-title">邮箱验证</h3>
        <div class="auth-form-content">
			<div class="control-group"><label class="control-label secur"></label>
				<div class="controls">	邮箱：${user.email }<a href="javascript:void(0);" id="uemail" class="f-purple tdu">修改邮箱</a></div>
			</div>
			<input type="hidden" value="${user.id }" id="userid"/>
	<!-- 		<div style="display:none" id="success"> -->
	<!-- 			<div class="control-group"> -->
	<!-- 				<label class="control-label secur">新邮箱：</label><div class="controls"><input class="input-large" type="text" id="newemail"/></div> -->
	<!-- 			</div> -->
	<!-- 			<div class="control-group"><label class="control-label secur"></label><div class="controls"><button class="submit" id="submit" data-loading-text="修改中...">确定</button></div></div> -->
	<!-- 		</div> -->
			<div style="display:none" id="success">
 				<div style="height:30px;margin:10px 0;width: 350px">
 					<div class="controls">
	 					<label for="" style="display:inline-block;width:80px; vertical-align: top;">新邮箱：</label>
	 					<input type="text" id="newemail" style="width: 240px;"/>
 					</div>
 				</div>
			</div>
			<c:if test="${user.email != null and user.email != ''}">
				<div class="control-group"><label class="control-label secur"></label><div class="controls"><button class="submit" id="sendemail" data-loading-text="发送中...">发送邮箱验证码</button><div id="sendmessage"></div></div></div>
			</c:if>
			<div id="follow"></div>
		</div>
		
		<div class="mail-infomation">
			<h3>无法收到验证邮件?</h3>
			<ul class="cricle">
				<li>验证邮件可能在垃圾邮箱或广告邮箱中，请仔细查收！</li>
				<li>网络原因可能出现延时，如果在10分钟内没有收到验证邮件，请再次发送验证请求，每个邮箱地址每天可发送5封验证邮件。</li>
			</ul>
		</div>
        

    </div>
    <div class="clr"></div> 
</div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script >
var systime = <%=systime %>;
var sentime = '${fn:substringAfter(user.checkcode, "|")}';
</script>
<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
<script src="jsLib/bootstrap/js/bootstrap-button.js" type="text/javascript"></script>
<script type="text/javascript" src="jsLib/artDialog-5.0.3/artDialog.min.js"></script>
<script type="text/javascript" src="jsLib/artDialog-5.0.3/artDialog.plugins.min.js"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/uemail.js"></script>
</body>
</html>