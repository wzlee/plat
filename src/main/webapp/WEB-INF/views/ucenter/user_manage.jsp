<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.eaglec.com/jsp/user/functions" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>深圳市中小企业公共服务平台</title>
<meta charset="UTF-8"/>
<link type="text/css" rel="stylesheet"
	href="resources/css/ucenter/style.css" />
<link type="text/css" rel="stylesheet"
	href="jsLib/jNotify/jNotify.jquery.css" />
<link type="text/css" rel="stylesheet"
	href="resources/css/ucenter/authentication.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/demo.css" />
<link type="text/css" rel="stylesheet" href="jsLib/jquery.easyui/themes/icon.css" />
<link rel="stylesheet" href="jsLib/kindeditor-4.1.9/themes/default/default.css" />
<link rel="stylesheet" href="resources/js/main/Selecter/jquery.fs.selecter.css" />
</head>

<body>
	<jsp:include page="header.jsp" flush="true" />
	<!-- /header -->
	<jsp:include page="../ucenter/head.jsp" flush="true" />
	<!-- /user-header -->
	<!-- /container -->
	<div class="main-container">
		<!-- 左边菜单 -->
		<jsp:include page="../ucenter/left.jsp" flush="true" />
		<div class="main-column">
			<h3 class="top-title">用户管理</h3>
			<div class="auth-form-content">
				<div class="col-bar">
					<div class="title">基本信息</div>
					<input type="text" style="display: none;" id="userId"  value ="${user.id }" name=""></input>
					<ul class="action">
						<li class="s1" action-data='edit'><a
							href="javascript:void(0)">编辑</a></li>
						<li class="s2" action-data='up'><a href="javascript:void(0)"><span
								class="hide">开关</span></a></li>
					</ul>
				</div>
				<div class="switch-content">
					<div class="show-info">
						<div class="control-group">
							<label class="control-label">用户名：</label>
							<div class="controls">
								<div class="result-text">${user.userName }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">邮箱：</label>
							<div class="controls">
								<div class="result-text">${user.email }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">企业简称：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.forShort }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">企业全称：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.name}</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">用户类型：</label>
							<div class="controls">
								<div class="result-text">
									<c:choose>
										<c:when test="${user.enterprise.type == 1 }">普通企业</c:when>
										<c:when test="${user.enterprise.type == 2 }">认证企业</c:when>
									</c:choose> 
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">企业简介：</label>
							<div class="controls">
								<div class="service-description">
									${fn:substring(my:replaceHTML(user.enterprise.profile), 0, 88) }
									${fn:length(my:replaceHTML(user.enterprise.profile)) > 88 ? '...' :'' }
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">注册时间：</label>
							<div class="controls">
								<div class="result-text">${user.regTime }</div>
							</div>
						</div>
					</div>
					<div class="info-form">
						<form action="" id="userinfoform"  method="post">
							<input name="id" style="display: none;"  type="text"
										value="${user.id }" />
							<div class="control-group">
								<label class="control-label">用户名：</label>
								<div class="controls">
										<input name="userName" class="input-large" type="text"
											value="${user.userName }" readonly="readonly"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">邮箱：</label>
								<div class="controls">
									<c:choose>
										<c:when test="${user.email == '' ||user.email == null}">
											<input id="userEmail" name="email" class="input-large" type="text"
											value="${user.email }" />
											<!-- <a href="ucenter/security_uemail?op=88" >去邮箱认证</a> -->
										</c:when>
										<c:otherwise>
											<input name="email" class="input-large" type="text"
											value="${user.email }" readonly="readonly"/>
											<c:choose>
												<c:when test="${user.isApproved == true}">
													<a href="ucenter/security_uemail?op=88" class="f-purple">更换邮箱并申请认证</a>
												</c:when>
												<c:otherwise><a href="ucenter/security_uemail?op=88" class="f-purple tdu">去邮箱认证</a></c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
									
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">企业简称：</label>
								<div class="controls">
									<input name="enterprise.forShort" class="input-large"
										type="text" value="${user.enterprise.forShort }" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">企业全称：</label>
								<div class="controls">
								<c:choose>
									<c:when test="${user.enterprise.isApproved == true }">
										<input name="enterprise.name" class="input-large"
										type="text" value="${user.enterprise.name}" readonly="readonly"/>
									</c:when>
									<c:otherwise>
									<input name="enterprise.name" class="input-large"
										type="text" value="${user.enterprise.name}" /><a href="ucenter/auth?op=2" class="f-purple tdu">去实名认证</a>
									
									</c:otherwise>
								</c:choose>
									
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">用户类型：</label>
								<div class="controls">
									<input readonly="readonly" name="isPersonal" class="input-large" type="text"
										value=<c:choose>
											<c:when test="${user.enterprise.type == 1 }">普通企业</c:when>
											<c:when test="${user.enterprise.type == 2 }">认证企业</c:when>
										</c:choose> />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">企业简介：</label>
								<div class="controls">
									<textarea id="editor_profile" name="enterprise.profile" style="width:300px;">${user.enterprise.profile }</textarea>
									<%-- <textarea name="enterprise.profile" class="textarea-large">${user.enterprise.profile }</textarea> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
									<ul class="">
						<li class="s1" action-data='save' id="infocommit"><a
							href="javascript:void(0)" class="btn-save">保存</a></li>
							</ul>
								</div>
							</div>
						</form>
						
					</div>
				</div>
				<!-- /基本信息 -->
				<div class="col-bar">
					<div class="title">图像设置</div>
					<ul class="action">
						<li class="s1" action-data='edit'><a
							href="javascript:void(0)">编辑</a></li>
						<li class="s2" action-data='up'><a href="javascript:void(0)"><span
								class="hide">开关</span></a></li>
					</ul>
				</div>
				<div class="switch-content">
					<div class="show-info">
						<div class="control-group">
							<label class="control-label">企业图像：</label>
							<div class="controls">
								<c:if test="${user.enterprise.photo == 'enterprise_logo.jpg'}">
									<img width="188" height="146" src="resources/images/ucenter/enterprise_logo.jpg" title="企业图像" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
								</c:if>
								<c:if test="${user.enterprise.photo != 'enterprise_logo.jpg' }">
									<c:if test="${user.enterprise.photo.contains('http')}">
										<img width="188" height="146" src="${user.enterprise.photo }" title="企业图像" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
									</c:if>
									<c:if test="${!user.enterprise.photo.contains('http')}">
										<img width="188" height="146" src="upload/${user.enterprise.photo }" title="企业图像" onerror="this.src='resources/images/service/default_service_pic.gif'"/>
									</c:if>
								</c:if>
							</div>
						</div>
					</div>
					<div class="info-form">
						<form action="" id="photoform" method="post" enctype="multipart/form-data">
							<div class="control-group">
								<label class="control-label">企业图像：</label>
								<div class="controls">
									<input id="enterLogo" class="" type="file" name="file"
										value="${user.enterprise.photo }" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
									<ul class="">
						<li class="s1" action-data='save' id="photocommit"><a
							href="javascript:void(0)" class="btn-save">保存</a></li>
							</ul>
								</div>
							</div>							
						</form>
					</div>
				</div>
				<!-- /图像设置 -->
				<div class="col-bar">
					<div class="title">企业信息</div>
					<ul class="action">
						<li class="s1" action-data='edit'><a
							href="javascript:void(0)">编辑</a></li>
						<li class="s2" action-data='up'><a href="javascript:void(0)"><span
								class="hide">开关</span></a></li>
					</ul>
				</div>
				<div class="switch-content">
					<div class="show-info">
						<div class="control-group">
							<!-- 1、电子装备 2、服装 3、港澳资源 4、工业设计 5、机械 6、家具 7、LED 8、软件 9、物流10、物联网11、新材料 12、医疗器械13、钟表14、珠宝15、其他  16、枢纽平台-->
							<label class="control-label">所属窗口：</label>
							<div class="controls">
								<div class="result-text">
									<c:choose>
										<c:when test="${user.enterprise.industryType == '1' }">
	                        				电子装备
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '2' }">
	                        				服装
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '3' }">
	                        				港澳资源
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '4' }">
	                        				工业设计
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '5' }">
	                        				机械
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '6' }">
	                        				家具
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '7' }">
	                        				LED
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '8' }">
	                        				软件
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '9' }">
	                        				物流
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '10' }">
	                        				物联网
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '11' }">
	                        				新材料
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '12' }">
	                        				医疗器械
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '13' }">
	                        				钟表
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '14' }">
	                        				珠宝
	                        			</c:when>
										<c:when test="${user.enterprise.industryType == '16' }">
	                        				枢纽平台
	                        			</c:when>
										<c:otherwise>
	                        				其它
	                        			</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="control-group">
							<!-- 1、农、林、牧、渔业 2、工业 3、建筑业 4、批发业 5、零售业 6、交通运输业7、仓储业 8、邮政业 9、住宿业10、餐饮业11、信息传输业12、软件和信息技术服务业13、房地产开发经营14、物业管理15、租赁和商务服务业  16、其他未列明行业-->
							<label class="control-label">企业所属行业：</label>
							<div class="controls">
								<div class="result-text">
									<c:choose>
										<c:when test="${user.enterprise.enterpriseIndustry == '1' }">
	                        			农、林、牧、渔业
	                        		</c:when>
										<c:when test="${user.enterprise.enterpriseIndustry == '2' }">
	                        			工业
	                        		</c:when>
										<c:when test="${user.enterprise.enterpriseIndustry == '3' }">
	                        			建筑业
	                        		</c:when>
										<c:when test="${user.enterprise.enterpriseIndustry == '4' }">
	                        			批发业
	                        		</c:when>
										<c:when test="${user.enterprise.enterpriseIndustry == '5' }">
	                        			零售业
	                        		</c:when>
										<c:when test="${user.enterprise.enterpriseIndustry == '6' }">
	                        			交通运输业
	                        		</c:when>
										<c:when test="${user.enterprise.enterpriseIndustry == '7' }">
	                        			仓储业
	                        		</c:when>
										<c:when test="${user.enterprise.enterpriseIndustry == '8' }">
	                        			邮政业
	                        		</c:when>
										<c:when test="${user.enterprise.enterpriseIndustry == '9' }">
	                        			住宿业
	                        		</c:when>
										<c:when test="${user.enterprise.enterpriseIndustry == '10' }">
	                        			餐饮业
	                        		</c:when>
										<c:when test="${user.enterprise.enterpriseIndustry == '11' }">
	                        			信息传输业
	                        		</c:when>
										<c:when test="${user.enterprise.enterpriseIndustry == '12' }">
	                        			软件和信息技术服务业
	                        		</c:when>
										<c:when test="${user.enterprise.enterpriseIndustry == '13' }">
	                        			房地产开发经营
	                        		</c:when>
									<c:when test="${user.enterprise.enterpriseIndustry == '14' }">
	                        			物业管理
	                        		</c:when>
									<c:when test="${user.enterprise.enterpriseIndustry == '15' }">
	                        			租赁和商务服务业
	                        		</c:when>
	                        		<c:when test="${user.enterprise.enterpriseIndustry == '16' }">
	                        			其他未列明行业餐饮业
	                        		</c:when>
	                        		<c:when test="${user.enterprise.enterpriseIndustry == '17' }">
	                        			金融
	                        		</c:when>
									<c:otherwise>
	                        			暂无数据
	                        		</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="control-group">
							<!-- 1企业；2事业单位；3社会团体；4个体工商户；5民办非企业；9其他； 必填项 下拉框显示 -->
							<label class="control-label">单位性质：</label>
							<div class="controls">
								<div class="result-text">
									<c:choose>
										<c:when test="${user.enterprise.enterpriseProperty == '1'}">
	                        		企业
	                        	</c:when>
										<c:when test="${user.enterprise.enterpriseProperty == '2'}">
	                        		事业单位
	                        	</c:when>
										<c:when test="${user.enterprise.enterpriseProperty == '3'}">
	                        		社会团体
	                        	</c:when>
										<c:when test="${user.enterprise.enterpriseProperty == '4'}">
	                        		个体工商户
	                        	</c:when>
										<c:when test="${user.enterprise.enterpriseProperty == '5'}">
	                        		民办非企业
	                        	</c:when>
										<c:when test="${user.enterprise.enterpriseProperty == '9'}">
	                        		其他
	                        	</c:when>
										<c:otherwise>
	                        		不详
	                        	</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="control-group">
							<!-- 1生产型；2贸易型；3服务性；4生产性、贸易型；5贸易型、服务型；6生产性、贸易型、服务性； -->
							<label class="control-label">经营模式：</label>
							<div class="controls">
								<div class="result-text">
									<c:choose>
										<c:when test="${user.enterprise.businessPattern =='1' }">
			                        		生产型
			                        	</c:when>
												<c:when test="${user.enterprise.businessPattern == '2'}">
			                        		贸易型
			                        	</c:when>
												<c:when test="${user.enterprise.businessPattern == '3'}">
			                        		服务型
			                        	</c:when>
												<c:when test="${user.enterprise.businessPattern == '4'}">
			                        		生产型、贸易型
			                        	</c:when>
												<c:when test="${user.enterprise.businessPattern == '5'}">
			                        		贸易型、服务型
			                        	</c:when>
												<c:when test="${user.enterprise.businessPattern == '6'}">
			                        		生产型、贸易型、服务型
			                        	</c:when>
												<c:otherwise>
			                        		其它
			                        	</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">年营业额(万)：</label>
							<div class="controls">
								<div class="result-text">
									<fmt:formatNumber value="${user.enterprise.salesRevenue}" pattern="0.0000"/>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">成立日期：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.openedTime }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">员工人数：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.staffNumber }人</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">法人名称：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.legalPerson }</div>
							</div>
						</div>
						<div class="control-group look-img">
							<label class="control-label">企业公函：</label>
							<div class="controls">
								<div class="result-text">
									<c:if test="${user.enterprise.businessLetter.contains('http')}">
										<a target="_blank" href="${user.enterprise.businessLetter}" class="f-purple">查看大图</a>
									</c:if>
									<c:if test="${!user.enterprise.businessLetter.contains('http')}">
										<a target="_blank" href="upload/${user.enterprise.businessLetter}" class="f-purple">查看大图</a>
									</c:if>
								</div>
							</div>
						</div>
						<div class="control-group look-img">
							<label class="control-label">营业执照附件：</label>
							<div class="controls">
								<div class="result-text">
									<c:if test="${user.enterprise.licenceDuplicate.contains('http')}">
										<a target="_blank" href="${user.enterprise.licenceDuplicate}" class="f-purple">查看大图</a>
									</c:if>
									<c:if test="${!user.enterprise.licenceDuplicate.contains('http')}">
										<a target="_blank" href="upload/${user.enterprise.licenceDuplicate}" class="f-purple">查看大图</a>
									</c:if>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">组织机构号：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.icRegNumber }</div>
							</div>
						</div>
						<%-- <div class="control-group">
							<label class="control-label">组织机构代码：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.enterpriseCode }</div>
							</div>
						</div> --%>
						<div class="control-group">
							<label class="control-label">主营产品/服务：</label>
							<div class="controls">
								<div class="service-description">
									${fn:substring(my:replaceHTML(user.enterprise.mainRemark), 0, 88) }
									${fn:length(my:replaceHTML(user.enterprise.mainRemark)) > 88 ? '...' :'' }
								</div>
								<%-- <div class="result-text">${user.enterprise.mainRemark }</div> --%>
							</div>
						</div>
					</div>
					<div class="info-form">
						<form action="" id="enterinfoform">
							<input name="id" style="display: none;"  type="text"
										value="${user.id }" />
							<%-- <div class="control-group">	暂时屏蔽，不让修改
								<label class="control-label">所属窗口：</label>
								<div class="controls">
									<div class="">
										<!-- 1、电子装备 2、服装 3、港澳资源 4、工业设计 5、机械 6、家具 7、LED 8、软件 9、物流10、物联网11、新材料 12、医疗器械13、钟表14、珠宝15、其他  16、枢纽平台-->
										<select name="enterprise.industryType"
											class="selected-js se0">
											<option value="16"
												${user.enterprise.industryType == '16' ?'selected':'' }>枢纽平台</option>
											<option value="1"
												${user.enterprise.industryType == '1' ?'selected':'' }>电子装备</option>
											<option value="2"
												${user.enterprise.industryType == '2' ?'selected':'' }>服装</option>
											<option value="3"
												${user.enterprise.industryType == '3' ?'selected':'' }>港澳资源</option>
											<option value="4"
												${user.enterprise.industryType == '4' ?'selected':'' }>工业设计</option>
											<option value="5"
												${user.enterprise.industryType == '5' ?'selected':'' }>机械</option>
											<option value="6"
												${user.enterprise.industryType == '6' ?'selected':'' }>家具 </option>
											<option value="7"
												${user.enterprise.industryType == '7' ?'selected':'' }>LED</option>
											<option value="8"
												${user.enterprise.industryType == '8' ?'selected':'' }>软件</option>
											<option value="9"
												${user.enterprise.industryType == '9' ?'selected':'' }>物流</option>
											<option value="10"
												${user.enterprise.industryType == '10' ?'selected':'' }>物联网</option>
											<option value="11"
												${user.enterprise.industryType == '11' ?'selected':'' }>新材料</option>
											<option value="12"
												${user.enterprise.industryType == '12' ?'selected':'' }>医疗器械</option>
											<option value="13"
												${user.enterprise.industryType == '13' ?'selected':'' }>钟表</option>
											<option value="14"
												${user.enterprise.industryType == '14' ?'selected':'' }>珠宝</option>
											<option value="15"
												${user.enterprise.industryType == '15' ?'selected':'' }>其他
											</option>
										</select>
									</div>
								</div>
							</div> --%>
							<div class="control-group">
								<label class="control-label">企业所属行业：</label>
								<div class="controls">
									<div class="">
										<!-- 1、农、林、牧、渔业 2、工业 3、建筑业 4、批发业 5、零售业 6、交通运输业7、仓储业 8、邮政业 9、住宿业10、餐饮业11、信息传输业12、软件和信息技术服务业13、房地产开发经营14、物业管理15、租赁和商务服务业  16、其他未列明行业-->
										<select name="enterprise.enterpriseIndustry"
											class="selected-js se1">
											<!-- <option value="0">请选择</option> -->
											<option value="17"
												${user.enterprise.enterpriseIndustry == '17' ?'selected':'' }>金融</option>
											<option value="1"
												${user.enterprise.enterpriseIndustry == '1' ?'selected':'' }>农、林、牧、渔业</option>
											<option value="2"
												${user.enterprise.enterpriseIndustry == '2' ?'selected':'' }>工业</option>
											<option value="3"
												${user.enterprise.enterpriseIndustry == '3' ?'selected':'' }>建筑业</option>
											<option value="4"
												${user.enterprise.enterpriseIndustry == '4' ?'selected':'' }>批发业</option>
											<option value="5"
												${user.enterprise.enterpriseIndustry == '5' ?'selected':'' }>零售业</option>
											<option value="6"
												${user.enterprise.enterpriseIndustry == '6' ?'selected':'' }>交通运输业 </option>
											<option value="7"
												${user.enterprise.enterpriseIndustry == '7' ?'selected':'' }>仓储业</option>
											<option value="8"
												${user.enterprise.enterpriseIndustry == '8' ?'selected':'' }>邮政业</option>
											<option value="9"
												${user.enterprise.enterpriseIndustry == '9' ?'selected':'' }>住宿业</option>
											<option value="10"
												${user.enterprise.enterpriseIndustry == '10' ?'selected':'' }>餐饮业</option>
											<option value="11"
												${user.enterprise.enterpriseIndustry == '11' ?'selected':'' }>信息传输业</option>
											<option value="12"
												${user.enterprise.enterpriseIndustry == '12' ?'selected':'' }>软件和信息技术服务业</option>
											<option value="13"
												${user.enterprise.enterpriseIndustry == '13' ?'selected':'' }>房地产开发经营</option>
											<option value="14"
												${user.enterprise.enterpriseIndustry == '14' ?'selected':'' }>物业管理</option>
											<option value="15"
												${user.enterprise.enterpriseIndustry == '15' ?'selected':'' }>租赁和商务服务业
											</option>
											<option value="16"
												${user.enterprise.enterpriseIndustry == '16' ?'selected':'' }>其他未列明行业
											</option>
										</select>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">单位性质：</label>
								<div class="controls">
									<div class="">
										<!-- 1企业；2事业单位；3社会团体；4个体工商户；5民办非企业；9其他； 必填项 下拉框显示 -->
										<select name="enterprise.enterpriseProperty"
											class="selected-js se2">
											<option value="1"
												${user.enterprise.enterpriseProperty == 1?'selected':''  }>企业</option>
											<option value="2"
												${user.enterprise.enterpriseProperty == 2?'selected':'' }>事业单位</option>
											<option value="3"
												${user.enterprise.enterpriseProperty == 3?'selected':'' }>个体工商户</option>
											<option value="4"
												${user.enterprise.enterpriseProperty == 4?'selected':'' }>工业设计</option>
											<option value="5"
												${user.enterprise.enterpriseProperty == 5?'selected':'' }>民办非企业</option>
											<option value="9"
												${user.enterprise.enterpriseProperty == 9?'selected':'' }>其它
											</option>
										</select>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">经营模式：</label>
								<div class="controls">
									<div class="">
										<!-- 1生产型；2贸易型；3服务性；4生产性、贸易型；5贸易型、服务型；6生产性、贸易型、服务性； -->
										<select name="enterprise.businessPattern"
											class="selected-js se3">
											<option value="1" ${user.enterprise.businessPattern == 1?'selected':'' }>生产型</option>
											<option value="2" ${user.enterprise.businessPattern == 2?'selected':'' }>贸易型</option>
											<option value="3" ${user.enterprise.businessPattern == 3?'selected':'' }>服务型</option>
											<option value="4" ${user.enterprise.businessPattern == 4?'selected':'' }>生产型、贸易型</option>
											<option value="5" ${user.enterprise.businessPattern == 5?'selected':'' }>贸易型、服务型</option>
											<option value="6" ${user.enterprise.businessPattern == 6?'selected':'' }>生产型、贸易型、服务型</option>
										</select>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">年营业额(万)：</label>
								<div class="controls">
									<input name="enterprise.salesRevenue" id="salesRevenue" class="input-large"
										type="text" value="<fmt:formatNumber value="${user.enterprise.salesRevenue}" pattern="0.0000"/>" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">成立日期：</label>
								<div class="controls cl-data">
									<input style="width:300px;" name="enterprise.openedTime" id="openedTime"  class="easyui-datebox input-large"
										type="text" value="${user.enterprise.openedTime }" data-options="showSeconds:false" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">员工人数：</label>
								<div class="controls">
									<input name="enterprise.staffNumber" id="staffNumber" class="input-large"
										type="text" value="${user.enterprise.staffNumber }" />人
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">法人名称：</label>
								<div class="controls">
									<input name="enterprise.icRegNumber" type="hidden" value="${user.enterprise.icRegNumber }"/>
									<input name="enterprise.enterpriseCode" type="hidden" value="${user.enterprise.enterpriseCode }"/>
									<input name="enterprise.legalPerson" class="input-large"
										type="text" value="${user.enterprise.legalPerson }" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">主营产品/服务：</label>
								<div class="controls">
									<textarea id="editor_mainRemark" name="enterprise.mainRemark" style="width:300px;">${user.enterprise.mainRemark }</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
									<ul class="">
						<li class="s1" action-data='save' id="entercommit"><a
							href="javascript:void(0)" class="btn-save">保存</a></li>
							</ul>
								</div>
							</div>							
						</form>
					</div>
				</div>
				<!-- /企业信息 -->
				<c:if test="${user.enterprise.type ==3}">
				<div class="col-bar">
					<div class="title">我的服务领域</div>
					<ul class="action">
						<li class="s1" action-data='edit'><a
							href="javascript:void(0)">编辑</a></li>
						<li class="s2" action-data='up'><a href="javascript:void(0)"><span
								class="hide">开关</span></a></li>
					</ul>
				</div>
				<div class="switch-content">
					<div class="show-info">
						<div class="control-group">
							<label class="control-label">我的服务领域：</label>
							<div class="controls">
								<div class="result-text2" id="myservice2">
									<c:choose>
										<c:when test="${user.enterprise.myServices.size()>0}">
										<c:forEach items="${user.enterprise.myServices}" var="item">
	                       				 <span>${item.text }</span>
	                       	 			</c:forEach>
										</c:when>
										<c:otherwise>
	                       	 			暂无
	                       				</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
					<div class="info-form">
						<form action="">
							<input name="enterprise.id" style="display: none;"  type="text"
										value="${user.enterprise.id }" />
							<div class="control-group">
								<label class="control-label">我的服务领域：</label>
								<div class="controls">
								<div class="result-text2" id = "myservice1">
									<c:choose>
										<c:when test="${user.enterprise.myServices.size()>0}">
										<c:forEach items="${user.enterprise.myServices}" var="item">
	                       				 <span>${item.text }</span>
	                       	 			</c:forEach>
										</c:when>
										<c:otherwise>
	                       	 			暂无
	                       				</c:otherwise>
									</c:choose>
								</div>
							</div>
								<div class="controls">
									<input class="service-type-my" type="button" value="请选择" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
									<ul class="">
						<li class="s1" action-data='save' id="myservicecommit"><a
							href="javascript:void(0)" class="btn-save">保存</a></li>
							</ul>
								</div>
							</div>								
						</form>
					</div>
				</div>
				</c:if>
				<!-- /我的服务领域 -->
				<div class="col-bar">
					<div class="title">联系信息</div>
					<ul class="action">
						<li class="s1" action-data='edit'><a
							href="javascript:void(0)">编辑</a></li>
						<li class="s2" action-data='up'><a href="javascript:void(0)"><span
								class="hide">开关</span></a></li>
					</ul>
				</div>
				<div class="switch-content">
					<div class="show-info">
						<div class="control-group">
							<label class="control-label">联系人：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.linkman }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">联系电话：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.tel }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">联系地址：</label>
							<div class="controls">

								<div class="result-text">
									<c:if test="${user.enterprise.city==0}">
										深圳市
										<c:choose>
											<c:when test="${user.enterprise.district ==1 }">福田区</c:when>
											<c:when test="${user.enterprise.district ==2 }">南山区</c:when>
											<c:when test="${user.enterprise.district ==3 }">罗湖区</c:when>
											<c:when test="${user.enterprise.district ==4 }">宝安区</c:when>
											<c:when test="${user.enterprise.district ==5 }">龙岗区</c:when>
											<c:when test="${user.enterprise.district ==6 }">盐田区</c:when>
											<c:when test="${user.enterprise.district ==7 }">其它区</c:when>
									</c:choose>
									</c:if>
									<c:if test="${user.enterprise.city==1}">
										深圳市外
									</c:if>
								${user.enterprise.address}
								</div>								
							</div>
						</div>
					</div>
					<div class="info-form">
						<form action="" id="linkinfofrom" name="linkinfofrom">
						<input name="id" style="display: none;"  type="text"
										value="${user.id }" />
						<input name="enterprise.id" style="display: none;"  type="text"
										value="${user.enterprise.id }" />
							<div class="control-group">
								<label class="control-label">联系人：</label>
								<div class="controls">
									<input class="input-large" type="text" name="enterprise.linkman"
										value="${user.enterprise.linkman }" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">联系电话：</label>
								<div class="controls">
									<input class="input-large" type="text" id = "enterpriseTel" name="enterprise.tel"
										value="${user.enterprise.tel }" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">联系地址：</label>
								<div class="controls" id="address-city">
									<select id="province" name="enterprise.city">
										<option value="0"  ${user.enterprise.city == 0?'selected':''  }>深圳市</option>
										<option value="1"  ${user.enterprise.city == 1?'selected':''  }>深圳市外</option>
									</select>
									<select class="city" name="enterprise.district">
										<option value="1"  ${user.enterprise.district == 1?'selected':''  }>福田区</option>
										<option value="2"  ${user.enterprise.district == 2?'selected':''  }>南山区</option>
										<option value="3"  ${user.enterprise.district == 3?'selected':''  }>罗湖区</option>
										<option value="4"  ${user.enterprise.district == 4?'selected':''  }>宝安区</option>
										<option value="5"  ${user.enterprise.district == 5?'selected':''  }>龙岗区</option>
										<option value="6"  ${user.enterprise.district == 6?'selected':''  }>盐田区</option>
										<option value="7"  ${user.enterprise.district == 7?'selected':''  }>其他区</option>
									</select>
									<select class="city" name="enterprise.district" id="areaId">
										<option value="" selected="selected">---</option>
									</select>									
									<input class="input-large" type="text" name="enterprise.address"
										value="${user.enterprise.address}" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
									<ul class="">
						<li class="s1" action-data='save' id="linkcommit"><a
							href="javascript:void(0)" class="btn-save">保存</a></li>
							</ul>
								</div>
							</div>								
						</form>
					</div>
				</div>
				<!-- /联系信息 -->
				<div class="col-bar">
					<div class="title">我感兴趣的服务领域</div>
					<ul class="action">
						<li class="s1" action-data='edit'><a
							href="javascript:void(0)">编辑</a></li>
						<li class="s2" action-data='up'><a href="javascript:void(0)"><span
								class="hide">开关</span></a></li>
					</ul>
				</div>
				<div class="switch-content">
					<div class="show-info">
						<div class="control-group">
							<label class="control-label">我感兴趣的服务领域：</label>
							<div class="controls">
								<div class="result-text2" id="myinterestservice1">
									<c:choose>
										<c:when test="${user.interestServices.size()>0}">
											<c:forEach items="${user.interestServices}"
												var="interestServiceItem">
	                       			 <span>${interestServiceItem.text }</span>
	                      		  </c:forEach>
										</c:when>
										<c:otherwise>
	                      	 		暂无
	                      	 	</c:otherwise>
									</c:choose>

								</div>
							</div>
						</div>
					</div>
					<div class="info-form">
						<form action="">
							<div class="control-group">
								<label class="control-label">我感兴趣的服务领域：</label>
								<div class="controls">
								<div class="result-text2" id="myinterestservice2">
									<c:choose>
									<c:when test="${user.interestServices.size()>0}">
											<c:forEach items="${user.interestServices}"
												var="interestServiceItem">
	                       			 <span>${interestServiceItem.text }</span>
	                      		  </c:forEach>
										</c:when>
										<c:otherwise>
	                      	 		暂无
	                      	 	</c:otherwise>
									</c:choose>

								</div>
							</div>
								<div class="controls">
									<input class="service-type" type="button" value="请选择" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
									<ul class="">
						<li class="s1" action-data='save' id="interestservicescommit"><a
							href="javascript:void(0)" class="btn-save">保存</a></li>
							</ul>
								</div>
							</div>								
						</form>
					</div>
				</div>
				<!-- /我关注的服务领域 -->
			</div>
		</div>
		<div class="clr"></div>
	</div>
	<!-- /container -->
	<jsp:include page="../layout/footer.jsp" flush="true" />
	<!-- 我感兴趣的服务领域 -->
	<div class="overlay interesting">
		<div class="service-layout">
			<div class="service-head">
				请选择服务类别 
				<!-- <span class="tip"><i>*注意</i>:请准确选择符合企业资质的服务,以便顺利通过审核。最多可选10项</span> -->
				<a class="close" href="javascript:void(0)">X</a>
			</div>
			<div class="service-body">
				<div class="selected-items">
					<div class="selected-name">已选择服务类别：</div>
					<ul class="selected-list">
					<c:forEach items="${user.interestServices}" var="item">
						<li>
							<input type="checkbox" value="${item.id }" name="${item.id }" checked="checked"/>
							<span>${item.text }</span>
						</li>
					</c:forEach>
					</ul>
				</div>
				<c:forEach var="parent" items="${requestScope.root.children }">
					<div class="service-row">
						<div class="service-name">${parent.text }</div>
						<ul class="service-list">
							<c:forEach var="leaf" items="${parent.children }">
								<li><input type="checkbox" name="${leaf.id }"
									value="${leaf.id }" ${user.interestServices.contains(leaf)==true?'checked="checked"':'' }  /><span>${leaf.text }</span></li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<!-- 我的服务 -->
	<div class="overlay myservice">
		<div class="service-layout-my">
			<div class="service-head-my">
				请选择服务类别 <span class="tip"><i>*注意</i>:请准确选择符合企业资质的服务,以便顺利通过审核。最多可选10项</span><a
					class="close" href="javascript:void(0)">X</a>
			</div>
			<div class="service-body-my">
				<div class="selected-items-my">
					<div class="selected-name-my">已选择服务类别：</div>
					<ul class="selected-list-my">
					<c:forEach items="${user.enterprise.myServices }" var="item">
						<li>
							<input type="checkbox" value="${item.id}" name="${item.id }" checked="checked">
							<span>${item.text }</span>
						</li>
					</c:forEach>
					</ul>
				</div>
				<c:forEach var="parent" items="${requestScope.root.children }">
					<div class="service-row-my">
						<div class="service-name-my">${parent.text }</div>
						<ul class="service-list-my">
							<c:forEach var="leaf" items="${parent.children }">
								<li>
									<input  type="checkbox" name="${leaf.id }"
										value="${leaf.id }" ${user.enterprise.myServices.contains(leaf)==true?'checked="checked"':'' } /><span>${leaf.text }</span>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
<!-- <script type="text/javascript" src="jsLib/jquery/jquery.form.js"></script>
	 <script type="text/javascript" src="jsLib/jquery.easyui/jquery.easyui.min.js"></script>
	 <script type="text/javascript" src="jsLib/jquery.easyui/locale/easyui-lang-zh_CN.js"></script> 
-->
<script type="text/javascript" src="jsLib/jNotify/jNotify.jquery.min.js"></script>
<script type="text/javascript" src="resources/js/ucenter/user_manage.js"></script>
<script type="text/javascript" src="jsLib/kindeditor-4.1.9/kindeditor.js"></script>
<script type="text/javascript" src="resources/js/main/Selecter/jquery.fs.selecter.js"></script>
</body>
</html>