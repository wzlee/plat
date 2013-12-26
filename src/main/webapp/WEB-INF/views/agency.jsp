<%@page import="com.eaglec.plat.domain.base.Category"%>
<%@page import="com.eaglec.plat.domain.base.Enterprise"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	/**
	 * 服务机构所属行业
	 * */
	Map<String, String> industry  = new HashMap<String, String>();
	industry.put("1", "电子装备");
	industry.put("2", "服装");
	industry.put("3", "港澳资源");
	industry.put("4", "工业设计");
	industry.put("5", "机械");
	industry.put("6", "家具");
	industry.put("7", "LED");
	industry.put("8", "软件");
	industry.put("9", "物流");
	industry.put("10", "物联网");
	industry.put("11", "新材料");
	industry.put("12", "医疗器械");
	industry.put("13", "钟表");
	industry.put("14", "珠宝");
	industry.put("15", "其他");
	industry.put("16", "枢纽平台");
	pageContext.setAttribute("industry", industry);
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<title>服务机构-深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="resources/css/main/organization.css" />
</head>

<body>
<jsp:include page="layout/head.jsp" flush="true"/>
<div class="wrap">
<div class="organization-cate">
			<div class="cate-guide">全部机构分类</div>
			<ul class="organization-cate-list">
				<c:forEach items="${categorysLev1}" var="category">
					<li><a href="enter/list?cid=${category.id}">${category.text}</a></li>
				</c:forEach>
			</ul>
			<ul class="organization-cate-sub" style="display:none">
				<c:forEach items="${categorysLev2}" var="categorys" varStatus="status">
					<li class="s${status.count}">
						<c:forEach items="${categorys}" var="category">
							<a href="enter/list?cid=${category.id}" target="_blank">${category.text}</a> |
						</c:forEach>
					</li>
				</c:forEach>
			</ul>
		</div>
		<!-- /organization-cate -->
	<div class="crumb-nav"><span class="current"><strong><a href="/">首页</a></strong>&gt;服务机构</span></div>
	<div class="organization-container">
		<div class="slider">
			<ul class="special-list">
				<c:forEach items="${modules}" var="module">
					<li><a href="enter/queryEnter?eid=${module.mindex}" target="_blank">
						<c:if test="${module.micon == 'enterprise_logo.jpg' }">
							<img class="detailimg" src="resources/images/ucenter/enterprise_logo.jpg" width="188" height="146" onerror="this.src='resources/images/service/default_service_pic.gif'"/></a>
						</c:if>
						<c:if test="${module.micon != 'enterprise_logo.jpg' }">
							<c:if test="${module.micon.contains('http')}">
								<img class="detailimg" src="${module.micon}" width="188" height="146" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
							</c:if>
							<c:if test="${!module.micon.contains('http')}">
								<img class="detailimg" src="upload/${module.micon}" width="188" height="146" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
							</c:if>
							</a>
						</c:if>
					</li>
				</c:forEach>
				<!-- <li><a href="#"><img src="resources/images/main/or_1.jpg" width="188" height="146" /></a></li>
				<li><a href="#"><img src="resources/images/main/or_2.jpg" width="188" height="146" /></a></li>
				<li><a href="#"><img src="resources/images/main/or_3.jpg" width="188" height="146" /></a></li>
				<li><a href="#"><img src="resources/images/main/or_4.jpg" width="188" height="146" /></a></li> -->
			</ul>
			<div class="special-item">
				<c:forEach items="${modules}" var="module2" varStatus="status">
					<c:set var="enterp" scope="page" value="${topEnterp[module2.mindex]}"></c:set>
					<div class="item-box s${status.count}">
						<div class="item-box-head">
							<a href="enter/queryEnter?eid=${enterp.id}" target="_blank" class="view-home">机构详情</a>
							<div class="item-box-top">
								<h3 class="name">${enterp.name}</h3>
								<div class="row">
						            <div class="is-cer">服务机构</div>
