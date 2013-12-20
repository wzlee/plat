<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户注册</title>
<link href="resources/css/main/style.css" rel="stylesheet"
	type="text/css" />
<link href="jsLib/artDialog-5.0.3/skins/default.css" rel="stylesheet" type="text/css" />
<link href="resources/js/main/Selecter/jquery.fs.selecter.css" rel="stylesheet" type="text/css" />
<link href="resources/css/main/user.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/droptime.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/demo.css" />
</head>

<body>
	<div class="register-wrap">
		<div class="register-header">
			<div class="register-logo">
				<h1>
					<a href="#"><span class="hide">深圳市中小企业公共服务平台</span></a>
				</h1>
			</div>
			<div class="register-title">用户注册</div>
			<div class="register-link">
				已经是注册用户？请<a href="login?redirect_url=./" class="f-purple">登录</a>
			</div>
		</div>
		<ul class="type-tab">
			<li id='div1' class="active"><a href="javascript:void(0)">企业用户注册</a></li>
			<li id='div3'><a href="javascript:void(0)">服务机构注册</a></li>
			<li id='div2'><a href="javascript:void(0)">个人用户注册</a></li>
		</ul>
		<!-- 企业用户注册 -->
		<div class="register-content" id='register1'>
			<form id="register-form" action="" method="post">
				<div class="control-group">
					<label class="control-label" for="username">用户名：</label>
					<div class="controls name-controls">
						<input type="text" id="username" name="userName" class="xxlarge name-guide" data-status="0" />
						
						
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="password">密码：</label>
					<div class="controls">
						<input type="password" id="password" class="xxlarge" />
					</div>
					<!-- <div class="passwordStrength"><span></span><span></span><span class="last"></span></div> -->
				
				</div>
				<div class="control-group">
					<label class="control-label" for="confirm_password">确认密码：</label>
					<div class="controls">
						<input id="confirm_password" name="confirm_password"
							class="xxlarge" type="password" />
					</div>
					
				</div>
				<div class="control-group">
					<label class="control-label" for="company_name">公司名称：</label>
					<div class="controls">
						<input id="company_name" name="enterpriseName" class="xxlarge" type="text" />
					</div>
				</div>
	            <div class="control-group">
	                <label class="control-label">所属窗口：</label>
	                <div class="controls">
	                    <select id="enterprise_type" class="custom-select" name="enterprise_type">
	                        <option value="16">枢纽平台</option>
	                        <option value="16">枢纽平台</option>
	                        <option value="1">电子装备</option>
	                        <option value="2">服装</option>
	                        <option value="3">港澳资源</option>
	                        <option value="4">工业设计</option>
	                        <option value="5">机械</option>
	                        <option value="6">家具</option>
	                        <option value="7">LED</option>
	                        <option value="8">软件</option>
	                        <option value="9">物流</option>
	                        <option value="10">物联网</option>
	                        <option value="11">新材料</option>
	                        <option value="12">医疗器械</option>
	                        <option value="13">钟表</option>
	                        <option value="14">珠宝</option>
	                        <option value="16">其他</option>
	                    </select>
	                </div>
	            </div>
	            <div class="control-group">
	                <label class="control-label">企业所属行业：</label>
	                <div class="controls">
	                    <select id="enterprise_industry" class="custom-select" name="enterpriseIndustry">
	                        <option value="">默认选项</option>
	                        <option value="17">金融</option>
	                        <option value="1">农、林、牧、渔业</option>
	                        <option value="2">工业</option>
	                        <option value="3">建筑业</option>
	                        <option value="4">批发业</option>
	                        <option value="5">零售业</option>
	                        <option value="6">交通运输业</option>
	                        <option value="7">仓储业</option>
	                        <option value="8">邮政业</option>
	                        <option value="9">住宿业</option>
	                        <option value="10">餐饮业</option>
	                        <option value="11">信息传输业</option>
	                        <option value="12">软件和信息技术服务业</option>
	                        <option value="13">房地产开发经营</option>
	                        <option value="14">物业管理</option>
	                        <option value="15">租赁和商务服务业</option>
	                        <option value="16">其他未列明行业</option>
	                    </select>
	                </div>
	            </div>
				<div class="control-group">
					<label class="control-label" for="email">电子邮件：</label>
					<div class="controls">
						<input id="email" name="email" class="xxlarge" type="text" data-status="0" />
					</div>
				</div>
