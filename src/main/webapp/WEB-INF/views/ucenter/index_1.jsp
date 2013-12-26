<%--
普通企业首页
 --%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.eaglec.com/jsp/user/functions" prefix="my"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>深圳市中小企业公共服务平台</title>
<meta charset="UTF-8"/>
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/style.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/index.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jNotify/jNotify.jquery.css" />
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
		<div class="">
		    <h3 class="common-head">我要认证</h3>
		    <%-- 认证提示 --%>
		       <c:if test="${lastappr == null }">
		               <div class="identi-block">
             <p>您还未申请企业实名认证。实名认证成功后方可在平台申请服务。</p>
             <a href="ucenter/auth_form?op=2" class="user-btn">申请实名认证</a>
             <p>您也可以直接申请认证为服务机构，成功通过认证后，可以在平台发布服务。</p>
              <a href="ucenter/auth_form2?op=2" class="user-btn">申请服务机构认证</a>
           </div>
		  </c:if>
		    <%-- 认证记录，最近一条 --%>
		    <c:if test="${lastappr != null }">
		    <div class="auth-list-content">
	            <table class="auth-grid" width="100%" border="0" cellpadding="0" cellspacing="0">
	                <thead>
	                  <tr>
	                    <th width="12%">申请编号</th>
	                    <th width="15%">申请类型</th>
	                    <th width="12%">申请时间</th>
	                    <th width="10%">处理状态</th>
	                    <th width="12%">处理时间</th>
	                    <th width="25%">处理意见</th>
	                    <th width="14%">操作</th>
	                  </tr>
	                </thead>
	                <tbody>
	                  <tr>
	                    <td>${lastappr.serialId }</td>
	                    <td>${lastappr.applyType }</td>
	                    <td>${fn:substring(lastappr.applyTime, 0, 10)}</td>
	                    <td><span class="notice">${lastappr.approveStatus }</span></td>
	                    <td>${fn:substring(lastappr.approveTime, 0, 10)}</td>
	                    <td>${lastappr.approvedContext }</td>
	                    <td>
	                    	<p><a href="ucenter/auth_form" style="color:#800080;"></a></p>
	                    	<p><a href="ucenter/auth_form2" style="color:#800080;"></a></p>
	                    </td>
	                  </tr>
	                </tbody>
	            </table>
	        </div>
		    </c:if>
		</div>
       <!-- /order-info -->
       <div class="recommend-service">
            <h3 class="common-head">推荐服务 <a href="javascript:void(0)" class="a-link interesting_a">我感兴趣的领域</a><a href="javascript:void(0);" class="expre"><img src="resources/images/ucenter/user-ex.png" alt="" /></a></h3>
           <ul class="recommend-service-list change">
           	<c:forEach var="map" items="${recommends }">
           		<c:choose>
           			<c:when test="${map['collected'] == true }">
           				<c:set value="${map['service']}" var="item"></c:set>
           				<c:set value="true" var="collected"></c:set>
           			</c:when>
           			<c:otherwise>
           				<c:set value="${map['service']}" var="item"></c:set>
           				<c:set value="false" var="collected"></c:set>
           			</c:otherwise>
          		</c:choose>
               <li>
                   <div class="recommend-service-thumb"><a class="service-a" target="_blank" href="service/detail?id=${item.id }">
                   		<c:if test="${item.picture.contains('http')}">
                   			<img class="service-img" src="${item.picture }" width="80" height="80" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
                   		</c:if>
                   		<c:if test="${!item.picture.contains('http')}">
                   			<img class="service-img" src="upload/${item.picture }" width="80" height="80" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
                   		</c:if>
                   </a></div>
                   <div class="recommend-service-text">
                       <h4><a class="a-link service-a" target="_blank" href="service/detail?id=${item.id }">${item.serviceName }</a></h4>
                       <div class="service-text">${fn:substring(my:replaceHTML(item.serviceProcedure),0,90) }${fn:length(my:replaceHTML(item.serviceProcedure)) > 90 ? '...' : '' }</div>
                   </div>
                   <div class="service-price">￥${item.costPrice }<div class="collect">
                      <c:choose>
                   	  	<c:when test="${collected == true}">
	                   		<a href="javascript:void(0);" class="active">已收藏</a>
                   	  	</c:when>
                   	  	<c:otherwise>
                   	  		<a href="javascript:void(0);" onclick="collect(this, ${item.id});">收藏</a>
                   	  	</c:otherwise>
                   	  </c:choose>
                   </div></div>
               </li>
			</c:forEach>              
           </ul>
       </div>
       <!-- /recommend-service -->
       <!-- 服务收藏 -->
       <div class="recommend-service">
            <h3 class="common-head">服务收藏</h3>
           <ul class="recommend-service-list change">
           	<c:forEach var="item" items="${myFavorites}">
               <li>
                   <div class="recommend-service-thumb"><a class="service-a" target="_blank" href="service/detail?id=${item.serviceId }">
                   		<c:if test="${item.picture.contains('http')}">
                   			<img class="service-img" src="${item.picture}" width="80" height="80" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
                   		</c:if>
                   		<c:if test="${!item.picture.contains('http')}">
                   			<img class="service-img" src="upload/${item.picture}" width="80" height="80" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
                   		</c:if>
                   </a></div>
                   <div class="recommend-service-text">
                       <h4><a class="a-link service-a" target="_blank" href="service/detail?id=${item.serviceId }">${item.serviceName }</a></h4>
                       <div class="service-text">${fn:substring(my:replaceHTML(item.serviceProcedure),0,90) }${fn:length(my:replaceHTML(item.serviceProcedure)) > 90 ? '...' : '' }</div>
                   </div>
                   <div class="service-price">￥${item.price}<div class="collect"></div></div>
               </li>
			</c:forEach>              
           </ul>
            ${fn:length(myFavorites) < 1 ? '<h4 class="title-error"><span class="icon-error"></span>暂时没有和您匹配的需求.</h4>' :''}
       </div>
       <!-- /服务收藏 -->
       <div class="chanel-box">
           <div class="common-box">
               <h3><a href="">企业社区</a></h3>
               <div class="eblog">
                 <div class="eblog-user-head">
                   <a href="http://shequ.smemall.com.cn/api/oauth.php"><img src="resources/images/ucenter/eblog_head.jpg" width="90" height="90" /></a>
                 </div>
                 <ul class="eblog-summary">
                   <li>评论: 20 </li>
                   <li>消息：5 </li>
                   <li>新增粉丝：23</li>
                   <li class="link"><a href="http://shequ.smemall.com.cn/api/oauth.php">进入企业社区></a></li>
                 </ul>
               </div>
           </div>
        <!--    <div class="common-box">
               <h3><a href="">企业百科</a></h3>
               <div class="wiki">
                 <h4 class="wiki-title">SME-MALL企业百科</h4>
                 <div class="description">
                   SMEmall企业百科平台给企业和员工提供创建和检阅海量知识，分享经验的平台。
                 </div>
                 <div class="link"><a href="">进入企业百科></a></div>
               </div>
           </div> -->
       </div>
       <!-- /chanel-box -->

    </div>
    <div class="clr"></div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<!-- 我感兴趣的服务领域 -->
	<div class="overlay interesting">
		<div class="service-layout">
			<div class="service-head">
				请选择服务类别 
				<button class="save" type="button">保存</button>
				<input id="userId" type="hidden" value="${user.id }"/>
				<a class="close" href="javascript:void(0)">X</a>
			</div>
			<div class="service-body">
				<div class="selected-items">
					<div class="selected-name">已选择服务类别：</div>
					<ul class="selected-list">
					<c:forEach items="${user.interestServices}" var="item">
						<li>
							<input type="checkbox" value="${item.id }" name="${item.id }" checked="checked"/>
							<span>${item.text }</span>
						</li>
					</c:forEach>
					</ul>
				</div>
				<c:forEach var="parent" items="${requestScope.root.children }">
					<div class="service-row">
						<div class="service-name">${parent.text }</div>
						<ul class="service-list">
							<c:forEach var="leaf" items="${parent.children }">
								<li><input type="checkbox" name="${leaf.id }"
									value="${leaf.id }" ${user.interestServices.contains(leaf)==true?'checked="checked"':'' }  /><span>${leaf.text }</span></li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
<script type="text/javascript" src="app/data/PlatMap.js"></script>
<script type="text/javascript" src="resources/js/ucenter/index.js"></script>
<script type="text/javascript" src="jsLib/jNotify/jNotify.jquery.min.js"></script>
<script type="text/javascript" src="resources/js/main/collectService.js"></script>
</body>
</html>