<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="${basePath}">
<title>我的招标</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta name="keywords" content="" />
<meta name="description" content="" />
<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
	<div id="header">
		<a href="javascript:history.go(-1)" class="up-data"></a>
		<img src="resources/images/wx-logo.png" alt="">
		<a href="/wx/index" class="home-icon"></a>
	</div>
	<!-- 头部 -->
	<div class="container">
		<div class="panel user-page">
			<div class="user-info clearfix">
				<img src="${loginEnterprise.photo}" alt="" class="fl">
				<div class="bfc">
					<div class="name">${user.userName }</strong>&nbsp;|&nbsp;${user.enterprise.name } <br></div>
					<img src="images/approve.jpg" alt="">
				</div>
			</div>
			<div class="title">
				<h3>我的招标</h3>
			</div>
			<form id="myBidForm">
				<input type="hidden" name="limit" value="6">
				<input type="hidden" id="start" name="start" value="0">
				<ul class="form-search">
					<li><label for="">服务状态</label>
						<select name="status" class="normal-w select-w">
						<!-- 1、待发布 2、待审核 3、平台退回 4、招标中 5、应标中 6、交易进行中 7、等待买家关闭 8、等待卖家关闭
							 * 9、申诉处理中 10、交易结束 11、招标取消 12、订单取消 -->
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
							<option value="12">订单取消</option>
							<option value="" selected="selected">全部</option>
						</select></li>
					<li><label for="">服务名称</label><input type="text" name="title" class="normal-w"></li>
					<li><label for="">&nbsp;</label><input id="myBidBut" type="button" value="搜索" class="btn-button"></li>
				</ul>
			</form>
			<div id="myBidDiv">
				<c:forEach items="${userBidList }" var="item">
					<div class="bid-result">
						<ul>
							<li><label for="">招标单号:</label>${item.bidNo }</li>
							<li><label for="">服务名称:</label>${item.name }</li>
							<li><label for="">服务类别:</label>${item.category.text }</li>
							<li><label for="">招标价格:</label>${item.minPrice }-${item.maxPrice }</li>
							<li><label for="">状态:</label>
							<!-- 1、待发布 2、待审核 3、平台退回 4、招标中 5、应标中 6、交易进行中 7、等待买家关闭 8、等待卖家关闭
							 * 9、申诉处理中 10、交易结束 11、招标取消 12、订单取消-->
								<c:if test="${item.status ==1}">
									待发布
								</c:if>
								<c:if test="${item.status ==2}">
									待审核
								</c:if>
								<c:if test="${item.status ==3}">
									平台退回
								</c:if>
								<c:if test="${item.status ==4}">
									招标中
								</c:if>
								<c:if test="${item.status ==5}">
									应标中
								</c:if>
								<c:if test="${item.status ==6}">
									交易进行中
								</c:if>
								<c:if test="${item.status ==7}">
									等待买家关闭
								</c:if>
								<c:if test="${item.status ==8}">
									等待卖家关闭
								</c:if>
								<c:if test="${item.status ==9}">
									申诉处理中
								</c:if>
								<c:if test="${item.status ==10}">
									交易结束
								</c:if>
								<c:if test="${item.status ==11}">
									招标取消
								</c:if>
								<c:if test="${item.status ==12}">
									订单取消
								</c:if>
							</li>
							<li><label for="">创建时间:</label>${item.createTime }</li>
							<li><label for="">联系人:</label>${item.linkMan }</li>
							<li><label for="">联系电话:</label>${item.tel }</li>
							<li><label for="">描述:</label>${item.description }</li>
						</ul>
					</div>
				</c:forEach>
			</div>
			<a type="myBid" href="javascript:void(0);" class="search_load-more">点击加载更多</a>	
		</div>		
	</div>
	<jsp:include page="footer.jsp" flush="true" />
	<script type="text/javascript" src="resources/js/wx/wx.js"></script>
</body>
</html>