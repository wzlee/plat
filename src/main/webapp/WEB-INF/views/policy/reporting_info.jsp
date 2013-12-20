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
<title>资助一键通-深圳市中小企业公共服务平台</title>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/main/common.css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/mall.css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/policy.css" />
</head>
<body>
<jsp:include page="../layout/head.jsp" flush="true" />
<div class="wrap clearfix">
		<div class="crumb-nav">
			<a href="">首页 </a>&gt; <a href="policy/reportingIndex"> 资助一键通</a>&gt;
			<span class="current">资助导航</span>
		</div>
		<div class="column-l">
			<div class="policy-side">
				<div class="title">
					<h2>资助导航</h2>
				</div>
				<dl id="policy-nav">
				<c:forEach items="${reportingViewList[0].infoList }" var="viewItem">
						<c:choose>
							<c:when test="${viewItem.policyCategory.id == currentCategory.id }">
								<dt class="current">
							</c:when>
							<c:otherwise>
								<dt>
							</c:otherwise>
						</c:choose>
						<h3 title="${viewItem.policyCategory.text}">
						<c:choose>
							<c:when test="${viewItem.policyCategory.text.length()<=14 }">
								${viewItem.policyCategory.text}
							</c:when>
							<c:otherwise>
								${fn:substring(my:replaceHTML(viewItem.policyCategory.text), 0, 14) }...
							</c:otherwise>
						</c:choose>
						</h3>	
						</dt>
						<dd>
						<ol>
							<c:forEach items="${viewItem.financialReportings }" var="item">
								<c:choose>
									<c:when test="${item.id ==currentReporting.id }">
										<li class="current">
									</c:when>
									<c:otherwise>
										<li >
									</c:otherwise>
									</c:choose>
										<a title="${item.title}" href="policy/reportingInfo?id=${item.id }">
											<c:choose>
												<c:when test="${item.title.length()<=14 }">
													>&nbsp;${item.title}
												</c:when>
												<c:otherwise>
													>&nbsp;${fn:substring(my:replaceHTML(item.title), 0, 14) }...
												</c:otherwise>
											</c:choose>
										</a></li>
							</c:forEach>
							</ol>
						</dd>	
				</c:forEach>			
			    </dl>				
			</div>
		</div>
		<div class="recommend-block" >
			<div class="policy-detail clearfix">
				<ul class="policy-tabs" id="policy-tabs">
					<li class="one current"><span>申报通知全文</span></li>
					<li><span class="title">申报资格</span></li>
					<li><span class="title">备案要求</span></li>
					<li><span class="title">所需材料</span></li>
					<li><span class="title">申报流程</span></li>
					<li  class="last"><span class="title">受理时限</span></li>
					
				</ul>
				<div class="policy-tabs-cont">
					<div class="tabs-d current">
						<p>${currentReporting.text }</p>
					</div>
					<div class="tabs-d">
					<!-- qualifications -->
						<p>${currentReporting.qualifications }</p>
					</div>
					<div class="tabs-d">
						<p>${currentReporting.material }</p>
					</div>
					<div class="tabs-d">
						${currentReporting.process }
					</div>
					<div class="tabs-d">
						${currentReporting.timeLimit }
					</div>
					<div class="tabs-d">
						${currentReporting.validity }
					</div>																				
				</div>
					<div class="clearer"></div>
					<a  class="link-now">立即申请</a>
			</div>
		</div>
</div>

<jsp:include page="../layout/footer.jsp" flush="true" />
<script type="text/javascript">
	$(function(){
		$('#policy-tabs > li').click(function() {
			$(this).addClass('current').siblings('li').removeClass('current');
			$('.policy-tabs-cont .tabs-d').eq($('#policy-tabs > li').index(this)).addClass('current').fadeIn(500).siblings().removeClass('current').hide();
		});
		//边栏导航 
		var $tf = $('#policy-nav').find('dt.current').index();
		$('#policy-nav dd').eq($tf/2).show();
			$('#policy-nav > dt').click(function(){
				$(this).addClass('current').siblings('dt').removeClass("current");
				 if(false == $(this).next().is(':visible')) {
		            $('#policy-nav > dd').slideUp(300);
		        }
		        $(this).next().slideToggle(300);
				return false;
			})
	})
</script>
</body>
</html>
