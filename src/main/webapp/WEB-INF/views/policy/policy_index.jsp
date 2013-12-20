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
					<h2>政策法规</h2>
				</div>
				<dl  id="policy-nav">
				<c:forEach var="policyCItem" items="${viewList }">
					<dt>
						<h3><a href="policy/list?policyId=${policyCItem.policyCategory.id }">${policyCItem.policyCategory.text }</a></h3>
					</dt>
				</c:forEach>
			    </dl>
			</div>
		</div>
		<div class="recommend-block">
			<div class="search-black">
				<form class="p-search-form" name="" action="policy/index" method="post">
					<label for="">关键词：</label><input class="key-text" type="text" name="name" placeholder="输入政策相关关键词" value="${param.name }"></input>
					<input type="submit" value="搜索" class="search-go" />
				</form>		
			</div>
				<c:forEach var="policyCItem" items="${viewList }">
					
					<div class="policy-list">
						<div class="title">
						<a class="fr" href="policy/list?policyId=${policyCItem.policyCategory.id }">更多>></a>
							<h2 >${policyCItem.policyCategory.text }</h2>
							
						</div>
				
							<ul class="ul-list">
								<c:forEach items="${policyCItem.policyList }" var="item">
									<li><span class="fr">${item.time }</span><a href="policy/detail?id=${item.id }">${item.title }
									<!--  【<c:if test="${item.fileType == 'video' }">视频</c:if>
									<c:if test="${item.fileType == 'image' }">图文</c:if>】-->
								</a></li>
								</c:forEach>
							</ul>
					</div>
				</c:forEach>
			
			</div>
		</div>
<jsp:include page="../layout/footer.jsp" flush="true" />
</body>
</html>