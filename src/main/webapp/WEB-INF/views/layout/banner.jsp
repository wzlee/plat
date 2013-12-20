<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="dom-banner">
	<ul class="dom-box">
		<c:forEach items="${bannerList}" var="banner">
			<c:if test="${banner.micon.contains('http')}">
				<li style="background: url(${banner.micon}) top center no-repeat;"></li>
			</c:if>
			<c:if test="${!banner.micon.contains('http')}">
				<li style="background: url(upload/${banner.micon}) top center no-repeat;"></li>
			</c:if>   			
   		</c:forEach>
	</ul>
</div>