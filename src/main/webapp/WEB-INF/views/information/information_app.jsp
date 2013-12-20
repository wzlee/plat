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
<title>移动APP信息化建设-企业信息化</title>
<link type="text/css" rel="stylesheet" href="resources/css/main/e-information.css" />
</head>
<!-- 移动APP信息化建设 -->
<body>
<jsp:include page="../layout/head.jsp" flush="true" />
<!-- /header -->
<!-- /banner -->
<div class="wrap clearfix">
<div class="crumb-nav"><a href="./">首页</a>&nbsp;>&nbsp;<a href="/public/information">企业信息化</a>&nbsp;>&nbsp;<span class="current">移动APP信息化建设</span></div>
    <div class="qi-et clearfix">
     
      <div class="pic fl">
        <img src="resources/images/main/app2-icon.jpg" alt="" />
      </div>
      <div class="bfc et">
      <h3>什么是APP?</h3>
        <p>
App是英文Application的简称，由于iPhone智能手机的流行而变成一个约定俗成的词汇。APP，现在多指第三方智能手机的应用程序。目前比较著名的App商店有Apple的IOS操作系统为代表的iTunes商店里面的App Store，以android操作系统为代表的Google Play Store，以WP8操作系统为代表的Windows Phone Marketplace。
@nywhere系统是爱特公司独立研发的移动终端应用综合营销工具，能为品牌商户建立一整套专业的移动终端平台营销方案，可以让客户在其便携终端上迅速获得商家的相关业务及宣传信息，并通过触摸屏快捷联系到您.
</p>
      </div>
    </div>
    <div class="app-banner mt2">
      <img src="resources/images/main/e-banner-app.jpg" alt="" />
    </div>
    <div class="qi-et mt2 clearfix">
      <h2>服务流程</h2>
        <ul class="service-step">
            <li class="clearfix">
              <div class="pic-s fl"><img src="resources/images/main/cbgt-icon.jpg" alt="" /></div>
              <div class="bfc">
                <h4>初步沟通</h4>
                <p>双方初步沟通,确定服务项目</p>
              </div>
            </li>
            <li class="clearfix">
              <div class="pic-s fl"><img src="resources/images/main/xmpg-icon.jpg" alt="" /></div>
              <div class="bfc">
                <h4>评估项目</h4>
                <p>我方根据项目难度和大小情况确定费用</p>
              </div>
            </li>
            <li class="clearfix">
              <div class="pic-s fl"><img src="resources/images/main/qdht-icon.jpg" alt="" /></div>
              <div class="bfc">
                <h4>签订合同</h4>
                <p>签订合同,支付项目预付款</p>
              </div>
            </li>
            <li class="clearfix">
              <div class="pic-s fl"><img src="resources/images/main/yxsj-icon.jpg" alt="" /></div>
              <div class="bfc">
                <h4>原型设计</h4>
                <p>设计产品原型，供客户参考</p>
              </div>
            </li>
            <li class="clearfix">
              <div class="pic-s fl"><img src="resources/images/main/kfjd-icon.jpg" alt="" /></div>
              <div class="bfc">
                <h4>开发阶段</h4>
                <p>技术人员根据确认的设计原型,进行app开发</p>
              </div>
            </li>
            <li class="clearfix">
              <div class="pic-s fl"><img src="resources/images/main/xmsx-icon.jpg" alt="" /></div>
              <div class="bfc">
                <h4>项目上线</h4>
                <p>我方把项目文件上线,并在各应用商店上线  </p>
              </div>
            </li>
            <li class="clearfix">
              <div class="pic-s fl"><img src="resources/images/main/xhfw-icon.jpg" alt="" /></div>
              <div class="bfc">
                <h4>售后服务</h4>
                <p>基本技术支持,服务器运维,软件升级等.</p>
              </div>
            </li>
        </ul>

    </div>
    <div class="qi-et mt2">
      <h2>为何选择我们</h2>
        <ul class="block-info">
        <li>
                    <div class="clearfix">
            <div class="pic-thumb fl">
              <img src="resources/images/main/chooes-icon.jpg" alt="" />
            </div>
            <div class="bfc">
              <h3>我们提供的是解决方案</h3>
              

<strong>开发服务:</strong>双系统开发/后台开发/APP制作/辅助工具 <br />

<strong>下载服务:</strong>二维码下载/应用商店发布/百科发布<br />

<strong>运营服务:</strong>交付与培训/实施指导/实施报告/升级服务/运营报告/软件支持/硬件支持/维护服务
            </div>
          </div>
        </li>
        <li>
                <div class="clearfix">
        <div class="pic-thumb fl">
              <img src="resources/images/main/specialty-icon.jpg" alt="" />
            </div>
            <div class="bfc">
              <h3>我们的专业团队</h3>
              

<strong>售前:</strong>

销售代表,销售总监 项目经理<br />

<strong>产品部:</strong>

产品经理 ui设计师 ue设计师 产品总监<br />

<strong>开发部:</strong>

软件工程师,技术总监,测试工程师 上架专员

            </div>
      </div>
        </li>
      </ul>
    </div>
</div>
<jsp:include page="../layout/footer.jsp" flush="true" />
</body>
</html>