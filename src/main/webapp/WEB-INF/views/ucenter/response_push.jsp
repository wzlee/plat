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
    	<div><h3 class="top-title">应标管理 >给我推送</h3></div>    	
    	<input id="uid" type="hidden" value="${usertype==1?user.id:user.parent.id}">    	
    	<form id="serach_form" method="post" style="margin-top: 10px;margin-bottom: 20px;">
    		<div class="sclass" style="float:left;width:260px;margin-top: 10px;margin-bottom: 20px;">  
				<label for="sclass">服务类别:</label>  
				 <div id="myServices" style="display: none">
                 [{id : '', text : '全部'}
                 <c:forEach var="item" items="${loginEnterprise.myServices}" varStatus="flag">
                 ,{id : ${item.id }, text : '${item.text }'}
                 </c:forEach>]
                </div>
               <input id="sclass" name="cid" style="width:137px;">
			</div>
	    	<div class="sname" style="float:left;width:260px;margin-right: 10px;margin-top: 10px;margin-bottom: 20px;">  
				<label for="sname">服务名称:</label>  
				<input id="sname" style="width:148px;height:20px;" class="easyui-validatebox" type="text" name="sname" />
			</div> 				    
			<div class="c_submit" style="float:left;margin-top: 10px;margin-bottom: 20px;">				    	
				<input id="search" type="button" value="搜索" style="height: 25px;width: 100px;float:right;font-size:13px;margin-left: 30px; color: #800080;">
			</div> 
		</form>
        <div style="padding: 10px 0px 0px 0px;">			
			<table id="pushlist" style="width:800px;height:684px">
			</table>			
		</div>         
    </div>
    	
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<!--<script type="text/javascript" src="jsLib/jquery.easyui/jquery.min.js"></script>-->
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="resources/js/ucenter/response_push.js"></script>
<script type="">
	
</script>
</body>
</html>