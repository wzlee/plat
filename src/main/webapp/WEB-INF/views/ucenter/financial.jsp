<%--
认证企业首页
 --%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.eaglec.com/jsp/user/functions" prefix="my"%>
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
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/index.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jNotify/jNotify.jquery.css" />
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
    	<div class="zrzx">
		<iframe src="http://rz.smemall.net/index.php/user_profile" frameborder="0"  style="width:100%;height:1400px;"></iframe>
		</div>
      
	
    </div>
    <div class="clr"></div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<!-- 我感兴趣的服务领域 -->
	<div class="overlay interesting">
		<div class="service-layout">
			<div class="service-head">
				请选择服务类别 
				<button class="save" type="button">保存</button>
				<input id="userId" type="hidden" value="${user.id }"/>
				<a class="close" href="javascript:void(0)">X</a>
			</div>
			<div class="service-body">
				<div class="selected-items">
					<div class="selected-name">已选择服务类别：</div>
					<ul class="selected-list">
					<c:forEach items="${user.interestServices}" var="item">
						<li>
							<input type="checkbox" value="${item.id }" name="${item.id }" checked="checked"/>
							<span>${item.text }</span>
						</li>
					</c:forEach>
					</ul>
				</div>
				<c:forEach var="parent" items="${requestScope.root.children }">
					<div class="service-row">
						<div class="service-name">${parent.text }</div>
						<ul class="service-list">
							<c:forEach var="leaf" items="${parent.children }">
								<li><input type="checkbox" name="${leaf.id }"
									value="${leaf.id }" ${user.interestServices.contains(leaf)==true?'checked="checked"':'' }  /><span>${leaf.text }</span></li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="jsLib/jNotify/jNotify.jquery.min.js"></script>
<script type="text/javascript" src="resources/js/main/collectService.js"></script>
</body>
</html>