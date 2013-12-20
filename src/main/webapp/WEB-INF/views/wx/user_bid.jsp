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
<title>我的招标-深圳中小企业服务平台</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta name="keywords" content="" />
<meta name="description" content="" />
<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
<div id="header">
	<a href="javascript:history.go(-1)" class="up-data"></a>
	<h1>我的招标</h1>
	<a href="/wx/index" class="home-icon"></a>
</div>
<!-- 头部 -->
<div class="container">
	<div class="panel user-page">
			<div class="user-info clearfix">
				<img src="http://dummyimage.com/65x65/660066/ffffff" alt="" class="fl">
				<div class="bfc">
					<div class="name">${user.userName }</strong>&nbsp;|&nbsp;${user.enterprise.name } <br></div>
					<img src="images/approve.jpg" alt="">
				</div>
			</div>
			<div class="title">
				<h3>我的招标</h3>
			</div>
				<form action="" method="post" class="">
					<ul class="form-search">
						<li><label for="">服务状态</label>
							<select name="" id="" class="normal-w">
								<option value="">待发布</option>
								<option value="">已发布</option>
							</select>
						</li>
						<li><label for="">服务名称</label><input type="text" name="" id="" class="normal-w"></li>
						<li><label for="">&nbsp;</label><input type="submit" value="搜索" class="btn-button"></li>
					</ul>
				</form>
				<c:forEach items="${userBidList }" var="item">
				<div class="bid-result">
					<ul>
						<li><label for="">招标单号:</label>${item.bidNo }</li>
						<li><label for="">服务名称:</label>${item.name }</li>
						<li><label for="">服务类别:</label>${item.category.text }</li>
						<li><label for="">招标价格:</label>${item.minPrice }-${item.maxPrice }</li>
						<li><label for="">状态:</label>${item.bidNo }</li>
						<li><label for="">创建时间:</label>${item.createTime }</li>
						<li><label for="">联系人:</label>${item.linkMan }</li>
						<li><label for="">联系电话:</label>${item.tel }</li>
						<li><label for="">描述:</label>${item.description }</li>
					</ul>
				</div>
				</c:forEach>
				
			<a href="" class="load-more">点击加载更多</a>	
	</div>		
</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>