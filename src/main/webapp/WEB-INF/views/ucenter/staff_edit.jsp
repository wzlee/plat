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
    	<div><h3 class="top-title">子账号管理>编辑子账号<input onclick="window.location.href='ucenter/staff_list?op=88'" type="submit" value="返回" style="height: 25px;width: 100px;float:right;font-size:13px;color: #800080;"></h3></div>
    	<div class="auth-form-content">
            <form class="real_name_auth" action="" method="post">     
            	<input type="hidden" name="id" value="${staff.id }"/>  
            	<input type="hidden" name="assignTime" value="${staff.assignTime }" />
            	<input type="hidden" id="parentId" name="parentId" value="${staff.parent.id }"/>  
            	<input type="hidden" name="staffStatus" value="${staff.staffStatus }" />         
                <div class="control-group">
                    <label class="control-label">用户名称：</label>
                    <div class="controls">
                        <input class="input-large" id="userName" name="userName" type="text" value="${staff.userName }" readonly="readonly"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">密码：</label>
                    <div class="controls">
                        <input class="input-large" id="password" name="password" type="password" value="${staff.password }" readonly="readonly"/>
                        <a id="modifyPwdText" class="modifyPwdText f-purple" style="margin-left: 10px;" href="javascript:void(0);">修改密码</a>    
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">姓名：</label>
                    <div class="controls">
                        <input class="input-large" id="staffName" name="staffName" type="text" value="${staff.staffName }"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">性别：</label>
                    <div class="controls">
	                    <input name="sex" type="radio"  ${staff.sex == 0 ? 'checked="checked"':''} value="0"/>男
	                    <input name="sex" type="radio" ${staff.sex == 1 ? 'checked="checked"':''} value="1" />女
                   </div> 
                   </div>

                <div class="control-group">
                    <label class="control-label">联系电话：</label>
                    <div class="controls">
                        <input class="input-large" id="mobile" name="mobile"" type="text" value="${staff.mobile }"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">选择角色：</label>
                    <div class="controls"> 
                    	<div class="checkbox-group">
	                    	<c:forEach items="${staffRoleList }" var="staffRole"><input id="roleId" ${staff.staffRole.id == staffRole.id ? 'checked="checked"':'' } type="radio" name="roleId" value="${staffRole.id }">
                    			<span class="separate-mini">${staffRole.rolename }</span>
                    		</c:forEach>
                    	</div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
		                <button class="submit" id="modify" type="button">保存</button>
		                <button class="submit" onclick="window.location.href='ucenter/staff_list?op=88'" type="button">返回</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<div class="overlay" id="modify_pwd">
	<!-- 修改密码弹窗 -->
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
	<!-- 修改密码弹窗 -->
</div>
<!-- 弹窗 -->
<div class="overlay" id="apply_ok">
<div class="auth-status-alert">
	<a href="javascript:void(0)" class="close" onclick="$('#apply_ok').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon success"></div>
		<div class="msg-box">
			<h2>提交成功！</h2>
		</div>
	</div>
	<div class="back-bar"><a href="ucenter/staff_list?op=88">返回子账号管理</a></div>
</div>
</div>
<div class="overlay" id="apply_err">
<div class="auth-status-alert">
	<a href="javascript:void(0)" class="close" onclick="$('#apply_err').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon error"></div>
		<div class="msg-box">
			<h2>提交失败！</h2>
			<p class="s1">详情请咨询技术客服。</p>
		</div>
	</div>
	<div class="back-bar"><a href="ucenter/staff_list?op=88">返回子账号管理</a></div>
</div>
</div>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/staff_edit.js"></script>
</body>
</html>