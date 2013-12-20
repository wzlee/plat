<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://www.eaglec.com/jsp/user/functions" prefix="my"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>搜索列表-深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/main/style.css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/search.css" />
</head>

<body>
	<jsp:include page="../../layout/head.jsp" flush="true"/>
<!-- /header -->
<div class="search-wrap">
  <div class="crumb-nav">搜索 > <span class="current">${name}</span><span class="result">共找到<span class="f-purple">${total}</span>个与<span class="f-purple">${name}</span>相关服务</span></div>
 <div class="sortbar">
    <ul class="nav-pills">
      <!-- <li class="active"><a href="javascript:void(0);" class="serviceNum-order">申请数<i class="up"></i></a></li> -->
      <li><a href="javascript:void(0);" class="serviceNum-order">申请数<i class="down"></i></a></li>
      <li><a href="javascript:void(0);" >评价<i class="down"></i></a></li>
      <li><a href="javascript:void(0);" class="registerTime-order">时间<i class="down"></i></a></li>
      <li><a href="javascript:void(0)" class="costPrice-order">价格<i class="down"></i></a></li>
      <li class="price-search">
      <div id="rank-priceform" class="">
          <form method="post" action="javascript:void(0);">
          	<p>
              <input type="text" value="${min}" class="pri jiage" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" name="min" />&nbsp;-&nbsp;
              <input type="text" value="${max}" class="pri jiage" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" name="max" />
            </p>
            <div class="btns">
            	<a class="reset" href="javascript:void(0);" >清除</a>
            	<span class="btn"><button class="i" type="submit">确定</button></span>
            </div>
          </form>
      </div>
      </li>
    </ul>
    <div class="items-control">
    <ul class="switch-control">
      <li class="s1-on"><a href="javascript:void(0)">大图</a></li>
      <li class="s2"><a href="javascript:void(0)">列表</a></li>
    </ul>
    <ul class="page-control">
      <li class="s1"><a href="">后退</a></li>
      <li class="s2-on"><a href="">前进</a></li>
    </ul>
    </div>
  </div>
  <div class="search-items">
    <ul class="item-list">
    	<c:forEach items="${serviceViews}" var="serviceView">
    		<li>
    			 <input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${serviceView.enterpriseId }"/> 
		        <div class="pic"><a href="service/detail?id=${serviceView.id}" target="_blank">
		        	<c:if test="${serviceView.picture.contains('http')}">
		        		<img src="${serviceView.picture}" width="218" height="180" onerror="this.src='resources/images/service/default_service_pic.gif'"/> 
		        	</c:if>
		        	<c:if test="${!serviceView.picture.contains('http')}">
		        		<img src="upload/${serviceView.picture}" width="218" height="180" onerror="this.src='resources/images/service/default_service_pic.gif'"/> 
		        	</c:if>
		        	<%-- <img class="lazy" src="resources/images/service-loading.gif" 
									data-original="upload/${serviceView.picture}" width="218" height="180" alt="${serviceView.serviceName }" 
									title="${serviceView.serviceName }" onerror="nofind(); "/> --%>
		        </a></div>
		        <h3 class="summary"><a href="service/detail?id=${serviceView.id}" target="_blank" title="${serviceView.serviceName}">
		        	${fn:substring(my:replaceHTML(serviceView.serviceName),0, 14) }</a></h3>
		        <div class="row">
		          <div class="star">
		          	<c:choose>
		        		<c:when test="${serviceView.evaluateScore == 0 }"><span class="meta-star level-0"></span></c:when>
		        		<c:when test="${serviceView.evaluateScore == 1 }"><span class="meta-star level-1"></span></c:when>
		        		<c:when test="${serviceView.evaluateScore == 2 }"><span class="meta-star level-2"></span></c:when>
		        		<c:when test="${serviceView.evaluateScore == 3 }"><span class="meta-star level-3"></span></c:when>
		        		<c:when test="${serviceView.evaluateScore == 4 }"><span class="meta-star level-4"></span></c:when>
		        		<c:when test="${serviceView.evaluateScore == 5 }"><span class="meta-star level-5"></span></c:when>
		        	</c:choose>
		          </div>
		          <div class="price">
		          	<c:choose>
		        		<c:when test="${serviceView.costPrice == null or serviceView.costPrice == 0}">面议</c:when>
			        	<c:otherwise>
			        		<em>￥</em>${serviceView.costPrice}
			        	</c:otherwise>
		        	</c:choose> 
		          </div>
		        </div>
		        <div class="row" style="height:20px;">
		          <div class="publish">发布时间：<span class="date">${fn:substring(serviceView.registerTime,0,10)}</span></div>
		          <div class="apply">已申请：<span class="num">${serviceView.serviceNum}</span></div>
		        </div>
		        <div class="company">${serviceView.enterpriseName}</div>
		        <div class="row">
		         <%--  <c:if test="${serviceView.isApproved == true}"> --%>
			          <div class="is-cer">服务机构</div>
		          <%-- </c:if> --%>
		         <!-- <div class="online-consultation"><a href="">在线咨询</a></div>-->
		        </div>
		        <div class="apply-action"><a class="apply-btn" target="_blank" href="service/detail?id=${serviceView.id}" >查看详情</a></div>
		     </li>
    	</c:forEach>
    </ul>
  </div>
  <!-- 分页  -->
 
  <div class="plist">
   	<c:if test="${total>0}">
		<pg:pager scope="request" maxIndexPages="10" index="center" maxPageItems="12"
			 url="service/result" items="${total}" export="currentPageNumber=pageNumber">
			<pg:param name="encodedName"  value="${encodedName}" />
			<pg:param name="order"  value="${order}" />
			<pg:param name="upOrDown"  value="${upOrDown}" />
			<pg:param name="max"  value="${max}" />
			<pg:param name="min"  value="${min}" />
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
	            <c:if test="${pageNumber != currentPageNumber }">
	            	<a href="${pageUrl}">&#8250;&#8250;</a>
	            </c:if>
	        </pg:last>
		</pg:pager> 
   </c:if>
   <c:if test="${total<=0}">
  	 	没有找到任何跟"关键词"相关的内容。您可以尝试使用其他关键词进行搜索，或返回 首页。
   </c:if>
   </div>
</div>

<!--/search-wrap-->
	<jsp:include page="../../layout/footer.jsp" flush="true"/>
	<script type="text/javascript" src="resources/js/main/jquery.placeholder.min.js"></script>
	<script src="jsLib/jquery/jquery.lazyload.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="resources/js/main/search_service.js"></script>
	<script type="text/javascript">
		$("img.lazy").lazyload({
			placeholder : "img/grey.gif",
			effect : "fadeIn"  
		});	
		$("input[name='type']").attr("value", 0);    //type是head.jsp的搜索框里的textfield,作为区分result1.jsp和result2.jsp的标志
	</script>
</body>
</html>