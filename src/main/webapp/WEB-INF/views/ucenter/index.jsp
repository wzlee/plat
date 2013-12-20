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
<link type="text/css" rel="stylesheet" href="resources/css/ucenter/index.css" />
<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/js/ucenter/main.js"></script>
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
		<c:if test="${loginEnterprise.type!=1 }">
			<div class="order-info">
				<h3 class="common-head">订单信息</h3>
				<div class="order-summary">
				    <div class="l">
				         <ul>
				             <li>已申请订单：( <a href="num">10</a> )</li>
				             <li>申请成功订单：( <a href="num">2</a> )</li>
				             <li>完成交易订单：( <a href="num">5</a> ) </li>
				         </ul>
				    </div>
				    <div class="r">
				         <ul>
				             <li>待评价订单：( <a href="num">10</a> )</li>
				             <li>已评价订单：( <a href="num">2</a> )</li>
				         </ul>
				    </div>
				</div>
			</div>
		</c:if>
      
		<c:if test="${loginEnterprise.type==1 }">
			<div class="order-info">
			    <h3 class="common-head">我要认证</h3>
			    <div class="order-summary">
			        <div class="l">
			             <a href="#">申请实名认证</a>
			        </div>
			        <div >
			             <a href="#">申请服务机构认证</a>
			        </div>
			    </div>
			</div>
		</c:if>
       <!-- /order-info -->
       <div class="recommend-service">
           <h3 class="common-head">推荐服务</h3>
           <ul class="recommend-service-list">
               <li>
                   <div class="recommend-service-thumb"><a href=""><img src="resources/images/ucenter/service_thumb.jpg" width="80" height="80" /></a></div>
                   <div class="recommend-service-text">
                       <h4>工业设计</h4>
                       <div>中国司机与SMRT之间的矛盾不断升温。后来，有三名中国司机在财神庙里碰头，讨论怎样才能向SMRT施加压力、解决他们的问题，并在网上吹响抗争的号角。</div>
                   </div>
                   <div class="service-price">￥0</div>
               </li>
               <li>
                   <div class="recommend-service-thumb"><a href=""><img src="resources/images/ucenter/service_thumb.jpg" width="80" height="80" /></a></div>
                   <div class="recommend-service-text">
                       <h4>工业设计</h4>
                       <div>中国司机与SMRT之间的矛盾不断升温。后来，有三名中国司机在财神庙里碰头，讨论怎样才能向SMRT施加压力、解决他们的问题，并在网上吹响抗争的号角。</div>
                   </div>
                   <div class="service-price">￥3000</div>
               </li>
           </ul>
       </div>
       <!-- /recommend-service -->
       <div class="chanel-box">
           <div class="common-box">
               <h3><a href="">企业社区</a></h3>
               <div class="eblog">
                 <div class="eblog-user-head">
                   <a href=""><img src="resources/images/ucenter/eblog_head.jpg" width="90" height="90" /></a>
                 </div>
                 <ul class="eblog-summary">
                   <li>评论: 20 </li>
                   <li>消息：5 </li>
                   <li>新增粉丝：23</li>
                   <li class="link"><a href="">进入企业社区></a></li>
                 </ul>
               </div>
           </div>
           <div class="common-box">
               <h3><a href="">企业百科</a></h3>
               <div class="wiki">
                 <h4 class="wiki-title">SME-MALL企业百科</h4>
                 <div class="description">
                   SMEmall企业百科平台给企业和员工提供创建和检阅海量知识，分享经验的平台。
                 </div>
                 <div class="link"><a href="">进入企业百科></a></div>
               </div>
           </div>
           <div class="common-box">
               <h3>浏览记录</h3>
               <ul class="history">
                 <li>
                  <div class="history-thumb"><a href=""><img src="resources/images/ucenter/history_1.jpg" width="68" height="68" /></a></div>
                  <h4><a href="">工业设计</a></h4>
                </li>
                 <li>
                  <div class="history-thumb"><a href=""><img src="resources/images/ucenter/history_1.jpg" width="68" height="68" /></a></div>
                  <h4><a href="">工业设计工业设计工业设计</a></h4>
                </li>
                 <li>
                  <div class="history-thumb"><a href=""><img src="resources/images/ucenter/history_1.jpg" width="68" height="68" /></a></div>
                  <h4><a href="">工业设计</a></h4>
                </li>
               </ul>
           </div>
       </div>
       <!-- /chanel-box -->
       <div class="two-row">
           <div class="row-box">
               <h3>我的订阅<span class="more"><a href="">更多></a></span></h3>
               <div class="row-content">
                   <div class="row-thumb"><a href=""><img src="resources/images/ucenter/feed_1.jpg" width="100" height="100" /></a></div>
                   <div class="row-text">
                        <h4><a href="">工业设计服务</a></h4>
                        <div class="description">
                            介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。
                        </div>
                   </div>
               </div>
           </div>
           <div class="row-box">
               <h3>新闻中心<span class="more"><a href="">更多></a></span></h3>
               <div class="row-content">
                   <div class="row-thumb"><a href=""><img src="resources/images/ucenter/feed_1.jpg" width="100" height="100" /></a></div>
                   <div class="row-text">
                        <h4><a href="">奥尼尔：中国是目前唯一称得上的金砖国家奥尼尔</a></h4>
                        <div class="description">
                            介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。介绍文字，不超过XX个字符不超过XX个字符不超过XX个字符字符不超。
                        </div>
                   </div>
               </div>
           </div>
       </div>
    </div>
    <div class="clr"></div>
</div>
<!-- /container -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
</body>
</html>