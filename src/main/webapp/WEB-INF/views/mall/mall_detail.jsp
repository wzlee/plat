<%@page import="com.eaglec.plat.utils.Common"%>
<%@page import="com.eaglec.plat.domain.service.Service"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	//得到服务评价四舍五入
	Service service = (Service)request.getAttribute("service");
	int score = Common.round(service.getEvaluateScore()).intValue();
	request.setAttribute("evaluationScore", score);
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/main/detail.css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/company.css" />
</head>
<body>
<c:set value='<%=request.getSession().getAttribute("user")%>' var="msg"/>
<jsp:include page="../layout/head.jsp" flush="true"/>
<!-- header end -->
<div class="detail-wrap">
  <div class="crumb-nav">
  <c:if test="${pmcategory == null }"><strong>服务商城</strong>&gt; ${mcategory.text }</c:if>
  <c:if test="${pmcategory != null }">
  	<strong>服务商城</strong> &gt;${pmcategory.text } &gt; ${mcategory.text }</div>
  </c:if>
  <div class="detail-container">
    <div class="column-l">
      <div class="thumb">
      		<c:if test="${service.picture.contains('http')}">
				<img class="detailimg" src="${service.picture}" width="232" height="190" title="${service.serviceName }" onerror="nofind();" />
			</c:if>
			<c:if test="${!service.picture.contains('http')}">
				<img class="detailimg" src="upload/${service.picture}" width="232" height="190" title="${service.serviceName }" onerror="nofind();" />
			</c:if>     	 
      </div>
      <ul class="choose-btn">
      	<c:choose>
     		<c:when test="${collected == true}">
     			<li class="s1"><a class="active" href="javascript:void(0);" onclick="collect(this, ${service.id})">已收藏</a></li>
     		</c:when>
     		<c:otherwise>
     			<li class="s1"><a href="javascript:void(0);" onclick="collect(this, ${service.id})">收藏</a></li>
     		</c:otherwise>
	    </c:choose>
      	<li class="s2"><a href="javascript:void(0);" onclick="share();">分享</a></li>
      </ul>
    <div class="column-company-l">
  	  <div class="company-info">
  	   <input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${service.enterprise.id }"/> 
  	   <input type="hidden" id="serviceEnterId" name="serviceEnterId" value="${service.enterprise.id }"/> 
				<h3 class="panel-head">服务机构名片</h3>
				<div class="panel-body">
					<div class="top">
						<h4>${service.enterprise.name }</h4>
						<div class="row">
				            <div class="is-cer">
				            	<!-- 1普通企业；2认证企业;3服务机构；4政府机构；  -->
				            	<c:choose>
				            		<c:when test="${service.enterprise.type eq '3' }">服务机构</c:when>
				            		<c:when test="${service.enterprise.type eq '4' }">政府机构</c:when>
				            	</c:choose>
				            </div>
<!-- 				            <div class="online-consultation"><a href="javascript:void(0);">在线咨询</a></div> -->
	        			</div>
	        			<div class="clearer"></div>
					</div>
					<ul class="company-meta">
			          <li><span class="meta-tit">经营模式：</span>
			          <!-- businessPattern -->
			          <!-- 1生产型；2贸易型；3服务性；4生产性、贸易型；5贸易型、服务型；6生产性、贸易型、服务性； -->
			          	<span class="meta-info">
			          		<c:choose>
			            		<c:when test="${service.enterprise.businessPattern eq '1' }">生产型</c:when>
			            		<c:when test="${service.enterprise.businessPattern eq '2' }">贸易型</c:when>
			            		<c:when test="${service.enterprise.businessPattern eq '3' }">服务型</c:when>
			            		<c:when test="${service.enterprise.businessPattern eq '4' }">生产型、贸易型</c:when>
			            		<c:when test="${service.enterprise.businessPattern eq '5' }">贸易型、服务型</c:when>
			            		<c:when test="${service.enterprise.businessPattern eq '6' }">生产型、贸易型、服务型</c:when>
				            </c:choose> 
			          	</span>
			          </li>
			          <li><span class="meta-tit">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</span><span class="meta-info">${service.enterprise.tel }</span></li>
			          <li><span class="meta-tit">联&nbsp;&nbsp;系&nbsp;&nbsp;人：</span><span class="meta-info">${service.enterprise.linkman }</span></li>
			          <li><span class="meta-tit">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</span><span class="meta-info">${service.enterprise.address}</span></li>
			        </ul>
 			        <div class="follow"><a href="javascript:void(0);"></a></div> 
				</div>
		</div>
		</div>
    </div>
    <div class="column-r">
      <div class="detail-hd">
      <h3 class="detail-title">${service.serviceName }</h3>
      <ul class="s-meta">
        <li><span class="meta-tit">服务费用：</span>
        	<c:choose>
        		<c:when test="${service.costPrice == null or service.costPrice == 0}"><span class="meta-price">面议</span></c:when>
	        	<c:otherwise>
	        		<span class="meta-yuan">￥</span><span class="meta-price">${service.costPrice }</span>
	        	</c:otherwise>
        	</c:choose> 
        </li>
        <li><span class="meta-tit">评价：</span>
        	<c:choose>
        		<c:when test="${evaluationScore == 0 }"><span class="meta-star level-0"></span></c:when>
        		<c:when test="${evaluationScore == 1 }"><span class="meta-star level-1"></span></c:when>
        		<c:when test="${evaluationScore == 2 }"><span class="meta-star level-2"></span></c:when>
        		<c:when test="${evaluationScore == 3 }"><span class="meta-star level-3"></span></c:when>
        		<c:when test="${evaluationScore == 4 }"><span class="meta-star level-4"></span></c:when>
        		<c:when test="${evaluationScore == 5 }"><span class="meta-star level-5"></span></c:when>
        	</c:choose>
        </li>
        <li><span class="meta-tit">已申请：</span><span class="meta-num">${service.serviceNum == 0 ? '暂无申请':service.serviceNum}</span></li>
        <li><span class="meta-tit">服务机构：</span><a href="javascript:void(0);"><span class="meta-com">${service.enterprise.name}</span></a></li>
      </ul>
  	<div class="s-action">
	  	<c:if test="${service.currentStatus == 3 }">
	      	<a class="apply-btn" id="apply-btn" href="javascript:void(0);" data-sid=${service.id } data-sname=${service.serviceName } data-sprice=${service.costPrice == null ? '面议':service.costPrice  }
				data-tel=${loginEnterprise.tel == null ? '暂无':loginEnterprise.tel } 
				data-email=${loginEnterprise.email == null ? '暂无': loginEnterprise.email}>申请服务
			</a>
		</c:if>
		<c:if test="${service.currentStatus == 4 }">
			<a class="apply-btn none" href="javascript:void(0);" >变更审核中</a>
		</c:if>
		<c:if test="${service.currentStatus == 7 }">
			<a class="apply-btn none"  href="javascript:void(0);" >下架审核中</a>
		</c:if>
   	</div>
    </div>
      <div class="detail-content">
        <h4 class="intro-title">服务介绍</h4>
        <div class="inro-content">
       		${service.serviceProcedure }
        </div>
      </div>
    </div>
    <div class="clearer"></div>
  </div>
