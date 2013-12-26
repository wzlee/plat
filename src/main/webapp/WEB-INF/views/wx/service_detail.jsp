<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="${basePath}">
<title>${service.serviceName }</title>
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
		<div class="panel">
				<ul id="tabs" class="tabs two">
					<li class="active">服务详情</li>
					<li>所属服务机构</li>
				</ul>
				<div id="tabs-content">
					<div class="hide" style="display:block">
						<div class="view-info clearfix">
							<div class="pic fl">
								<c:if test="${service.picture.contains('http')}">
									<img src="${service.picture }" alt="">
								</c:if>
								<c:if test="${!service.picture.contains('http')}">
									<img src="upload/${service.picture }" alt="">
								</c:if>	
								<c:choose>
						      		<c:when test="${collected == true}">
						      			<a href="javascript:void(0);" class="collect">已收藏</a>
						      		</c:when>
						      		<c:otherwise>
						      			<a href="myFavorites/addFromWX?serviceId=${service.id}" class="collect">收藏该服务</a>
						      		</c:otherwise>
							    </c:choose>	
							</div>
							<div class="info">
								<h1 class="view-h1">${service.serviceName }</h1>
								<div class="row">
									<div class="col one">服务费用：</div>
									<div class="col">¥&nbsp;<span class="red">${service.costPrice }</span></div>
								</div>
								<div class="row">
									<div class="col one">评&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</div>
									<div class="col"><span class="stars level-5"></span></div>
								</div>
								<div class="row ">
									<div class="col one">已申请：</div>
									<div class="col">${service.serviceNum }次</div>
								</div>
								<div class="row">
									<div class="col one">&nbsp;</div>
									<div class="col"><a href="wxpage/userApply?id=${service.id }" class="btn-button">立即申请</a></div>
								</div>
								
							</div>
						</div>
						<div class="view-title">服务介绍</div>
						<div class="view-content">
							<p>
								${service.serviceProcedure }
							</p>
						</div>
					</div>
					<div class="hide">
						<div class="view-info clearfix">
							<div class="pic fl">
								<c:if test="${service.enterprise.photo.contains('http')}">
									<img src="${service.enterprise.photo}" alt="">
								</c:if>
								<c:if test="${!service.enterprise.photo.contains('http')}">
									<img src="upload/${service.enterprise.photo}" alt="">
								</c:if>								
							</div>
							<div class="info">
								<h1 class="view-h1">${service.enterprise.name }</h1>
								<div class="row">
									<div class="col one">经营模式：</div>
									<div class="col">
										<c:choose>
										<c:when test="${service.enterprise.businessPattern =='1' }">
			                        		生产型
			                        	</c:when>
												<c:when test="${service.enterprise.businessPattern == '2'}">
			                        		贸易型
			                        	</c:when>
												<c:when test="${service.enterprise.businessPattern == '3'}">
			                        		服务型
			                        	</c:when>
												<c:when test="${service.enterprise.businessPattern == '4'}">
			                        		生产型、贸易型
			                        	</c:when>
												<c:when test="${service.enterprise.businessPattern == '5'}">
			                        		贸易型、服务型
			                        	</c:when>
												<c:when test="${service.enterprise.businessPattern == '6'}">
			                        		生产型、贸易型、服务型
			                        	</c:when>
										<c:otherwise>
			                        		其它
			                        	</c:otherwise>
									</c:choose>
									</div>
								</div>
								<div class="row">
									<div class="col one">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</div>
									<div class="col">${service.enterprise.tel }</div>
								</div>
								<div class="row ">
									<div class="col one">联系人：</div>
									<div class="col">${service.enterprise.linkman }</div>
								</div>
								<div class="row">
									<div class="col one">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</div>
									<div class="col">${service.enterprise.address}</div>
								</div>
								<div class="row">
									<div class="col one">&nbsp;&nbsp;&nbsp;</div>
									<div class="col"><a href="wxpage/serviceDetail?id=${service.id }" class="btn-button">服务详情</a></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
<jsp:include page="footer.jsp" flush="true" />
<script type="text/javascript" src="resources/js/wx/wx_collect_service.js"></script>
</body>
</html>