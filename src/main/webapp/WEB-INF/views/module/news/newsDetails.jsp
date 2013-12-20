<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.eaglec.com/jsp/user/functions" prefix="my"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ page session="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>${item.title }-新闻列表-深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/main/news.css" />
<link href="jsLib/jquery.slider/skitter.styles.css" rel="stylesheet" type="text/css" />
<link href="resources/css/node/index.css" rel="stylesheet" type="text/css" />
</head>

<body>
<jsp:include page="../../layout/head.jsp" flush="true"/>
<!-- /header -->
<!-- 导航栏 -->
<div class="wrap">
  <div class="crumb-nav">首页 >  <span class="current">新闻</span></div>
  <!-- /crumb-nav -->
  <div class="news-container">
    <div class="column">
      <h2 class="ar-h1">${oneNews.title}</h2>
      <div class="ar-info">来源：${oneNews.source}<span class="ar-date">${oneNews.pubdate}</span></div>
      <div class="ar-content">
      	<c:choose>
			<c:when test="${oneNews.picture != ''}">
				<p align="center">
				<c:if test="${oneNews.picture.contains('http')}">
					<img width="520" height="390" src="${oneNews.picture}" />
				</c:if>
				<c:if test="${!oneNews.picture.contains('http')}">
					<img width="520" height="390" src="upload/${oneNews.picture}" />
				</c:if>
				</p>
			</c:when>
		</c:choose>
      	${oneNews.content}
      </div>
    </div>
    <div class="aside">
      <div class="column-box">
        <h3 class="column-box-head">新闻热点</h3>
          <c:forEach var="item" items="${hotNews }" begin="0" end="0">
        	<div class="box-thumb">
        		<a href="news/getOneNewsDetails?id=${item.id }" title="${item.title }">
        		<c:if test="${item.picture.contains('http')}">
        			<img src="${item.picture}" alt="" width="180" height="80" />
        		</c:if>
        		<c:if test="${!item.picture.contains('http')}">
        			<img src="upload/${item.picture}" alt="" width="180" height="80" />
        		</c:if>      		
        		<h4>${fn:substring(item.title, 0, 13) }</h4></a>
        	</div>
        	
          </c:forEach>
        <ul class="box-list">
          <c:forEach var="item" items="${hotNews }" begin="1" end="9">
           <li><a href="news/getOneNewsDetails?id=${item.id }" title="${item.title }">•${fn:substring(item.title, 0, 13) }</a></li>
          </c:forEach>
        </ul>
      </div>
      <div class="column-box mt16">
        <h3 class="column-box-head">新闻推荐</h3>
        <ul class="box-list">
          <c:forEach var="item" items="${pushNews }">
           <li><a href="news/getOneNewsDetails?id=${item.id }" title="${item.title }">•${fn:substring(item.title, 0, 13) }</a></li>
          </c:forEach>
        </ul>
      </div>
      <div class="mt16">
        <a href=""><img src="resources/images/main/ad1.jpg" /></a>
      </div>
      <div class="mt16">
        <a href=""><img src="resources/images/main/ad2.jpg" /></a>
      </div>
    </div>
    <div class="clearer"></div>
  </div>
</div>
<!-- /wrap -->
<jsp:include page="../../layout/footer.jsp" flush="true"/>
</body>
</html>