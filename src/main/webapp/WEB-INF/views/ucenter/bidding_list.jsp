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
        <h3 class="top-title">招标管理>我的招标</h3>
		<input type="hidden" id="userId" name="userId" value="${user.id }"/>
		<form id="serach_form" method="post">  
		    <div class="c_orderNo c_leng">  
		        <label for="orderNo">服务类别:</label>  
                <input id="categoryId" style="width:243px;" class="easyui-combotree" /> 
		    </div>  
		    <div class="c_orderStatus">  
		        <label for="orderStatus">服务名称:</label>  
		        <input id="serviceName" style="width:148px;height:20px;" class="easyui-validatebox" type="text" name="orderNo" />  <!--data-options="required:true" --> 
		    </div> 
		    <div class="c_orderTime c_leng">  
		      	<label for="startDate">创建时间:</label>  
		        <input  id="beginTime" class="easyui-datebox" style="width:105px" 
		       	name="startDate" data-options="showSeconds:false" /><a>&nbsp;&nbsp;&nbsp;到&nbsp;&nbsp;&nbsp;</a>
		     	<input id="endTime" class="easyui-datebox" style="width:105px" type="text"
		        	name="endTime" data-options="showSeconds:false"/>
		    </div>
		    <div class="c_endTime">
		    	<label for="email">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</label>
		    	<select id="status" style="width:150px;" class="easyui-combobox" name="orderStatus">  
				   	<option value="">全部</option>  
				    <option value="1">待发布</option>  
				    <option value="2">待审核</option>  
				    <option value="3">平台退回</option>  
				    <option value="4">招标中</option>  
				    <option value="5">应标中</option> 
				    <option value="6">交易进行中</option>  
				    <option value="7">等待买家关闭</option>   
				    <option value="8">等待卖家关闭</option>   
				    <option value="9">申诉处理中</option>   
				    <option value="10">交易结束</option>   
				    <option value="11">招标取消</option>   
				</select> 
		    </div>
		    <div class="c_submit c_leng2">
		     <input id="search" onclick ="bidding_list()" type="button" value="搜索" style="height: 25px;width: 100px;float:right;font-size:13px;margin-left: 30px; margin-top: 4px;color: #800080;"/>
		    </div>   
		</form>  
		<table id="bidding-list" style="width:800px;height:524px">
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