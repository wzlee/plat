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
    		<div><h3 class="top-title">服务管理 <input onclick="window.location.href='/ucenter/service_add?op=5'" type="submit" value="新增服务" style="height: 25px;width: 100px;float:right;font-size:13px;color: #800080;"></h3></div>
    		<div style="padding:10px">
    			<input id=user type="hidden" value="${loginEnterprise.id}">
    			<form id="serach_form" method="post" style="margin-top: 10px;margin-bottom: 20px;">    				
	    			<div class="sname" style="float:left;width:260px;margin-right: 10px;margin-top: 10px;margin-bottom: 20px;">  
				        <label for="sname">服务名称:</label>  				        
				        <input id="sname" style="width:148px;height:20px;" class="easyui-validatebox" type="text" name="serviceName" />
				    </div> 
				    <div class="sclass" style="float:left;width:260px;margin-top: 10px;margin-bottom: 20px;">  
				        <label for="sclass">服务类别:</label>
				        <div id="myServices" style="display: none">
		                 [{id : '', text : '全部'}
		                 <c:forEach var="item" items="${loginEnterprise.myServices}" varStatus="flag">
		                 ,{id : ${item.id }, text : '${item.text }'}
		                 </c:forEach>]
		                </div>	         
				        <input id="sclass" style="width:150px;" name="cid">
				    </div>
				    <div class="c_submit" style="float:left;margin-top: 10px;margin-bottom: 20px;">				    	
			    		<input id="search" type="button" value="搜索" style="height: 25px;width: 100px;float:right;font-size:13px;margin-left: 30px;color: #800080;">
			    	</div> 
			    </form>
    			<div id="service" class="easyui-tabs" data-options="plain:true,border:false" style="width:752px;height:780px">
					<div title="新服务" style="padding: 10px 0px 0px 0px;">
						<div class="pb10">序号前加*号的服务为平台管理人员添加的服务</div>
						<table id="nservice" style="width:751px;height:684px">
						</table>			
					</div>
					<div title="已上架" style="padding: 10px 0px 0px 0px;">
						<div class="pb10">序号前加*号的服务为平台管理人员添加的服务</div>
						<table id="uservice" style="width:751px;height:684px">				
						</table>
					</div>
					<div title="已下架" style="padding: 10px 0px 0px 0px;">
						<div class="pb10">序号前加*号的服务为平台管理人员添加的服务</div>
						<table id="dservice" style="width:751px;height:684px">				
						</table>
					</div>
					<div title="已删除" style="padding: 10px 0px 0px 0px;">
						<div class="pb10">序号前加*号的服务为平台管理人员添加的服务</div>
						<table id="rservice" style="width:751px;height:684px">				
						</table>
					</div>
				</div>	
			</div>
    </div>    
	<div class="clr"></div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/service_list.js"></script>
<script type="text/javascript">
	
</script>
</body>
</html>