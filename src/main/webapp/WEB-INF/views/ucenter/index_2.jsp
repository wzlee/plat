<%--
认证企业首页
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
<base href="<%=basePath%>">
<title>深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
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
			<div class="order-info">
				<h3 class="common-head">订单信息</h3>
			    <div class="order-summary">
                	<div class="l remove-border">
	             	 	<c:if test="${hotServices == null }">
	                    <ul class="clearfix">
	                        <li>申请服务卖家已确认：( <a href="ucenter/buyer_order?op=3&orderStatus=6&flag=0">${mybuys[0] }</a> )</li>
	                        <li>申请服务卖家已拒绝：( <a href="ucenter/buyer_order?op=3&orderStatus=11&flag=0">${mybuys[1] }</a> )</li>
	                        <li>我的招标：( <a href="bidding/toDeallist?op=101&orderStatus=4">${mybidding }</a> )</li>
	                        <li>等待关闭的订单：( <a href="ucenter/buyer_order?op=3&orderStatus=11&flag=0">${mybuys[2] }</a> )</li>
	                    </ul>
	                    </c:if>
	                    <c:if test="${hotServices != null }">
	                    <div class="buy-now clearfix">
						<p>您还没在平台购买任何服务,赶快开始购买之旅吧</p>
							<c:forEach var="item" items="${hotServices }" begin="0" end="4">
							<div class="half clearfix"><a href="service/detail?id=${item.id }">
							<c:if test="${item.picture.contains('http')}">
								<img src="${item.picture }" width="75" height="70" onerror="this.src='resources/images/service/default_service_pic.gif'"/></a>
							</c:if>
							<c:if test="${!item.picture.contains('http')}">
								<img src="upload/${item.picture }" width="75" height="70" onerror="this.src='resources/images/service/default_service_pic.gif'"/></a>
							</c:if>
							<div class="panel">${item.serviceName } <a href="service/detail?id=${item.id }">hot</a></div>
							</div>
							</c:forEach>
							
						</div>
						</c:if>
	               </div>
	     
          		</div>
				
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
          <!--  <div class="common-box">
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
<script type="text/javascript" src="jsLib/jNotify/jNotify.jquery.min.js"></script>
<script type="text/javascript" src="resources/js/main/collectService.js"></script>
</body>
</html>