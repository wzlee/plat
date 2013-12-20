<%@page import="com.eaglec.plat.utils.StrUtils"%>
<%@page import="com.eaglec.plat.domain.policy.Policy"%>
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
<link type="text/css" rel="stylesheet" href="resources/css/main/droptime.css" />
<script type="text/javascript" src="resources/js/public/change_region.js"></script>
</head>
<body>
<jsp:include page="../layout/head.jsp" flush="true" />
	<div class="wrap mt2 clearfix">
		<div class="policy-banner"><img src="resources/images/main/policy-banner.jpg" alt="banner" /></div>
		<div class="policy-apply">
			<h3>请根据指引填写项目，查看企业可申请的资金资助项目</h3>
			<p>(以下皆为选填项，项目填写越多结果越精确)</p>
			<form action="" class="policy-form" name="form1">
				<ul class="policy-c">
					<li><label for="">企业成立时间：</label>
					<input type="text" id="date-input" placeholder="时间" class="time ud" name="time" value="${param.time }"/></li>
					<li class="drops-select"><label for="">注册地点：</label>
						<div class="address">
							<div style="display: none;">
								<select name="country" id="country">
									<option value="China">中国</option>
									<script language="javascript">
										set_select_options(
											window.document.form1.country,
											country, "China");
				   					 </script>
								</select>
							</div>
							<div class="fu">
								<select name="state" class="selects"
									onChange="change_region(window.document.form1.addr , window.document.form1.state.options[selectedIndex].value );">
									<option value="Not set" selected>-- 请选择省--</option>
									<script>
										change_region(
											window.document.form1.state,
											window.document.form1.country.options[2].value);
									 </script>
								</select>
							</div>
							<select name="addr" id="addr" class="selects">
								<option value="" selected>-- 请选择市 --</option>
							</select>
						</div>
					</li>
					<li class="drops-select"><label for="">所属行业：</label>
							<select name="enterprise" class="selects entery">
							<option value="0">==请选择==</option>
							<option value="301">进出口外贸</option>
							<option value="302">互联网产业</option>
							<option value="303">新能源产业</option>
							<option value="304">新材料产业</option>
							<option value="305">生物产业</option>
							<option value="306">新一代信息技术产业</option>
							<option value="307">会展业</option>
							<option value="308">军工配套等新兴产业</option>
							<option value="308">其他</option>
					</select>		
					</li>
					<li><label for="">上年度主营业务收入：</label><input type="text" class="" value="${param.businessIncome }" name="businessIncome" onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))"/><em>万元</em></li>
					<li><label for="">上年度销售总收入：</label><input type="text" class="" value="${param.totalIncome }" name="totalIncome"  onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" /><em>万元</em></li>
					<li><label for="">&nbsp;</label><input type="submit" class="submit" value="查看结果" /><a href="/policy/reportingIndex">查看全部资助项目</a></li>
				</ul>
			</form>
		</div>
		<div class="apply-result">
			<div class="result-con">
				<strong class="title">您所在的企业目前可申请的资金资助项目如下</strong>
				<div class="policy-list">
				<c:forEach items="${reportingViewList }" var="item">
					<c:if test="${item.infoList.size()>0 }">
					<table class="policy-table">
						<!-- 二级类别 -->
						<caption>${item.policyCategory.text }</caption>
						<c:forEach items="${item.infoList }" var="infoItem">
							<c:if test="${infoItem.financialReportings.size()>0 }">
							<tr class="sub-tr">
								<td colspan="2">${infoItem.policyCategory.text }
								</td>
							</tr>
							<c:forEach items="${infoItem.financialReportings }"
								var="reporting">
							<tr>
							<!-- 可申请  time-apply-->
							<!-- 即将到期  time-apply--timeover-->
									<td class="one"><%
										//结束时间
										Policy reporting = (Policy)pageContext.getAttribute("reporting");
										if (reporting != null&&null!=reporting.getOverTime()) {
											String end = reporting.getOverTime();
											long time = StrUtils.subTime(end);
											if (time<0) {
										%>
											
											<span class="time-apply-no"  title="不可申请"></span>
										<%
											}else if(0<time&&time<=7){
										%>
												<span class="timeover"  title="即将到期"></span>
										<%	
											}else{
										%>
											 <span class="time-apply"  title="可申请"></span>
										<%
											}
										}else if(null==reporting.getOverTime()){
											%>
											 <span class="time-apply"  title="可申请"></span>
											<%
										}
										%> <a href="policy/detail?id=${reporting.id }">${reporting.title }</a>
										
									<td class="four"><a href="policy/reportingInfo?id=${reporting.id }" class="q-link">资助导航</a> <a
										class="q-link">立即申请</a></td>
							</tr>
							</c:forEach>
							</c:if>
						</c:forEach>
					</table>
					</c:if>
				</c:forEach>
				</div>	
			</div>
		</div>
	</div>
<jsp:include page="../layout/footer.jsp" flush="true" />
<script type="text/javascript" src="resources/js/main/droptime.js"></script>
</body>
</html>