<!-- 						            <div class="online-consultation"><a href="javascript:void(0);">在线咨询</a></div> -->
								</div>
							</div>
							<div class="company-info">
								<div class="trade"><i>所属窗口：</i>${industry[enterp.industryType.toString()]}</div>
								<div class="apply"><i>提供服务：</i>
								<%
				        			/* 提供的服务名称 */
									Enterprise enterprise = (Enterprise)pageContext.getAttribute("enterp"); 
						        	List<Category> list = enterprise.getMyServices();
									int size = list.size();
									StringBuilder sb = new StringBuilder();
									if (size > 0) {
										for (int i = 0; i < size - 1; i++) {
											sb.append(list.get(i).getText()).append(",");
										}
										sb.append(list.get(size - 1).getText());
									} else {
										sb.append("暂无");
									}
									pageContext.setAttribute("myServices", sb.toString());
			        			%>
								${fn:substring(myServices,0,30)}${fn:length(myServices) > 30 ? '...':''}</div>
								<div class="address"><i>地址：</i>${enterp.address}</div>
							</div>
						</div>
						<ul class="show-services">
							<c:forEach items="${serviceMap[enterp.id.toString()]}" var="service">
								<li>
									<div class="photo"><a href="service/detail?id=${service.id}&op=enter" target="_blank">
										<c:if test="${service.picture.contains('http')}">
											<img src="${service.picture}" width="130" height="100" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
										</c:if>
										<c:if test="${!service.picture.contains('http')}">
											<img src="upload/${service.picture}" width="130" height="100" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
										</c:if>
										</a></div>
									<div class="title"><a href="service/detail?id=${service.id}&op=enter" target="_blank">${service.serviceName}</a></div>
								</li>
							</c:forEach>
							<!-- <li>
								<div class="photo"><a href="#"><img src="resources/images/main/show_pic_1.jpg" width="130" height="100" /></a></div>
								<div class="title"><a href="#">服务加工</a></div>
							</li>
						 	 -->
						</ul>
					</div>
				</c:forEach>
				<!-- <div class="item-box s2">
					<div class="item-box-head">
						<a href="#" class="view-home">进入机构主页</a>
						<div class="item-box-top">
							<h3 class="name">阿里巴巴科技有限科技有限公司</h3>
							<div class="row">
					            <div class="is-cer">认证企业</div>
					            <div class="online-consultation"><a href="#">在线咨询</a></div>
							</div>
						</div>
						<div class="company-info">
							<div class="trade"><i>所属行业：</i>服装</div>
							<div class="apply"><i>提供服务：</i> 服装加工、服装加工、服装加工、服装加工</div>
							<div class="address"><i>地址：</i>深圳市福田区皇岗村</div>
						</div>
					</div>
					<ul class="show-services">
						<li>
							<div class="photo"><a href="#"><img src="resources/images/main/show_pic_1.jpg" width="130" height="100" /></a></div>
							<div class="title"><a href="#">服务加工名字很长很长很长</a></div>
						</li>
						<li>
							<div class="photo"><a href="#"><img src="resources/images/main/show_pic_1.jpg" width="130" height="100" /></a></div>
							<div class="title"><a href="#">服务加工</a></div>
						</li>
						<li>
							<div class="photo"><a href="#"><img src="resources/images/main/show_pic_1.jpg" width="130" height="100" /></a></div>
							<div class="title"><a href="#">服务加工</a></div>
						</li>
						<li>
							<div class="photo"><a href="#"><img src="resources/images/main/show_pic_1.jpg" width="130" height="100" /></a></div>
							<div class="title"><a href="#">服务加工</a></div>
						</li>
						<li>
							<div class="photo"><a href="#"><img src="resources/images/main/show_pic_1.jpg" width="130" height="100" /></a></div>
							<div class="title"><a href="#">服务加工</a></div>
						</li>
						<li>
							<div class="photo"><a href="#"><img src="resources/images/main/show_pic_1.jpg" width="130" height="100" /></a></div>
							<div class="title"><a href="#">服务加工</a></div>
						</li>
					</ul>
				</div>
				 -->
			</div>
		</div>
		<!-- /slider -->
		
		<c:forEach items="${modulesMap}" var="entry">
			<div class="common-column">
				<h3>${entry.key}</h3>
				<ul class="column-list">
					<c:forEach items="${entry.value}" var="module">
						<li>
							<div class="pic">
								<a href="enter/queryEnter?eid=${module.mindex}" target="_blank">
								<c:if test="${module.micon == 'enterprise_logo.jpg' }">
									<img src="resources/images/ucenter/enterprise_logo.jpg" width="188" height="146" onerror="this.src='resources/images/service/default_service_pic.gif'"/></a>
								</c:if>
								<c:if test="${module.micon != 'enterprise_logo.jpg' }">
									<c:if test="${module.micon.contains('http')}">
										<img width="188" height="146" src="${module.micon}" onerror="this.src='resources/images/service/default_service_pic.gif'">
									</c:if>
									<c:if test="${!module.micon.contains('http')}">
										<img width="188" height="146" src="upload/${module.micon}" onerror="this.src='resources/images/service/default_service_pic.gif'">
									</c:if>
									</a>
								</c:if>
							</div>
							<h4 class="summary"><a href="enter/queryEnter?eid=${module.mindex}" target="_blank">${module.mname}</a></h4>
							<div class="row">
					            <div class="is-cer">服务机构</div>
