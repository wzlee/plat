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
<title>企业信息化品中心</title>
<link type="text/css" rel="stylesheet" href="resources/css/main/e-information.css" />
</head>
<!-- 企业信息化频道首页 -->
<body>
<jsp:include page="../layout/head.jsp" flush="true" />
<!-- /header -->
<div class="banner" id="banner">
  <div class="dom-banner">
    <ul class="dom-box">
      <li class="s1"></li>
      <li class="s2"></li>
      <li class="s3"></li>
    </ul>
  </div>
</div>
<!-- /banner -->
<div class="wrap clearfix">
    <div class="qi-et clearfix">
      <h2>一体化企业信息化利器</h2>
      <div class="pic fl">
        <img src="resources/images/main/erp-icon.jpg" alt="" />
      </div>
      <div class="bfc et">
        <p>
企E通融合协同办公、ERP、进销存、CRM,企业邮箱,云呼叫中心等主要企业应用软件于一体，集成各种电信通讯方式，采用基于云计算的软件体系结构，面向中小企业，打造最适合中小企业应用的一体化软件产品，并由此为企业提供专业、深入的信息服务。
</p>
<a href="public/information_detail" class="btn-button">查看详情</a>
      </div>
    </div>
    <div class="qi-et clearfix">
      <h2>信息化服务</h2>
      <ul class="block-info">
        <li>
                    <div class="clearfix">
            <div class="pic-thumb fl">
              <img src="resources/images/main/wuye-icon.jpg" alt="" />
            </div>
            <div class="bfc">
              <h3><a href="public/information_property">园区物业管理系统</a></h3>
              <p>园区物业管理系统是针对中国商用物业、园区物业管理而推出的一款云计算产品,整合物业招商、租售、租金物业收费、物业服务、增值服务等基础服务，并在此基础上实现物业（园区）运营商、入驻企业、政府主管部门的联合资源管理信息化
.</p><a href="public/information_property" class="btn-button">查看详情</a>
            </div>
          </div>
        </li>
        <li>
                <div class="clearfix">
        <div class="pic-thumb fl">
              <img src="resources/images/main/market-icon.jpg" alt="" />
            </div>
            <div class="bfc">
              <h3><a href="public/information_market">专业市场管理系统</a></h3>
              <p>专业市场管理系统是针对大型商场、商业城的现代管理系统，包括销 售、柜台 、店面出租、租金和管理费管理、结算管理和销售提成、销售人员管理等功能，是对商业城、商厦、国营百货公司进行现代信息化管理专业应用系统。</p>
              <a href="public/information_market" class="btn-button">查看详情</a>
            </div>
      </div>
        </li>
      </ul>
    </div>
    <div class="qi-et clearfix">
      <h2>移动信息化</h2>
      <ul class="block-info">
        <li>
                    <div class="clearfix">
            <div class="pic-thumb fl">
              <img src="resources/images/main/app-icon.jpg" alt="" />
            </div>
            <div class="bfc">
              <h3><a href="public/information_app">移动APP信息化建设</a></h3>
              <p>提供企业app、行业app定制开发服务。支持原生及框架型应用开发，可覆盖iPhone、iPad、Android手机平板等终端设备</p>
              <a href="public/information_app" class="btn-button">查看详情</a>
            </div>
          </div>
        </li>
        <li>
                <div class="clearfix">
        <div class="pic-thumb fl">
              <img src="resources/images/main/zh-icon.jpg" alt="" />
            </div>
            <div class="bfc">
              <h3><a href="public/information_exhibition">展会通</a></h3>
              <p>企业去参加展会无法带更多的产品展览,有了展会通不必在为此事烦恼,存储海量产品到便携的移动设备,能与目标用户产生更好，更有趣味的互动</p>
              <a href="public/information_exhibition" class="btn-button">查看详情</a>
            </div>
      </div>
        </li>
                <li>
                <div class="clearfix">
        <div class="pic-thumb fl">
              <img src="resources/images/main/weixin-icon.jpg" alt="" />
            </div>
            <div class="bfc">
              <h3><a href="public/information_weixin">微信公共账号信息化建设</a></h3>
              <p>通过微信公众平台，将企业品牌展示给上亿微信用户，减少宣传成本，建立企业与消费者、客户一对一的互动和沟通。</p>
              <a href="public/information_weixin" class="btn-button">查看详情</a>
            </div>
      </div>
        </li>
      </ul>
    </div>    
</div>

<!-- /wrap -->
<jsp:include page="../layout/footer.jsp" flush="true" />
<script src="resources/js/main/e-slider.js/"></script>
</body>
</html>