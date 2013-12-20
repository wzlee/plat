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
        <h3 class="top-title">平台消息>消息类别</h3>
		<div style="margin:10px 0;">
        	<button id="addbutton" style="height: 25px;width: 100px;float:left;font-size:13px;margin-left: 30px; margin-top: -2px;color: #800080;">添加类别</button>
<!--         	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#w').window('close')">Close</a> -->
    	</div>
    	<div id="w" class="easyui-window" title="添加消息类别" data-options="maximizable:false,modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
			<form id="messagecategore" action="ucenter/savecategory" method="post" >
				<input type="hidden" id="categoryid"/>
				<div style="width: 400px">
			  		<div style="height:30px;width: 400px"><label for="" style="display:inline-block;width:80px;">消息类别：</label><input type="text"  id="cname"/ style="width: 150px"><span id="cname1" style="width: 170px"></span></div>
			  		<div style="height:30px;margin-top:20px;width: 400px"><label for="" style="display:inline-block;width:80px; vertical-align: top;">消息描述：</label><input type="text"  id="cdesc"/></div>
		  		</div>
			</form>
	  		<div style="height:30px;margin-top:20px"><label for="" style="display:inline-block;width:80px; vertical-align: top;">&nbsp;</label>
	  			<button id="submit" data-loading-text="处理中..." onclick="javascript:$('#messagecategore').submit();" style="height: 25px;width: 100px;color: #800080;">提交</button>
	  			<button id="cancelsubmit" style="height: 25px;width: 100px;font-size:13px;color: #800080;margin-left: 20px">取消</button>
	  		</div>
    	</div> 
	
		<table id="my-send" style="width:750px;height:524px"></table>
    	<div class="clr"></div> 
	</div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script src="jsLib/bootstrap/js/bootstrap-button.js" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/category.js"></script>
</body>
</html>