<!-- 					            <div class="online-consultation"><a href="#">在线咨询</a></div> -->
		        			</div>
		        			<div class="row"><i class="label">所属窗口：</i>${industry[module.industryType.toString()]}</div>
		        			<!-- <div class="row"><i class="label">提供服务：</i>服装加工</div> -->
						</li>
					</c:forEach>
					<!-- <li>
						<div class="pic"><a href="#"><img width="188" height="146" src="resources/images/main/or_1.jpg"></a></div>
						<h4 class="summary"><a href="#">XXXX科技有限公司</a></h4>
						<div class="row">
				            <div class="is-cer">认证企业</div>
				            <div class="online-consultation"><a href="#">在线咨询</a></div>
	        			</div>
	        			<div class="row"><i class="label">所属行业：</i>服装</div>
	        			<div class="row"><i class="label">提供服务：</i>服装加工</div>
					</li>
					<li>
						<div class="pic"><a href="#"><img width="188" height="146" src="resources/images/main/or_1.jpg"></a></div>
						<h4 class="summary"><a href="#">XXXX科技有限公司</a></h4>
						<div class="row">
				            <div class="is-cer">认证企业</div>
				            <div class="online-consultation"><a href="#">在线咨询</a></div>
	        			</div>
	        			<div class="row"><i class="label">所属行业：</i>服装</div>
	        			<div class="row"><i class="label">提供服务：</i>服装加工</div>
					</li>
					<li>
						<div class="pic"><a href="#"><img width="188" height="146" src="resources/images/main/or_1.jpg"></a></div>
						<h4 class="summary"><a href="#">XXXX科技有限公司</a></h4>
						<div class="row">
				            <div class="is-cer">认证企业</div>
				            <div class="online-consultation"><a href="#">在线咨询</a></div>
	        			</div>
	        			<div class="row"><i class="label">所属行业：</i>服装</div>
	        			<div class="row"><i class="label">提供服务：</i>服装加工</div>
					</li> -->
				</ul>
			</div>
		</c:forEach>
		<!-- <div class="common-column">
			<h3>信息服务</h3>
			<ul class="column-list">
				<li>
					<div class="pic"><a href="#"><img width="188" height="146" src="resources/images/main/or_1.jpg"></a></div>
					<h4 class="summary"><a href="#">XXXX科技有限公司</a></h4>
					<div class="row">
			            <div class="is-cer">认证企业</div>
			            <div class="online-consultation"><a href="#">在线咨询</a></div>
        			</div>
        			<div class="row"><i class="label">所属行业：</i>服装</div>
        			<div class="row"><i class="label">提供服务：</i>服装加工</div>
				</li>
				<li>
					<div class="pic"><a href="#"><img width="188" height="146" src="resources/images/main/or_1.jpg"></a></div>
					<h4 class="summary"><a href="#">XXXX科技有限公司</a></h4>
					<div class="row">
			            <div class="is-cer">认证企业</div>
			            <div class="online-consultation"><a href="#">在线咨询</a></div>
        			</div>
        			<div class="row"><i class="label">所属行业：</i>服装</div>
        			<div class="row"><i class="label">提供服务：</i>服装加工</div>
				</li>
				<li>
					<div class="pic"><a href="#"><img width="188" height="146" src="resources/images/main/or_1.jpg"></a></div>
					<h4 class="summary"><a href="#">XXXX科技有限公司</a></h4>
					<div class="row">
			            <div class="is-cer">认证企业</div>
			            <div class="online-consultation"><a href="#">在线咨询</a></div>
        			</div>
        			<div class="row"><i class="label">所属行业：</i>服装</div>
        			<div class="row"><i class="label">提供服务：</i>服装加工</div>
				</li>
				<li>
					<div class="pic"><a href="#"><img width="188" height="146" src="resources/images/main/or_1.jpg"></a></div>
					<h4 class="summary"><a href="#">XXXX科技有限公司</a></h4>
					<div class="row">
			            <div class="is-cer">认证企业</div>
			            <div class="online-consultation"><a href="#">在线咨询</a></div>
        			</div>
        			<div class="row"><i class="label">所属行业：</i>服装</div>
        			<div class="row"><i class="label">提供服务：</i>服装加工</div>
				</li>
			</ul>
		</div> -->
		<!-- /common-column -->
	</div>
	<!-- /organization-container -->
</div>
<jsp:include page="layout/footer.jsp" flush="true"/>
<script type="text/javascript" src="resources/js/main/organization.js"></script>
</body>
</html>