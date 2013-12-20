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
	<link type="text/css" rel="stylesheet" href="resources/css/ucenter/page.css" />
</head>

<body>
	<jsp:include page="layout/head.jsp" flush="true"/>
<div class="wrap">
<div class="crumb-nav">首页 &gt;  <span class="current">平台介绍</span></div>
<div class="banner"><img src="resources/images/ucenter/banner.jpg" width="960" height="288" /></div>
<div class="main-container">
    <ul class="sidebar">
      <li><a href="/about">关于平台</a></li>
      <li><a href="/target">平台目标</a></li>
      <li><a href="">平台文化</a></li>
      <li><a href="">平台动态</a></li>
      <li><a href="">人才招聘</a></li>
      <li><a href="">广告服务</a></li>
      <li class="current"><a href="/contact">联系我们</a></li>
    </ul>
    <div class="main-column">
      <h3 class="summary">联系我们</h3>
      <div class="article">
        <h4 class="company-name">深圳市中小企业公共平台</h4>
        <table class="grid">
          <tr>
            <td width="92">办公地址：</td>
            <td>深圳市南山区科技园高新中二道生产力大楼D栋1楼</td>
          </tr>
          <tr>
            <td>邮政编码：</td>
            <td>518027</td>
          </tr>
          <tr>
            <td>联系电话：</td>
            <td>+86(755)22662277</td>
          </tr>
          <tr>
            <td>售后服务热线：</td>
            <td>22662277</td>
          </tr>
          <tr>
            <td>传真：</td>
            <td>+86(755)22662277</td>
          </tr>
          <tr>
            <td valign="top">地图：</td>
            <td>
            <iframe width="425" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com.hk/maps?f=q&amp;source=s_q&amp;hl=zh-CN&amp;geocode=&amp;q=%E6%B7%B1%E5%9C%B3%E7%94%9F%E4%BA%A7%E5%8A%9B%E5%A4%A7%E6%A5%BC&amp;aq=&amp;sll=22.545257,113.944051&amp;sspn=0.182958,0.338173&amp;brcurrent=3,0x3403e2eda332980f:0xf08ab3badbeac97c,0&amp;ie=UTF8&amp;hq=%E7%94%9F%E4%BA%A7%E5%8A%9B%E5%A4%A7%E6%A5%BC&amp;hnear=%E4%B8%AD%E5%9B%BD%E5%B9%BF%E4%B8%9C%E7%9C%81%E6%B7%B1%E5%9C%B3&amp;t=m&amp;z=13&amp;iwloc=A&amp;cid=14516045516133651275&amp;ll=22.545677,113.943356&amp;output=embed"></iframe><br /><small><a href="https://maps.google.com.hk/maps?f=q&amp;source=embed&amp;hl=zh-CN&amp;geocode=&amp;q=%E6%B7%B1%E5%9C%B3%E7%94%9F%E4%BA%A7%E5%8A%9B%E5%A4%A7%E6%A5%BC&amp;aq=&amp;sll=22.545257,113.944051&amp;sspn=0.182958,0.338173&amp;brcurrent=3,0x3403e2eda332980f:0xf08ab3badbeac97c,0&amp;ie=UTF8&amp;hq=%E7%94%9F%E4%BA%A7%E5%8A%9B%E5%A4%A7%E6%A5%BC&amp;hnear=%E4%B8%AD%E5%9B%BD%E5%B9%BF%E4%B8%9C%E7%9C%81%E6%B7%B1%E5%9C%B3&amp;t=m&amp;z=13&amp;iwloc=A&amp;cid=14516045516133651275&amp;ll=22.545677,113.943356" style="color:#0000FF;text-align:left">查看大图</a></small>
			</td>
          </tr>
        </table>
      </div>
    </div>
    <div class="clearer"></div>
</div>
<!-- /main-container -->
</div>
<!-- /wrap -->
	<jsp:include page="layout/footer.jsp" flush="true"/>
</body>
</html>