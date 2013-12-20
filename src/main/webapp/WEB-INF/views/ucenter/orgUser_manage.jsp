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
							<label class="control-label">机构简称：</label>
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
										<c:when test="${user.enterprise.type == 3 }">服务机构</c:when>
										<c:when test="${user.enterprise.type == 4 }">政府机构</c:when>
									</c:choose> 
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">机构介绍：</label>
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
								<label class="control-label">机构简称：</label>
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
											<c:when test="${user.enterprise.type == 3 }">服务机构</c:when>
											<c:when test="${user.enterprise.type == 4 }">政府机构</c:when>
										</c:choose> />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">机构介绍：</label>
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
							<!-- 1、金融 2、软件及信息服务 3、 物流 4、物联网 5、法律 6、财税 7、科技 8、咨询 9、其他-->
							<label class="control-label">行业：</label>
							<div class="controls">
								<div class="result-text">
									<c:choose>
										<c:when test="${user.enterprise.orgIndustry == '1' }">
	                        			金融
	                        		</c:when>
										<c:when test="${user.enterprise.orgIndustry == '2' }">
	                        			软件及信息服务
	                        		</c:when>
										<c:when test="${user.enterprise.orgIndustry == '3' }">
	                        			物流
	                        		</c:when>
										<c:when test="${user.enterprise.orgIndustry == '4' }">
	                        			物联网
	                        		</c:when>
										<c:when test="${user.enterprise.orgIndustry == '5' }">
	                        			法律
	                        		</c:when>
										<c:when test="${user.enterprise.orgIndustry == '6' }">
	                        			财税
	                        		</c:when>
										<c:when test="${user.enterprise.orgIndustry == '7' }">
	                        			科技
	                        		</c:when>
										<c:when test="${user.enterprise.orgIndustry == '8' }">
	                        			咨询
	                        		</c:when>
										<c:when test="${user.enterprise.orgIndustry == '9' }">
	                        			其他
	                        		</c:when>
									<c:otherwise>
	                        			待完善
	                        		</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="control-group">
							<!-- 1、国营 2、民营 3、三资 4、股份制 5、合伙制 6、民非  7、其他； 必填项 下拉框显示 -->
							<label class="control-label">机构性质：</label>
							<div class="controls">
								<div class="result-text">
									<c:choose>
										<c:when test="${user.enterprise.orgProperty == '1'}">
	                        		国营
	                        	</c:when>
										<c:when test="${user.enterprise.orgProperty == '2'}">
	                        		民营
	                        	</c:when>
										<c:when test="${user.enterprise.orgProperty == '3'}">
	                        		三资
	                        	</c:when>
										<c:when test="${user.enterprise.orgProperty == '4'}">
	                        		股份制
	                        	</c:when>
										<c:when test="${user.enterprise.orgProperty == '5'}">
	                        		合伙制
	                        	</c:when>
										<c:when test="${user.enterprise.orgProperty == '6'}">
	                        		民非
	                        	</c:when>
								<c:when test="${user.enterprise.orgProperty == '7'}">
	                        		其他
	                        	</c:when>
								<c:otherwise>
	                        		待完善
	                        	</c:otherwise>
								</c:choose>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">经营范围：</label>
							<div class="controls">
								<div class="result-text">
									${fn:substring(my:replaceHTML(user.enterprise.business), 0, 88) }
									${fn:length(my:replaceHTML(user.enterprise.business)) > 88 ? '...' :'' }
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">优势服务领域：</label>
							<div class="controls">
								<div class="result-text">
									${fn:substring(my:replaceHTML(user.enterprise.advantageServiceAreas), 0, 88) }
									${fn:length(my:replaceHTML(user.enterprise.advantageServiceAreas)) > 88 ? '...' :'' }
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">总资产(上年、万元)：</label>
							<div class="controls">
								<div class="result-text"><fmt:formatNumber value="${user.enterprise.totalAssets}" pattern="0.0000"/></div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">年营业额(上年、万元)：</label>
							<div class="controls">
								<div class="result-text">
									<fmt:formatNumber value="${user.enterprise.salesRevenue}" pattern="0.0000"/>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">利润或净收入(上年、万元)：</label>
							<div class="controls">
								<div class="result-text"><fmt:formatNumber value="${user.enterprise.profit}" pattern="0.0000"/></div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">成立日期：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.openedTime }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">职工人数：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.staffNumber }人</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">组织机构代码：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.icRegNumber }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">机构电话：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.tel }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">机构传真：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.orgFax }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">公司网址：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.orgWebsite }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">机构地址：</label>
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
						<div class="control-group">
							<label class="control-label">社会荣誉：</label>
							<div class="controls">
								<div class="service-description">
									${fn:substring(my:replaceHTML(user.enterprise.honorSociety), 0, 88) }
									${fn:length(my:replaceHTML(user.enterprise.honorSociety)) > 88 ? '...' :'' }
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">专业资质：</label>
							<div class="controls">
								<div class="service-description">
									${fn:substring(my:replaceHTML(user.enterprise.professionalQualifications), 0, 88) }
									${fn:length(my:replaceHTML(user.enterprise.professionalQualifications)) > 88 ? '...' :'' }
								</div>
							</div>
						</div>
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
							<input type="hidden" name="enterprise.industryType" value="${user.enterprise.industryType }" />
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
								<label class="control-label">行业：</label>
								<div class="controls">
									<div class="">
										<!-- 1、金融 2、软件及信息服务 3、 物流 4、物联网 5、法律 6、财税 7、科技 8、咨询 9、其他-->
										<select name="enterprise.orgIndustry" id="orgIndustry"
											class="selected-js se1">
											<option value="0">请选择</option>
											<option value="1"
												${user.enterprise.orgIndustry == '1' ?'selected':'' }>金融</option>
											<option value="2"
												${user.enterprise.orgIndustry == '2' ?'selected':'' }>软件及信息服务</option>
											<option value="3"
												${user.enterprise.orgIndustry == '3' ?'selected':'' }>物流</option>
											<option value="4"
												${user.enterprise.orgIndustry == '4' ?'selected':'' }>物联网</option>
											<option value="5"
												${user.enterprise.orgIndustry == '5' ?'selected':'' }>法律</option>
											<option value="6"
												${user.enterprise.orgIndustry == '6' ?'selected':'' }>财税</option>
											<option value="7"
												${user.enterprise.orgIndustry == '7' ?'selected':'' }>科技</option>
											<option value="8"
												${user.enterprise.orgIndustry == '8' ?'selected':'' }>咨询</option>
											<option value="9"
												${user.enterprise.orgIndustry == '9' ?'selected':'' }>其他</option>
										</select>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">机构性质：</label>
								<div class="controls">
									<div class="">
										<!-- 1、国营 2、民营 3、三资 4、股份制 5、合伙制 6、民非  7、其他； 必填项 下拉框显示 -->
										<select name="enterprise.orgProperty" id="orgProperty"
											class="selected-js se2">
											<option value="0">请选择</option>
											<option value="1"
												${user.enterprise.orgProperty == 1?'selected':''  }>国营</option>
											<option value="2"
												${user.enterprise.orgProperty == 2?'selected':'' }>民营</option>
											<option value="3"
												${user.enterprise.orgProperty == 3?'selected':'' }>三资</option>
											<option value="4"
												${user.enterprise.orgProperty == 4?'selected':'' }>股份制</option>
											<option value="5"
												${user.enterprise.orgProperty == 5?'selected':'' }>合伙制</option>
											<option value="6"
												${user.enterprise.orgProperty == 6?'selected':'' }>民非</option>
											<option value="7"
												${user.enterprise.orgProperty == 7?'selected':'' }>其他</option>
										</select>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">经营范围：</label>
								<div class="controls">
									<textarea id="org_business" style="width:480px;" rows="6" name="enterprise.business">${user.enterprise.business }</textarea>
									<span style="color:#F75050">输入文字不超过100字</span>
									<%-- <textarea id="editor_business" name="enterprise.business" style="width:300px;">${user.enterprise.business }</textarea> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">优势服务领域：</label>
								<div class="controls">
									<textarea id="org_advantageServiceAreas" style="width:480px;" rows="6" name="enterprise.advantageServiceAreas" >${user.enterprise.advantageServiceAreas }</textarea>
									<span style="color:#F75050">输入文字不超过100字</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">总资产(上年、上万)：</label>
								<div class="controls">
									<input name="enterprise.totalAssets" id="totalAssets" class="input-large"
										type="text" value="<fmt:formatNumber value="${user.enterprise.totalAssets}" pattern="0.0000"/>" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">年营业额(上年、上万)：</label>
								<div class="controls">
									<input name="enterprise.salesRevenue" id="salesRevenue" class="input-large"
										type="text" value="<fmt:formatNumber value="${user.enterprise.salesRevenue}" pattern="0.0000"/>" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">利润或净收入(上年、上万)：</label>
								<div class="controls">
									<input name="enterprise.profit" id="profit" class="input-large"
										type="text" value="<fmt:formatNumber value="${user.enterprise.profit}" pattern="0.0000"/>" />
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
								<label class="control-label">职工人数：</label>
								<div class="controls">
									<input name="enterprise.staffNumber" id="staffNumber" class="input-large"
										type="text" value="${user.enterprise.staffNumber }" />人
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">组织机构代码：</label>
								<div class="controls">
									<input name="enterprise.icRegNumber" id="icRegNumber" type="text"  class="input-large" value="${user.enterprise.icRegNumber }"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">机构电话：</label>
								<div class="controls">
									<input name="enterprise.tel" type="text" id="orgTel" class="input-large" value="${user.enterprise.tel }"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">机构传真：</label>
								<div class="controls">
									<input name="enterprise.orgFax" type="text" id="orgFax" class="input-large" value="${user.enterprise.orgFax }"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">公司网址：</label>
								<div class="controls">
									<input name="enterprise.orgWebsite" type="text" id="orgWebsite" class="input-large" value="${user.enterprise.orgWebsite }"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">机构地址：</label>
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
								<label class="control-label">社会荣誉：</label>
								<div class="controls">
									<textarea id="org_honorSociety" style="width:480px;" rows="6" name="enterprise.honorSociety" >${user.enterprise.honorSociety }</textarea>
									<span style="color:#F75050">输入文字不超过100字</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">专业资质：</label>
								<div class="controls">
									<textarea id="org_professionalQualifications" style="width:480px;" rows="6" name="enterprise.professionalQualifications" >${user.enterprise.professionalQualifications }</textarea>
									<span style="color:#F75050">输入文字不超过100字</span>
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
							<label class="control-label">法定代表人：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.legalPerson }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">手机：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.legalRepresentativeMobile }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Email：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.email }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">总经理姓名：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.generalName }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">手机：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.generalManagerMobile }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Email：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.generalManagerMobile }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">联系人姓名：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.linkman }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">手机：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.contactNameMobile }</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Email：</label>
							<div class="controls">
								<div class="result-text">${user.enterprise.contactNameEmail }</div>
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
								<label class="control-label">法定代表人：</label>
								<div class="controls">
									<input class="input-large" type="text" name="enterprise.legalPerson"
										value="${user.enterprise.legalPerson }" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">手机：</label>
								<div class="controls">
									<input class="input-large" type="text" id="legalRepresentativeMobile" name="enterprise.legalRepresentativeMobile"
										value="${user.enterprise.legalRepresentativeMobile }" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Email：</label>
								<div class="controls">
									<input class="input-large" type="text" id="email" name="enterprise.email"
										value="${user.enterprise.email }" />
								</div>
							</div>			
										
							<div class="control-group">
								<label class="control-label">总经理姓名：</label>
								<div class="controls">
									<input class="input-large" type="text" name="enterprise.generalName"
										value="${user.enterprise.generalName }" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">手机：</label>
								<div class="controls">
									<input class="input-large" type="text" id="generalManagerMobile" name="enterprise.generalManagerMobile"
										value="${user.enterprise.generalManagerMobile }" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Email：</label>
								<div class="controls">
									<input class="input-large" type="text" id="generalManagerEmail" name="enterprise.generalManagerEmail"
										value="${user.enterprise.generalManagerEmail }" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">联系人姓名：</label>
								<div class="controls">
									<input class="input-large" type="text" name="enterprise.linkman"
										value="${user.enterprise.linkman }" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">手机：</label>
								<div class="controls">
									<input class="input-large" type="text" id="contactNameMobile" name="enterprise.contactNameMobile"
										value="${user.enterprise.contactNameMobile }" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Email：</label>
								<div class="controls">
									<input class="input-large" type="text" id="contactNameEmail" name="enterprise.contactNameEmail"
										value="${user.enterprise.contactNameEmail }" />
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
<script type="text/javascript" src="jsLib/jNotify/jNotify.jquery.min.js"></script>
<script type="text/javascript" src="resources/js/ucenter/orgUser_manage.js"></script>
<script type="text/javascript" src="jsLib/kindeditor-4.1.9/kindeditor.js"></script>
<script type="text/javascript" src="resources/js/main/Selecter/jquery.fs.selecter.js"></script>
</body>
</html>