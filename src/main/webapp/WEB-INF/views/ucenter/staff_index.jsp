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
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/icon.css" />
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
    	<div><h3 class="top-title">我的账号</h3></div>
    	<div class="pt10"><a id="btn" style="color:#606;font-size:14px;margin-left: 700px;" onclick="showform()" href="javascript:void(0);">编辑我的信息</a></div>
    	<div id="detail" class="auth-form-content" style="display:block;">
                <div class="control-group">
                    <label class="control-label">用户名称：</label>
                    <div class="controls">
                    	<div class="result-text">${user.userName}</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">密码：</label>
                    <div class="controls">
                         <div class="result-text">.............</div>                 
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">姓名：</label>
                    <div class="controls"> 
                    	<div class="result-text">${user.staffName}</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">性别：</label>
                    <div class="controls">
                    	<div class="result-text">${user.sex == 0?"男":"女"}</div>                    	                        
                    </div>
                </div>                
                <div class="control-group">
                    <label class="control-label">联系电话：</label>
                    <div class="controls">
                        <div class="result-text">${user.mobile}</div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">我的角色：</label>
                    <div class="controls">
                        <div class="result-text"><c:if test="${user.staffRole != null}">${user.staffRole.rolename}</c:if></div>
                    </div>
                </div> 
        </div>
        <div id="edit" class="auth-form-content" style="display:none;">
               <form class="real_name_auth" method="post">                
                <div class="control-group">
                    <label class="control-label">用户名称：</label>
                    <div class="controls">
                    	<input type="hidden" value="${user.id}" name="id">
                    	<input class="input-large" id="userName" type="text" readOnly="true" value="${user.userName}">                       	
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">密码：</label>                    
                    <div class="controls">
                        <input class="input-large" id="password" name="password" type="password" readOnly="true" value="${user.password}">                       
                        <a id="modifyPwdText" class="modifyPwdText f-purple" style="margin-left: 10px;" href="javascript:void(0);">修改密码</a>                                        
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">姓名：</label>
                    <div class="controls">
                    	<input class="input-large" id="staffName" name="staffName" type="text" value="${user.staffName}"> 
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">性别：</label>
                    <div class="controls">
                        <div class="checkbox-group">     
                        <input name="sex" type="radio"  ${user.sex == 0 ? 'checked="checked"':''} value="0"/>男               	
                        <input style="margin-left: 50px;" name="sex" type="radio" ${user.sex == 1 ? 'checked="checked"':''} value="1" />女
                   </div> 
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">联系电话：</label>
                    <div class="controls">
                        <input class="input-large" id="mobile" name="mobile" type="text" value="${user.mobile}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">我的角色：</label>
                    <div class="controls">
                        <div class="result-text"><c:if test="${user.staffRole != null}">${user.staffRole.rolename}</c:if></div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
		                <button class="submit" id="save" type="button">保存</button>
		                <button class="submit" id="cancel" type="button" style="margin-left: 30px;">取消</button>	                
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<!-- 修改密码弹窗 -->
<div class="overlay" id="modify_pwd">	
	<div class="auth-status-alert">
		<h2 class="title">修改密码</h2>	
		<a  class="close" href="javascript:void(0);" onclick="$('#modify_pwd').hide();"></a>
		<div class="auth-status-content" style="padding:60px 0 0 60px">		
			<div class="modify_pwd">
				<span>新密码:</span><input class="" id="newpwd" name="newpwd" type="password">
				<label for=""></label>		
            </div>
			<div class="modify_pwd">			
				<span>确认新密码:</span><input class="" id="confirmnewpwd" name="confirmnewpwd" type="password">
				<label for=""></label>					
			</div>
		</div>
		<div class="bottom-bar" >
			<a class="submit" href="javascript:void(0);" id="modifyPwd">修改密码</a>
			<a class="submit cancel" style="margin-left: 40px;" href="javascript:void(0);" onclick="$('#modify_pwd').hide();">取消</a>
		</div>
	</div>	
</div>
<!-- 修改密码弹窗 -->
<div class="overlay" id="apply_ok">
<div class="auth-status-alert">
<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close" onclick="$('#apply_ok').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon success"></div>
		<div class="msg-box">
			<h2>提交成功！</h2>
		</div>
	</div>
	<div class="back-bar"><a href="ucenter/staff_index?op=1">返回</a></div>
</div>
</div>
<div class="overlay" id="apply_err">
<div class="auth-status-alert">
<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close" onclick="$('#apply_err').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon error"></div>
		<div class="msg-box">
			<h2>提交失败！</h2>
			<p class="s1">详情请咨询技术客服。</p>
		</div>
	</div>
	<div class="back-bar"><a href="ucenter/staff_index?op=1">返回子账号管理</a></div>
</div>
</div>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/staff_index.js"></script>
</body>
</html>