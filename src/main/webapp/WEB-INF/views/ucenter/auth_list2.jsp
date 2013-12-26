<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/style.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<script type="text/javascript" src="app/data/PlatMap.js"></script>
</head>

<body>
<jsp:include page="header.jsp" flush="true"/>
<!-- /header -->
<jsp:include page="head.jsp" flush="true"/>
<!-- /user-header -->
<!-- /container -->
<div class="main-container">
    <!-- 左边菜单 -->
   	<jsp:include page="left.jsp" flush="true" />
    <div class="main-column">
        <h3 class="top-title">认证管理</h3>
        <div class="auth-list-content">
            <table class="auth-grid" width="100%" border="0" cellpadding="0" cellspacing="0">
                <thead>
                  <tr>
                    <th width="12%">申请人姓名</th>
                    <th width="15%">申请机构名</th>
                    <th width="12%">申请时间</th>
                    <th width="10%">处理状态</th>
                    <th width="12%">处理时间</th>
                   <!--  <th width="25%">处理意见</th> -->
                  <!--   <th width="14%">操作</th> -->
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="appr" items="${requestScope.apprlist }">
                  <tr>
                    <td>${appr.userName }</td>
                    <td>${appr.orgName }</td>
                    <td>${fn:substring(appr.regTime, 0, 10)}</td>
                    <td><span class="notice">${appr.approveStatus }</span></td>
                    <td>${fn:substring(appr.approveTime, 0, 10)}</td>
                    <%-- <td>${appr.approvedContext }</td> --%>
                  </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="clr"></div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script type="text/javascript" src="app/data/PlatMap.js"></script>
<script type="text/javascript">
	$(function(){
		var trs = $('table tbody tr');
		for(var i = 0; i < trs.length; i++){
			var tds = trs.eq(i).children();
			// STEP2. 处理状态 	
			var approveStatus = tds.eq(3).text();
			tds.eq(3).children().eq(0).text(PlatMap.ApprovedDetail.approveStatus[approveStatus]);
		}
	});
</script>
</body>
</html>
