<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.eaglec.com/jsp/user/functions" prefix="my"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>服务商城-深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet"
	href="resources/css/main/common.css" />
<link type="text/css" rel="stylesheet"
	href="resources/css/main/mall.css" />
</head>

<c:choose>
	<c:when test="${mallId==1 }">
		<body>
	</c:when>
	<c:when test="${mallId==2 }">
		<body class="style-green">
	</c:when>
	<c:when test="${mallId==3 }">
		<body class="style-purple">
	</c:when>
	<c:when test="${mallId==4 }">
		<body class="style-blue">
	</c:when>
	<c:otherwise>
		<body class="style-blue">
	</c:otherwise>
</c:choose>
<jsp:include page="../layout/head.jsp" flush="true" />
<div class="wrap">
		<div class="dom-banner m-t-14">
			<div class="title"><img src="resources/images/main/icon-${mallId}.png" alt="" onerror="this.src='resources/images/service/default_service_pic.gif'"/>${mallCategory.text}</div>
			<ul class="dom-box">
			  <c:forEach items="${banneList }" var="banneritem">
			  	<c:if test="${banneritem.micon.contains('http')}">
			  		<li class="s1" style="background:url(${banneritem.micon}) no-repeat"></li>
			  	</c:if>
			 	 <c:if test="${!banneritem.micon.contains('http')}">
			  		<li class="s1" style="background:url(upload/${banneritem.micon}) no-repeat"></li>
			  	</c:if>
			  </c:forEach>
			</ul>
	  	</div>
	<div class="clearfix m-t-10">
		<div class="column-l">
			<div class="all-cate-column">
				<h3>服务分类</h3>
				<ul id="frist_list" class="mod-cate">
					<span class="cate-loading"></span>
				</ul>
			</div>
			<div class="adv m-t-14">
				<img src="resources/images/main/adv-5.jpg" alt="" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
			</div>
		</div>
		<div class="recommend-block">
			<div class="scrollTab m-t-20">
				<div class="krakatoa" data-settings="{ items : 3, autoplay : false loop : true }">
					<c:forEach items="${topAdList }" var="topAdListItem">
						<a href="${topAdListItem.pageLink}">
							<c:if test="${topAdListItem.uploadImage.contains('http')}">
			  					<img src="${topAdListItem.uploadImage }" onerror="this.src='resources/images/service/default_service_pic.gif'" />
			  				</c:if>
							<c:if test="${!topAdListItem.uploadImage.contains('http')}">
			  					<img src="upload/${topAdListItem.uploadImage }" onerror="this.src='resources/images/service/default_service_pic.gif'" />
			  				</c:if>
						</a>
					</c:forEach>	
				</div>
			</div>
			<div class="recommend-tab m-t-10">
				<ul class="bb-cy clearfix" id="recommend-tab">
					<li>热门服务</li>
					<li>最新服务</li>
					<li>推荐服务</li>
					<li>好评服务</li>
				</ul>
				<div class="recommend-box m-t-20">
					<div class="recommend-list" style="dispaly:block">
						<ul class="service-list clearfix">
							<c:forEach items="${mallHotService }" var="hotserviceitem">
								<li>
								 <input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${hotserviceitem.enterprise.id }"/> 
									<div class="pic">
										<a href="service/detail?id=${hotserviceitem.id }&op=mall"
											target="_blank">
											<c:if test="${hotserviceitem.picture.contains('http')}">
												<img src="${hotserviceitem.picture }" 
											alt="" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
											</c:if>
											<c:if test="${!hotserviceitem.picture.contains('http')}">
												<img src="upload/${hotserviceitem.picture }" 
											alt="" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
											</c:if>
											</a>

									</div>
									<h4>
										<a href="service/detail?id=${hotserviceitem.id }&op=mall"
											target="_blank">
											<c:choose>
												<c:when test="${hotserviceitem.serviceName.length()<=14 }">
													${hotserviceitem.serviceName}
												</c:when>
												<c:otherwise>
													${fn:substring(my:replaceHTML(hotserviceitem.serviceName), 0, 14) }...
												</c:otherwise>
											</c:choose>
											
											</a>
									</h4> <span class="price">
												<%-- <i>¥</i>${hotserviceitem.costPrice} --%>
												<c:choose>
									        		<c:when test="${hotserviceitem.costPrice == null or hotserviceitem.costPrice == 0}"><span class="meta-price">面议</span></c:when>
										        	<c:otherwise>
										        		<span class="meta-yuan">￥</span><span class="meta-price">${hotserviceitem.costPrice }</span>
										        	</c:otherwise>
									        	</c:choose>
											</span> 
										<a class="apply-s" href="service/detail?id=${hotserviceitem.id }&op=mall"
											target="_blank">查看详情</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="recommend-list">
						<ul class="service-list clearfix">
							<c:forEach items="${mallNewService }" var="newserviceitem">
								<li>
								<input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${newserviceitem.enterprise.id }"/>
									<div class="pic">
										<a href="service/detail?id=${newserviceitem.id }&op=mall"
											target="_blank">
											<c:if test="${newserviceitem.picture.contains('http')}">
												<img src="${newserviceitem.picture }" 
											alt="" onerror="this.src='resources/images/service/default_service_pic.gif'" />
											</c:if>
											<c:if test="${!newserviceitem.picture.contains('http')}">
												<img src="upload/${newserviceitem.picture }" 
											alt="" onerror="this.src='resources/images/service/default_service_pic.gif'" />
											</c:if>
											</a>
									</div>
									<h4>
										<a href="service/detail?id=${newserviceitem.id }&op=mall"
											target="_blank">
											<c:choose>
												<c:when test="${newserviceitem.serviceName.length()<=14 }">
													${newserviceitem.serviceName}
												</c:when>
												<c:otherwise>
													${fn:substring(my:replaceHTML(newserviceitem.serviceName), 0, 14) }...
												</c:otherwise>
											</c:choose>
											</a>
									</h4> <span class="price">
												<%-- <i>¥</i>${newserviceitem.costPrice} --%>
												<c:choose>
									        		<c:when test="${newserviceitem.costPrice == null or newserviceitem.costPrice == 0}"><span class="meta-price">面议</span></c:when>
										        	<c:otherwise>
										        		<span class="meta-yuan">￥</span><span class="meta-price">${newserviceitem.costPrice }</span>
										        	</c:otherwise>
									        	</c:choose>
										</span>
										<a class="apply-s" href="service/detail?id=${newserviceitem.id }&op=mall"
											target="_blank">查看详情</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="recommend-list">
						<ul class="service-list clearfix">
							<c:forEach items="${mallRecommendService }"
								var="recommendserviceitem">
								<li>
								<input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${recommendserviceitem.service.enterprise.id }"/>
									<div class="pic">
										<a href="service/detail?id=${recommendserviceitem.service.id }&op=mall"
											target="_blank">
											<c:if test="${recommendserviceitem.service.picture.contains('http')}">
												<img src="${recommendserviceitem.service.picture }" 
											alt="" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
											</c:if>
											<c:if test="${!recommendserviceitem.service.picture.contains('http')}">
												<img src="upload/${recommendserviceitem.service.picture }" 
											alt="" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
											</c:if>
											</a>
									</div>
									<h4>
										<a href="service/detail?id=${recommendserviceitem.service.id }&op=mall"
											target="_blank">
											<c:choose>
												<c:when test="${recommendserviceitem.service.serviceName.length()<=14 }">
													${recommendserviceitem.service.serviceName}
												</c:when>
												<c:otherwise>
													${fn:substring(my:replaceHTML(recommendserviceitem.service.serviceName), 0, 14) }...
												</c:otherwise>
											</c:choose>
											</a>
									</h4> <span class="price">
												<%-- <i>¥</i>${recommendserviceitem.service.costPrice} --%>
												<c:choose>
									        		<c:when test="${recommendserviceitem.service.costPrice == null or recommendserviceitem.service.costPrice == 0}"><span class="meta-price">面议</span></c:when>
										        	<c:otherwise>
										        		<span class="meta-yuan">￥</span><span class="meta-price">${recommendserviceitem.service.costPrice }</span>
										        	</c:otherwise>
									        	</c:choose>
											</span> 
										<a class="apply-s" href="service/detail?id=${recommendserviceitem.service.id }&op=mall"
											target="_blank">查看详情</a>
								</li>
							</c:forEach>

						</ul>
					</div>
					<div class="recommend-list">
						<ul class="service-list clearfix">
							<c:forEach items="${mallGoodService }" var="goodserviceitem">
								<li>
								<input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${goodserviceitem.enterprise.id }"/>
									<div class="pic">
										<a href="service/detail?id=${goodserviceitem.id }&op=mall"
											target="_blank">
											<c:if test="${goodserviceitem.picture.contains('http')}">
												<img src="${goodserviceitem.picture }" 
											alt="" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
											</c:if>
											<c:if test="${!goodserviceitem.picture.contains('http')}">
												<img src="upload/${goodserviceitem.picture }" 
											alt="" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
											</c:if>
											</a>
									</div>
									<h4>
										<a href="service/detail?id=${goodserviceitem.id }&op=mall"
											target="_blank">
											<c:choose>
												<c:when test="${goodserviceitem.serviceName.length()<=14 }">
													${goodserviceitem.serviceName}
												</c:when>
												<c:otherwise>
													${fn:substring(my:replaceHTML(goodserviceitem.serviceName), 0, 14) }...
												</c:otherwise>
											</c:choose>
											</a>
									</h4> <span class="price">
												<%-- <i>¥</i>${goodserviceitem.costPrice} --%>
												<c:choose>
									        		<c:when test="${goodserviceitem.costPrice == null or goodserviceitem.costPrice == 0}"><span class="meta-price">面议</span></c:when>
										        	<c:otherwise>
										        		<span class="meta-yuan">￥</span><span class="meta-price">${goodserviceitem.costPrice }</span>
										        	</c:otherwise>
									        	</c:choose>
										  </span>
										<a class="apply-s" href="service/detail?id=${goodserviceitem.id }&op=mall"
											target="_blank">查看详情</a>
									
								</li>
							</c:forEach>

						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="adv-960 m-t-10">
		<a href=""><img src="resources/images/main/adv-960.jpg" alt="" /></a>
	</div>
	<!-- /960 -->
	<c:forEach items="${allList}" var="allListItem">
	<c:if test="${allListItem.recomServiceList.size()>0}">
		<div class="service-block m-t-20 ">
			<div class="title clearfix">
				<div class="column-l">
					<h3 class="n-column">${allListItem.category.text }</h3>
				</div>
				<div class="column-r category ta-r">
					<c:forEach items="${allListItem.category.children }" var="mapchildrenitem"
						varStatus="itemcount">
						<c:if test="${itemcount.index<5 }">
							<a href="mall/mallList?mallId=${mapchildrenitem.id}">${mapchildrenitem.text }</a>
						</c:if>
					</c:forEach>
					<a href="mall/mallList?mallId=${allListItem.category.id}" class="more-all">查看全部</a>
		 		</div>
			</div>
			<div class="m-t-14 clearfix">
				<div class="column-l">
					<c:forEach items="${allListItem.serviceAdList}" var="serviceAdItem">
						<div class="mall-adv">
							<a href="${serviceAdItem.pageLink }">
							<c:if test="${serviceAdItem.uploadImage.contains('http')}">
								<img src="${serviceAdItem.uploadImage }" alt="" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
							</c:if>
							<c:if test="${!serviceAdItem.uploadImage.contains('http')}">
								<img src="upload/${serviceAdItem.uploadImage }" alt="" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
							</c:if>
							</a>
						</div>
					</c:forEach>
					
				</div>
				<div class="column-r">
					<ul class="service-list clearfix">
						<c:forEach items="${allListItem.recomServiceList}" var="recomServiceItem">
						<input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${recomServiceItem.service.enterprise.id }"/>
							<li>
								<div class="pic">
									<a href="${recomServiceItem.pageLink}"
											target="_blank">
											<c:if test="${recomServiceItem.service.picture.contains('http')}">
												<img src="${recomServiceItem.service.picture }" 
											alt="" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
											</c:if>
											<c:if test="${!recomServiceItem.service.picture.contains('http')}">
												<img src="upload/${recomServiceItem.service.picture }" 
											alt="" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
											</c:if>
											</a>
								</div>
								<h4>
									<a href="service/detail?id=${recomServiceItem.service.id }&op=mall"
											target="_blank">
											<c:choose>
												<c:when test="${recomServiceItem.service.serviceName.length()<=14 }">
													${recomServiceItem.service.serviceName}
												</c:when>
												<c:otherwise>
													${fn:substring(my:replaceHTML(recomServiceItem.service.serviceName), 0, 14) }...
												</c:otherwise>
											</c:choose>
											</a>
									<a class="apply-s" href="service/detail?id=${recomServiceItem.service.id }&op=mall"
											target="_blank">查看详情</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		</c:if>
	</c:forEach>
</div>
<jsp:include page="../layout/footer.jsp" flush="true" />
<script type="text/javascript">
    var mid = '${param.mallId}';
</script>
<script type="text/javascript"
	src="resources/js/main/jquery.krakatoa.js"></script>
<script type="text/javascript" src="resources/js/main/mall.js"></script>
<script>
	$(window).on('load',function(){
		$('.krakatoa').krakatoa( { width: '703px', height: '180px' });
	});
</script>
</body>
</html>