<!-- 			    <div class="control-group-mini">
			    	<label class="control-label" for="">用户身份：</label>
			        <div class="controls">
				        <label title="可申请/使用平台及供应商提供的服务"><input class="user_radio" type="radio" name="enterpriseType" value="1" />我是采购商</label>
				        <label title="既可在平台销售服务,也可采购其他服务"><input class="user_radio" type="radio" name="enterpriseType" value="3" />我是供应商(即服务机构)</label>
			        </div>
			    </div> -->
				<div class="control-group-mini">
					<label class="control-label" for="checkcode"></label>
					<div class="controls gray">
						<input type="checkbox" name="checkbox" id="checkbox" /> <label
							for="checkbox">同意<a class="rule-link f-purple" href="javascript:void(0)">《深圳市中小企业服务平台条款》</a></label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="checkcode"></label>
					<div class="controls">
						<button id="commit" class="register-btn" type="submit" data-loading-text="处理中...">注册</button>
					</div>
				</div>
			</form>
		</div>
		<!-- 服务机构注册 -->
		<div class="register-content" style="display: none" id='register3'>
			<form id="org-register-form" action="user/org_register" method="post">
				<table class="table-reg">
					<tr>
						<td class="one no-border">申请机构<em>*</em></td>
						<td colspan="3"><input type="text" id="orgName" data-status="0" name="orgName" class="large required" /></td>
					</tr>
					<tr>
						<td>用户名<em>*</em></td>
						<td class="two"><input type="text" id="org-userName" name="userName" class="" /></td>
						<td class="three">E-mail<em>*</em></td>
						<td><input type="text" id="org_email" name="email" class="" /></td>
					</tr>
					<tr>
						<td>密码<em>*</em></td>
						<td class="two"><input type="password" id="org-password" name="password" class="" /></td>
						<td class="three">确认密码<em>*</em></td>
						<td><input type="password" id="org-confirm_password" name="confirm_password" class="" /></td>
						
					</tr>
					<tr>
						<td class="no-border">组织机构代码<em>*</em></td>
						<td colspan="3"><input type="text" id="icRegNumber" name="icRegNumber" class="" /></td>
					</tr>
					<tr>
						<td>成立时间<em></em></td>
						<td><input type="text" data-options="showSeconds:false" id="date-input" placeholder="成立时间..." class="easyui-datebox" name="openedTime" value="${param.openedTime }"/></li></td>
						<td class="three">职工人数</td>
						<td><input type="text" id="staffNumber" name="staffNumber" onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" type="text" name="beginPrice" class="pri jiage" class="" /></td>
					</tr>
					<tr>
						<td class="no-border">所属窗口<em>*</em></td>
						<td colspan="5">
							<select id="industry_type" class="custom-select" name="industryType">
		                        <option value="16">枢纽平台</option>
		                        <option value="16">枢纽平台</option>
		                        <option value="1">电子装备</option>
		                        <option value="2">服装</option>
		                        <option value="3">港澳资源</option>
		                        <option value="4">工业设计</option>
		                        <option value="5">机械</option>
		                        <option value="6">家具</option>
		                        <option value="7">LED</option>
		                        <option value="8">软件</option>
		                        <option value="9">物流</option>
		                        <option value="10">物联网</option>
		                        <option value="11">新材料</option>
		                        <option value="12">医疗器械</option>
		                        <option value="13">钟表</option>
		                        <option value="14">珠宝</option>
		                        <option value="16">其他</option>
	                    	</select>
						</td>
					</tr>
					<tr>
						<td class="no-border">行业<em>*</em></td>
						<td colspan="5">
							<select id="org_industry" class="custom-select" name="orgIndustry">
		                        <option value="">默认选项</option>
		                        <option value="1">金融</option>
		                        <option value="2">软件及信息服务</option>
		                        <option value="3">物流</option>
		                        <option value="4">物联网</option>
		                        <option value="5">法律</option>
		                        <option value="6">财税</option>
		                        <option value="7">科技</option>
		                        <option value="8">咨询</option>
		                        <option value="9">其他</option>
		                    </select>
						</td>
					</tr>
					<tr>
						<td class="no-border">机构性质<em>*</em></td>
						<td colspan="3">
							<select id="org_property" class="custom-select" name="orgProperty">
		                        <option value="">默认选项</option>
		                        <option value="1">国营</option>
		                        <option value="2">民营</option>
		                        <option value="3">三资</option>
		                        <option value="4">股份制</option>
		                        <option value="5">合伙制</option>
		                        <option value="6">民非</option>
		                        <option value="7">其他</option>
		                    </select>
						</td>
					</tr>
					<tr>
						<td class="no-border">经营范围<em>*</em></td>
						<td colspan="3">
							<input type="text" id="org_business" name="business" class="large" />
						</td>
						
					</tr>
					<tr>
						<td>优势服务领域<em>*</em></td>
						<td colspan="3">
							<input type="text" id="advantageServiceAreas" name="advantageServiceAreas" class="large" />
						</td>
					</tr>
					<tr>
						<td class="no-border">总资产 <br /><small>(上年、万元)</small><em>*</em></td>
						<td colspan="3"><input type="text" id="totalAssets" onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" type="text"  name="totalAssets" class=""  /></td>
					</tr>
					<tr>
						<td>营业额 <br /><small>(上年、万元)</small><em>*</em></td>
						<td><input type="text" id="turnover" onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" type="text" name="turnover" class="" /></td>
						<td class="three">利润或净收入 <br /><small>(上年、万元)</small><em>*</em></td>
						<td><input type="text" id="profit" onkeyup="value=value.replace(/[^\d{1,10}(\.\d{1,2})?$]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d{1,10}(\.\d{1,2})?$]/g,''))" type="text" name="profit" class="" /></td>
					</tr>
					<tr>
						<td class="no-border">社会荣誉</td>
						<td colspan="3"><input type="text" id="honorSociety" name="honorSociety" class="large" /></td>
					</tr>
					<tr>
						<td class="no-border">专业资质</td>
						<td colspan="3"><input type="text" id="professionalQualifications" name="professionalQualifications" class="large" /></td>
					</tr>
					<tr>
						<td class="no-border">法定代表人<em>*</em></td>
						<td colspan="3"><input type="text" id="legalRepresentative" name="legalRepresentative" class="" /></td>
					</tr>
					<tr>
						<td>手机<em>*</em></td>
						<td><input type="text" id="legalRepresentativeMobile" name="legalRepresentativeMobile" class="" /></td>
						<td class="three">E-mail<em>*</em></td>
						<td><input type="text" id="legalRepresentativeEmail" name="legalRepresentativeEmail" class="" /></td>

					</tr>
					<tr>
						<td class="no-border">总经理姓名</td>
						<td colspan="3"><input type="text" id="generalName" name="generalName" class="" /></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" id="generalManagerMobile" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" name="generalManagerMobile" class="" /></td>
						<td class="three">E-mail</td>
						<td><input type="text" id="generalManagerEmail" name="generalManagerEmail" class="" /></td>
					</tr>
					<tr>
						<td class="no-border">联系人姓名</td>
						<td colspan="3"><input type="text" id="contactName" name="contactName" class="" /></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" id="contactNameMobile" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" name="contactNameMobile" class="" /></td>
						<td class="three">E-mail</td>
						<td><input type="email" id="contactNameEmail" name="contactNameEmail" class="" /></td>
						
					</tr>
					<tr>
						<td class="no-border">机构地址</td>
						<td colspan="3"><input type="text" id="orgAddress" name="orgAddress" class="large" /></td>
					</tr>
					<tr>
						<td class="no-border">机构电话</td>
						<td><input type="text" id="orgPhone" name="orgPhone" class="" /></td>
						<td class="three">传真</td>
						<td><input type="text" id="orgFax" name="orgFax" class="" /></td>
					</tr>
					<tr>
						<td>公司网址</td>
						<td colspan="3"><input type="text" id="orgWebsite" name="orgWebsite" class="large" /></td>
					</tr>
					<tr rowspan="3">
						<td class="no-border">机构介绍</td>
						<td colspan="3"><textarea name="profile" id="profile"></textarea></td>
					</tr>
				</table>
		
				
						<button id="commit3" class="register-btn" type="submit" data-loading-text="处理中...">注册</button>
					
			
			</form>
			
		</div>
		<!-- /个人用户注册 -->
		<div class="register-content" style="display: none" id='register2'>
			<form id="register-form1" action="" method="post">
				<div class="control-group">
					<label class="control-label" for="username">用户名：</label>
					<div class="controls">
						<input type="text" id="username1" name="userName" class="xxlarge" data-status="0" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="password">密码：</label>
					<div class="controls">
						<input type="password" id="password1" class="xxlarge" />
					</div>
					<!-- <div class="passwordStrength"><span></span><span></span><span class="last"></span></div> -->
				
				</div>
				<div class="control-group">
					<label class="control-label" for="confirm_password">确认密码：</label>
					<div class="controls">
						<input id="confirm_password1" name="confirm_password"
							class="xxlarge" type="password" />
					</div>
					
				</div>
				<!-- <div class="control-group">
					<label class="control-label" for="company_name">公司名称：</label>
					<div class="controls">
						<input id="company_name" name="enterpriseName" class="xxlarge" type="text" />
					</div>
				</div> -->
	           <!--  <div class="control-group">
	                <label class="control-label">所属行业：</label>
	                <div class="controls">
	                    <select id="enterprise_type" class="custom-select" name="enterprise_type">
	                        <option value="">默认选项</option>
	                        <option value="1">电子装备</option>
	                        <option value="2">服装</option>
	                        <option value="3">港澳资源</option>
	                        <option value="4">工业设计</option>
	                        <option value="5">机械</option>
	                        <option value="6">家具</option>
	                        <option value="7">LED</option>
	                        <option value="8">软件</option>
	                        <option value="9">物流</option>
	                        <option value="10">物联网</option>
	                        <option value="11">新材料</option>
	                        <option value="12">医疗器械</option>
	                        <option value="13">钟表</option>
	                        <option value="14">珠宝</option>
	                        <option value="15">其他</option>
	                    </select>
	                </div>
	            </div> -->
				<div class="control-group">
					<label class="control-label" for="email">电子邮件：</label>
					<div class="controls">
						<input id="email1" name="email" class="xxlarge" type="text" data-status="0" />
					</div>
				</div>
			    <!-- <div class="control-group-mini">
			    	<label class="control-label" for="">用户身份：</label>
			        <div class="controls">
				        <label title="可申请/使用平台及供应商提供的服务"><input class="user_radio" type="radio" name="enterpriseType" value="1" />我是采购商</label>
				        <label title="既可在平台销售服务,也可采购其他服务"><input class="user_radio" type="radio" name="enterpriseType" value="3" />我是供应商(即服务机构)</label>
			        </div>
			    </div> -->
				<div class="control-group-mini">
					<label class="control-label" for="checkcode"></label>
					<div class="controls">
						<input type="checkbox" name="checkbox" id="checkbox1" /> <label
							for="checkbox">同意<a class="rule-link f-purple" href="javascript:void(0)">《深圳市中小企业服务平台条款》</a></label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="checkcode"></label>
					<div class="controls">
						<button id="commit1" class="register-btn" type="submit" data-loading-text="处理中...">注册</button>
					</div>
				</div>
			</form>
		</div>
	<div class="user-footer">
        <p class="s1">主管部门 ：深圳市中小企业服务中心 | 建设单位：深圳市商业联合会 | 技术支持：深圳市依格欣计算机技术有限公司</p>
        <p class="s2">备案号：粤ICP备13083911号 | 增值电信业务经营许可证：B2-1234567号(ICP加挂服务)  &copy;2012 1234567</p>
    </div>
	</div>
	<script type="text/javascript" src="jsLib/jquery/jquery-1.9.1.min.js"></script>
	<script src="jsLib/jquery/jquery.form.js" type="text/javascript"></script>
	<script src="jsLib/bootstrap/js/bootstrap-button.js" type="text/javascript"></script>
	<script type="text/javascript" src="resources/js/main/Selecter/jquery.fs.selecter.min.js"></script>
	<script type="text/javascript" src="resources/js/main/passwordStrength-min.js"></script>
	<script type="text/javascript" src="jsLib/jquery/jquery.md5.js" ></script>
	<script type="text/javascript" src="jsLib/artDialog-5.0.3/artDialog.min.js"></script>
	<script type="text/javascript" src="resources/js/main/register.js"></script>
	<script type="text/javascript" src="resources/js/main/org-register.js"></script>
	<!-- <script type="text/javascript" src="resources/js/main/droptime.js"></script> -->
	
<script type="text/javascript" src="jsLib/jquery.easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jsLib/jquery.easyui/locale/easyui-lang-zh_CN.js"></script>

</body>
</html>
