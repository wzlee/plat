<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<title>服务机构-深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="resources/css/main/company.css" />
</head>

<body>
<jsp:include page="../layout/head.jsp" flush="true"/>
<div class="wrap">
	<div class="crumb-nav">首页 &gt; <c:choose>
		<c:when test="${enterprise.type eq '3' }">服务机构</c:when>
		<c:when test="${enterprise.type eq '4' }">政府机构</c:when>
	</c:choose> &gt; <span class="current">${enterprise.name }</span></div>
	<div class="banner">${enterprise.name }</div>
	<ul class="company-nav">
		<li><a href="enter/queryEnter?eid=${enterprise.id }">首页</a></li>
		<li><a href="enter/orgservices?eid=${enterprise.id }&view=icon">服务列表</a></li>
		<li class="active"><a href="enter/detailEnter?eid=${enterprise.id }">公司信息</a></li>
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
<!-- 				            <div class="online-consultation"><a href="#">在线咨询</a></div> -->
	        			</div>
	        			<div class="clearer"></div>
					</div>
					<ul class="company-meta">
			          <li><span class="meta-tit">经营模式：</span>
			          <!-- businessPattern -->
			          <!-- 1生产型；2贸易型；3服务性；4生产性、贸易型；5贸易型、服务型；6生产性、贸易型、服务性； -->
			          	<span class="meta-info">
			          		<c:choose>
			            		<c:when test="${enterprise.businessPattern eq '1' }">生产型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '2' }">贸易型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '3' }">服务型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '4' }">生产型、贸易型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '5' }">贸易型、服务型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '6' }">生产型、贸易型、服务型</c:when>
				            </c:choose> 
			          	</span>
			          </li>
			          <li><span class="meta-tit">电&nbsp;&nbsp;&nbsp;&nbsp;话：</span><span class="meta-info">${enterprise.tel }</span></li>
			          <li><span class="meta-tit">联&nbsp;系&nbsp;人：</span><span class="meta-info">${enterprise.linkman }</span></li>
			          <li><span class="meta-tit">地&nbsp;&nbsp;&nbsp;&nbsp;址：</span><span class="meta-info">${enterprise.address}</span></li>
			        </ul>
 			        <div class="follow"><a href="javascript:void(0);">关注该机构</a></div> 
				</div>
			</div>
			<div class="group-panel">
				<h3 class="panel-head">服务分组</h3>
				<div class="panel-body">
					<ul class="group-list">
					<c:forEach var="group" items="${serviceGroup}">
						<li><a href="enter/orgservices?eid=${enterprise.id }&categoryId=${group.id}">${group.categoryName }</a><span>（${group.serviceNum }）</span></li>
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
			<div class="company-intro">
				<h3 class="panel-head">公司介绍</h3>
				<div class="panel-body">
					<div class="intro-wrap">
						<div id="show-intro" class="intro-content short-show">
							<p>${enterprise.profile}</p>
						</div>
						<ul class="control-action">
							<li class="s1"><a href="javascript:void(0)">查看更多</a></li>
							<li class="s2"><a href="javascript:void(0)">收起</a></li>
						</ul>
					</div>
					<div class="cer-show"><img src="resources/images/main/cer_pic.jpg" /></div>
				</div>

			</div>
			<div class="detail-info">
				<h3 class="panel-head">详细信息</h3>
				
					<table width="100%" class="detail-info-table">
						<tr>
						<!-- 1、电子装备 2、服装 3、港澳资源 4、工业设计 5、机械 6、家具 7、LED 8、软件 9、物流10、物联网11、新材料 12、医疗器械13、钟表14、珠宝15、其他 -->
							<td>所属窗口：
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
							</td>
							<!--  1企业；2事业单位；3社会团体；4个体工商户；5民办非企业；9其他； 必填项 下拉框显示 -->
							<td>企业性质：
								<c:choose>
				            		<c:when test="${enterprise.enterpriseProperty eq '1' }">企业</c:when>
				            		<c:when test="${enterprise.enterpriseProperty eq '2' }">事业单位</c:when>
				            		<c:when test="${enterprise.enterpriseProperty eq '3' }">社会团体</c:when>
				            		<c:when test="${enterprise.enterpriseProperty eq '4' }">个体工商户</c:when>
				            		<c:when test="${enterprise.enterpriseProperty eq '5' }">民办非企业</c:when>
				            		<c:when test="${enterprise.enterpriseProperty eq '9' }">其他</c:when>
					            </c:choose>
							</td>
						</tr>
						<tr>
							<td>经营模式：
							<c:choose>
			            		<c:when test="${enterprise.businessPattern eq '1' }">生产型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '2' }">贸易型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '3' }">服务型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '4' }">生产型、贸易型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '5' }">贸易型、服务型</c:when>
			            		<c:when test="${enterprise.businessPattern eq '6' }">生产型、贸易型、服务型</c:when>
				            </c:choose> 
							</td>
							<td>组织机构号：${enterprise.icRegNumber}</td>
						</tr>
						<tr><td colspan="2">主营产品或服务：${enterprise.mainRemark}</td></tr>
						<tr><td>成立日期：${enterprise.openedTime}</td><td>员工人数：${enterprise.staffNumber}</td></tr>
						<tr>
							<td>
								年营业额(万)：<fmt:formatNumber value="${enterprise.salesRevenue}" pattern="0.0000"/> 
							</td>
							<td>经营地址：${enterprise.address}</td>
						</tr>
					</table>
			</div>
			<div class="contact-panel">
				<h3 class="panel-head">联系方式</h3>
				<div class="panel-body">
					<ul class="contact-list">
						<li>联系人：${enterprise.linkman}</li>
						<li>地   址：${enterprise.address}</li>
						<li>邮   箱：${enterprise.email}</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="clearer"></div>
	</div>
</div>
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script type="text/javascript" src="resources/js/main/company.js"></script>
</body>
</html>