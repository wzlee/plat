<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="top-nav">
	<ul>
		<c:forEach var="channel" items="${channelList}" varStatus="status">
			<li><a href="${channel.chttp}" target="${channel.linktype}">${channel.cname}</a></li>
		</c:forEach>
    </ul>
</div>