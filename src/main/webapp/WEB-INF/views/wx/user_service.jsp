<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = "";
	basePath =request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	basePath ="http://wx.smemall.net/";
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">
<title>我的服务-深圳中小企业服务平台</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta name="keywords" content="" />
<meta name="description" content="" />
<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
<div id="header">
	<a href="javascript:history.go(-1)" class="up-data"></a>
	<h1>我的服务</h1>
	<a href="/wx/index" class="home-icon"></a>
</div>
<!-- 头部 -->
<div class="container">
	<div class="panel user-page">
			<div class="user-info clearfix">
				<img src="http://dummyimage.com/65x65/660066/ffffff" alt="" class="fl">
				<div class="bfc">
					<div class="name"><strong>${user.userName }</strong>&nbsp;|&nbsp;${user.enterprise.name } <br></div>
					<img src="images/approve.jpg" alt="">
				</div>
			</div>
			<div class="title">
				<h3>我的服务</h3>
			</div>
				<form action="" method="post" class="">
					<ul class="form-search">
						<li><label for="">服务状态</label>
							<select name="" id="" class="normal-w">
								<option value="">已上架</option>
								<option value="">未上架</option>
							</select>
						</li>
						<li><label for="">服务名称</label><input type="text" name="" id="" class="normal-w"></li>
						<li><label for="">&nbsp;</label><input type="submit" value="搜索" class="btn-button"></li>
					</ul>

				</form>
			<div class="view-info clearfix">
			<c:forEach items="${userService }" var="serviceItem">
				<div class="pic fl">
					<img src="upload/${serviceItem.picture }" alt="">					
				</div>
				<div class="info">
					<div class="row">
						<div class="col one">服务名称：</div>
						<div class="col">${serviceItem.serviceName}</div>
					</div>
					<div class="row">
						<div class="col one">服务类别：</div>
						<div class="col">${serviceItem.category.text}</div>
					</div>
					<div class="row ">
						<div class="col one">服务状态：</div>
						<div class="col">
							<c:if test="${serviceItem.currentStatus ==3 }">
								已上架
							</c:if>
							<c:if test="${serviceItem.currentStatus ==3 }">
								变更审核中
							</c:if>
							<c:if test="${serviceItem.currentStatus ==3 }">
								下架审核中
							</c:if>
						</div>
					</div>
					<div class="row ">
						<div class="col one">价&nbsp;&nbsp;格：</div>
						<div class="col">¥:<span class="red">${serviceItem.costPrice}</span></div>
					</div>
					<div class="row ">
						<div class="col one">添加时间：</div>
						<div class="col">${serviceItem.registerTime}</div>
					</div>
				</div>	
			</c:forEach>
			</div>
			<a href="" class="load-more">点击加载更多</a>	
	</div>		
</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>