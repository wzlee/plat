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
<title>园区物业管理系统-企业信息化品中心</title>
<link type="text/css" rel="stylesheet" href="resources/css/main/e-information.css" />
</head>
<!-- 企业信息化频道首页 -->
<body>
<jsp:include page="../layout/head.jsp" flush="true" />
<!-- /header -->
<div class="wrap clearfix">
<div class="crumb-nav"><a href="./">首页</a>&nbsp;>&nbsp;<a href="/public/information">企业信息化</a>&nbsp;>&nbsp;<span class="current">园区物业管理系统</span></div>
  <div class="qi-banner">
      <img src="resources/images/main/e-banner-plan.jpg" alt="" />
  </div>
<!-- /banner -->
  <h2 class="mt2">园区物业管理系统</h2>
  <ul id="tabs" class="clearfix">
    <li class="active">系统架构图</li>
    <li>详细功能描述</li>
  </ul>
  <div id="tab-content">
      <div class="hide" style="display:block;background: #fff;">
        <img src="resources/images/main/wuye.png" alt="" />
      </div>
      <div class="hide">
        <table class="qi-table">
         
          <tr>
            <td rowspan="6" class="title">园区官网</td>
            <td class="col">首页</td>
            <td>聚焦园区活动、园区重大事情、新闻，园区物业等。</td>
          </tr>
          <tr>
            <td>园区介绍</td>
            <td>介绍园区面积、户型、综合环境、政府与政策等</td>
          </tr>
          <tr>
            <td>新闻资讯</td>
            <td>管理园区的资讯、企业动态、举办的活动等。</td>
          </tr>
          <tr>
            <td>园区服务</td>
            <td>园区服务内容介绍</td>
          </tr>
          <tr>
            <td>企业服务中心</td>
            <td>点击进处“企业服务中心”，实现自助服务。</td>
          </tr>
           <tr>
            <td>联系我们</td>
            <td>联系园区，交流导航方式</td>
          </tr>
        </table>
        <table class="qi-table">
          <tr>
            <td rowspan="4" class="title">招商租售</td>
            <td class="col">产品管理</td>
            <td>管理园区的物业户型、物业类别、物业户型添加、修改、删除等。</td>
          </tr>
          <tr>
            <td>销售工具</td>
            <td>招商人员用到的短信、联系人、汇报等常规工具。</td>
          </tr>
          <tr>
            <td>数据报表</td>
            <td>分析招商成果、园区企业流失、招商企业来源等</td>
          </tr>
          <tr>
            <td>销售视图</td>
            <td>根据后台设置的销售流程，分析招商流程销售视图、效果。</td>
          </tr>
         
        </table>
        <table class="qi-table">
          <tr>
            <td rowspan="4" class="title">财务缴费</td>
            <td class="col">合同管理</td>
            <td>合同编号管理、合同结转、终止、修改等财务专用功能。</td>
          </tr>
          <tr>
            <td>押金账单管理</td>
            <td>对应收账款、账单、押金管理，同时生成每个月物业、租金账单。</td>
          </tr>
          <tr>
            <td>缴费收款管理</td>
            <td>负责每月收缴租金、物业费用，录入老合同，历史账单。</td>
          </tr>
          <tr>
            <td>统计报表</td>
            <td>统计收费情况，客户情况，流失情况</td>
          </tr>
         
        </table>
        <table class="qi-table">
          <tr>
            <td rowspan="3" class="title">物业服务</td>
            <td class="col">数据采集</td>
            <td>采集每月水电表数据，自动生成每月物业、租金账单。</td>
          </tr>
          <tr>
            <td>客户管理</td>
            <td>管理客户物业服务工单情况，投诉、建议情况等</td>
          </tr>
          <tr>
            <td>资讯中心</td>
            <td>发布物业通知公告等，用户在企业用户中心自动查阅。</td>
          </tr>

         
        </table>
        <table class="qi-table">
          <tr>
            <td rowspan="3" class="title">数据分析</td>
            <td class="col">企业评价</td>
            <td>评价园区企业，服务日志，追踪企业服务记录等。</td>
          </tr>
          <tr>
            <td>统计报表</td>
            <td>统计园区企业稳定性、流失率、营业数据、资质情况。</td>
          </tr>
          <tr>
            <td>用户中心</td>
            <td>企业管理基本信息、密码、在线付款等。</td>
          </tr>

         
        </table>                
      </div>
  </div>
</div>
<jsp:include page="../layout/footer.jsp" flush="true" />
<!-- /footer -->
<script type="text/javascript">
  $(document).ready(function() {
      $('#tabs li').click(function(){
        $(this).addClass('active').siblings('li').removeClass('active');
        var act = $('#tabs li').index(this);
        $('#tab-content .hide').eq(act).show().siblings('.hide').hide();
      })
  });
</script>
</body>
</html>