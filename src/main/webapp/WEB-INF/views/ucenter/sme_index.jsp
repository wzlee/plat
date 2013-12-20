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
        <h3 class="top-title">中小微企业认定</h3>
        <div class="auth-list-content">
            <table class="auth-grid" width="100%" border="0" cellpadding="0" cellspacing="0">
                <thead>
                  <tr>
                    <th width="12%">申请日期</th>
                    <th width="15%">处理状态</th>
                    <th width="12%">处理时间</th>
                    <th width="25%">处理意见</th>
                    <th width="14%">操作</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="identifie" items="${requestScope.identifieList }">
                  <tr>
                  
                    <td>${fn:substring(identifie.applicationTime, 0, 10)}</td>
                    <td>
                    	<c:if test="${identifie.approveStatus ==0 }">未通过</c:if>
                    	<c:if test="${identifie.approveStatus ==1 }">通过</c:if>
                    	<c:if test="${identifie.approveStatus ==2 }">未处理</c:if>
                    </td>
                    <td>
                    <c:choose>
                    	<c:when test="${identifie.approvedTime eq '' or identifie.approvedTime == null }">
                    		暂未处理
                  	  </c:when>
                   		<c:otherwise>
                   		   ${fn:substring(identifie.approvedTime, 0, 10)}
                   		</c:otherwise>
                    </c:choose>
                    </td>
                    <td>
	                    <span class="notice">
	                      <c:choose>
	                    	<c:when test="${identifie.approvedOpinion  eq '' or identifie.approvedOpinion  == null }">
	                    		暂未处理
	                  	  </c:when>
	                   		<c:otherwise>
	                   		    ${identifie.approvedOpinion }
	                   		</c:otherwise>
	                    </c:choose>
	                    </span>
                    </td>
                    <td>
                    	<p><c:if test="${identifie.approveStatus ==0 }"><a href="sme/auth?id=${identifie.id }&op=45">重新申请认定</a></c:if></p>
                    </td>
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
</script>
</body>
</html>
