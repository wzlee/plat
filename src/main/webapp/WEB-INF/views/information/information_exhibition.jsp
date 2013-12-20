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
<title>展会通-企业信息化品中心</title>
<link type="text/css" rel="stylesheet" href="resources/css/main/e-information.css" />
</head>
<!-- 展会通 -->
<body>
<jsp:include page="../layout/head.jsp" flush="true" />
<!-- /header -->
<!-- /banner -->
<div class="wrap clearfix">
<div class="crumb-nav"><a href="./">首页</a>&nbsp;>&nbsp;<a href="/public/information">企业信息化</a>&nbsp;>&nbsp;<span class="current">展会通</span></div>

  <div class="ex-banner">
    <img src="resources/images/main/e-banner-show.jpg" alt="" />
  </div>

      <div class="qi-et mt2 clearfix">
      	  <div class="hero">
  			<h2>特色功能</h2>
  			</div>
      <ul class="block-info mt2">
        <li>
                    <div class="clearfix">
            <div class="pic-thumb fl">
              <img src="resources/images/main/gsjs-icon.jpg" alt="" />
            </div>
            <div class="bfc">
              <h3>公司介绍</h3>
              <p>公司的全面介绍，可以播放视频音频等多媒体、图文并茂，全方位展示公司的历程事纪。地图上展示公司位置等,提高互动性, 节约人力成本
</p>
            
            </div>
          </div>
        </li>
        <li>
                <div class="clearfix">
        <div class="pic-thumb fl">
              <img src="resources/images/main/cpjs-icon.jpg" alt="" />
            </div>
            <div class="bfc">
              <h3>展会产品</h3>
              <p>产品图片展示、多张图片互动展示、3D展示，可配音频解说。更换颜色、配置、模拟实景、切换场景。不限容量,灵活配置.
</p>
              
            </div>
      </div>
        </li>
                <li>
                <div class="clearfix">
        <div class="pic-thumb fl">
              <img src="resources/images/main/wjdc-icon.jpg" alt="" />
            </div>
            <div class="bfc">
              <h3>问卷调查</h3>
              <p>自助式问卷调查,可在后台配置问题,通过问卷收集客户最感兴趣的问题,和客户建议,以便改进产品.集成问卷调查可节约参加的人力成本.</p>
              
            </div>
      </div>
        </li>
                <li>
                <div class="clearfix">
        <div class="pic-thumb fl">
              <img src="resources/images/main/khtj-icon.jpg" alt="" />
            </div>
            <div class="bfc">
              <h3>统计客户</h3>
              <p>收集客户资料，可以在现场设置服务器联网，实现多台Pad同步操作，将用户资料提交到数据库，方便展会结束后公司管理和统计。</p>
              
            </div>
      </div>
        </li>
                <li>
                <div class="clearfix">
        <div class="pic-thumb fl">
              <img src="resources/images/main/cjhd-icon.jpg" alt="" />
            </div>
            <div class="bfc">
              <h3>抽奖活动</h3>
              <p>为了让更多用户参于此活动，可以设置抽奖。</p>
              
            </div>
      </div>
        </li>                
      </ul>
    </div>   
      <div class="qi-et mt2 clearfix">
      	  <div class="hero">
  			<h2>案例展示</h2>
 		 </div>
 		 <ul class="case-show mt2">
 		    <li>
 		    	<div class="show-img"><img src="resources/images/main/e-case-show.jpg" alt="" /></div>
 		    	<p>HIB医药展。展示公司信息和产品，有视频播放等功能。</p>
 		    </li>
 		    <li>
 		    	<div class="show-img"><img src="resources/images/main/e-case-show2.jpg" alt="" /></div>
 		    	<p>红酒展会，可以下订单。</p>
 		    </li>
 		    <li>
 		    	<div class="show-img"><img src="resources/images/main/e-case-show3.jpg" alt="" /></div>
 		    	<p>NSK汽车展，介绍公司和详细产品。问卷调查。</p>
 		    </li>
 		     
 		 </ul>    
 	 </div>    
</div>
<jsp:include page="../layout/footer.jsp" flush="true" />
</body>
</html>