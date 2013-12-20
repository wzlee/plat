<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.eaglec.com/jsp/user/functions" prefix="my"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
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
<title>深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet"
	href="resources/css/main/search.css" />
<link type="text/css" rel="stylesheet"
	href="resources/css/mall_list.css" />
</head>

<body>
	<jsp:include page="../layout/head.jsp" flush="true" />
	<div class="wrap">
		<div class="crumb-nav posi">
			首页 &gt; 服务商城&gt;
			<c:choose>
				<c:when test="${currentCategory ==null }">
					<span class="current">${currentPCategory.text }</span>
				</c:when>
				<c:otherwise>
					${currentPCategory.text }&gt; 
					<span class="current">${currentCategory.text}</span>
				</c:otherwise>
			</c:choose>
		</div>
		<c:if test="${mallcategoryList !=null}">
			<div class="organization-cate">
				<div class="cate-guide">全部分类</div>
				<ul class="organization-cate-list">
					<c:forEach items="${mallcategoryList }" var="pmallcateitem">
						<c:choose>
							<c:when test="${currentPCategory.id == pmallcateitem.id  }">
								<li class="active" ><p>${pmallcateitem.text}</p></li>
							</c:when>
							<c:otherwise>
								<li><p>${pmallcateitem.text}</p></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
				<ul class="organization-cate-sub">
					<c:forEach items="${mallcategoryList }" var="pmallcateitem"
						varStatus="lineNum" begin="0">
						<li class="s${lineNum.index+1 }"><c:choose>
								<c:when test="${currentCategory ==null }">
									<a href="mall/mallList?mallId=${currentPCategory.id}" style="color: #606;">全部</a>
								</c:when>
								<c:otherwise>
									<a href="mall/mallList?mallId=${currentPCategory.id}">全部</a>
								</c:otherwise>
							</c:choose> <c:forEach items="${pmallcateitem.children}"
								var="childrencateitem" varStatus="num">
								<c:choose>
									<c:when test="${currentCategory.id == childrencateitem.id}">
										<a
											href="mall/mallList?mallId=${childrencateitem.id}"
											style="color: #606;">${childrencateitem.text}</a>
									</c:when>
									<c:otherwise>
										<a
											href="mall/mallList?mallId=${childrencateitem.id}">${childrencateitem.text
											}</a>
									</c:otherwise>
								</c:choose>
								<c:if test="${num.index-1 < pmallcateitem.children.size()-1 }"></c:if>|
					</c:forEach></li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<!-- /organization-cate -->
		<div class="sortbar mt-50">
			<ul class="sortpics">
				<li>
				<c:choose>
					<c:when test="${mparam.orderName=='serviceNum'}">
						<a
						href="javascript:filterService('orderType=desc&orderName=serviceNum');"
						class="active"> 申请数<span class="icon-bottom"></span>
						</a>
					</c:when>
					<c:when test="${mparam.orderName==null}">
						<a
						href="javascript:filterService('orderType=desc&orderName=serviceNum');"
						class="active"> 申请数<span class="icon-bottom"></span>
						</a>
					</c:when>
					<c:otherwise>
					<a
					href="javascript:filterService('orderType=desc&orderName=serviceNum');"
					class=""> 申请数<span class="icon-bottom"></span>
				</a>
					</c:otherwise>
				</c:choose>
				
					
				</li>
				<li><a
					href="javascript:filterService('orderType=desc&orderName=evaluateScore');"
					class="${mparam.orderName == 'evaluateScore' ? 'active' : ''}">
						评价<span class="icon-bottom"></span>
				</a></li>
				<li><c:choose>
						<c:when test="${mparam.orderType=='desc' && mparam.orderName=='registerTime'}">
							<a
								href="javascript:filterService('orderType=asc&orderName=registerTime');"
								class="${mparam.orderName == 'registerTime' ? 'active' : ''}">
								时间<span
								class="icon-bottom ${mparam.orderType == 'asc' ? 'on' : ''}"></span>
							</a>
						</c:when>
						<c:otherwise>
							<a
								href="javascript:filterService('orderType=desc&orderName=registerTime');"
								class="${mparam.orderName == 'registerTime' ? 'active' : ''}">
								时间<span
								class="icon-bottom ${mparam.orderType == 'asc' ? 'on' : ''}"></span>
							</a>
						</c:otherwise>
					</c:choose></li>
				<li><c:choose>
						<c:when test="${mparam.orderType=='desc' && mparam.orderName=='costPrice'}">
							<a
								href="javascript:filterService('orderType=asc&orderName=costPrice');"
								class="${mparam.orderName == 'costPrice' ? 'active' : ''}">
								价格<span
								class="icon-bottom ${mparam.orderType == 'asc' ? 'on' : ''}"></span>
							</a>
						</c:when>
						<c:otherwise>
							<a
								href="javascript:filterService('orderType=desc&orderName=costPrice');"
								class="${mparam.orderName == 'costPrice' ? 'active' : ''}">
								价格<span
								class="icon-bottom ${mparam.orderType == 'asc' ? 'on' : ''}"></span>
							</a>
						</c:otherwise>
					</c:choose></li>
				<li class="price-search">
					<div id="rank-priceform">
						<form action="mall/mallList" method="get">
              		<c:choose>
						<c:when test="${currentCategory == null}">
							<input type="hidden" name="mallId" value="${currentPCategory.id }">
						</c:when>
						<c:otherwise>
							<input type="hidden" name="mallId" value="${currentCategory.id }">
						</c:otherwise>
					</c:choose>
					<input type="hidden" name="serviceName" value="${mparam.serviceName}">
            		<input type="hidden" name="view" value="${view }">
               		<input onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" type="text" name="beginPrice" class="pri jiage" value="${mparam.beginPrice}" />&nbsp;—&nbsp;
               		<input  onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" type="text" name="endPrice" class="pri jiage" value="${mparam.endPrice}" />
               		<input type="submit" value="确定" class="search-submit" />
              </form>
					</div>&nbsp;
				</li>
				<li class="result-search">
					<form action="mall/mallList" method="get">
					<c:choose>
						<c:when test="${currentCategory == null}">
							<input type="hidden" name="mallId" value="${currentPCategory.id }">
						</c:when>
						<c:otherwise>
							<input type="hidden" name="mallId" value="${currentCategory.id }">
						</c:otherwise>
					</c:choose>
					<input type="hidden" name="beginPrice" value="${mparam.beginPrice}">
					<input type="hidden" name="endPrice" value="${mparam.endPrice}">
						<input type="hidden" name="view" value="${view }"> <input
							type="text" name="serviceName" id="" class="pri keywords"
							value="${mparam.serviceName }" /> <input type="submit"
							class="search-submit" name="" id="" value="搜索" />
					</form>
				</li>

			</ul>
			<ul class="show-type">
				<li class="${view=='icon' ? 'active' : '' }"><a class="s1"
					href="javascript:void(0)" title="大图显示"><span>大图</span></a></li>
				<li class="${view=='list' ? 'active' : '' }"><a class="s2"
					href="javascript:void(0)" title="列表显示"><span>列表</span></a></li>
			</ul>
		</div>
		<!-- 服务列表展示 -->
		<div class="search-items clearfix">
			<c:forEach items="${serviceList }" var="serviceitem">
			<input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${serviceitem.enterprise.id }"/>
				<div class="list-view mall-listd">
					<div class="col pic">
						<a href="service/detail?id=${serviceitem.id }&op=mall" target="_blank">
							<c:if test="${serviceitem.picture.contains('http')}">
								<img src="${serviceitem.picture}" width="168" height="144" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
							</c:if>
							<c:if test="${!serviceitem.picture.contains('http')}">
								<img src="upload/${serviceitem.picture}" width="168" height="144" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
							</c:if>
							</a>
					</div>
					<div class="col company">
						<h3 class="name">
							<a href="service/detail?id=${serviceitem.id }&op=mall" target="_blank">
						<c:choose>
							<c:when test="${serviceitem.serviceName.length()<=14 }">
								${serviceitem.serviceName}
							</c:when>
							<c:otherwise>
								${fn:substring(my:replaceHTML(serviceitem.serviceName), 0, 14) }...
							</c:otherwise>
						</c:choose>
							
							</a>
						</h3>
						<p>${fn:substring(my:replaceHTML(serviceitem.serviceProcedure),
							0, 80) }...</p>
						<div class="extend clearfix">
							<h4 class="c-name">
								<a href="">${serviceitem.enterprise.name}</a>
							</h4>
							<div class="is-cer">服务机构</div>
