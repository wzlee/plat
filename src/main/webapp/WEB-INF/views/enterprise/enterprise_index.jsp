<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
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
<title>服务机构-深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet"
	href="resources/css/main/company.css" />
</head>

<body>
	<jsp:include page="../layout/head.jsp" flush="true" />
	<div class="wrap">
		<div class="crumb-nav">
			<a href=""></a>首页&gt;
			<c:choose>
				<c:when test="${enterprise.type eq '3' }">
				<a href="enter/index">服务机构</a></c:when>
				<c:when test="${enterprise.type eq '4' }"><a href="enter/index">政府机构</a></c:when>
			</c:choose>
			&gt; <span class="current">${enterprise.name }</span>
		</div>
		<div class="banner">${enterprise.name }</div>
		<ul class="company-nav">
			<li class="active"><a
				href="enter/queryEnter?eid=${enterprise.id }">首页</a></li>
			<li><a href="enter/orgservices?eid=${enterprise.id }">服务列表</a></li>
			<li><a href="enter/detailEnter?eid=${enterprise.id }">公司信息</a></li>
		</ul>
		<div class="column-company">
			<div class="column-company-l">
				<div class="company-info">
					<h3 class="panel-head">服务机构名片</h3>
					<div class="panel-body">
						<div class="top">
							<h4>${enterprise.name }</h4>
							<div class="row">
								<div class="is-cer">
									<!-- 1普通企业；2认证企业;3服务机构；4政府机构；  -->
									<c:choose>
										<c:when test="${enterprise.type eq '1' }">普通企业</c:when>
										<c:when test="${enterprise.type eq '2' }">认证企业</c:when>
										<c:when test="${enterprise.type eq '3' }">服务机构</c:when>
										<c:when test="${enterprise.type eq '4' }">政府机构</c:when>
									</c:choose>
								</div>
