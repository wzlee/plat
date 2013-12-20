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
</head>

<body>
	<jsp:include page="layout/head.jsp" flush="true"/>
<div class="wrap">
<div class="crumb-nav">首页 &gt;  <span class="current">中小微企业认定</span></div>
<div class="main-container">
	<div class="step-grounp">
		<div class="step-content" >
			<div class="step-1 step-details" style="display:block">
				<ul class="stepNav clearfix">
					<li class="current"><strong>第一步</strong>中小企业证明信息表</li>
					<li><strong>第二步</strong>上传证明材料</li>
					<li class="last"><strong>第三步</strong>认定成功</li>
				</ul>
				<h2>办理中小企业证明信息表</h2>
				<table class="table-cert">
					<tr>
						<td class="td-one"> 公司名称</td>
						<td colspan="4"><input type="text" name="" class="large"  /></td>
						<td>成立日期</td>
						<td colspan="2"><input type="text" name=""  class="large-r" /></td>
					</tr>
					<tr>
						<td>公司地址</td>
						<td colspan="4"><input type="text" name="" class="large"   /></td>
						<td>邮政编码</td>
						<td colspan="2"><input type="text" name="" class="large-r" /></td>
					</tr>
					<tr>
						<td>公司负责人</td>
						<td class="td-two"><input type="text" name=""  /></td>
						<td class="td-three">职务</td>
						<td class="td-four"><input type="text" name="" class="large-m" /></td>
						<td class="td-five">电话</td>
						<td class="td-six"><input type="text" name="" class="large-m" /></td>
						<td class="td-seven">手机</td>
						<td class="td-eight"><input type="text" name="" class="large-l" /></td>
					</tr>
					<tr>
						<td>公司联系人</td>
						<td><input type="text" name=""  /></td>
						<td>传真</td>
						<td><input type="text" name=""  class="large-m" /></td>
						<td>手机</td>
						<td><input type="text" name=""  class="large-m" /></td>
						<td>邮箱</td>
						<td><input type="text" name=""  class="large-l" /></td>
					</tr>
					<tr>
						<td>其他联系人</td>
						<td colspan="7"><input type="text" name="" class="large" /></td>
						
					</tr>
					<tr>
						<td>主营业务产品</td>
						<td colspan="7"><textarea name="" id="" ></textarea></td>
						
					</tr>
					<tr>
						<td>已取得资质</td>
						<td colspan="7"><textarea name="" id="" ></textarea></td>
						
					</tr>
					<tr>
						<td>企业规模简介</td>
						<td colspan="7"><textarea name="" id="" ></textarea></td>
						
					</tr>										
					<tr>
						<td>办理中小型企业信息类型</td>
						<td colspan="4"><input type="text" name="" class="large" /></td>
						<td>办证日期</td>
						<td colspan="2"><input type="text" name="" class="large-r" /></td>
					</tr>
					<tr>
						<td colspan="8"></td>
					</tr>
				</table>
						<div class="step-submit">


      		<button type="submit" class="next1">下一步</button>
		</div>
			</div>
			<div class="step-2 step-details">
				<ul class="stepNav clearfix">
					<li class="one"><strong>第一步</strong>填写中小企业证明信息表</li>
					<li class="current"><strong>第二步</strong>上传证明材料及装订顺序</li>
					<li class="last"><strong>第三步</strong>认定成功</li>
				</ul>
				<h2>办理中小企需提交资料及装订顺序</h2>
				<div class="step-upload">
					<ol>
						<li>一、申请报告 <br />
						 内容包括：企业成立日期，从事专业，所属行业类型、职工人数说明（缴纳社保人数、未缴纳社保人数说明）、取得的资质荣誉，
						 经营情况等，申请要求事项、企业联系人等信息。(<span>仅限word文档</span>)<input type="file" name="" />
						 </li>
						<li>二、企业营业执照，组织机构代码复印件<input type="file" name="" /></li>
						<li>三、企业（包括分公司）上一个月社保缴费明细表（加盖社保局社保费缴纳清单证明章）；<input type="file" name="" /></li>
						<li>四、企业上年度审计报告复印件；<input type="file" name="" /></li>
						<li>五、其他证明企业资质或荣誉的证明文件；<input type="file" name="" /></li>
						<li>六、深圳市中小微企业划型证明申请承诺书（企业盖章）（见附件）；(<span>仅限word文档</span>)<input type="file" name="" /></li>
						<li>七、企业的投标文件（提供电子版）；<input type="file" name="" /></li>
					</ol>
					<div class="beizhu">
						<span>注：</span><em>*</em>以上资料，验原件留复印件。<br /> 
						<em>*</em>申请报告内容需以word文档形式上传。<br />
						 <em>*</em>申请承诺书需下载附件填写完成后再上传。<br />
						 （办理期限：交材料后3－4个工作日）<br /><br />
						 附件：  <a href="">深圳市中小微企业划型证明申请承诺书</a>
						
					</div>
									<div class="step-submit">
					<button type="submit" class="previous prev2">上一步</button>
		      		<button type="submit" class="next2"  >下一步</button>
					</div>
					<div class="clearer"></div>
				</div>

			</div>
			<div class="step-3 step-details">
				<ul class="stepNav clearfix">
					<li class="one sec"><strong>第一步</strong>中小企业证明信息表</li>
					<li class="one"><strong>第二步</strong>上传证明材料</li>
					<li class="last current"><strong>第三步</strong>认定成功</li>
				</ul>
				<h2>资料提交成功</h2>
				<div class="step-upload">
					
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
	<div class="back-bar"><a href="ucenter/buyer_order?op=8">返回订单管理</a></div>
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
<!-- /wrap -->
	<jsp:include page="layout/footer.jsp" flush="true"/>
	<script type="text/javascript">
	$(function(){
		$('.next1').click(function(event){
			$('.step-1').hide();
			$('.step-2').show();
		})
		$('.prev2').click(function(event){
			$('.step-2').hide();
			$('.step-1').show();
		})
		$('.next2').click(function(event){
			$('.step-2').hide();
			$('.step-3').show();
			art.dialog({
				width: 350,
				height: 100,
				fixed: true,
				lock: true,
				resize: false,
				title: '提示',
				content:'<div class="ucenter-msg"><span class="icon-good"></span>提交成功</div>',
				ok: function () {
			        this.lock();
			    }
			});
		});
	});
	</script>
</body>
</html>