<!-- 							<div class="online-consultation"><a href="">在线咨询</a></div> -->
						</div>
					</div>
					<div class="col introduction">
						<ul class="clearfix">
							<li class="col-1 pr"><span class="label hd">服务费用：
								<%-- <em>${serviceitem.costPrice}</em> --%>
								<c:choose>
									<c:when test="${serviceitem.costPrice == null or serviceitem.costPrice == 0}">面议</c:when>
									<c:otherwise>
										<span class="meta-yuan">￥</span><span class="meta-price">${serviceitem.costPrice }</span>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="col-2"><span class="label hd">评&nbsp;&nbsp;&nbsp;&nbsp;价：</span>
								<span class="stars star3"></span> 
							</li>
							<li class="col-3"><span class="label">已申请：</span>${serviceitem.serviceNum}次</li>
							<li class="col-4"><span class="label">发布时间：</span>${fn:substring(my:replaceHTML(serviceitem.registerTime),
								0, 10) }</li>
						</ul>
					</div>
					<div class="col end apply-service">
							<a class="apply-btn" href="service/detail?id=${serviceitem.id }&op=mall" target="_blank">查看详情</a>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- 插入分页 -->
		<c:if test="${serviceList.size()>1}">
		<div class="plist">
			<pg:pager scope="request" maxItems="9" maxIndexPages="10"
				index="center" maxPageItems="${mparam.limit }" url="mall/mallList"
				items="${mparam.total}" export="currentPageNumber=pageNumber">
				<pg:param name="mallId" value="${mparam.mallId}" />
				<pg:param name="beginPrice" value="${mparam.beginPrice}" />
				<pg:param name="endPrice" value="${mparam.endPrice}" />
				<pg:param name="serviceName" value="${mparam.serviceName}" />
				<pg:param name="orderName" value="${mparam.orderName}" />
				<pg:param name="orderType" value="${mparam.orderType}" />
				<pg:first>
					<c:if test="${pageNumber != currentPageNumber }">
						<a href="${pageUrl}">&#8249;&#8249;</a>
					</c:if>
				</pg:first>
				<c:out value="${pageUrl}"></c:out>
				<pg:prev>
					<a href="${pageUrl}">&#8249;</a>
				</pg:prev>
				<pg:pages>
					<c:choose>
						<c:when test="${pageNumber eq currentPageNumber }">
							<a class="on" href="javascript:;" url="${pageUrl }">${pageNumber
								}</a>
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
					<c:if test="${pageNumber != currentPageNumber }">
						<a href="${pageUrl}">&#8250;&#8250;</a>
					</c:if>
				</pg:last>
			</pg:pager>
		</div>
		</c:if>
	</div>
	<!-- /wrap -->
	<jsp:include page="../layout/footer.jsp" flush="true" />
	<script type="text/javascript" src="resources/js/main/mall_list.js"></script>
	<script type="text/javascript"
		src="resources/js/main/search_service.js"></script>
</body>
</html>