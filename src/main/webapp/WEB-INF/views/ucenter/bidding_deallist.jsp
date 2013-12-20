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
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/demo.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/icon.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/order.css" />
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
        <h3 class="top-title">招标管理>待我处理</h3>
		<input type="hidden" id="userId" name="userId" value="${user.id }"/>
		<form id="serach_form" method="post">  
		    <div class="c_orderNo">  
		        <label for="orderNo">服务类别:</label>
                <input id="categoryId" style="width:150px;" class="easyui-combotree" /> 
		    </div>  
		    <div class="c_orderStatus">  
		        <label for="orderStatus">服务名称:</label>  
		        <input id="serviceName" style="width:148px;height:20px;" class="easyui-validatebox" type="text" name="orderNo" />  <!--data-options="required:true" --> 
		    </div> 
		    <div class="c_endTime">
		    	<label for="email">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</label>
		    	<select id="status" style="width:150px;" class="easyui-combobox" name="orderStatus">  
				   	<option value="">全部</option>  
				    <option value="1" ${param.orderStatus==1 ? 'selected="selected"' : ''}>待发布</option>  
				    <option value="3" ${param.orderStatus==3 ? 'selected="selected"' : ''}>平台退回</option>  
				    <option value="4" ${param.orderStatus==4 ? 'selected="selected"' : ''}>招标中</option>  
				</select> 
		    </div>
		    <div class="c_submit">
		     <input id="search" onclick ="bidding_deallist()" type="button" value="搜索" style="height: 25px;width: 100px;float:right;font-size:13px;margin-left: 30px; margin-top: 4px;color: #800080;"/>
		    </div>   
		</form>  
		<table id="bidding-deallist" style="width:800px;height:524px">
		</table>
    <div class="clr"></div>
</div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/bidding.js"></script>
<script type="text/javascript" src="app/data/PlatMap.js"></script>
</body>
</html>