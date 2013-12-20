<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<title>深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="resources/css/main/e-information.css" />
</head>
<!-- 企业信息化详情页 -->
<body>
	<jsp:include page="../layout/head.jsp" flush="true"/>
<div class="wrap">
<div class="crumb-nav"><a href="./">首页</a>&nbsp;>&nbsp;<a href="/public/information">企业信息化</a>&nbsp;>&nbsp;<span class="current">企E通</span></div>

<div class="main-container">
	<div class="eiNav ">
		<ul class="clearfix">
			<li class="s1"><a href="javascript:void(0);"></a></li>
			<li class="s2"><a href="javascript:void(0);"></a></li>
			<li class="s3"><a href="javascript:void(0);"></a></li>
			<li class="s4"><a href="javascript:void(0);"></a></li>
			<li class="s5"><a href="javascript:void(0);"></a></li>
		</ul>
	</div>
	<div class="ei-content">
		<h2 class="title">企E通---企业信息服务产品介绍</h2>
		<div class="panel">
			<h4>产品简介</h4>
			<P>
				企E通融合协同办公、ERP、进销存、CRM等主要企业应用软件于一体，集成各种电信通讯方式，采用基于云计算的软件体系结构，面向中小企业，打造最适合中小企业应用的一体化软件产品，并由此为企业提供专业、深入的信息服务。
				<img src="resources/images/main/ei-001.jpg" alt="" />
			</P>
			<h4>产品特点(一)</h4>
			<p><strong>产品功能高度集成</strong></p>
			<ol class="ei-arrow">
				 <li>协同办公</li>
				 <li>ERP</li>
				 <li>CRM</li>
				 <li>进销存</li>
				 <li>企业邮箱</li>
				<li>企业短信</li>
				<li>网络传真</li>
				<li>在线呼叫（Click and Call)</li>
				<li>在线客户</li>
				<li>即时通讯</li>
				<li>个性化功能插件</li>
				
			</ol>
			<h4>产品特点(二)</h4>
			<p><strong>产品功能高度集成</strong></p>
			<p>
				分域管理，独立访问，最佳SAAS用户体验
   目前市场上的SAAS产品，无论是金蝶友商网还是八佰客等公司的产品，用户使用这些业务的时候，都必须通过访问一个第三方网站登录使用。而企E通产品是目前国内唯一的采用分域管理的SAAS产品，各个企业使用自己独立的域名进行访问，大大提升了用户体验，对于SAAS业务推广有着重要意义。
			</p>
			<h4>产品特点(三)</h4>
			<p><strong>强大的通讯功能</strong> <br />
			<strong>企E通里面集成了大量的通讯功能</strong> <br />
  <strong> 网络传真：</strong>企业申请一个传真号码，然后就可以通过邮件接收、发送传真，支持传真分机功能，企业每个人都有一个自己的传真机<br />
   <strong>企业短信：</strong>与协同办公、ERP、CRM系统紧密集成，最好的企业信息发布、事务提醒方式<br />
   <strong> 呼叫中心：</strong>点击即呼叫，方便实现语音外呼。语音信箱与邮件系统结合，收听语音信箱方便快捷<br />
    <strong>移动办公门户：</strong>随时随地通过3G手机处理日常办公事务，查询生产情况<br />
			
			</p>
			<h4>产品特点(四)</h4>
			<p><strong>最好的可扩展性</strong><br />
				企E通独有的“业务插件”功能，可以针对企业的各种个性化需求，在企E通平台上面快速开发出满足企业需求的产品，不同企业使用不用的“业务插件”，个性化和标准化完美结合。
				
			</p>
			<h4>软件一览</h4>
			<p>
			<img src="resources/images/main/ei-soft1.jpg" />
			<img src="resources/images/main/ei-soft1.jpg" />
			</p>
		</div>
	</div>
</div>
<!-- /main-container -->
</div>
<!-- /wrap -->
	<jsp:include page="layout/footer.jsp" flush="true"/>
</body>
</html>