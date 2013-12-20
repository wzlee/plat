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
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jsLib/jquery/jquery.form.js"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/auth_form.js"></script>
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
            <form action="ucenter/apply" method="post">
                <div class="control-group">
                    <label class="control-label" for="username">用户名：</label>
                    <div class="controls">
                    	<%-- 会员id --%>
                        <input name="user.id" value="${user.id }" type="hidden" />
                        <input name="enterprise.id" value="${user.enterprise.id }" type="hidden" />
                        <%-- 认证描述 1=实名认证 --%>
                        <input name="applyType" value="2" type="hidden" />
                        <%-- 处理状态 2=处理中 --%>
                        <input name="approveStatus" value="2" type="hidden" />
                        <input class="input-large" name="user.userName" value="${user.userName }" type="text" disabled="disabled" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="username">组织机构号：</label>
                    <div class="controls">
                    	<c:if test="${user.enterprise.isApproved }">
	                        <input class="input-large" id="icRegNumber" name="icRegNumber" type="text" disabled="disabled" value="${user.enterprise.icRegNumber }"/>
                        </c:if>
                    	<c:if test="${!user.enterprise.isApproved }">
	                        <input class="input-large" id="icRegNumber" name="icRegNumber" type="text" />
                        </c:if>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="username">公司实名：</label>
                    <div class="controls">
                    	<c:if test="${user.enterprise.isApproved }">
                        	<input class="input-large" id="name" name="name" type="text" disabled="disabled" value="${user.enterprise.name }"/>
                        </c:if>
                    	<c:if test="${!user.enterprise.isApproved }">
                        	<input class="input-large" id="name" name="name" type="text" />
                        </c:if>
                    </div>
                </div>
                <%-- 当已经通过实名认证 不再显示 --%>
                <c:if test="${!user.enterprise.isApproved }">
	                <div class="control-group">
	                    <label class="control-label">工商营业执照：</label>
	                    <div class="controls">
	                        <input class="file" id="licencefile" type="file" name="file"/>
	                        <input type="hidden" name="licenceDuplicate"/>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <label class="control-label">企业公函：</label>
	                    <div class="controls">
	                        <input class="file" id="businessLetter" type="file" name="file"/>
	                        <input type="hidden" name="businessLetter"/>
	                    </div>
	                </div>
	                <div class="control-group">
                    	<label class="control-label"></label>
	                    <div class="controls" >                        
	                        <div style="margin-bottom: 5px;">请下载<a target="_blank" href="upload/files/qysmrzsqgh.doc">《企业实名认证申请公函》</a>，加盖企业公章（合同</div>
	                        <div style="margin-bottom: 5px;">章、财务章等无效）后扫描或者拍照上传</div>
	                        <div style="margin-top: 10px;">支持jpg,png,gif格式，大小不超过2M/张</div>
	                    </div>
                	</div>
                </c:if>
                <%--  
                <div class="control-group">
                    <label class="control-label" for="username">服务类别：</label>
                    <div class="controls">
                        <input class="service-type" type="button" value="请选择" />
                    </div>
                </div> 
                --%>
                <div class="control-group">
                    <div class="controls">
		                <button class="submit" id="commit2" type="button">申请服务机构认证</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <form id="licenceDuplicate_form" action="public/uploadFile" method="post" enctype="multipart/form-data"></form>
    <form id="businessLetter_form" action="public/uploadFile" method="post" enctype="multipart/form-data"></form>
    <div class="clr"></div>
</div>
<!-- /container -->
<%--
<div class="overlay">
    <div class="service-layout">
        <div class="service-head">请选择服务类别 <span class="tip"><i>*注意</i>:请准确选择符合企业资质的服务,以便顺利通过审核。最多可选10项</span><a class="close" href="javascript:void(0)">X</a></div>
        <div class="service-body">
            <div class="selected-items">
                <div class="selected-name">已选择服务类别：</div>
                <ul class="selected-list">
       
                </ul>
            </div>
            <c:forEach var="parent" items="${requestScope.root.children }">
            	<div class="service-row">
	                <div class="service-name">${parent.text }</div>
	                <ul class="service-list">
	                	<c:forEach var="leaf" items="${parent.children }">
		                    <li><input type="checkbox" name="${leaf.id }" value="${leaf.id }" /><span>${leaf.text }</span></li>
	                	</c:forEach>
	                </ul>
	            </div>
            </c:forEach>
        </div>
    </div>
</div>
--%>
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
			<p class="s1">详情请咨询技术客服。</p>
		</div>
	</div>
	<div class="back-bar"><a href="ucenter/auth?op=2">返回认证管理</a></div>
</div>
</div>
</body>
</html>