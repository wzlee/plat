<%@page import="com.eaglec.plat.utils.StrUtils"%>
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
<title>资助一键通-深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/main/common.css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/mall.css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/policy.css" />
</head>
<body>
<jsp:include page="../layout/head.jsp" flush="true" />
	<div class="wrap mt2 clearfix">
		<div class="column-l">
			<div class="policy-side">
				<div class="title">
					<h2><a href="policy/index">资助一键通<a></h2>
				</div>
				<dl  id="policy-nav">
				<c:forEach var="policyCItem" items="${cList }">
					<c:choose>
						<c:when test="${policyCItem.id == policyCategory.id }">
							<dt>
								<h3 class="current"><a href="policy/list?policyId=${policyCItem.id }">${policyCItem.text }</a></h3>
							</c:when>
						<c:otherwise>
							<dt>
							<h3><a href="policy/list?policyId=${policyCItem.id }">${policyCItem.text }</a></h3>
						</c:otherwise>
					</c:choose>
					</dt>
				</c:forEach>
			    </dl>
			</div>
		</div>
		<div class="recommend-block" >
			<div class="search-black">
				<h2>${policyCategory.text }</h2>
				<form class="p-search-form" name="" action="policy/list" method="post">
				<input style="display: none;" name="policyId" value="${policyCategory.id }"/>
					<label for="">关键词：</label><input class="key-text" type="text" name="policyName" placeholder="输入政策相关关键词" ></input>
					<input type="submit" value="搜索" class="search-go" />
				</form>		
			</div>
			<div class="policy-list">
				<!-- 遍历二级类别 -->
				<c:forEach items="${reportingViewList }" var="item">
					<c:if test="${item.infoList.size()>0 }">
					<table class="policy-table" width="100%">
						<!-- 二级类别 -->
						<caption>${item.policyCategory.text }</caption>
						<c:forEach items="${item.infoList }" var="infoItem">
						<c:if test="${infoItem.financialReportings.size()>0 }">
							<tr class="sub-tr">
								<td colspan="2">${infoItem.policyCategory.text }</td>
							</tr>
							<c:forEach items="${infoItem.financialReportings }"
								var="reporting">
							<tr>
									<td class="one"><a href="policy/detail?id=${reporting.id }">${reporting.title }</a>
									
									</td>
									<td class="four"><a href="policy/reportingInfo?id=${reporting.id }" class="q-link">资助导航</a> <a href=""
										class="q-link last">立即申请</a></td>
							</tr>
							</c:forEach>
						</c:if>
						</c:forEach>
					</table>
					</c:if>
				</c:forEach>
			</div>
			<!-- 插入分页 -->
			<c:if test="${policyList.size()>1}">
			<div class="plist">
				<pg:pager scope="request" maxItems="9" maxIndexPages="10"
					index="center" maxPageItems="25" url="policy/list"
					items="${total}" export="currentPageNumber=pageNumber">
					<pg:param name="policyId" value="${policyCategory.id }" />
					<pg:param name="policyName" value="${policyName}" />
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
	</div>
<jsp:include page="../layout/footer.jsp" flush="true" />
</body>
</html>