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
    	<div><h3 class="top-title">应标管理 >我的应标</h3></div>
    	<input id="uid" type="hidden" value="${usertype==1?user.id:user.parent.id}">
    	<form id="serach_form" method="post" style="margin-top: 10px;">
    		<div class="bCategoryId" style="float:left;margin-top: 10px;margin-right: 30px;">  
				<label for="bCategoryId">服务类别:</label>
				<div id="myServices" style="display: none">
                 [{id : '', text : '全部'}
                 <c:forEach var="item" items="${loginEnterprise.myServices}" varStatus="flag">
                 ,{id : ${item.id }, text : '${item.text }'}
                 </c:forEach>]
                </div>
               <input id="sclass" style="width:243px;">
			</div>
	    	<div class="bName" style="float:left;margin-right: 10px;margin-top: 10px;">  
				<label for="bName">服务名称:</label>  
				<input id="bName" style="height:20px;width:180px;" class="easyui-validatebox" type="text" name="bName" />
			</div>
			<div class="time" style="float:left;margin-right: 30px;margin-top: 10px;margin-bottom: 20px;">  
				<label for="time">应标时间:</label>  
				<input  id="begintime" class="easyui-datebox" style="width:105px" name="begintime" data-options="showSeconds:false"  ><a>&nbsp;&nbsp;&nbsp;到&nbsp;&nbsp;&nbsp;</a>
				<input id="endtime" class="easyui-datebox" style="width:105px" type="text" name="endtime" data-options="showSeconds:false">				  
			</div> 
			<div class="bStatus" style="float:left;margin-right: 10px;margin-top: 10px;margin-bottom: 20px;">  
				<label for="bStatus">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</label>  
				<select id="bStatus" style="width:194px;" class="easyui-combobox" name="bStatus" data-options="editable:false,panelHeight:110">  
					    <option value="">全部</option>
					    <option value="5">应标中</option>  
					    <option value="6">应标失败</option>  
					    <option value="7">交易进行中</option>  
					    <option value="10">交易结束</option>  
					    <option value="11">招标取消</option>
				</select> 
			</div>  				    
			<div class="c_submit" style="float:left;margin-top: 10px;margin-bottom: 20px;">				    	
				<input id="search" type="button" value="搜索" style="height: 25px;width: 100px;float:right;font-size:13px;margin-left: 30px;color: #800080;">
			</div> 
		</form>
        <div style="padding: 10px 0px 0px 0px;">			
			<table id="responselist" style="width:800px;height:684px">
			</table>			
		</div>         
    </div>
    	
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<!--<script type="text/javascript" src="jsLib/jquery.easyui/jquery.min.js"></script>-->
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/my_response.js"></script>
<script type="">
	
</script>
</body>
</html>