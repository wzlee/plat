<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.eaglec.com/jsp/user/functions" prefix="my"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<title>公司信息-深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="resources/css/main/company.css" />
</head>

<body>
<jsp:include page="../layout/head.jsp" flush="true"/>
<div class="wrap">
 <input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${enterprise.id }"/>
  <div class="crumb-nav">
  	<a href="">首页</a>&gt;
    <a href="enter/index">服务机构 </a>&gt;  <span class="current">${enterprise.name }</span></div>
  <div class="banner">${enterprise.name }</div>
  <ul class="company-nav">
    <li><a href="enter/queryEnter?eid=${enterprise.id }">首页</a></li>
    <li class="active"><a href="enter/orgservices?eid=${enterprise.id }">服务列表</a></li>
    <li><a href="enter/detailEnter?eid=${enterprise.id }">公司信息</a></li>
  </ul>
  <div class="column-company">
    <div class="column-company-l">
      <div class="company-logo">
        <a href="">
        	<c:if test="${enterprise.photo == 'enterprise_logo.jpg' }">
				<img width="226" height="194" src="resources/images/ucenter/enterprise_logo.jpg" onerror="this.src='resources/images/service/default_service_pic.gif'"> 
			</c:if>
			<c:if test="${enterprise.photo != 'enterprise_logo.jpg' }">
				<c:if test="${enterprise.photo.contains('http')}">
					<img width="226" height="194" src="${enterprise.photo}" onerror="this.src='resources/images/service/default_service_pic.gif'"> 
				</c:if>
				<c:if test="${!enterprise.photo.contains('http')}">
					<img width="226" height="194" src="upload/${enterprise.photo}" onerror="this.src='resources/images/service/default_service_pic.gif'"> 
				</c:if>				
			</c:if>
        </a>
      </div>
      <div class="company-info mt38">
        <h3 class="panel-head">服务机构名片</h3>
        <div class="panel-body">
          <div class="top">
            <h4>${enterprise.name }</h4>
            <div class="row">
                    <div class="is-cer">
			            <%-- 1普通企业；2认证企业;3服务机构；4政府机构；  --%>
		            	<c:choose>
		            		<c:when test="${enterprise.type eq '1' }">普通企业</c:when>
		            		<c:when test="${enterprise.type eq '2' }">认证企业</c:when>
		            		<c:when test="${enterprise.type eq '3' }">服务机构</c:when>
		            		<c:when test="${enterprise.type eq '4' }">政府机构</c:when>
		            	</c:choose>
		            </div>
		           <!-- <div class="online-consultation"><a href="#">在线咨询</a></div>-->
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
                <td>
                	<input type="hidden" name="eid" value="${enterprise.id }">
            		<input type="hidden" name="view" value="${view }">
                	<input class="input-large" name="serviceName">
                </td>
              </tr>
              <tr>
                <td class="t1">价格：</td>
                <td><input class="input-small" name="beignPrice">---<input class="input-small" name="endPrice"></td>
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
        <div class="sortbar">
        <ul class="sortpics">
          <li>
            <a href="javascript:filterService('orderType=desc&orderName=serviceNum');" 
            	class="${sparam.orderName == 'serviceNum' ? 'apply' : ''}">
              	申请数<span class="icon-bottom"></span>    
            </a>
          </li>
          <li>
          	<a href="javascript:filterService('orderType=desc&orderName=evaluateScore');" 
          		class="${sparam.orderName == 'evaluateScore' ? 'apply' : ''}">
          		评价<span class="icon-bottom"></span>
          	</a>
          </li>
          <li>
          	<a href="javascript:filterService('orderType=desc&orderName=registerTime');"
          		class="${sparam.orderName == 'registerTime' ? 'apply' : ''}">
          		时间<span class="icon-bottom"></span>
          	</a>
          </li>
          <li>
          	<a href="javascript:filterService('orderType=${sparam.orderType=='desc' ? 'asc' :'desc' }&orderName=costPrice');" 
          		class="${sparam.orderName == 'costPrice' ? 'apply' : ''}">
          		价格<span class="icon-bottom ${sparam.orderType == 'asc' ? 'on' : ''}"></span>
          	</a>
          </li>
          <li class="price-search">
            <div id="rank-priceform">
              <form action="enter/orgservices" method="get">
              	<input type="hidden" name="eid" value="${enterprise.id }">
            	<input type="hidden" name="view" value="${view }">
                <input onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" type="text" name="beignPrice" class="pri jiage" value="${sparam.beignPrice}" />&nbsp;—&nbsp;
                <input  onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" type="text" name="endPrice" class="pri jiage" value="${sparam.endPrice}" />
                <input type="submit" value="确定" class="search-submit" />
              </form>
            </div>&nbsp;
          </li>
          <li class="result-search">
            <form action="enter/orgservices" method="get">
            <input type="hidden" name="eid" value="${enterprise.id }">
            <input type="hidden" name="view" value="${view }">
            <input type="text" name="serviceName" id="" class="pri keywords"  value="${sparam.serviceName }" />
            <input type="submit" class="search-submit" name="" id="" value="搜索"/>
            </form>
          </li>
         
        </ul>
        <ul class="show-type">
           <li class="${view=='icon' ? 'active' : '' }"><a class="s1" href="javascript:void(0)" title="大图显示"><span>大图</span></a></li>
           <li class="${view=='list' ? 'active' : '' }"><a class="s2" href="javascript:void(0)" title="列表显示"><span>列表</span></a></li>
        </ul>
      </div>
      <!-- 服务列表展示 -->
      
      <div class="s-list-show">
        <ul class="${view=='icon' ? 'list-show-block' : 'list-show' } clearfix">
	        <c:forEach var="item" items="${list }">  
	          <li>
	            <div class="info">
	              <a href="service/detail?id=${item.id }&op=enter" target="_blank"><img src="upload/${item.picture }" alt="${item.serviceName }"  onerror="this.src='resources/images/service/default_service_pic.gif'"/></a>
	              <h3 class="ov-h"><a href="service/detail?id=${item.id }&op=enter" target="_blank">${item.serviceName }</a></h3>
	              <p>${fn:substring(my:replaceHTML(item.serviceProcedure), 0, 50) }</p>
	              <div class="col">
	                <span class="price"><i class="j">服务费用：</i> 
	                	<%-- <em><i>¥&nbsp;</i>${item.costPrice }</em> --%>
	                	<c:choose>
							<c:when test="${item.costPrice == null or item.costPrice == 0}"><span class="meta-price">面议</span></c:when>
							<c:otherwise>
								<span class="meta-yuan">￥</span><span class="meta-price">${item.costPrice }</span>
							</c:otherwise>
						</c:choose> 
	                </span>
	                <a class="apply" href="service/detail?id=${item.id }&op=enter" target="_blank">查看详情</a>
	              </div>
	            </div>
	            <div class="refe">
	              <div>评价：</div>
	              <div><label for="">已申请：</label><span class="click">${item.serviceNum }</span>&nbsp;次</div>
	              <div><label for="">发布时间：</label><span class="pubdate">${fn:substring(item.registerTime, 0, 10)}</span></div>
	            </div>
	          </li>
	        </c:forEach>
        </ul>
        <!-- 插入分页 -->
        <div class="plist">
			<pg:pager scope="request" maxItems="9" maxIndexPages="10" index="center" maxPageItems="${sparam.limit }"
				 url="enter/orgservices" items="${total}" export="currentPageNumber=pageNumber">
				<pg:param name="view"  value="${view}" />
				<pg:param name="eid"  value="${sparam.eid}" />
				<pg:param name="beignPrice"  value="${sparam.beignPrice}" />
				<pg:param name="endPrice"  value="${sparam.endPrice}" />
				<pg:param name="categoryId"  value="${sparam.categoryId}" />
				<pg:param name="serviceName"  value="${sparam.serviceName}" />
				<pg:param name="orderName"  value="${sparam.orderName}" />
				<pg:param name="orderType"  value="${sparam.orderType}" />
		    	<pg:first>
		    		<c:if test="${pageNumber != currentPageNumber }">
		    			<a href="${pageUrl}">&#8249;&#8249;</a>
		    		</c:if>
		    	</pg:first>
		    	<c:out value="${pageUrl}"></c:out>
		        <pg:prev><a href="${pageUrl}">&#8249;</a></pg:prev>
		        <pg:pages>
		            <c:choose>
		            <c:when test="${pageNumber eq currentPageNumber }">
		            <a class="on" href="javascript:;" url="${pageUrl }">${pageNumber }</a>
		            </c:when>
		            <c:otherwise>
		            <a href="${pageUrl }">${pageNumber}</a>
		            </c:otherwise>
		            </c:choose>
		   		</pg:pages>
		        <pg:next><a href="${pageUrl}">&#8250;</a></pg:next>
		        <pg:last>
		        	<c:if test="${pageNumber != currentPageNumber }">
		        		<a href="${pageUrl}">&#8250;&#8250;</a>
		        	</c:if>
		        </pg:last>
			</pg:pager> 
	    </div>
      </div>
    </div>
    <div class="clearer"></div>
  </div>
</div>
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script type="text/javascript" src="resources/js/main/company.js"></script>
<!-- <script type="text/javascript" src="jsLib/jquery/jquery.lazyload.min.js"></script> -->
</body>
</html>