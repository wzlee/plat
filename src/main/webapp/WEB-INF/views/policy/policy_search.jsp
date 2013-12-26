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
<link type="text/css" rel="stylesheet" href="resources/css/main/policy.css" />
</head>
<body>
<jsp:include page="../layout/head.jsp" flush="true" />
	<div class="wrap mt2 clearfix">
		<div class="crumb-nav">
			<a href="/">首页 </a>&gt;<span class="current">政策资金</span>
		</div>
		<div class="list-policy-search">
			<h2><a href="">搜索结果标题</a></h2>
			<p>文章简介小型微型企业在增加就业、促进经济增长、科技创新与社会和谐稳定等方面具有不可替代的作用，对国民经济和社会发展具有重要的战略意义。党中央、国务院高度重视小型微型企业的发展，出台了一系列财税金融扶持政策，取得了积极成效。但受国内外复杂多变的经济形势影响，当前，小型微型企业经营压力大、成本上升、融资困难和税费偏重等问题仍很突出，必须引起高度重视。为进一步支持小型微型企业健康发展，现提出以下意见。
一、充分认识进一步支持小型微型企业健康发展的重要意义</p>
		</div>
	</div>
<jsp:include page="../layout/footer.jsp" flush="true" />
</body>
</html>