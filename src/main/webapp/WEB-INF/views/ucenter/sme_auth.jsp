<%@page import="com.eaglec.plat.domain.base.User"%>
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
	<title>中小微企业认定-深圳市中小企业公共服务平台</title>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="resources/css/main/certification.css" />
	<link type="text/css" rel="stylesheet" href="resources/css/ucenter/authentication.css" />
	<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
	<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/demo.css" />
	
</head>
<body>
	<jsp:include page="../layout/head.jsp" flush="true"/>
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
	<div class="wrap">
		<div class="crumb-nav">首页 &gt;  <span class="current">中小微企业认定</span></div>
		<div class="main-container">
			<form id="sme_info" method="post">
				<input id="fileMap" name="fileMap" type="hidden"/>
				<div class="step-grounp">
					<div class="step-content" >
						<div class="step-1 step-details" style="display:block">
							<ul class="stepNav clearfix">
								<li class="one"><strong>说明</strong>中小微企业认定说明</li>
								<li class="current"><strong>第一步</strong>中小企业证明信息表</li>
								<li class="last"><strong>第二步</strong>上传证明材料</li>
							</ul>
							<div class="info-rending">
								<div class="text">
									<p class="blockquote">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为支持我市中小微企业拓展国内市场，享受国内市场竞标活动相关优惠政策，根据工业和信息化部、国家统计局、发展改革委、财政部2011年6月18日发布的《<a href="http://smemall.net/policy/detail?id=389" target="_blank" class="f-purple">关于印发中小企业划型标准规定的通知</a>》（工信部联企业[2011]300号），<a href="http://www.szsmb.gov.cn/" target="_blank" class="f-purple">深圳市中小企业服务中心</a>对企业进行审核划型，并出具相关证明。
									</p>
									
									<img src="resources/images/main/biao3.jpg" class="biao3" />
								</div>
							</div>
							<div class="step-submit">
								<%
								User user = (User)request.getSession().getAttribute("user");
								if( user!= null){%>
									<a class="next1" href="javascript:void(0);">下一步</a>
								
								<%
								}else{%>
									<div class="un-login">
									<p>您还未登录，请登录后进行下一步操作 </p>
									<a class="" href="http://smemall.net/login">登录</a>&nbsp;&nbsp;<a class="reg-now" href="http://smemall.net/signup">免费注册</a>
									</div>
								<%
								}
							%>
			      			</div>
						</div>
						<div class="step-2 step-details" >
							<ul class="stepNav clearfix">
								<li class="one"><strong>说明</strong>中小微企业认定说明</li>
								<li class="current"><strong>第一步</strong>中小企业证明信息表</li>
								<li class="last"><strong>第二步</strong>上传证明材料</li>
							</ul>
							<h2>办理中小企业证明信息表</h2>
							<table class="table-cert" id="">
								
									<input type="hidden" name="id" class="large"  value="${identifie.id }" />
									<input type="hidden" name="user.id" class="large"   value="${identifie.user.id }"/>
									<c:if test="${manager!=null }">
										<input type="hidden" name="manager.id" class="large"  value="${identifie.manager.id }"/>
									</c:if>
							
								<tr>
									<td class="td-one">公司名称<em class="mark">*</em></td>
									<td colspan="3"><input type="text" id="companyName" name="companyName"   value="${identifie.companyName }" class="large"  /></td>
								</tr>
								<tr>
									<td>成立日期</td>
									<td colspan="3"><input type="text" class="easyui-datebox" name="registerDate"  value="${identifie.registerDate }" class="large" /></td>
								</tr>
								<tr>
									<td>公司地址<em class="mark">*</em></td>
									<td colspan="3"><input type="text" name="companyAddress" id="companyAddress" value="${identifie.companyAddress }" class="large"   /></td>
									
								</tr>
								<tr>
									<td>邮政编码</td>
									<td colspan="3"><input type="text" name="postcode"   value="${identifie.postcode }" class="large" /></td>
								</tr>
								<tr>
								<td>公司负责人<em class="mark">*</em></td>
								<td class="pos-r"><input type="text" name="responsiblePerson" id="responsiblePerson"  value="${identifie.responsiblePerson }"/></td>
								<td>职务</td>
								<td><input type="text" name="responsiblePosition" value="${identifie.responsiblePosition }" class="" /></td>
									
								</tr>
								<tr>
									<td class="">电话</td>
									<td class=""><input type="text" name="responsiblePhone" value="${identifie.responsiblePhone }"  class="large-m" /></td>
									<td class="">手机</td>
									<td class=""><input type="text" name="responsibleMobile" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${identifie.responsibleMobile }"  class="large-l" /></td>
								</tr>
								<tr>
									<td>公司联系人<em class="mark">*</em></td>
									<td><input type="text" name="contactPserson" id="contactPserson" value="${identifie.contactPserson }"   /></td>
									<td>传真</td>
									<td><input type="text" name="contactFax" value="${identifie.contactFax }"  class="large-m" /></td>
									
								</tr>
								<tr>
									<td>手机</td>
									<td><input type="text" name="contactMobile" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${identifie.contactMobile }"  class="large-m" /></td>
									<td>邮箱</td>
									<td><input type="text" name="contactEmail" value="${identifie.contactEmail }"  class="large-l" /></td>
								</tr>
								<tr>
									<td>其他联系人</td>
									<td colspan="3"><input type="text" name="otherContactPerson" value="${identifie.otherContactPerson }"  class="large" /></td>
									
								</tr>
								<tr>
									<td>主营业务产品<em class="mark">*</em></td>
									<td colspan="3"><textarea name="mainBusinessProducts" id="mainBusinessProducts"  value="${identifie.mainBusinessProducts }" ></textarea></td>
									
								</tr>
								<tr>
									<td>已取得资质</td>
									<td colspan="3"><textarea name="qualification" id="" value="${identifie.qualification }" ></textarea></td>
									
								</tr>
								<tr>
									<td>企业规模简介</td>
									<td colspan="3"><textarea name="scaleIntroduction" id="" value="${identifie.scaleIntroduction }" ></textarea></td>
									
								</tr>										
								<tr>
									<td>办理中小型企业信息类型</td>
									<td colspan="3"><input type="text" name="documenType" class="large" value="${identifie.documenType }" /></td>
									
								</tr>
								<tr>
									<td>办证日期</td>
									<td colspan="3"><input type="text" name="rushDate" class="easyui-datebox" value="${identifie.rushDate }" /></td>
								</tr>
								<tr>
									<td colspan="4"></td>
								</tr>
							</table>
							<div class="step-submit">
								<a class="prev1" href="javascript:void(0);">返回</a>
			      				<a class="next2" id="next2" href="javascript:void(0);">下一步</a>
			      			</div>
					
				</div>
			</form>
			<div class="step-3 step-details">
				<ul class="stepNav clearfix">
					<li class="one sec"><strong>说明</strong>中小微企业认定说明</li>
					<li class="one"><strong>第一步</strong>填写中小企业证明信息表</li>
					<li class="last current"><strong>第二步</strong>上传证明材料及装订顺序</li>
				</ul>
				<h2>办理中小企需提交资料及装订顺序</h2>
				<table class="table-cert up-step2">
					<tr>
						<td class="up-step">一、申请报告 <br /> 内容包括：企业成立日期，从事专业，所属行业类型、职工人数说明（缴纳社保人数、未缴纳社保人数说明）、取得的资质荣誉，
						 经营情况等，申请要求事项、企业联系人等信息。(<span>仅限word文档</span>)</td>
						<td>
							<div class="more-files">
								<button class="btn-upload" data-file="applicationReport">添加附件</button>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							二、企业营业执照，组织机构代码复印件
						</td>
						<td>
							<div class="more-files">
								<button class="btn-upload" data-file="copiesOfDocuments">添加附件</button>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							三、企业（包括分公司）上一个月社保缴费明细表（加盖社保局社保费缴纳清单证明章）；
						</td>
						<td>
							<div class="more-files">
								<button class="btn-upload" data-file="socialSecurityDetails">添加附件</button>
							</div>
				
						</td>
					</tr>
					<tr>
						<td>
							四、企业上年度审计报告复印件；
						</td>
						<td>
							<div class="more-files">
								<button class="btn-upload" data-file="copyOfTheAuditReport">添加附件</button>
							</div>
							
						</td>
					</tr>
					<tr>
						<td>
							五、其他证明企业资质或荣誉的证明文件；
						</td>
						<td>
							<div class="more-files">
								<button class="btn-upload" data-file="otherSupportingDocuments">添加附件</button>
							</div>
							
						</td>
					</tr>
					<tr>
						<td>
							六、深圳市中小微企业划型证明申请承诺书（企业盖章）（见附件）；(<span>仅限word文档</span>)
						</td>
						<td>
							<div class="more-files">
								<button class="btn-upload" data-file="applicationUndertaking">添加附件</button>
							</div>
							
						</td>
					</tr>
					<tr>
						<td>
							七、企业的投标文件（提供电子版）；
						</td>
						<td>
							<div class="more-files">
								<button class="btn-upload" data-file="tenderDocuments">添加附件</button>
							</div>
		
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<div class="beizhu">
						<span>注：</span><em>*</em>以上资料，验原件留复印件。<br /> 
						<em>*</em>申请报告内容需以word文档形式上传。<br />
						 <em>*</em>申请承诺书需下载附件填写完成后再上传。<br />
						 （办理期限：交材料后3－4个工作日）<br /><br />
						 附件：  <a href="">深圳市中小微企业划型证明申请承诺书</a>
						
					</div>
						</td>
					</tr>
				</table>
				<div class="step-upload">
					
					<div class="step-submit">
					<a class="previous prev2" href="javascript:void(0);">上一步</a>
		      		<a type="button" id="save" href="javascript:void(0);">提交</a>
					</div>
					<div class="clearer"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="overlay" id="apply_ok">
