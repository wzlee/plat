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
        <h3 class="top-title">买家管理中心>申诉管理</h3>
        <div class="easyui-tabs" data-options="tabWidth:112" style="width:780px;height:650px">
		<div title="我的申诉" style="padding:10px">
			<form id="serach_form" method="post">  
			    <div class="c_orderNo" style="width:200px;">  
			        <label for="orderNo">订单编号:</label>  
			        <input style="width:148px;height:20px;" class="easyui-validatebox" type="text" name="orderNo" id="myorderNo"/>  <!--data-options="required:true" --> 
			    </div>   
			    <div class="c_orderTime" style="width:200px;">  
			      	<label for="email">下单时间:</label>  
			       <input  class="easyui-datebox" style="width:150px" 
			       	name="startDate" id="mystartTime" data-options="showSeconds:false"  >  
			    </div>
			    <div class="c_orderTime" style="width:200px;">  
			      	<label for="email">结束时间:</label>  
			        <input  class="easyui-datebox" style="width:150px" 
			        	name="endDate" id="myendTime" data-options="showSeconds:false">
			    </div>
			    <div class="c_orderTime" style="width:100px;">
			    	<label for="email">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
			    	<input type="button" id="easyui-linkbutton1" value="搜索" style="height: 25px;width: 100px;float:right;font-size:13px;margin-left: 30px; margin-top: -2px;color: #800080;"/>
			    </div>   
			</form>  
			<table id="my-appeal" style="width:750px;height:524px">
			</table>
		</div>
		<div title="我被申诉" style="padding:10px">
			<form id="serach_form" method="post">  
			    <div class="c_orderNo" style="width:200px">  
			        <label for="orderNo">订单编号:</label>  
			        <input style="width:148px;height:20px;" class="easyui-validatebox" type="text" name="orderNo" id="beorderNo"/>  <!--data-options="required:true" --> 
			    </div>   
			    <div class="c_orderTime" style="width:200px">  
			      	<label for="email">下单时间:</label>  
			       <input  class="easyui-datebox" style="width:150px" 
			       	name="startDate" id="bestartTime" data-options="showSeconds:false"  >  
			    </div>
			    <div class="c_orderTime" style="width:200px">  
			      	<label for="email">结束时间:</label>  
			        <input  class="easyui-datebox" style="width:150px" 
			        	name="endDate" id="beendTime" data-options="showSeconds:false">
			    </div>
			    <div class="c_orderTime" style="width:100px;">
			    	<label for="email">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
			    	<input type="button" id="easyui-linkbutton2" value="搜索" style="height: 25px;width: 100px;float:right;font-size:13px;margin-left: 30px; margin-top: -2px;color: #800080;"/>
			    	<!-- <a href="javascript:void(0);" class="easyui-linkbutton "id="easyui-linkbutton2" data-options="iconCls:'icon-search'">查询</a> -->
			    </div>   
			</form>  
			<table id="be-appeal" style="width:750px;height:524px">

			</table>
		</div>
    </div>
    <div class="clr"></div> 
</div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/appeal.js"></script>
</body>
</html>