<!-- 								<div class="online-consultation"><a href="#">在线咨询</a></div> -->
							</div>
							<div class="clearer"></div>
						</div>
						<ul class="company-meta">
							<li><span class="meta-tit">经营模式：</span> <!-- businessPattern -->
								<!-- 1生产型；2贸易型；3服务性；4生产性、贸易型；5贸易型、服务型；6生产性、贸易型、服务性； --> <span
								class="meta-info">
								<c:choose>
			            		<c:when test="${enterprise.businessPattern eq '1' }">生产型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '2' }">贸易型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '3' }">服务型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '4' }">生产型、贸易型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '5' }">贸易型、服务型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '6' }">生产型、贸易型、服务型</c:when>
				            </c:choose> 
							</span></li>
							<li><span class="meta-tit">电&nbsp;&nbsp;&nbsp;&nbsp;话：</span><span
								class="meta-info">${enterprise.tel }</span></li>
							<li><span class="meta-tit">联&nbsp;系&nbsp;人：</span><span
								class="meta-info">${enterprise.linkman }</span></li>
							<li><span class="meta-tit">地&nbsp;&nbsp;&nbsp;&nbsp;址：</span><span
								class="meta-info">${enterprise.address}</span></li>
						</ul>
 						<div class="follow" ><a href="javascript:void(0);" ></a></div> 
					</div>
				</div>
				<div class="group-panel">
					<h3 class="panel-head">服务分组</h3>
					<div class="panel-body">
						<ul class="group-list">
							<c:forEach var="group" items="${serviceGroup}">
								<li><a href="enter/orgservices?eid=${enterprise.id }&categoryId=${group.id}">${group.categoryName }</a><span>（${group.serviceNum
										}）</span></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="search-panel">
					<h3 class="panel-head">服务搜索</h3>
					<div class="panel-body">
						<form action="enter/orgservices" method="get">
							<table class="search-table">
								<tr>
									<td class="t1">服务名：</td>
									<td><input type="hidden" name="eid"
										value="${enterprise.id }"> <input type="hidden"
										name="view" value="${view }"> <input
										class="input-large" name="serviceName"></td>
								</tr>
								<tr>
									<td class="t1">价格：</td>
									<td><input class="input-small" name="beignPrice">---<input
										class="input-small" name="endPrice"></td>
								</tr>
								<tr>
									<td class="t1">&nbsp;</td>
									<td><button class="search-btn">搜索</button></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
			<div class="column-company-r">
				<div class="index-intro">
					<p class="s1">
						所属窗口：
						<c:choose>
							<c:when test="${enterprise.industryType eq '1' }">电子装备</c:when>
							<c:when test="${enterprise.industryType eq '2' }">服装</c:when>
							<c:when test="${enterprise.industryType eq '3' }">港澳资源</c:when>
							<c:when test="${enterprise.industryType eq '4' }">工业设计</c:when>
							<c:when test="${enterprise.industryType eq '5' }">机械</c:when>
							<c:when test="${enterprise.industryType eq '6' }">家具 </c:when>
							<c:when test="${enterprise.industryType eq '7' }">LED</c:when>
							<c:when test="${enterprise.industryType eq '8' }">软件</c:when>
							<c:when test="${enterprise.industryType eq '9' }">物流</c:when>
							<c:when test="${enterprise.industryType eq '10' }">物联网</c:when>
							<c:when test="${enterprise.industryType eq '11' }">新材料</c:when>
							<c:when test="${enterprise.industryType eq '12' }">医疗器械</c:when>
							<c:when test="${enterprise.industryType eq '13' }">钟表</c:when>
							<c:when test="${enterprise.industryType eq '14' }">珠宝</c:when>
							<c:when test="${enterprise.industryType eq '15' }">其他</c:when>
							<c:when test="${enterprise.industryType eq '16' }">枢纽平台</c:when>
						</c:choose>
					<p>
					<p class="s1">
						企业性质：
						<c:choose>
							<c:when test="${enterprise.enterpriseProperty eq '1' }">企业</c:when>
							<c:when test="${enterprise.enterpriseProperty eq '2' }">事业单位</c:when>
							<c:when test="${enterprise.enterpriseProperty eq '3' }">社会团体</c:when>
							<c:when test="${enterprise.enterpriseProperty eq '4' }">个体工商户</c:when>
							<c:when test="${enterprise.enterpriseProperty eq '5' }">民办非企业</c:when>
							<c:when test="${enterprise.enterpriseProperty eq '9' }">其他</c:when>
						</c:choose>
					</p>
					<p class="s1">主营产品或服务：${enterprise.mainRemark}</p>
					<p class="s1">
						经营模式：
							<c:choose>
			            		<c:when test="${enterprise.businessPattern eq '1' }">生产型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '2' }">贸易型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '3' }">服务型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '4' }">生产型、贸易型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '5' }">贸易型、服务型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '6' }">生产型、贸易型、服务型</c:when>
				            </c:choose> 
					</p>
					<p class="s1">企业简介：</p>
					<p class="s2">${fn:substring(my:replaceHTML(enterprise.profile),
						0, 100) }...</p>
					<p class="s3">
						<a href="enter/detailEnter?eid=${enterprise.id }">查看更多></a>
					</p>
				</div>
				<div class="index-service">
					<div class="index-service-head">
						<h3 class="title">服务列表</h3>
						<span class="more"><a href="enter/orgservices?eid=${enterprise.id }">更多></a></span>
					</div>
						<input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${enterprise.id }"/>
					<ul class="clearfix list-show-block">
						<c:forEach items="${serviceList}" var="item">
							<li>
								<div class="info">
									<c:if test="${enterprise.photo.contains('http')}">
										<a href="service/detail?id=${item.id }&op=enter"
										target="_blank" class="a-img"><img alt=""
										src="${item.picture}" onerror="this.src='resources/images/service/default_service_pic.gif'"></a>
									</c:if>
									<c:if test="${!enterprise.photo.contains('http')}">
										<a href="service/detail?id=${item.id }&op=enter"
										target="_blank" class="a-img"><img alt=""
										src="upload/${item.picture}" onerror="this.src='resources/images/service/default_service_pic.gif'"></a>
									</c:if>
									<h3 class="ov-h">
										<a href="service/detail?id=${item.id }&op=enter"
											target="_blank">${item.serviceName }</a>
									</h3>
									<div class="col">
										<span class="price"><i class="j">服务费用：</i> 
											<%-- <em><i>¥&nbsp;</i>${item.costPrice}</em> --%>
											<c:choose>
								        		<c:when test="${item.costPrice == null or item.costPrice == 0}"><span class="meta-price">面议</span></c:when>
									        	<c:otherwise>
									        		<span class="meta-yuan">￥</span><span class="meta-price">${item.costPrice }</span>
									        	</c:otherwise>
								        	</c:choose> 
										</span> 
										<a class="apply" href="service/detail?id=${item.id }&op=enter"
											target="_blank">查看详情</a>
									</div>
								</div>
								<div class="refe">
									<div>评价：</div>
									<div>
										<label for="">已申请：</label><span class="click">${item.serviceNum
											}</span>&nbsp;次
									</div>
									<div>
										<label for="">发布时间：</label><span class="pubdate">${registerTime
											}</span>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
					<!--/分页放下面-->
					<div class="plist">
						<pg:pager scope="request" maxItems="9" maxIndexPages="20"
							index="center" maxPageItems="9" url="enter/queryEnter"
							items="${total }" export="currentPageNumber=pageNumber">
							<pg:param name="eid" value="${enterprise.id}" />
							<pg:first>
								<a href="${pageUrl}">&#8249;&#8249;</a>
							</pg:first>
							<c:out value="${pageUrl}"></c:out>
							<pg:prev>
								<a href="${pageUrl}">&#8249;</a>
							</pg:prev>
							<pg:page>
							</pg:page>
							<pg:pages>
								<c:choose>
									<c:when test="${pageNumber eq currentPageNumber }">
										<a class="on" href="javascript:;">${pageNumber }</a>
									</c:when>
									<c:otherwise>
										<a href="${pageUrl }">${pageNumber}</a>
									</c:otherwise>
								</c:choose>
							</pg:pages>
							<pg:next>
								<a href="${pageUrl}">&#8250;</a>
							</pg:next>
							<pg:last>
								<a href="${pageUrl}">&#8250;&#8250;</a>
							</pg:last>
						</pg:pager>
					</div>
				</div>
			</div>
			<div class="clearer"></div>
		</div>
	</div>
	<jsp:include page="../layout/footer.jsp" flush="true" />
	<script type="text/javascript" src="resources/js/main/company.js"></script>
</body>
</html>