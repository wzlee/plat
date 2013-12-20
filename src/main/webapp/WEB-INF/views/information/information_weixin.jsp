<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.eaglec.com/jsp/user/functions" prefix="my"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta  charset=UTF-8">
<title>微信公共账号信息化建设-企业信息化品中心</title>
<link type="text/css" rel="stylesheet" href="resources/css/main/e-information.css" />
</head>
<!-- 企业信息化频道首页 -->
<body>
<jsp:include page="../layout/head.jsp" flush="true" />
<!-- /header -->
<!-- /banner -->
<div class="wrap clearfix">
	<div class="crumb-nav"><a href="./">首页</a>&nbsp;>&nbsp;<a href="/public/information">企业信息化</a>&nbsp;>&nbsp;<span class="current">微信公共账号信息化建设</span></div>
  <div class="ex-banner">
    <img src="resources/images/main/e-banner-wx.jpg" alt="" />
  </div>
  <div class="hero">
      <h2>采用技术</h2>
      <p>微网站采用HTML5+CSS3技术，适配于多种移动终端，是针对微信量身定制的网站，数亿微信用户在与企业点对点亲密互动的同时，实现了完美便捷的体验,和微信公共账号无缝对接</p>
  </div>
   <div class="hero">
      <h2>海量模板</h2>
      <div class="clearfix">
        <div class="pic fl">
          <img src="resources/images/main/templet-icon.jpg" alt="" />
        </div>
        <div class="bfc">
          <p>微网站拥有涵盖各行各业的海量模板,精致美观,适合各个行业的企业选用,快速搭建,快速调试上线</p>
        </div>
      </div>
  </div> 
<div class="hero">
      <h2>解决方案</h2>
      <div class="case-list clearfix">
        <div class="pic fl">
          <img src="resources/images/main/member-icon.jpg" alt="" />
        </div>
        <div class="bfc">
          <h3>微会员</h3>
          <p>
微会员属于自己的微信端会员管理系统 <br />
自行创建会员等级，系统根据等级规则自动识别会员，可制定不同等级会员可享受的优惠政策，消费一定的频次后，会员还可自动升级。清晰记录会员的消费行为，并进行数据分析，从而实现老会员的维护及各种模式的精准营销。
</p>
        </div>
      </div>
      <div class="case-list clearfix">
        <div class="pic fl">
          <img src="resources/images/main/vmall-icon.jpg" alt="" />
        </div>
        <div class="bfc">
          <h3>微商城</h3>
          <p>微商城构建一个全流程的购物体验，商家可发布商品、设定价格，客户在手机上直接浏览查看商品信息，只需轻松一点即可提交订单并付款，购物过程轻松、便捷；微商城把店铺开到用户手机上</p>
        </div>
      </div>
      <div class="case-list clearfix">
        <div class="pic fl">
          <img src="resources/images/main/custom-icon.jpg" alt="" />
        </div>
        <div class="bfc">
          <h3>微定制</h3>
          <p>根据企业微信、微博运营的需要，提供专业化、个性化、全方位的服务，内容包括：模板设计、功能设计、实现与企业CRM系统对接、微信运营指导、人员技术培训等</p>
        </div>
      </div>
  </div>   
</div>
<jsp:include page="../layout/footer.jsp" flush="true" />
</body>
</html>