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
<base href="<%=basePath%>"/>
<title>深圳市中小企业公共服务平台</title>
<meta charset="UTF-8"/>
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/style.css" />
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
        <h3 class="top-title">认证管理</h3>
        <div class="auth-form-content">
            <form class="real_name_auth" action="ucenter/applyPersonal" method="post">
                <div class="control-group">
                    <label class="control-label" for="username">用户名：</label>
                    <div class="controls">
                    	<%-- 会员id --%>
                        <input name="user.id" value="${user.id }" type="hidden" />
                        <%-- 认证描述 1=实名认证 --%>
                        <input name="applyType" value="1" type="hidden" />
                        <%-- 处理状态 2=处理中 --%>
                        <input name="approveStatus" value="2" type="hidden" />
                        <input class="input-large" name="user.userName" value="${user.userName }" type="text" readonly="readonly" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="username">真实姓名：</label>
                    <div class="controls">
                        <input class="input-large" id="name" name="name" type="text" value="${user.realName}"/>
                    </div>
                </div> 
                <div class="control-group">
                    <label class="control-label">个人近照图片：</label>
                    <div class="controls">
                        <input class="file" id="personalPhoto" type="file" name="file"/>
                        <input type="hidden" name="personalPhoto"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">身份证附件图片：</label>
                    <div class="controls">
                        <input class="file" id="idCardPhoto" type="file" name="file"/>
                        <input type="hidden" name="idCardPhoto"/>                        
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"></label>
                    <div class="controls" >                        
                        <div style="margin-top: 10px;color:#CCC;">支持jpg,png,gif格式，大小不超过2M/张</div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
		                <button class="submit" id="commit" type="button">申请实名认证</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <form id="personalPhoto_form" action="public/uploadFile" method="post" enctype="multipart/form-data"></form>
    <form id="idCardPhoto_form" action="public/uploadFile" method="post" enctype="multipart/form-data"></form>
    <div class="clr"></div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<div class="overlay" id="apply_ok">
<div class="auth-status-alert">
	<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close" onclick="$('#apply_ok').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon success"></div>
		<div class="msg-box">
			<h2>认证申请提交成功！</h2>
			<p class="s1">返回认证管理即可查看申请进度。</p>
		</div>
	</div>
	<div class="back-bar"><a href="ucenter/auth?op=2">返回认证管理</a></div>
</div>
</div>
<div class="overlay" id="apply_err">
<div class="auth-status-alert">
	<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close" onclick="$('#apply_err').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon error"></div>
		<div class="msg-box">
			<h2>认证申请提交失败！</h2>
			<p class="s1" id="errMsg">详情请咨询技术客服。</p>
		</div>
	</div>
	<div class="back-bar"><a href="ucenter/auth?op=2">返回认证管理</a></div>
</div>
</div>
<script type="text/javascript" src="jsLib/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/auth_form3.js"></script>
</body>
</html>