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
<title>政策法规-深圳市中小企业公共服务平台</title>
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
					<h2><a href="policy/index">政策法规<a></h2>
				</div>
				<dl  id="policy-nav" class="zczy">
				<c:forEach var="policyCItem" items="${cList }">
					<c:choose>
						<c:when test="${policyCItem.id == policyCategory.id }">
							<dt class="current">
						</c:when>
						<c:otherwise >
							<dt>
						</c:otherwise>
						</c:choose>
						<h3>
							<a href="policy/list?policyId=${policyCItem.id }">${policyCItem.text }</a>
						</h3>
						</dt>
					<dd>
					<ol>
						<c:forEach var="policyChildrenItem" items="${policyCItem.children }">
						<c:if test="${policyCategory.id ==policyChildrenItem.id  }">
							<li class="current">
						</c:if>
						<c:if test="${policyCategory.id !=policyChildrenItem.id  }">
							<li>
						</c:if>
							<a href="policy/list?policyId=${policyChildrenItem.id }">>&nbsp;${policyChildrenItem.text }</a>
							</li>
						</c:forEach>
					</ol>
					</dd>
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
					<ul class="ul-list">
						<c:forEach items="${policyList }" var="item" varStatus="status" begin="0">
							<li><span class="fr">${item.time }</span><a href="policy/detail?id=${item.id }">${item.title }
							<!-- 【<c:if test="${item.fileType == 'video' }">视频</c:if>
							<c:if test="${item.fileType == 'image' }">图文</c:if>】 -->
							</a></li>
							<c:if test="${(status.index+1)%5==0 }">
								<li class="stuff">&nbsp;</li>
							</c:if>
						</c:forEach>
					</ul>					
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
<script type="text/javascript">
	$(function(){
		//边栏导航 
		 $('#policy-nav').find('dt.current').next().show();
		$('#policy-nav ol').find('li.current').each(function(){
			$(this).parent().parent().show();
		})
	})
</script>
</body>
</html>