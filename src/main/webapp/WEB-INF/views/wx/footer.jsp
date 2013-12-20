<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = "";
	basePath =request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	basePath ="http://wx.smemall.net/";
	
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">
<div id="footer">
	<div class="copyright">
		技术支持:深圳市依格欣计算机技术有限公司<br>
		主管部门:深圳中小企业服务中心&nbsp;&nbsp;
		建设单位:深圳商业联合会 
		
</div>
</div>
<a href="#" id="gotop">返回顶部</a>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
  <script>window.jQuery || document.write('<script src="jsLib/jquery/jquery-1.7.1.min.js"><\/script>')</script>	
  <script type="text/javascript" src="resources/js/web-docs.js"></script>