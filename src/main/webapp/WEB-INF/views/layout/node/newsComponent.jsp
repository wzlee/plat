<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="column-news">
    <h3>行业新闻<a class="more" href="news/getNewsList?start=0">更多></a></h3>
    <ul class="list">
     	<c:forEach items="${newsList}" var="news">
     		<c:choose>
     			<c:when test="${fn:length(fn:trim(news.title)) > 16}">
     				<li><a href="news/getOneNewsDetails?id=${news.id}">${fn:substring(news.title, 0, 16)}</a></li>	
     			</c:when>
     			<c:otherwise>
     				<li><a href="news/getOneNewsDetails?id=${news.id}">${fn:trim(news.title)}</a></li>		
     			</c:otherwise>
     		</c:choose>
		</c:forEach> 
    </ul>
</div>