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
<title>平台概况-深圳中小企业服务平台</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta name="keywords" content="" />
<meta name="description" content="" />
<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
<div id="header">
	<a href="javascript:history.go(-1)" class="up-data"></a>
	<h1>平台概况</h1>
	<a href="/wx/index" class="home-icon"></a>
</div>
<!-- 头部 -->
<div class="container">
	<div class="panel">
		<ul id="tabs" class="tabs three">
			<li class="active">关于平台</li>
			<li>平台目标</li>
			<li>联系我们</li>
		</ul>
			<div id="tabs-content">
				<div class="hide" style="display:block">
<p>			
深圳市中小企业公共服务平台（英文名称为Shenzhen Small and Medium Enterprises Public Service mall，简写为“SMEmall”）是根据工信部中小企业公共服务平台网络的建设要求，按照深圳市政府关于促进中小企业发展的政策精神，开发的一个颇具“创新精神”的建设理念，为深圳市中小企业提供优质公共服务的平台。</p>
<p>	
SMEmall主旨是企业成长的孵化器，服务机构的助推器，公共管理的加速器。</p>
<p>	
平台的建立在解决中小企业共性需求，畅通信息渠道，改善经营管理，提高发展质量，增强中小企业核心竞争力，帮助企业做大做强，实现创新发展等方面发挥着重要支撑作用，对改善企业发展环境，促进社会资源优化配置和专业化分工协作，推动共性关键技术的转移与应用，逐步形成社会化、市场化、专业化的公共服务体系和长效机制具有重要现实意义。
</p>		
				</div>
				<div class="hide">
					<p>
深圳市中小企业公共服务平台坚持以提供企业最优质、最安全、最便捷的服务为宗旨，以实现提供中小企业“找得着、用得起、有保障”的公共服务为目标，为我市中小企业提供一体化、专业化、协同化的公共服务，促进我市中小企业健康快速发展。
</p>
 <p>
具体目标体现如下：
</p>
 <p>
找得着： <br>
服务导航功能齐备，支持网站、呼叫中心、移动APP、应用软件常规服务导航功能
彰显服务价值，在常规服务导航的基础上，依托于数据挖掘和分析，实现服务个性化推送、定制，从找服务到服务随时就在身边，提升平台服务水准<br>
 </p>
 <p>
用得起：<br>
以产业集群为单位，集合社会各方资源为企业提供服务，企业所付出的单位成本远远低于单个企业获取资源所需成本
细分服务：基础服务、增值服务、个性服务，建议：基础服务免费，增值、个性服务收费，普通会员免费，高级 会员收费<br>
 </p>
 <p>
有保障：<br>
严格服务接入机制<br>
制定完善的服务监控、考核机制<br>
持续优化，不断探索，健全服务协同机制
</p>
</p>
				</div>
				<div class="hide">			
深圳市中小企业公共平台 <br>
办公地址：     深圳市南山区科技园高新中二道生产力大楼D栋1楼 <br>
邮政编码：     518027 <br>
联系电话：     +86(0755)22662277 <br>
服务热线：     +86(0755)-22662277 <br>
传&nbsp;&nbsp;真：     +86(0755)22662277

				</div>

			</div>
	</div>
</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>