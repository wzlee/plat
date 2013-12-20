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
	<title>企业服务导航盘-深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="resources/html/lunpan/lib/selecter/jquery.fs.selecter.css">
	<link rel="stylesheet" type="text/css" href="resources/html/lunpan/css/style.css">
	<link rel="stylesheet" type="text/css" href="resources/html/lunpan/lib/ui/theme/jquery-ui.min.css">
</head>

<body>
<jsp:include page="layout/head.jsp" flush="true" />
<div class="wrap">
	<div id="loading"><img src="resources/html/lunpan/images/loading.gif" width="45" height="45" alt=""></div>
	<!-- /loading -->
	<div class="service-canvas">
		<div class="enterprise-service-list">
			<div class="bookmark-close"></div>
			<div class="enterprise-service-content">
				<h2 class="title">腾讯科技有限公司</h2>
				<h4 class="message">属于<span class="s1">微型</span>企业，可享受政府资金资助金额为<span class="s2">12345</span>万元</h4>
				<div class="service-pandora">
					<h3 class="title">您还可以享受SMEmall为您提供的以下服务：</h3>
					<ul class="service-list">
						<li>
							<div class="service-author"><a href="javascript:void(0)"><img src="resources/html/lunpan/images/avatar_default.png" alt=""></a></div>
							<div class="property">
								<p class="s1">3D打印服务</p>
								<p class="s2">3D打印模型服务介绍</p>
							</div>
							<div class="price">
								<p class="s1">￥12345</p>
								<p class="s2">50%off</p>
							</div>
							<div class="apply">
								<a href="/service/detail?id=363">申请服务</a>
							</div>
						</li>
						<li>
							<div class="service-author"><a href=""><img src="resources/html/lunpan/images/avatar_default.png" alt=""></a></div>
							<div class="property">
								<p class="s1">WLD3系列RFID数据采集器</p>
								<p class="s2">服务介绍</p>
							</div>
							<div class="price">
								<p class="s1">￥0</p>
								<p class="s2">免费</p>
							</div>
							<div class="apply">
								<a href="/service/detail?id=484&op=mall">申请服务</a>
							</div>
						</li>
					</ul>
					<p class="more"><a href="javascript:void(0)">其它更多服务>></a></p>
				</div>
			</div>
		</div>
		<!-- .enterprise-service-list -->
		<div class="loading">正在评测...请等待几秒钟</div>
		<!-- /loading -->
		<div class="enterprise-info-panel">
			<div class="bookmark-close"></div>
			<div class="commit">提交</div>
			<form action="">
				<div class="form-panel">
					<div class="controls">
						<input class="xxx-large" type="text" placeholder="企业名称">
					</div>
					<div class="controls">
						<input class="xxx-large" type="text" placeholder="企业人数">
					</div>
					<div class="controls">
						<input class="xxx-large" type="text" placeholder="创立时间" id="datepicker">
					</div>
					<div class="controls">
						<select class="selecter" id="selecterTrade">
                        <option value="16">枢纽平台</option>
                        <option value="1">电子装备</option>
                        <option value="2">服装</option>
                        <option value="3">港澳资源</option>
                        <option value="4">工业设计</option>
                        <option value="5">机械</option>
                        <option value="6">家具</option>
                        <option value="7">LED</option>
                        <option value="8">软件</option>
                        <option value="9">物流</option>
                        <option value="10">物联网</option>
                        <option value="11">新材料</option>
                        <option value="12">医疗器械</option>
                        <option value="13">钟表</option>
                        <option value="14">珠宝</option>
                        <option value="15">其他</option>
						</select>
					</div>
					<div class="controls">
						<select class="selecter" id="selecterFund">
                        <option value="16">发展新技术</option>
                        <option value="1">投资建厂</option>
						</select>
					</div>
				</div>
			</form>
		</div>
		<div class="huge-arrow"></div>
		<div id="service-wheel" class="service-wheel jump">
			<div class="wheel-a"><span>市场</span></div>
			<div class="wheel-b"><span>资金</span></div>
			<div class="wheel-c"><span>人才</span></div>
			<div class="wheel-d"><span>技术</span></div>
		</div>
		<div class="shadow-line"></div>
	</div>
	<!-- .service-canvas -->
	<div class="enterprise-test">
		<div class="book"></div>
		<div id="broad" class="broad">
			<div id="result-bookmark-close" class="result-bookmark-close"></div>
			<div class="company-scroll"></div>
			<div class="test-sign"></div>
			<div id="magnifier" class="magnifier"></div>
			<div class="test-result">
				<p class="info">贵公司是<span class="s1">小卖部</span>级别公司，请继续努力哦！</p>
				<!-- Baidu Button BEGIN -->
				<div id="bdshare" class="bdshare_b" style="line-height: 12px;">
				<img src="http://bdimg.share.baidu.com/static/images/type-button-1.jpg?cdnversion=20120831" />
				<a class="shareCount"></a>
				</div>
				<script type="text/javascript" id="bdshare_js" data="type=button&amp;uid=0" ></script>
				<script type="text/javascript" id="bdshell_js"></script>
				<script type="text/javascript">
				document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
				</script>
				<!-- Baidu Button END -->
			</div>
		</div>
	</div>
	<!-- .enterprise-test -->
</div>
<!-- .wrap -->
<jsp:include page="layout/footer.jsp" flush="true" />
<!-- <script type="text/javascript" src="resources/html/lunpan/lib/jquery-1.9.1.min.js"></script> -->
<script type="text/javascript" src="resources/html/lunpan/lib/selecter/jquery.fs.selecter.min.js"></script>
<script type="text/javascript" src="resources/html/lunpan/lib/ui/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="resources/html/lunpan/lib/ui/jquery.ui.datepicker.min.js"></script>
<script type="text/javascript" src="resources/html/lunpan/lib/ui/i18n/jquery.ui.datepicker-zh-CN.min.js"></script>
<script type="text/javascript" src="resources/html/lunpan/js/main.js"></script>
</body>
</html>