<div class="auth-status-alert">
	<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close" onclick="$('#apply_ok').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon success"></div>
		<div class="msg-box">
			<h2>提交成功！</h2>
		</div>
	</div>
	<div class="back-bar"><a href="sme/inden?op=8">返回订单管理</a></div>
</div>
</div>
<div class="overlay" id="apply_err">
<div class="auth-status-alert">
	<h2 class="title">提示</h2>
	<a href="javascript:void(0)" class="close" onclick="$('#apply_err').hide();"></a>
	<div class="auth-status-content">
		<div class="alert-icon error"></div>
		<div class="msg-box">
			<h2>提交失败！</h2>
			<p class="s1">详情请咨询技术客服。</p>
		</div>
	</div>
	<div class="back-bar"><a href="ucenter/buyer_order?op=8">返回订单管理</a></div>
</div>
</div>
<!-- /main-container -->
</div>
<form id="attachment_form" action="public/uploadAttachments" enctype="multipart/form-data" method="post"></form>
<!-- /wrap -->
	<jsp:include page="../layout/footer.jsp" flush="true"/>
	<script type="text/javascript" src="resources/js/public/sme.js"></script>
	<script type="text/javascript" src="jsLib/jquery.easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jsLib/jquery.easyui/locale/easyui-lang-zh_CN.js"></script>
</body>
</html>