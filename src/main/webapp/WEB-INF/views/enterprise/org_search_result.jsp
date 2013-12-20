<%@page import="com.eaglec.plat.domain.base.Enterprise"%>
<%@page import="com.eaglec.plat.domain.base.Category"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.eaglec.plat.utils.StrUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
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
	<title>深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="resources/css/main/search.css" />
	<link type="text/css" rel="stylesheet" href="resources/css/main/organization.css" />
</head>

<body>
	<jsp:include page="../layout/head.jsp" flush="true"/>
<div class="wrap">
	<div class="crumb-nav posi">全部结果 &gt; <span class="current">${name}</span></div>
	<div class="organization-container">
		<!-- /organization-cate -->
 <div class="sortbar mt-110" style="margin-top: 20px;">
    <ul class="nav-pills search-drop">
      <li>
      		<select class="select-drop" value="${industryType}">
      			<option value="">所属窗口</option>
      			<option value="16">枢纽平台</option>
      			<option value="1">电子装备</option>
      			<option value="2">服装</option>
      			<option value="3">港澳资源</option>
      			<option value="4">工业设计</option>
      			<option value="5">机械</option>
      			<option value="6">家具</option>
      			<option value="7">LED</option>
      			<option value="8">软件</option>
      			<option value="9">物流</option>
      			<option value="10">物联网</option>
      			<option value="11">新材料</option>
      			<option value="12">医疗器械</option>
      			<option value="13">钟表</option>
      			<option value="13">珠宝</option>
      			<option value="15">其他</option>
      		</select>
      </li>
      <li>
      		<select class="select-drop" value="${businessPattern}">
      			<option value="">经营模式</option>
      			<option value="1">生产型</option>
      			<option value="2">贸易型</option>
      			<option value="3">服务性</option>
      			<option value="4">生产性、贸易型</option>
      			<option value="5">贸易型、服务型</option>
      			<option value="6">生产性、贸易型、服务性</option>
      		</select>      
      </li>
      <li class="result-search">
        <form action="enter/search" method="post" style="display: none;">
	        <input type="hidden" name="industryType" >
	        <input type="hidden" name="businessPattern" >
	        <input type="text" name="name" value="${name}" />
	        <input type="button" class="search-submit" name="" value="搜索"/>
        </form>
      </li>
    </ul>
   
  </div>		
		<div class="channel-panel">
		  	<div class="search-items">
		  		<c:forEach items="${enterprises}" var="enterprise" varStatus="status">
				    <div class="list-view">
				      <div class="col pic">
				      	<a href="enter/queryEnter?eid=${enterprise.id}" target="_blank">
				      		<c:if test="${enterprise.photo == 'enterprise_logo.jpg' }">
								<img width="188" height="146" src="resources/images/ucenter/enterprise_logo.jpg" onerror="this.src='resources/images/service/default_service_pic.gif'"> 
							</c:if>
							<c:if test="${enterprise.photo != 'enterprise_logo.jpg' }">
								<c:if test="${enterprise.photo.contains('http')}">
									<img width="188" height="146" src="${enterprise.photo}" onerror="this.src='resources/images/service/default_service_pic.gif'"> 
								</c:if>
								<c:if test="${!enterprise.photo.contains('http')}">
									<img width="188" height="146" src="upload/${enterprise.photo}" onerror="this.src='resources/images/service/default_service_pic.gif'"> 
								</c:if>								
							</c:if>
				      		<%-- <img class="lazy" src="resources/images/service-loading.gif" 
									data-original="upload/${enterprise.photo}" 
									width="168" height="144" alt="${enterprise.name}" 
									title="${enterprise.name}" onerror="nofind();"/> --%>
				      	</a>
				      </div>
				      <div class="col company com-s">
				        <h3 class="name ov-h"><a href="enter/queryEnter?eid=${enterprise.id}" target="_blank">${enterprise.name}</a></h3>
				        <div class="extend">
				          <div class="is-cer">服务机构</div>
<!-- 				          <div class="online-consultation"><a href="">在线咨询</a></div> -->
				        </div>
				        <%
				        	/* 截取服务介绍字符串,并去掉html标签 */
							Enterprise enterprise = (Enterprise)pageContext.getAttribute("enterprise");
				        	String profile = enterprise.getProfile();
							String temp = null;
				        	if (profile == null) {
				        		temp = "暂无";
				        	} else {
				        		temp = StrUtils.replaceHTML(profile, 0, 30);
				        	}
							enterprise.setProfile(temp);
						%>
				        <p>  ${enterprise.profile}</p>
				      </div>
				      <div class="col introduction">
				         <ul>
				         	<li><span class="label">所属窗口：</span>${industry[enterprise.industryType.toString()]}</li>
				         	<li>
				         		<span class="label">提供服务：</span>
				         		<%
				        			/* 提供的服务名称 */
									/* Enterprise enterprise = (Enterprise)pageContext.getAttribute("enterprise"); */
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
				         		<div class="serv">${myServices}</div>
				         	</li>
				         	<li><span class="label">联&nbsp;系&nbsp;人：</span>${enterprise.linkman}</li>
				         	<li><span class="label">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</span><div class="serv">${enterprise.address}</div></li>
				         </ul>
				      </div>
				       <div class="col end apply-service"><a href="enter/queryEnter?eid=${enterprise.id}" target="_blank">机构详情</a></div>
				    </div>
		  		</c:forEach>
		    </div>			
		</div>
		<!-- /common-column -->
	</div>
	<!-- 分页  -->
	  <div class="plist">
	  	<c:if test="${total>0}">
			<pg:pager scope="request" maxIndexPages="10" index="center" maxPageItems="20"
				 url="enter/search" items="${total}" export="currentPageNumber=pageNumber">
				<pg:param name="encodedName"  value="${encodedName}" />
		    	<pg:first>
		    		<c:if test="${pageNumber != currentPageNumber }">
		    			<a href="${pageUrl}">&#8249;&#8249;</a>
		    		</c:if>
		    	</pg:first>
		        <pg:prev><a href="${pageUrl}">&#8249;</a></pg:prev>
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
		        <pg:next><a href="${pageUrl}">&#8250;</a></pg:next>
		        <pg:last>
		            <c:if test="${(pageNumber != currentPageNumber) && (pageNumber != 0) }">
		            	<a href="${pageUrl}">&#8250;&#8250;</a>
		            </c:if>
		        </pg:last>
			</pg:pager> 
			 </c:if>
   		 <c:if test="${total<=0}">
  	 	没有找到任何跟"关键词"相关的内容。您可以尝试使用其他关键词进行搜索，或返回 首页。
   		</c:if>	
	   </div>
	<!-- /organization-container -->
</div>
<!-- /wrap -->
	<jsp:include page="../layout/footer.jsp" flush="true"/>
	<!-- <script type="text/javascript" src="resources/js/main/mall_list.js"></script>
	<script type="text/javascript" src="resources/js/main/search_service.js"></script> -->
	<script src="jsLib/jquery/jquery.lazyload.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="resources/js/main/organization_list.js"></script>
</body>
</html>