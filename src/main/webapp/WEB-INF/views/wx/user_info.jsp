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
<title>用户信息-深圳中小企业服务平台</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<meta name="keywords" content="" />
<meta name="description" content="" />
<link type="text/css" rel="stylesheet" href="resources/css/webcss.css" />
</head>
<body>
<div id="header">
	<a href="javascript:history.go(-1)" class="up-data"></a>
	<h1>用户信息</h1>
	<a href="/wx/index" class="home-icon"></a>
</div>
<!-- 头部 -->
<div class="container">
	<div class="panel user-page">
		<div class="user-info clearfix">
			<img src="upload/${user.enterprise.photo }" alt="" class="fl">
			<div class="bfc">
				<div class="name"><strong>${user.userName }</strong>&nbsp;|&nbsp;${user.enterprise.name } <br></div>
				<img src="images/approve.jpg" alt="">
			</div>
		</div>
		<div id="tabs" class="tabs three">
			<li class="active">基本资料</li>
			<li>企业信息</li>
			<li>联系方式</li>
		</div>				
			<div id="tabs-content">
				<div class="hide" style="display:block">				
					用户名: <br />
					邮箱: <br />
					机构简称: <br />
					企业全称: <br />
					用户类型: <br />
					注册时间: <br />
					机构介绍: 
				</div>
				<div class="hide">
				<!-- 1、电子装备 2、服装 3、港澳资源 4、工业设计 5、机械 6、家具 7、LED 8、软件 9、物流10、物联网11、新材料 12、医疗器械13、钟表14、珠宝15、其他
	 * 16、枢纽平台 -->
					所属窗口:${user.enterprise.industryType} <br>
<!-- 1、农、林、牧、渔业
	 *	2、工业 3、建筑业 4、批发业 5、零售业 6、交通运输业 7、仓储业 8、邮政业
     *	9、住宿业 10、餐饮业 11、信息传输业 12、软件和信息技术服务业 13、房地产开发经营
	 *	14、物业管理 15、租赁和商务服务业 16、其他未列明行业 17、金融 -->
					行业:${user.enterprise.enterpriseIndustry } <br>
					<!-- 1、国营 2、民营 3、三资 4、股份制 5、合伙制 6、民非  7、其他 -->
					机构性质:${user.enterprise.orgProperty } <br>
					
					经营范围:${user.enterprise.business } <br>
					
					优势服务领域:<c:forEach items="${user.enterprise.myServices }" var="myserviceItem">
					${myserviceItem.text }&nbsp;&nbsp;&nbsp;
					</c:forEach> <br>
					
					总资产(上年、万元):${user.enterprise.totalAssets } <br>
					
					年营业额(上年、万元)：${user.enterprise.salesRevenue } <br>
					
					利润或净收入(上年、万元)：${user.enterprise.profit } <br>
					
					成立日期:${user.enterprise.openedTime } <br>
					
					职工人数： ${user.enterprise.staffNumber }<br>
					
					组织机构代码：${user.enterprise.icRegNumber } <br>
					
					机构电话：${user.enterprise.tel } <br>
					
					机构传真：${user.enterprise.orgFax } <br>
					
					公司网址：${user.enterprise.orgWebsite } <br>
					
					机构地址：${user.enterprise.address } <br>
					
					社会荣誉：${user.enterprise.honorSociety } <br>
					
					专业资质：${user.enterprise.professionalQualifications }<br>
					
					主营产品/服务：${user.enterprise.mainRemark }
				</div>
				<div class="hide">				
					法定代表人：${user.enterprise.legalPerson }<br>
					手机：${user.enterprise.legalRepresentativeMobile }<br>
					Email：${user.enterprise.generalManagerMobile }<br>
					<hr>
					总经理姓名：<br>
					手机：<br>
					Email：<br>
					<hr>
					联系人姓名：${user.enterprise.linkman }<br>
					手机：${user.enterprise.contactNameMobile }<br>
					Email：${user.enterprise.contactNameEmail }<br>
				
				</div>
			</div>		
	</div>		
</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>