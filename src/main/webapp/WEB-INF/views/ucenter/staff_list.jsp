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
<c:set value='<%=request.getSession().getAttribute("user")%>' var="user"/>
<jsp:include page="header.jsp" flush="true"/>
<!-- /header -->
<jsp:include page="head.jsp" flush="true"/>
<!-- /user-header -->
<!-- /container -->
<div class="main-container">
   <!-- 左边菜单 -->
   	<jsp:include page="left.jsp" flush="true" />
    <div class="main-column">
    	<div><h3 class="top-title">我的子账号<input onclick="window.location.href='ucenter/staff_add?type=${user.enterprise.type}&op=88'" type="submit" value="添加子账号" style="height: 25px;width: 100px;float:right;font-size:13px;color: #800080;"></h3></div>    	
    	<input id="pid" type="hidden" value="${user.id}">
    	<input id="eType" type="hidden" value="${user.enterprise.type}">
    	<form id="serach_form" method="post" style="margin-top: 10px;margin-bottom: 20px;">
    		<div class="status" style="float:left;width:260px;margin-top: 10px;margin-bottom: 20px;">  
				<label for="sclass">状态:</label>
               	<select id="staffStatus" name="staffStatus" style="width:137px;">  
				    <option value="">全部</option>  
				    <option value=1>正常</option>  
				    <option value=2>禁用</option>
				</select>
			</div>
	    	<div class="sname" style="float:left;width:260px;margin-right: 10px;margin-top: 10px;margin-bottom: 20px;">  
				<label for="staffRoleId">所属角色:</label>  
				<input id="staffRoleId" style="width:137px;" name="staffRoleId" />
			</div> 				    
			<div class="c_submit" style="float:left;margin-top: 10px;margin-bottom: 20px;">				    	
				<input id="search" type="button" value="搜索" style="height: 25px;width: 100px;float:right;font-size:13px;margin-left: 30px;color: #800080;">
			</div> 
		</form>
        <div style="padding: 10px 0px 0px 0px;">			
			<table id="stafflist" style="width:800px;height:684px">
			</table>			
		</div>         
    </div>
    	
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/staff_list.js"></script>
</body>
</html>