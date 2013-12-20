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
<link href="jsLib/artDialog-5.0.3/skins/default.css" rel="stylesheet" type="text/css" />
<link href="jsLib/artDialog-5.0.3/skins/login.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/style.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/order.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/demo.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/icon.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />

<style type="text/css">
	.delete{
		height: 25px;
		width: 100px;
		font-size:13px;
		margin-left: 30px; 
		margin-top: -2px;
		color: #800080;
	}
	.sendmessage{
		height: 25px;
		width: 100px;
		float:left;
		font-size:13px;
		margin-left: 30px; 
		margin-top: -2px;
		color: #800080;
	}
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
        <h3 class="top-title">平台消息>站内消息</h3>
        <div class="easyui-tabs" data-options="tabWidth:112" style="width:780px;height:650px">
        <input type="hidden" id="userId" name="userId" value="${user.id }"/>
		<div title="已发送" style="padding:10px">
			<input type="button" value="发送消息" class="sendmessage"/>
			<input id="deletes" data-loading-text="处理中..." type="button" value="删除消息" class="delete"/>
			<table id="my-send" style="width:750px;height:524px"></table>
		</div>
		<div title="已接收" ${param.flag == 'receiver' ? 'selected="true"' : ''} style="padding:10px">
			<input type="hidden" id="rMessageType" value="${param.messageType }" />
			<input type="button" value="发送消息" class="sendmessage"/>
			<input id="deleter" data-loading-text="处理中..." type="button" value="删除消息" class="delete"/>
			<table id="my-receiver" style="width:750px;height:524px"></table>
		</div>
		<div title="已删除" style="padding:10px">
			<input type="button" value="发送消息" class="sendmessage"/>
			<table id="my-delete" style="width:750px;height:524px"></table>
		</div>
    </div>
    <div class="clr"></div> 
</div>
</div>
<!-- 发消息window -->
<div class="apply-html hide">
	<form id="messagecategore" action="ucenter/save" method="post" class="apply-form">
		<div style="width: 500px">
	 		<div style="height:30px;margin-top:20px;width: 500px"><label for="" style="display:inline-block;width:80px; vertical-align: top;">收信人：</label><input type="text" id="cname" style="width: 240px;"/><span id="cname1" style="width: 170px;"></span></div>
	 		<div style="height:30px;margin-top:20px;width: 500px"><label for="" style="display:inline-block;width:80px;">消息类别：</label><input id="cc" style="width: 250px;"/><span id="cc1" style="width: 170px;"></span></div>
	 		<div style="height:120px;margin-top:20px;width: 500px"><label for="" style="display:inline-block;width:80px; vertical-align: top;">消息内容：</label><textarea id="content" style="height:85px;width:250px"></textarea><span id="content1" style="width: 170px;"></span></div>
		</div>
	</form>
</div>
<!-- <div id="w" class="easyui-window" title="发送消息" data-options="maximizable:false,modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:280px;padding:10px;">
	<div style="height:30px;margin-top:20px"><label for="" style="display:inline-block;width:80px; vertical-align: top;">&nbsp;</label>
		<button id="submit" data-loading-text="处理中..." onclick="javascript:$('#messagecategore').submit();" style="height: 25px;width: 100px;color: #800080;">提交</button>
		<button id="cancelsubmit" style="height: 25px;width: 100px;font-size:13px;color: #800080;margin-left: 20px">取消</button>
	</div>
</div>  -->
<!-- 读消息window -->
<!-- <div id="r" class="easyui-window" title="消息内容详情" data-options="maximizable:false,inline:true,modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
</div>  -->
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script src="jsLib/bootstrap/js/bootstrap-button.js" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/info.js"></script>
</body>
</html>