</div>
<!-- 申请窗口 -->	
<div class="apply-html hide">
	<form class="apply-form">
	   <table width="350" class="apply-data">
	      <tr>
	      	<td colspan="3">
	      		<div class="apply-message">请您确认以下信息，并确保信息的真实性，服务申请提交成功后，我们将与您线下联系具体事宜。</div>
			</td>
	      </tr>
	   	  <input class="apply-sid" name="sid" type="hidden">
	      <tr><td valign="top" width="108" height="36">申请的服务：</td><td valign="top" class="sname"></td></tr>
	      <tr><td  valign="top" width="108" height="36">价格：</td><td valign="top" class="sprice"></td></tr>
	      <tr>
	      	<td valign="top" height="56">联系人姓名：</td>
	      	<td valign="top">
	      		<input class="link-name" name="linker" type="text" value="${msg.userName }">
	      		<p class="help-inline apply-name-info" data-label="联系人"></p>
	      	</td>
	      </tr>
	      <tr>
	      	<td valign="top" height="56">联系电话：</td>
	      	<td valign="top" class="linkTel">
	      		<!-- <span class="tel"></span> -->
	      		<input class="link-tel" name="linkTel" type="text" value="${loginEnterprise.tel == null ? '':loginEnterprise.tel }">
	      		<p  class="help-inline apply-tel-info" data-label="联系电话" ></p>
	      	</td>
	      <!-- 	<td style="width:40px"><a onclick="updateTel();" class="updateTel" href="javascript:void(0);">修改</a></td>	 -->
	      </tr>
	      <tr>
	      	<td valign="top" height="56">邮箱：</td>
	      	<td valign="top" class="linkMail"> 	 
	      		<input class="link-mail" name="linkMail" type="text" value="${loginEnterprise == null ? '':loginEnterprise.email }">
	      		<p class="help-inline apply-email-info" data-label="联系邮箱"></p>
	      	</td>
	      </tr>
	      <tr>
	      	<td valign="top" height="56">买家备注：</td>
	      	<td valign="top" class="buyerRemark"> 	 
	      		<textarea class="buyer-remark" name="buyerRemark" style="width:210px;height:50px;"></textarea>
	      		<p class="help-inline apply-remark-info" data-label="买家备注"></p>
	      	</td>
	      </tr>
	   </table>
  	</form>
</div>
<!-- 服务确认提交窗口 -->
<div class="apply-html hide">
	<form class="submit-form">
	<table width="350" class="apply-data">
		<div class="dia-success">
			<p>服务申请已提交！</p>
			<p>您可在<a href="ucenter/buyer_order?op=3"  target="_blank">企业管理用户中心</a>中查看已申请服务订单的状态。</p>
			<p>&nbsp;</p>
			<p align="center"><button onclick="closeApply();" type="button" class="dia-button">关闭</button></p>
		</div>
	</table>
  	</form>
  	<input type="hidden" name="search_index" id="search_index" value="${search_index }" /> 
</div>
<!--/detail-wrap -->
<jsp:include page="../layout/footer.jsp" flush="true"/>
<script type="text/javascript" src="resources/js/public/applyService.js"></script>
<script type="text/javascript" src="resources/js/main/collectService.js"></script>
<script type="text/javascript">
	serviceApply();
</script>
</body>
</html>

